package com.actionimpl.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.actionimpl.LikeImpl;
import com.jdbc.DbUtil;

public class LikeDo implements LikeImpl{

	@Override
	public int add(int uid, int mid) {
		// TODO 自动生成的方法存根
		Connection connection = null;
		Statement statement = null;
		try {
			
			connection = DbUtil.getInstance().getConnection();
			String sql = "insert into likes value(null,"+uid+","+mid+")";
			statement = connection.createStatement();
			int result = statement.executeUpdate(sql);
			return result;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int remove(int uid,int mid) {
		// TODO 自动生成的方法存根
		Connection connection = null;
		Statement statement = null;
		try {
			
			connection = DbUtil.getInstance().getConnection();
			String sql = "delete from likes where l_uid = "+uid+" and l_mid = "+mid;
			statement = connection.createStatement();
			int result = statement.executeUpdate(sql);
			return result;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public ArrayList<Integer> likelist(int uid) {
		// TODO 自动生成的方法存根
		Connection connection = null;
		Statement statement = null;
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		ResultSet resultSet = null;
		try {
			connection = DbUtil.getInstance().getConnection();
			String sql = "select * from likes where l_uid="+uid;
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()){
				Integer integer = resultSet.getInt("l_mid");
				arrayList.add(integer);
			}
			return arrayList;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
