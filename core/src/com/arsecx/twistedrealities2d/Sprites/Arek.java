package com.arsecx.twistedrealities2d.Sprites;

import com.arsecx.twistedrealities2d.LevelController;
import com.arsecx.twistedrealities2d.Levels.Level_1;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Arek extends Sprite {
    public World world;
    public Body body;
    private Array<TextureRegion> frames;
    private Animation idle;
    private Animation run;
    private Animation jump;
    private Animation death_spike;

    public boolean isFacingRight() {
        return facingRight;
    }

    private boolean facingRight;

    private float timePassed;
    public enum State {IDLE, RUNNING, JUMPING, FALLING, DEATH_SPIKE};
    public State currentState;
    public State previousState;
    public Arek(World world, Level_1 level1) {
        super(level1.getAtlas_Arek().findRegion("character-idle"));
        this.world = world;
        currentState = State.IDLE;
        previousState = State.IDLE;
        timePassed = 0;
        facingRight = true;
        frames = new Array<>();

        for(int i = 1; i < 5; i++) { // 4 frames for idle
            frames.add(new TextureRegion(getTexture(),  338+(i * 17), 1, 17, 32));
        }
        idle = new Animation(1/3f, frames);

        frames.clear();

        frames.add(new TextureRegion(getTexture(), 254, 1, 23, 32)); // jump anim 1
        frames.add(new TextureRegion(getTexture(), 277, 1, 24, 31)); // jump anim 2
        frames.add(new TextureRegion(getTexture(), 301, 1, 25, 30)); // jump anim 3
        frames.add(new TextureRegion(getTexture(), 326, 1, 27, 32)); // jump anim 4

        jump = new Animation(0.1f, frames);

        frames.clear();

        frames.add(new TextureRegion(getTexture(), 138, 1, 19, 32));
        frames.add(new TextureRegion(getTexture(), 157, 1, 14, 32));
        frames.add(new TextureRegion(getTexture(), 171, 1, 20, 32));
        frames.add(new TextureRegion(getTexture(), 191, 1, 27, 32));
        frames.add(new TextureRegion(getTexture(), 218, 1, 15, 32));
        frames.add(new TextureRegion(getTexture(), 233, 1, 19, 32));

        run = new Animation(0.1f, frames);

        frames.clear();

        frames.add(new TextureRegion(getTexture(), 1, 1, 17, 32));
        frames.add(new TextureRegion(getTexture(), 18, 1, 23, 32));
        frames.add(new TextureRegion(getTexture(), 41, 1, 20, 27));
        frames.add(new TextureRegion(getTexture(), 61, 1, 25, 14));
        frames.add(new TextureRegion(getTexture(), 86, 1, 25, 11));
        frames.add(new TextureRegion(getTexture(), 111, 1, 25, 8));

        death_spike = new Animation(0.1f, frames);

        frames.clear();

        defineCharacter();
        setBounds(0, 0, 19, 32);
    }

    public void defineCharacter() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(156, 156); // the spawn location, set by the amount of tiles
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();

        fixtureDef.filter.categoryBits = LevelController.AREK;
        fixtureDef.filter.maskBits = LevelController.SPIKE | LevelController.WEAK_PLATF | LevelController.BASE_PLATF;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(17/2f, 15.6f);
//        shape.setRadius(1);

//        CircleShape shape = new CircleShape();
//        shape.setRadius(15.8f);

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);

        EdgeShape feet = new EdgeShape();
        feet.set(new Vector2(7, -15.8f), new Vector2(-7, -15.8f));
        fixtureDef.shape = feet;
        fixtureDef.isSensor = true;

        body.createFixture(fixtureDef).setUserData("feet");

    }

    public void update() {
        setPosition(body.getPosition().x - getWidth()/2, body.getPosition().y - getHeight()/2);
        setRegion(getFrame());
    }

    public TextureRegion getFrame() {
        TextureRegion region = null;
        currentState = getCurrentState();
        switch (currentState) {
            case JUMPING:
                region = (TextureRegion) jump.getKeyFrame(timePassed);
                break;
            case RUNNING:
                region = (TextureRegion) run.getKeyFrame(timePassed, true);
                break;
            case FALLING:
            case IDLE:
                region = (TextureRegion) idle.getKeyFrame(timePassed, true);
                break;
            case DEATH_SPIKE:
                region = (TextureRegion) death_spike.getKeyFrame(timePassed);
        }
        if((body.getLinearVelocity().x < 0 || !facingRight) && !region.isFlipX()) {
            region.flip(true, false);
            facingRight = false;
        }
        else if((body.getLinearVelocity().x > 0 || facingRight) && region.isFlipX()) {
            region.flip(true, false);
            facingRight = true;
        }

        timePassed = currentState == previousState ? timePassed+= Gdx.graphics.getDeltaTime() : 0;
        previousState = currentState;
        return region;
    }

    public State getCurrentState() {
        if((body.getLinearVelocity().y > 0)|| (body.getLinearVelocity().y < 0 && previousState==State.JUMPING))
            return State.JUMPING;
        else if(body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if(body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else return State.IDLE;
    }
}
