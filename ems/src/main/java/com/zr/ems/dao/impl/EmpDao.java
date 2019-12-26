package com.zr.ems.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zr.ems.dao.IEmpDao;
import com.zr.ems.pojo.Emp;
import com.zr.ems.utils.MyDBUtils;

public class EmpDao implements IEmpDao{

	@Override
	public Emp findEmpByNicknameAndPassword(String nickname, String password) {
		
		String sql = "select * from emp where nickname=? and password=?";

		// 1, ��ȡ���������ݿ�Ķ���
		Connection connection = MyDBUtils.getConnection();

		try {

			// 2, ��ȡ��ִ��sql����Ԥ�������
			PreparedStatement prepareStatement = connection.prepareStatement(sql);

			// 3, ��sql�е�ռλ��[?] ��ֵ
			prepareStatement.setString(1, nickname);
			prepareStatement.setString(2, password);

			// 4, ִ��sql���, �õ������
			ResultSet resultSet = prepareStatement.executeQuery();

			// 5, �����ֵ, ���װ, Ȼ��, ����
			while (resultSet.next()) {
				// ��ȡ���ݿ��ж�Ӧ������ֵ
				int id = resultSet.getInt("id");
				String gender = resultSet.getString("gender");
				double salary = resultSet.getDouble("salary");

				// �����ݴ��뵽������
				Emp emp = new Emp(id, nickname, password, gender, salary);
				return emp;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// �Ͽ�����, �ͷ���Դ
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public int findEmpByNickname(String nickname) {
		String sql = "select * from emp where nickname=?";

		// 1, ��ȡ���������ݿ�Ķ���
		Connection connection = MyDBUtils.getConnection();

		try {

			// 2, ��ȡ��ִ��sql����Ԥ�������
			PreparedStatement prepareStatement = connection.prepareStatement(sql);

			// 3, ��sql�е�ռλ��[?] ��ֵ
			prepareStatement.setString(1, nickname);

			// 4, ִ��sql���, �õ������
			ResultSet resultSet = prepareStatement.executeQuery();

			// 5, �����ֵ, ���װ, Ȼ��, ����
			while (resultSet.next()) {
	
				return 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// �Ͽ�����, �ͷ���Դ
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public void registerEmp(Emp emp) {
		String sql = "insert into emp values(null, ?, ?, ?, ?)";

		Connection connection = MyDBUtils.getConnection();

		try {
			// ��ȡ��ִ��sql�Ķ���
			PreparedStatement prepareStatement = connection.prepareStatement(sql);

			// ��ռλ����ֵ
			prepareStatement.setString(1, emp.getNickname());
			prepareStatement.setString(2, emp.getPassword());
			prepareStatement.setString(3, emp.getGender());
			prepareStatement.setDouble(4, emp.getSalary());

			// ִ�� ��, ɾ, ��, ����ʹ��executeUpdate()����
			prepareStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
    public List<Emp> findEmp() {
    	List<Emp> list = new ArrayList<>();
		
		String sql = "select * from emp ";

		// 1, ��ȡ���������ݿ�Ķ���
		Connection connection = MyDBUtils.getConnection();

		try {

			// 2, ��ȡ��ִ��sql����Ԥ�������
			PreparedStatement prepareStatement = connection.prepareStatement(sql);		
			// 4, ִ��sql���, �õ������
			ResultSet resultSet = prepareStatement.executeQuery();

			// 5, �����ֵ, ���װ, Ȼ��, ����
			while (resultSet.next()) {
				// ��ȡ���ݿ��ж�Ӧ������ֵ
				int id = resultSet.getInt("id");
				String nickname = resultSet.getString("nickname");
				String password = resultSet.getString("password");
				String gender = resultSet.getString("gender");
				double salary = resultSet.getDouble("salary");
					// ��ԃ���˽Y�������Y�����b��һ��emp������
				Emp emp = new Emp(id, nickname, password, gender, salary);
					// ��������뼯��
					list.add(emp);
				}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// �Ͽ�����, �ͷ���Դ
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
    public void DelByid(int id) {
    	
    	String sql = "delete  from emp where id=?";
    	Connection connection=MyDBUtils.getConnection();
    	try {
			// ��ȡ��ִ��sql�Ķ���
			PreparedStatement prepareStatement = connection.prepareStatement(sql);

			// ��ռλ����ֵ
			prepareStatement.setInt(1, id);

			// ִ�� ��, ɾ, ��, ����ʹ��executeUpdate()����
			prepareStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }

	@Override
	public void UpdateByid(Emp emp) {
		// ��ȡ���Ӷ���
		Connection connection=MyDBUtils.getConnection();
				// Ҫִ�е�sql���
		String sql = "update emp set nickname=?,gender=?,salary=? where id=?";
				try {
					PreparedStatement prepareStatement = connection.prepareStatement(sql);
					
					prepareStatement.setString(1, emp.getNickname());
					prepareStatement.setString(2, emp.getGender());
					prepareStatement.setDouble(3, emp.getSalary());
					prepareStatement.setInt(4, emp.getId());
					
					prepareStatement.executeUpdate();
					
					
				} catch (Exception e) {
					
				} finally {
					try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
	}

	@Override
	public Emp FindByid(int id) {
		
		String sql = "select nickname,gender,salary from emp where id=?";

		// 1, ��ȡ���������ݿ�Ķ���
		Connection connection = MyDBUtils.getConnection();

		try {

			// 2, ��ȡ��ִ��sql����Ԥ�������
			PreparedStatement prepareStatement = connection.prepareStatement(sql);

			// 3, ��sql�е�ռλ��[?] ��ֵ
			prepareStatement.setInt(1, id);

			// 4, ִ��sql���, �õ������
			ResultSet resultSet = prepareStatement.executeQuery();

			// 5, �����ֵ, ���װ, Ȼ��, ����
			while (resultSet.next()) {
				// ��ȡ���ݿ��ж�Ӧ������ֵ
				String nickname = resultSet.getString("nickname");
				String gender = resultSet.getString("gender");
				double salary = resultSet.getDouble("salary");

				// �����ݴ��뵽������
				Emp emp = new Emp(id, nickname, gender, salary);
				return emp;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// �Ͽ�����, �ͷ���Դ
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;

		
		
	}
	
}

