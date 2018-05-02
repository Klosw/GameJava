package com.zhou.game;

public class HeroTank extends Tank {
	private HeroTank(int w, int h, int x, int y, int mx, int my) {
		super(w, h, x, y, mx, my);
		mDirection = Direction.u;// 我的坦克 是朝上的
		autorun = false; // 不自动运行
		type = TYPE_SELF;
	}

	/**
	 * @param w
	 *            Tank 的体形大小
	 * @param x
	 *            x位置
	 * @param y
	 *            y位置
	 * @param mx
	 *            限制位置x
	 * @param my
	 *            限制位置y <br>
	 *            保持比例 高度用宽度计算出来
	 */
	public HeroTank(int w, int x, int y, int mx, int my) {
		this(getWW(w), getHH(w), x, y, mx, my);
	}
}
