package businessLogic.getInfoBL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import blService.getInfoBLService.GetBenchmarkBLService;
import businessLogic.statisticsBL.AverageLine;
import dataService.sqlService.SQLUtilityService;
import dto.ShareDTO;
import integration.apiInte.BenchmarkInte;
import sql.AdvancedUtil;
import vo.AverageVO;
import vo.FullShareVO;
import vo.ShareVO;
import vo.SimpilifiedShareVO;

public class Benchmark implements GetBenchmarkBLService {

	@Override
	public ArrayList<ShareVO> getList(String strategy) {
		// 使用默认时区和语�?环境获得�?个日�?
		Calendar cal = Calendar.getInstance();
		// 定义格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 取当前日期的�?5�?
		cal.add(Calendar.DAY_OF_MONTH, -30);
		String startDate = format.format(cal.getTime());
		// 取当前日期的后一�?
		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, +1);
		String endDate = format.format(cal.getTime());
		return getList(strategy, startDate, endDate);
	}

	@Override
	public ArrayList<ShareVO> getList(String strategy, String start, String end) {
		// 结束日期推后�?�?
		Calendar c = Calendar.getInstance();
		String[] sp = end.split("-");
		c.set(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]) - 1, Integer.parseInt(sp[2]));
		c.add(Calendar.DAY_OF_MONTH, +1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		end = format.format(c.getTime());

		BenchmarkInte benchmark = new BenchmarkInte();
		ArrayList<ShareDTO> dtoList = benchmark.getBenchmark(strategy, start, end);
		// 如果没有直接返回
		if (dtoList == null || dtoList.isEmpty()) {
			// System.out.println("333333");
			return null;
		}

		ArrayList<ShareVO> list = new ArrayList<ShareVO>();
		// 将DTO转化成VO
		for (ShareDTO dto : dtoList) {
			ShareVO bench = transDTOToVO(dto);
			list.add(bench);
		}
		return list;
	}

	private ShareVO transDTOToVO(ShareDTO dto) {
		if (dto == null)
			return null;
		ShareVO share = new ShareVO(dto.getID(), dto.getOpen(), dto.getClose(), dto.getDate(), dto.getHigh(),
				dto.getLow(), dto.getVolume(), dto.getTurnover(), dto.getAdj_price(), dto.getPb(), dto.getPe());
		return share;
	}

	public SimpilifiedShareVO getTodaySimple() {
		SQLUtilityService shareInte = new AdvancedUtil();
		ShareDTO dto = shareInte.getLastDay("sh000300");

		double rate = shareInte.getRate("sh000300");
		SimpilifiedShareVO share = new SimpilifiedShareVO(dto.getID(), dto.getName(), dto.getClose(), rate);

		return share;
	}

	public FullShareVO getTodayDetail() {
		SQLUtilityService shareInte = new AdvancedUtil();
		ShareDTO dto = shareInte.getLastDay("sh000300");

		FullShareVO share = new FullShareVO(dto.getID(), dto.getOpen(), dto.getClose(), dto.getDate(), dto.getHigh(),
				dto.getLow(), dto.getVolume(), dto.getTurnover(), dto.getAdj_price(), dto.getPb(), dto.getPe());

		share.setName(dto.getName());

		// PBratio pb = new PBratio();
		// share.setPb(pb.getPB("sh000300"));
		//
		// PEratio pe = new PEratio();
		// share.setPe(pe.getPE("sh000300"));

		double rate = shareInte.getRate("sh000300");
		share.setRate(rate);

		AverageLine average = new AverageLine();
		ArrayList<AverageVO> list = average.getAverageLine(7, dto.getDate(), 10);

		double bias = 0;
		if (list != null && !list.isEmpty()) {
			AverageVO ave = list.get(0);
			bias = (dto.getClose() - ave.getValue()) / ave.getValue() * 100;
		}
		share.setBias(bias);

		return share;
	}

	public String analysis() {
		String conclusion = "";
		FullShareVO shareinfo = getTodayDetail();
		double high = shareinfo.getHigh();
		double low = shareinfo.getLow();
		double close = shareinfo.getClose();
		double open = shareinfo.getOpen();

		if (close > open) {
			conclusion += "阳线说明买方的力量强过卖方，阳线越长，说明多方力量胜过空方越多，后市继续走强的可能�?�就越大�?";
		}
		if (close < open) {
			conclusion += "阴线表示卖方力量强过买方力量，阴线越长，说明空方力量胜过多方越多，后市走弱的可能性就越大�?";
		}

		if (high == close && low == open) {
			conclusion += "市场买方踊跃，涨势未尽；";

		} else if (high == open && low == close) {
			conclusion += "市场强烈跌势，危险；";

		} else if (high > close && low == open && close > open) {// 阳线带上影线
			conclusion += "价格冲高回落，涨势受阻，虽然收盘价仍高于�?盘价，但上方有阻力，可视为弱势；";

		} else if (high == close && low < open && close > open) {// 阳线带下影线
			conclusion += "价格�?度大幅下滑，但受到买盘势力支持，价格又回升向上，收盘在最高价处，属强势形态；";

		} else if (high == open && low < close && close < open) {// 阴线带下影线
			conclusion += "价格�?度大幅下滑后但受到买盘势力支持，价格回升向上�?" + "虽然收盘价仍然低于开盘价，也可视为强势，但在高价区出现时�?" + "说明价格有回调要求，应注意卖出；";

		} else if (high > open && low == close && close < open) {// 上影阴线
			conclusion += "价格冲高受阻，涨势受阻，收盘价低于开盘价，上方有阻力，可视为弱势�?";

		} else if (high > open && low < close && close == open) {// 下十字线
			if (high - close < close - low)
				conclusion += "�?盘后价格大幅下滑，但在低位处获得支撑，下方买盘积极主动，" + "�?终在�?高价附近收盘，属强势，当长下影线出现在低价区时，常常是重要的反转信号�?";
		} else if (high == open && open == close && close == low) {// �?字线
			conclusion += "四价合一K线反映出市场成交清淡，后市难有大的变化；但如果出现在涨停（跌停）处，表明买卖双方力量悬殊太大，后市方向明确，短期难以逆转�?";
		}

		if(conclusion.charAt(conclusion.length()-1)=='�?'){
			conclusion = conclusion.substring(0, conclusion.length()-1);
			conclusion+="�?";
		}
		
		return conclusion;
	}

	public String lineanalyse() {
		String result = "";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String enddate = format.format(cal.getTime());
		ArrayList<ShareVO> list = getList( "open+close+high+low+volume","2015-01-01", enddate);
		ShareVO today = list.get(list.size() - 1);
		ShareVO yesterday = list.get(list.size() - 2);

		if (yesterday.getHigh() > yesterday.getOpen() && yesterday.getLow() < yesterday.getClose()
				&& yesterday.getClose() == yesterday.getOpen()) {
			if (yesterday.getHigh() - yesterday.getClose() > yesterday.getClose() - yesterday.getLow()) {
				if (today.getOpen() > yesterday.getClose() && today.getClose() < today.getOpen()) {
					double a = Math.abs(today.getClose() - yesterday.getClose());
					double b = a / (today.getOpen() - today.getClose());
					if (b < 0.07) {
						result += " 昨日收上十字线，多头上攻受阻；第二天再度高开低走，最终收盘于昨收盘价附近�?" + "表明多空争夺�?烈，上方卖压较重，应密切留意后市，注意出货；";
					}
				}
			}
			if (today.getOpen() < yesterday.getClose() && today.getClose() < today.getOpen()) {
				result += "昨日收十字线，价格有反转迹象；第二天即开盘于昨收盘价之下�?" + "随后价格�?路下滑，�?终以阴线报收，表明空方占据主动，多预示行情转跌，应注意出货；";
			}
		}

		if (yesterday.getClose() > yesterday.getOpen()) {
			double differ = yesterday.getClose() - yesterday.getOpen();
			double drate = differ / yesterday.getOpen();
			if (drate > 0.03 && drate < 0.07) {
				if (today.getOpen() > yesterday.getClose() && today.getClose() < yesterday.getClose()) {
					result += "昨日收中阳线，买方气势正盛；第二天高�?后多方无力顺势上攻，" + "大幅下滑于昨收盘价之下收盘，于是行情走软；高位盘整后出现此形态，应提防庄家拉高出�?";
				}
				if (today.getClose() > yesterday.getClose() && today.getClose() < today.getOpen()) {
					result += "多空交战异常�?烈，多头胜一筹，应密切留意后市变化；";
				}
			}
		}

		if (yesterday.getHigh() > yesterday.getOpen() && yesterday.getLow() < yesterday.getClose()
				&& yesterday.getClose() == yesterday.getOpen()) {
			if (yesterday.getHigh() - yesterday.getClose() < yesterday.getClose() - yesterday.getLow()) {
				double differ = today.getClose() - today.getOpen();
				double drate = differ / today.getOpen();
				if (drate > 0.01 && drate < 0.03) {
					result += " 昨日收下十字线，表明下方买盘积极，价格止跌回稳，" + "第二天开盘后价格持续走高，最终以小阳线报收，于是多头信心增强�?"
							+ "价格回升在即，该组合出现在低价区，是标准的反弹形态；";
				}

			}
		}
		
		double differ = yesterday.getOpen() - yesterday.getClose();
		double drate = differ / yesterday.getOpen();
		if (drate > 0.01 && drate < 0.03) {
			if(today.getOpen() < yesterday.getClose()&& today.getClose() > today.getOpen()
					&&today.getClose() > yesterday.getClose() ){
				result +=" 昨日收中阴线，空头来势凶猛，第二天低�?，但买方在低位积极入市，"
						+ "不跌反涨，最终以阳线报收，并高于昨收盘价，表明空方下攻乏力，价格回升可能性大�?";
			}
			if(today.getOpen() > yesterday.getClose()&& today.getClose() == today.getHigh()){
				result +="昨日收中阴线，空头气盛，但第二天反�?�大幅高�?，价格一路上扬，"
						+ "�?终收盘于�?高价处，表明多方大获全胜，后市可望转强；";
			}
		}
		
		if(result.length()>0)
		if(result.charAt(result.length()-1)=='�?'){
			result = result.substring(0, result.length()-1);
			result+="�?";
		}
		
		return result;
	}
}
