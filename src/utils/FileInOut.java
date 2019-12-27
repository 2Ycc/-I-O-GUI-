package utils;

import bean.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileInOut {  //进行txt文件的读取
	public boolean readUserTxt(User user) throws IOException {  //进行登录用户的信息验证
        File file = new File("D:\\user.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);  //采用字符缓冲流进行读取
        while (true) {
            String line = br.readLine();
            if (line == null) {   //当查询到最后没有可查的时候退出循环
                break;
            }
            String[] strs = line.split(",");  //进行字符串分割 取出用户名和密码
            if (strs[0].equals(user.getUsername()) && strs[1].equals(user.getPassword())) {
                br.close();
                fr.close();
                return true;
            }
        }
        return false;
    }

    public List<Student> readStudentTxt() throws IOException {  //将所有学生的信息以一个学生对象的集合返回
        List<Student> students = new ArrayList<>();
        Student student;
        File file = new File("D:\\student.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            if (!line.equals("") && line != null) {
                String[] strs = line.split(",");  //将读取一行的进行字符串分割取出id和姓名和专业
                student = new Student();
                student.setId(Integer.parseInt(strs[0]));
                student.setName(strs[1]);
                student.setMajor(strs[2]);
                students.add(student);
            }
            
        }
        return students;
    }

    public List<Teacher> readTeacherTxt() throws IOException {  //将所有老师的信息以一个老师对象的集合返回
        List<Teacher> teachers = new ArrayList<>();
        Teacher teacher;
        File file = new File("D:\\teacher.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            if (!line.equals("") && line != null) {
                teacher = new Teacher();
                String[] strs = line.split(",");  //将读取一行的进行字符串分割取出id和姓名和学院
                teacher.setId(Integer.parseInt(strs[0]));
                teacher.setName(strs[1]);
                teacher.setCollege(strs[2]);
                teachers.add(teacher);
            }

        }
        return teachers;
    }
    
    public void writeStudentTxt(Student student) throws IOException {  //进行学生信息txt的录入
        File file = new File("D:\\student.txt");
        FileWriter fw = new FileWriter(file, true);  //true表示可以进行追加，防止后面写入的用户将前面的学生覆盖掉
        BufferedWriter bw = new BufferedWriter(fw);  //用字符缓冲流进行文件的写入
        String str = student.getId() + "," + student.getName() + "," + student.getMajor();
        bw.write(str);
        bw.newLine(); //换行，这样存储的时候就是每一个学生一行信息，方便读取操作时候的方便
        bw.flush();  //将缓冲区的内容进行录入
        bw.close();
        fw.close();
    }

    public void writeUserTxt(User user) throws IOException {  //进行登录用户信息txt的录入
        File file = new File("D:\\user.txt");
        FileWriter fw = new FileWriter(file, true);  //true表示可以进行追加，防止后面写入的用户将前面的用户覆盖掉
        BufferedWriter bw = new BufferedWriter(fw);  //用字符缓冲流进行文件的写入
        String str = user.getUsername() + "," + user.getPassword();
        bw.write(str);
        bw.newLine(); //换行，这样存储的时候就是每一个用户一行信息，方便读取操作时候的方便
        bw.flush();  //将缓冲区的内容进行录入
        bw.close();
        fw.close();
    }

    public void writeTeacherTxt(Teacher teacher) throws IOException {  //进行老师信息txt的录入
        File file = new File("D:\\teacher.txt");
        FileWriter fw = new FileWriter(file, true);  //true表示可以进行追加，防止后面写入的用户将前面的老师覆盖掉
        BufferedWriter bw = new BufferedWriter(fw);  //用字符缓冲流进行文件的写入
        String str = teacher.getId() + "," + teacher.getName() + "," + teacher.getCollege();
        bw.write(str);
        bw.newLine(); //换行，这样存储的时候就是每一个老师一行信息，方便读取操作时候的方便
        bw.flush();  //将缓冲区的内容进行录入
        bw.close();
        fw.close();
    }
    
}
