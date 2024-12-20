package com.juniper.kassa.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class JButton extends javax.swing.JButton {

	private int   _cornerRadius;
	private Shape _shape;

	private Color _buttonColor;
	private Color _armedColor;

	public JButton(int cornerRadius) {
		
	}
	
	public JButton(String text, int cornerRadius) {
		super(text);

		this._cornerRadius = cornerRadius;

		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);

		setContentAreaFilled(false);
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		g2d.setColor(getModel().isArmed() ? _armedColor : _buttonColor);

		RoundRectangle2D rect = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, _cornerRadius, _cornerRadius);
		g2d.fill(rect);

		super.paintComponent(g2d);
		g2d.dispose();
	}

	@Override
	protected void paintBorder(Graphics g) {

	}

	@Override
	public boolean contains(int x, int y) {
		if(_shape == null || !_shape.getBounds().equals(getBounds()))
			_shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), _cornerRadius, _cornerRadius);

		return _shape.contains(x, y);
	}

	public void setColor(Color color) {
		_buttonColor = color;
	}

	public void setArmedColor(Color color) {
		_armedColor = color;
	}
}