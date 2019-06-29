<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<% session.setAttribute("title_page", "Alimentation de compte"); %>
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
	ArrayList<String> numeros= new ArrayList<String>();
	numeros=myDao.getCpts(id);%>

<div class="container-fluid p-4 m-0"><div class="row">

<!-- Div contenant le contenu specifique à la page -->	
<div class="col-8">		
	<form action="VirTreat" method="post">
		<div class="form-group text-center p-1 w-75 mx-auto">
			<h1 class="text-center">Effectuer votre virement en toute sécurité</h1>
			<label for="numCB" class="d-inline">Numéro du compte :</label>
			<select name="numCB" size ="1" class="form-control d-inline w-50 ml-3" required>
				<%for (String num : numeros){ %>
					<option value= "<%=num%>"><%=num%></option>
				<%}%>
			</select>
			<!-- input de type number acceptant 2 decimaux (centimes) -->
			<div class="p-3"><input type="number" step="0.01" name="depot" placeholder="Montant du virement (en EUR)" class="form-control w-100" required></div>
			<div class="p-3"><input type ="text" name="dest" class="form-control" placeholder="IBAN du compte destinataire" required></div>
			<input type="submit" name="btn_send" class="btn btn-dark mx-auto m-sm-3" value="Valider"/>
		</div>
	</form>
</div>

<!-- div contenant le menu client -->
<div class="col-4">
	<%@include file="MenuClient.jsp" %>
</div>

</div></div>
<% } // End else (si user connecté) %>


<%@include file="footer.jsp" %>
</body>
</html>
