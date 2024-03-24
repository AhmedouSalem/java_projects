<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="com.services.implement.*,com.services.*,com.beans.*,java.util.*,javax.servlet.ServletOutputStream,java.io.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Winter Fashion</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="preconnect" href="https://fonts.gstatic.com" />
<link
	href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<!-- <link href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css' rel='stylesheet'> -->
<link href="https://fonts.googleapis.com/css?family=Fira+Sans"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet" href="css/addresspagestyle.css">

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">

<script type="text/javascript" charset="utf8"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/plug-ins/1.11.5/i18n/French.json"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/store.css" />
</head>
<body onload="showSnackbar()" class="home">
	<!--Nav bar-->
	<section class="head-section">
		<header class="container">
			<nav class="navbar navbar-expand-lg navbar-light penguin-nabbar">
				<div class="container-fluid">
					<a class="navbar-brand" href="#"> <img src="Images/welcome.png"
						alt="" class="penguin-logo img-fluid"
						style="background-color: #f3f3f3;" />
					</a>
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarNav"
						aria-controls="navbarNav" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse justify-content-end"
						id="navbarNav">
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link active"
								aria-current="page" href="./ProductsView">Acceuil</a></li>
							<li class="nav-item"><a class="nav-link" href="#products">Produits</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="#about-us">à
									propos</a></li>
							<c:if test="${not empty sessionScope.isRegistred }">
								<li class="nav-item"><a class="nav-link"
									href="account.jsp?cartCount=${cartCount}">Mon compte</a></li>
							</c:if>
							<!-- Utilisation de Bootstrap pour superposer le badge sur l'icône panier -->
							<li class="nav-item"><a class="nav-link"
								href="./AddToCart?redirect=goToCart&cartCount=${not empty cartCount ? cartCount : param.cartCount}">Panier<span
									class="position-relative"> <!-- Icône du panier --> <i
										class="fa fa-shopping-cart"></i> <!-- Badge pour afficher le nombre de produits -->
										<span
										class="badge rounded-pill bg-danger position-absolute top-0 start-100 translate-middle">
											${not empty cartCount ? cartCount : param.cartCount} <!-- Remplacez ce nombre par le nombre réel de produits -->
									</span>
								</span>
							</a></li>
							<c:if test="${empty sessionScope.isRegistred}">
								<li class="nav-item"><a class="nav-link" type="button"
									id="form-open"> Se connecter </a></li>
							</c:if>
							<c:if test="${not empty sessionScope.isRegistred}">
								<li class="nav-item"><a class="nav-link"
									href="./LogoutServlet" onclick="myFunction()"> Se
										déconnecter </a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</nav>
		</header>
	</section>