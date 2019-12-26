package com.zr.ems.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * �������ݿ�Ĺ�����
 */
public class MyDBUtils {

	public static String driver;
	public static String url;
	public static String username;
	public static String password;

	// ���ڴ���db.properties�ļ������ݵļ���
	private static Properties properties = new Properties();

	/**
	 * ˽�й��췽��
	 */
	private MyDBUtils() {
	}

	static {
		try {
			// ��ȡdb.properties�ļ�
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");

			// �Ѷ�ȡ����db.properties�ļ��е����ݼ��뵽Properties������
			properties.load(is);

			// �����ݶ�ȡ�����ݸ�ֵ������
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");

			// ע������
			Class.forName(driver);

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ���ø÷����ͻ�ȡ�����ݿ�����Ӷ���
	 * @return
	 */
	public static Connection getConnection()
	{
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
