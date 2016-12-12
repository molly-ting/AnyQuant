
function allowDrop(ev){
  ev.preventDefault();
}

function drag(ev){
  ev.dataTransfer.setData("Text",ev.target.id);
    //alert(ev.target.id);
}

function drop(ev){

    ev.preventDefault();
    var data=ev.dataTransfer.getData("Text"); 

    var stockcode = document.getElementById(data).children[0].innerText;
    var stockname = document.getElementById(data).children[1].innerText;

    if(!isexisted(stockcode)){
    	 var para = document.createElement("li");
    	 para.setAttribute("style", "width:100%;height:9%;padding-top:3%;font-size: 0.9em;border-bottom: 1px solid #d1d4d7;");
    	 
        var node0 = document.createElement("span");
        node0.innerText = stockcode;
        node0.setAttribute("style", "width:15%;margin-left:3%;");
        //node0.value = stockcode;
        para.appendChild(node0);

        var node1 = document.createElement("span");
        node1.innerText = stockname;
        node1.setAttribute("style", "width:15%;margin-left:3%;");
        para.appendChild(node1);

        var node2 = document.createElement("input");
        node2.type = "number";
        node2.min = "0";
        node2.placeholder = "股数";
        node2.setAttribute("style","width:20%;height:18px;padding:0;border-radius: 5px;border: 1px solid lightgrey;margin-left:3%;");
        para.appendChild(node2);

        var node3 = document.createElement("i");
        node3.setAttribute("class", "fa fa-close");
        node3.setAttribute("style","cursor:pointer;color:red;width:5%;margin-right:1%;padding-right:0%;text-align:right;float:right;");
        node3.setAttribute("onclick","clearstock(event)");
        para.appendChild(node3);

        ev.target.appendChild(para);
    }

}

function addstock(stockcode,stockname) {
	
    if(!isexisted(stockcode)){
        var para = document.createElement("li");
        para.setAttribute("style", "width:100%;height:9%;padding-top:3%;font-size: 0.9em;border-bottom: 1px solid #d1d4d7;");

        var node0 = document.createElement("span");
        node0.innerText = stockcode;
        node0.setAttribute("style", "width:15%;margin-left:3%;");
        //node0.value = stockcode;
        para.appendChild(node0);
        
        var node1 = document.createElement("span");
        node1.innerText = stockname;
        node1.setAttribute("style", "width:15%;margin-left:3%;");
        para.appendChild(node1);

        var node2 = document.createElement("input");
        node2.type = "number";
        node2.min = "0";
        node2.placeholder = "股数";
        node2.setAttribute("style","width:20%;height:18px;padding:0;border-radius: 5px;border: 1px solid lightgrey;margin-left:3%;");
        para.appendChild(node2);

        var node3 = document.createElement("i");
        node3.setAttribute("class", "fa fa-close");
        node3.setAttribute("style","cursor:pointer;color:red;width:5%;margin-right:1%;padding-right:0%;text-align:right;float:right;");
        node3.setAttribute("onclick","clearstock(event)");
        para.appendChild(node3);

        document.getElementById("stockcontainer").appendChild(para);
    }
}

function clearstock(ev){
    var node = ev.target.parentNode;
    ev.target.parentNode.parentNode.removeChild(node);
    //getchosenstock();
}

function getchosenstock() {
    var data = new Array();
    var con = document.getElementById("stockcontainer");
    var nodes = con.children; //alert(nodes[2].innerText);
    var num = nodes.length;//alert(num);
    
    var i;
    for(i=0;i<num;i++){
        data[i] = new Array();
       // alert(nodes[i].children[0].innerHTML);
        data[i][0]= nodes[i].children[0].innerText;
        //data[i][1]= nodes[i].children[1].innerText;
        data[i][1]= nodes[i].children[2].value;
    }
    
    return data;
}

function isexisted(stockcode) {
	if(stockcode=="sh000300"){
		return true;
	}
	
    var con = document.getElementById("stockcontainer");
    var nodes = con.children; //alert(nodes[2].innerText);
    var num = nodes.length;//alert(num);

    var i;
    for(i=0;i<num;i++){
        if(stockcode==nodes[i].children[0].innerText){
        	 toastr.info('该股票已经在队列里了', '注意', {
        	        closeButton: true,
        	        progressBar: false,
        	    });
            return true;
        }
    }
    return false;
}