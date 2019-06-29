<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<% session.setAttribute("title_page", "Alimentation de compte"); %>
<%@include file="entete.jsp" %>
<%@page import = "dao.CptDAO"%>
<%@page import = "java.util.ArrayList"%>

<%	if(session.getAttribute("idClient")==null){ //id non défini => user non connecté %>
	<h1 class="text-danger text-center">Vous n'êtes pas connectés!</h1>
<%} else{//Si user connecté
	CptDAO mydao = new CptDAO () ;
	ArrayList<String> numeros= new ArrayList<String>();
	int id = (int) session.getAttribute("idClient");
	numeros=mydao.getCpts(id);%>

<div class="container-fluid p-4 m-0"><div class="row">

<!-- Div contenant le contenu specifique à la page -->	
<div class="col-8">
	<form action="AlimCTreat" method="post">
		<div class="form-group text-center p-1 w-75 mx-auto">
			<h1 class="text-center">Alimentez votre compte en toute sécurité</h1>
			
			<!-- input de type number acceptant 2 decimaux (centimes) -->
			<div class="p-3"><input type="number" step="0.01" name="depot" placeholder="Montant du dépot (en EUR)" class="form-control w-100" required></div>
			
			<div class="form-group p-3 text-left">
				<label for="numCB" class="d-inline">Numéro de compte :</label>
				<select name="numCB" size ="1" class="form-control d-inline w-50 ml-3" required>
					<%for (String num : numeros){ %>
						<option value= "<%=num%>"><%=num%></option>
					<%}%>
				</select>
			</div>
			
			<!-- select permettant de définir la méthode d'alimentation du compte -->
			<div class="form-group p-3 text-left">
				<label for="modeAlim" class="d-inline">Type de dépôt :</label>
				<select name="modeAlim" id="modeAlim" class="form-control d-inline w-50 ml-3" required onchange="dynamicTextField(this.value)">
					<option value="cheque"> Par Chèque </option>
					<option value="carte"> Par Carte bancaire </option>
				</select>
			</div>
			
			<!-- Affichage des champs sur les infos du chèque si alimentation cheque choisi -->
			<script type="text/javascript">
				function dynamicTextField(val)
				{
					if(val == "cheque") { document.getElementById("chequeInfos").style.display = "block"; }
					else{ document.getElementById("chequeInfos").style.display = "none"; }
				}
			</script>
			
			<!-- div contenant les champs renseignants les infos du chèque -->
			<div id="chequeInfos" style="display: none;">
				<div class="p-3"><input type="text" name="numCheque" placeholder="Numero du chèque" class="form-control w-100"></div>
				<div class="p-3"><input type="text" name="numCompte" placeholder="Numero du compte source" class="form-control w-100"></div>
			</div>
				
			<div class="p-3"><input type="submit" value="Alimenter" class="btn btn-dark mx-auto"></div>
		</div>
	</form>
</div>

<!-- div contenant le menu client -->
<div class="col-4">
	<%@ include file="MenuClient.jsp" %>
</div>

</div></div>
<% } // End else (si user connecté) %>

<!-- Import du bas de page -->
<%@include file="footer.jsp" %>
