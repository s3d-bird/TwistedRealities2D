package com.arsecx.twistedrealities2d;

import com.arsecx.twistedrealities2d.Sprites.BasePlatform;
import com.arsecx.twistedrealities2d.Sprites.Spikes;
import com.arsecx.twistedrealities2d.Sprites.WeakPlatform;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class WorldCreator {
    public WorldCreator(World world, TiledMap map) {
        BodyDef bodyDef = new BodyDef(); // defines the body
        Body body;
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Fixture fixture;

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
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth()/2), (rectangle.getY() + rectangle.getHeight()/2));

            body = world.createBody(bodyDef);

            shape.setAsBox((rectangle.getWidth()/2), (rectangle.getHeight()/2));
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }
    }
}
