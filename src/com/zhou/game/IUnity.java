package com.zhou.game;

import java.awt.Graphics;
import java.util.Iterator;

/**
 * ��λ��
 * 
 * @author han
 *
 */
public abstract class IUnity {
	/**
	 * TanK ����
	 */
	public int type = TYPE_OTHER;
	public static final int TYPE_OTHER = -1;
	/**
	 * �Լ���Tank
	 */
	public static final int TYPE_SELF = 0;
	/**
	 * ����̹��
	 */
	public static final int TYPE_FOE = 1;
	/**
	 * �����ӵ�
	 */
	public static final int TYPE_BULLET_FOE = 2;
	/**
	 * �Լ����ӵ�
	 */
	public static final int TYPE_BULLET_SELF = 3;

	// �Ƿ��Զ�����
	protected boolean autorun = true;
	// �ٶ�
	protected int speed = 2;

	// ��ʾ̹�˵Ŀ��
	protected int w = 0;
	// ̹�˵��ݸ߶�
	protected int h = 0;

	// ��ʾ̹�˵ĺ�����
	protected int x = 0;
	// ̹�˵�������
	protected int y = 0;
	// ���λ��
	protected int maxx = 0;
	// ���λ��
	protected int maxy = 0;
	// ����
	protected Direction mDirection = Direction.o;

	// ����
	static enum Direction {
		u(90), d(270), l(180), r(0),
		/** ��û�и������� */
		o(-1),
		/** ��ʾ���߽��� ���߱����� */
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
	 *            ��ǰtankλ��
	 * @param y
	 *            ��ǰtankλ��
	 * @param mx
	 *            ���λ��
	 * @param my
	 *            ���λ��
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
	 *            ��ǰtankλ��
	 * @param y
	 *            ��ǰtankλ��
	 * @param mx
	 *            ���λ��
	 * @param my
	 *            ���λ��
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
	 * ��ͼ
	 * 
	 * @param g
	 */
	public abstract void onDraw(Graphics g);

	// ���ƶ�
	public final void u() {
		if (mDirection == Direction.u) {
			this.y -= speed;
			if (y < 0) {
				y = 0;
			}
		}
		this.mDirection = Direction.u;
	}

	// ���ƶ�
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

	// ���ƶ�
	public final void l() {
		if (mDirection == Direction.l) {
			this.x -= speed;
			if (x < 0) {
				x = 0;
			}
		}
		this.mDirection = Direction.l;
	}

	// ���ƶ�
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
	 * �Զ����� �������г���
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

	// ���Ƶ�λ
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