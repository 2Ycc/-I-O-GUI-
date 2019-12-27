package gui;

import bean.Student;
import dao.information;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class addStudent extends JDialog { //录入按钮点击后的学生录入窗口

	private static final long serialVersionUID = 1L;
	JButton btnAdd;  //确认增加按钮
    JButton btnReturn;  //返回主面板的按钮
    JLabel studentID; //学生id标签
    JLabel studentName; //学生名字标签
    JLabel studentMajor;  //学生专业标签
    JPanel jinput;  //输入信息面板
    JTextField idtext;  //输入id框
    JTextField nametext;  //输入名字框
    JTextField majortext; //输入专业框

    addStudent(JFrame f) {
        super(f);
        this.setSize(400, 300);
        this.setModal(true);
        this.setLayout(null);
        jinput = new JPanel();
        jinput.setSize(400, 300);
        jinput.setLayout(null);
        
        idtext = new JTextField();  //初始化学生id输入框
        nametext = new JTextField();  //初始化学生姓名输入框
        majortext = new JTextField();  //初始化学生专业输入框
        
        studentID = new JLabel("ID：");
        studentID.setBounds(50, 50, 50, 30);
        
        idtext.setBounds(100, 50, 180, 30);
        nametext.setBounds(100, 100, 180, 30);
        majortext.setBounds(100, 150, 180, 30);
        
        studentName = new JLabel("姓名：");
        studentName.setBounds(50, 100, 50, 30);
        
        studentMajor = new JLabel("专业：");
        studentMajor.setBounds(50, 150, 50, 30);
          
        btnAdd = new JButton("提交");
        btnAdd.setBounds(100, 200, 70, 30);
        
        btnReturn = new JButton("返回");
        btnReturn.setBounds(210, 200, 70, 30);
        
        jinput.add(studentName);
        jinput.add(studentID);
        jinput.add(studentMajor);
        jinput.add(idtext);
        jinput.add(nametext);
        jinput.add(majortext);
        jinput.add(btnAdd);
        jinput.add(btnReturn);
        
        this.add(jinput);
        this.setLocationRelativeTo(f);  //设置窗口相对于指定组件的位置。
        btnAdd.addActionListener(new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                if (idtext.getText().isEmpty() || nametext.getText().isEmpty() || majortext.getText().isEmpty()) {  //进行判空操作，为空不写入
                    JOptionPane.showMessageDialog(null, "录入信息不能为空！");
                } else {
                    Student student = new Student();
                    //将文本信息存入学生对象里面
                    student.setId(Integer.parseInt(idtext.getText()));
                    student.setName(nametext.getText());
                    student.setMajor(majortext.getText());
                    information schoolPersonnelDAO = new information();   //new一个文件操作对象
                    try {
                        schoolPersonnelDAO.addStudent(student); //写入信息
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "录入成功");
                    dispose();  //添加成功后关闭窗口
                    updatetable((MainJFrame) f); //调用表格更新方法
                }
            }
        });

        btnReturn.addActionListener(new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    //表格更新方法
    static void updatetable(MainJFrame f) {
    	information schoolPersonnelDAO = new information();
        try {
            f.tmStudent.setMessages(schoolPersonnelDAO.getStudentList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.tablestudent.setModel(f.tmStudent); //将更新后的表格模型存入
        f.tablestudent.updateUI();  //更新表格模型
    }
}
