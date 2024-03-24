<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="com.services.implement.*,com.services.*,com.beans.*,java.util.*,javax.servlet.ServletOutputStream,java.io.*"%>
<c:import url="header.jsp" />
<main class='snippet-body'>
	<div class="head-section">
		<div class="d-flex justify-content-center row">
			<div class="col-md-8">
				<div class="p-2">
					<button type="button" class="btn penguin-btn" id="form-open-order">+address</button>
				</div>
				<!-- partial:index.partial.html -->
				<form class="formCheck" action="./OrderUserServlets?sendOrder=up"
					method="post">
					<h2>Choisir un addresse de livraison</h2>
					<c:forEach items="${addressList}" var="address">
						<div class="inputGroup">
							<input name="radio" id="radio${address.addressId }" type="radio"
								value="${address.addressId }" required /> <label
								for="radio${address.addressId }">${address.addressStreet },
								${ address.addressCity}</label>
						</div>
					</c:forEach>

					<div>
						<label for="bankily">Destinataire</label> <input id="bankily"
							name="bankily" type="text" maxlength="12" style="width: 100%;"
							required placeholder="Téléphone du destinataire"
							value="${sessionScope.userdata.customerPhone }" />
					</div>

					<div>
						<label for="pricedelivery">Service de livraison</label> <input
							id="pricedelivery" name="pricedelivery" type="text"
							maxlength="12" style="width: 100%;" value="2000 MRU" readonly />
					</div>
					<div>
						<label for="requiredDate">Date requise</label> <input
							id="requiredDate" name="requiredDate" type="date" required
							style="width: 100%;" />
					</div>
					<div
						class="d-flex flex-row align-items-center mt-3 p-2 bg-white rounded">
						<button type="submit"
							class="btn penguin-btn btn-block btn-lg ml-2 pay-button"
							style="width: 100%;">Effectuez la commande</button>
					</div>
				</form>
				<!-- partial -->
			</div>
		</div>
	</div>
</main>
<!-- Modal -->
<div class="modal" tabindex="-1" role="dialog" id="addressModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Add Address</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<form method="post" action="./AddAdressServlet">
				<div class="modal-body">
					<input type="hidden" name="totalPrice" value="${param.totalPrice}" />
					<input type="hidden" name="discount" value="${param.discount}" />
					<input type="hidden" name="cartCount" value="${param.cartCount}" />
					<div class="form-group">
						<label for="address1">Adresse (Rue, Numero du maison)</label> <input
							type="text" name="street" class="form-control" id="address1" />
					</div>
					<div class="form-group">
						<label for="address2">Ville </label> <input type="text"
							name="city" class="form-control" id="address2" />
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn penguin-btn">Enregistrer</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- Inclure Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- Script pour ouvrir le modal -->
<script>
	// Attend que le document soit prêt
	$(document).ready(function() {
		$('#form-open-order').click(function() {
			$('#addressModal').modal('show');
		});

		// Gérer manuellement le clic sur le bouton de fermeture
		$('.modal-header .close').click(function() {
			$('#addressModal').modal('hide');
		});
	});
</script>
<!-- Form Authentication -->
<c:import url="form.jsp" />
<script src="js/bootstrap.min.js"></script>
<!--Footer section-->
<c:import url="footer.jsp" />

</body>
</html>