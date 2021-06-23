package com.arsecx.twistedrealities2d.Sprites;

import com.arsecx.twistedrealities2d.LevelController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Spikes extends InteractiveTiles {
    public Spikes(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(LevelController.SPIKE);
    }

    @Override
    public void onPlayerCollision() {
        Gdx.app.log("Player is on spike", "OOOF");

    }
}
