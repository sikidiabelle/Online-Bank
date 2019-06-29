<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<% session.setAttribute("title_page", "Modification des informations personnelles"); %>
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

	
		<div class="form-group text-center p-1 w-75 mx-auto">
		 <h1 class="text-center">Modifiez vos informations personnelles</h1>

		<%
			HttpSession s = request.getSession();
			String email = (String) s.getAttribute("email");
			ClientDAO d = new ClientDAO(); Client cl = d.getInfos(d.getId(email));
			s.setAttribute("mdp",cl.getMdp());

		%>		
				<form action="ModifInfo" method="post">
			
				<h4>Votre numéro de téléphone actuel: <%= cl.getTel() %></h4>
				<input type="text" name="tel" class="form-control m-sm-3" id="tel" value=<%=cl.getTel()%> required placeholder="Votre telephone...">
				
				<h4>Votre addresse actuelle : <%= cl.getAdr() %></h4>
				<input type="text" name="address" class="form-control m-sm-3" id="addr" value=<%=cl.getAdr()%> required placeholder="Votre adresse...">
				
				<h4>Votre ville actuelle: <%= cl.getCity() %></h4>
				<input type="text" name="city" class="form-control m-sm-3" id="city" value=<%= cl.getCity() %> required placeholder="Votre ville...">
				
				<h4>Votre code postal actuel : <%= cl.getCP() %></h4>
				<input type="text" name="codeP" class="form-control m-sm-3" id="codeP" value=<%= cl.getCP() %> required placeholder="Votre code postal...">
					
		 	<div class="p-3"><input type="submit" class="btn btn-primary" value="Modifier"/>
			</div>
		 </form>	
		</div>
		
</div>
<!-- div contenant le menu client -->
<div class="col-4">
	<%@include file="MenuClient.jsp" %>
</div>

</div></div>

<%} //End else (si user connecté) %>

<!-- Import du bas de page -->
<%@include file="footer.jsp" %>