<%@page import="net.sf.json.JSONArray"%>
<%@page import="vo.DonutVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="startegy.Strategy"%>
<%@page import="blSerivce.predictSerivce.StrategyService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
StrategyService service = new Strategy();
ArrayList<DonutVO> list=service.getSingleAspect();
JSONArray arr=new JSONArray();
for(DonutVO vo:list){
	arr.add(vo.getMax());
	arr.add(vo.getId());
	arr.add(vo.getName());
	//20-16  16-12  12-8 8-4 4-0
	int[] res=vo.getArr();
	arr.add(res[0]);
	arr.add(res[1]);
	arr.add(res[2]);
	arr.add(res[3]);
	arr.add(res[4]);
}

// 8 a group
out.print(arr);
service=null;
list=null;

%>
