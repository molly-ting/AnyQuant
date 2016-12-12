package integration;

import java.util.ArrayList;

import dto.ShareDTO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONTranslation {

	public ArrayList<ShareDTO> getShareList(JSONArray ja, String id, String strategy) {
		try {
			ArrayList<ShareDTO> list = new ArrayList<ShareDTO>();
			for (int i = 0; i < ja.size(); i++) {
				ShareDTO share = analysis(ja.getJSONObject(i),id,strategy);
				list.add(share);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private ShareDTO analysis(JSONObject jo,String id, String strategy) {
		ShareDTO share = new ShareDTO(id);
		int len = 0;

		String[] item;
		//解析strategy
		if (strategy.contains("+")) {
			item = strategy.split("[+]");
			len = item.length;
		} else {
			item = new String[1];
			item[0] = strategy;
			len = 1;
		}
		//获得时间，不在strategy中
		share.setDate(jo.getString("date"));
		//获得strategy指定的数据
		for (int i = 0; i < len; i++) {
			String s = item[i];
			switch (s) {
			case "open":
				share.setOpen(jo.getDouble(s));
				break;
			case "close":
				share.setClose(jo.getDouble(s));
				break;
			case "high":
				share.setHigh(jo.getDouble(s));
				break;
			case "low":
				share.setLow(jo.getDouble(s));
				break;
			case "volume":
				share.setVolume(jo.getDouble(s));
				break;
			case "turnover":
				share.setTurnover(jo.getDouble(s));
				break;
			case "adj_price":
				share.setAdj_price(jo.getDouble(s));
				break;
			case "pb":
				share.setPb(jo.getDouble(s));
				break;
			case "pe":
				share.setPe(jo.getDouble(s));
				break;
			default:
				break;
			}
		}
		return share;
	}
}
