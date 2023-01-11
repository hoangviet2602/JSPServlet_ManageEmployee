package model.bo;

import model.dao.DeleteProductDAO;

public class DeleteProductBO {
	DeleteProductDAO deleteProductDAO = new DeleteProductDAO();
	public void deleteProduct(String proID) {
		deleteProductDAO.deleteProduct(proID);
		
	}

}
