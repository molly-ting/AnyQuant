package businessLogic.statisticsBL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import blService.statisticBLService.KLineBLService;
import dto.ShareDTO;
import inteService.apiInteService.ApiInteBenchmarkService;
import integration.apiInte.BenchmarkInte;
import vo.ShareVO;

public class BenchMarkWeeklyK implements KLineBLService {
	public ArrayList<ShareVO> getWeeklyKLineData() {
		ArrayList<ShareVO> voList = new ArrayList<>();
		ApiInteBenchmarkService service = new BenchmarkInte();

		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek < 0)
			dayOfWeek = 0; // 0 is Sunday

		// 使用默认时区和语言环境获得一个日历
		// 定义格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 取当前日期的前5天
		cal.add(Calendar.DAY_OF_MONTH, -365);
		String startDate = format.format(cal.getTime());
		// 取当前日期的后一天
		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -dayOfWeek);
		String endDate = format.format(cal.getTime());

		// cal.add(Calendar.WEEK_OF_YEAR, 0);
		// String endline=format.format(cal.getTime());
		// System.out.println(endline); //4 -10

		// Calendar cal2=Calendar.getInstance();

		// 50 weeks
		ArrayList<ShareDTO> list = service.getBenchmark("open+close+high+low", startDate, endDate);
		if (list == null) {
			return null;
		} else {
			int i = list.size() - 1;
			int k = 0;
			cal.add(Calendar.DATE, -k * 7); // k and k-1
			String up = format.format(cal.getTime());

			cal.add(Calendar.DATE, 7 * (k - 1));
			String down = format.format(cal.getTime());
			// System.out.println(up);
			// System.out.println(down);

			ArrayList<ShareDTO> temp = new ArrayList<>();

			ShareDTO dt = list.get(list.size() - 1);
			temp.add(dt);

			for (; i >= 0; i--) {

				ShareDTO dto = list.get(i);
				String dtodate = dto.getDate();
				if (dtodate.compareTo(down) >= 0 && dtodate.compareTo(up) < 0) {
					// System.out.println(dtodate);
					temp.add(dto);
				} else {

					// System.out.println("dtodate"+dtodate);
					// System.out.println("up:"+up);
					// System.out.println("down:"+down);

					ShareVO svo = calculate(temp);
					voList.add(svo);

					temp.clear();

					up = down;
					// cal.add(Calendar.DATE ,7*(k-1)); //k and k-1
					// up=format.format(cal.getTime());

					cal.add(Calendar.DATE, -7);
					down = format.format(cal.getTime());

					//System.out.println("--------------------------");
					//System.out.println(dtodate);
					temp.add(dto);
				}

			}

		}
		return voList;
	}

	private ShareVO calculate(ArrayList<ShareDTO> list) {
		// 0st is friday
		// last is monday
		// date is friday date
		double close = list.get(0).getClose();
		double open = list.get(list.size() - 1).getOpen();
		String date = list.get(0).getDate();

		double maxhigh = list.get(0).getHigh();
		for (ShareDTO shareDTO : list) {
			if (shareDTO.getHigh() > maxhigh) {
				maxhigh = shareDTO.getHigh();
			}
		}

		double minlow = list.get(0).getLow();
		for (ShareDTO shareDTO : list) {
			if (shareDTO.getLow() < minlow) {
				minlow = shareDTO.getLow();
			}
		}

		// System.out.println(
		// "open:" + open + " close:" + close + " date:" + date + " high:" +
		// maxhigh + " minlow:" + minlow);

		ShareVO vo = new ShareVO("sh000300", open, close, date, maxhigh, minlow, 0, 0, 0, 0, 0);
		return vo;

	}

	@Override
	public ArrayList<ShareVO> getMonthlyKLineData() {
		ArrayList<ShareVO> voList = new ArrayList<>();
		ApiInteBenchmarkService service = new BenchmarkInte();

		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		if (dayOfMonth < 0)
			dayOfMonth = 0; // 0 is Sunday

		// 使用默认时区和语言环境获得一个日历
		// 定义格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 取当前日期的前5天
		cal.add(Calendar.DAY_OF_MONTH, -550);
		String startDate = format.format(cal.getTime());
		// 取当前日期的后一天
		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -dayOfMonth);
		String endDate = format.format(cal.getTime());

		ArrayList<ShareDTO> list = service.getBenchmark("open+close+high+low", startDate, endDate);
		if (list == null) {
			return null;
		} else {

			int i = list.size() - 1;
			int k = 0;
			cal.add(Calendar.MONTH, -k); // k and k-1
			String up = format.format(cal.getTime());

			cal.add(Calendar.MONTH, (k - 1));
			String down = format.format(cal.getTime());
			// System.out.println(up);
			// System.out.println(down);

			ArrayList<ShareDTO> temp = new ArrayList<>();

			ShareDTO dt = list.get(list.size() - 1);
			temp.add(dt);

			for (; i >= 0; i--) {

				ShareDTO dto = list.get(i);
				String dtodate = dto.getDate();
				if (dtodate.compareTo(down) > 0 && dtodate.compareTo(up) <= 0) {
					//System.out.println(dtodate);
					temp.add(dto);
				} else {

					// System.out.println("dtodate"+dtodate);
					// System.out.println("up:"+up);
					// System.out.println("down:"+down);

					ShareVO svo = calculateformonth(temp);
					voList.add(svo);

					temp.clear();

					up = down;
					// cal.add(Calendar.DATE ,7*(k-1)); //k and k-1
					// up=format.format(cal.getTime());

					cal.add(Calendar.MONTH, -1);
					down = format.format(cal.getTime());

					// System.out.println("--------------------------");
					// System.out.println(dtodate);
					temp.add(dto);
				}

			}

		}
		return voList;

		// return null;
	}

	private ShareVO calculateformonth(ArrayList<ShareDTO> list) {
		// 0st is friday
		// last is monday
		// date is friday date
		double close = list.get(0).getClose();
		double open = list.get(list.size() - 1).getOpen();
		String date = list.get(0).getDate();

		double maxhigh = list.get(0).getHigh();
		for (ShareDTO shareDTO : list) {
			if (shareDTO.getHigh() > maxhigh) {
				maxhigh = shareDTO.getHigh();
			}
		}

		double minlow = list.get(0).getLow();
		for (ShareDTO shareDTO : list) {
			if (shareDTO.getLow() < minlow) {
				minlow = shareDTO.getLow();
			}
		}

		date = date.substring(0, 7);

		// System.out.println("transdate:"+date);

		// System.out.println(
		// "open:" + open + " close:" + close + " date:" + date + " high:" +
		// maxhigh + " minlow:" + minlow);

		ShareVO vo = new ShareVO("sh000300", open, close, date, maxhigh, minlow, 0, 0, 0, 0, 0);
		return vo;

	}

}
