package com.zr.ems.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.zr.ems.pojo.Emp;
import com.zr.ems.service.IEmpService;
import com.zr.ems.service.impl.EmpService;

@SuppressWarnings("serial")
@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet{
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��������ı���
		req.setCharacterEncoding("utf-8");
		//���߿ͻ�����ν������������͵ķ���
		resp.setContentType("text/html;charset=utf-8");
		//�����������
		String cmd =req.getParameter("cmd");
		
		if("login".equals(cmd)) {
			login(req,resp);
		}
		else if("register".equals(cmd)) {
			
			register(req,resp);
		}
		else if("findAll".equals(cmd)) {
			
			findAll(req,resp);
			
		}
		else if("delete".equals(cmd)) {
			delete(req,resp);
			
		}
		else if("update".equals(cmd)) {
			update(req,resp);
		}
		else if("FindByid".equals(cmd)) {
			FindByid(req,resp);
		}
	
	}

	

	/**
	 * ���ڴ���ע�Ṧ�ܵķ���
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ���Ȼ�ȡ��ǰ̨ҳ�洫�ݵ�����
				String nickname = req.getParameter("nickname");
				String password = req.getParameter("password");
				String gender = req.getParameter("gender");
				double salary = Double.parseDouble(req.getParameter("salary"));
				
				/**
				 * ���������ж��ǳ��Ƿ��Ѿ���ʹ��, �����ʹ��, ������ע��.
				 */
				// ��ȡ���������
				IEmpService service = new EmpService();

				// �����ж��û����Ƿ���ڵķ���.
				// 1��ʾ����, ��1��ʾ������
				int flag = service.findEmpByNickname(nickname);

				if (flag == 1) {

					resp.getWriter().write("<script>alert('�˺��Ѿ����ڣ�'); window.location='register.jsp'; window.close();</script>");
					resp.getWriter().flush();
					return;
				} else {
					
					// �����е����ݷ�װ��ʵ�������
					Emp emp = new Emp(1, nickname, password, gender, salary);

					// ����ע��ķ���
					service.registerEmp(emp);
					//��ʾע��ɹ��󣬷��ص�¼ҳ��
					resp.getWriter().write("<script>alert('ע��ɹ���'); window.location='login.jsp'; window.close();</script>");
					resp.getWriter().flush();
				}

		
	}
	/**
	 * ר�����ڴ����¼���ܵķ���
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void login (HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		/**
		 * 1.��ȡ�û���������
		 * 2.ȥ���ݿ�ȶ� 
		 * 3.���������������ת
		 * 4.��������ڣ�������û��˺Ż���������
		 */
		
		//1.��ȡ�û���������
		String nickname=req.getParameter("nickname");
		String password=req.getParameter("password");
		
		//2.ȥ���ݿ�ȶ� 
		IEmpService service = new EmpService();

		Emp emp = service.findEmpByNicknameAndPassword(nickname, password);

		if (emp != null) {
			resp.getWriter().write("<script>alert('��ϲ�㣬��¼�ɹ���'); window.location='EmpServlet?cmd=findAll'; window.close();</script>");
			resp.getWriter().flush();

			return;
		} else {
			//resp.getWriter().write("�˺Ż���������, ������¼");

			resp.getWriter().write("<script>alert('�˺Ż���������, ������¼'); window.location='login.jsp'; window.close();</script>");
			resp.getWriter().flush();
		}
		
	}
	/**
	 * 
	 * ͨ������������id���ҵ�Ա����Ϣ
	 * ��Ա����Ϣ���͵��޸�ҳ��
	 * ���û����ĺ����Ϣ���ս�ȥ
	 * �������ݿ�����޸�
	 * ������ҳ��
	 * @throws IOException 
	 * @throws ServletException 
	 * 
	 */
	private void FindByid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IEmpService service = new EmpService();
		int id = Integer.parseInt(req.getParameter("id"));
		Emp emp=service.FindByid(id);
		req.setAttribute("emp", emp);
		req.getRequestDispatcher("update.jsp").forward(req, resp);
		
	}
	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/*
		 * 1.����ҳ����޸���Ϣ
		 * 2.���и���
		 * 3.������ɺ󷵻���ҳ��
		 */
		IEmpService service = new EmpService();
		int id = Integer.parseInt(req.getParameter("id"));
		String nickname = req.getParameter("nickname");
		String gender = req.getParameter("gender");
		double salary = Double.parseDouble(req.getParameter("salary"));
		Emp emp = new Emp(id, nickname, gender, salary);
		service.UpdateByid(emp);
		resp.getWriter().write("<script>alert('�޸ĳɹ���'); window.location='EmpServlet?cmd=findAll'; window.close();</script>");
		resp.getWriter().flush();
		
		
		}
	/**
	 * 
	 * ͨ��idɾ��Ա����Ϣ
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			IEmpService service = new EmpService();
			int id = Integer.parseInt(req.getParameter("id"));
			System.out.println(id);
			service.DelByid(id);
			resp.getWriter().write("<script>alert('ɾ���ɹ���'); window.location='EmpServlet?cmd=findAll'; window.close();</script>");
			resp.getWriter().flush();
			
		}
	private void findAll (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
			
			/**
			 * 1.�������ݿ���Ա����Ϣ
			 * 2.��������Ϣ���͵�ǰ��չʾ
			 * 3.
			 */
					IEmpService service = new EmpService();
					List<Emp> list=service.findEmp();
					req.setAttribute("list", list);
					req.getRequestDispatcher("main.jsp").forward(req, resp);

			
		}
}
