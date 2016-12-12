/**
 * Created by peiyulin on 16/5/28.
 */
function getNextDay(d){
    d = new Date(d);
    d = +d + 1000*60*60*24;
    d = new Date(d);
    var x=d.getFullYear()+"-";

    if((d.getMonth()+1)<10){
        x+="0";
    }
    x+=(d.getMonth()+1);
    x+="-";
    if(d.getDate()<10){
        x+="0";
    }
    x+=d.getDate();
    return x;
    // var x=new Date();
    // alert(x.getFullYear()+" "+x.getMonth()+1+" "+x.getDate());

}

function get15daysbefore() {
    var d = new Date();
    d = +d - 1000*60*60*24*15;
    d = new Date(d);

    var x=d.getFullYear()+"-";

    if((d.getMonth()+1)<10){
        x+="0";
    }
    x+=(d.getMonth()+1);
    x+="-";
    if(d.getDate()<10){
        x+="0";
    }
    x+=d.getDate();
    return x;
}

function getBuyindate() {

    var datearr=new Array();
    datearr[0]=get15daysbefore();
    var i=0;
    for(i=0;i<30;i++){
        datearr[i+1]=getNextDay(datearr[i]);
    }
   return datearr;
}

function getSelldate() {
    var datearr=new Array();
    var d=new Date();
    var x=d.getFullYear()+"-";

    if((d.getMonth()+1)<10){
        x+="0";
    }
    x+=(d.getMonth()+1);
    x+="-";
    if(d.getDate()<10){
        x+="0";
    }
    x+=d.getDate();
    datearr[0]=x;
    for(i=0;i<30;i++){
        datearr[i+1]=getNextDay(datearr[i]);
    }
   return datearr;
    
    
}
function getBuyDateByID(index) {
    var x=getBuyindate();
    return x[index];
}

function getSellDateByID(index) {
    var x=getSelldate();
    return x[index];
}

function getShareNumByID(index) {
    
    var x=[100,500,1000,3000,5000,10000,30000,50000,80000,100000,200000];
    return x[index];
}








