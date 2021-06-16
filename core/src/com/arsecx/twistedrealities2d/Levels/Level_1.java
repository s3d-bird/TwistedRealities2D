package com.arsecx.twistedrealities2d.Levels;

import com.arsecx.twistedrealities2d.LevelController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level_1 implements Screen {

    LevelController levelController; // contains the sprite batch
    Viewport viewport; // primary viewport for the camera
    Camera camera; // character follow camera; or the main basic camera

    public Level_1 (LevelController lvlController) {
        this.levelController = lvlController;
        camera = new OrthographicCamera();
        viewport = new FitViewport(256, 144, camera);
    }

    public void inputController() {
        // will be used to handle user input
    }

    public void update() {
        // this function will deal with level logic and post-input functionalities
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
    }

    @Override
    public void resize(int width, int height) {

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

    }
}
