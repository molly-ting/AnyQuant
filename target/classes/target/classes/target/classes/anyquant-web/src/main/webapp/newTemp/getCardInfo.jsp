<%@ page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="blService.getInfoBLService.GetShareBLService" %>
<%@page import="businessLogic.getInfoBL.Share" %>
<%@page import="blService.getInfoBLService.GetBenchmarkBLService" %>
<%@page import="businessLogic.getInfoBL.Benchmark" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vo.FullShareVO" %>
<%@ page import="java.text.DecimalFormat"%>
<%
//String x = "sh600112";
    String id = request.getParameter("name");
    System.out.println(id);
    FullShareVO shareinfo;
    double yesprice = 0;
    String name = "沪深300指数";
    DecimalFormat df = new DecimalFormat("0.00");
    
    if(id.equals("sh000300")){
    	GetBenchmarkBLService benchmark = new Benchmark();
    	shareinfo = benchmark.getTodayDetail();
		//yesprice = list.get(1).getClose();
    }else{
    	GetShareBLService share = new Share("open+close+high+low+volume");
    	shareinfo = share.getOneShareToday(id);
    	//yesprice = list.get(list.size()-2).getClose();
    }
    
    double rate = shareinfo.getRate();
    yesprice = shareinfo.getClose()/(1+rate/100);
    JSONArray arr=new JSONArray();
	arr.add(shareinfo.getID());
	arr.add(shareinfo.getName());
	arr.add(df.format(yesprice)+"元");
	arr.add(df.format(shareinfo.getRate()));
	arr.add(shareinfo.getClose()+"元");
	arr.add(shareinfo.getHigh()+"元");
	arr.add(shareinfo.getLow()+"元");
	
	double volume = shareinfo.getVolume();
	String unit = "";
	
	if (volume > 10000) {
		volume /= 10000;
		unit = "万";
	}
	if (volume > 10000) {
		volume /= 10000;
		unit = "亿";
	}
	arr.add(df.format(volume) + unit);
	
	double sum = shareinfo.getSum();
	if (sum > 10000) {
		sum /= 10000;
		unit = "万元";
	}
	if (sum > 10000) {
		sum /= 10000;
		unit = "亿元";
	}
	if (sum > 10000) {
		sum /= 10000;
		unit = "万亿元";
	}
	arr.add(df.format(sum) + unit);
	
	arr.add(df.format(shareinfo.getPe()));
	arr.add(df.format(shareinfo.getPb()));//市净率
	arr.add(df.format(shareinfo.getBias())+"%");
	shareinfo=null;
	out.print(arr);
%>