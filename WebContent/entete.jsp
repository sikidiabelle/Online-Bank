<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String title = (String) session.getAttribute("title_page");
	session.removeAttribute("title_page"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<!-- Inclusion des icones fontawesome -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	
	<title><%= title %></title>
</head>

<body style="background-color : #e9ecef;">

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<!-- Bouton accueil -->
			<a class="navbar-brand" href="index.jsp">
				<i class="fa fa-bank mr-1"></i>BANKTIC
			</a>
			
			<ul class="navbar-nav mr-auto">
				<!-- Bouton devenir client si client non connecté -->
				<% if (session.getAttribute("idClient")==null) { %>
				<li class="nav-item">
					<a class="nav-link" href="InscriptionClient.jsp">
						<i class="fa fa-handshake-o mr-1"></i>Devenir client
					</a>
				</li>
				<% } //End si user not connected %>
				
				<!-- Bouton nos offres -->
				<li class="nav-item">
					<a class="nav-link" href="Offres.jsp">
						<i class="fa fa-gift mr-1"></i>Nos offres
					</a>
				</li>
				
				<!-- Bouton la bourse -->
				<li class="nav-item">
					<a class="nav-link" href="courscac40.jsp">
						<i class="fa fa-bar-chart mr-1"></i>La bourse
					</a>
				</li>
				
				<!-- Bouton nous contacter -->
				<li class="nav-item">
					<a class="nav-link" href="AskQuestion.jsp">
						<i class="fa fa-envelope mr-1"></i>Nous contacter
					</a>
				</li>
				
				<!-- Bouton nos actualité -->
				<li class="nav-item">
					<a class="nav-link" href="actu.jsp">
						<i class="fa fa-newspaper-o mr-1"></i>Actualité
					</a>
				</li>
				
			</ul>
			
			<ul class="navbar-nav justify-content-end">
				
				<!-- Lien du bouton Espace client si l'utilisateur est connecté ou sinon -->
				<% String logLink;
				//Récupération de la variable de spécifique aux pages de création de compte après inscription
				// pour ne pas afficher des pages liés à un user connecté
				Object o = session.getAttribute("checkConn");
				if(session.getAttribute("idClient")==null || o!=null) { logLink = "Login.jsp"; }
				else { logLink = "InfosPerso.jsp"; }
				
				//Suppression de la variable de session une fois utilisée 
				if (o!=null) session.removeAttribute("checkConn"); %>
				
				<li class="nav-item">
					<a class="nav-link bg-info text-white" href=<%= logLink %>>
						<i class="fa fa-user mr-1"></i>Espace client
					</a>
				</li>
				
				<!-- Bouton de deconnexion si utilisateur connecté -->
				<% if(session.getAttribute("idClient")!=null && o==null){ %>
					<li class="nav-item ml-2">
						<a class="nav-link bg-danger text-white" href="Disconnect">
							<i class="fa fa-sign-out mr-1"></i>Deconnexion
						</a>
					</li>
				<%} %>
				
			</ul>
		</div>
	</nav>
	
	<%  String succes = (String) session.getAttribute("msg_succes");
	if(succes != null){//Si message succes defini%>
		<div class="alert alert-success m-5"><%= succes %></div>
	<% session.removeAttribute("msg_succes"); //Suppression du message une fois affichée
	}

	String err = (String) session.getAttribute("msg_error");
	if(err != null){//Si message erreur defini%>
		<div class="alert alert-danger m-5"><%= err %></div>
	<% session.removeAttribute("msg_error"); //Suppression du message une fois affichée
	}
	%>