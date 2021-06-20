package com.arsecx.twistedrealities2d.UIElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MovementButtons {
    public Stage stage;
    private Table table;

    public ImageButton btn_LeftMove;
    public ImageButton btn_RightMove;
    private ImageButton btn_Jump;

    private Viewport viewport;

    private boolean leftPressed, rightPressed, jumpPressed;

    public MovementButtons(SpriteBatch batch) {
        viewport = new FitViewport(256, 144, new OrthographicCamera());
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.bottom();

        Texture textureBtnRight = new Texture("UIElements/ArrowRight.png");
        TextureRegion textureRegion_BtnRt = new TextureRegion(textureBtnRight);
        TextureRegionDrawable txtRgDr_BtnRt = new TextureRegionDrawable(textureRegion_BtnRt);

        Texture textureBtnLeft = new Texture("UIElements/ArrowLeft.png");
        TextureRegion textureRegion_BtnLft = new TextureRegion(textureBtnLeft);
        TextureRegionDrawable txtRgDr_BtnLft = new TextureRegionDrawable(textureRegion_BtnLft);

        Texture textureBtnJump = new Texture("UIElements/jump.png");
        TextureRegion textureRegion_BtnJmp = new TextureRegion(textureBtnJump);
        TextureRegionDrawable txtRgDr_BtnJmp = new TextureRegionDrawable(textureRegion_BtnJmp);

        btn_RightMove = new ImageButton(txtRgDr_BtnRt);
        btn_LeftMove = new ImageButton(txtRgDr_BtnLft);
        btn_Jump = new ImageButton(txtRgDr_BtnJmp);
        btn_RightMove.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = false;
            }
        });

        btn_LeftMove.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = false;
            }
        });

        btn_Jump.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                jumpPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                jumpPressed = false;
            }
        });

        table.row();
        table.row();
        table.add(btn_Jump).expandX().pad(5);
        table.add(btn_LeftMove).align(-10).expandX().pad(5);
        table.add(btn_RightMove).align(-10).expandX().pad(5);
        table.setFillParent(true);

        stage.addActor(table);
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isJumpPressed() {
        return jumpPressed;
    }
}
