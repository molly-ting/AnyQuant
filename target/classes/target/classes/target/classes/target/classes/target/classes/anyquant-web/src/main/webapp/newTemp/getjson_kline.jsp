<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="blService.getInfoBLService.GetShareBLService" %>
<%@page import="businessLogic.getInfoBL.Share" %>
<%@page import="java.util.ArrayList" %>
<%@page import="vo.ShareVO" %>
<%@page import="java.text.ParseException" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<% 
	String x = request.getParameter("name");
	



Calendar cal = Calendar.getInstance();
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
String enddate = format.format(cal.getTime());

	GetShareBLService share = new Share("open+close+high+low+volume");
	ArrayList<ShareVO> list = share.getShareDetail(x, "2015-01-01", enddate, null);
	JSONArray arr=new JSONArray();
	for(int i = 0;i<list.size();i++){
		String date = list.get(i).getDate();
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
		Date d = formatter.parse(date);
		double[] data = {d.getTime(),list.get(i).getOpen(),list.get(i).getHigh(),
				list.get(i).getLow(),list.get(i).getClose(),list.get(i).getVolume()};
		
		arr.add(data);
	}

	out.print(arr);
	//out.flush();

%>



