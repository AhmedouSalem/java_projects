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
					<h4>Panier</h4>
				</div>
				<c:forEach items="${cartList }" var="product">
					<div
						class="d-flex flex-row justify-content-between align-items-center p-2 bg-white mt-4 px-3 rounded">
						<div class="mr-1">
							<img class="rounded" src="./ShowImage?pid=${product.productId}"
								width="70">
						</div>
						<div class="d-flex flex-column align-items-center product-details">
							<span class="font-weight-bold">${product.productNameFr }</span>
							<div class="d-flex flex-row product-desc">
								<div class="color">
									<span class="text-grey">${product.brandNameFr }</span>
								</div>
							</div>
						</div>
						<div class="d-flex flex-row align-items-center qty">
							<a href="./ViewCart?decrementProduct=${product.productId }"><i
								class="fa fa-minus text-danger"></i></a>
							<h5 class="text-grey mt-1 mr-1 ml-1"
								style="margin-left: 10px; margin-right: 10px;">${product.quantity }</h5>
							<a href="./ViewCart?incrementProduct=${product.productId }"><i
								class="fa fa-plus text-success"></i></a>
						</div>
						<div>
							<h5 class="text-grey">${product.listPrice} MRU</h5>
						</div>
						<div class="d-flex align-items-center">
							<a href="./ViewCart?deleteProduct=${product.productId }"> <i
								class="fa fa-trash mb-1 text-danger"></i>
							</a>
						</div>
					</div>
				</c:forEach>
				<c:choose>
					<c:when test="${empty cartList }">
						<div
							style="text-align: center; height: 400px; vertical-align: middle; line-height: 400px; font-weight: bold;">
							Votre panier est vide...</div>
					</c:when>
					<c:otherwise>
						<div class="p-2">
							<div class="d-flex flex-row align-items-center pull-right">
								<span class="mr-1">Prix total :</span><span
									class="mr-1 font-weight-bold"> ${totalPrice} MRU</span>
							</div>
						</div>
						<form method="post">
							<div
								class="d-flex flex-row align-items-center mt-3 p-2 bg-white rounded">

								<input type="text" name="coupon_discount" value="${initValue}"
									class="form-control border-0 gift-card"
									placeholder="code de réduction" ${isDisabled}>
								<button class="btn btn-sm ml-2 penguin-btn" type="submit"
									formaction="./ViewCart" ${isDisabled}>Appliquer</button>
							</div>
							<c:choose>
								<c:when test="${sessionScope.userdata.solde < totalPrice}">
									<p><i>Vous ne pouvez pas passer le commande car votre solde "${sessionScope.userdata.solde } MRU"  est insuffisant</i></p>
									<div
										class="d-flex flex-row align-items-center mt-3 p-2 bg-white rounded">
										<button
											class="btn penguin-btn btn-block btn-lg ml-2 pay-button"
											type="submit"
											formaction="./OrderUserServlets?totalPrice= ${totalPrice}&discount=${discount}&cartCount=${cartCount}" disabled="disabled">Passer
											la commande</button>
									</div>
								</c:when>
								<c:otherwise>
									<div
										class="d-flex flex-row align-items-center mt-3 p-2 bg-white rounded">
										<button
											class="btn penguin-btn btn-block btn-lg ml-2 pay-button"
											type="submit"
											formaction="./OrderUserServlets?totalPrice= ${totalPrice}&discount=${discount}&cartCount=${cartCount}">Passer
											la commande</button>
									</div>
								</c:otherwise>
							</c:choose>
						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</main>
<!-- Form Authentication -->
<c:import url="form.jsp" />
<script src="js/bootstrap.min.js"></script>
<!--Footer section-->
<c:import url="footer.jsp" />
<script type='text/javascript'
	src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js'></script>
<script type='text/javascript'
	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<script type='text/javascript'>
	var myLink = document.querySelector('a[href="#"]');
	myLink.addEventListener('click', function(e) {
		e.preventDefault();
	});
</script>

</body>

</html>