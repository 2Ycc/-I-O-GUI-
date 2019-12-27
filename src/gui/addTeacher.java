package gui;

import bean.Teacher;
import dao.information;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class addTeacher extends JDialog { //录入按钮点击后的老师录入窗口

	private static final long serialVersionUID = 1L;
	JButton btnAdd;  //确认增加按钮
    JButton btnReturn;  //返回主面板的按钮
    JLabel teacherID; //老师id标签
    JLabel teacherName; //老师名字标签
    JLabel teacherMajor;  //老师专业标签
    JPanel jinput;  //输入信息面板
    JTextField idtext;  //输入id框
    JTextField nametext;  //输入名字框
    JTextField collegetext; //输入专业框

    addTeacher(JFrame f) {
        super(f);
        this.setSize(400, 300);
        this.setModal(true);
        this.setLayout(null);
        
        jinput = new JPanel();
        jinput.setSize(400, 300);
        jinput.setLayout(null);
        
        idtext = new JTextField();  //初始化老师id输入框
        nametext = new JTextField();  //初始化老师姓名输入框
        collegetext = new JTextField();  //初始化老师学院输入框
        
        idtext.setBounds(100, 50, 180, 30);
        nametext.setBounds(100, 100, 180, 30);     
        
        teacherID = new JLabel("ID：");
        teacherID.setBounds(50, 50, 50, 30);
        collegetext.setBounds(100, 150, 180, 30);
        
        
        teacherName = new JLabel("姓名：");
        teacherName.setBounds(50, 100, 50, 30);
        
        teacherMajor = new JLabel("学院：");
        teacherMajor.setBounds(50, 150, 50, 30);
          
        btnAdd = new JButton("提交");
        btnAdd.setBounds(100, 200, 70, 30);
        
        btnReturn = new JButton("返回");
        btnReturn.setBounds(210, 200, 70, 30);
        
        jinput.add(teacherName);
        jinput.add(teacherID);
        jinput.add(teacherMajor);
        jinput.add(idtext);
        jinput.add(nametext);
        jinput.add(collegetext);
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
                if (idtext.getText().isEmpty() || nametext.getText().isEmpty() || collegetext.getText().isEmpty()) {  //进行判空操作，为空不写入
                    JOptionPane.showMessageDialog(null, "录入信息不能为空！");
                } else {
                    Teacher teacher = new Teacher();
                    //将文本信息存入老师对象里面
                    teacher.setId(Integer.parseInt(idtext.getText()));
                    teacher.setName(nametext.getText());
                    teacher.setCollege(collegetext.getText());
                    information schoolPersonnelDAO = new information();   //new一个文件操作对象
                    try {
                        schoolPersonnelDAO.addTeacher(teacher); //写入信息
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "录入成功");
                    dispose();  //添加成功后将添加框隐藏
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
            f.tmTeacher.setMessages(schoolPersonnelDAO.getTeacherList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.tableteacher.setModel(f.tmTeacher);   //将更新后的表格模型存入
        f.tableteacher.updateUI();  //更新表格模型
    }
}
