<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="entete.jsp" %>
<html>
<head>
   <title>Créer un compte épargne</title>
</head>
<body>

<!-- JUMBOTRON -->

  <div class="jumbotron p-5">
	<div class=" text-center" style ="background-color: rgba(0,0,0,0);">
		<div class="container" style ="padding-top: 200px; padding-bottom: 150px;">
         <div style ="width:500px; margin:auto">
         <form action="EpargneTreat" method="post">
			 <p class="text-white">
				<cite>Epargnez votre argent chez nous pour qu'il soit en sécurité. Votre livret A vous permet d'avoir une assurance et vos fonds sont disponibles à tout moment.</cite>
		 </p>
		 <div class="form-group m-sm-5 text-center">
		<div class="p-3"><input type="text" name="compte" class="form-control" id="lcompte" required placeholder="Numero du compte courant..."></div><br>	
		<div class="p-3"><input type="int" name="solde" class="form-control" id="sold" required placeholder="Premier montant..."></div><br>
		<input type="submit" class="btn btn-dark btn-lg btn-block" value="Valider" name="bouton">
	 </div>
	</form>
	</div>
  </div>
			
 </div>
</div>
	
<!-- FIN JUMBOTRON -->
	  
 <%@include file="footer.jsp" %>
</body>
</html>