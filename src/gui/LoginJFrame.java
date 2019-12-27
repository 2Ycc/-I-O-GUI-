package gui;

import bean.User;
import dao.logRegis;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoginJFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField unameField;  //用户名输入文本框
    JPasswordField upwdField; //用户密码输入文本框
    JButton btnLogin;  //登录按钮
    JButton btnRegister;  //进行注册
    JLabel title=new JLabel("师生信息管理系统");//用户名文本标签
    JLabel unameLabel = new JLabel("用户名:");//用户名文本标签
    JLabel upwdLabel = new JLabel("密    码:");//密码文本标签

    //在空构造器里面调用初始化方法
    public LoginJFrame() {
        super();
        initialize();
    }

    //初始化一个登录面板
    private void initialize() {
        this.setTitle("学生教师信息管理系统");
        this.setSize(560, 360);
        this.setLocation(670, 300);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  //点击关闭图标后将关闭
        
        title.setBounds(150,20,300,30);
        title.setFont(new Font("微软雅黑",Font.PLAIN,30));
        unameField = new JTextField("");
        unameField.setBounds(180, 110, 200, 30);
        unameLabel.setBounds(120, 110, 60, 30);
        upwdField = new JPasswordField("");
        upwdField.setBounds(180, 160, 200, 30);
        upwdLabel.setBounds(120, 160, 60, 30);
        btnLogin = new JButton("登录");
        btnLogin.setBounds(180, 220, 70, 30);
        btnRegister = new JButton("注册");
        btnRegister.setBounds(300, 220, 70, 30);
        this.add(unameLabel);
        this.add(upwdLabel);
        this.add(unameField);
        this.add(upwdField);
        this.add(btnLogin);
        this.add(btnRegister);
        this.add(title);

        btnLogin.addActionListener(new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                logRegis userDAO = new logRegis();
                user.setUsername(unameField.getText());
                user.setPassword(new String(upwdField.getPassword()));
                try {
                    if (unameField.getText().isEmpty() || upwdField.getPassword().length < 1) {
                        JOptionPane.showMessageDialog(null, "用户名或密码不能为空！");
                    } else {
                        if (userDAO.verificationUser(user)) {
                            MainJFrame mainJFrame = new MainJFrame();
                            mainJFrame.setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "密码错误");
                        }
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnRegister.addActionListener(new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {  //创建一个新的线程用来显示新的注册界面窗口
                    @Override
                    public void run() {
                        RegisterJFrame registerJFrame = new RegisterJFrame();  //创建注册窗口实例
                        registerJFrame.setVisible(true);
                    }
                }).start();
            }
        });

    }
}
