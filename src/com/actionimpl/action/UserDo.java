package com.actionimpl.action;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.actionimpl.UserImpl;
import com.javabean.User;
import com.jdbc.DbUtil;

public class UserDo implements UserImpl{ 

	@Override
	public int register(User user) {
		// TODO 自动生成的方法存根
		//注册完成了自动登录，并且find，
		Connection connection = null;
		Statement preparedStatement = null;
		int result = 0;
		try {
			connection = DbUtil.getInstance().getConnection();
			String sql = "insert into users value(null,null,'"+user.getPassword()+"',null,null,'"+user.getUemail()+"');";
			System.out.println("register sql:"+sql);
			preparedStatement = connection.createStatement();
			result = preparedStatement.executeUpdate(sql);
			if(result != 0){
				return login(user.getUemail(), user.getPassword());
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			
		} finally{
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
		return 0;
	}

	@Override
	public int login(String username, String password) {
		// TODO 自动生成的方法存根
		//当用户登录通过的时候，返回用户的ID。用Find（ID）再去查找User的信息，然后在前端页面进行显示。
		//未通过返回 0 或者 -1，，有需要判断用户密码错误或者账号错误时候返回不同的<0的标识码。
		
		Connection connection = null;
		Statement preparedStatement =null;
		ResultSet result = null;
		try {
			connection = DbUtil.getInstance().getConnection();
			String sql = "select uid,passname from users where uemail='"+username+"' and passname='"+password+"'";
			System.out.println("userLong 数据库："+sql);
			preparedStatement = connection.createStatement();
			result = preparedStatement.executeQuery(sql);
			while(result.next()){
				int uid = result.getInt("uid");
				//或者直接返回用户的email。
				return uid;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				result.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public void remove(int uid) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public int update(User user) {
		//传过来的数据中没有uimage的二进制文件，只有一些string和文件名称？？不能取到文件。
		//接下来的步骤是连接数据库进行数据的更新。返回受影响的行数，作为函数的返回结果，判断是否执行成功。
		//用户找回密码没有实现。
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DbUtil.getInstance().getConnection();
			String sql = "update users set username=?,uautograph=?,passname=?,uimage=? where uemail =?";
			//注意：：用户的头像文件没有修改
			System.out.println("update user info :"+sql);
			preparedStatement = connection.prepareStatement(sql);
			//可以先进行预编译，然后将SQL的参数传入到statement中进行执行。
			//通过预编译处理的时候可以将参数完全包装为string 例如这样的数据也能写入( i'm a good boy.)不会因为'出问题;
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getUantograph());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setBlob(4, user.getUimage());
			preparedStatement.setString(5, user.getUemail());
			int result = preparedStatement.executeUpdate();
			System.out.println("update resule success : "+result);
			return result;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
		return 0;
		// TODO 自动生成的方法存根
		
	}

	@Override
	public User find(int uid) {
		// TODO 自动生成的方法存根
		Connection connection = null;
		Statement preparedStatement = null;
		ResultSet resultSet = null;
		User user = new User();
		try {
			connection = DbUtil.getInstance().getConnection();
			String sql = "select * from users where uid = "+uid;
			System.out.println("User Find :"+sql);
			preparedStatement = connection.createStatement();
			resultSet = preparedStatement.executeQuery(sql);
			int cnt = 0;
			while(resultSet.next()){
				cnt++;
				user.setUid(resultSet.getInt("uid"));
				user.setPassword(resultSet.getString("passname"));
				user.setUantograph(resultSet.getString("uautograph"));
				user.setUimage(resultSet.getBinaryStream("uimage"));
				user.setUsername(resultSet.getString("username"));
				user.setUemail(resultSet.getString("uemail"));
			}
			System.out.println("Find user counter: "+cnt);
			return user;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	//这个方法用于放回用户的头像文件。
	public InputStream returnImage(int id) {
		// TODO 自动生成的方法存根
		User user = find(id);
		InputStream inputStream = (InputStream)user.getUimage();
		return inputStream;
	}
}
