<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!-- Definition du titre de la page -->
<% session.setAttribute("title_page", "Créer un compte bancaire"); %>

<!-- Import de l'entête -->
<%@include file="entete.jsp" %>

<div class="jumbotron p-5">

<form action="AddBancaireTreat" method="post">
	<div class="form-group text-center p-1 w-75 mx-auto">
		
		<h6 class="text-center">
			L’augmentation immédiate des plafonds de votre carte bancaire: Vous souhaitez retirer une somme d'argent importante? Vous avez besoin d'une capacité de paiement supérieure? Augmentez les plafonds de votre carte bancaire facilement et instantanément!
		</h6>
		<div class="form-group m-sm-5 text-center">
			<div class="p-3"><input type="int" name="solde" class="form-control" id="sold" required placeholder="Premier montant..."></div>
			<div class="p-3"><input type="int" name="plafondRetrait" class="form-control" id="plafondr" required placeholder="Plafond de retrait"></div>
			<div class="p-3"><input type="text" name="plafondVirement" class="form-control" id="plafondv" required placeholder="Plafond de virement"></div>
			<div class="p-3"><input type="text" name="plafondDecouvert" class="form-control" id="plafondd" required placeholder="Plafond de découvert"></div>
			<div class="p-3">
				<p><label for="chBx_chequier" > Voulez-vous un chéquier? </label>
				<input type="checkbox" name="chBx_chequier">
				<p><label for="chBx_carte"> Voulez-vous une carte bancaire? </label>
				<input type="checkbox" name="chBx_carte">
			</div>
			<input type="submit" class="btn btn-dark mx-auto" value="Ouvrir mon compte bancaire">
			
		</div>
	</div>
</form>
</div>

<%@include file="footer.jsp" %>