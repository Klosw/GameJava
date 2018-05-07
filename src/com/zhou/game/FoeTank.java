package com.zhou.game;

import java.awt.Graphics;
import java.util.Random;

/**
 * // ���˵�Tank
 * 
 * @author han
 *
 */
public class FoeTank extends Tank {
	private int runcount = 0;
	private volatile int maxRunCount = 100;
	private final Random mRandom = new Random();

	private FoeTank(int w, int h, int x, int y, int mx, int my) {
		super(w, h, x, y, mx, my);
		mDirection = Direction.d;// �ҵ�̹�� �ǳ��ϵ�
		autorun = true; // �Զ�����
		type = TYPE_FOE;
		speed = 1;
	}

	public FoeTank(int w, int x, int y, int mx, int my) {
		this(getWW(w), getHH(w), x, y, mx, my);
	}

	@Override
	public void onDraw(Graphics g) {
		if (autorun) {
			runcount++;
			if (runcount % maxRunCount == 0) {
				mDirection = getRunDirection(mRandom);// ��÷���
				maxRunCount = 80 + Math.abs(mRandom.nextInt(130));// ������д���
			}
			if (runcount % (maxRunCount / 3) == 0) {
				fire();
			}
			if (runcount >= Integer.MAX_VALUE)
				runcount = 0;
			autoRunDraw();
			// �����Զ�����
		}
		super.onDraw(g);
	}

	// �õ��Զ����еķ���
	private Direction getRunDirection(Random mRandom) {
		int i = mRandom.nextInt(4);
		i = Math.abs(i);
		switch (i) {
		case 0:
			return Direction.d;
		case 1:
			return Direction.u;
		case 2:
			return Direction.l;
		case 3:
			return Direction.r;

		default:
			break;
		}
		return mDirection;
	}

}
