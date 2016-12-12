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
    String name = "æ²ªæ·±300æŒ‡æ•°";
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
	arr.add(df.format(yesprice)+"å…?");
	arr.add(df.format(shareinfo.getRate()));
	arr.add(shareinfo.getClose()+"å…?");
	arr.add(shareinfo.getHigh()+"å…?");
	arr.add(shareinfo.getLow()+"å…?");
	
	double volume = shareinfo.getVolume();
	String unit = "";
	
	if (volume > 10000) {
		volume /= 10000;
		unit = "ä¸?";
	}
	if (volume > 10000) {
		volume /= 10000;
		unit = "äº?";
	}
	arr.add(df.format(volume) + unit);
	
	double sum = shareinfo.getSum();
	if (sum > 10000) {
		sum /= 10000;
		unit = "ä¸‡å…ƒ";
	}
	if (sum > 10000) {
		sum /= 10000;
		unit = "äº¿å…ƒ";
	}
	if (sum > 10000) {
		sum /= 10000;
		unit = "ä¸‡äº¿å…?";
	}
	arr.add(df.format(sum) + unit);
	
	arr.add(df.format(shareinfo.getPe()));
	arr.add(df.format(shareinfo.getPb()));//å¸‚å‡€çŽ?
	arr.add(df.format(shareinfo.getBias())+"%");
	shareinfo=null;
	out.print(arr);
%>
