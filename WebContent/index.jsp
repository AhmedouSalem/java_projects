<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="com.services.implement.*,com.services.*,com.beans.*,java.util.*,javax.servlet.ServletOutputStream,java.io.*"%>

<c:import url="header.jsp" />
<main>
	<!--Banner section-->
	<section class="head-section">
		<div class="pt-2 pb-5 container">
			<div class="row d-flex align-items-center">
				<div class="col-md-6 px-3">
					<h1 id="text-style">Faire du shopping</h1>
					<h1 class="text-style2">rapidement, facilement et commodément</h1>
					<p>Bénneficiez de nos réductions, abbonnez-vous pour avoir le
						dont d'acceuil (un promo code de x%)</p>
					<!-- <button type="button" class="btn penguin-btn button"
							id="form-open">
							<i class="fa fa-shopping-cart"></i> BUY NOW
						</button> -->
				</div>
				<div class="col-md-6 how-img px-5">
					<img src="Images/logomarketplace.png" class="img-fluid" alt="" />
				</div>
			</div>
		</div>
	</section>

	<!--Woman collection section-->
	<section id="products" class="container mt-5">
		<div class="row row-cols-1 row-cols-md-3 g-4 mt-3">
			<c:forEach items="${productList}" var="product">
				<div class="col">
					<div class="card h-100 penguin-card-border shadow rounded">
						<img src="./ShowImage?pid=${product.productId}"
							class="card-img-top penguin-card-img w-75" alt="..."
							style="object-fit: contain; height: 100%;" />
						<div class="card-body">
							<h5 class="card-title">${product.productNameFr}</h5>
							<p class="card-text">${product.prodBrand}</p>
						</div>
						<div
							class="card-footer d-flex justify-content-between align-items-center penguin-card-footer">
							<div>
								<h3 class="price-text-style">${product.listPrice}</h3>
							</div>
							<div>
								<c:choose>
									<c:when
										test="${not empty sessionScope.customerID && not empty sessionScope.isRegistred}">
										<form method="post">
											<button type="submit" class="btn penguin-btn"
												formaction="./AddToCart?customerID=${sessionScope.customerID}&productID=${product.productId}&pqty=1">
												<i class="fa fa-shopping-cart"></i> Acheter maintenant
											</button>
										</form>
									</c:when>
									<c:otherwise>
										<button type="button" class="btn penguin-btn"
											id="form-open-buy-now">
											<i class="fa fa-shopping-cart"></i> Acheter maintenant
										</button>
									</c:otherwise>
								</c:choose>

							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>

	<!--Some information section-->
	<section class="container mt-5 pt-5">
		<div class="pt-2 pb-5 container">
			<div
				class="row d-flex align-items-center flex-column-reverse flex-lg-row">
				<div class="col-md-6 px-3">
					<div class="card mb-3 penguin-card-border shadow"
						style="max-width: 540px;">
						<div class="row g-0">
							<div class="col-md-3">
								<img src="icon/image 12.png" class="w-50 penguin-info" alt="..." />
							</div>
							<div class="col-md-9">
								<div class="card-body">
									<h5 class="card-title">Trouvez la solution idéale</h5>
									<p class="card-text">Tout le monde est différent, c'est
										pourquoi nous proposons des produits pour tous les goûts.</p>
								</div>
							</div>
						</div>
					</div>
					<div class="card mb-3 penguin-card-border shadow"
						style="max-width: 540px;">
						<div class="row g-0">
							<div class="col-md-3">
								<img src="icon/image 13.png" class="w-50 penguin-info" alt="..." />
							</div>
							<div class="col-md-9">
								<div class="card-body">
									<h5 class="card-title">Échanges moins cher et gratuites
										sur place</h5>
									<p class="card-text">Une des nombreuses raisons pour
										lesquelles vous pouvez magasiner en toute sérénité.</p>
								</div>
							</div>
						</div>
					</div>
					<div class="card mb-3 penguin-card-border shadow"
						style="max-width: 540px;">
						<div class="row g-0">
							<div class="col-md-3">
								<img src="icon/image 14.png" class="w-50 penguin-info" alt="..." />
							</div>
							<div class="col-md-9">
								<div class="card-body">
									<h5 class="card-title">Contactez nos vendeurs</h5>
									<p class="card-text">Ils sont là pour vous aider.</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 how-img px-5">
					<img src="icon/XMLID 1.png" class="img-fluid" alt="" />
				</div>
			</div>
		</div>
	</section>
</main>


<!-- Form Authentication -->
<c:import url="form.jsp" />
<script src="js/bootstrap.min.js"></script>
<!--Footer section-->
<c:import url="footer.jsp" />
</body>
</html>
