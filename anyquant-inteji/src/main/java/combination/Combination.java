package combination;

import java.util.ArrayList;

import blSerivce.predictSerivce.CombinationService;
import blService.getInfoBLService.GetShareBLService;
import businessLogic.getInfoBL.Share;
import sql.IntejiTool;
import vo.FullShareVO;
import vo.SimpilifiedShareVO;

public class Combination implements CombinationService {
	private int days = 400;
	private int shortTerm = 7;
	private int middleTerm = 150;
	private int longTerm = 350;

	public ArrayList<SimpilifiedShareVO> selectShare(double[] num) {
		GetShareBLService service = new Share();
		ArrayList<SimpilifiedShareVO> list = service.getAllShareToday();

		ArrayList<SimpilifiedShareVO> selectList = new ArrayList<SimpilifiedShareVO>();

		IntejiTool inteji = new IntejiTool();
		for (SimpilifiedShareVO share : list) {
			double[] x = inteji.getData(share.getId(), days);

			// 上涨天数符合
			if (getRaiseDays(x, (int) num[1])) {
				// 短期符合
				if (getShortTerm(x, num[7]) >= num[4]) {
					// 中期符合
					if (getLongTerm(x, middleTerm, x[7]) >= num[5]) {
						// 长期符合
						if (getLongTerm(x, longTerm, x[7]) >= num[6]) {
							FullShareVO fullShare = service.getOneShareToday(share.getId());
							// 成交量符合
							if (getVolume(fullShare.getVolume(), num[2], num[3]))
								selectList.add(share);
						}
					}
				}
			}
		}
		return selectList;
	}

	/**
	 * 
	 * @param x
	 * @return 短期
	 */
	private double getShortTerm(double[] x, double lambda) {
		double ave = 0;
		for (int i = 0; i < shortTerm; i++) {
			ave += x[i] - x[i + 1];
		}
		ave /= shortTerm;

		double std = 0;
		for (int i = 0; i < shortTerm; i++) {
			std += (x[i] - x[i + 1] - ave) * (x[i] - x[i + 1] - ave);
		}
		std /= shortTerm;
		std = Math.sqrt(std);

		return ave - (1 - lambda) * std;
	}

	/**
	 * 
	 * @param x
	 * @param day
	 * @return 中长期
	 */
	private double getLongTerm(double[] x, int day, double lambda) {
		double first = 0;
		for (int i = 0; i < shortTerm; i++) {
			first += x[i];
		}
		first /= shortTerm;

		double std = 0;
		for (int i = 0; i < shortTerm; i++) {
			std += (x[i] - first) * (x[i] - first);
		}
		std /= shortTerm;
		std = Math.sqrt(std);

		double last = 0;
		for (int i = day - 1; i >= (day - shortTerm); i--) {
			last += x[i];
		}
		last /= shortTerm;

		double std1 = 0;
		for (int i = day - 1; i >= (day - shortTerm); i--) {
			std1 += (x[i] - last) * (x[i] - last);
		}
		std1 /= shortTerm;
		std1 = Math.sqrt(std1);
		// 平均数-lambda*标准差
		return first - last - lambda * (std - std1);
	}

	/**
	 * 
	 * @param x
	 * @param days
	 * @return 上涨天数
	 */
	private boolean getRaiseDays(double[] x, int days) {
		boolean raise = true;
		for (int i = 0; i < days; i++) {
			if ((x[i] - x[i + 1]) < 0) {
				raise = false;
				break;
			}
		}

		return raise;
	}

	private boolean getVolume(double volume, double low, double high) {
		if (volume >= low && volume <= high)
			return true;
		else
			return false;
	}
}
