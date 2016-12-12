package startegy;

import java.util.ArrayList;

import blSerivce.predictSerivce.StrategyService;
import blService.getInfoBLService.GetShareBLService;
import businessLogic.getInfoBL.Share;
import businessLogic.sortBL.Sort;
import businessLogic.statisticsBL.PBratio;
import businessLogic.statisticsBL.PEratio;
import dataService.sqlService.SQLUtilityService;
import dto.ShareDTO;
import sql.AdvancedUtil;
import sql.IntejiTool;
import vo.DonutVO;
import vo.FullShareVO;
import vo.ShareScoreVO;
import vo.ShareVO;
import vo.StrategyVO;

public class Strategy implements StrategyService {

	private int length = 10;
	private int days = 20;
	private int days2 = 5;
	private int num_of_days1 = 60;
	private int num_of_days2 = 200;

	/**
	 * 
	 * @return 回报率+风险+适宜度+价值+潜力
	 */
	public ArrayList<DonutVO> getSingleAspect() {
		ArrayList<ShareVO> shareList = getShareList();
		String[] type = { "high", "pb", "close", "pe", "open" };

		ArrayList<DonutVO> donutList = new ArrayList<DonutVO>();

		for (int i = 0; i < 5; i++) {
			int[] arr = { 0, 0, 0, 0, 0 };

			String id = "";
			String name = "";
			int max = 0;
			for (int j = 0; j < shareList.size(); j++) {
				int num = 0;
				ShareVO share = shareList.get(j);
				switch (type[i]) {
				case "high":
					num = (int) share.getHigh();
					break;
				case "pb":
					num = (int) share.getPb();
					break;
				case "close":
					num = (int) share.getClose();
					break;
				case "pe":
					num = (int) share.getPe();
					break;
				case "open":
					num = (int) share.getOpen();
					break;
				}
				if (max < num) {
					id = share.getID();
					name = share.getName();
					max = num;
				}
				if (num >= 16)
					arr[0]++;
				else if (num >= 12 && num < 16)
					arr[1]++;
				else if (num >= 8 && num < 12)
					arr[2]++;
				else if (num >= 4 && num < 8)
					arr[3]++;
				else
					arr[4]++;
			}

			DonutVO donut = new DonutVO(id, name, max, arr);
			donutList.add(donut);
		}
		return donutList;
	}

	public ArrayList<ShareScoreVO> getStartegy() {
		ArrayList<ShareVO> shareList = getShareList();
		Sort sort = new Sort();
		StrategyVO strategy = new StrategyVO("volume");
		sort.sort(strategy, shareList);

		ArrayList<ShareScoreVO> scoreList = new ArrayList<ShareScoreVO>();
		for (int i = 0; i < 15; i++) {
			ShareVO share = shareList.get(i);
			ShareScoreVO score = new ShareScoreVO(share.getID(), share.getName(), (int) share.getVolume(),
					(int) share.getHigh(), (int) share.getPb(), (int) share.getClose(), (int) share.getOpen(),
					(int) share.getPe());
			scoreList.add(score);
		}
		return scoreList;
	}

	public ShareScoreVO getStrategy(String id) {
		IntejiTool inteji = new IntejiTool();
		double[] x = inteji.getData(id, num_of_days2);
		double[] y = inteji.getData(id, num_of_days1);

		GetShareBLService shareService = new Share();

		FullShareVO shareVo = shareService.getOneShareToday(id);

		int k = getK(x);
		int pbValue = getPb(shareVo.getPb());
		int peValue = getPe(shareVo.getPe());
		int cost = getCost(y);
		int stable = getStable(x);
		int score = k + pbValue + peValue + cost + stable;

		return new ShareScoreVO(id, shareVo.getName(), score, stable, pbValue, cost, k, peValue);
	}

	private ArrayList<ShareVO> getShareList() {
		SQLUtilityService advancedUtil = new AdvancedUtil();
		ArrayList<ShareDTO> list = advancedUtil.getAllShareInOneDay();
		ArrayList<ShareVO> shareList = new ArrayList<ShareVO>();

		IntejiTool inteji = new IntejiTool();
		for (ShareDTO share : list) {
			ShareVO vo = new ShareVO();
			double[] x = inteji.getData(share.getID(), num_of_days2);
			double[] y = inteji.getData(share.getID(), num_of_days1);

			PEratio pe = new PEratio();
			double value = pe.getPE(share.getID());

			PBratio pb = new PBratio();
			double val = pb.getPB(share.getID());

			int k = getK(x);
			int pbValue = getPb(val);
			int peValue = getPe(value);
			int cost = getCost(y);
			int stable = getStable(x);
			int score = k + pbValue + peValue + cost + stable;

			vo.setId(share.getID());
			vo.setName(share.getName());
			vo.setVolume(score);
			vo.setPb(pbValue);
			vo.setPe(peValue);
			vo.setClose(cost);
			vo.setHigh(stable);
			vo.setOpen(k);
			shareList.add(vo);
		}
		return shareList;
	}

	/**
	 * 
	 * @param x
	 * @return 回报率
	 */
	private int getStable(double[] x) {
		double ave = 0;
		for (int i = 0; i < days; i++) {
			ave += x[i];
		}
		ave /= days;
		double bias = (x[0] - ave) / ave * 100;
		if (bias >= 10)
			return 20;
		else if (bias <= -10)
			return 0;
		else
			return (int) (bias + 10);
	}

	/**
	 * 
	 * @param pb
	 * @return 投资风险
	 */
	private int getPb(double pb) {
		if (pb < -1000)
			return 5;
		else if (pb < 0 || pb > 20)
			return 0;
		else
			return (int) (-pb + 20);
	}

	/**
	 * 
	 * @return 买入适宜度
	 */
	private int getCost(double[] x) {
		double ave = 0;
		for (int i = 0; i < days2; i++) {
			ave += x[i];
		}
		ave /= days2;
		double max = 0;
		double min = Double.MAX_VALUE;

		for (int i = 0; i < x.length; i++) {
			if (max < x[i])
				max = x[i];
			if (min > x[i])
				min = x[i];
		}

		return (int) (-20.0 / (max - min) * ave + (20 * max) / (max - min));
	}

	/**
	 * 
	 * @param pe
	 * @return 投资价值
	 */
	private int getPe(double pe) {
		if (pe < -1000)
			return 5;
		else if (pe <= 0)
			return 0;
		else if (pe > 0 && pe < 80)
			return (int) (0.25 * pe);
		else if (pe >= 80 && pe < 120)
			return (int) (-0.25 * pe + 40);
		else
			return 0;
	}

	/**
	 * 
	 * @param first
	 * @param last
	 * @return 增值潜力
	 */
	private int getK(double[] x) {
		double[] first = new double[10];
		for (int i = 0; i < length; i++) {
			first[i] = 0;
			for (int j = 0; j < days; j++) {
				first[i] += x[i + j];
			}
			first[i] /= days;
		}
		double[] last = new double[10];
		int len = x.length - 1;
		for (int i = 9; i >= 0; i--) {
			last[i] = 0;
			for (int j = 0; j < days; j++) {
				last[i] += x[len - i - j];
			}
			last[i] /= days;
		}

		double a1 = 0;
		double a2 = 0;
		for (int i = 0; i < first.length; i++) {
			a1 += first[i];
			a2 += last[i];
		}
		double a3 = a1 - a2;
		if (a3 > 30)
			return 20;
		else if (a3 < -60)
			return 0;
		else
			return (int) (2.0 / 9.0 * a3 + 40.0 / 3.0);

	}
}
