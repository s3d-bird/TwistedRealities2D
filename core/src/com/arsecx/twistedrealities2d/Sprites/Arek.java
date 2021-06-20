package com.arsecx.twistedrealities2d.Sprites;

import com.arsecx.twistedrealities2d.LevelController;
import com.arsecx.twistedrealities2d.Levels.Level_1;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Arek extends Sprite {
    public World world;
    public Body body;
    private Array<TextureRegion> frames;
    private Animation idle;
    private Animation run;
    private Animation jump;

    private boolean facingRight;

    private float timePassed;
    public enum State {IDLE, RUNNING, JUMPING, FALLING};
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
            frames.add(new TextureRegion(getTexture(),  201+(i * 17), 1, 17, 32));
        }
        idle = new Animation(1/3f, frames);

        frames.clear();

        frames.add(new TextureRegion(getTexture(), 117, 1, 23, 32)); // jump anim 1
        frames.add(new TextureRegion(getTexture(), 140, 1, 24, 31)); // jump anim 2
        frames.add(new TextureRegion(getTexture(), 164, 1, 25, 30)); // jump anim 3
        frames.add(new TextureRegion(getTexture(), 189, 1, 27, 32)); // jump anim 4

        jump = new Animation(0.1f, frames);

        frames.clear();

        frames.add(new TextureRegion(getTexture(), 1, 1, 19, 32));
        frames.add(new TextureRegion(getTexture(), 20, 1, 14, 32));
        frames.add(new TextureRegion(getTexture(), 34, 1, 20, 32));
        frames.add(new TextureRegion(getTexture(), 54, 1, 27, 32));
        frames.add(new TextureRegion(getTexture(), 81, 1, 15, 32));
        frames.add(new TextureRegion(getTexture(), 96, 1, 19, 32));

        run = new Animation(0.1f, frames);

        frames.clear();
        defineCharacter();
        setBounds(0, 0, 17, 32);
    }

    public void defineCharacter() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32, 32);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();

        CircleShape shape = new CircleShape();
        shape.setRadius(16);

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
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
        if(body.getLinearVelocity().y > 0 || (body.getLinearVelocity().y < 0 && previousState==State.JUMPING))
            return State.JUMPING;
        else if(body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if(body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else return State.IDLE;
    }
}
