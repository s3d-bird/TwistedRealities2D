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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameOver {
    public Stage stage;
    private Table table;

    private Viewport viewport;

    private Label gameOverLabel;
    private Label retryLabel;

    private boolean retryPressed;

    public boolean isRetryPressed() {
        return retryPressed;
    }

    public GameOver(SpriteBatch batch) {
        viewport = new FitViewport(256, 144, new OrthographicCamera());
        stage = new Stage(viewport, batch);
//        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.center();

        gameOverLabel = new Label("GAME OVER", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        retryLabel = new Label("RETRY", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        retryLabel.addListener ( new InputListener (){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
              retryPressed=true;


                return true;
            }

        } );


        table.setFillParent(true);
        table.add(gameOverLabel);
        table.row();
        table.add();
        table.row();
        table.add(retryLabel);
        stage.addActor(table);

    }
}


