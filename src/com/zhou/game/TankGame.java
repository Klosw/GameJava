package com.zhou.game;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.zhou.game.tankgame.TankPanel;

/*
 * ����:̹����Ϸ��1.0
 * 1.����̹��
 * */
public class TankGame extends JFrame {
	private static final long serialVersionUID = -5239748238921132750L;

	public static void main(String[] args) {
		TankGame.start();
	}

	public static void start() {
		new TankGame();
	}

	private TankPanel mp = null;
	private int width = 400, height = 300;

	public TankGame() {
		mp = new TankPanel(width, height);
		this.addKeyListener(mp);
		this.add(mp);
		this.setSize(width, height);
		this.setResizable(false);// ��С���ɸ���
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���õ��رմ���ʱ��֤JVMҲ�˳�
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();// ��Ļ����
		Dimension frameSize = this.getSize();// ��Ĵ�С
		this.setLocation((displaySize.width - frameSize.width) / 2, (displaySize.height - frameSize.height) / 2);
	}
}
