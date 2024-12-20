package com.juniper.kassa.swing.custom;

import java.awt.Color;

public class Gradient {

	private final int _startX;
	private final int _startY;
	private final int _endX;
	private final int _endY;
	
	private final Color _startColor;
	private final Color _endColor;
	
	public Gradient(int startX, int startY, int endX, int endY, Color startColor, Color endColor) {
		this._startX = startX;
		this._startY = startY;
		this._endX = endX;
		this._endY = endY;
		
		this._startColor = startColor;
		this._endColor = endColor;
	}
	
	public int getStartX() {
		return _startX;
	}
	
	public int getStartY() {
		return _startY;
	}
	
	public int getEndX() {
		return _endX;
	}
	
	public int getEndY() {
		return _endY;
	}
	
	public Color getStartColor() {
		return _startColor;
	}
	
	public Color getEndColor() {
		return _endColor;
	}
	
}
