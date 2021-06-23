package com.arsecx.twistedrealities2d;

import com.arsecx.twistedrealities2d.Sprites.BasePlatform;
import com.arsecx.twistedrealities2d.Sprites.BoundryWall;
import com.arsecx.twistedrealities2d.Sprites.Spikes;
import com.arsecx.twistedrealities2d.Sprites.WeakPlatform;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class WorldCreator {
    public WorldCreator(World world, TiledMap map) {
        // The base platform
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            new BasePlatform(world, map, rectangle);
        }

        // The Spikes
        for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            new Spikes(world, map, rectangle);
        }

        // The breakable platforms
        for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            new WeakPlatform(world, map, rectangle);
        }

        // The Border Wall, so player don't jump out of map
        for (MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            new BoundryWall(world, map, rectangle);
        }
    }
}
