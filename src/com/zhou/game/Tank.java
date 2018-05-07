package com.zhou.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;

/**
 * // ̹����
 * 
 * @author han
 *
 */
public class Tank extends IUnity {
	// �ҵ�tank�������ӵ�����
	private final HashSet<Bullet> mBullet = new HashSet<>();

	public Tank(int w, int h, int x, int y, int mx, int my) {
		super(getWW(w), h, x, y, mx - h, my - h * 2 + getWW(w) / 4 + 1);// ����tankʵ��λ��
	}

	// Tank �Ŀ��Ҫ�� 4�ı���������������
	protected static int getWW(int w) {
		w = ((int) (w / 4f + 0.49f)) * 4;// 4��5��fa
		return w;
	}

	// Tank �Ŀ�� ����߶�
	protected static int getHH(int w) {
		return getWW(w) * 25 / 20;
	}

	@Override
	public void onDraw(Graphics g) {
		this.DrawTank(g);
		paintUnity(mBullet.iterator(), g);
	}

	// �ӵ���ʼλ��
	public int[] getBulletStartCoordinate() {
		int[] is = new int[2];
		int wx = 0;
		int hy = 0;
		switch (mDirection) {
		case u:
			wx = x + w / 2 - 1;
			hy = y - h / 10;
			break;
		case d:
			wx = x + w / 2 - 1;
			hy = y + h + h / 10;
			break;
		case l:
			hy = y + w / 2 - 1;
			wx = x - h / 10;
			break;
		case r:
			hy = y + w / 2 - 1;
			wx = x + h + h / 10;
			break;
		default:
			break;
		}
		is[0] = wx;
		is[1] = hy;
		return is;
	}

	// ����̹�˵ĺ���
	// direct����Ϊ��������--0123
	protected final void DrawTank(Graphics g) {
		// �ж���ʲô���͵�̹��
		switch (type) {
		case TYPE_SELF:
			g.setColor(Color.cyan);
			break;
		case TYPE_FOE:
			g.setColor(Color.yellow);
			break;
		}
		// g.setColor(Color.yellow);
		// g.fill3DRect(x, y, (mDirection == Direction.r || mDirection ==
		// Direction.l) ? h : w,
		// (mDirection == Direction.r || mDirection == Direction.l) ? w : h,
		// false);
		// g.setColor(Color.cyan);
		switch (mDirection) {
		// ����
		case u:
			int mx = x;
			int my = y;
			int mw = w / 4;
			int mh = h;
			// �����ҵ�̹��(��ʱ�ڷ�װ��һ������)
			// ��������ľ���
			g.fill3DRect(mx, my, mw, mh, false);
			mx = x + w * 3 / 4;
			my = y;
			mw = w / 4;
			mh = h;
			// �����ұߵľ���
			g.fill3DRect(mx, my, mw, mh, false);
			mx = x + w / 4;
			my = y + h / 6;
			mw = w / 2;
			mh = h * 2 / 3;
			// �����м����
			g.fill3DRect(mx, my, mw, mh, false);
			mx = x + w / 4 - 1;
			my = (y + h / 6) + ((h * 2 / 3) - w / 2) / 2;
			mh = w / 2;
			mw = w / 2;
			// ����Բ��
			g.drawOval(mx, my, mw, mh);
			mx = x + w / 2 - 1;
			my = y + h / 2;
			mh = y - h / 10;
			// ������
			g.drawLine(mx, my, mx, mh);
			break;
		// ����
		case d:
			mx = x;
			my = y;
			mw = w / 4;
			mh = h;
			// ��������ľ���
			g.fill3DRect(mx, my, mw, mh, false);
			mx = x + w * 3 / 4;
			my = y;
			mw = w / 4;
			mh = h;
			// �����ұߵľ���
			g.fill3DRect(mx, my, mw, mh, false);
			mx = x + w / 4;
			my = y + h / 6;
			mw = w / 2;
			mh = h * 2 / 3;
			// �����м����
			g.fill3DRect(mx, my, mw, mh, false);
			mx = x + w / 4 - 1;
			my = (y + h / 6) + ((h * 2 / 3) - w / 2) / 2;
			mh = w / 2;
			mw = w / 2;
			// ����Բ��
			g.drawOval(mx, my, mw, mh);

			mx = x + w / 2 - 1;
			my = y + h / 2;
			mh = y + h + h / 10;
			// ������
			g.drawLine(mx, my, mx, mh);
			break;
		// ����
		case r:// ��Ϊ���ҵ�ʱ�� ��� ���Ǹ߶�
			mx = x;
			my = y;
			mw = h;
			mh = w / 4;
			g.fill3DRect(mx, my, mw, mh, false);
			mx = x;
			my = y + w * 3 / 4;
			mw = h;
			mh = w / 4;
			g.fill3DRect(mx, my, mw, mh, false);
			mx = x + h / 6;
			my = y + w / 4;
			mw = h * 2 / 3;
			mh = w / 2;
			g.fill3DRect(mx, my, mw, mh, false);
			mx = x + h / 6 + (h * 2 / 3 - w / 2) / 2;
			my = y + (w / 4) - 1;
			mh = w / 2;
			mw = w / 2;
			g.drawOval(mx, my, mw, mh);
			mx = x + h / 2;
			my = y + w / 2 - 1;
			mw = x + h + h / 10;
			// ������
			g.drawLine(mx, my, mw, my);
			break;
		// ����
		case l:
			mx = x;
			my = y;
			mw = h;
			mh = w / 4;
			g.fill3DRect(mx, my, mw, mh, false);
			mx = x;
			my = y + w * 3 / 4;
			mw = h;
			mh = w / 4;
			g.fill3DRect(mx, my, mw, mh, false);
			mx = x + h / 6;
			my = y + w / 4;
			mw = h * 2 / 3;
			mh = w / 2;
			g.fill3DRect(mx, my, mw, mh, false);
			mx = x + h / 6 + (h * 2 / 3 - w / 2) / 2;
			my = y + (w / 4) - 1;
			mh = w / 2;
			mw = w / 2;
			g.drawOval(mx, my, mw, mh);
			mx = x + h / 2;
			my = y + w / 2 - 1;
			mw = x - h / 10;
			// ������
			g.drawLine(mx, my, mw, my);
			break;

		default:
			break;
		}
	}

	/**
	 * ���𷽷�
	 */
	public void fire() {
		int[] is = this.getBulletStartCoordinate();
		Bullet bu = new Bullet(is[0], is[1], maxx + w, maxy + y);
		bu.mDirection = this.mDirection;
		mBullet.add(bu);
	}

}
