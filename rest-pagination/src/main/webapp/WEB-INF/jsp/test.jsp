<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

 <html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/jquery.validation/1.17.0/jquery.validate.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
</head>
<body>
<div id="leftside" class="col-sm-4"></div>
<div class="container col-sm-4" align="center">
  <h2>Registration Form</h2>
  <form action="">
    <div class="form-group">
       <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
    </div>
    <div class="form-group">
       <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
    </div>
     <div class="form-group">
       <input type="number" class="form-control" id="age" start="0" end="150" placeholder="Enter Age" name="pwd">
    </div>

    
    <div class="form-group">
   		<select class="form-control" id="sel1">
   			 <option>Graduate</option>
   			 <option>Post Graduate</option>
   			 <option>Others</option>
   		</select>
	</div>

    
    <div class="form-group">
       <input type="text" class="form-control" id="fullName" placeholder="Full Name" name="fullName">
       <input type="text" class="form-control" id="mobileNumber" placeholder="10 digit Mobile Number" name="mobileNumber">
       <input type="text" class="form-control" id="pincode" placeholder="6 digits pincode [0-9]" name="pincode">
       <input type="text" class="form-control" id="houseNo" placeholder="Flat/HouseNo/ Floor/Building" name="houseNo">
       <input type="text" class="form-control" id="street" placeholder="Colony /Street /Locality" name="street">
       <input type="text" class="form-control" id="landmark" placeholder="Landmark" name="landmark">
       <input type="text" class="form-control" id="city" placeholder="City" name="city">
    </div>
    <div class="form-group">
   		<select class="form-control" id="state">
   			 <option>Telangana</option>
   			 <option>Bangalore</option>
   			 <option>Hyderabad</option>
   		</select>
	</div>
       <div class="form-group" align="left">
       <label class="radio-inline"><input type="radio" name="optradio">Male</label>
	   <label class="radio-inline"><input type="radio" name="optradio">Female</label>
    </div>
    <div class="checkbox" align="left">
      <label><input type="checkbox" name="remember">I Accept all terms and conditions</label>
    </div>
    <button type="button" class="btn btn-default">Submit</button>
  </form>
</div>
<script>
 	$('form').validate({
 		rules:{
 			email:{
 				required:true
 			}, 
 			fullname:{
 				required:true
 			}
 		}
 	});
</script>
</body>
</html>
