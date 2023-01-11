package model.bo;

import model.dao.CreateProductDAO;
import common.StringCommon;
import common.ValidateCommon;
public class CreateProductBO {
	CreateProductDAO createProductDAO = new CreateProductDAO();
	
	 public String insertProduct( String tenHH, String dvt, String donGiaTK,String ghiChu) {
		 
		 String returnedString = null;
		 String tempValidate = ValidateCommon.validateProduct(tenHH, dvt, donGiaTK, ghiChu);

         if (!"No error".equals(tempValidate)) {
                 return tempValidate;
         }
		 for (int i = 1; i <= 10; i++) { // Lặp tối đa 10 lần:
             // Lấy mã hàng hóa mới nhất trong DB
             String lastestMaHH = createProductDAO.getLastestMaHH();
             // Tách HH và số thứ tự ra riêng
             // Tăng số thứ tự lên 1
             // Gộp số thứ tự mới với HH
             if (lastestMaHH == null) {
                     lastestMaHH = "HH001";
             } else {
            	 long orderNumber = Long.valueOf(lastestMaHH.substring(2, 5));

                 orderNumber++;

                 lastestMaHH = "HH" + StringCommon.convertNumberToString(orderNumber, 3);
             }
             // Truyền mã hàng hóa mới vào trong createProductDAO.insertProduct
             String returnedMessage = createProductDAO.insertProduct(lastestMaHH, tenHH, dvt, donGiaTK, ghiChu);           
             if ("Duplicate ID Error".equals(returnedMessage)) {
                 returnedString = "Duplicate ID Error";
                     continue;
             } else if ("No error".equals(returnedMessage)){
            	  returnedString = "No error";
                     break;
             }     
     }
     return returnedString;
}
	
	
}
