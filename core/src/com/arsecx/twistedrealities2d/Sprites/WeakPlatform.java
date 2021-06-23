package com.arsecx.twistedrealities2d.Sprites;

import com.arsecx.twistedrealities2d.LevelController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Timer;
import java.util.TimerTask;

public class WeakPlatform extends InteractiveTiles {
    public WeakPlatform(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(LevelController.WEAK_PLATF);
    }

    @Override
    public void onPlayerCollision() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                setCategoryFilter(LevelController.DESTROYED_PLATF);
                Gdx.app.log("Collision on breakable" , "dd");
                getCell().setTile(null);
            }
        }, 5000);
    }
}
