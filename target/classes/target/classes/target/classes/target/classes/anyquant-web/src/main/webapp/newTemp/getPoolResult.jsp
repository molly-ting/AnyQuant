<%@page import="java.text.DecimalFormat"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.EstimateVO"%>
<%@page import="vo.PartitionVO"%>
<%@page import="combination.Estimate"%>
<%@page import="blSerivce.predictSerivce.EstimateService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

String para = request.getParameter("name");

String[] res=para.split("@");
int len=Integer.parseInt(res[0]);
ArrayList<PartitionVO> arrlist=new ArrayList<PartitionVO>();

for(int i=1;i<len*2;i+=2){
	int num=Integer.parseInt(res[i+1]);
	PartitionVO vo=new PartitionVO(res[i],num);
	arrlist.add(vo);
}

EstimateService service=new Estimate();
EstimateVO evo=service.estimate(arrlist);

JSONArray arr=new JSONArray();

DecimalFormat df = new DecimalFormat("0.00");
arr.add(df.format(evo.getHighest()));
arr.add(df.format(evo.getLowest()));
arr.add(df.format(evo.getPossibleHigh()));
arr.add(df.format(evo.getPossibleLow()));
out.print(arr);

service=null;
evo=null;
arrlist=null; 





%>