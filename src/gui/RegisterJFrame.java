package gui;

import bean.User;
import dao.logRegis;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class RegisterJFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField unameField;  //用户名输入文本框
    JPasswordField upwdField; //用户密码输入文本框
    JPasswordField urepwdField; //用户密码再次确认输入文本框
    JButton btnSave; //用于保存用户信息
    JButton btnReturn;  //用于返回登录界面
    JLabel unameLabel=new JLabel("用  户  名:");//用户名文本标签
    JLabel upwdLabel=new JLabel("密        码:");//密码文本标签
    JLabel urepwdLabel=new JLabel("确认密码:");//确认密码文本标签
//    public RegisterJFrame(){
//        super();
//        initialize();
//    }
    public RegisterJFrame(){
        this.setTitle("注册");
        this.setSize(520,310);
        this.setLocation(690, 320);
        this.setLayout(null);

        unameField=new JTextField("");
        unameField.setBounds(180,50,200,30);
        
        unameLabel.setBounds(120,50,60,30);
        
        upwdField=new JPasswordField("");
        upwdField.setBounds(180,100,200,30);
        
        upwdLabel.setBounds(120,100,60,30);
        
        urepwdField=new JPasswordField("");
        urepwdField.setBounds(180,150,200,30);
        
        urepwdLabel.setBounds(120,150,60,30);
        
        btnSave=new JButton("注册");
        btnSave.setBounds(180,210,70,30);
        
        btnReturn=new JButton("返回");
        btnReturn.setBounds(300,210,70,30);
        
        this.add(unameLabel);
        this.add(upwdLabel);
        this.add(urepwdLabel);
        this.add(unameField);
        this.add(upwdField);
        this.add(urepwdField);
        this.add(btnSave);
        this.add(btnReturn);
        btnSave.addActionListener(new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {  //将注册的信息的保存起来
                if(upwdField.getPassword().length<1||unameField.getText().isEmpty()||urepwdField.getPassword().length<1){
                    JOptionPane.showMessageDialog(null, "输入信息不能为空");
                }else{
                    if(new String(urepwdField.getPassword()).equals(new String(upwdField.getPassword()))){  //将获取的密码文本转换成字符串进行比较
                        User user=new User();
                        user.setUsername(unameField.getText());
                        user.setPassword(new String(upwdField.getPassword()));
                        try{
                            new logRegis().addUser(user);   //调用写好的方法将user对象写入txt
                        }catch (IOException e1){
                            e1.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null,"注册成功！");
                        dispose(); // 注册成功后关闭界面
                    }else{
                        JOptionPane.showMessageDialog(null,"密码不一致");
                    }
                }
            }
        });

        btnReturn.addActionListener(new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {  //点击将返回登录界面
                dispose();
            }
        });
    }
}
