<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!-- Definition du titre de la page -->
<% session.setAttribute("title_page", "Inscription Client"); %>

<!-- Import de l'entête -->
<%@include file="entete.jsp" %>

<div class="jumbotron p-5">

<form action="RegisterTreat" method="post">
	<div class="form-group text-center p-1 w-75 mx-auto">
		
		<h1 class="text-center">Inscription</h1>
		<div class="form-group m-sm-5 text-center">
			
			<!-- Saisie des infos basiques de connexion -->
			<div class="p-3"><input type="email" name="email" class="form-control" id="email" required placeholder="Votre email..."></div>
			
			<!-- Password validation -->
			<div class="p-3"><input name="password" required class="form-control" type="password" id="password" placeholder="Votre mot de passe..."/></div>
			<div class="p-3"><input name="password_confirm" required class="form-control" type="password" id="password_confirm" oninput="check(this)" placeholder="Confirmez votre mot de passe..."/></div>
			<script language='javascript' type='text/javascript'>
			    function check(input) {
			        if (input.value != document.getElementById('password').value) {
			            input.setCustomValidity('Les 2 mots de passes doivent �tre identiques!');
			        } else {
			            // input is valid -- reset the error message
			            input.setCustomValidity('');
			        }
			    }
			</script>
			
			<!-- Saisie des informations personnelles -->
			
			<!-- Choix de la civilité -->
			<div class="form-group mx-sm-3 text-left">
				<label for="sex" class="d-inline">Civilité :</label>
				<select name="sex" class="form-control d-inline w-25 ml-3" required>
					<option></option>
					<option value="F"> Madame </option>
					<option value="H"> Monsieur </option>
				</select>
			</div>
			<div class="p-3"><input type="text" name="nom" class="form-control" id="firstname" required placeholder="Votre nom..."></div>
			<div class="p-3"><input type="text" name="prenom" class="form-control" id="laststname" required placeholder="Votre prénom..."></div>
			<div class="p-3"><input type="text" name="address" class="form-control" id="addr" required placeholder="Votre adresse..."></div>
			<div class="p-3"><input type="text" name="city" class="form-control" id="city" required placeholder="Votre ville..."></div>
			<div class="p-3"><input type="text" name="codeP" class="form-control" id="codeP" required placeholder="Votre code postal..."></div>
			<div class="p-3"><input type="date" name="birth" class="form-control" id="birth" required placeholder="Votre date de naissance..."></div>
			<div class="p-3"><input type="text" name="tel" class="form-control" id="tel" required placeholder="Votre telephone..."></div>
			
			<div class="form-group mx-sm-3 text-left">
				<label for="pays" class="d-inline">Pays de résidence :</label>
				<select name="pays" class="form-control d-inline w-50 ml-3" required>
					<option></option>
					<option value="Afghanistan">Afghanistan </option>
					<option value="Afrique_Centrale">Afrique_Centrale </option>
					<option value="Afrique_du_sud">Afrique_du_Sud </option> 
					<option value="Albanie">Albanie </option>
					<option value="Algerie">Algerie </option>
					<option value="Allemagne">Allemagne </option>
					<option value="Andorre">Andorre </option>
					<option value="Angola">Angola </option>
					<option value="Anguilla">Anguilla </option>
					<option value="Arabie_Saoudite">Arabie_Saoudite </option>
					<option value="Argentine">Argentine </option>
					<option value="Armenie">Armenie </option> 
					<option value="Australie">Australie </option>
					<option value="Autriche">Autriche </option>
					<option value="Azerbaidjan">Azerbaidjan </option>
					
					<option value="Bahamas">Bahamas </option>
					<option value="Bangladesh">Bangladesh </option>
					<option value="Barbade">Barbade </option>
					<option value="Bahrein">Bahrein </option>
					<option value="Belgique">Belgique </option>
					<option value="Belize">Belize </option>
					<option value="Benin">Benin </option>
					<option value="Bermudes">Bermudes </option>
					<option value="Bielorussie">Bielorussie </option>
					<option value="Bolivie">Bolivie </option>
					<option value="Botswana">Botswana </option>
					<option value="Bhoutan">Bhoutan </option>
					<option value="Boznie_Herzegovine">Boznie_Herzegovine </option>
					<option value="Bresil">Bresil </option>
					<option value="Brunei">Brunei </option>
					<option value="Bulgarie">Bulgarie </option>
					<option value="Burkina_Faso">Burkina_Faso </option>
					<option value="Burundi">Burundi </option>
					
					<option value="Caiman">Caiman </option>
					<option value="Cambodge">Cambodge </option>
					<option value="Cameroun">Cameroun </option>
					<option value="Canada">Canada </option>
					<option value="Canaries">Canaries </option>
					<option value="Cap_vert">Cap_Vert </option>
					<option value="Chili">Chili </option>
					<option value="Chine">Chine </option> 
					<option value="Chypre">Chypre </option> 
					<option value="Colombie">Colombie </option>
					<option value="Comores">Colombie </option>
					<option value="Congo">Congo </option>
					<option value="Congo_democratique">Congo_democratique </option>
					<option value="Cook">Cook </option>
					<option value="Coree_du_Nord">Coree_du_Nord </option>
					<option value="Coree_du_Sud">Coree_du_Sud </option>
					<option value="Costa_Rica">Costa_Rica </option>
					<option value="Cote_d_Ivoire">Côte_d_Ivoire </option>
					<option value="Croatie">Croatie </option>
					<option value="Cuba">Cuba </option>
					
					<option value="Danemark">Danemark </option>
					<option value="Djibouti">Djibouti </option>
					<option value="Dominique">Dominique </option>
					
					<option value="Egypte">Egypte </option> 
					<option value="Emirats_Arabes_Unis">Emirats_Arabes_Unis </option>
					<option value="Equateur">Equateur </option>
					<option value="Erythree">Erythree </option>
					<option value="Espagne">Espagne </option>
					<option value="Estonie">Estonie </option>
					<option value="Etats_Unis">Etats_Unis </option>
					<option value="Ethiopie">Ethiopie </option>
					
					<option value="Falkland">Falkland </option>
					<option value="Feroe">Feroe </option>
					<option value="Fidji">Fidji </option>
					<option value="Finlande">Finlande </option>
					<option value="France" selected="selected">France </option>
					
					<option value="Gabon">Gabon </option>
					<option value="Gambie">Gambie </option>
					<option value="Georgie">Georgie </option>
					<option value="Ghana">Ghana </option>
					<option value="Gibraltar">Gibraltar </option>
					<option value="Grece">Grece </option>
					<option value="Grenade">Grenade </option>
					<option value="Groenland">Groenland </option>
					<option value="Guadeloupe">Guadeloupe </option>
					<option value="Guam">Guam </option>
					<option value="Guatemala">Guatemala</option>
					<option value="Guernesey">Guernesey </option>
					<option value="Guinee">Guinee </option>
					<option value="Guinee_Bissau">Guinee_Bissau </option>
					<option value="Guinee equatoriale">Guinee_Equatoriale </option>
					<option value="Guyana">Guyana </option>
					<option value="Guyane_Francaise ">Guyane_Francaise </option>
					
					<option value="Haiti">Haiti </option>
					<option value="Hawaii">Hawaii </option> 
					<option value="Honduras">Honduras </option>
					<option value="Hong_Kong">Hong_Kong </option>
					<option value="Hongrie">Hongrie </option>
					
					<option value="Inde">Inde </option>
					<option value="Indonesie">Indonesie </option>
					<option value="Iran">Iran </option>
					<option value="Iraq">Iraq </option>
					<option value="Irlande">Irlande </option>
					<option value="Islande">Islande </option>
					<option value="Israel">Israel </option>
					<option value="Italie">italie </option>
					
					<option value="Jamaique">Jamaique </option>
					<option value="Jan Mayen">Jan Mayen </option>
					<option value="Japon">Japon </option>
					<option value="Jersey">Jersey </option>
					<option value="Jordanie">Jordanie </option>
					
					<option value="Kazakhstan">Kazakhstan </option>
					<option value="Kenya">Kenya </option>
					<option value="Kirghizstan">Kirghizistan </option>
					<option value="Kiribati">Kiribati </option>
					<option value="Koweit">Koweit </option>
					
					<option value="Laos">Laos </option>
					<option value="Lesotho">Lesotho </option>
					<option value="Lettonie">Lettonie </option>
					<option value="Liban">Liban </option>
					<option value="Liberia">Liberia </option>
					<option value="Liechtenstein">Liechtenstein </option>
					<option value="Lituanie">Lituanie </option> 
					<option value="Luxembourg">Luxembourg </option>
					<option value="Lybie">Lybie </option>
					
					<option value="Macao">Macao </option>
					<option value="Macedoine">Macedoine </option>
					<option value="Madagascar">Madagascar </option>
					<option value="Madère">Madère </option>
					<option value="Malaisie">Malaisie </option>
					<option value="Malawi">Malawi </option>
					<option value="Maldives">Maldives </option>
					<option value="Mali">Mali </option>
					<option value="Malte">Malte </option>
					<option value="Man">Man </option>
					<option value="Mariannes du Nord">Mariannes du Nord </option>
					<option value="Maroc">Maroc </option>
					<option value="Marshall">Marshall </option>
					<option value="Martinique">Martinique </option>
					<option value="Maurice">Maurice </option>
					<option value="Mauritanie">Mauritanie </option>
					<option value="Mayotte">Mayotte </option>
					<option value="Mexique">Mexique </option>
					<option value="Micronesie">Micronesie </option>
					<option value="Midway">Midway </option>
					<option value="Moldavie">Moldavie </option>
					<option value="Monaco">Monaco </option>
					<option value="Mongolie">Mongolie </option>
					<option value="Montserrat">Montserrat </option>
					<option value="Mozambique">Mozambique </option>
					
					<option value="Namibie">Namibie </option>
					<option value="Nauru">Nauru </option>
					<option value="Nepal">Nepal </option>
					<option value="Nicaragua">Nicaragua </option>
					<option value="Niger">Niger </option>
					<option value="Nigeria">Nigeria </option>
					<option value="Niue">Niue </option>
					<option value="Norfolk">Norfolk </option>
					<option value="Norvege">Norvege </option>
					<option value="Nouvelle_Caledonie">Nouvelle_Caledonie </option>
					<option value="Nouvelle_Zelande">Nouvelle_Zelande </option>
					
					<option value="Oman">Oman </option>
					<option value="Ouganda">Ouganda </option>
					<option value="Ouzbekistan">Ouzbekistan </option>
					
					<option value="Pakistan">Pakistan </option>
					<option value="Palau">Palau </option>
					<option value="Palestine">Palestine </option>
					<option value="Panama">Panama </option>
					<option value="Papouasie_Nouvelle_Guinee">Papouasie_Nouvelle_Guinee </option>
					<option value="Paraguay">Paraguay </option>
					<option value="Pays_Bas">Pays_Bas </option>
					<option value="Perou">Perou </option>
					<option value="Philippines">Philippines </option> 
					<option value="Pologne">Pologne </option>
					<option value="Polynesie">Polynesie </option>
					<option value="Porto_Rico">Porto_Rico </option>
					<option value="Portugal">Portugal </option>
					
					<option value="Qatar">Qatar </option>
					
					<option value="Republique_Dominicaine">Republique_Dominicaine </option>
					<option value="Republique_Tcheque">Republique_Tcheque </option>
					<option value="Reunion">Reunion </option>
					<option value="Roumanie">Roumanie </option>
					<option value="Royaume_Uni">Royaume_Uni </option>
					<option value="Russie">Russie </option>
					<option value="Rwanda">Rwanda </option>
					
					<option value="Sahara Occidental">Sahara Occidental </option>
					<option value="Sainte_Lucie">Sainte_Lucie </option>
					<option value="Saint_Marin">Saint_Marin </option>
					<option value="Salomon">Salomon </option>
					<option value="Salvador">Salvador </option>
					<option value="Samoa_Occidentales">Samoa_Occidentales</option>
					<option value="Samoa_Americaine">Samoa_Americaine </option>
					<option value="Sao_Tome_et_Principe">Sao_Tome_et_Principe </option> 
					<option value="Senegal">Senegal </option> 
					<option value="Seychelles">Seychelles </option>
					<option value="Sierra Leone">Sierra Leone </option>
					<option value="Singapour">Singapour </option>
					<option value="Slovaquie">Slovaquie </option>
					<option value="Slovenie">Slovenie</option>
					<option value="Somalie">Somalie </option>
					<option value="Soudan">Soudan </option> 
					<option value="Sri_Lanka">Sri_Lanka </option> 
					<option value="Suede">Suede </option>
					<option value="Suisse">Suisse </option>
					<option value="Surinam">Surinam </option>
					<option value="Swaziland">Swaziland </option>
					<option value="Syrie">Syrie </option>
					
					<option value="Tadjikistan">Tadjikistan </option>
					<option value="Taiwan">Taiwan </option>
					<option value="Tonga">Tonga </option>
					<option value="Tanzanie">Tanzanie </option>
					<option value="Tchad">Tchad </option>
					<option value="Thailande">Thailande </option>
					<option value="Tibet">Tibet </option>
					<option value="Timor_Oriental">Timor_Oriental </option>
					<option value="Togo">Togo </option> 
					<option value="Trinite_et_Tobago">Trinite_et_Tobago </option>
					<option value="Tristan da cunha">Tristan de cuncha </option>
					<option value="Tunisie">Tunisie </option>
					<option value="Turkmenistan">Turmenistan </option> 
					<option value="Turquie">Turquie </option>
					
					<option value="Ukraine">Ukraine </option>
					<option value="Uruguay">Uruguay </option>
					
					<option value="Vanuatu">Vanuatu </option>
					<option value="Vatican">Vatican </option>
					<option value="Venezuela">Venezuela </option>
					<option value="Vierges_Americaines">Vierges_Americaines </option>
					<option value="Vierges_Britanniques">Vierges_Britanniques </option>
					<option value="Vietnam">Vietnam </option>
					
					<option value="Wake">Wake </option>
					<option value="Wallis et Futuma">Wallis et Futuma </option>
					
					<option value="Yemen">Yemen </option>
					<option value="Yougoslavie">Yougoslavie </option>
					
					<option value="Zambie">Zambie </option>
					<option value="Zimbabwe">Zimbabwe </option>
				</select>
			</div>
			
			<div class="form-group mx-sm-3 text-left">
				<label for="nationality" class="d-inline">Nationalité :</label>
				<select name="nationality" class="form-control d-inline w-50 ml-3" required>
					<option></option>
			        <option value="AFG">Afghane (Afghanistan)</option>
			        <option value="ALB">Albanaise (Albanie)</option>
			        <option value="DZA">Algérienne (Algérie)</option>
			        <option value="DEU">Allemande (Allemagne)</option>
			        <option value="USA">Americaine (États-Unis)</option>
			        <option value="AND">Andorrane (Andorre)</option>
			        <option value="AGO">Angolaise (Angola)</option>
			        <option value="ATG">Antiguaise-et-Barbudienne (Antigua-et-Barbuda)</option>
			        <option value="ARG">Argentine (Argentine)</option>
			        <option value="ARM">Armenienne (Arménie)</option>
			        <option value="AUS">Australienne (Australie)</option>
			        <option value="AUT">Autrichienne (Autriche)</option>
			        <option value="AZE">Azerbaïdjanaise (Azerbaïdjan)</option>
			        <option value="BHS">Bahamienne (Bahamas)</option>
			        <option value="BHR">Bahreinienne (Bahreïn)</option>
			        <option value="BGD">Bangladaise (Bangladesh)</option>
			        <option value="BRB">Barbadienne (Barbade)</option>
			        <option value="BEL">Belge (Belgique)</option>
			        <option value="BLZ">Belizienne (Belize)</option>
			        <option value="BEN">Béninoise (Bénin)</option>
			        <option value="BTN">Bhoutanaise (Bhoutan)</option>
			        <option value="BLR">Biélorusse (Biélorussie)</option>
			        <option value="MMR">Birmane (Birmanie)</option>
			        <option value="GNB">Bissau-Guinéenne (Guinée-Bissau)</option>
			        <option value="BOL">Bolivienne (Bolivie)</option>
			        <option value="BIH">Bosnienne (Bosnie-Herzégovine)</option>
			        <option value="BWA">Botswanaise (Botswana)</option>
			        <option value="BRA">Brésilienne (Brésil)</option>
			        <option value="GBR">Britannique (Royaume-Uni)</option>
			        <option value="BRN">Brunéienne (Brunéi)</option>
			        <option value="BGR">Bulgare (Bulgarie)</option>
			        <option value="BFA">Burkinabée (Burkina)</option>
			        <option value="BDI">Burundaise (Burundi)</option>
			        <option value="KHM">Cambodgienne (Cambodge)</option>
			        <option value="CMR">Camerounaise (Cameroun)</option>
			        <option value="CAN">Canadienne (Canada)</option>
			        <option value="CPV">Cap-verdienne (Cap-Vert)</option>
			        <option value="CAF">Centrafricaine (Centrafrique)</option>
			        <option value="CHL">Chilienne (Chili)</option>
			        <option value="CHN">Chinoise (Chine)</option>
			        <option value="CYP">Chypriote (Chypre)</option>
			        <option value="COL">Colombienne (Colombie)</option>
			        <option value="COM">Comorienne (Comores)</option>
			        <option value="COG">Congolaise (Congo-Brazzaville)</option>
			        <option value="COD">Congolaise (Congo-Kinshasa)</option>
			        <option value="COK">Cookienne (Îles Cook)</option>
			        <option value="CRI">Costaricaine (Costa Rica)</option>
			        <option value="HRV">Croate (Croatie)</option>
			        <option value="CUB">Cubaine (Cuba)</option>
			        <option value="DNK">Danoise (Danemark)</option>
			        <option value="DJI">Djiboutienne (Djibouti)</option>
			        <option value="DOM">Dominicaine (République dominicaine)</option>
			        <option value="DMA">Dominiquaise (Dominique)</option>
			        <option value="EGY">Égyptienne (Égypte)</option>
			        <option value="ARE">Émirienne (Émirats arabes unis)</option>
			        <option value="GNQ">Équato-guineenne (Guinée équatoriale)</option>
			        <option value="ECU">Équatorienne (Équateur)</option>
			        <option value="ERI">Érythréenne (Érythrée)</option>
			        <option value="ESP">Espagnole (Espagne)</option>
			        <option value="TLS">Est-timoraise (Timor-Leste)</option>
			        <option value="EST">Estonienne (Estonie)</option>
			        <option value="ETH">Éthiopienne (Éthiopie)</option>
			        <option value="FJI">Fidjienne (Fidji)</option>
			        <option value="FIN">Finlandaise (Finlande)</option>
			        <option value="FRA">Française (France)</option>
			        <option value="GAB">Gabonaise (Gabon)</option>
			        <option value="GMB">Gambienne (Gambie)</option>
			        <option value="GEO">Georgienne (Géorgie)</option>
			        <option value="GHA">Ghanéenne (Ghana)</option>
			        <option value="GRD">Grenadienne (Grenade)</option>
			        <option value="GTM">Guatémaltèque (Guatemala)</option>
			        <option value="GIN">Guinéenne (Guinée)</option>
			        <option value="GUY">Guyanienne (Guyana)</option>
			        <option value="HTI">Haïtienne (Haïti)</option>
			        <option value="GRC">Hellénique (Grèce)</option>
			        <option value="HND">Hondurienne (Honduras)</option>
			        <option value="HUN">Hongroise (Hongrie)</option>
			        <option value="IND">Indienne (Inde)</option>
			        <option value="IDN">Indonésienne (Indonésie)</option>
			        <option value="IRQ">Irakienne (Iraq)</option>
			        <option value="IRN">Iranienne (Iran)</option>
			        <option value="IRL">Irlandaise (Irlande)</option>
			        <option value="ISL">Islandaise (Islande)</option>
			        <option value="ISR">Israélienne (Israël)</option>
			        <option value="ITA">Italienne (Italie)</option>
			        <option value="CIV">Ivoirienne (Côte d'Ivoire)</option>
			        <option value="JAM">Jamaïcaine (Jamaïque)</option>
			        <option value="JPN">Japonaise (Japon)</option>
			        <option value="JOR">Jordanienne (Jordanie)</option>
			        <option value="KAZ">Kazakhstanaise (Kazakhstan)</option>
			        <option value="KEN">Kenyane (Kenya)</option>
			        <option value="KGZ">Kirghize (Kirghizistan)</option>
			        <option value="KIR">Kiribatienne (Kiribati)</option>
			        <option value="KNA">Kittitienne et Névicienne (Saint-Christophe-et-Niévès)</option>
			        <option value="KWT">Koweïtienne (Koweït)</option>
			        <option value="LAO">Laotienne (Laos)</option>
			        <option value="LSO">Lesothane (Lesotho)</option>
			        <option value="LVA">Lettone (Lettonie)</option>
			        <option value="LBN">Libanaise (Liban)</option>
			        <option value="LBR">Libérienne (Libéria)</option>
			        <option value="LBY">Libyenne (Libye)</option>
			        <option value="LIE">Liechtensteinoise (Liechtenstein)</option>
			        <option value="LTU">Lituanienne (Lituanie)</option>
			        <option value="LUX">Luxembourgeoise (Luxembourg)</option>
			        <option value="MKD">Macédonienne (Macédoine)</option>
			        <option value="MYS">Malaisienne (Malaisie)</option>
			        <option value="MWI">Malawienne (Malawi)</option>
			        <option value="MDV">Maldivienne (Maldives)</option>
			        <option value="MDG">Malgache (Madagascar)</option>
			        <option value="MLI">Maliennes (Mali)</option>
			        <option value="MLT">Maltaise (Malte)</option>
			        <option value="MAR">Marocaine (Maroc)</option>
			        <option value="MHL">Marshallaise (Îles Marshall)</option>
			        <option value="MUS">Mauricienne (Maurice)</option>
			        <option value="MRT">Mauritanienne (Mauritanie)</option>
			        <option value="MEX">Mexicaine (Mexique)</option>
			        <option value="FSM">Micronésienne (Micronésie)</option>
			        <option value="MDA">Moldave (Moldovie)</option>
			        <option value="MCO">Monegasque (Monaco)</option>
			        <option value="MNG">Mongole (Mongolie)</option>
			        <option value="MNE">Monténégrine (Monténégro)</option>
			        <option value="MOZ">Mozambicaine (Mozambique)</option>
			        <option value="NAM">Namibienne (Namibie)</option>
			        <option value="NRU">Nauruane (Nauru)</option>
			        <option value="NLD">Néerlandaise (Pays-Bas)</option>
			        <option value="NZL">Néo-Zélandaise (Nouvelle-Zélande)</option>
			        <option value="NPL">Népalaise (Népal)</option>
			        <option value="NIC">Nicaraguayenne (Nicaragua)</option>
			        <option value="NGA">Nigériane (Nigéria)</option>
			        <option value="NER">Nigérienne (Niger)</option>
			        <option value="NIU">Niuéenne (Niue)</option>
			        <option value="PRK">Nord-coréenne (Corée du Nord)</option>
			        <option value="NOR">Norvégienne (Norvège)</option>
			        <option value="OMN">Omanaise (Oman)</option>
			        <option value="UGA">Ougandaise (Ouganda)</option>
			        <option value="UZB">Ouzbéke (Ouzbékistan)</option>
			        <option value="PAK">Pakistanaise (Pakistan)</option>
			        <option value="PLW">Palaosienne (Palaos)</option>
			        <option value="PSE">Palestinienne (Palestine)</option>
			        <option value="PAN">Panaméenne (Panama)</option>
			        <option value="PNG">Papouane-Néo-Guinéenne (Papouasie-Nouvelle-Guinée)</option>
			        <option value="PRY">Paraguayenne (Paraguay)</option>
			        <option value="PER">Péruvienne (Pérou)</option>
			        <option value="PHL">Philippine (Philippines)</option>
			        <option value="POL">Polonaise (Pologne)</option>
			        <option value="PRT">Portugaise (Portugal)</option>
			        <option value="QAT">Qatarienne (Qatar)</option>
			        <option value="ROU">Roumaine (Roumanie)</option>
			        <option value="RUS">Russe (Russie)</option>
			        <option value="RWA">Rwandaise (Rwanda)</option>
			        <option value="LCA">Saint-Lucienne (Sainte-Lucie)</option>
			        <option value="SMR">Saint-Marinaise (Saint-Marin)</option>
			        <option value="VCT">Saint-Vincentaise et Grenadine (Saint-Vincent-et-les Grenadines)</option>
			        <option value="SLB">Salomonaise (Îles Salomon)</option>
			        <option value="SLV">Salvadorienne (Salvador)</option>
			        <option value="WSM">Samoane (Samoa)</option>
			        <option value="STP">Santoméenne (Sao Tomé-et-Principe)</option>
			        <option value="SAU">Saoudienne (Arabie saoudite)</option>
			        <option value="SEN">Sénégalaise (Sénégal)</option>
			        <option value="SRB">Serbe (Serbie)</option>
			        <option value="SYC">Seychelloise (Seychelles)</option>
			        <option value="SLE">Sierra-Léonaise (Sierra Leone)</option>
			        <option value="SGP">Singapourienne (Singapour)</option>
			        <option value="SVK">Slovaque (Slovaquie)</option>
			        <option value="SVN">Slovène (Slovénie)</option>
			        <option value="SOM">Somalienne (Somalie)</option>
			        <option value="SDN">Soudanaise (Soudan)</option>
			        <option value="LKA">Sri-Lankaise (Sri Lanka)</option>
			        <option value="ZAF">Sud-Africaine (Afrique du Sud)</option>
			        <option value="KOR">Sud-Coréenne (Corée du Sud)</option>
			        <option value="SSD">Sud-Soudanaise (Soudan du Sud)</option>
			        <option value="SWE">Suédoise (Suède)</option>
			        <option value="CHE">Suisse (Suisse)</option>
			        <option value="SUR">Surinamaise (Suriname)</option>
			        <option value="SWZ">Swazie (Swaziland)</option>
			        <option value="SYR">Syrienne (Syrie)</option>
			        <option value="TJK">Tadjike (Tadjikistan)</option>
			        <option value="TZA">Tanzanienne (Tanzanie)</option>
			        <option value="TCD">Tchadienne (Tchad)</option>
			        <option value="CZE">Tchèque (Tchéquie)</option>
			        <option value="THA">Thaïlandaise (Thaïlande)</option>
			        <option value="TGO">Togolaise (Togo)</option>
			        <option value="TON">Tonguienne (Tonga)</option>
			        <option value="TTO">Trinidadienne (Trinité-et-Tobago)</option>
			        <option value="TUN">Tunisienne (Tunisie)</option>
			        <option value="TKM">Turkmène (Turkménistan)</option>
			        <option value="TUR">Turque (Turquie)</option>
			        <option value="TUV">Tuvaluane (Tuvalu)</option>
			        <option value="UKR">Ukrainienne (Ukraine)</option>
			        <option value="URY">Uruguayenne (Uruguay)</option>
			        <option value="VUT">Vanuatuane (Vanuatu)</option>
			        <option value="VAT">Vaticane (Vatican)</option>
			        <option value="VEN">Vénézuélienne (Venezuela)</option>
			        <option value="VNM">Vietnamienne (Viêt Nam)</option>
			        <option value="YEM">Yéménite (Yémen)</option>
			        <option value="ZMB">Zambienne (Zambie)</option>
			        <option value="ZWE">Zimbabwéenne (Zimbabwe)</option>
				</select>
			</div>

			<h4 class="mt-sm-4">Quel type de compte souhaitez-vous créer?</h4>
			
			<div class="radio">
				<label class="radio-inline"><input type="radio" name="choix" checked value="1"> Compte Titre</label>
				<label class="radio-inline"><input type="radio" name="choix" value="2"> Compte Bancaire</label>
			</div>
			
			<button type="submit" class = "btn-default">S'inscrire</button>
		</div>
	</div>
</form>
</div>   
			
<!-- FIN JUMBOTRON -->
		
<%@include file="footer.jsp" %>
</body>
</html>

