package businessLogic.statisticsBL;

import java.text.DecimalFormat;
import java.util.ArrayList;

import businessLogic.getInfoBL.Benchmark;
import businessLogic.getInfoBL.Share;
import vo.ATRVO;
import vo.ShareVO;

public class ATRMark {

	public ArrayList<ATRVO> getATRMark(String id, int interval, String end) {
		DateTansfer datetransfer = new DateTansfer();
		String t_start = datetransfer.getDate(end, -interval - 108);
		String t_end = datetransfer.getDate(end, -1);

		Share share = new Share();

		ArrayList<ShareVO> list = share.getShareDetail(id, t_start, t_end, "open+low+high+close");
		if (list == null || list.size() < 14) {
			return null;
		}

		return compute(list, interval);
	}

	public ArrayList<ATRVO> getATRMark(int interval, String end) {
		DateTansfer datetransfer = new DateTansfer();
		String t_start = datetransfer.getDate(end, -interval - 108);
		String t_end = datetransfer.getDate(end, -1);

		Benchmark benchmark = new Benchmark();

		ArrayList<ShareVO> list = benchmark.getList("open+low+high+close", t_start, t_end);
		if (list == null || list.size() < 14) {
			return null;
		}

		return compute(list, interval);
	}

	private ArrayList<ATRVO> compute(ArrayList<ShareVO> list, int interval) {

		ArrayList<ATRVO> atrList = new ArrayList<ATRVO>();
		int size = list.size();
		int atr = Math.min(14, size);

		double value = 0;

		for (int i = 0; i < atr; i++) {
			ShareVO share = list.get(--size);
			if (share.getOpen() == 0 || share.getHigh() == 0 || share.getLow() == 0) {
				continue;
			} else {
				double hl = Math.abs(share.getHigh() - share.getLow());
				double ch = Math.abs(share.getClose() - share.getHigh());
				double cl = Math.abs(share.getClose() - share.getLow());
				double temp = 0;
				temp = Math.max(hl, ch);
				value += Math.max(temp, cl);
			}
		}

		for (int i = interval; i > 0; i--) {
			double temp = value / (double) atr;
			DecimalFormat df = new DecimalFormat("0.00");
			String num = df.format(temp);

			ShareVO lastShare = list.get(size + atr - 1);
			if (lastShare.getOpen() != 0 && lastShare.getHigh() != 0 && lastShare.getLow() != 0) {
				double lastHL = Math.abs(lastShare.getHigh() - lastShare.getLow());
				double lastCH = Math.abs(lastShare.getClose() - lastShare.getHigh());
				double lastCL = Math.abs(lastShare.getClose() - lastShare.getLow());
				double t1 = 0;
				t1 = Math.max(lastHL, lastCH);
				value -= Math.max(t1, lastCL);
			}
			ATRVO a = new ATRVO(lastShare.getDate(), Double.parseDouble(num));
			atrList.add(a);
			
			if (size > 0) {
				ShareVO firstShare = list.get(--size);
				if (firstShare.getOpen() != 0 && firstShare.getHigh() != 0 && firstShare.getLow() != 0) {
					double hl = Math.abs(firstShare.getHigh() - firstShare.getLow());
					double ch = Math.abs(firstShare.getClose() - firstShare.getHigh());
					double cl = Math.abs(firstShare.getClose() - firstShare.getLow());
					double t = 0;
					t = Math.max(hl, ch);
					value += Math.max(t, cl);
				}
			}
		}
		if(atrList.isEmpty())
			return null;
		return atrList;
	}
}
