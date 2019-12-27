package dao;

import bean.User;
import utils.FileInOut;
import java.io.IOException;

public class logRegis {    //进行登录用户的查询和录入

	FileInOut userInTxt = new FileInOut();
	FileInOut readFromTxt = new FileInOut();

    public void addUser(User user) throws IOException {  //录入用户
    	userInTxt.writeUserTxt(user);
    }

    public boolean verificationUser(User user) throws IOException {  //根据用户名和密码进行登录用户的验证
        if (readFromTxt.readUserTxt(user)) {
            return true;
        }
        return false;
    }
}
