package com.arsecx.twistedrealities2d.Levels;

import com.arsecx.twistedrealities2d.CollisionDetector;
import com.arsecx.twistedrealities2d.LevelController;
import com.arsecx.twistedrealities2d.Sprites.Arek;
import com.arsecx.twistedrealities2d.UIElements.GameOver;
import com.arsecx.twistedrealities2d.UIElements.MovementButtons;
import com.arsecx.twistedrealities2d.WorldCreator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level_1 implements Screen {

    LevelController levelController; // contains the sprite batch
    Viewport viewport; // primary viewport for the camera
    Camera camera; // character follow camera; or the main basic camera
    WorldCreator worldCreator;
    // TILEMAP Loader
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;

    // Box 2D World
    private final World world;

    // Sprites
    private final Arek player;

    // Texture loader for Sprite Sheets
    private final TextureAtlas arek;

    // UI Elements
    MovementButtons buttons;
    GameOver gameOverScreen;
    // Turn Around and move while jump check
    private boolean movedAlready;
    private boolean hasDoubleJumped;

    public Level_1 (LevelController lvlController) {
        this.levelController = lvlController;
        camera = new OrthographicCamera();
        viewport = new FitViewport(256, 144, camera);

        // map loading
        map = new TmxMapLoader().load("Level_1/Level_1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1);

        // creating the world
        world = new World(new Vector2(0, -100), true); // Vector2 is the vector for gravity
        worldCreator = new WorldCreator(world, map);

        // Setting up the character and sprite sheet atlas
        arek = new TextureAtlas("SpriteSheet/Arek-SpriteSheet.atlas");
        player = new Arek(world, this);
        world.setContactListener(new CollisionDetector());
        // Input buttons
        buttons = new MovementButtons(this.levelController.batch);
        //GameOver Screen
        gameOverScreen= new GameOver ( levelController.batch );

        movedAlready = false;
        hasDoubleJumped = false;
    }

    public TextureAtlas getAtlas_Arek() {
        return arek;
    }

    public void inputController() {
        // will be used to handle user input
        if(buttons.isRightPressed() && player.body.getLinearVelocity().y == 0 && player.currentState != Arek.State.FALLING ) {
            movedAlready = false; // a temporary fix to detect that player is on ground
            hasDoubleJumped = false;
            if(buttons.isJumpPressed()) {
                player.body.setLinearVelocity(250, 165);
            }
            else
                player.body.setLinearVelocity(220, 0);
        }
        else if(buttons.isLeftPressed() && player.currentState != Arek.State.FALLING && player.body.getLinearVelocity().y == 0 ) {
            movedAlready = false;
            hasDoubleJumped = false;
            if(buttons.isJumpPressed()){
                player.body.setLinearVelocity(-250, 165);
            }
            else
                player.body.setLinearVelocity(-220, 0);
        }
//        else if(buttons.isRightPressed () && (player.currentState == Arek.State.FALLING || player.currentState == Arek.State.JUMPING) && player.body.getLinearVelocity().x <=40) {
//
//            player.body.setLinearVelocity(40, 0);
//        }
//        else if(buttons.isRightPressed() && buttons.isJumpPressed()) {
//            player.body.setLinearVelocity(player.body.getLinearVelocity().x + 20f, 60);
//        }
//        else if(buttons.isLeftPressed() && buttons.isJumpPressed()) {
//            player.body.setLinearVelocity(-(player.body.getLinearVelocity().x - 20f), 60);
//        }
        else if(buttons.isJumpPressed() && player.currentState != Arek.State.JUMPING) {
            if(player.isFacingRight()) {
                player.body.setLinearVelocity(player.body.getLinearVelocity().x + 150, 130);
            }
            else if (!player.isFacingRight()) {
                player.body.setLinearVelocity(player.body.getLinearVelocity().x - 150, 130);
            }

        }
        else if(buttons.isDoubleJmpPressed() && !hasDoubleJumped && player.body.getLinearVelocity().y != 0) {
            System.out.println("boooo");
            if(player.isFacingRight()) {
                player.body.setLinearVelocity(player.body.getLinearVelocity().x + 100, 90);
                hasDoubleJumped = true;
            }
            else if (!player.isFacingRight()) {
                player.body.setLinearVelocity(player.body.getLinearVelocity().x - 100, 90);
                hasDoubleJumped = true;
            }
        }
//        else if(buttons.isJumpPressed()) {
//            if(buttons.isRightPressed() && player.currentState == Arek.State.JUMPING) {
//                player.body.setLinearVelocity(player.body.getLinearVelocity().x+10, player.body.getLinearVelocity().y);
//            }
//            else if (buttons.isLeftPressed() && player.currentState == Arek.State.JUMPING) {
//                player.body.setLinearVelocity(player.body.getLinearVelocity().x-10, player.body.getLinearVelocity().y);
//            }
//        }
        else if(buttons.isRightPressed() && player.body.getLinearVelocity().y != 0) // turn while in air
        {
            if(!movedAlready) { // if player hasn't already moved; without this check; player will just fly
                player.body.setLinearVelocity(player.body.getLinearVelocity().x+30, player.body.getLinearVelocity().y);
                movedAlready = true;
            }
        }
        else if(buttons.isLeftPressed() && player.body.getLinearVelocity().y != 0)
        {
            if(!movedAlready) { // if player hasn't already moved; without this check; player will just fly
                player.body.setLinearVelocity(player.body.getLinearVelocity().x-30, player.body.getLinearVelocity().y);
                movedAlready = true;
            }
        }
        else if(!buttons.isJumpPressed() && !buttons.isLeftPressed() && !buttons.isRightPressed()) {
            player.body.setLinearVelocity(0, player.body.getLinearVelocity().y);
        }
    }

    public void update() {
        // this function will deal with level logic and post-input functionalities
        inputController();

        world.step(1/60f, 6, 2);
//        if(player.isOnLand()) {
//            movedAlready = false;
//        }
        player.update();
//        if(player.body.getUserData() == "death_spike") {
//            Gdx.app.log("asdasd", "");
//        }
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

        // Render the sprite
        levelController.batch.setProjectionMatrix(camera.combined);
        levelController.batch.begin();
        player.draw(levelController.batch);
        levelController.batch.end();

        // Render the UI
        levelController.batch.setProjectionMatrix(buttons.stage.getCamera().combined);
        buttons.stage.act(delta);
        buttons.stage.draw();

        if(player.currentState == Arek.State.DEATH_SPIKE) {
                gameOver ();
        }
    }

    public void gameOver() {
        levelController.batch.setProjectionMatrix(gameOverScreen.stage.getCamera().combined);
        gameOverScreen.stage.act(Gdx.graphics.getDeltaTime());
        gameOverScreen.stage.draw();
        buttons.stage.clear();
        Gdx.input.setInputProcessor ( gameOverScreen.stage );
        if (gameOverScreen.isRetryPressed ( )) {
            dispose();
            levelController.setScreen(new Level_1(levelController));
//            player.currentState = Arek.State.IDLE;
//            player = new Arek(world, this );
//            buttons = new MovementButtons(this.levelController.batch);
//            worldCreator = new WorldCreator(world, map);
//            buttons.stage.act(Gdx.graphics.getDeltaTime());
//            buttons.stage.draw ();
//            gameOverScreen.stage.clear ();
//            Gdx.input.setInputProcessor ( buttons.stage );

        }
    }
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        buttons.stage.getViewport().update(width, height);
        gameOverScreen.stage.getViewport().update(width, height);
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
        gameOverScreen.dispose();
        buttons.dispose();
    }

}
