package com.arno.logiquefloue.problemes;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

public class MonPanel extends JPanel {

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.red);
		
		Polygon p=new Polygon();
		p.addPoint(1, 1);
		p.addPoint(100, 150);
		p.addPoint(200, 40);
		g.fillPolygon(p);
	}

}
