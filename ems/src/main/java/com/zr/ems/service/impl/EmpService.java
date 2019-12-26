package com.zr.ems.service.impl;

import java.util.List;

import com.zr.ems.dao.IEmpDao;
import com.zr.ems.dao.impl.EmpDao;
import com.zr.ems.pojo.Emp;
import com.zr.ems.service.IEmpService;

/**
 * 
 * ����ӿڵ�ʵ����
 * 
 * @author Administrator
 *
 */
public class EmpService implements IEmpService {
	// ��ȡ��ר�Ŵ���־ò㷽��Ķ���
	IEmpDao empDao = new EmpDao();
	@Override
	public Emp findEmpByNicknameAndPassword(String nickname, String password) {

		// ����dao�еķ���
		return empDao.findEmpByNicknameAndPassword(nickname, password);
	}

	@Override
	public int findEmpByNickname(String nickname) {

		// ����dao�еķ���
		return empDao.findEmpByNickname(nickname);
	}

	@Override
	public void registerEmp(Emp emp) {
		
		// ����dao�еķ���
		 empDao.registerEmp(emp);

	}
	public List<Emp> findEmp() {
		return empDao.findEmp();
	}
	public void DelByid(int id) {
		empDao.DelByid(id);
	}

	@Override
	public void UpdateByid(Emp emp) {
		empDao.UpdateByid( emp);
		
	}

	@Override
	public Emp FindByid(int id) {
		
		return empDao.FindByid(id);
	}
}
