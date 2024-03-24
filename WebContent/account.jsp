<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="com.services.implement.*,com.services.*,com.beans.*,java.util.*,javax.servlet.ServletOutputStream,java.io.*"%>
<c:import url="header.jsp" />
<div class="container mt-5">
	<h1>Bonjour ${sessionScope.userdata.customerName }</h1>
	<div class="row">
		<div class="col-md-4 card-account" onclick="redirectToOrders()">
			<div class="card p-3">
				<div class="d-flex flex-row mb-3">
					<img src="./Images/commande.png" width="70">
					<div class="d-flex flex-column ml-2">
						<span>Vos commandes</span><span class="text-black-50">Suivre,
							retourner, annuler ou acheter à nouveau en toute conformité 
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-4 card-account">
			<div class="card p-3">
				<div class="d-flex flex-row mb-3">
					<img src="./Images/address.png" width="70">
					<div class="d-flex flex-column ml-2">
						<span>Vos addresses</span><span class="text-black-50">
							Modifier les address et les préferences de livraison des
							commandes et des cadeaux 
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-4 card-account" onclick="redirectToStores()">
			<div class="card p-3">
				<div class="d-flex flex-row mb-3">
					<img src="./Images/stores.png" width="70">
					<div class="d-flex flex-column ml-2">
						<span>Lancez votre magasin</span><span class="text-black-50">
							Devenir vendeur et atteindre vos objectifs commerciaux à travers
							nos services exceptionnelle 
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-4 card-account">
			<div class="card p-3">
				<div class="d-flex flex-row mb-3">
					<img src="./Images/payment.png" width="70">
					<div class="d-flex flex-column ml-2">
						<span>Vos paiements</span><span class="text-black-50">
							Afficher toutes les transactions 
					</div>
				</div>
			</div>
		</div>
		<div style="height: 20px;"></div>
	</div>
</div>
<!-- Form Authentication -->
<c:import url="form.jsp" />
<script src="js/bootstrap.min.js"></script>
<!--Footer section-->
<c:import url="footer.jsp" />
<script type="text/javascript">
	function redirectToStores() {
		window.location.href = "./index.st";
	}
	function redirectToOrders() {
		window.location.href = "./MyOrders";
	}
</script>
</body>
</html>