<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>
	<label id="contactName"></label>
	<label id="contactNumber"></label>
	<br>
	<a id="prev" href="">prev</a>
	<a id="next" href="">next</a>


</body>
<script>

 	var count = 1;
	$(document).ready(function() {
		$("#prev").click(function(event) {
			event.preventDefault();
			alert('PREV clicked');
			alert($(this).attr("href"));
			$.ajax({
		 		type:'GET',
		 		url:$(this).attr("href"),
		 		success:function(result){
		 			procesLinks(result);
		 			$.each(result.content,function(key,value){
		 				 alert(key + " " + value.id + " "+ value.contactName + " " + value.contactNumber);
 		 			});
 		 			
		 		}
		 			
		 	});
		});
		$("#next").click(function(event) {
			event.preventDefault();
			alert('NEXT clicked');
			alert($(this).attr("href"));
			$.ajax({
		 		type:'GET',
		 		url:$(this).attr("href"),
		 		success:function(result){
		 			procesLinks(result);
		 			$.each(result.content,function(key,value){
		 				 alert(key + " " + value.id + " "+ value.contactName + " " + value.contactNumber);
 		 			});
 		 		}
			
		 			
		 	});
		});
		if(count == 1){
			count=2;
		 	$.ajax({
				type:'GET',
				url:"https://localhost:9000/contacts?page=0&size=1",
				success:function(result){
					procesLinks(result);
					$.each(result.content,function(key,value){
						 alert(key + " " + value.id + " "+ value.contactName + " " + value.contactNumber);
					});
				}
					
			});
		}
	
		 	function procesLinks(result){
		 		var baseUrl="https://localhost:9000/contacts?page=";
 		 		 
		 		var currentPage = result.number;
		 		alert('Current Page = ' + currentPage);
				var nextPage;
				var prevPage;
				if(result.first == true && result.last==false){
					nextPage = currentPage + 1;
				//	baseUrl = baseUrl+nextPage+"&size=1";
 					$("#prev").hide(); 
					$("#next").show();
					$("#next").attr("href",baseUrl+nextPage+"&size=1");			
				} 
				
				else if(result.first == false && result.last==false) {
					$("#prev").show();
					prevPage = parseInt(currentPage) -1;
					//baseUrl =baseUrl+prevPage+"&size=1";
					alert("Case 2 --\nPrev Page = " +prevPage +"\n"+baseUrl+prevPage+"&size=1");
					$("#prev").attr("href", baseUrl+prevPage+"&size=1");
					$("#next").show();
					nextPage = parseInt(currentPage) + 1;
					//baseUrl = baseUrl+nextPage+"&size=1";
					$("#next").attr("href", baseUrl+nextPage+"&size=1");
					alert("Case 2 --\nNext Page = " +nextPage +"\n"+baseUrl+nextPage+"&size=1");

				}

				else if(result.first == true && result.last==true) {
					alert("Case 3 --\nPrev Page = " +prevPage +"\n"+baseUrl);
					$("#prev").hide();
					$("prev").attr("href", window.location.href);
					$("#next").hide();
					$("#prev").attr("href", window.location.href);

				}

				
				else if(result.first == false && result.last==true) {
					$("#prev").show();
					prevPage = parseInt(currentPage) -1;
					alert("Case 4 --\nPrev Page = " +prevPage +"\n"+baseUrl+prevPage+"&size=1");
					$("#prev").attr("href", baseUrl+prevPage+"&size=1");
					$("#next").hide();
					$("#next").attr("href",window.location.href);

				}
				
		 	}
			
 
		
		
	});
</script>
</html>