package com.codigoymate.chat.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConnectDialog extends JDialog {
	private static final long serialVersionUID = -3092660330817609942L;

	private Chat chat;
	
	private JTextField tfAddress = new JTextField("localhost");
	private JTextField tfUserName = new JTextField();
	
	public static void show(Chat chat, Window parent) {
		ConnectDialog dialog = new ConnectDialog(chat, parent);
		dialog.setVisible(true);
	}
	
	private ConnectDialog(Chat chat, Window parent) {
		super(parent);
		this.chat = chat;
		
		this.setTitle("Conectar a ...");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setPreferredSize(new Dimension(300, 200));
		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
		listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// Coloqué los campos en el norte para que queden aplastados verticalmente.
		getContentPane().add(listPanel, BorderLayout.NORTH);
		
		// Direccion
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		JLabel lblAddress = new JLabel("Dirección: ");
		lblAddress.setPreferredSize(new Dimension(75, -1));
		panel.add(lblAddress);
		panel.add(tfAddress);
		
		listPanel.add(panel);
		
		// Agrega un espacio
		listPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		// Username
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		JLabel lblUserName = new JLabel("Usuario: ");
		lblUserName.setPreferredSize(new Dimension(75, -1));
		panel.add(lblUserName);
		panel.add(tfUserName);
		
		listPanel.add(panel);
		
		// Boton
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		panel.add(Box.createHorizontalGlue());
		
		JButton btnConnect = new JButton("Conectar");
		btnConnect.addActionListener(e -> connect());
		panel.add(btnConnect);
		
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		pack();
		
		setLocationRelativeTo(null);
	}
	
	private void connect() {
		chat.connect(tfAddress.getText().trim(), 2020, tfUserName.getText().trim());
		this.dispose();
	}
}
