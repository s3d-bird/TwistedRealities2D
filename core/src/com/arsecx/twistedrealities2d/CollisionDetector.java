package com.arsecx.twistedrealities2d;

import com.arsecx.twistedrealities2d.Sprites.InteractiveTiles;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

public class CollisionDetector implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixture = contact.getFixtureA();
        Fixture fixture1 = contact.getFixtureB();

        if(fixture.getUserData() == "feet" || fixture1.getUserData() == "feet") {
            Fixture feet = fixture.getUserData().equals("feet") ? fixture : fixture1;
            Fixture object = feet == fixture ? fixture1 : fixture;

            if(object.getUserData() != null &&
                    InteractiveTiles.class.isAssignableFrom(object.getUserData().getClass())) {
                ((InteractiveTiles) object.getUserData()).onPlayerCollision();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
