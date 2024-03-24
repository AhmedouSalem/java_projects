<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="com.services.implement.*,com.services.*,com.beans.*,java.util.*,javax.servlet.ServletOutputStream,java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="preconnect" href="https://fonts.gstatic.com" />
<link
	href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body>
	<section class="footer-section">
		<div class="container pb-5">
			<div class="row">
				<div id="about-us" class="col-sm pe-5 pt-3">
					<img src="Images/welcome.png" alt="" class="penguin-logo img-fluid"
						style="background-color: #f3f3f3;" />
					<hr />
					<p>Changer la façon dont vous vous habillez changera la façon
						dont vous vous sentez. Lorsque vous êtes bien habillé et que vous
						avez fière allure, vous serez automatiquement se sentir mieux.
						Lorsque vous vous sentez bien dans votre peau, vous êtes plus
						susceptible de se sentir bien à l'intérieur, de mieux traiter les
						autres et d'avoir plus énergie.</p>
				</div>
				<div id="contact-us" class="col-sm pe-5 pt-3">
					<h5>Contact us:</h5>
					<hr />
					<p>
						<i class="fa fa-map-marker fa-1x"> </i>Rue d'indépendance, lot
						LKSAR, Nouakchott-Nord.
					</p>
					<p>
						<i class="fa fa-envelope-open"></i> ahmedou@gmail.com
					</p>
					<p>
						<i class="fa fa-phone"></i> +22249744757
					</p>
				</div>
				<div class="col-sm pe-5 pt-3">
					<div>
						<h5>Entrer en contact</h5>
						<hr />
						<i class="fa fa-facebook-square fa-2x pe-3"> </i> <i
							class="fa fa-twitter-square fa-2x pe-3"></i> <i
							class="fa fa-linkedin fa-2x pe-3"></i>
					</div>
					<div class="pt-5">
						<h5>Payer avec</h5>
						<hr />
						<img src="icon/pay_with.png" style="background-color: grey;" class="w-75" alt="" />
					</div>
				</div>
			</div>
		</div>
		<footer>
			<hr />
			<p class="text-center">©Groupe java</p>
		</footer>
	</section>
	<!-- Snackbar -->
	<div id="snackbar" class="snackbar">
		${message} <span class="snackbar-close" onclick="closeSnackbar()">&times;</span>
	</div>

	<!--  /****************************************************************************************/ -->

	<!-- JavaScript pour afficher et fermer le Snackbar -->
	<script>
		// Fonction pour afficher le Snackbar
		function showSnackbar() {
			var snackbar = document.getElementById("snackbar");
			snackbar.style.visibility = "visible";
			// Cacher le Snackbar après 3 secondes
			setTimeout(function() {
				snackbar.style.visibility = "hidden";
			}, 3000);
		}

		// Fonction pour fermer le Snackbar
		function closeSnackbar() {
			var snackbar = document.getElementById("snackbar");
			snackbar.style.visibility = "hidden";
		}
	</script>
</body>
</html>