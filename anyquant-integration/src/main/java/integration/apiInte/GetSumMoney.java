package integration.apiInte;

import dataService.getShareDataService.GetSumMoneyDataService;
import integration.HttpHelper;

public class GetSumMoney implements GetSumMoneyDataService{

	@Override
	public double getSumMoney() {
		String url="http://hq.sinajs.cn/list=sz399300"; //hs300
		HttpHelper helper=new HttpHelper();
		String result=helper.sendGetSina(url);
		String strarr[] =result.split(",");
		//System.out.println(strarr[9]);
		double ans=Double.parseDouble(strarr[9]);
		return ans;
	}

}
