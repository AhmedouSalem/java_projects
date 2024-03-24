<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="com.services.*,com.services.implement.*,com.beans.*,java.util.*,
javax.servlet.ServletOutputStream,java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Confirmation</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="panel panel-primary  ">
		<div class="panel-heading">
			<h3>Stores</h3>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<label>ID :</label> <label>${store.ID}</label>
			</div>
			<div class="form-group">
				<label>storeNameFr :</label> <label>${store.storeNameFr}</label>
			</div>
			<div class="form-group">
				<label>storeNameAr : </label> <label>${store.storeNameAr}</label>
			</div>
			<div class="form-group">
				<label>phone :</label> <label>${store.phone}</label>
			</div>
			<div class="form-group">
				<label>email :</label> <label>${store.email}</label>
			</div>
			<div class="form-group">
				<label>street :</label> <label>${store.street}</label>
			</div>
			<div class="form-group">
				<label>city :</label> <label>${store.city}</label>
			</div>
			<div class="form-group">
				<label>storesActive :</label> <label>${store.storesActive}</label>
			</div>
			<div class="form-group">
				<label>credit :</label> <label>${store.credit}</label>
			</div>
		</div>
	</div>
</body>
</html>