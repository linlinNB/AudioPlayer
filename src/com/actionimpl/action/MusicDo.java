package com.actionimpl.action;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.actionimpl.MusicImpl;
import com.actionimpl.SongImpl;
import com.javabean.Music;
import com.javabean.Song;
import com.jdbc.DbUtil;
import com.sun.javafx.sg.prism.web.NGWebView;
import com.sun.prism.Presentable;

public class MusicDo implements MusicImpl {

	@Override
	public void saveMusic(Music music) {
		// TODO 自动生成的方法存根
		System.out.println("输出文件的path:" + music.getMparh());
		// 这里输出的文件路径是正常的，但是拼接SQL到数据库中以后,string发生了变化，
		// 没有了\（当做转义字符了，没有存入到数据库中），怎么保存文件路径到数据库中？
		// E:\\aa 这样的string才能存入数据库中一个\ ==> E:\a
		// String sql = "insert into musics
		// values(null,'"+music.getMname()+"','"+music.getMauthor()+"','"+music.getMparh()+"','','流行','哈哈')";
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
			System.out.println("jieguo:" + result);

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

	}

	@Override
	public ArrayList<Music> findMusicById(ArrayList<Integer> idList) {
		// TODO 自动生成的方法存根
		ArrayList<Music> arrayList = new ArrayList<Music>();
		// 循环执行statement,返回一个结果。先预编译，然后循环传参执行。
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DbUtil.getInstance().getConnection();
			String sql = "select * from musics where mid=?";
			preparedStatement = connection.prepareStatement(sql);
			// 多组参数的执行两种方法，1.一条一条的按顺序执行(多次请求数据库)。2.把每条添加到batch，一起打包执行(一次请求数据库)。
			for (int i = 0; i < idList.size(); i++) {
				preparedStatement.setInt(1, idList.get(i));
				//打包批处理返回的结果为int[]，存放受影响行数，不适合查，适合改删增
				//一批处理，会当为一个事务，有一条出错全部rollback,如果没问题，需要手动commit。
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Music music = new Music();
					music.setMname(resultSet.getString("mname"));
					// 添加其他的属性。
					arrayList.add(music);
					music = null;
				}
			}
			return arrayList;
			// http://blog.csdn.net/lmj623565791/article/details/25652197
			// http://blog.csdn.net/maguochao_mark/article/details/52291791
			// 对象的创建与销毁，防止内存泄漏，这个对象在这种情况下回自动回收，有可能有其他的情况不会回收需要注意处理。

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

}
