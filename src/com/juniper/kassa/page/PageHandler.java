package com.juniper.kassa.page;

import java.awt.Toolkit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class PageHandler {

	private static JFrame _jFrame;
	
	private static Page currentPage = null;

	public static void init() {
		if(_jFrame == null) {
			_jFrame = new JFrame();

			_jFrame.setDefaultCloseOperation(3);
			_jFrame.setTitle("zKassa");
			_jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			_jFrame.setUndecorated(true);
			_jFrame.setLocationRelativeTo(null);
		}
		
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		Runnable updateTimeTask = new Runnable() {
			private long nextExecutionTime = System.currentTimeMillis();
			
			@Override
			public void run() {
				if(currentPage != null)
					currentPage.update();
				
				nextExecutionTime += 1000;
				long delay = Math.max(0, nextExecutionTime - System.currentTimeMillis());
				scheduler.schedule(this, delay, TimeUnit.MILLISECONDS);
			}
		};
		
		scheduler.execute(updateTimeTask);
	}
	
	public static void openPage(Page page) {
		_jFrame.setContentPane(page.getPanel());
		_jFrame.setVisible(true);
		
		page.open();
		page.start();
		page.update();
		
		currentPage = page;
		
		_jFrame.revalidate();
		_jFrame.repaint();
	}
	
	public static void closePage(Page page) {
		page.close();
		currentPage = null;
	}

}
