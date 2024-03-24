<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="com.services.*,com.beans.*,java.util.*,javax.servlet.ServletOutputStream,java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/form.css" />
<!-- Unicons -->
<link rel="stylesheet"
	href="https://unicons.iconscout.com/release/v4.0.0/css/line.css" />
</head>
<body>
	<!-- Home -->
	<!-- <section class="home"> -->
	<div class="form_container">
		<i class="uil uil-times form_close"></i>
		<!-- Login From -->
		<div class="form login_form">
			<form action="./LoginServlet" method="post">
				<h2>Se conncecter</h2>

				<div class="input_box">
					<input type="email" name="email" id="myEmail"
						placeholder="Entrer votre email..." required /> <i
						class="uil uil-envelope-alt email"></i>
				</div>
				<div class="input_box">
					<input type="password" name="password" id="myPassword"
						placeholder="Entrer votre mot de passe..." required /> <i
						class="uil uil-lock password"></i> <i
						class="uil uil-eye-slash pw_hide"></i>
				</div>

				<div class="option_field">
					<span class="checkbox"> <input type="checkbox"
						id="remember_me" name="remember_me" /> <label for="remember_me">Se
							souvenir de moi</label>
					</span> <a href="./forgetpassword.jsp" class="forgot_pw">Mot de passe oublié?</a>
				</div>

				<button class="button btn">Se connecter</button>

				<div class="login_signup">
					Vous n'avez pas un compte? <a href="#" id="signup">Régistrer</a>
				</div>
			</form>
		</div>

		<!-- Signup From -->
		<div class="form signup_form">
			<form action="./SignUpServlet" method="post">
				<h2>Régistrer</h2>
				<div class="input_box">
					<input type="text" name="username" placeholder="Le nom d'utilisateur..." required />
					<i class="fa fa-user email"></i>
				</div>
				<div class="input_box">
					<input type="email" name="email" placeholder="Entrer votre email" required /> <i
						class="uil uil-envelope-alt email"></i>
				</div>
				<div class="input_box">
					<input type="tel" name="phoneNumber" placeholder="Télephone" required /> <i
						class="uil uil-phone email"></i>
				</div>
				<div class="input_box">
					<input type="password" name="password" placeholder="Mot de passe" required /> <i
						class="uil uil-lock password"></i> <i
						class="uil uil-eye-slash pw_hide"></i>
				</div>
				<div class="input_box">
					<input type="password" name="confirmPassword" placeholder="Confirmé le mot de passe"
						required /> <i class="uil uil-lock password"></i> <i
						class="uil uil-eye-slash pw_hide"></i>
				</div>

				<button class="button btn">Régistrer maintenant</button>

				<div class="login_signup">
					Avez-vous un compte? <a href="#" id="login">Se connecter</a>
				</div>
			</form>
		</div>
	</div>
	<!-- </section> -->
	<script type="text/javascript" src="js/form.js"></script>
	<script>
		window.onload = function() {
			showSnackbar();
			var emailCookie = getCookie("email");
			var passwordCookie = getCookie("password");
			var rememberMeCookie = getCookie("remember_me");

			if (emailCookie && passwordCookie) {
				document.getElementById("myEmail").value = emailCookie;
				document.getElementById("myPassword").value = passwordCookie;
			}

			if (rememberMeCookie === "true") {
				document.getElementById("remember_me").checked = true;
			}
		}

		function getCookie(name) {
			var value = "; " + document.cookie;
			var parts = value.split("; " + name + "=");
			if (parts.length == 2)
				return parts.pop().split(";").shift();
		}
	</script>
</body>
</html>