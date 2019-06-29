<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.CptDAO" %>
<%@ page import="model.Action" %>
<%@ page import="dao.ActionDAO" %>

<!-- Definition du titre de la page -->
<% session.setAttribute("title_page", "Acheter des actions"); %>

<!-- Import de l'entête -->
<%@include file="entete.jsp" %>

<%	if(session.getAttribute("idClient")==null){ //id non défini => user non connecté %>
	
	<h1 class="text-danger text-center">Vous n'êtes pas connectés!</h1>
	
<%} else {//Si user connecté
	
	CptDAO cptDAO = new CptDAO();
	int id = (int) session.getAttribute("idClient");
	
	if (!cptDAO.UniTitre(id)){ //Si user n'a pas de compte titre %>
	
		<h1 class="text-danger text-center">Vous devez avoir un compte titre pour acheter des action!</h1>
	
	<% }else{ //Si user connecté a un compte titre %>
		
		<div class="container-fluid p-4 m-0"><div class="row">

		<!-- Div contenant le contenu specifique à la page -->	
		<div class="col-8">
			<h1 class="text-center">Acheter des actions</h1>
			
			<!-- Entête de la liste -->
			<div class="row bg-dark p-3">
				<div class="col-3">
					<h3 class="text-center text-light">Entreprise</h3>
				</div>
				<div class="col-3">
					<h3 class="text-center text-light">Prix</h3>
				</div>
				<div class="col-3">
					<h3 class="text-center text-light">Quantité</h3>
				</div>
				<div class="col-3">
					<h3 class="text-center text-light"></h3>
				</div>
			</div>
			
			<%
			ActionDAO actDAO = new ActionDAO();
			ArrayList<Action> listAct = actDAO.getListCAC40();
			for (Action act : listAct){
			%>
			
			<form action="AchatTreat" method="post">
				<div class="row bg-light p-3 border border-dark">
					<!-- Envoi de l'id de l'action pour le traitement -->
					<input type="hidden" name="idAction" value=<%= act.getId() %> >
					
					<!-- Affichage infos de l'action -->
					<div class="col-3">
						<h4 class="text-center"><%= act.getNom() %></h4>
					</div>
					<div class="col-3">
						<h4 class="text-center"><%= act.getPrix() %> EUR</h4>
					</div>
					<div class="col-3">
						<input type="number" step="1" name="qteAction" placeholder="Quantité" min="1" required>
					</div>
					<div class="col-3">
						<input type="submit" class="btn btn-dark btn-block btn-lg" value="Acheter">
					</div>
				</div>
			</form>
			
			<%} //End for actions %>
			
		<!-- End div col-8 -->
		</div>
		
		<!-- div contenant le menu client -->
		<div class="col-4">
			<%@ include file="MenuClient.jsp" %>
		</div>
		
		</div></div>
			
	<% } //End user connecté a un compte titre
} //End si user connecté%>

<%@include file="footer.jsp" %>