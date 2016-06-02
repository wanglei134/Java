/**
 * University Of Chongqing.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package cn.dsx.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cn.dsx.baidu.Baidu;

/**
 * 
 * @author wanglei
 * @version $Id: TieBaAutoFaTie.java, v 0.1 2016年5月22日 下午10:13:01 wanglei Exp $
 */
public class TieBaAutoFaTie extends JFrame {

    /**  */
    private static final long serialVersionUID = 6928148000517022701L;
    private JPanel contentPane;
    private JTextField userName;
    private JPasswordField passwordField;
    private JTextArea argsArea;
    private JLabel label_3;
    private JTextField time;
    private static JTextArea result;
    private static boolean flag=true;
    private static boolean loginResult=false;
    private static final Baidu baidu=new Baidu();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TieBaAutoFaTie frame = new TieBaAutoFaTie();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static void printResult(final String args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
               result.append(args);
               result.append("\n");               
            }
        });
    }

    /**
     * Create the frame.
     */
    public TieBaAutoFaTie() {
        setResizable(false);
        setTitle("TieBaAutoFaTie(\u81EA\u52A8\u9876\u5E16)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 559, 323);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel("\u8D26\u53F7:");
        label.setBounds(22, 10, 54, 15);
        contentPane.add(label);
        
        userName = new JTextField();
        userName.setBounds(61, 7, 225, 21);
        contentPane.add(userName);
        userName.setColumns(10);
        
        JLabel label_1 = new JLabel("\u5BC6\u7801:");
        label_1.setBounds(22, 38, 54, 15);
        contentPane.add(label_1);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(61, 35, 225, 21);
        contentPane.add(passwordField);
        
        final JButton button_1 = new JButton("\u6682\u505C");
        
        final JButton button = new JButton("\u5F00\u59CB");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {                                         
                final String content_="1";            
                String args=argsArea.getText().trim();
                if(args.trim().length()==0||userName.getText().length()==0||passwordField.getText().length()==0||time.getText().length()==0){
                    JOptionPane.showMessageDialog(null, "账号或密码或参数或间隔时间为空!");
                }
                final int timeToSleep=Integer.parseInt(time.getText().trim());  
                final String [] argList=args.split("\n");
                button.setEnabled(false);
                button_1.setEnabled(true);  
                if(loginResult==false){                    
                    String uName=userName.getText().trim();
                    String uPass=passwordField.getText().trim();
                    loginResult=baidu.login(uName, uPass);
                    printResult("登录成功状态:"+loginResult); 
                    if(loginResult==true){
                       
                        new Thread(
                        new Runnable() {                    
                            public void run() {
                                
                                //每次运行前判断是否有了一百行数据,有了就干掉
                                int len=result.getText().split("\n").length;
                                if(len==100) result.setText("");
                                
                                //判断是否暂停
                                while(true){
                                    if(flag==true){
                                        try {
                                            String res=null;
                                            for (String s : argList) {
                                                res=baidu.replyBuildingPost(s.split("\\|")[0], s.split("\\|")[1], content_, s.split("\\|")[2], s.split("\\|")[3], s.split("\\|")[4], s.split("\\|")[4]); 
                                                if(!res.contains("回帖成功"))
                                                printResult(res);
                                                Thread.sleep(5000);
                                            }                                        
                                            Thread.sleep((3+(new Random().nextInt(timeToSleep>0?timeToSleep:1)))*60000);
                                        } catch (Exception e) {
                                            printResult(e.getMessage());
                                        }
                                    }
                                }                           
                            }
                        }).start();
                    }
                }else{
                    //点击了开始且已经登录 
                    flag=true;
                   
                    new Thread(
                    new Runnable() {                    
                        public void run() {
                            
                            //每次运行前判断是否有了一百行数据,有了就干掉
                            int len=result.getText().split("\n").length;
                            if(len==100) result.setText("");
                            //判断是否暂停
                            while(true){
                                if(flag==true){
                                    try {
                                        String res=null;
                                        for (String s : argList) {
                                            res=baidu.replyBuildingPost(s.split("\\|")[0], s.split("\\|")[1], content_, s.split("\\|")[2], s.split("\\|")[3], s.split("\\|")[4], s.split("\\|")[4]); 
                                            if(!res.contains("回帖成功"))
                                            printResult(res);
                                            Thread.sleep(5000);
                                        }    
                                        Thread.sleep((3+(new Random().nextInt(timeToSleep>0?timeToSleep:1)))*60000);
                                    } catch (Exception e) {
                                        printResult(e.getMessage());
                                    }
                                }
                            }                           
                        }
                    } ).start();
                }                             
            }
        });
        button.setBounds(382, 133, 119, 47);
        contentPane.add(button);
        
       
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button_1.setEnabled(false);
                button.setEnabled(true);
                flag=false;
            }
        });
        button_1.setBounds(382, 229, 119, 53);
        contentPane.add(button_1);
        
        label_3 = new JLabel("\u65F6\u95F4:");
        label_3.setBounds(22, 267, 54, 15);
        contentPane.add(label_3);
        
        time = new JTextField();
        time.setColumns(10);
        time.setBounds(61, 264, 225, 21);
        contentPane.add(time);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(296, 10, 247, 98);
        contentPane.add(scrollPane);
        
        result = new JTextArea();
        scrollPane.setViewportView(result);
        
        JLabel lbltidfidpid = new JLabel("\u53C2\u6570\u683C\u5F0F\uFF1Atid|fid|\u8D34\u5427|\u697C\u5C42|pid");
        lbltidfidpid.setBounds(22, 68, 264, 15);
        contentPane.add(lbltidfidpid);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(22, 93, 264, 161);
        contentPane.add(scrollPane_1);
        
        argsArea = new JTextArea();
        scrollPane_1.setViewportView(argsArea);
    }
}
