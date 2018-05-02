package com.zhou.game;

public class HeroTank extends Tank {
	private HeroTank(int w, int h, int x, int y, int mx, int my) {
		super(w, h, x, y, mx, my);
		mDirection = Direction.u;// �ҵ�̹�� �ǳ��ϵ�
		autorun = false; // ���Զ�����
		type = TYPE_SELF;
	}

	/**
	 * @param w
	 *            Tank �����δ�С
	 * @param x
	 *            xλ��
	 * @param y
	 *            yλ��
	 * @param mx
	 *            ����λ��x
	 * @param my
	 *            ����λ��y <br>
	 *            ���ֱ��� �߶��ÿ�ȼ������
	 */
	public HeroTank(int w, int x, int y, int mx, int my) {
		this(getWW(w), getHH(w), x, y, mx, my);
	}
}
