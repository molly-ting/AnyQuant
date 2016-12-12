<%@page import="net.sf.json.JSONArray"%>
<%@page import="vo.NewsVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="businessLogic.newsBL.GetSpecificNews"%>
<%@page import="blService.newsBLService.GetSpecificNewsBLService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String x = request.getParameter("name");
String code=x.substring(2);
GetSpecificNewsBLService  snewsService=new GetSpecificNews();

ArrayList<NewsVO> list=snewsService.getAnnounceList(code);
JSONArray arr=new JSONArray();
for(NewsVO vo:list){
	arr.add(vo.getURL());
	arr.add(vo.getContent());
}
snewsService=null;
out.print(arr);
%>