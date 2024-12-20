package com.juniper.kassa.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

public class JPasswordField extends javax.swing.JPasswordField {

	private String _hintText      = "";
	private Color  _hintTextColor = Color.WHITE;

	private int     _cornerRadius  = 0;
	private boolean _borderVisible = false;
	private Color   _borderColor   = Color.WHITE;

	public JPasswordField() {
		setOpaque(false);
		
		addClearListener();
	}

	public JPasswordField(int columns) {
		super(null, null, columns);
		setOpaque(false);
		
		addClearListener();
	}

	public JPasswordField(String hint) {
		_hintText = hint;
	}
	
	private void addClearListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent keyEvent) {
				if(keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE && keyEvent.isControlDown())
					setText("");
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		g2d.setColor(new Color(237, 237, 237, 75));
		g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, _cornerRadius, _cornerRadius);

		if(getPassword().length == 0) {
			g2d.setColor(_hintTextColor);
			g2d.drawString(_hintText, getInsets().left, getHeight() / 2 + g.getFontMetrics().getAscent() / 2 - 2);
		}

		super.paintComponent(g);
	}

	@Override
	public void paintBorder(Graphics g) {
		if(_borderVisible) {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

			g2d.setColor(_borderColor);
			g2d.setStroke(new BasicStroke(2));

			g2d.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, _cornerRadius, _cornerRadius));
			g2d.dispose();
		}
	}
	
	public void setCornerRadius(int radius) {
		_cornerRadius = radius;
	}

	public int getCornerRadius() {
		return _cornerRadius;
	}

	public void setHintText(String hint) {
		_hintText = hint;
	}
	
	public String getHintText() {
		return _hintText;
	}

	public void setHintTextColor(Color color) {
		_hintTextColor = color;
	}
	
	public Color getHintTextColor() {
		return _hintTextColor;
	}

	public void setBorderVisible(boolean visible) {
		_borderVisible = visible;
	}

	public boolean isBorderVisible() {
		return _borderVisible;
	}

	public void setBorderColor(Color color) {
		_borderColor = color;
	}
	
	public Color getBorderColor() {
		return _borderColor;
	}

}
