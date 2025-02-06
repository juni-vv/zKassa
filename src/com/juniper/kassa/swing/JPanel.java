package com.juniper.kassa.swing;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import com.juniper.kassa.swing.custom.Gradient;

public class JPanel extends javax.swing.JPanel {

	private Gradient _gradient;
	
	private int cornerRadius = 0;

	public JPanel(LayoutManager layoutManager, boolean isDoubleBuffered) {
		super(layoutManager, isDoubleBuffered);
		setOpaque(false);
	}

	public JPanel(LayoutManager layoutManager, Gradient gradient) {
		this._gradient = gradient;
		setOpaque(false);
	}

	public JPanel(LayoutManager layoutManager) {
		this(layoutManager, true);
		setOpaque(false);
	}
	
	public JPanel() {
		super();
		setOpaque(false);
	}
	
	public JPanel(int cornerRadius) {
		this.cornerRadius = cornerRadius;
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		if(_gradient != null) {
			super.paintComponent(graphics);
			
			Graphics2D    g             = (Graphics2D) graphics;
			GradientPaint gradientPaint = new GradientPaint(_gradient.getStartX(), _gradient.getStartY(), _gradient.getStartColor(), _gradient.getEndX(), _gradient.getEndY(), _gradient.getEndColor());

			g.setPaint(gradientPaint);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		
		if(cornerRadius > 0) {
			Graphics2D g2 = (Graphics2D) graphics;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			RoundRectangle2D.Float roundedRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
	        g2.setColor(getBackground());
	        g2.fill(roundedRect);
		}
	}

	public Gradient getGradient() {
		return _gradient;
	}

	public void setGradient(Gradient gradient) {
		this._gradient = gradient;
	}

}
