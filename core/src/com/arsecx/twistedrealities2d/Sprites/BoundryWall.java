package com.arsecx.twistedrealities2d.Sprites;

        import com.arsecx.twistedrealities2d.LevelController;
        import com.badlogic.gdx.maps.tiled.TiledMap;
        import com.badlogic.gdx.math.Rectangle;
        import com.badlogic.gdx.physics.box2d.World;

public class BoundryWall extends InteractiveTiles {
    public BoundryWall(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(LevelController.BASE_PLATF);
    }

    @Override
    public void onPlayerCollision() {

    }
}
