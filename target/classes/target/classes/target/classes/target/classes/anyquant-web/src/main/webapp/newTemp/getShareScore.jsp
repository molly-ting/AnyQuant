<%@page import="net.sf.json.JSONArray"%>
<%@page import="vo.ShareScoreVO"%>
<%@page import="startegy.Strategy"%>
<%@page import="blSerivce.predictSerivce.StrategyService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String x=request.getParameter("name");
StrategyService service=new Strategy();
ShareScoreVO vo=service.getStrategy(x);
JSONArray arr=new JSONArray();
arr.add(vo.getSum());
arr.add(vo.getPayOff());
arr.add(vo.getRisk());
arr.add(vo.getBuy());
arr.add(vo.getValue());
arr.add(vo.getIncrease());
service=null;
out.print(arr);
%>