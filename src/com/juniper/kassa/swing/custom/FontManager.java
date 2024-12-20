package com.juniper.kassa.swing.custom;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class FontManager {
	
	public static Font createFont(String path, float size) {
		try {
		    Font font = Font.createFont(Font.TRUETYPE_FONT, FontManager.class.getResourceAsStream(path)).deriveFont(size);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(font);
		    
		    return font;
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		return new Font("arial", 0, (int) size);
	}

}
