package configProperties;

import java.util.Date;

public class Utilities {
	
	public static int impliciteTime = 10;
	public static int pageLoadTime = 100;
	public static String pathForTestDataProperties = System.getProperty("user.dir")+ "\\src\\main\\java\\configProperties\\TestData_Properties";
	
	
	public static String dateForEmail() {
		
		Date date = new Date();
		String time = date.toString().replace(" ", "").substring(9).replace(":", "");
		return "Abdu"+time+"@gmail.com";

}
}
