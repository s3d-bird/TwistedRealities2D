package com.arsecx.twistedrealities2d;

import com.arsecx.twistedrealities2d.Levels.Level_1;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class LevelController extends Game {
	public SpriteBatch batch;

//	public static enum BITS {DEFAULT, AREK, SPIKE, WEAK_PLATF, DESTROYED_PLATF};
	public static final short DEFAULT = 1;
	public static final short AREK = 2;
	public static final short SPIKE = 4;
	public static final short WEAK_PLATF = 8;
	public static final short BASE_PLATF = 16;
	public static final short DESTROYED_PLATF = 32;

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
