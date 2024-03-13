package com.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.entity.User;

public class UserDAOImpl implements UserDAO{
	
	private Connection conn;

	public UserDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean userRegister(User user) {
		// TODO Auto-generated method stub
		boolean f = false;
		try {
			String query = "insert into user(name,email,phno,password) values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user.getName());
			ps.setString(2,user.getEmail());
			ps.setString(3, user.getPhno());
			ps.setString(4, user.getPassword());
			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected == 1) {
				f = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}

	public User login(String email, String password) {
		
		User us = null;
		
		try {
			String query = "select * from user where email=? and password=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				us = new User();
				us.setId(rs.getInt(1));
				us.setName(rs.getString(2));
				us.setEmail(rs.getString(3));
				us.setPhno(rs.getString(4));
				us.setPassword(rs.getString(5));				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return us;
	}

	public boolean checkPassword(int id,String password) {

		boolean f = false;
		
			try {
				String sqlString = "select * from user where id=? and password=?";
				PreparedStatement pStatement = conn.prepareStatement(sqlString);
				pStatement.setInt(1, id);
				pStatement.setString(2, password);
				
				ResultSet rs = pStatement.executeQuery();
				while(rs.next()) {
					f = true;
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		return f;
	}
	public boolean checkEncryptedPassword(String password, User user ) {
		 return BCrypt.checkpw(password, user.getPassword());
	}

	public boolean updateProfile(User user) {
		
		boolean f = false;
		try {
//			System.out.println(us);
			String query = "update user set name=?,email=?,phno=? where id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user.getName());
			ps.setString(2,user.getEmail());
			ps.setString(3, user.getPhno());
			ps.setInt(4, user.getId());
//			ps.setInt(5, us.getId());
			int i = ps.executeUpdate();
			if(i == 1) {
				f = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	public boolean changePassword(String email, String password) {
	    boolean f = false;
	    try {
	        String query = "update user set password=? where email=?";
	        PreparedStatement ps = conn.prepareStatement(query);
	            ps.setString(1,  BCrypt.hashpw(password, BCrypt.gensalt()));
	            ps.setString(2, email);
	            int i = ps.executeUpdate();
	            if (i == 1) {
	                f = true;
	            }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return f;
	}


	public boolean checkUser(String email) {
		
		boolean f = true;
		try {
//			System.out.println(us);
			String query = "select * from user where email=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,email);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				f= false;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;

	}
	
	public User getUserByEmail(String email) {
        User user = null;
        String query = "SELECT * FROM user WHERE email = ?";

        try{
        	PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            try {
            	ResultSet rs = stmt.executeQuery(); 
            
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPhno(rs.getString("phno"));
                    user.setPassword(rs.getString("password")); // Note: Password is stored hashed in the database
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
	
	
	
}
