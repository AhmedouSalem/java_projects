/**
 * 
 */
// Fonction pour ouvrir une tab spécifique
function openCity(evt, cityName) {
	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}
	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace(" active", "");
	}
	document.getElementById(cityName).style.display = "block";
	evt.currentTarget.className += " active";
}
$(document)
	.ready(
		function() {
			$('.display')
				.DataTable(
					{
						language: {
							"sProcessing": "Traitement en cours...",
							"sSearch": "Rechercher&nbsp;:",
							"sLengthMenu": "Afficher _MENU_ &eacute;l&eacute;ments",
							"sInfo": "Affichage de l'&eacute;l&eacute;ment _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ments",
							"sInfoEmpty": "Affichage de l'&eacute;l&eacute;ment 0 &agrave; 0 sur 0 &eacute;l&eacute;ments",
							"sInfoFiltered": "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
							"sInfoPostFix": "",
							"sLoadingRecords": "Chargement en cours...",
							"sZeroRecords": "Aucun &eacute;l&eacute;ment &agrave; afficher",
							"sEmptyTable": "Aucune donn&eacute;e disponible dans le tableau",
							"oPaginate": {
								"sFirst": "Premier",
								"sPrevious": "Pr&eacute;c&eacute;dent",
								"sNext": "Suivant",
								"sLast": "Dernier"
							},
							"oAria": {
								"sSortAscending": ": activer pour trier la colonne par ordre croissant",
								"sSortDescending": ": activer pour trier la colonne par ordre d&eacute;croissant"
							},
							"select": {
								"rows": {
									"_": "%d lignes s&eacute;lectionn&eacute;es",
									"0": "Aucune ligne s&eacute;lectionn&eacute;e",
									"1": "1 ligne s&eacute;lectionn&eacute;e"
								}
							},
							"buttons": {
								"copy": "Copier",
								"colvis": "Visibilité"
							}
						}
					});
			$('#pendingTable').DataTable();
			// Initialisez d'autres tables de la même manière si nécessaire
			$('#rejectedTable').DataTable();
			$('#shippedTable').DataTable();
			// Définit la première tab active par défaut
			document.getElementById('defaultOpen').click();
		});
// Fonction pour ouvrir le modal et afficher les détails de la commande
function openModal(orderId) {
	// Vous devez implémenter une logique pour récupérer les détails de la commande en fonction de orderId
	// Supposons qu'il existe une fonction getOrderDetails(orderId) pour cela

	// Récupérer les détails de la commande
	var orderDetails = getOrderDetails(orderId);

	// Mettre à jour le contenu du modal avec les détails de la commande
	$('#orderDetails').html(orderDetails);

	// Afficher le modal
	$('#orderModal').modal('show');
}

// Fonction fictive pour récupérer les détails de la commande
function getOrderDetails(orderId) {
	// Vous devez implémenter la logique pour récupérer les détails de la commande du backend
	// Supposons qu'il existe une URL pour cela, par exemple, /getOrderDetails?orderId=orderId
	// Vous pouvez utiliser AJAX pour récupérer les détails de la commande

	// Exemple de requête AJAX fictive pour récupérer les détails de la commande
	// Cette fonction doit être remplacée par une vraie logique pour récupérer les détails de la commande
	var orderDetails = ''; // Initialisez les détails de la commande à une chaîne vide
	$.ajax({
		url: '/getOrderDetails?orderId=' + orderId,
		type: 'GET',
		async: false, // Attendre la réponse avant de continuer
		success: function(response) {
			// Mettre à jour les détails de la commande avec la réponse de la requête AJAX
			orderDetails = response;
		},
		error: function() {
			// Gérer les erreurs si nécessaire
			alert('Erreur lors de la récupération des détails de la commande');
		}
	});
	return orderDetails;
}