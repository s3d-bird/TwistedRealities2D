package com.arsecx.twistedrealities2d.Sprites;

import com.arsecx.twistedrealities2d.LevelController;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Arek extends Sprite {
    public World world;
    public Body body;

    public Arek(World world) {
        this.world = world;
        defineCharacter();
    }

    public void defineCharacter() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32, 32);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();

        CircleShape shape = new CircleShape();
        shape.setRadius(20);

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }
}
