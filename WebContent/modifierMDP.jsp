<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<% session.setAttribute("title_page", "Modification du mot de passe"); %>
<%@include file="entete.jsp" %>
<%@page import="dao.ClientDAO"%>
<%@page import="model.Client"%>
<%@page import = "dao.CptDAO"%>
<%@page import = "model.Compte"%>
<%@page import = "java.util.ArrayList"%>



<%  if(session.getAttribute("idClient")==null){ //id non défini => user non connecté %>
	<h1 class="text-danger text-center">Vous n'êtes pas connecté!</h1>

<%} else{//Si user connecté
	int id = (int) session.getAttribute("idClient");%>

<div class="container-fluid p-4 m-0"><div class="row">

<!-- Div contenant le contenu specifique à la page -->	
<div class="col-8">

	<form action="motdpTreat" method="post">
		<div class="form-group text-center p-1 w-75 mx-auto">
		 <h1 class="text-center">Modifiez votre mot de passe</h1>

			<div class="p-3"><input type="password" id="password1" name="password1" placeholder="Veuillez renseigner votre mot de passe actuel" class="form-control w-100" required></div>
			<div class="p-3"><input type="text" id="password2" name="password2" placeholder="Veuillez renseigner votre nouveau mot de passe" class="form-control w-100" required></div>
			<div class="p-3"><input type="text" id="password3" name="password3" oninput="check(this)" placeholder="Confirmation du nouveau mot de passe" class="form-control w-100" required></div>
		 		<script language='javascript' type='text/javascript'>
	    		function check(input) {
	        	if (input.value != document.getElementById('password2').value) {
	        		input.setCustomValidity('Le nouveau mdp et sa confirmation doivent être identiques');

	       		 } else {
	            	// input is valid -- reset the error message
	            	input.setCustomValidity('');
	       		 }
	   		 }
				</script>
		 	<div class="p-3"><input type="submit" value="Modifier" class="mx-auto"></div>
		 	
		</div>
		</form>
</div>
<!-- div contenant le menu client -->
<div class="col-4">
	<%@include file="MenuClient.jsp" %>
</div>

</div></div>

<%} //End else (si user connecté) %>

<!-- Import du bas de page -->
<%@include file="footer.jsp" %>