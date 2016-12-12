<%@page import="java.util.ArrayList"%>
<%@page import="businessLogic.getInfoBL.Share"%>
<%@page import="blService.getInfoBLService.GetShareBLService"%>
<%@page import="businessLogic.getInfoBL.Benchmark"%>
<%@page import="vo.SimpilifiedShareVO"%>
<%@page import="blService.getInfoBLService.GetBenchmarkBLService"%>
<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
int row=17;
String now=request.getParameter("name");
System.out.println(now);
JSONArray arr=new JSONArray();
if (now.equals("1")){
	GetBenchmarkBLService benchmark = new Benchmark();
	SimpilifiedShareVO vo1=benchmark.getTodaySimple();
	arr.add(vo1.getId());
	arr.add(vo1.getName());
	arr.add(vo1.getClose());
	arr.add(vo1.getRate());
	
	GetShareBLService share = new Share();
    ArrayList<SimpilifiedShareVO> list2 = share.getAllShareToday();
    int count=0;
    count++;
    for (SimpilifiedShareVO vo2:list2){
    	count++;
    	if (count<=row){
    		arr.add(vo2.getId());
    		arr.add(vo2.getName());
    		arr.add(vo2.getClose());
    		arr.add(vo2.getRate());
    	}
    }
}else {
	GetShareBLService share = new Share();
    ArrayList<SimpilifiedShareVO> list3 = share.getAllShareToday();
    int count2=0;
    //count++;
    for (SimpilifiedShareVO vo3:list3){
    	count2++;
    	if (count2>row*(Integer.parseInt(now)-1)-1
    			&&count2<row*(Integer.parseInt(now))){
    		arr.add(vo3.getId());
    		arr.add(vo3.getName());
    		arr.add(vo3.getClose());
    		arr.add(vo3.getRate());
    	}
    }

//System.out.println(count2);
}
out.print(arr);
System.out.println(arr);


%>
