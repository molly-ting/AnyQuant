<%@page import="blSerivce.predictSerivce.StrategyService"%>
<%@page import="startegy.Strategy"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="vo.ShareScoreVO"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
JSONArray arr=new JSONArray();

StrategyService strategy = new Strategy();
ArrayList<ShareScoreVO> list= strategy.getStartegy();
for (ShareScoreVO vo:list){
	Object[] data = {vo.getName(),vo.getSum()};
	arr.add(data);
//	arr.add(vo.getName());
//	arr.add(vo.getSum()+"");
	
}
out.print(arr);
System.out.println("hello");

%>