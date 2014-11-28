package wzh.Http;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JScrollPane;

import org.json.JSONException;
import org.json.JSONObject;

public class MailReg extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField code;
	private JLabel imgLabel;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MailReg frame = new MailReg();
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
	public MailReg() {
		setResizable(false);
		setTitle("\u90AE\u7BB1\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 271);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u9A8C\u8BC1\u7801:");
		lblNewLabel.setBounds(28, 81, 54, 15);
		contentPane.add(lblNewLabel);
		
	    imgLabel = new JLabel("\u56FE\u7247\u533A\u57DF");
		imgLabel.setBounds(118, 55, 199, 41);
		contentPane.add(imgLabel);
		
		JButton btnNewButton = new JButton("\u5237\u65B0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CookieUtil.Reflesh();
				ImageIcon imageIcon=new ImageIcon("d:\\code.bmp");
				imageIcon.getImage().flush();
				imgLabel.setIcon(imageIcon);
			}
		});
		btnNewButton.setBounds(144, 10, 95, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("\u8F93\u5165:");
		lblNewLabel_2.setBounds(28, 132, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		code = new JTextField();
		code.setBounds(117, 129, 146, 21);
		contentPane.add(code);
		code.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("\u6CE8\u518C");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result=CookieUtil.Reg(code.getText().trim());
				try {
					JSONObject obj=new JSONObject(result);
					String status=obj.getString("result");
					if(!status.equals("0"))
					{
					textArea.append("×¢²áÊ§°Ü!"+"--"+obj.getString("error")+" \n");
					}else {
						textArea.append("User:\""+CookieUtil.getName()+"\" ×¢²á³É¹¦!\n");
						 MyFile.creatTxtFile("result");
			             MyFile.writeTxtFile(CookieUtil.getName()+"----"+"a123456");
					}
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_1.setBounds(144, 203, 95, 25);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(327, 10, 307, 226);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
}
