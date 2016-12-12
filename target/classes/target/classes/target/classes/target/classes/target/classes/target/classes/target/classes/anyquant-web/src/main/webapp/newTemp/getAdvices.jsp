<%@page import="net.sf.json.JSONArray"%>
<%@page import="businessLogic.getInfoBL.Share"%>
<%@page import="blService.getInfoBLService.GetShareBLService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

GetShareBLService service=new Share();
JSONArray arr=new JSONArray();
String id = request.getParameter("name");

arr.add(service.analysis(id));
arr.add(service.lineanalyse(id));

service=null;
out.print(arr);




%>