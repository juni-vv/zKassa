package com.juniper.kassa.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ModernScrollBarUI extends BasicScrollBarUI {
	
	@Override
    protected void configureScrollBarColors() {
        this.thumbColor = new Color(198, 129, 235); // Scroll thumb color
        this.trackColor = new Color(240, 240, 240); // Background track color
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton(); // Remove arrows
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton(); // Remove arrows
    }

    private JButton createZeroButton() {
        JButton btn = new JButton(5);
        btn.setPreferredSize(new Dimension(0, 0));
        btn.setVisible(false);
        return btn;
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
    	Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
    	
        g2.setColor(thumbColor);
        g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
    	Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
    	
        g2.setColor(trackColor);
        g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }
	
}
