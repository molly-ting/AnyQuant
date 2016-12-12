/**
 * Created by peiyulin on 16/5/14.
 */
$(function(){

    var i=0;
    var clone=$(".mybanner .img li").first().clone();
    $(".mybanner .img").append(clone);
    var size=$(".mybanner .img li").size();
    for(var j=0;j<size-1;j++){
        $(".mybanner .num").append("<li></li>");
    }
    $(".mybanner .num li").first().addClass("on");

    $(".mybanner  .img").css({left:-300})
    $(".mybanner  .img").css({top:-150})
    /*鼠标划入圆点*/
    $(".mybanner .num li").hover(function(){
        var index=$(this).index();
        i=index;
        $(".mybanner .img").stop().animate({left:-index*900},500)
        $(this).addClass("on").siblings().removeClass("on")
    })


    /*自动轮播*/
    var t=setInterval(function(){
        i++;
        move()
    },2000)


    /*对banner定时器的操作*/
    $(".mybanner").hover(function(){
        clearInterval(t);
    },function(){
        t=setInterval(function(){
            i++;
            move()
        },2000)
    })



    /*向左的按钮*/
    $(".mybanner .btn_r").click(function(){
        i++
        move();
    })



    /*向右的按钮*/
    $(".mybanner .btn_l").click(function(){
        i--
        move()
    })






    function move(){




        if(i==size){
            $(".mybanner  .img").css({left:-300})
            i=1;
        }


        if(i==-1){
            $(".mybanner .img").css({left:-(size-1)*900-300})
            i=size-2;
        }

        $(".mybanner .img").stop().animate({left:-i*900-300},500)

        if(i==size-1){
            $(".mybanner .num li").eq(0).addClass("on").siblings().removeClass("on")
        }else{
            $(".mybanner .num li").eq(i).addClass("on").siblings().removeClass("on")
        }



    }
});
