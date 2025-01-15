package com.juniper.kassa.swing.custom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import com.juniper.kassa.swing.JButton;
import com.juniper.kassa.swing.JPanel;

public class Numpad {

	private final JButton jb_00, jb_0, jb_1, jb_2, jb_3, jb_4, jb_5, jb_6, jb_7, jb_8, jb_9, jb_backspace, jb_enter;

	private JPanel                 keyboardPanel      = new JPanel(new GridBagLayout());
	private List<KeyboardListener> keyboardListeners = new ArrayList<KeyboardListener>();

	public Numpad(int cornerRadius) {
		keyboardPanel.setOpaque(false);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);

		jb_00 = createButton(cornerRadius, "00");
		jb_0 = createButton(cornerRadius, "0");
		jb_1 = createButton(cornerRadius, "1");
		jb_2 = createButton(cornerRadius, "2");
		jb_3 = createButton(cornerRadius, "3");
		jb_4 = createButton(cornerRadius, "4");
		jb_5 = createButton(cornerRadius, "5");
		jb_6 = createButton(cornerRadius, "6");
		jb_7 = createButton(cornerRadius, "7");
		jb_8 = createButton(cornerRadius, "8");
		jb_9 = createButton(cornerRadius, "9");
		jb_backspace = createButton(cornerRadius, "Backspace");
		jb_enter = createButton(cornerRadius, "Enter");

		constraints.gridy = 0;

		constraints.gridx = 0;
		keyboardPanel.add(jb_7, constraints);
		constraints.gridx = 1;
		keyboardPanel.add(jb_8, constraints);
		constraints.gridx = 2;
		keyboardPanel.add(jb_9, constraints);

		constraints.gridy = 1;

		constraints.gridx = 0;
		keyboardPanel.add(jb_4, constraints);
		constraints.gridx = 1;
		keyboardPanel.add(jb_5, constraints);
		constraints.gridx = 2;
		keyboardPanel.add(jb_6, constraints);
		
		constraints.gridy = 2;

		constraints.gridx = 0;
		keyboardPanel.add(jb_1, constraints);
		constraints.gridx = 1;
		keyboardPanel.add(jb_2, constraints);
		constraints.gridx = 2;
		keyboardPanel.add(jb_3, constraints);
		
		constraints.gridy = 3;

		constraints.gridx = 0;
		keyboardPanel.add(jb_backspace, constraints);
		constraints.gridx = 1;
		keyboardPanel.add(jb_0, constraints);
		constraints.gridx = 2;
		keyboardPanel.add(jb_00, constraints);
		
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.gridheight = 4;
		jb_enter.setPreferredSize(new Dimension(125, 85 * 4 + 10 * 3));
		keyboardPanel.add(jb_enter, constraints);
	}
	
	public JPanel getJPanel() {
		return keyboardPanel;
	}
	
	public int getWidth() {
		return 125 * 4 + 10 * 5;
	}
	
	public int getHeight() {
		return 85 * 4 + 10 * 5;
	}

	public void addKeyboardListener(KeyboardListener keyboardListener) {
		keyboardListeners.add(keyboardListener);
	}

	public void addTo(JComponent jComponent, GridBagConstraints constraints) {
		jComponent.add(keyboardPanel, constraints);
	}

	public void addTo(JComponent jComponent) {
		jComponent.add(keyboardPanel);
	}
	
	private JButton createButton(int cornerRadius, String text) {
		JButton button = new JButton(text, cornerRadius);
		button.addActionListener(actionEvent -> {
			for(KeyboardListener e : keyboardListeners) {
				e.keyboardEvent(new KeyboardEvent(KeyboardEvent.getKey(text)));
			}
		});

		button.setPreferredSize(new Dimension(125, 85));
		button.setFont(FontManager.createFont("/Raleway-SemiBold.ttf", 16));
		button.setFocusPainted(false);
		button.setColor(new Color(237, 237, 237, 150));
		button.setArmedColor(new Color(237, 237, 237, 200));
		button.setForeground(Color.white);

		return button;
	}

	public enum Key {
		KEY_00, KEY_0, KEY_1, KEY_2, KEY_3, KEY_4, KEY_5, KEY_6, KEY_7, KEY_8, KEY_9, KEY_BACKSPACE, KEY_ENTER;
	}

}
