package com.actionimpl.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.actionimpl.SongImpl;
import com.javabean.Music;
import com.javabean.Song;
import com.jdbc.DbUtil;

public class SongDo implements SongImpl{
	
	@Override
	public void saveSong(Song song,String filepath) {
		// TODO 自动生成的方法存根
		
		//这里必须需要绝对路径，相对路径取不到文件。。。。
		filepath = "E:/个人文件/picture/"+filepath;
		FileInputStream file = null;
		try {
			file = new FileInputStream(filepath);
		} catch (FileNotFoundException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		String sql = "insert into demo value(null,?,'"+song.getSname()+"','"+song.getSongname()+"',now(),'"+song.getSongpath()+"')";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = (Connection) DbUtil.getInstance().getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			try {
				preparedStatement.setBinaryStream(1, file,file.available());
				System.out.println("二进制sql拼接正常");
				int result = preparedStatement.executeUpdate();
				System.out.println("这是执行返回的结果Result:"+result);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void removeSong(int id) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void updataSong(Song song) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public List<Song> selectSong() {
		return null;
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Song selectByName(String sname) {
		//在这里返回数据库查出的对象，注意simage的文件路径需要特殊处理。
		String sql = "select * from demo where sname = '"+sname+"';";
		System.out.println("sql"+sql);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Song song = new Song();
		try {
			connection = DbUtil.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				song.setSname(resultSet.getString("sname"));
				song.setSdate(resultSet.getDate("sdate"));
				song.setSongname(resultSet.getString("songname"));
				song.setSongpath(resultSet.getString("songpath"));
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return song;
		// TODO 自动生成的方法存根
		
	}

	@Override
	public InputStream getImageBySname(String sname) {
		// TODO 自动生成的方法存根
		String sql = "select simage from demo where sname='"+sname+"'";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		InputStream inputStream = null;
		try {
			connection = DbUtil.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				inputStream = resultSet.getBinaryStream("simage");
				return inputStream;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
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

	@Override
	public void saveMusic(Music music) {
		// TODO 自动生成的方法存根
		
	}

}
