<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<% session.setAttribute("title_page", "Historique des transactions"); %>
<%@include file="entete.jsp" %>
<%@page import = "dao.CptDAO"%>
<%@page import = "model.Compte"%>
<%@page import = "dao.TransactionDAO"%>
<%@page import = "model.Transaction"%>
<%@page import = "java.util.ArrayList"%>

<%  if(session.getAttribute("idClient")==null){ //id non défini => user non connecté %>
	<h1 class="text-danger text-center">Vous n'êtes pas connecté!</h1>

<%} else{//Si user connecté
	int id = (int) session.getAttribute("idClient");
	CptDAO myDao = new CptDAO () ;
	ArrayList<String> l = myDao.getCpts(id);%>

<div class="container-fluid p-4 m-0"><div class="row">

<!-- Div contenant le contenu specifique à la page -->	
<div class="col-8">
	<form action="choixcpt.jsp" method="get">
		<div class="form-group text-center p-1 w-75 mx-auto">
			<h1 class="text-center">Consulter l'historique complet de transactions</h1>
			<div class="form-group p-3 text-left">
				<label for="numCB" class="d-inline">Numéro du compte :</label>
				<select name="numCB" size ="1" class="form-control d-inline w-50 ml-3" required>
					<%for (String num : l){ %>
						<option value= "<%=num%>"><%=num%></option>
					<%}%>
				</select>
				<input type="submit" class="btn btn-dark" value="Valider" />
			</div>
		</div>
	</form>
	
		<!-- Traitement du formulaire précédent -->
	<%  String numCpt = (String) request.getParameter("numCB");
		if(numCpt!=null){
			Compte cpt = myDao.getInfosCompte(numCpt);
			Double solde = myDao.getSolde(numCpt);%>
			
			<h1 class="text-center text-white bg-info">Votre solde est de : <%=solde %> EUR</h1>
			
			<% //Affichage des 3 dernières transaction
			TransactionDAO tr = new TransactionDAO();
			String type;
			ArrayList<Transaction> trans = tr.getTransactions(numCpt);
			if(trans.size()>0){ //transactions non vide %>
				<div class="text-center py-2">				
					<form action = "downloadCSV" method = "get">
						<input type="hidden" name="numCpt" value=<%= numCpt %>>
						<input type="submit" class="btn btn-info btn-lg" value="Exporter l'historique en format .csv"/>
					</form>
				</div>
				
				<h3>Toutes vos transactions :</h3>
				
				<%for (Transaction t: trans){
					if(t.getType())
						type = "Crédit";
					else 
						type = "Débit"; %>
					<div class="bg-light p-2 m-sm-5">
					<h4>Description : <%= t.getDescp() %></h4>
					<h4>Montant : <%= t.getMontant() %></h4>
					<% if (!t.getIban_cible().equals("Non défini")) { %>
						<h4>Iban compte cible (Destinataire/source): <%= t.getIban_cible() %></h4>
					<% } %>
				    <h4>Date : <%= t.getDate().toString() %></h4>
				    <h4>Type d'opération : <%= type %></h4>
				    </div>
				
				<% } // End for %>
			<% } //End if transactions non vide
		} //End if numCpt non vide %>
</div>

<!-- div contenant le menu client -->
<div class="col-4">
	<%@include file="MenuClient.jsp" %>
</div>

</div></div>

<%} //End else (si user connecté) %>

<!-- Import du bas de page -->
<%@include file="footer.jsp" %>