<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Forgot Password Page</title>
<link rel="stylesheet" href="css/forgetpassword.css" />
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="author" content="Yinka Enoch Adedokun" />
<meta name="description"
	content="Simple Forgot Password Page Using HTML and CSS" />
<meta name="keywords" content="forgot password page, basic html and css" />

</head>
<body>
	<div class="row">
		<h1>Mot de passe oublié</h1>
		<h6 class="information-text">Entrez votre email enregistré pour
			réinitialiser votre mot de passe.</h6>
		<div class="form-group">
			<form method="get" action="./verifycode.jsp">
				<input type="hidden" name="forgetPassword" value="forgetPassword"/>
				<input type="email" name="email" id="user_email" required="required">
				<p>
					<label for="user_email">Email</label>
				</p>
				<button type="submit">Continuer</button>
			</form>
		</div>
		<!-- <div class="footer">
			<h5>New here? <a href="#">Sign Up.</a></h5>
			<h5>Already have an account? <a href="#">Sign In.</a></h5>
			<p class="information-text"><span class="symbols" title="Lots of love from me to YOU!">&hearts; </span><a href="https://www.facebook.com/adedokunyinka.enoch" target="_blank" title="Connect with me on Facebook">Yinka Enoch Adedokun</a></p>
		</div> -->
	</div>

</body>
</html>