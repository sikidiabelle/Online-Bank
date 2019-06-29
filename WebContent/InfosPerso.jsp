<%@page import="dao.ClientDAO"%>
<%@page import="model.Client"%>

<!-- Definition du titre de la page -->
<% session.setAttribute("title_page", "Vos Informations"); %>

<!-- Import de l'entête -->
<%@include file="entete.jsp" %>

<% if(session.getAttribute("idClient")==null){ //id non défini => user non connecté %>
	<h1 class="text-danger text-center">Vous n'êtes pas connecté!</h1>
<%} else{//Si user connecté
	String email = (String) session.getAttribute("email");
	ClientDAO d = new ClientDAO(); Client cl = d.getInfos(d.getId(email));
	String civ = "";
	switch (cl.getCiv()){
		case 'H':
			civ = "Homme"; break;
		case 'F':
			civ = "Femme"; break;
	}
%>

<div class="container-fluid p-4 m-0"><div class="row">

<!-- Div contenant le contenu specifique à la page -->	
<div class="col-8">
	<div class="p-2 mx-auto">		
		<h1 class="text-center">Vos informations personnelles</h1>
		<div class="m-sm-5">
			<h4>Nom : <%= cl.getNom() %></h4>
			<h4>Prenom : <%= cl.getPrenom() %></h4>
			<h4>Civilité : <%= civ %></h4>
			<h4>Email : <%= cl.getEmail() %></h4>
			<h4>Adresse : <%= cl.getAdr() %></h4>
			<h4>Date de naissance : <%= cl.getBday() %></h4>
			<h4>Pays : <%= cl.getPays() %></h4>
			<h4>Ville : <%= cl.getCity() %></h4>
			<h4>Nationalité : <%= cl.getNat() %></h4>
		</div>
	</div>
</div>

<!-- div contenant le menu client -->
<div class="col-4">
	<%@include file="MenuClient.jsp" %>
</div>

</div></div>
<% } // End else (si user connecté) %>
<!-- Import du bas de page -->
<%@include file="footer.jsp" %>