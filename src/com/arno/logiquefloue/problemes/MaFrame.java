package com.arno.logiquefloue.problemes;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MaFrame extends JFrame {
	public MaFrame() {
		setSize(600, 400);
		setTitle("Zoom");
		MonPanel panel=new MonPanel();
		add(panel);
		DataPanel panel2=new DataPanel();
		add(panel2,BorderLayout.SOUTH);
	}
}
