package com.zhou.game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * // 子弹类
 * 
 * @author han
 *
 */
public class Bullet extends IUnity {

	/**
	 * 
	 * @param w
	 *            子弹体积
	 * @param x
	 * @param y
	 * @param mx
	 * @param my
	 */
	public Bullet(int x, int y, int mx, int my) {
		this(2, 2, x, y, mx, my);
	}

	/**
	 * 
	 * @param w
	 *            子弹体积
	 * @param x
	 * @param y
	 * @param mx
	 * @param my
	 */
	public Bullet(int w, int x, int y, int mx, int my) {
		this(w, w, x, y, mx, my);
	}

	public Bullet(int w, int h, int x, int y, int mx, int my) {
		super(w, h, x, y, mx, my);
		speed = 6;// 默认快点
	}

	@Override
	public void onDraw(Graphics g) {
		if (autorun) {
			if (y > maxy || x > maxx || y < 0 || x < 0) {// 出边界了
				mDirection = Direction.t; // 标志位置
				return;
			}
			switch (mDirection) {
			case d:
				y += speed;
				break;
			case u:
				y -= speed;
				break;
			case l:
				x -= speed;
				break;
			case r:
				x += speed;
				break;
			default:
				break;
			}
		}
		int mx = x;
		int my = y;
		int mw = w;
		int mh = h;
		g.setColor(Color.WHITE);
		if (x == y) {
			g.fill3DRect(mx, my, mw, mh, false);
		} else {
			switch (mDirection) {
			case d:
			case u:
				g.fill3DRect(mx, my, mw, mh, false);
				break;
			case l:
			case r:
				g.fill3DRect(mx, my, mh, mw, false);
				break;
			default:
				break;
			}

		}

	}
}
