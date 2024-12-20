package com.juniper.kassa.swing;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import com.juniper.kassa.swing.custom.Gradient;

public class JPanel extends javax.swing.JPanel {

	private Gradient _gradient;

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

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		if(_gradient != null) {
			Graphics2D    g             = (Graphics2D) graphics;
			GradientPaint gradientPaint = new GradientPaint(_gradient.getStartX(), _gradient.getStartY(), _gradient.getStartColor(), _gradient.getEndX(), _gradient.getEndY(), _gradient.getEndColor());

			g.setPaint(gradientPaint);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}

	public Gradient getGradient() {
		return _gradient;
	}

	public void setGradient(Gradient gradient) {
		this._gradient = gradient;
	}

}
