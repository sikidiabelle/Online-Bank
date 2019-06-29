<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!-- Definition du titre de la page -->
<% session.setAttribute("title_page", "Connexion"); %>

<!-- Import de l'entête -->
<%@include file="entete.jsp" %>
	
<!-- JUMBOTRON -->

<div class="jumbotron p-5">
	<form action="ConnectTreat" method="post">
		<div class="form-group text-center p-1 w-75 mx-auto">
			<h1 class="text-center">Connexion</h1>
			<div class="p-3"><input type="text" name="email" placeholder="Email" class="form-control w-100" required></div>
			<div class="p-3"><input type="password" name="password" placeholder="Mot de passe" class="form-control w-100" required></div>
			<div class="p-3"><input type="submit" value="Connexion" class="mx-auto m-sm-3"></div>
			<a class="nav-link" href="InscriptionClient.jsp">
				<h5 class="text-danger">Pas encore client? Rejoignez-nous!</h5>
			</a>
		</div>
	</form>
</div>

<!-- Import du bas de page -->
<%@include file="footer.jsp" %>