package gui;

import javax.swing.*;
import dao.information;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel studentjpanel;  //用来显示学生的面板
    JPanel teacherjpanel;  //用来显示老师的面板
    JTabbedPane schoolperdonneljpanel;  //用来容纳学生与老师面板的面板容器
    JScrollPane stuentmessage; //用来显示学生信息的面板
    JPanel studentoperation;  //下方用来显示学生面板按钮的面板
    JButton btnStuAdd; //用来实现录入学生信息的按钮
    JTable tablestudent; //学生信息表格
    JTable tableteacher; //老师信息表格
    TableModel tmStudent; //学生表格模型
    TableModel tmTeacher; //老师表格模型
    JScrollPane teachermessage; //用来显示老师信息的面板
    JPanel teacheroperation;  //下方用来显示老师面板按钮的面板
    JButton btnTeaAdd; //用来实现录入老师信息的按钮
    String[] strstudent = {"ID", "姓名", "专业"};  //学生信息的名称
    String[] strteacher = {"ID", "姓名", "学院"};   //老师信息的名称
    information schoolPersonnelDAO; //学生老师信息录入读出

    //初始化一个主页面面板
     public MainJFrame() {
        this.setTitle("教师、学生信息显示");
        this.setSize(550, 534);
        this.setLocation(750, 200);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  //点击关闭图标后将关闭
        
        
        information schoolPersonnelDAO = new information();
        
        //学生面板的设计
        studentjpanel = new JPanel();
        studentjpanel.setLayout(new FlowLayout());
        studentoperation = new JPanel();
        studentoperation.setLayout(new FlowLayout());
        btnStuAdd = new JButton("录入学生信息");
        studentoperation.add(btnStuAdd);  //将录入按钮添加到学生按钮面板上
        tmStudent = new TableModel();  //初始化一个学生信息表格模型
        tmStudent.setColumnNames(strstudent);//初始化表格标题
        
        try {
            tmStudent.setMessages(schoolPersonnelDAO.getStudentList());  //初始化表格数据
        } catch (IOException e) {
            e.printStackTrace();
        }
        tablestudent = new JTable(tmStudent);  //将表格模型加入表格
        tablestudent.setRowHeight(30);
        stuentmessage = new JScrollPane(tablestudent);  //将表格加入信息模板
        studentjpanel.add(stuentmessage);
        studentjpanel.add(studentoperation);
        
        //老师面板的设计
        teacherjpanel = new JPanel();
        teacherjpanel.setLayout(new FlowLayout());
        teacheroperation = new JPanel();
        btnTeaAdd = new JButton("录入教师信息");
        teacheroperation.add(btnTeaAdd);
        tmTeacher = new TableModel();
        tmTeacher.setColumnNames(strteacher);
        try {
            tmTeacher.setMessages(schoolPersonnelDAO.getTeacherList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableteacher = new JTable(tmTeacher);
        tableteacher.setRowHeight(30);
        teachermessage = new JScrollPane(tableteacher);
        teacherjpanel.add(teachermessage);
        teacherjpanel.add(teacheroperation);

       // 选项卡的高度与宽度无法调整
        schoolperdonneljpanel = new JTabbedPane();
        schoolperdonneljpanel.add(studentjpanel);
        schoolperdonneljpanel.add(teacherjpanel); //面板容器添加两个面板
        schoolperdonneljpanel.setTitleAt(0, "                                   学生                                     ");
        schoolperdonneljpanel.setTitleAt(1, "                                   教师                                     ");
        this.setContentPane(schoolperdonneljpanel);
        MainJFrame mainJFrame = this;   //将这个主窗口传递给一个new的新对象
        btnTeaAdd.addActionListener(new AbstractAction() {
            /**
			 * 点击录入教师信息按钮后弹出表单
			 */
			private static final long serialVersionUID = 1L;
			@Override
            public void actionPerformed(ActionEvent e) {
				addTeacher addTeacherDialog = new addTeacher(mainJFrame);
                addTeacherDialog.setVisible(true);
            }
        });

        btnStuAdd.addActionListener(new AbstractAction() {
            /**
			 * 点击录入学生信息按钮后弹出表单
			 */
			private static final long serialVersionUID = 1L;
			@Override
            public void actionPerformed(ActionEvent e) {
				addStudent addStudentDialog = new addStudent(mainJFrame);
                addStudentDialog.setVisible(true);
            }
        });
    }
}
