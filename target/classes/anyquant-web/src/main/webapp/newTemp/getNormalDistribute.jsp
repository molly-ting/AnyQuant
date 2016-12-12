<%@page import="net.sf.json.JSONArray"%>
<%@page import="vo.NormalDistributionVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="accuracy.NormalDistribution"%>
<%@page import="blSerivce.predictSerivce.NormalDistributionService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String x = request.getParameter("name");
NormalDistributionService service=new NormalDistribution(x);
ArrayList<NormalDistributionVO> list=service.getNormalDistribution();
JSONArray arr=new JSONArray();
for(NormalDistributionVO vo:list){
	arr.add(vo.getX());
	arr.add(vo.getY()); 
	
	System.out.println("x:"+vo.getX()+" y:"+vo.getY());
}

out.print(arr);


%>