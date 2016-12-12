<%@page import="java.util.List"%>
<%@page import="vo.ChartBean"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="vo.PartitionVO"%>
<%@page import="vo.MoneyVO"%>
<%@page import="java.util.ArrayList"%>
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

ArrayList<MoneyVO> list=service.shareEstimate(arrlist);


List<Object> alist = new ArrayList<Object>();

for(MoneyVO vo:list){
	ChartBean bean=new ChartBean();
	bean.setName(vo.getId());
	bean.setY(vo.getMoney());
	alist.add(bean);
}


JSONArray arr=JSONArray.fromObject(alist);
out.print(arr);

service=null;
arrlist=null;
list=null; 



%>