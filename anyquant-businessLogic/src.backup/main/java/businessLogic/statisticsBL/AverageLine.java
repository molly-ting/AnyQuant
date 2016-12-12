package businessLogic.statisticsBL;

import java.text.DecimalFormat;
import java.util.ArrayList;

import businessLogic.getInfoBL.Benchmark;
import businessLogic.getInfoBL.Share;
import vo.AverageVO;
import vo.ShareVO;

public class AverageLine {

	public ArrayList<AverageVO> getAverageLine(String id, int interval, String end, int donsity) {
		DateTansfer datetransfer = new DateTansfer();
		String t_start = datetransfer.getDate(end, -interval - 300);
		String t_end = datetransfer.getDate(end, 1);

		Share share = new Share();

		ArrayList<ShareVO> list = share.getShareDetail(id, t_start, t_end, "close");
		if (list == null || list.size() < 5) {
			return null;
		}

		return compute(list, interval, donsity);
	}

	public ArrayList<AverageVO> getAverageLine(int interval, String end, int donsity) {
		DateTansfer datetransfer = new DateTansfer();
		String t_start = datetransfer.getDate(end, -interval - 108);
		String t_end = datetransfer.getDate(end, 1);

		Benchmark benchmark = new Benchmark();

		ArrayList<ShareVO> list = benchmark.getList("close", t_start, t_end);
		if (list == null || list.size() < 5) {
			return null;
		}

		return compute(list, interval, donsity);
	}

	private ArrayList<AverageVO> compute(ArrayList<ShareVO> list, int interval, int donsity) {

		ArrayList<AverageVO> average = new ArrayList<AverageVO>();
		int size = list.size();
		int ave = Math.min(donsity, size);

		double value = 0;

		for (int i = 0; i < ave; i++) {
			ShareVO share = list.get(--size);
			value += share.getClose();
		}

		for (int i = interval; i > 0; i--) {
			double temp = value / (double) ave;
			DecimalFormat df = new DecimalFormat("0.00");
			String num = df.format(temp);

			ShareVO lastShare = list.get(size + ave - 1);
			value -= lastShare.getClose();

			AverageVO a = new AverageVO(lastShare.getDate(), Double.parseDouble(num));
			average.add(a);

			if (size > 0) {
				ShareVO firstShare = list.get(--size);
				value += firstShare.getClose();
			}
		}
		if (average.isEmpty())
			return null;
		return average;
	}
}
