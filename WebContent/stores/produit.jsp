<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="com.services.*,com.services.implement.*,com.beans.*,java.util.*,
javax.servlet.ServletOutputStream,java.io.*"%>
<c:import url="../header.jsp" />
<div style="margin: 40px;">
	<%@include file="header.jsp"%>
	<p></p>
	<div class="panel panel-primary  ">
		<div class="panel-heading">
			<h3>Produits</h3>
			<!-- Bouton "Ajouter des stores" -->
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#addItemModal">Ajouter des items</button>
		</div>
		<div class="panel-body">
			<div>
				<form action="chercherProd.st" method="get">
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
								<th>Identifiant</th>
								<th>Nom du Produit</th>
								<th>Ann�e du Mod�le</th>
								<th>Prix</th>
								<th>Marque</th>
								<th>Cat�gorie</th>
								<th>Image</th>
								<th>Action</th>
							</tr>
						</thead>

						<tbody id="data">
							<c:forEach var="product" items="${products}">
								<tr>
									<td>${product.productId}</td>
									<td>${product.productNameFr}</td>
									<td>${product.modelYear}</td>
									<td>${product.listPrice}</td>
									<td>${product.prodBrand}</td>
									<td>${product.prodCategory}</td>
									<td><img src="./ShowImage?pid=${product.productId}"
										alt="Image du Produit" width="50"></td>
									<td><a href="#" onclick="fillModal(${product.productId})"
										data-toggle="modal" data-target="#EditItemModal">
											<button type="button" class="btn btn-warning">Edit</button>
									</a> <a href="supprimerProd.st?ID=${product.productId}"
										onclick="showSnackbar()">
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
				<form action="saveProduit.st" method="POST"
					enctype="multipart/form-data">
					<div class="form-group">
						<label for="itemName">Nom En Francais:</label> <input type="text"
							name="productNameFr" class="form-control" id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">product Image</label> <input type="file"
							name="prodImage" class="form-control" id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">modelYear :</label> <input type="number"
							name="modelYear" class="form-control" id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">listPrice :</label> <input type="text"
							name="listPrice" class="form-control" id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">brand_id :</label> <input type="number"
							name="brandId" class="form-control" id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">category_id :</label> <input type="number"
							name="categoryId" class="form-control" id="itemName">
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
				<form action="modifierProduit.st" method="POST"
					enctype="multipart/form-data">
					<div class="form-group">
						<label for="itemName">Id of Product:</label> <input type="number"
							name="productId" value="${prod.productId}" class="form-control"
							id="itemName">
					</div>


					<div class="form-group">
						<label for="itemName">Nom En Francais:</label> <input type="text"
							name="productNameFr" value="${prod.productNameFr}"
							class="form-control" id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">product Image</label> <input type="file"
							name="prodImage" value="${prod.prodImage}" class="form-control"
							id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">modelYear :</label> <input type="number"
							name="modelYear" value="${prod.modelYear}" class="form-control"
							id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">listPrice :</label> <input type="text"
							name="listPrice" value="${prod.listPrice}" class="form-control"
							id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">brand_id :</label> <input type="number"
							name="brandId" value="${prod.brandId}" class="form-control"
							id="itemName">
					</div>

					<div class="form-group">
						<label for="itemName">category_id :</label> <input type="number"
							name="categoryId" value="${prod.categoryId}" class="form-control"
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


<div id="snackbar">Element supprim� avec succ�s!</div>
<script>
			function showSnackbar() {
			  var snackbar = document.getElementById("snackbar");
			  snackbar.className = "show";
			  setTimeout(function(){ snackbar.className = snackbar.className.replace("show", ""); }, 6000);
			}
			
			function fillModal(productId) {
			    // Utilisez AJAX pour r�cup�rer les donn�es du magasin en fonction de son ID
			    // Dans cet exemple, j'utilise jQuery pour la simplicit�, assurez-vous d'avoir inclus jQuery dans votre page

			    $.ajax({
			        url: 'EditProd.st',
			        type: 'GET',
			        data: { ID: productId },
			        success: function(data) {
			            // Remplissez les champs du formulaire dans le modal avec les donn�es re�ues
			        	$('#EditItemModal input[name="productId"]').val(data.productId);
			            $('#EditItemModal input[name="productNameFr"]').val(data.productNameFr);
			            $('#EditItemModal input[name="modelYear"]').val(data.modelYear);
			            $('#EditItemModal input[name="listPrice"]').val(data.listPrice);
			            $('#EditItemModal input[name="brandId"]').val(data.brandId);
			            $('#EditItemModal input[name="categoryId"]').val(data.categoryId);
			        },
			        error: function(xhr, status, error) {
			            console.error('Erreur lors de la r�cup�ration des donn�es du magasin: ' + error);
			        }
			    });
			}
		</script>
<!--Footer section-->
<c:import url="../footer.jsp" />
</body>
</html>