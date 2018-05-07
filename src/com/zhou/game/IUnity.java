package com.zhou.game;

import java.awt.Graphics;
import java.util.Iterator;

/**
 * 单位类
 * 
 * @author han
 *
 */
public abstract class IUnity {
	/**
	 * TanK 类型
	 */
	public int type = TYPE_OTHER;
	public static final int TYPE_OTHER = -1;
	/**
	 * 自己的Tank
	 */
	public static final int TYPE_SELF = 0;
	/**
	 * 敌人坦克
	 */
	public static final int TYPE_FOE = 1;
	/**
	 * 敌人子弹
	 */
	public static final int TYPE_BULLET_FOE = 2;
	/**
	 * 自己的子弹
	 */
	public static final int TYPE_BULLET_SELF = 3;

	// 是否自动运行
	protected boolean autorun = true;
	// 速度
	protected int speed = 2;

	// 表示坦克的宽度
	protected int w = 0;
	// 坦克的纵高度
	protected int h = 0;

	// 表示坦克的横坐标
	protected int x = 0;
	// 坦克的纵坐标
	protected int y = 0;
	// 最大位置
	protected int maxx = 0;
	// 最大位置
	protected int maxy = 0;
	// 方向
	protected Direction mDirection = Direction.o;

	// 方向
	static enum Direction {
		u(90), d(270), l(180), r(0),
		/** 还没有给定方向 */
		o(-1),
		/** 表示出边界了 或者被击中 */
		t(-2);
		int i;

		Direction(int i) {
			this.i = i;
		}
	}

	public IUnity(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @param x
	 *            当前tank位置
	 * @param y
	 *            当前tank位置
	 * @param mx
	 *            最大位置
	 * @param my
	 *            最大位置
	 */
	public IUnity(int x, int y, int mx, int my) {
		this.x = x;
		this.y = y;
		maxx = mx;
		maxy = my;
	}

	/**
	 * 
	 * @param x
	 *            当前tank位置
	 * @param y
	 *            当前tank位置
	 * @param mx
	 *            最大位置
	 * @param my
	 *            最大位置
	 */
	public IUnity(int w, int h, int x, int y, int mx, int my) {
		this.x = x;
		this.y = y;
		maxx = mx;
		maxy = my;
		this.w = w;
		this.h = h;
	}

	/**
	 * 画图
	 * 
	 * @param g
	 */
	public abstract void onDraw(Graphics g);

	// 上移动
	public final void u() {
		if (mDirection == Direction.u) {
			this.y -= speed;
			if (y < 0) {
				y = 0;
			}
		}
		this.mDirection = Direction.u;
	}

	// 下移动
	public final void d() {
		if (mDirection == Direction.d) {
			this.y += speed;
			if (maxy != 0) {
				if (y > maxy) {
					y = maxy;
				}
			}
		}
		this.mDirection = Direction.d;
	}

	// 左移动
	public final void l() {
		if (mDirection == Direction.l) {
			this.x -= speed;
			if (x < 0) {
				x = 0;
			}
		}
		this.mDirection = Direction.l;
	}

	// 右移动
	public final void r() {
		if (mDirection == Direction.r) {
			this.x += speed;
			if (maxx != 0) {
				if (x > maxx) {
					x = maxx;
				}
			}
		}
		this.mDirection = Direction.r;
	}

	/**
	 * 自动运行 不能运行出界
	 */
	public final void autoRunDraw() {
		switch (mDirection) {
		case u:
			u();
			break;
		case d:
			d();
			break;
		case l:
			l();
			break;
		case r:
			r();
			break;
		default:
			break;
		}
	}

	// 绘制单位
	public static <T extends IUnity> void paintUnity(Iterator<T> t, Graphics g) {
		while (t.hasNext()) {
			IUnity mFoetank = t.next();
			if (mFoetank.mDirection != Direction.t) {
				mFoetank.onDraw(g);
			} else {
				t.remove();
			}
		}
	}

}