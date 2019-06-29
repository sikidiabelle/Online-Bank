<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!-- Definition du titre de la page -->
<% session.setAttribute("title_page", "Poser une question"); %>

<!-- Import de l'entête -->
<%@include file="entete.jsp" %>

<!-- JUMBOTRON -->

<% //Récupération de l'attribut email si user connecté
String valueEmail = "";
if(session.getAttribute("email")!=null)
	valueEmail = (String) session.getAttribute("email"); %>

<div class="jumbotron p-5">
	<form action="ConnectTreat" method="post">
		<div class="form-group text-center p-1 w-75 mx-auto">
			<h5 class="text-center">
				Soumettez-nous vos questions via ce formulaire. Nos conseillers vous enverrons une réponse à l'email renseigné!
			</h5>
			
			<!-- Champ email rempli automatiquement si user connecté -->
			<% if (valueEmail.isEmpty()){ %>
				<div class="p-3"><input type ="email" name="login" class="form-control" placeholder="Entrer votre email..." required></div>
			<% } else {%>
			<div class="p-3"><input type ="email" name="login" class="form-control" placeholder="Entrer votre email..." value= <%= valueEmail %> required></div>
			<% } %>
			
			<div class="p-3"><input type ="text" name="object" class="form-control" placeholder="Objet du message" required></div>
			<div class="p-3"><textarea type ="textarea" name="message" class="form-control" placeholder="Saisissez votre message..." required></textarea></div>
			<input type="submit" name="btn_send btn btn-dark btn-lg" class="mx-auto m-sm-3" value="Envoyer votre message"/>
		</div>
	</form>
</div>

<%@include file="footer.jsp" %>