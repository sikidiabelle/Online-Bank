<%@page import = "dao.CptDAO"%>
<%  int idClient = (int) session.getAttribute( "idClient" );
	CptDAO c = new CptDAO(); %>

<h3 class="text-center"> Menu - Espace Client</h3>

<div>
	<a href="InfosPerso.jsp" class="btn btn-dark btn-block btn-lg m-2" role="button" > Informations Personnelles </a>
  	</div>
 <div>
	<a href="ModifierInfos.jsp" class="btn btn-dark btn-block btn-lg m-2" role="button" > Modifier mes informations personnelles </a>
</div>
<div>
	<a href="modifierMDP.jsp" class="btn btn-dark btn-block btn-lg m-2" role="button" > Modifier mon mot de passe </a>
</div>
<div>
	<a href="EffectuerVir.jsp" class="btn btn-dark btn-block btn-lg m-2" role="button" > Effectuer un virement</a>
</div>
<div>
	<a href="alimenterCpt.jsp" class="btn btn-dark btn-block btn-lg m-2" role="button" > Alimenter mon compte </a>
</div>
<div>
	<a href="Debits.jsp" class="btn btn-dark btn-block btn-lg m-2" role="button" > Paiements & retraits </a>
</div>
<div>
	<a href="consulterSolde.jsp" class="btn btn-dark btn-block btn-lg m-2" role="button" > Consulter mon solde</a>
</div>
<div>
	<a href="choixcpt.jsp" class="btn btn-dark btn-block btn-lg m-2" role="button" > Consulter mon historique</a>
</div>

<% if (c.UniTitre(idClient)) { //Si client poss�de un compte titre %>
<div>
	<a href="acheterAction.jsp" class="btn btn-dark btn-block btn-lg m-2" role="button" > Acheter des actions</a>
</div>
<div>
	<a href="vendreAction.jsp" class="btn btn-dark btn-block btn-lg m-2" role="button" >Vendre mes actions</a>
</div>
<% } %>

<!-- Bouton cr�ation compte bancaire -->
<% if (!c.UniBancaire(idClient)) { //Si client ne poss�de pas encore de compte bancaire %>
<div>
	<a href="AddCptBancaire.jsp" class="btn btn-dark btn-block btn-lg m-2" role="button" > Cr�er un compte bancaire </a>
</div>
<% } %>

<% if (!c.UniTitre(idClient)) { //Si client ne poss�de pas encore de compte titre %>
<!-- Bouton cr�ation titre -->
<div>
	<a href="AddCptTitre.jsp" class="btn btn-dark btn-block btn-lg m-2" role="button" > Cr�er un compte titre </a>
</div>
<% } %>

<% if (!c.UniEpargne(idClient)) { //Si client ne poss�de pas encore de compte epargne %>
<!-- Bouton cr�ation compte �pargne -->
<div>
	<a href="FormCompteEpargne.jsp" class="btn btn-dark btn-block btn-lg m-2" role="button" > Cr�er un compte d'�pargne </a>
</div>
<% } %>