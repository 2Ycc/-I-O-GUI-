package dao;

import bean.Student;
import bean.Teacher;
import utils.FileInOut;
import java.io.IOException;
import java.util.List;

public class information {  //进行学生老师的多态父类存储的录入和查询

	FileInOut txt = new FileInOut();

    public void addStudent(Student student) throws IOException {  //进行学生信息的录入
    	txt.writeStudentTxt(student);
    }

    public void addTeacher(Teacher teacher) throws IOException {  //进行老师信息的录入
    	txt.writeTeacherTxt(teacher);
    }

    public String[][] getStudentList() throws IOException {  //将所有学生信息以字符串数组的方式返回
        List<Student> list = txt.readStudentTxt(); //从文件中读出学生信息
        String[][] strings = new String[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            strings[i][0] = Integer.toString(list.get(i).getId());
            strings[i][1] = list.get(i).getName();
            strings[i][2] = list.get(i).getMajor();
        }
        return strings;
    }

    public String[][] getTeacherList() throws IOException {  //将所有老师信息以字符串数组的方式返回
        List<Teacher> list = txt.readTeacherTxt(); //从文件中读出学生信息
        String[][] strings = new String[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            strings[i][0] = Integer.toString(list.get(i).getId());
            strings[i][1] = list.get(i).getName();
            strings[i][2] = list.get(i).getCollege();
        }
        return strings;
    }
}
