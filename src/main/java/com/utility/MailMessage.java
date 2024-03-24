package com.utility;

import javax.mail.MessagingException;

public class MailMessage {
	public static void registrationSuccess(String emailId, String name, int verifyCode,String subject) {
		String recipient = emailId;
		String htmlTextMessage = "" + "<html>" + "<body>"
				+ "<h2 style='color:green;'>Bienvenue sur java market place</h2>" + "" + "Bonjour" + name + ","
				+"<br><br>void votre code de verification :<b> " + verifyCode + "</b> veuilleiz approuver votre compte afin d'y accéder,"
				+ "<br><br>Merci pour votre inscription sur notre market place<br>"
				+ "Nous sommes heureux que vous nous choisissiez. Nous vous invitons à découvrir notre dernière collection de nouveaux appareils électroniques."
				+ "<br>Nous offrons jusqu'à 60 % de réduction sur la plupart des gadgets électroniques. Alors s'il vous plaît visitez notre site et explorez les collections."
				+ "<br><br>Notre électronique en ligne se développe de plus en plus ces jours-ci et nous sommes très demandés, nous vous remercions donc tous pour "
				+ "nous faisant atteindre ce niveau. Nous livrons le produit à votre domicile et nous collectons également la plupart des"
				+ "articles de marque.<br><br>En guise de cadeau de bienvenue pour nos nouveaux clients, nous offrons 20 % de réduction supplémentaire jusqu'à 500 Rs pour le premier achat de produit."
				+ "<br>Pour bénéficier de cette offre, vous devez uniquement"
				+ "pour saisir le code promotionnel indiqué ci-dessous.<br><br><br> CODE PROMO :" + "Black Friday<br><br><br>"
				+ "Passez une bonne journée!<br>" + "" + "</body>" + "</html>";
		try {
			JavaMailUtil.sendMail(recipient, subject, htmlTextMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void transactionSuccess(String recipientEmail, String name, String transId, double transAmount) {
		String recipient = recipientEmail;
		String subject = "Commande passée chez market place";
		String htmlTextMessage = "<html>" + "  <body>" + "    <p>" + "      Bonjour " + name + ",<br/><br/>"
				+ "      Nous sommes heureux que vous fassiez vos achats chez market place !" + "      <br/><br/>"
				+ "      Votre commande a été passée avec succès et est en cours d'expédition."
				+ "<br/><h6>Veuillez noter qu'il s'agit d'un e-mail de projet de démonstration et que vous n'avez effectué aucune transaction réelle avec nous jusqu'à présent !</h6>"
				+ "      <br/>" + "      Voici les détails de votre transaction :<br/>" + "      <br/>"
				+ "      <font style=\"color:red;font-weight:bold;\">Numéro de commande:</font>"
				+ "      <font style=\"color:green;font-weight:bold;\">" + transId + "</font><br/>" + "      <br/>"
				+ "      <font style=\"color:red;font-weight:bold;\">Le montant à payer :</font> <font style=\"color:green;font-weight:bold;\">"
				+ transAmount + " MRU</font>" + "      <br/><br/>" + "      Merci d'avoir acheté chez nous!<br/><br/>"
				+ "      Veneze acheter à nouveau! <br/<br/> <font style=\"color:green;font-weight:bold;\">Market place.</font>"
				+ "    </p>" + "    " + "  </body>" + "</html>";

		try {
			JavaMailUtil.sendMail(recipient, subject, htmlTextMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void orderShipped(String recipientEmail, String name, String transId, double transAmount) {
		String recipient = recipientEmail;
		String subject = "Holla !!, votre commande a été expédiée de Market Place";
		String htmlTextMessage = "<html>" + "  <body>" + "    <p>" + "      Hey " + name + ",<br/><br/>"
				+ "      Nous sommes heureux que vous fassiez vos achats chez Market place !" + "      <br/><br/>"
				+ "      Votre commande a été expédiée avec succès et est en passe d'être livrée."
				+ "<br/><h6>Veuillez noter qu'il s'agit d'un e-mail de projet de démonstration et que vous n'avez effectué aucune transaction réelle avec nous jusqu'à présent !</h6>"
				+ "      <br/>" + "      Voici les détails de votre transaction :<br/>" + "      <br/>"
				+ "      <font style=\"color:red;font-weight:bold;\">Numéro de commande:</font>"
				+ "      <font style=\"color:green;font-weight:bold;\">" + transId + "</font><br/>" + "      <br/>"
				+ "      <font style=\"color:red;font-weight:bold;\">Le montant payé:</font> <font style=\"color:green;font-weight:bold;\">"
				+ transAmount + "</font>" + "      <br/><br/>" + "      Merci d'avoir acheté chez nous!<br/><br/>"
				+ "      Venez magasiner à nouveau! <br/<br/> <font style=\"color:green;font-weight:bold;\">Market Place.</font>"
				+ "    </p>" + "    " + "  </body>" + "</html>";

		try {
			JavaMailUtil.sendMail(recipient, subject, htmlTextMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	

	public static String sendMessage(String toEmailId, String subject, String htmlTextMessage) {
		try {
			JavaMailUtil.sendMail(toEmailId, subject, htmlTextMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Echec";
		}
		return "Succès";
	}
}
