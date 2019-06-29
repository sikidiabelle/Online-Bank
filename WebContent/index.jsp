<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% session.setAttribute("title_page", "La banque qu'il vous faut"); %>

<%@ include file="entete.jsp" %>

<div id="homeCarousel" class="carousel slide" data-ride="carousel">

  <!-- Indicators -->
  <ul class="carousel-indicators">
    <li data-target="#homeCarousel" data-slide-to="0" class="active"></li>
	<li data-target="#homeCarousel" data-slide-to="1"></li>
  </ul>

  <!-- The slideshow -->
  <div class="carousel-inner">
    <div class="carousel-item active">
		<img src="img/home1.jpg" class="img-fluid" alt="Image">
		<div class="carousel-caption">
			<h3 class="text-primary">Lancez vous dans la bourse!</h3>
			<p class="text-primary">Achetez, vendez et restez au courant des cours boursiers</p>
		</div>
    </div>
    <div class="carousel-item">
		<img src="img/home3.jpg" class="img-fluid" alt="Image">
		<div class="carousel-caption">
			<h3 class="text-primary">Epargnez chez nous!</h3>
			<p class="text-primary">Votre satisfaction est notre priorité</p>
		</div>
    </div>
  </div>

  <!-- Left and right controls -->
  <a class="carousel-control-prev" href="#homeCarousel" data-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </a>
  <a class="carousel-control-next" href="#homeCarousel" data-slide="next">
    <span class="carousel-control-next-icon"></span>
  </a>

</div>
  
<div class="container text-center mt-3">    
  <h3>Découvrez toute l'actualité</h3><br>
  <div class="row">
    <div class="col-sm-4">
      <img src="img/actu1.jpg" class="img-responsive" style="width:100%" alt="Image">
      <p>Lancez vous dans la bourse</p>
    </div>
    <div class="col-sm-4"> 
      <img src="img/cb1.jpg" class="img-responsive" style="width:100%" alt="Image">
      <p>Nos offres de cartes bancaires</p>    
    </div>
    <div class="col-sm-4">
      <div class="well">
       <p>BANKTIC!!!! La banque moins chère... <br> 
       BANKTIC est classée 1ère banque la moins chère du monde grâce à ses offres et à ses services. Grace à BANKTIC vous avez la main sur votre argent ou que vous soyez et 7j/7. Ouvrez votre compte bancaire, épargne et titre!!!. N'hésitez pas à consulter nos offres. Chez BANKTIC les  cartes bancaires sont gratuites.
       </p>
      </div>
      <div class="well">
       <p>Les conseillers BANKTIC sont à vos côtés sur Twitter, Facebook et par téléphone. Obtenez également toutes les réponses à vos questions en instantané par Chat de 10h à 19h en semaine et de 10h à 17h30 le samedi. Nous avons voulu construire la meilleure des banques en ligne, celle qui répond à vos besoins</p>
      </div>
    </div>
  </div>
</div><br>

<div class="container text-center">    
  <h3>Tout savoir sur nous</h3>
  <br>
  <div class="row">
    <div class="col-sm-2">
      <img src="img/contactez.jpg" class="img-responsive" style="width:100%" alt="Image">
      <p>Facebook. Service client</p>
    </div>
    <div class="col-sm-2"> 
      <img src="img/quiz.jpg" class="img-responsive" style="width:100%" alt="Image">
      <p>Des questions</p>    
    </div>
    <div class="col-sm-2"> 
      <img src="img/parrain.jpg" class="img-responsive" style="width:100%" alt="Image">
      <p>Faites vous parrainer</p>
    </div> 
    <div class="col-sm-2"> 
      <img src="img/partenaire.jpg" class="img-responsive" style="width:100%" alt="Image">
      <p>Nos partenaires</p>
    </div>     
    <div class="col-sm-2"> 
      <img src="img/bourse1.jpg" class="img-responsive" style="width:100%" alt="Image">
      <p>La bourse</p>
    </div>
    <div class="col-sm-2"> 
      <img src="img/actu2.jpg" class="img-responsive" style="width:100%" alt="Image">
      <p>Extras</p>
    </div> 
  </div>
</div><br>

<%@include file="footer.jsp" %>

</body>
</html>