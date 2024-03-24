<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="com.services.implement.*,com.services.*,com.beans.*,java.util.*,javax.servlet.ServletOutputStream,java.io.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Verification Code</title>
<link
	href='https://fonts.googleapis.com/css?family=Lato&subset=latin,latin-ext'
	rel='stylesheet' type='text/css'>
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>
<link rel="stylesheet" href="css/verifycode.css">

</head>
<body>
	<!-- partial:index.partial.html -->
	<div id="wrapper">
		<div id="dialog">
			<button class="close">×</button>
			<h3>Entrez le code de vérification qui a été envoyé à votre
				email :</h3>
			<span>(nous voulons nous assurer qu'il s'agit bien de vous)</span>
			<div id="form">
				<form method="post" action="./VeirfyCodeServlet">
						<input type="hidden" name="email" value="${param.email}" />
						<c:if test="${not empty param.forgetPassword }">
							<input type="hidden" name="forgetPassword" value="forgetPassword" />
						</c:if>
						<input
						type="text" name="code1" maxLength="1" size="1" min="0" max="9"
						pattern="[0-9]{1}" required="required" /> <input type="text" name="code2"
						maxLength="1" size="1" min="0" max="9" pattern="[0-9]{1}" required="required" /><input
						type="text" name="code3" maxLength="1" size="1" min="0" max="9"
						pattern="[0-9]{1}" required="required" /><input type="text" name="code4"
						maxLength="1" size="1" min="0" max="9" pattern="[0-9]{1}" required="required" />
					<button class="btn btn-primary btn-embossed">Vérifier</button>
				</form>
			</div>

			<div>
				Le code n'a pas été réçu?<br /> <a href="./ResendCodeServlet?email=${param.email}">Renvoyer le code</a><br />
			</div>
			<img
				src="http://jira.moovooz.com/secure/attachment/10424/VmVyaWZpY2F0aW9uLnN2Zw=="
				alt="test" />
		</div>
		<div id="message" style="color: red;">
		${param.error}
		</div>
	</div>
	<!-- partial -->
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src="js/verifycode.js"></script>

</body>
</html>
