<%@page import="vo.NewsVO"%>
<%@page import="businessLogic.newsBL.GetSpecificNews"%>
<%@page import="blService.newsBLService.GetSpecificNewsBLService"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="vo.ImageNewsVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="businessLogic.newsBL.GetNews"%>
<%@page import="blService.newsBLService.GetNewsBLService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String x = request.getParameter("name");
if(x.equals("sh000300")){
	GetNewsBLService newsservice=new GetNews();
    ArrayList<ImageNewsVO> newsList= newsservice.getImageNewsList();
 	JSONArray arr=new JSONArray();
     for(ImageNewsVO vo:newsList){
    	 arr.add(vo.getURL());
    	 arr.add(vo.getContent());
     }
     
     newsservice=null;
	out.print(arr);
	
}else{
	String code=x.substring(2);
	GetSpecificNewsBLService  snewsService=new GetSpecificNews();
	ArrayList<NewsVO> list=snewsService.getNewsList(code);
	JSONArray arr=new JSONArray();
	for(NewsVO vo:list){
		arr.add(vo.getURL());
		arr.add(vo.getContent());
	}
	snewsService=null;
	out.print(arr);
	
}



%>