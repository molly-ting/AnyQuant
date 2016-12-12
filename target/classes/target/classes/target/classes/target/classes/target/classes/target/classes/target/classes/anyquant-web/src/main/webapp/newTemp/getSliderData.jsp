<%@page import="net.sf.json.JSONArray"%>
<%@page import="accuracy.NormalDistribution"%>
<%@page import="blSerivce.predictSerivce.NormalDistributionService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String x = request.getParameter("name");

NormalDistributionService service=new NormalDistribution(x);
JSONArray arr=new JSONArray();


String min2str=String.format("%.2f", service.getMin());
String max2str=String.format("%.2f", service.getMax());
String min3str=String.format("%.2f", service.getMinThreeSigma());
String max3str=String.format("%.2f", service.getMaxThreeSigma());



arr.add(min2str);
arr.add(max2str);
arr.add(min3str);
arr.add(max3str);


System.out.println(x+": "+service.getMin()+" "+service.getMax()+" "+service.getMinThreeSigma()+" "+service.getMaxThreeSigma());
service=null;

out.print(arr);
%>