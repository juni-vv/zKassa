package com.juniper.kassa.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

public class JScrollPane extends javax.swing.JPanel {
	
	private List<String> content;
	private int cornerRadius;
	
	public JScrollPane(List<String> content, int cornerRadius) {
		this.cornerRadius = cornerRadius;
		this.content = content;
		
		setLayout(null);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		RoundRectangle2D.Float roundedRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.setColor(getBackground());
        g2.fill(roundedRect);
	}
	
}