package com.zhou.game;

import java.awt.Graphics;

/**
 * // ���˵�Tank
 * 
 * @author han
 *
 */
public class FoeTank extends Tank {
	private FoeTank(int w, int h, int x, int y, int mx, int my) {
		super(w, h, x, y, mx, my);
		mDirection = Direction.d;// �ҵ�̹�� �ǳ��ϵ�
		autorun = true; // �Զ�����
		type = TYPE_FOE;
	}

	public FoeTank(int w, int x, int y, int mx, int my) {
		this(getWW(w), getHH(w), x, y, mx, my);
	}

	@Override
	public void onDraw(Graphics g) {
		// �����Զ�����
		super.onDraw(g);
	}

}
