package com.arsecx.twistedrealities2d.UIElements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class WinScreen implements Disposable {
    public Stage stage;

    private final Label gameWonLabel;
    private final Label restartLabel;

    private boolean restartPressed;

    public boolean isRestartPressed() {
        return restartPressed;
    }

    public WinScreen(SpriteBatch batch) {
        Viewport viewport = new FitViewport(256, 144, new OrthographicCamera());
        stage = new Stage(viewport, batch);
        Table table = new Table();
        table.center();

        gameWonLabel = new Label("Congrats, You Made it", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        restartLabel = new Label("Go Again for a better time score!", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        restartLabel.addListener ( new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                restartPressed = true;
                return true;
            }

        } );
        table.setFillParent(true);
        table.add(gameWonLabel);
        table.row();
        table.add();
        table.row();
        table.add(restartLabel);
        stage.addActor(table);

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}