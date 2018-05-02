package com.zhou.game;

import java.awt.Graphics;

/**
 * // 敌人的Tank
 * 
 * @author han
 *
 */
public class FoeTank extends Tank {
	private FoeTank(int w, int h, int x, int y, int mx, int my) {
		super(w, h, x, y, mx, my);
		mDirection = Direction.d;// 我的坦克 是朝上的
		autorun = true; // 自动运行
		type = TYPE_FOE;
	}

	public FoeTank(int w, int x, int y, int mx, int my) {
		this(getWW(w), getHH(w), x, y, mx, my);
	}

	@Override
	public void onDraw(Graphics g) {
		// 处理自动运行
		super.onDraw(g);
	}

}
