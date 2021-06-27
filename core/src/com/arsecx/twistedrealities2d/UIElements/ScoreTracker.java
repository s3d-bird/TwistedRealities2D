package com.arsecx.twistedrealities2d.UIElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Timer;
import java.util.TimerTask;

public class ScoreTracker implements Disposable {

    public Stage stage;
    Viewport viewport;
    Label timeTakenLabel;
    float timeTaken;
    int minCount;

    public void setTimeTaken() {
        timeTaken += Gdx.graphics.getDeltaTime();
        if(timeTaken > 1) {
            if((int) timeTaken % 60 == 0) {
                minCount++;
                timeTaken = 0;
            }
            timeTakenLabel.setText("Time Taken: " + minCount + ":" +(int) timeTaken);
        }
    }

    public ScoreTracker(SpriteBatch batch) {
        viewport = new FitViewport(312, 196, new OrthographicCamera());
        stage = new Stage(viewport, batch);
        timeTaken = 0;
        minCount = 0;
        timeTakenLabel = new Label("Time Taken: " + timeTaken, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Table table = new Table();

        table.top();
        table.align(Align.topRight);
        table.setFillParent(true);
        table.add(timeTakenLabel);
        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}