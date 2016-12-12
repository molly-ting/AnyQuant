/**
 * Created by peiyulin on 16/6/13.
 */
function sendToTable(){

    var selectrisk = document.getElementById('selectrisk').value;
    var selectday = document.getElementById('selectday').value;


    if(selectday==0){
        toastr.warning('请填写连涨天数', '注意', {
            closeButton: true,
            progressBar: false,
        });
        return;
    }
    if(selectrisk==0){
        toastr.warning('请填写风险系数', '注意', {
            closeButton: true,
            progressBar: false,
        });
        return;
    }

    selectday--;
    selectrisk--;
    selectrisk/=10;


    var volindexleft= $('#range_02volume').data().from;
    var shortindexleft= $('#range_03short').data().from;
    var middleindexleft= $('#range_04middle').data().from;
    var longindexleft= $('#range_05long').data().from;
    shortindexleft/=100;
    middleindexleft/=100;
    longindexleft/=100;
    volindexleft*=10000;



    var volindexright= $('#range_02volume').data().to;

    volindexright*=10000;

    var para="0";
    para+="@";
    para+=selectday;
    para+="@";
    para+=volindexleft;
    para+="@";
    
    para+=volindexright;
    para+="@";
    
    
    para+=shortindexleft;
    para+="@";
    
    
    
    para+=middleindexleft;
    para+="@";
   
    
    para+=longindexleft;
    para+="@";
    
    
    
    para+=selectrisk;
    
    $.getJSON('getPoolSearchResult.jsp',{name:para},function (data) {
    	var up="<i class=\"fa fa-arrow-up\" style=\"color: red;\"></i>";
        var down="<i class=\"fa fa-arrow-down\" style=\"color: green;\"></i>";
        var add = "<i class=\"fa fa-plus-square-o\"></i>";
        var i=1;
        
        if (data.length==0){
        	//alert("没找到结果");
        	$(function(){

        	    toastr.error('没找到结果', '搜索结果：', {
        	    	positionClass: "toast-top-left",
        	        closeButton: false,
        	        progressBar: false,
        	       
        	    });
        	  });
        	    
        	//document.getElementById("ul").style.display="block";
        	returnFirst();
        }else {
            //alert(data.length/4);
            for (i=1;i<=101;i++){
               var id=1000+i+"";

               if ((i-1)*4<data.length){
            	   if (data[3+(i-1)*4]>=0){
                 	  document.getElementById(id).innerHTML="<td>"+
                 	  data[0+(i-1)*4]+"</td>"+"<td>"+data[1+(i-1)*4]+"</td>"+"<td>"+data[2+(i-1)*4]+"</td>"
                          +"<td>"+up+"</td>"+"<td>"+add+"</td>";
                      }else {
                 			document.getElementById(id).innerHTML="<td>"+
                 			data[0+(i-1)*4]+"</td>"+"<td>"+data[1+(i-1)*4]+"</td>"+"<td>"+data[2+(i-1)*4]+"</td>"
                                +"<td>"+down+"</td>"+"<td>"+add+"</td>";
                 			
                 		}
                   var jqu="tablefunc('"+data[0+(i-1)*4]+"')";
                   var jquid="#"+id;
              	   $(jquid).attr({	
              		   
              		   "onclick" : jqu
              	   });
                    
                   var addfun = "addstock('"+data[0+(i-1)*4]+"','"+data[1+(i-1)*4]+"')";
                   document.getElementById(id).children[4].setAttribute("onclick",addfun);
              	 document.getElementById(id).style.display="table-row";
                   document.getElementById(id).draggable = true;
                   document.getElementById(id).setAttribute("ondragstart","drag(event)");
                   
                }else {
                	document.getElementById(id).style.display="none";

                }
               }
        

           document.getElementById("ul").style.display="none";
   		
        }
		
		
    	
    	
    	
    	
    });

    



    
    
    


}