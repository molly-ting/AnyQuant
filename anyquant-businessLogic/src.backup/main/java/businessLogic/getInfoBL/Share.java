package businessLogic.getInfoBL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map.Entry;

import blService.getInfoBLService.GetShareBLService;
import businessLogic.statisticsBL.ATRMark;
import businessLogic.statisticsBL.AverageLine;
import businessLogic.statisticsBL.PBratio;
import businessLogic.statisticsBL.PEratio;
import data.getShare.ShareGetter;
import dataService.getShareDataService.GetShareService;
import dataService.sqlService.SQLUtilityService;
import dto.ShareDTO;
import inteService.apiInteService.ApiInteShareService;
import integration.apiInte.ShareInte;
import sql.AdvancedUtil;
import vo.ATRVO;
import vo.AverageVO;
import vo.FullShareVO;
import vo.ShareVO;
import vo.SimpilifiedShareVO;

public class Share implements GetShareBLService {

	/**
	 * 
	 * @param strategy
	 *            应对变更
	 */
	public Share(String strategy) {

	}

	/**
	 * default strategy:open+close
	 */
	public Share() {

	}

	/**
	 * 初始化股票信�?
	 * 
	 * @param strategy
	 * @param exchange
	 * @return �?有股票最近一天的信息列表
	 */
	private ArrayList<ShareVO> getOverViewList(String strategy, String exchange) {
		GetShareService service1 = new ShareGetter("sh");
		GetShareService service2 = new ShareGetter("sz");

		ShareInte shareInte = new ShareInte();

		// 使用默认时区和语�?环境获得�?个日�?
		Calendar cal = Calendar.getInstance();
		// 定义格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 取当前日期的�?5�?
		cal.add(Calendar.DAY_OF_MONTH, -3);
		String startDate = format.format(cal.getTime());
		// 取当前日期的后一�?
		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, +1);
		String endDate = format.format(cal.getTime());

		ArrayList<ShareVO> list = new ArrayList<ShareVO>();
		String[] idList = { "sz002024", "sh601398", "sh601166", "sh601857", "sz002458", "sz002256", "sz002192",
				"sz300319", "sz002218", "sh600005" };

		for (int i = 0; i < idList.length; i++) {
			ArrayList<ShareDTO> shareInfo = shareInte.getShareDetail(idList[i], startDate, endDate, strategy);

			if (shareInfo != null && !shareInfo.isEmpty()) {
				ShareDTO temp = shareInfo.get(shareInfo.size() - 1);
				ShareVO share = transDTOToVO(temp);
				String name = service1.getShareName(idList[i]);
				if (name == null) {
					name = service2.getShareName(idList[i]);
				}
				share.setName(name);
				list.add(share);
			}
		}
		if (list.isEmpty())
			return null;
		else
			return list;
	}

	@Override
	public String getShareName(String id) {
		if (id == null || id.length() < 2)
			return "";
		String exchange = id.substring(0, 2);
		if (exchange.equals("sh") || exchange.equals("sz")) {
			GetShareService share = new ShareGetter(exchange);
			String name = share.getShareName(id);
			if (name == null)
				return "";
			else
				return name;
		} else {
			return "";
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ListIterator<String> getNameList(String input) {
		String exchange = "";

		// 确认交易�?
		if (input.startsWith("sh"))
			exchange = "sh";
		else if (input.startsWith("sz"))
			exchange = "sz";
		else if (input.startsWith("s"))
			exchange = "sh";

		// 获得股票列表
		Iterator map;
		GetShareService service = new ShareGetter(exchange);
		if (exchange.equals("sz")) {
			map = service.getSZShare();
		} else {
			map = service.getSHShare();
		}

		ArrayList<String> list = new ArrayList<String>();
		ListIterator<String> nameList = list.listIterator();

		if (map == null)
			return nameList;
		while (map.hasNext()) {
			Entry entry = (Entry) map.next();
			String id = (String) entry.getKey();
			id = exchange + id;
			if (id.startsWith(input))
				nameList.add(id);
		}
		while (nameList.hasPrevious()) {
			nameList.previous();
		}
		return nameList;
	}

	@Override
	public ArrayList<ShareVO> getInfoList(String exchange) {
		return getOverViewList(null, null);
	}

	@Override
	public ArrayList<ShareVO> getShareDetail(String id, String start, String end, String strategy) {
		// 结束日期推后�?�?
		Calendar c = Calendar.getInstance();
		String[] sp = end.split("-");
		c.set(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]) - 1, Integer.parseInt(sp[2]));
		c.add(Calendar.DAY_OF_MONTH, +1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		end = format.format(c.getTime());

		ApiInteShareService shareInte = new ShareInte();
		ArrayList<ShareDTO> dtoList = shareInte.getShareDetail(id, start, end, strategy);
		return common(dtoList);
	}

	@Override
	public ArrayList<ShareVO> getSpecifiedInfo(String id, String strategy) {
		ApiInteShareService shareInte = new ShareInte();
		ArrayList<ShareDTO> dtoList = shareInte.getShareDetail(id, strategy);
		return common(dtoList);
	}

	public ArrayList<ShareVO> common(ArrayList<ShareDTO> dtoList) {
		if (dtoList == null)
			return null;

		ArrayList<ShareVO> list = new ArrayList<ShareVO>();
		for (ShareDTO dto : dtoList) {
			ShareVO share = transDTOToVO(dto);
			list.add(share);
		}

		// 记录到历史纪�?
		// recordHistory(id);
		if (list.isEmpty())
			return null;
		else
			return list;
	}

	@Override
	public void recordHistory(String name) {

	}

	private ShareVO transDTOToVO(ShareDTO dto) {
		if (dto == null)
			return null;
		ShareVO share = new ShareVO(dto.getID(), dto.getOpen(), dto.getClose(), dto.getDate(), dto.getHigh(),
				dto.getLow(), dto.getVolume(), dto.getTurnover(), dto.getAdj_price(), dto.getPb(), dto.getPe());
		share.setName(dto.getName());
		return share;
	}

	@Override
	public ArrayList<SimpilifiedShareVO> getAllShareToday() {
		SQLUtilityService shareInte = new AdvancedUtil();
		ArrayList<ShareDTO> dtoList = shareInte.getAllShareInOneDay();
		ArrayList<SimpilifiedShareVO> list = new ArrayList<SimpilifiedShareVO>();
		double rate = 0;
		for (ShareDTO dto : dtoList) {
			rate = shareInte.getRate(dto.getID());
			SimpilifiedShareVO share = new SimpilifiedShareVO(dto.getID(), dto.getName(), dto.getClose(), rate);
			share.setName(dto.getName());
			list.add(share);
		}

		return list;
	}

	public FullShareVO getOneShareToday(String id) {
		SQLUtilityService shareInte = new AdvancedUtil();
		ShareDTO dto = shareInte.getLastDay(id);

		FullShareVO share = new FullShareVO(dto.getID(), dto.getOpen(), dto.getClose(), dto.getDate(), dto.getHigh(),
				dto.getLow(), dto.getVolume(), dto.getTurnover(), dto.getAdj_price(), dto.getPb(), dto.getPe());

		share.setName(dto.getName());

		PBratio pb = new PBratio();
		share.setPb(pb.getPB(id));

		PEratio pe = new PEratio();
		share.setPe(pe.getPE(id));

		share.setRate(shareInte.getRate(dto.getID()));
		share.setSum(dto.getClose() * dto.getVolume());

		ATRMark atr = new ATRMark();
		ArrayList<ATRVO> list = atr.getATRMark(7, dto.getDate());
		if (list != null && !list.isEmpty())
			share.setBias(list.get(0).getValue());

		return share;
	}

	@Override
	public ArrayList<SimpilifiedShareVO> find(String text) {
		String benchmark = "sh000300";
		SQLUtilityService shareInte = new AdvancedUtil();
		ArrayList<SimpilifiedShareVO> list = new ArrayList<SimpilifiedShareVO>();

		if (benchmark.contains(text)) {
			ShareDTO dto = shareInte.getLastDay(benchmark);
			double rate = shareInte.getRate(benchmark);
			SimpilifiedShareVO share = new SimpilifiedShareVO(dto.getID(), dto.getName(), dto.getClose(), rate);
			list.add(share);
		}

		ArrayList<ShareDTO> dtoList = shareInte.find(text);

		if (dtoList != null && !dtoList.isEmpty()) {
			double rate = 0;
			for (ShareDTO dto : dtoList) {
				rate = shareInte.getRate(dto.getID());
				SimpilifiedShareVO share = new SimpilifiedShareVO(dto.getID(), dto.getName(), dto.getClose(), rate);
				share.setName(dto.getName());
				list.add(share);
			}
		}

		if (!list.isEmpty())
			return list;
		return null;
	}

	public String analysis(String id) {
		String conclusion = "从今日指标看�?";
		FullShareVO shareinfo = getOneShareToday(id);
		double pe = shareinfo.getPe();
		if (pe < 0)
			conclusion += "该公司亏�?;";
		else if (pe >= 0 && pe < 14)
			conclusion += "股票价�?�被低估;";
		else if (pe >= 14 && pe < 21)
			conclusion += "股票价�?�正�?;";
		else if (pe >= 21 && pe < 28)
			conclusion += "股票价�?�被高估;";
		else if (pe >= 28)
			conclusion += "股市出现投机性泡�?;";

		double pb = shareinfo.getPb();
		if (pb <= 6) {
			conclusion += "市净率较低，股票有潜�?;";
		} else if (pb > 6 && pb < 12) {
			conclusion += "市净率正常，建议少量买入或观�?;";
		} else if (pb > 12) {
			conclusion += "市净率较高，建议离场;";
		}

		double high = shareinfo.getHigh();
		double low = shareinfo.getLow();
		double close = shareinfo.getClose();
		double open = shareinfo.getOpen();

		double atr = shareinfo.getBias();
		if (atr > close) {
			conclusion += "均线位于价格之上，看跌；";
		} else {
			conclusion += "均线位于价格之下，看涨；";
		}

		if (high == close && low == open) {// 光头光脚阳线
			conclusion += "极端强势上涨，后市看多；";
		} else if (high == open && low == close) {// 光头光脚阴线
			conclusion += "极端强势下跌，后市看空；";
		} else if (high > close && low < open && close > open) {// 大阳�?
			conclusion += "强势上涨，后市看多；";
		} else if (high > open && low < close && close < open) {// 大阴�?
			conclusion += "强势下跌，后市看空；";
		}

		else if (high == close && low < open && close > open) {// 光头阳线
			// if((open-low)<(close-open))
			conclusion += "较强势上涨，空方�?始反击了，需要注意；";
			// else
			// conclusion+="曾遇到过剧烈反击，后市有变；";
		} else if (high == open && low < close && close < open) {// 光头阴线
			// if((close-low)<(open-close))
			conclusion += "较强势下跌，多方�?始反击了，需要注意；";
			// else
			// conclusion+="曾遇到过剧烈反击，后市有变；";
		} else if (high > close && low == open && close > open) {// 光脚阳线
			// if(high-close<close-open)
			conclusion += "较强势上涨，遇到空方反击了，�?要注意；";
			// else
			// conclusion+="相比过去，摸高受阻，后市有变�?";
		} else if (high > open && low == close && close < open) {// 光脚阴线
			// if(high-open<open-close)
			conclusion += "较强势下跌，遇到多方反击了，�?要注意；";
			// else
			// conclusion+="相比过去，曾经大涨，后市有变�?";
		}
		if (low == close) {
			conclusion += "该股在尾市受到的打压很大，下�?交易日可能会进一步下探，" + "对于已经有一定获利积累的短线操盘者应该尽快出�?�?";
		} else if (high == close) {
			conclusion += "当天股票�?路上涨，�?终收于最高价，表明该股处于盘中推动上涨的形势�?" + "下一交易日很可能继续向上�?爬，进�?�出现更高的价格�?"
					+ "对于短线操盘者来说，在第二天早盘出现高价时，可以获利出局�?";
		}
		if (conclusion.charAt(conclusion.length() - 1) == '�?') {
			conclusion = conclusion.substring(0, conclusion.length() - 1);
			conclusion += "�?";
		}
		// conclusion += lineanalyse(id);
		return conclusion;
	}

	public String lineanalyse(String id) {
		String result = "";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String enddate = format.format(cal.getTime());
		ArrayList<ShareVO> list = getShareDetail(id, "2015-01-01", enddate, "open+close+high+low+volume");
		ShareVO today = list.get(list.size() - 1);
		ShareVO yesterday = list.get(list.size() - 2);
		// System.out.println(yesterday.getOpen());
		// System.out.println(yesterday.getClose());
		// System.out.println(yesterday.getHigh());
		// System.out.println(yesterday.getLow());
		// System.out.println(today.getOpen());
		// System.out.println(today.getClose());
		// System.out.println(today.getHigh());
		// System.out.println(today.getLow());
		if (yesterday.getOpen() < yesterday.getClose()) {
			if (yesterday.getHigh() < today.getOpen()) {
				result += "�?盘价格高于昨日最高价，表明买方的实力很强，若此时股价已经经历了一个缓慢的上升过程，可以大胆介入；";
			}
			if (yesterday.getHigh() >= today.getOpen() && yesterday.getClose() <= today.getOpen()) {
				result += "�?盘价在昨日收盘价和最高价之间，买家的实力有所减弱�?" + "应观察卖家的实力再做下一步的策略�?";
			}
			if (yesterday.getClose() >= today.getOpen() && yesterday.getOpen() <= today.getOpen()) {
				result += "�?盘价低开，但位于前一交易日的实体K线之中，表示买家的实力已经比较弱了，" + "卖方随时可能反击，此时要密切关注股市的动向，�?旦有任何风吹草动就可能出现变盘；";
			}
			if (yesterday.getLow() > today.getOpen()) {
				result += "�?盘价位于昨日�?低价以下，表明卖家的实力逐渐加强，并�?始反攻，" + "同时买家的实力表现得很弱小，此时应转向看空，并继续观望；";
			}
		}

		if (yesterday.getOpen() > yesterday.getClose()) {
			if (yesterday.getHigh() < today.getOpen()) {
				result += "�?盘价高于昨日的最高价，说明股市是在特别条件下发生了重大的利好�?" + "而且在此利好的刺�?下，后势增长还有很大的空间；";
			}
			if (yesterday.getHigh() >= today.getOpen() && yesterday.getClose() <= today.getOpen()) {
				result += "�?盘价在昨日收盘价和最高价之间�?" + "这时卖方抛压的大小决定着股价下调过程中的后市操作过程�?" + "如果抛压太重，则调整期将会比较漫长，之后走势还有待观察；";
			}
			if (yesterday.getClose() <= today.getOpen() && yesterday.getOpen() >= today.getOpen()) {
				result += "�?盘价位于昨日K线实体中，说明买卖双方的实力依然未变�?";
			}
			if (yesterday.getClose() >= today.getClose() && yesterday.getLow() <= today.getClose()) {
				result += "收盘价在昨日收盘与最低价之间，表明卖方实力强劲，而买方实力很弱，" + "市场继续走低的可能�?�非常大�?";
			}
			if (yesterday.getLow() > today.getOpen()) {
				result += "�?盘价低于昨日的最低价，表明这个市场空方已经主宰了市场的行情，股市将加速下跌；";
			}
		}

		String s = "";

		boolean above = true;
		boolean state2 = true;
		boolean state3 = true;
		boolean increase = true;
		boolean rise = true;

		AverageLine ave = new AverageLine();
		ArrayList<AverageVO> average = ave.getAverageLine(id, 7, enddate, 5);
		ArrayList<AverageVO> average10 = ave.getAverageLine(id, 7, enddate, 10);
		for (int i = average.size() - 1; i >= 0; i--) {
			// System.out.println(average.get(i).getDate());
			// System.out.println(list.get(list.size()-i-1).getDate());
			// if (average.get(i).getDate().equals(list.get(i).getDate())) {
			if (list.get(list.size() - i - 1).getClose() < average.get(i).getValue()) {
				state2 = false;
				if (above) {
					above = false;
					s += list.get(list.size() - i - 1).getDate() + "股价跌破移动平均线，是卖出信号；";
				}

				if (state3) {
					if (i > 0)
						if (list.get(list.size() - i - 1).getClose() > list.get(list.size() - i).getClose()) {
							state3 = false;
						}
				}

			} else {
				state3 = false;
				if (state2) {
					if (!increase) {
						if (i > 0)
							if (list.get(list.size() - i - 1).getClose() < list.get(list.size() - i).getClose()) {
								s += list.get(list.size() - i - 1).getDate() + "股价位于移动平均线之上运行，回档时未跌破移动平均线后又再度上升时为买进时机；";
								increase = true;
							}
					}
					if (i > 0)
						if (list.get(list.size() - i - 1).getClose() > list.get(list.size() - i).getClose()) {
							increase = false;
						}
				}
				if (i > 0)
					if (list.get(list.size() - i - 1).getClose() > list.get(list.size() - i).getClose()) {
						rise = false;
					}
				if (!above) {
					above = true;
					s += list.get(list.size() - i - 1).getDate() + "股价突破移动平均线，是买入信号；";
				}

			}

			// }

		}
		if (state3) {
			s += "股价位于移动平均线以下运行，突然暴跌，距离移动平均线太远�?" + "极有可能向移动平均线靠近（物极必反，下跌反弹），此时为买进时机；";
		}
		if (state2 && rise) {
			s += "股价位于移动平均线之上运行，连续数日大涨，离移动平均线愈来愈远，" + "说明近期内购买股票�?�获利丰厚，随时都会产生获利回吐的卖压，应暂时卖出持股；";
		}

		for (int i = average.size() - 1; i >= 0; i--) {
			if (average.get(i).getValue() == average10.get(i).getValue()) {
				if (i > 0 && i < average.size() - 1) {
					if ((average.get(i + 1).getValue() < average10.get(i + 1).getValue())
							&& (average.get(i - 1).getValue() > average10.get(i - 1).getValue())) {
						s += average.get(i).getDate() + "黄金交叉，后市上涨的可能性很大，是买入时机；";
					}
					if ((average.get(i + 1).getValue() > average10.get(i + 1).getValue())
							&& (average.get(i - 1).getValue() < average10.get(i - 1).getValue())) {
						s += average.get(i).getDate() + "死亡交叉，预示股价将下跌，此时应卖出持有的股票，离场观望�?";
					}
				}
			}
		}
		result += s;
		if (result.charAt(result.length() - 1) == '�?') {
			result = result.substring(0, result.length() - 1);
			result += "�?";
		}
		return result;
	}
}
