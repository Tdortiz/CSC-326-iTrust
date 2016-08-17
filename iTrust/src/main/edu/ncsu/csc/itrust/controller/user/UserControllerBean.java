package edu.ncsu.csc.itrust.controller.user;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.DataBean;
import edu.ncsu.csc.itrust.model.old.enums.Role;
import edu.ncsu.csc.itrust.model.user.User;
import edu.ncsu.csc.itrust.model.user.UserMySQLConvBean;

@ManagedBean(name="user")
public class UserControllerBean {
	private DataBean<User> userData;
	public UserControllerBean() throws DBException{
		userData = new UserMySQLConvBean();
		
	}
	
	public String getUserNameForID(long mid) throws DBException{
		User user = userData.getByID(mid);
		if(user.getRole().equals(Role.TESTER)){
			return Long.toString(user.getMID());
		}
		else{
			return user.getLastName().concat(", "+user.getFirstName());
		}

		
	}
	
	
	

}
