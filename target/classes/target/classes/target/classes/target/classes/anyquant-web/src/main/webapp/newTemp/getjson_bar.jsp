<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="blService.getInfoBLService.GetShareBLService" %>
<%@page import="businessLogic.getInfoBL.Share" %>
<%@page import="java.util.ArrayList" %>
<%@page import="vo.ShareVO" %>
<%@page import="java.text.ParseException" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<%@page import="java.util.Calendar" %>
<% 
	String x = request.getParameter("name");

Calendar cal = Calendar.getInstance();
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
String enddate = format.format(cal.getTime());
cal.add(Calendar.YEAR, -1);
String startdate = format.format(cal.getTime());
	GetShareBLService share = new Share();
	ArrayList<ShareVO> list = share.getShareDetail(x, startdate,enddate,"volume");
	//System.out.println(list.size());
	JSONArray arr=new JSONArray();
	
	for(int i = 0;i<list.size();i++){
		String date = list.get(i).getDate();
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
		Date d = formatter.parse(date);
		double[] data = {d.getTime(),list.get(i).getVolume()};
		arr.add(data);
	}

	out.print(arr);
	//out.flush();

%>
