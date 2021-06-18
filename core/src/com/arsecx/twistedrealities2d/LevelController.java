package com.arsecx.twistedrealities2d;

import com.arsecx.twistedrealities2d.Levels.Level_1;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class LevelController extends Game {
	public SpriteBatch batch;
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new Level_1(this)); // for now level 1 is called this way
	}

	@Override
	public void render () {
		super.render();
	}

}
