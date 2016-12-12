<%@page import="vo.ShareScoreVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="startegy.Strategy"%>
<%@page import="blSerivce.predictSerivce.StrategyService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
StrategyService service=new Strategy();
JSONArray arr=new JSONArray();

ArrayList<ShareScoreVO> volist=service.getStartegy();

arr.add(volist.get(0).getId());
arr.add(volist.get(0).getName());

out.print(arr);
volist=null;
service=null;



%>