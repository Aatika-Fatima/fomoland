$(document).ready(function(){
    $('.modal').modal();
    $(".button-collapse").sideNav();
    $(".brand-logo").click(function(){
    	$(".button-collapse").sideNav('show');
    });
  });