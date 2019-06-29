<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!-- Definition du titre de la page -->
<% session.setAttribute("title_page", "Cours de la bourse"); %>

<!-- Import de l'entête -->
<%@include file="entete.jsp" %>


<div class="jumbotron p-5">
<div class="text-center">
 <h2 class="section-heading text-uppercase mt-5">Cours de la bourse CAC40</h2><br>
 <%@include file="BourseChart.jsp" %>
<br>
<h4>LA BOURSE DE PARIS</h4>

<p>La bourse de Paris fait référence au marché officiel des actions d'entreprises en France, ou par métonymie peut aussi désigner les indices boursiers français tels que le CAC 40, indice de référence pour les investisseurs français.
<br></br>
Euronext étant l'entreprise qui organise les cotations de la bourse de Paris, les termes de bourse de Paris et d'Euronext Paris sont devenus synonymes. Euronext Paris diffuse différents indices boursiers tels que le SBF 120 (déterminé à partir des cours de 40 actions du CAC 40 et de 80 valeurs des premier et second marchés) ou le CAC Mid & Small (composé de capitalisations boursières de l'univers des PME).
<br></br>
Comme l'ensemble des places européennes, la bourse de Paris est un marché de capitaux ouverts à l'international. Historiquement, la bourse de Paris se situait au palais Brongniart avant d'être dématérialisée en novembre 1998.</p>
</div>
</div>
<%@include file="footer.jsp" %>

</body>
</html>