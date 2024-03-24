<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Reset Password Page</title>
<link rel="stylesheet" href="css/forgetpassword.css" />
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="author" content="Yinka Enoch Adedokun" />
<meta name="description"
	content="Simple Forgot Password Page Using HTML and CSS" />
<meta name="keywords" content="forgot password page, basic html and css" />

</head>
<body>
	<div class="row">
		<h6 class="information-text">Réinitialiser votre mot de passe.</h6>
		<div class="form-group">
			<form method="post" action="./ResetPassword">
				<input type="hidden" name="email" value="${param.email}" /> <input
					type="password" name="password" id="password" required />
				<p>
					<label for="password">Mot de passe</label>
				</p>
				<input type="password" name="confirmPassword" id="confirmPassword"
					required />
				<p>
					<label for="confirmPassword">Confirmer le mot de passe</label><span
						id='message'></span>
				</p>
				<button type="submit" id="submitButton">Continuer</button>
			</form>
		</div>
	</div>
	<div style="color: red;">${param.error}</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
					$('#submitButton').prop('disabled', true);
					$('#message').html('le mot de passe ne correspond pas')
					.css('color', 'red');
			$('input[type="password"]').keyup(function() {
				if (($('#password').val() == $('#confirmPassword').val()) && ($('#password').val() != "" && $('#confirmPassword').val() != "")) {
					$('#submitButton').prop('disabled', false);
					$('#message').html('Le mot de passe correspond').css(
							'color', 'green');
				} else {
					$('#submitButton').prop('disabled', true);
					$('#message').html('le mot de passe ne correspond pas').css('color', 'red');
				}
			});
		});
	</script>
</body>
</html>