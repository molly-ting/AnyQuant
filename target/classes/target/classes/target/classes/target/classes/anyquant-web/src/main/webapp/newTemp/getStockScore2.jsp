<%@page import="vo.DrillDownBean"%>
<%@page import="java.util.List"%>
<%@page import="vo.ShareScoreVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="startegy.Strategy"%>
<%@page import="blSerivce.predictSerivce.StrategyService"%>
<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

StrategyService strategy = new Strategy();
ArrayList<ShareScoreVO> list= strategy.getStartegy();


List<Object> alist = new ArrayList<Object>();
for (ShareScoreVO vo:list){
DrillDownBean bean=new DrillDownBean();
bean.setName(vo.getName());
bean.setY(vo.getSum());
bean.setDrilldown(vo.getName());

alist.add(bean);
	
}

JSONArray arr=JSONArray.fromObject(alist);
out.print(arr);





%>