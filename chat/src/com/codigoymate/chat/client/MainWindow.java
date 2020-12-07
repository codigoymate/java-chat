package com.codigoymate.chat.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.codigoymate.chat.StringMessage;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = -1738982773003242653L;

	private Chat chat;
	
	private JTextField tfInput = new JTextField();
	private JTextArea taOutput = new JTextArea();
	
	public MainWindow(Chat chat) {
		this.chat = chat;
		
		this.setTitle("Chat");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				chat.close();
			}
		});
		
		// Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Chat");
		JMenuItem connectItem = new JMenuItem("Conectar");
		menu.add(connectItem);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		connectItem.addActionListener(e -> ConnectDialog.show(chat, this));
		
		getContentPane().setLayout(new BorderLayout());
		
		// Salida
		taOutput.setEditable(false);
		taOutput.setFocusable(false);
		taOutput.setPreferredSize(new Dimension(1000, 600));
		getContentPane().add(taOutput, BorderLayout.CENTER);
		
		// Entrada
		tfInput.addActionListener((e) -> sendMessage());
		getContentPane().add(tfInput, BorderLayout.SOUTH);
		
		this.pack();
		
		this.setLocationRelativeTo(null);
	}
	
	private void sendMessage() {
		String msg = tfInput.getText().trim();
		tfInput.setText("");
		
		chat.getClient().sendMessage(new StringMessage(msg));
	}

	public JTextArea getOutput() {
		return taOutput;
	}
}
