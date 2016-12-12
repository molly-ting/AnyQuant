package businessLogic.filterInfoBL;

public class Check {

	/**
	 * 
	 * @param number 要检查的数值
	 * @return 是数字返回true，不是返回false
	 */
	public boolean checkNumber(String number){
		try{
			Double.parseDouble(number);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
