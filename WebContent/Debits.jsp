<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<% session.setAttribute("title_page", "Débit du compte"); %>
<%@include file="entete.jsp" %>
<%@page import = "dao.CptDAO"%>
<%@page import = "java.util.ArrayList"%>

<%  if(session.getAttribute("idClient")==null){ //id non défini => user non connecté %>
	<h1 class="text-danger text-center">Vous n'êtes pas connecté!</h1>

<%} else{//Si user connecté
	int id = (int) session.getAttribute("idClient");
	CptDAO mydao = new CptDAO () ;
	ArrayList<String> numeros= new ArrayList<String>();
	numeros=mydao.getCpts(id);%>

<div class="container-fluid p-4 m-0"><div class="row">

<!-- Div contenant le contenu specifique à la page -->	
<div class="col-8">
	<form action="DebitTreat" method="post">
		<div class="form-group text-center p-1 w-75 mx-auto">
			<h1 class="text-center">Faîtes vos paiements/retraits  en toute sécurité</h1>
			
			<!-- input de type number acceptant 2 decimaux (centimes) -->
			<div class="p-3"><input type="number" step="0.01" name="debit" placeholder="Montant à débiter (en EUR)" class="form-control w-100" required></div>
			<div class="form-group p-3 text-left">
				<label for="numCB" class="d-inline">Num de compte :</label>
				<select name="numCB" size ="1" class="form-control d-inline w-50 ml-3" required>
					<%for (String num : numeros){ %>
						<option value= "<%=num%>"><%=num%></option>
					<%}%>
				</select>
			</div>
			
			<!-- select permettant de définir la méthode d'alimentation du compte -->
			<div class="form-group p-3 text-left">
				<label for="modeAlim" class="d-inline">Type de débit :</label>
				<select name="mode" id="mode" class="form-control d-inline w-50 ml-3" required onchange="dynamicTextField(this.value)">
					<option value="pcb" selected> Paiement par CB </option>
					<option value="pch"> Paiement par chèque </option>
					<option value="retrait"> Retrait </option>
				</select>
			</div>
			
			<!-- Affichage des champs sur les infos du chèque si paiement par cheque choisi -->
			<script type="text/javascript">
				function dynamicTextField(val) {
					if(val == "pch") {
						document.getElementById("chequeInfos").style.display = "block";
						//Rendre obligatoire les champs activés
						document.getElementById("numCheque").setAttribute("required", true);
						document.getElementById("ibanCpt").setAttribute("required", true);
					} else {
						document.getElementById("chequeInfos").style.display = "none";
						//Rendre facultatif les champs désactivés
						document.getElementById("numCheque").removeAttribute("required");
						document.getElementById("ibanCpt").removeAttribute("required");
					}
				}
			</script>
			
			<!-- div contenant les champs renseignants les infos du chèque -->
			<div id="chequeInfos" style="display: none;">
				<div class="p-3"><input type="text" id="numCheque" name="numCheque" placeholder="Numero du chèque" class="form-control w-100"></div>
			</div>
				
			<div class="p-3"><input type="submit" value="Débiter" class="mx-auto"></div>
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