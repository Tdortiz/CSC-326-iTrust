package edu.ncsu.csc.itrust.controller.user;

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
	
	public String getUserNameForID(String mid) throws DBException{
		User user = null;
		if( mid == null) return "";
		if(mid.isEmpty()) return "";
		long id = -1;
		try{
			id = Long.parseLong(mid);
		}
		catch(NumberFormatException ne){
			return "";
		}
		//if(id<1) return "";
		user = userData.getByID(id);
		if(user.getRole().equals(Role.TESTER)){
			return Long.toString(user.getMID());
		}
		else{
			return user.getLastName().concat(", "+user.getFirstName());
		}

		
	}
	public String getUserRoleForID(String mid) throws DBException{
		User user = null;
		if( mid == null) return "";
		if(mid.isEmpty()) return "";
		long id = -1;
		try{
			id = Long.parseLong(mid);
		}
		catch(NumberFormatException ne){
			return "";
		}
		if(id<1) return "";
		user = userData.getByID(id);
		return user.getRole().getUserRolesString().toLowerCase();
	}
	
	public boolean doesUserExistWithID(String mid) throws DBException{
		User user = null;
		if( mid == null) return false;
		long id = -1;
		try{
			id = Long.parseLong(mid);
		}
		catch(NumberFormatException ne){
			return false;
		}
		user = userData.getByID(id);
		if(!(user == null)){
				return true;
		}
		else{
			return false;
		}

		
	}
	
	
	

}
