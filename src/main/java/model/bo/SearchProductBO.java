package model.bo;

import java.util.ArrayList;

import model.bean.HangHoa;
import model.dao.SearchProductDAO;

public class SearchProductBO {
	SearchProductDAO searchProductDAO = new SearchProductDAO();
	public ArrayList<HangHoa> getDsHangHoa(String searchText) {
		return searchProductDAO.getDsHangHoa(searchText);
	}
	public int getTotalPageNumber(String searchText) {
		return searchProductDAO.getTotalPageNumber(searchText) ;
	}
	public ArrayList<HangHoa> getDsHangHoa(String searchText, int pageNumber) {
		
		return searchProductDAO.getDsHangHoa(searchText,pageNumber);
	}

}
