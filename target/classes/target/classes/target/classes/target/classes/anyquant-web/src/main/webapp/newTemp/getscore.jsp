<%@page import="blSerivce.predictSerivce.EvaluationService"%>
<%@page import="evaluation.GMEvaluation"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="evaluation.RBFEvaluation"%>
<%@page import="blSerivce.predictSerivce.EvaluationService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String x = request.getParameter("name");
EvaluationService rbfEvalution = new RBFEvaluation(x);
JSONArray arr=new JSONArray();
arr.add(rbfEvalution.getSum());
arr.add(rbfEvalution.getComplexity());//算法直观度
arr.add(rbfEvalution.getConvergenceSpeed());//学习速度
arr.add(rbfEvalution.getSpeed());//计算速度
arr.add(rbfEvalution.getLongTermAccuracy());//
arr.add(rbfEvalution.getShortTermAccuracy());//

EvaluationService gray=new GMEvaluation(x);
arr.add(gray.getSum());
arr.add(gray.getComplexity());//算法直观度
arr.add(gray.getConvergenceSpeed());//学习速度
arr.add(gray.getSpeed());//计算速度
arr.add(gray.getLongTermAccuracy());//
arr.add(gray.getShortTermAccuracy());//


out.print(arr);


%>