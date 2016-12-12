<%@page import="graymodel.GrayModel"%>
<%@page import="vo.PredicateVO"%>
<%@page import="rbf.RBFNet"%>
<%@page import="blSerivce.predictSerivce.PredictService"%>
<%@page import="java.util.Calendar"%>
<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="blService.getInfoBLService.GetShareBLService"%>
<%@page import="businessLogic.getInfoBL.Share"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.ShareVO"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%
	String x = request.getParameter("name");

	if (x.charAt(0) == 'p') {//get predict value
		if(x.charAt(1)=='R'){//rbf
			String code=x.substring(2);
			PredictService predicteService=new RBFNet(code);	
			double[] beforetoday=predicteService.predict("2015-02-01");
			ArrayList<PredicateVO> aftertoday=predicteService.predict(30);  
			
			
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String enddate = format.format(cal.getTime());

			GetShareBLService share = new Share("open+close+high+low+volume");
			ArrayList<ShareVO> list = share.getShareDetail(code, "2015-02-01", enddate, "open+close+high+low+volume");

			JSONArray arr = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				String date = list.get(i).getDate();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date d = formatter.parse(date);
				//double[] data = { d.getTime(), list.get(i).getClose() };
				double[] data={d.getTime(),beforetoday[list.size()-i-1]};
				arr.add(data);
			}
			
			for(PredicateVO vo:aftertoday){
				String date=vo.getDate();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date d = formatter.parse(date);
				double[] data={d.getTime(),vo.getValue()};
				arr.add(data);
			}
			out.print(arr);
			
			
			
			
		}else{//gray
			String code=x.substring(2);
			PredictService predicteService=new GrayModel(code);
			double[] beforetoday=predicteService.predict("2015-02-01");
			ArrayList<PredicateVO> aftertoday=predicteService.predict(30);  
			
			
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String enddate = format.format(cal.getTime());

			GetShareBLService share = new Share("open+close+high+low+volume");
			ArrayList<ShareVO> list = share.getShareDetail(code, "2015-02-01", enddate, "open+close+high+low+volume");

			JSONArray arr = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				String date = list.get(i).getDate();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date d = formatter.parse(date);
				//double[] data = { d.getTime(), list.get(i).getClose() };
				double[] data={d.getTime(),beforetoday[list.size()-i-1]};
				arr.add(data);
			}
			
			for(PredicateVO vo:aftertoday){
				String date=vo.getDate();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date d = formatter.parse(date);
				double[] data={d.getTime(),vo.getValue()};
				arr.add(data);
			}
			out.print(arr);			
		}
		
		
	} else {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String enddate = format.format(cal.getTime());

		GetShareBLService share = new Share("open+close+high+low+volume");
		ArrayList<ShareVO> list = share.getShareDetail(x, "2015-02-01", enddate, "open+close+high+low+volume");

		JSONArray arr = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			String date = list.get(i).getDate();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date d = formatter.parse(date);
			double[] data = { d.getTime(), list.get(i).getClose() };
			arr.add(data);
		}

		out.print(arr);
		//out.flush();
	}
%>
