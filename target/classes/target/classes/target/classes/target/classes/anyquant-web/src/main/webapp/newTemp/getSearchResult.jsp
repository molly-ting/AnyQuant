<%@page import="net.sf.json.JSONArray"%>
<%@page import="vo.SimpilifiedShareVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="businessLogic.getInfoBL.Share"%>
<%@page import="blService.getInfoBLService.GetShareBLService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String x = request.getParameter("name");
JSONArray arr=new JSONArray();
GetShareBLService service=new Share();
ArrayList<SimpilifiedShareVO> list=service.find(x);
if(list==null){
	out.print(arr);
}else {
	int count =0;
	for (SimpilifiedShareVO vo:list){
		arr.add(vo.getId());
		arr.add(vo.getName());
		arr.add(vo.getClose());
		arr.add(vo.getRate());
		count++;
		if(count==18){
			break;
		}
	}
    out.print(arr);
    service=null;
    list=null;
}

%>