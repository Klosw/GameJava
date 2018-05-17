package com.zhou.game.tankgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;

import javax.swing.JPanel;

import com.zhou.game.tankgame.IUnity.Direction;

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

	// Tank ˢ��ʱ��
	private final int mTimeFoeTank = 5 * 1000;
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
			hero.fire();
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
		hero.x = (hero.maxx - hero.w) / 2;
		hero.y = hero.maxy;
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
					i++;
					if (i == Integer.MAX_VALUE)
						i = 0;
					paintKeyDown();// ����
					if (mFoeTank.size() < mMaxFoe) {
						if (/* i * mTimefps > mTimeFoeTank && */ (i * mTimefps) % mTimeFoeTank == 0) {
							FoeTank foe = new FoeTank(24, Math.abs(mRandom.nextInt(width)), 0, width, height);
							foe.speed = 1;
							mFoeTank.add(foe);
						}
					}

					// ˢ������ 60 FPS
					TankPanel.this.repaint();
					Thread.sleep(mTimefps);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};

	/**
	 * ����� �����¼�
	 */
	private void paintKeyDown() {
		if (mKeytmp.size() != 0) {// ����
			// mKeytmp.
			int cc = 0;
			Iterator<Integer> m = mKeytmp.iterator();
			while (m.hasNext()) {// �������������ƺ�����
				cc = m.next();
			}
			onKeyDown(cc);
		}
	}

	// ��дpaint
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 400, 300);
		hero.onDraw(g);
		// ���� ����̹��
		paintUnity(mFoeTank.iterator(), g);

	}

	// ���Ƶ�λ
	private <T extends IUnity> void paintUnity(Iterator<T> t, Graphics g) {
		while (t.hasNext()) {
			IUnity mFoetank = t.next();
			if (mFoetank.mDirection != Direction.t) {
				mFoetank.onDraw(g);
			} else {
				t.remove();
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
