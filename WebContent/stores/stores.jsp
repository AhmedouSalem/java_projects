<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="com.services.*,com.services.implement.*,com.beans.*,java.util.*,
javax.servlet.ServletOutputStream,java.io.*"%>
<c:import url="../header.jsp" />
<div style="margin: 40px">
	<%@include file="header.jsp"%>
	<p></p>
	<div class="panel panel-primary  ">
		<div class="panel-heading">
			<h3>Stores</h3>
			<!-- Bouton "Ajouter des stores" -->
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#addItemModal">Ajouter des items</button>
		</div>
		<div class="panel-body">
			<div>
				<form action="chercher.st" method="get">
					<label> Rechercher </label> <input type="text" name="motCle">
					<button type="submit" class="btn btn-primary">Chercher</button>
				</form>
			</div>

			<div class="row">
				<div class="col-12">
					<table
						class="table table-striped table-hover mt-3 text-center table-bordered table-responsive"
						id="myDataTable">

						<thead class="bg-primary text-white">
							<tr>
								<th>Id</th>
								<th>store Name Ar</th>
								<th>store Name Fr</th>
								<th>Phone</th>
								<th>Email</th>
								<th>Street</th>
								<th>City</th>
								<th>Stores Active</th>
								<th>Credit</th>
								<th>Action</th>
							</tr>
						</thead>

						<tbody id="data">
							<c:forEach items="${stores}" var="store">
								<tr>
									<td>${store.ID}</td>
									<td>${store.storeNameAr}</td>
									<td>${store.storeNameFr}</td>
									<td>${store.phone}</td>
									<td>${store.email}</td>
									<td>${store.street}</td>
									<td>${store.city}</td>
									<td>${store.storesActive}</td>
									<td>${store.credit}</td>
									<td><a href="#" onclick="fillModal(${store.ID})"
										data-toggle="modal" data-target="#EditItemModal">
											<button type="button" class="btn btn-warning">Edit</button>
									</a> <a href="supprimer.st?ID=${store.ID}" onclick="showSnackbar()">
											<button type="button" class="btn btn-danger">
												Supprimer</button>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>

					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Modal pour ajouter des stores -->
<div class="modal fade" id="addItemModal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Ajouter un nouvel store</h4>
			</div>
			<div class="modal-body">
				<!-- Formulaire pour ajouter un nouvel item -->
				<form action="saveStore.st" method="POST">
					<div class="form-group">
						<label for="itemName">Nom En Francais:</label> <input type="text"
							name="storeNameFr" class="form-control" id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">Nom En Arabe</label> <input type="text"
							name="storeNameAr" class="form-control" id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">Phone :</label> <input type="text"
							name="phone" class="form-control" id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">Email :</label> <input type="text"
							name="email" class="form-control" id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">Street :</label> <input type="text"
							name="street" class="form-control" id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">City :</label> <input type="text"
							name="city" class="form-control" id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">stores Active :</label> <input type="text"
							name="storesActive" class="form-control" id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">Credit :</label> <input type="text"
							name="credit" class="form-control" id="itemName">
					</div>


					<button type="submit" class="btn btn-default">Ajouter</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
			</div>
		</div>
	</div>
</div>

<!-- Modal pour modifier des stores -->
<div class="modal fade" id="EditItemModal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Modification dans Store</h4>
			</div>
			<div class="modal-body">
				<!-- Formulaire pour ajouter un nouvel item -->
				<form action="EditStore.st" method="post">
					<div class="form-group">
						<label for="itemName">ID:</label> <input type="text" name="ID"
							value="${store.ID}" class="form-control" id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">Nom En Francais:</label> <input type="text"
							name="storeNameFr" value="${store.storeNameFr}"
							class="form-control" id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">Nom En Arabe</label> <input type="text"
							name="storeNameAr" value="${store.storeNameAr}"
							class="form-control" id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">Phone :</label> <input type="text"
							name="phone" value="${store.phone}" class="form-control"
							id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">Email :</label> <input type="text"
							name="email" value="${store.email}" class="form-control"
							id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">Street :</label> <input type="text"
							name="street" value="${store.street}" class="form-control"
							id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">City :</label> <input type="text"
							name="city" value="${store.city}" class="form-control"
							id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">stores Active :</label> <input type="text"
							name="storesActive " value="${store.storesActive}"
							class="form-control" id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">Credit :</label> <input type="text"
							name="credit" value="${store.credit}" class="form-control"
							id="itemName">
					</div>


					<button type="submit" class="btn btn-default">Modifier</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
			</div>
		</div>
	</div>
</div>



<!-- Form Authentication -->
<c:import url="../form.jsp" />
<script src="../js/bootstrap.min.js"></script>
<!--Footer section-->
<c:import url="../footer.jsp" />
<script>
			//function showSnackbar() {
			 // var snackbar = document.getElementById("snackbar");
			 // snackbar.className = "show";
			 // setTimeout(function(){ snackbar.className = snackbar.className.replace("show", ""); }, 6000);
			//}
			
			function fillModal(storeId) {
			    // Utilisez AJAX pour r�cup�rer les donn�es du magasin en fonction de son ID
			    // Dans cet exemple, j'utilise jQuery pour la simplicit�, assurez-vous d'avoir inclus jQuery dans votre page

			    $.ajax({
			        url: 'Edit.st',
			        type: 'GET',
			        data: { ID: storeId },
			        success: function(data) {
			            // Remplissez les champs du formulaire dans le modal avec les donn�es re�ues
			            $('#EditItemModal input[name="ID"]').val(data.ID);
			            $('#EditItemModal input[name="storeNameFr"]').val(data.storeNameFr);
			            $('#EditItemModal input[name="storeNameAr"]').val(data.storeNameAr);
			            $('#EditItemModal input[name="phone"]').val(data.phone);
			            $('#EditItemModal input[name="email"]').val(data.email);
			            $('#EditItemModal input[name="street"]').val(data.street);
			            $('#EditItemModal input[name="city"]').val(data.city);
			            $('#EditItemModal input[name="storesActive"]').val(data.storesActive);
			            $('#EditItemModal input[name="credit"]').val(data.credit);
			        },
			        error: function(xhr, status, error) {
			            console.error('Erreur lors de la r�cup�ration des donn�es du magasin: ' + error);
			        }
			    });
			}
		</script>
</body>
</html>