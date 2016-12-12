<%@page import="rbf.RBFNet"%>
<%@page import="vo.PredicateVO"%>
<%@page import="accuracy.NormalDistribution"%>
<%@page import="blSerivce.predictSerivce.NormalDistributionService"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="graymodel.GrayModel"%>
<%@page import="blSerivce.predictSerivce.PredictService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String x = request.getParameter("name");
String[] res=x.split("@");

JSONArray arr=new JSONArray();
 String code=res[0];
 String algotype=res[1];
 String buydate=res[2];
 String selldate=res[3];
 String stocknumstr=res[4];
 

int stocknum=Integer.parseInt(stocknumstr);
PredictService service;
 if(algotype.equals("g")){
  service=new GrayModel(code);
 }else{
	 service=new RBFNet(code);
 }
 
 NormalDistributionService normal = new NormalDistribution(code);
 
 double profit=  service.predictOne(buydate, selldate, normal.getMin(), normal.getMax(), stocknum);  
 
	PredicateVO pvo=service.getBest(stocknum);//  某一天收益最大　
	double maxmoney=pvo.getValue();
	String maxdate=pvo.getDate();
	int score=service.evaluate(profit/stocknum);

	String profitstr=String.format("%.2f", profit);
	String maxmoneystr=String.format("%.2f", maxmoney);

	if (Math.abs(profit) > 10000) {
		profit /= 10000;
		profitstr=String.format("%.2f", profit);
		profitstr+="万";
	}
	
	if (Math.abs(maxmoney) > 10000) {
		maxmoney /= 10000;
		maxmoneystr=String.format("%.2f", maxmoney);
		maxmoneystr+="万";
	}
	
	arr.add(profitstr);
	arr.add(maxmoneystr);
	arr.add(maxdate);
	arr.add(score);
	
	out.print(arr);
%>