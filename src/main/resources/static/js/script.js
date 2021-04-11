$(function(){
    /*스크롤바 위치에 따른 메뉴 고정*/
    $(window).scroll(function(){
        var scrPos = $(this).scrollTop();

        if ($(this).scrollTop() >= 100) {
            $(".header").addClass("active");
        } else {
            $(".header").removeClass("active");
        }
    });
    
    /*달력 해당일 클릭시*/
    $(".js-calendar .day").click(function() {
        $(".js-calendar .day").removeClass("active");
        $(this).addClass("active");
    });
    
    
    /*메시지 클릭시*/
    $(".js-message li").click(function() {
        $(".js-message li").removeClass("active");
        $(this).addClass("active");
    });
    
    
    /*기본형 토글*/
    $(".js-toggle").click(function() {
        $(this).toggleClass("active");
        $(this).next().toggleClass("active");
    });
    
    
    /*팝업창 - 베이직*/
    $(".js-popbasic-open").click(function() {
        $(".js-popbasic").show();
    });
    $(".js-popbasic-close").click(function() {
        $(".js-popbasic").hide();
    });
    $(".js-popbasic2-open").click(function() {
        $(".js-popbasic2").show();
    });
    $(".js-popbasic2-close").click(function() {
        $(".js-popbasic2").hide();
    });
    
    
    /*textarea 자동 높이 조절*/
    $(".inp-chat").on("keydown keyup", function() {
      $(this).height(1).height( $(this).prop("scrollHeight")+1 );	
      $(".bar-chat").height(1).height( $(this).prop("scrollHeight")+1 );
    });
    
    
    /*햄버거 메뉴*/
    $(".js-hamburger").click(function () {
        $(".m-gnb").animate({'right': 0}, 500, "easeOutExpo");
        $(".m-gnb, .m-gnb-bg").show();
    });
    $(".js-hamburger-close").click(function () {
        $(".m-gnb").animate({'right': "-100%"}, 500, "easeOutExpo");
        $(".m-gnb, .m-gnb-bg").hide();
    });
});