<%@page import="net.sf.json.JSONArray"%>
<%@page import="vo.SimpilifiedShareVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="combination.Combination"%>
<%@page import="blSerivce.predictSerivce.CombinationService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%


String x = request.getParameter("name");
String[] res=x.split("@");
double[] s=new double[8];
for(int i=0;i<8;i++){
	double num=Double.parseDouble(res[i]);
	s[i]=num;
}

for(int i=0;i<8;i++){
	System.out.println(s[i]);
}



CombinationService service=new Combination();

ArrayList<SimpilifiedShareVO> list= service.selectShare(s);

JSONArray arr=new JSONArray();

if(list==null){
	out.print(arr);
}else {
	int count =0;
	for (SimpilifiedShareVO vo:list){
		arr.add(vo.getId());
		arr.add(vo.getName());
		arr.add(vo.getClose());
		arr.add(vo.getRate());
		count++;
		if(count==25){
			break;
		}
	}
    out.print(arr);
    service=null;
    list=null;
}





%>