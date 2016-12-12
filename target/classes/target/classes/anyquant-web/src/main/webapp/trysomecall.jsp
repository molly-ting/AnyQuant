<%@page import="vo.NewsVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="businessLogic.newsBL.GetSpecificNews"%>
<%@page import="blService.newsBLService.GetSpecificNewsBLService"%>
<%@page import="vo.ShareVO"%>
<%@page import="businessLogic.getInfoBL.Share"%>
<%@page import="blService.getInfoBLService.GetShareBLService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<%
GetShareBLService share = new Share();
ArrayList<ShareVO> list = share.getShareDetail("sh000300", "2016-05-01", "2016-05-11", null);

for(ShareVO vo:list){
	String a="<a href=\"";
	a+=vo.getDate();
	a+="\">";
	a+=vo.getClose();
	a+="</a> <br/>";
	out.println(a);
}
%> 


</body>
</html>