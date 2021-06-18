package com.arsecx.twistedrealities2d.Levels;

import com.arsecx.twistedrealities2d.LevelController;
import com.arsecx.twistedrealities2d.Sprites.Arek;
import com.arsecx.twistedrealities2d.WorldCreator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level_1 implements Screen {

    LevelController levelController; // contains the sprite batch
    Viewport viewport; // primary viewport for the camera
    Camera camera; // character follow camera; or the main basic camera
    WorldCreator worldCreator;
    // TILEMAP Loader
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // Box 2D World
    private World world;
    private Box2DDebugRenderer debugRenderer; // This renderer is for development only
    // TODO Remove this renderer before releasing the game

    // Sprites
    private Arek player;

    public Level_1 (LevelController lvlController) {
        this.levelController = lvlController;
        camera = new OrthographicCamera();
        viewport = new FitViewport(256, 144, camera);

        // map loading
        map = new TmxMapLoader().load("Level_1/Level_1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1);

        world = new World(new Vector2(0, -100), true); // Vector2 is the vector for gravity
        debugRenderer = new Box2DDebugRenderer();
        worldCreator = new WorldCreator(world, map);
        player = new Arek(world);
    }

    public void inputController() {
        // will be used to handle user input
        if(Gdx.input.isTouched()) {
            player.body.applyLinearImpulse(new Vector2(3, 6f), player.body.getWorldCenter(), true);
        }
    }

    public void update() {
        // this function will deal with level logic and post-input functionalities
        inputController();

        world.step(1/60f, 6, 2);

        camera.position.x = player.body.getPosition().x;
        camera.position.y = player.body.getPosition().y;
        camera.update();
        renderer.setView((OrthographicCamera) camera);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(); // update needs to be called every time render is called since it handles all the business and input logic

        Gdx.gl.glClearColor(0, 0, 0, 1); // set the background color to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // further code will be added when HUD and Level are created
        renderer.render(); // render the map

        // FOR DEBUG ONLY
        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        debugRenderer.dispose();

    }
}
