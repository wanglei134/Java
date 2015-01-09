package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import unity.ScheduleJob;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class CompareFrame extends JFrame {
	private JPanel contentPane;
	private static JComboBox Congig1 ;
	private static JComboBox Config2;
	private static JLabel count2;
	private static JTextArea result ;
	private static JLabel labelResult;
	private static String [] lookAndFeel=new String[]{
		"com.jtattoo.plaf.acryl.AcrylLookAndFeel",
		"com.jtattoo.plaf.aero.AeroLookAndFeel",
		"com.jtattoo.plaf.aluminium.AluminiumLookAndFeel",
		"com.jtattoo.plaf.bernstein.BernsteinLookAndFeel",
		"com.jtattoo.plaf.fast.FastLookAndFeel",
		"com.jtattoo.plaf.hifi.HiFiLookAndFeel",
		"com.jtattoo.plaf.luna.LunaLookAndFeel",
		"com.jtattoo.plaf.mcwin.McWinLookAndFeel",
		"com.jtattoo.plaf.mint.MintLookAndFeel",
		"com.jtattoo.plaf.smart.SmartLookAndFeel"};
	public static JLabel getLabelResult() {
		return labelResult;
	}

	public static JTextArea getResult() {
		return result;
	}

	public static JLabel getCount1() {
		return count1;
	}

	public static JLabel getCount2() {
		return count2;
	}

	private static JLabel count1;
    public static JComboBox getCongig1() {
		return Congig1;
	}

	public static JComboBox getConfig2() {
		return Config2;
	}

	public static JTextArea getSet1() {
		return Set1;
	}

	public static JTextArea getSet2() {
		return Set2;
	}
		
	private static JTextArea Set1;
    private static JTextArea Set2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String lookAndFeel1="com.jtattoo.plaf.acryl.AcrylLookAndFeel";
					UIManager.setLookAndFeel(lookAndFeel1);
					} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				try {
					CompareFrame frame = new CompareFrame();					
					new ScheduleJob().InitConfigName();
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
	public CompareFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 510);
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		setTitle("Configuration Compare Tool V1.2 Pom");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Config1");
		lblNewLabel.setBounds(25, 21, 48, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblConfig = new JLabel("Config2");
		lblConfig.setBounds(25, 46, 48, 14);
		getContentPane().add(lblConfig);
		
	    Congig1 = new JComboBox();
	    Congig1.setMaximumRowCount(100);
		Congig1.setBounds(76, 15, 615, 20);
		getContentPane().add(Congig1);
		
	    Config2 = new JComboBox();
	    Config2.setMaximumRowCount(100);
		Config2.setBounds(76, 43, 615, 20);
		getContentPane().add(Config2);
		
		JButton btnNewButton = new JButton("Compare");
		
		btnNewButton.setBounds(316, 247, 89, 54);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 98, 260, 232);
		getContentPane().add(scrollPane);
		
		Set1 = new JTextArea();
		Set1.setEditable(false);
		scrollPane.setViewportView(Set1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(426, 98, 265, 232);
		getContentPane().add(scrollPane_1);
		
	    Set2 = new JTextArea();
	    Set2.setEditable(false);
		scrollPane_1.setViewportView(Set2);
		
		JLabel lblConfigsets = new JLabel("Config1-Sets");
		lblConfigsets.setBounds(25, 73, 79, 14);
		contentPane.add(lblConfigsets);
		
		JLabel lblConfigsets_1 = new JLabel("Config2-Sets");
		lblConfigsets_1.setBounds(444, 74, 89, 14);
		contentPane.add(lblConfigsets_1);
		
		JButton btnGetsets = new JButton("GetSets");
		
		btnGetsets.setBounds(316, 136, 89, 54);
		contentPane.add(btnGetsets);
		
	    count1 = new JLabel("");
		count1.setBounds(118, 74, 129, 14);
		contentPane.add(count1);
		
		count2 = new JLabel("");
		count2.setBounds(580, 74, 111, 14);
		contentPane.add(count2);
		
		labelResult = new JLabel("Result:");
		labelResult.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelResult.setBounds(27, 362, 140, 69);
		contentPane.add(labelResult);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(211, 341, 480, 115);
		contentPane.add(scrollPane_2);
		
	    result = new JTextArea();
		scrollPane_2.setViewportView(result);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1)
				{
					final int index=comboBox.getSelectedIndex();
					EventQueue.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							try {
								UIManager.setLookAndFeel(lookAndFeel[index]);
								SwingUtilities.updateComponentTreeUI(getRootPane());
							} catch (Exception e2) {
								// TODO: handle exception
							}
						}
					});					
				}	
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"acryl", "aero", "aluminium", "bernstein", "fast", "hifi", "luna", "mcwin", "mint", "smart"}));
		comboBox.setBounds(25, 436, 142, 20);
		contentPane.add(comboBox);
		btnGetsets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Set1.setText("");
				Set2.setText("");				
				count1.setText("");
				count2.setText("");		
				labelResult.setText("Result:");
				result.setText("");
				new ScheduleJob().InitSets();
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ScheduleJob().CompareXmlData();
			}
		});
	}
}
