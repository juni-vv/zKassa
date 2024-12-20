package com.juniper.kassa.swing.custom;

import com.juniper.kassa.swing.custom.Numpad.Key;

public class KeyboardEvent {
	
	private Numpad.Key _pressedKey;
	
	public KeyboardEvent(Numpad.Key pressedKey) {
		this._pressedKey = pressedKey;
	}
	
	public Numpad.Key getPressedKey() {
		return _pressedKey;
	}
	
	public static Numpad.Key getKey(String key) {
		return Enum.valueOf(Key.class, "KEY_" + key.toUpperCase());
	}
	
}
