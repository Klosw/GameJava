package com.zhou.game;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.zhou.game.tankgame.TankPanel;

/*
 * 功能:坦克游戏的1.0
 * 1.画出坦克
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
		this.setResizable(false);// 大小不可更改
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置当关闭窗口时保证JVM也退出
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();// 屏幕属性
		Dimension frameSize = this.getSize();// 框的大小
		this.setLocation((displaySize.width - frameSize.width) / 2, (displaySize.height - frameSize.height) / 2);
	}
}
