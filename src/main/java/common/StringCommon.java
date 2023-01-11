package common;

import java.text.DecimalFormat;

public class StringCommon {
	//digit là tham số chứa số lượng đầu ra mong đơi
	public static String convertNumberToString	(long number	, int digit) {
		number = number + 10000000000000000L; // 16 chữ số 0
        String returnedStr = String.valueOf(number); // Có chiều dài là 17 chữ số
        return returnedStr.substring(returnedStr.length() - digit);
	}
	public static String convertDoubleToString(double d) {
		return new DecimalFormat("#").format(d);
	}
	 public static String convertDoubleToStringWithComma(double d) {
         return new DecimalFormat("###,###").format(d);
	 }
}
