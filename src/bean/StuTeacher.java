package bean;

public class StuTeacher {//存储教师与学生相同的部分
    private int id;		 //用户的ID
    private String name; //用户的姓名

    public int getId() {
        return id;		//返回ID
    }
    public void setId(int id) {
        this.id = id;	//设置ID
    }
    public String getName() {
        return name;	//返回姓名
    }
    public void setName(String name) {
        this.name = name; //设置姓名
    }
}
