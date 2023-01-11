package model.bo;

import model.dao.CheckLoginDAO;

public class CheckLoginBO {
	CheckLoginDAO checkLoginDAO = new CheckLoginDAO();
	public int getAcountRole(String tenDangNhap, String matKhau) {
		// TODO Auto-generated method stub
		return checkLoginDAO.getAccountRole(tenDangNhap,matKhau);
	}

}
