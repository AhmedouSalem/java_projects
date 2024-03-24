<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="com.services.implement.*,com.services.*,com.beans.*,java.util.*,javax.servlet.ServletOutputStream,java.io.*"%>
<c:import url="header.jsp" />
<div style="margin: 40px;">
	<h2>Tabs</h2>
	<p>Mes commandes :</p>

	<div class="tab">
		<button id="defaultOpen" class="tablinks"
			onclick="openCity(event, 'pending')">En attente</button>
		<button class="tablinks" onclick="openCity(event, 'rejected')">Rejetée</button>
		<button class="tablinks" onclick="openCity(event, 'shipped')">Livrée</button>
	</div>

	<div id=pending " class="tabcontent">
		<h3>En attente</h3>
		<table id="pendingTable" class="display">
			<thead>
				<tr>
					<th>Numéro commande</th>
					<th>Date réquit de livrasion</th>
					<th>% réduction</th>
					<th>Prix total</th>
					<th>Tel réceveur</th>
					<th>Date de la commande</th>
					<th>Prix de livraison</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="order" items="${ordersList}">
					<c:if test="${order.orderStatus == 0}">
						<tr>
							<td>${order.orderId}</td>
							<td>${order.requiredDate}</td>
							<td>${order.discount}</td>
							<td>${order.totalPrice}</td>
							<td>${order.receiver}</td>
							<td>${order.orderDate}</td>
							<td>${order.orderPriceDelivery}</td>
							<td>
								<button type="button" class="btn btn-info"
									onclick="openModal(${order.orderId})">Voir détails</button>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div id="rejected" class="tabcontent">
		<h3>Rejetée</h3>
		<table id="rejectedTable" class="display">
			<thead>
				<tr>
					<th>Numéro commande</th>
					<th>Date réquit de livrasion</th>
					<th>% réduction</th>
					<th>Prix total</th>
					<th>Tel réceveur</th>
					<th>Date de la commande</th>
					<th>Prix de livraison</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="order" items="${ordersList}">
					<c:if test="${order.orderStatus == -1}">
						<tr>
							<td>${order.orderId}</td>
							<td>${order.requiredDate}</td>
							<td>${order.discount}</td>
							<td>${order.totalPrice}</td>
							<td>${order.receiver}</td>
							<td>${order.orderDate}</td>
							<td>${order.orderPriceDelivery}</td>
							<td>
								<button type="button" class="btn btn-info"
									onclick="openModal(${order.orderId})">Voir détails</button>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div id="shipped" class="tabcontent">
		<h3>Livrée</h3>
		<table id="shippedTable" class="display">
			<thead>
				<tr>
					<th>Numéro commande</th>
					<th>Date réquit de livrasion</th>
					<th>% réduction</th>
					<th>Prix total</th>
					<th>Tel réceveur</th>
					<th>Date de la commande</th>
					<th>Prix de livraison</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="order" items="${ordersList}">
					<c:if test="${order.orderStatus == 1}">
						<tr>
							<td>${order.orderId}</td>
							<td>${order.requiredDate}</td>
							<td>${order.discount}</td>
							<td>${order.totalPrice}</td>
							<td>${order.receiver}</td>
							<td>${order.orderDate}</td>
							<td>${order.orderPriceDelivery}</td>
							<td>
								<button type="button" class="btn btn-info"
									onclick="openModal(${order.orderId})">Voir détails</button>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<!-- Modal -->
<div class="modal fade" id="orderModal" tabindex="-1" role="dialog"
	aria-labelledby="orderModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="orderModalLabel">Détails de la
					commande</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" id="orderDetails">
				<!-- Les détails de la commande seront affichés ici -->
			</div>
		</div>
	</div>
</div>

<script src="js/myorders.js"></script>
<!-- Form Authentication -->
<c:import url="form.jsp" />
<script src="js/bootstrap.min.js"></script>
<!--Footer section-->
<c:import url="footer.jsp" />

</body>
</html>
