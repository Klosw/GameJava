package com.zhou.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;

import javax.swing.JPanel;

import com.zhou.game.IUnity.Direction;

/**
 * ���� ���������õ�
 * 
 * @author han
 *
 */
public class TankPanel extends JPanel implements KeyListener {
	private static final long serialVersionUID = -5336208631429308273L;
	// �����ӵ�
	private final HashSet<Bullet> mFoeBullet = new HashSet<>();
	// ����
	private final HashSet<Tank> mFoeTank = new HashSet<>();
	// ��ֵ�洢
	private final LinkedHashSet<Integer> mKeytmp = new LinkedHashSet<>();
	// �ҵ�tank�������ӵ�����
	private final HashSet<Bullet> mHeroBullet = new HashSet<>();
	// Tank ˢ��ʱ��
	private final int mTimeFoeTank = 10 * 1000;
	// ����ˢ��ʱ��
	private final int mTimekey = 32;
	// FPSˢ��ʱ��
	private int mTimefps = 16;
	// ����һ���ҵ�̹��
	private HeroTank hero = null;
	private Thread mThread = null;

	private Random mRandom;
	// ��ǰ��Ļ��ʾ���ٸ� �л�
	private final int mMaxFoe = 5;
	// ��ǰ�����ʾ���ٸ�
	private final int mSizeFoe = 20;

	// ֻ�����ƶ��¼�
	private void onKeyDown(int keycode) {
		switch (keycode) {
		// ����
		case 38:
			hero.u();
			break;
		// ��
		case 40:
			hero.d();
			break;
		// ��
		case 37:
			hero.l();
			break;
		// ��
		case 39:
			hero.r();
			break;
		}
	}

	private void onKeyDownBullet(int keycode) {
		switch (keycode) {
		case 32:// �ո�����ӵ�
			int[] is = hero.getBulletStartCoordinate();
			Bullet bu = new Bullet(is[0], is[1], width, height);
			bu.mDirection = hero.mDirection;
			mHeroBullet.add(bu);
			break;
		}
	}

	private boolean getMoveKey(int keycode) {
		switch (keycode) {
		case 38:
		case 40:
		case 37:
		case 39:
			return true;
		}
		return false;
	}

	final int width, height;

	// ���캯��
	public TankPanel(int width, int height) {
		mRandom = new Random();
		this.setSize(width, height);
		this.width = width;
		this.height = height;
		hero = new HeroTank(24, 10, 10, width, height);
		startThread();
	}

	private void startThread() {
		// ֹͣ��ǰ�����߳�
		if (mThread != null) {
			try {
				if (mThread.isAlive())
					mThread.interrupt();
				mThread = null;
			} catch (Exception e) {
			}
		}
		mThread = new Thread(mRunable);
		mThread.start();
	}

	private void stopThread() {
		if (mThread != null) {
			try {
				if (mThread.isAlive())
					mThread.interrupt();
				mThread = null;
			} catch (Exception e) {
			}
		}
	}

	// ˢ���߳�
	private Runnable mRunable = new Runnable() {
		@Override
		public void run() {
			try {
				int i = 0;
				for (;;) {// ���߳�
					// ˢ������ 60 FPS
					TankPanel.this.repaint();
					Thread.sleep(mTimefps);
					i++;
					if (i == Integer.MAX_VALUE)
						i = 0;
					if (mKeytmp.size() != 0) {
						if ((i % ((int) (((mTimekey / mTimefps * 1.0) + 0.6)))) == 0) {
							// mKeytmp.
							int cc = 0;
							Iterator<Integer> m = mKeytmp.iterator();
							while (m.hasNext()) {// �������������ƺ�����
								cc = m.next();
							}
							onKeyDown(cc);
						}
					}
					if (mFoeTank.size() < mMaxFoe) {
						if (i * mTimefps > mTimeFoeTank && (i * mTimefps) % mTimeFoeTank == 0) {
							FoeTank foe = new FoeTank(24, Math.abs(mRandom.nextInt(width)), 0, width, height);
							mFoeTank.add(foe);
						}
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};

	// ��дpaint
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 400, 300);
		hero.onDraw(g);
		// �����ҵ��ӵ�
		Iterator<Bullet> m = mHeroBullet.iterator();
		while (m.hasNext()) {
			Bullet b = m.next();
			if (b.mDirection != Direction.t) {
				b.onDraw(g);
			} else {
				m.remove();
			}
		}
		// ���� ����̹��
		Iterator<Tank> m2 = mFoeTank.iterator();
		while (m2.hasNext()) {
			Tank b = m2.next();
			if (b.mDirection != Direction.t) {
				b.onDraw(g);
			} else {
				m.remove();
			}
		}
	}

	@Override
	// ��������ȥ��
	public void keyPressed(KeyEvent e) {
		if (getMoveKey(e.getKeyCode()))
			mKeytmp.add(e.getKeyCode());
		onKeyDownBullet(e.getKeyCode());
	}

	@Override
	// �����ͷ���
	public void keyReleased(KeyEvent e) {
		if (getMoveKey(e.getKeyCode()))
			mKeytmp.remove(e.getKeyCode());
	}

	@Override
	// ����һ��ֵ�����
	public void keyTyped(KeyEvent e) {
	}
}
