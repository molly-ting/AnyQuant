package integration.apiInte;

import java.util.ArrayList;

import dto.ShareDTO;
import integration.HttpHelper;
import integration.JSONTranslation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Common {

	/**
	 * 
	 * @param id
	 *            股票或大盘代码
	 * @param strategy
	 *            要获得的内容
	 * @param start
	 *            开始日期
	 * @param end
	 *            结束日期
	 * @param url
	 *            股票或大盘的url
	 * @return
	 */
	public ArrayList<ShareDTO> getList(String id, String start, String end, String url) {
		HttpHelper httpHeler = new HttpHelper();
		String startDate = "";
		String endDate = "";
		String strategy = "open+close+low+high+volume";

		// 如果规定日期，则在请求中添加日期
		if (start != null)
			startDate = "start=" + start + "&";
		if (end != null)
			endDate = "end=" + end + "&";
		// 得到请求描述
		String param = startDate + endDate + "fields=" + strategy;

		try {
			// 传入url和请求
			String result = httpHeler.sendGet(url, param);
			// 如果没有数据，直接跳出
			if (result == null || result.isEmpty())
				return null;
			// 解析json数据
			JSONObject jo = JSONObject.fromObject(result);
			JSONObject joData = jo.getJSONObject("data");
			JSONArray ja = joData.getJSONArray("trading_info");
			// 处理json数据
			JSONTranslation translation = new JSONTranslation();
			return translation.getShareList(ja, id, strategy);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
