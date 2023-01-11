package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteProductDAO extends BaseDAO {

	public void deleteProduct(String proID) {
		
		Connection connection = getConnection();
		String sql = "DELETE FROM HANGHOA WHERE maHH = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,proID);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(connection,pstmt,null);
		}
		
	}

}
