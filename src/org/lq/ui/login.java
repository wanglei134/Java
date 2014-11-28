package org.lq.ui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import org.lq.util.user;

import wzh.Http.CookieUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	private JTextField code;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setResizable(false);
		setTitle("\u767B\u5F55\u7A97\u53E3");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 390, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5E10\u53F7:");
		label.setBounds(70, 45, 54, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801:");
		label_1.setBounds(70, 89, 54, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u9A8C\u8BC1\u7801:");
		label_2.setBounds(70, 192, 54, 15);
		contentPane.add(label_2);
		
		username = new JTextField();
		username.setText("913000562131");
		username.setBounds(134, 42, 185, 21);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setText("472345");
		password.setBounds(134, 86, 185, 21);
		contentPane.add(password);
		
		final JLabel piclabel = new JLabel("\u70B9\u51FB\u53F3\u8FB9\u5237\u65B0");
		piclabel.setBounds(140, 139, 95, 34);
		contentPane.add(piclabel);
		
		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				user u=new user();
				u.setUsername(username.getText().trim());
				u.setPassword(password.getText().trim());
				u.setVirtifycode(code.getText().trim());
				//String resultString=CookieUtil.Login(u);				
				JOptionPane.showMessageDialog(null, "µÇÂ½³É¹¦!");
			}
		});
		btnNewButton.setBounds(178, 236, 95, 25);
		contentPane.add(btnNewButton);
		
		code = new JTextField();
		code.setBounds(134, 186, 185, 21);
		contentPane.add(code);
		code.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("\u5237\u65B0");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CookieUtil.RefreshCode();
				ImageIcon icon=new ImageIcon("d://data/code.jpg");
				icon.getImage().flush();
				piclabel.setIcon(icon);
				
			}
		});
		btnNewButton_1.setBounds(247, 144, 72, 25);
		contentPane.add(btnNewButton_1);
	}
}
