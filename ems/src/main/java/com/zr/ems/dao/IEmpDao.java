package com.zr.ems.dao;

import java.util.List;

import com.zr.ems.pojo.Emp;

public interface IEmpDao {

	/**
	 * ͨ���ǳƺ��������emp����
	 * 
	 * @param nickname
	 * @param password
	 * @return
	 */
	Emp findEmpByNicknameAndPassword(String nickname, String password);

	/**
	 * �����ǳ��ж��û��Ƿ����
	 * 
	 * @param nickname
	 * @return
	 */
	int findEmpByNickname(String nickname);

	/**
	 * ע��Ա����Ϣ
	 * 
	 * @param emp
	 */
	void registerEmp(Emp emp);
	/**
	 * ��ѯ����Ա����Ϣ
	 * @return
	 */
	List<Emp> findEmp();
	/**
	 * ����idɾ��Ա����Ϣ
	 */
	void DelByid(int id);

	void UpdateByid(Emp emp);

	Emp FindByid(int id);
}
