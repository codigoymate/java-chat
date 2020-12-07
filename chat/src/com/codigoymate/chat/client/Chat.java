package com.codigoymate.chat.client;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Chat {
	private MainWindow mainWindow;
	
	private ChatClient client = null;
	
	public Chat() {
		mainWindow = new MainWindow(this);
		
		SwingUtilities.invokeLater(() -> mainWindow.setVisible(true));
	}
	
	public MainWindow getMainWindow() {
		return mainWindow;
	}
	
	public ChatClient getClient() {
		return client;
	}
	
	public void connect(String address, int port, String userName) {
		client = new ChatClient(this, address, port, userName);
		Thread thread = new Thread(client);
		thread.start();
	}
	
	public void close() {
		
		if (client != null) {
			if (client.isRunning()) {
				client.stop();
			}
		}
		
		mainWindow.dispose();
	}
	
	public static void main(String[] args) {
		
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				try { UIManager.setLookAndFeel(info.getClassName());
				} catch (Exception e) {}
			}
		}
		
		new Chat();
	}
}
