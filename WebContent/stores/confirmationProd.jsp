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
				<label>ID :</label> <label>${product.productId}</label>
			</div>
			<div class="form-group">
				<label>product Name French :</label> <label>${product.productNameFr}</label>
			</div>
			<div class="form-group">
				<label>Model year :</label> <label>${product.modelYear}</label>
			</div>
			<div class="form-group">
				<label>List of price :</label> <label>${product.listPrice}</label>
			</div>
			<div class="form-group">
				<label>Image :</label> <label><img
					src="ImageServlet?pid=${product.productId}" alt="Image du Produit"
					width="50"></label>
			</div>
		</div>
	</div>
</body>
</html>