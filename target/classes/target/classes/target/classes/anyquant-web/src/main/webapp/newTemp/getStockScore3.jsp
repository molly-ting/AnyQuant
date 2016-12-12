<%@page import="vo.DetailBean"%>
<%@page import="java.util.List"%>
<%@page import="startegy.Strategy"%>
<%@page import="vo.ShareScoreVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="blSerivce.predictSerivce.StrategyService"%>
<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

StrategyService strategy = new Strategy();
ArrayList<ShareScoreVO> listx= strategy.getStartegy();
List<Object> alist = new ArrayList<Object>();
for (ShareScoreVO vo:listx){
DetailBean bean=new DetailBean();
bean.setName(vo.getName());
bean.setId(vo.getName());
/* String a=""+vo.getPayOff();
String b=""+vo.getRisk();
String c=""+vo.getBuy();
String d=""+vo.getValue(); 
String e=""+vo.getIncrease(); */


bean.set5scores(vo.getPayOff(), vo.getRisk(), vo.getBuy(), vo.getValue(), vo.getIncrease());

alist.add(bean);
	
}


JSONArray arr=JSONArray.fromObject(alist);
out.print(arr);


%>