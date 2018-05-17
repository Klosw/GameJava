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
 * 画板 用来画画用的
 * 
 * @author han
 *
 */
public class TankPanel extends JPanel implements KeyListener {
	private static final long serialVersionUID = -5336208631429308273L;
	// 敌人子弹
	private final HashSet<Bullet> mFoeBullet = new HashSet<>();
	// 敌人
	private final HashSet<Tank> mFoeTank = new HashSet<>();
	// 键值存储
	private final LinkedHashSet<Integer> mKeytmp = new LinkedHashSet<>();

	// Tank 刷新时间
	private final int mTimeFoeTank = 5 * 1000;
	// 按键刷新时间
	private final int mTimekey = 32;
	// FPS刷新时间
	private int mTimefps = 16;
	// 定义一个我的坦克
	private HeroTank hero = null;
	private Thread mThread = null;

	private Random mRandom;
	// 当前屏幕显示多少个 敌机
	private final int mMaxFoe = 5;
	// 当前最多显示多少个
	private final int mSizeFoe = 20;

	// 只处理移动事件
	private void onKeyDown(int keycode) {
		switch (keycode) {
		// 向上
		case 38:
			hero.u();
			break;
		// 下
		case 40:
			hero.d();
			break;
		// 左
		case 37:
			hero.l();
			break;
		// 右
		case 39:
			hero.r();
			break;
		}
	}

	private void onKeyDownBullet(int keycode) {
		switch (keycode) {
		case 32:// 空格键发子弹
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

	// 构造函数
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
		// 停止当前运行线程
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

	// 刷新线程
	private Runnable mRunable = new Runnable() {
		@Override
		public void run() {
			try {
				int i = 0;
				for (;;) {// 死线程
					i++;
					if (i == Integer.MAX_VALUE)
						i = 0;
					paintKeyDown();// 按键
					if (mFoeTank.size() < mMaxFoe) {
						if (/* i * mTimefps > mTimeFoeTank && */ (i * mTimefps) % mTimeFoeTank == 0) {
							FoeTank foe = new FoeTank(24, Math.abs(mRandom.nextInt(width)), 0, width, height);
							foe.speed = 1;
							mFoeTank.add(foe);
						}
					}

					// 刷新率是 60 FPS
					TankPanel.this.repaint();
					Thread.sleep(mTimefps);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};

	/**
	 * 画最后 按键事件
	 */
	private void paintKeyDown() {
		if (mKeytmp.size() != 0) {// 按键
			// mKeytmp.
			int cc = 0;
			Iterator<Integer> m = mKeytmp.iterator();
			while (m.hasNext()) {// 按键处理这样似乎不好
				cc = m.next();
			}
			onKeyDown(cc);
		}
	}

	// 重写paint
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 400, 300);
		hero.onDraw(g);
		// 绘制 敌人坦克
		paintUnity(mFoeTank.iterator(), g);

	}

	// 绘制单位
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
	// 键被按下去了
	public void keyPressed(KeyEvent e) {
		if (getMoveKey(e.getKeyCode()))
			mKeytmp.add(e.getKeyCode());
		onKeyDownBullet(e.getKeyCode());
	}

	@Override
	// 键被释放了
	public void keyReleased(KeyEvent e) {
		if (getMoveKey(e.getKeyCode()))
			mKeytmp.remove(e.getKeyCode());
	}

	@Override
	// 键的一个值被输出
	public void keyTyped(KeyEvent e) {
	}
}
