package integration.apiInte;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import dto.ShareDTO;
import inteService.apiInteService.ApiInteShareService;
import sql.DBUtility;

public class ShareInte implements ApiInteShareService {

	private static HashMap<String, String> urlMapsh = new HashMap<String, String>();
	private static HashMap<String, String> urlMapsz = new HashMap<String, String>();

	@Override
	public Iterator getShareList(String exchange) {
		if (exchange.equals("sh"))
			return urlMapsh.entrySet().iterator();
		else
			return urlMapsz.entrySet().iterator();
	}

	@Override
	public ArrayList<ShareDTO> getShareDetail(String id, String strategy) {
		Calendar cal = Calendar.getInstance();
		// 定义格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 取当前日期的前5天
		cal.add(Calendar.DAY_OF_MONTH, -30);
		String startDate = format.format(cal.getTime());
		// 取当前日期的后一天
		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, +1);
		String endDate = format.format(cal.getTime());
		return getShareDetail(id, startDate, endDate, strategy);
	}

	@Override
	public ArrayList<ShareDTO> getShareDetail(String id, String start, String end, String strategy) {
		DBUtility dbUtility = new DBUtility();
		return dbUtility.getData(id, start, end);
	}
	
	public ArrayList<ShareDTO> getShareDetail(String id, String start, String end) {
		String url = "http://121.41.106.89:8010/api/stock/";
		url += id;
		url += "/";

		Common common = new Common();
		return common.getList(id, start, end, url);
	}
}
