package com.actionimpl.action;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.actionimpl.SongImpl;
import com.javabean.Music;
import com.javabean.Song;
import com.jdbc.DbUtil;

public class MusicDo implements SongImpl {

	@Override
	public void saveSong(Song song, String filepath) {
		// TODO 自动生成的方法存根
		
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
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Song selectByName(String sname) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public InputStream getImageBySname(String sname) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void saveMusic(Music music) {
		// TODO 自动生成的方法存根
		System.out.println("输出文件的path:"+music.getMparh());
		//这里输出的文件路径是正常的，但是拼接SQL到数据库中以后,string发生了变化，
		//没有了\（当做转义字符了，没有存入到数据库中），怎么保存文件路径到数据库中？
		//E:\\aa  这样的string才能存入数据库中一个\  ==> E:\a  
		//String sql = "insert into musics values(null,'"+music.getMname()+"','"+music.getMauthor()+"','"+music.getMparh()+"','','流行','哈哈')";
		String sql = "insert into musics values(null,?,?,?,'','流行','哈哈')";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    try {
			connection = DbUtil.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, music.getMname());
			preparedStatement.setString(2, music.getMauthor());
			preparedStatement.setString(3, music.getMparh());
			System.out.println("SQL语句拼接完成。");
			int result = preparedStatement.executeUpdate();
			System.out.println("jieguo:"+result);
			
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

}
