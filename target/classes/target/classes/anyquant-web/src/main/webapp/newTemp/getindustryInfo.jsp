<%@ page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vo.IndustryInfoTop50VO" %>
<%@ page import="businessLogic.industriesBL.GetTopIndustries50" %>
<%@ page import="blService.IndustriesBLService.GetTopIndustriesBLService" %>
<%
	GetTopIndustriesBLService service = new GetTopIndustries50();
	ArrayList<IndustryInfoTop50VO> list = service.geTop50s();
	JSONArray arr=new JSONArray();
	for(int i = 0;i<15;i++){
		Object[] data = {list.get(i).getName(),list.get(i).getTotalvolume()};
		arr.add(data);
	}
	
	out.print(arr);
	service=null;
	list=null;
%>