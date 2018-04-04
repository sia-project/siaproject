<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Utilisateur"%>
<%@ page import="model.DAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>La Vieille Sardine</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="css/style.css" type="text/css" />
<link rel="stylesheet" href="css/font-awesome.css" type="text/css" />
<link rel="stylesheet" href="css/easy-responsive-tabs.css"
	type="text/css" />
<link rel="stylesheet" href="css/bootstrap-datepicker.css"
	type="text/css" />
<link
	href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800"
	rel="stylesheet">
<link
	href='//fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,900,900italic,700italic'
	rel='stylesheet' type='text/css'>
</head>
<div class="header" id="home">
	<%
		session = request.getSession();
		Utilisateur userSession = (Utilisateur) session.getAttribute("user");
	%>
	<div class="container">
		<ul>


			<li><a href="#"><i class="fa fa-external-link-square"
					aria-hidden="true"></i> Accès Admin</a></li>
			<li><a href="#" data-toggle="modal" data-target="#myModal3"><i
					class="glyphicon glyphicon-share-alt" aria-hidden="true"></i> Nos
					boutiques</a></li>
			<li><a href="#" data-toggle="modal" data-target="#myModal"><i
					class="fa fa-unlock-alt" aria-hidden="true"></i> Connexion </a></li>
			<li><a href="#" data-toggle="modal" data-target="#myModal2"><i
					class="fa fa-pencil-square-o" aria-hidden="true"></i> Inscription </a></li>
			<li><a href="gallery/grid/index.html"" ><i
					class="glyphicon glyphicon-modal-window" aria-hidden="true"></i>
					Gallerie</a></li>
			<li><a href="contact.html"><i
					class="glyphicon glyphicon-modal-window" aria-hidden="true"></i>
					Contact</a></li>
			<li><a href="about.html"><i
					class="glyphicon glyphicon-menu-right" aria-hidden="false"></i> A
					Propos </a></li>
			<li><a href="suivi/index.html"><i class="fa-location-arrow"
					aria-hidden="false"></i>Suivre ma commande</a></li>


		</ul>
	</div>
</div>
<!-- //header -->
<!-- header-bot -->
<div class="header-bot">
	<div class="header-bot_inner_wthreeinfo_header_mid">
		<div class="col-md-4 header-middle">
			<form action="#" method="post">
				<input type="search" name="search" placeholder="Rechercher..."
					required=""> <input type="submit" value=" ">
				<div class="clearfix"></div>
			</form>
		</div>
		<!-- header-bot -->
		<div class="col-md-4 logo_agile">
			<h1>
				<a href="index.html"><span>L</span>a Vieille Sardine </a>
			</h1>
		</div>
		<!-- header-bot -->

		<div class="clearfix"></div>
	</div>
</div>
<!-- //header-bot -->
<!-- banner -->
<div class="ban-top">
	<div class="container">
		<div class="top_nav_left">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
					</div>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse menu--shylock"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav menu__list">
							<li class="active menu__item menu__item--current"><a
								class="menu__link" href="index.html">Acceuil <span
									class="sr-only">(current)</span></a></li>
							<li class="dropdown menu__item"><a href="#"
								class="dropdown-toggle menu__link" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Sardines
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu multi-column columns-3">
									<div class="agile_inner_drop_nav_info">
										<div class="col-sm-3 multi-gd-img">
											<ul class="multi-column-dropdown">
												<li><a href="womens.html">Promotions</a></li>
												<li><a href="womens.html">Classiques</a></li>
												<li><a href="womens.html">Assortiments</a></li>
											</ul>
										</div>
										<div class="col-sm-6 multi-gd-img multi-gd-text ">
											<a href="womens.html"><img src="images/top2.jpg" alt=" " /></a>
										</div>
										<div class="clearfix"></div>
									</div>
								</ul></li>
							<li class="dropdown menu__item"><a href="#"
								class="dropdown-toggle menu__link" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Thon
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu multi-column columns-3">
									<div class="agile_inner_drop_nav_info">
										<div class="col-sm-3 multi-gd-img">
											<ul class="multi-column-dropdown">
												<li><a href="womens.html">Promotions</a></li>
												<li><a href="womens.html">Classiques</a></li>
												<li><a href="womens.html">Assortiments</a></li>
											</ul>
										</div>
										<div class="col-sm-6 multi-gd-img multi-gd-text ">
											<a href="womens.html"><img src="images/top1.jpg" alt=" " /></a>
										</div>
										<div class="clearfix"></div>
									</div>
								</ul></li>

							<li class="dropdown menu__item"><a href="#"
								class="dropdown-toggle menu__link" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Saumon
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu multi-column columns-3">
									<div class="agile_inner_drop_nav_info">
										<div class="col-sm-3 multi-gd-img">
											<ul class="multi-column-dropdown">
												<li><a href="womens.html">Classiques</a></li>
												<li><a href="womens.html">Spécialités</a></li>
											</ul>
										</div>
										<div class="col-sm-6 multi-gd-img multi-gd-text ">
											<a href="womens.html"><img src="images/top3.jpg" alt=" " /></a>
										</div>
										<div class="clearfix"></div>
									</div>
								</ul></li>


							<li class="dropdown menu__item"><a href="#"
								class="dropdown-toggle menu__link" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Coffrets
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu multi-column columns-3">
									<div class="agile_inner_drop_nav_info">
										<div class="col-sm-3 multi-gd-img">
											<ul class="multi-column-dropdown">
												<li><a href="womens.html">Cadeaux</a></li>
												<li><a href="womens.html">Apéro</a></li>
											</ul>
										</div>
										<div class="col-sm-6 multi-gd-img multi-gd-text ">
											<a href="womens.html"><img src="images/top4.jpg" alt=" " /></a>
										</div>
										<div class="clearfix"></div>
									</div>
								</ul></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
		<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Modal Header</h4>
					</div>
					<div class="modal-body">
						<p>Some text in the modal.</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>
		<div class="top_nav_right">
			<div class="wthreecartaits wthreecartaits2 cart cart box_1">
				<form action="https://www.paypal.com/cgi-bin/webscr" method="post">
					<button class="w3view-cart" type="submit" name="submit" value="">
						<i class="fa fa-cart-arrow-down" aria-hidden="true"></i>
					</button>
					<input type="hidden" name="cmd" value="_cart" /> <input
						type="hidden" name="add" value="1" /> <input type="hidden"
						name="business" value="example@minicartjs.com" /> <input
						type="hidden" name="item_name" value="Livraison" /> <input
						type="hidden" name="quantity" value="1" /> <input type="hidden"
						name="amount" value="8.00" /> <input type="hidden"
						name="discount_amount" value="8.00" /> <input type="hidden"
						name="currency_code" value="USD" /> <input type="hidden"
						name="return" value=" " /> <input type="hidden" name="shipping"
						value="1.00"> <input type="submit" name="submit"
						value="Payer" class="btn btn-info" />
				</form>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<!-- //banner-top -->
<!-- Modal1 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body modal-body-sub_agile">
				<div id="connexion"
					class="col-md-8 modal_body_left modal_body_left1"></div>

				<div class="clearfix"></div>
			</div>
		</div>
		<!-- //Modal content-->
	</div>
</div>
<!-- //Modal1 -->
<!-- Modal2 -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body modal-body-sub_agile">
				<div id="forminscription"
					class="col-md-8 modal_body_left modal_body_left1">
					<h3 class="agileinfo_sign">
						Inscription <span>Maintenant</span>
					</h3>
					<form action="manageAccount?page=registerAccount" method="post">
						<div class="form-check">
							<input class="form-check-input" type="checkbox"
								onchange="dynInput();" id="Check1"> <label
								class="form-check-label" for="Check1" name="cbPro"> Client
								professionnel </label>
						</div>
						<div>
							<span>Civilité</span> <span></span>
						</div>
						<div type="radio">
							<label><input type="ra()dio" name="civilite" required="">
								M</label> <label><input type="radio" name="civilite" required="">
								Mme</label> <span></span>
						</div>

						<div class="styled-input agile-styled-input-top">
							<input type="text" name="nom" required=""> <label>Nom</label>
							<span></span>
						</div>
						<div>
							<div id="prenom" class="styled-input agile-styled-input-top">
								<input type="text" name="prenom" required=""> <label>Prénom</label>
								<span></span>
							</div>
						</div>
						<div id="c1" class="styled-input">
							<input type="text" name="entNom" required=""> <label>Nom
								de l'entreprise</label> <span></span>
						</div>

						<div id="c2" class="styled-input">
							<input type="text" name="siret" required=""> <label>Siret</label>
							<span></span>
						</div>
						<div class="styled-input">
							<input type="text" name="adresse" required=""> <label>Adresse</label>
							<span></span>
						</div>

						<div class="styled-input">
							<input type="text" name="cp" required=""> <label>Code
								Postal</label> <span></span>
						</div>


						<div class="styled-input">
							<input type="text" name="ville" required=""> <label>Ville</label>
							<span></span>
						</div>
						<div class="styled-input">
							<input type="text" name="tel" required=""> <label>Téléphone</label>
							<span></span>
						</div>
						<div class="styled-input">
							<input type="email" name="email" required=""> <label>Email</label>
							<span></span>
						</div>
						<div class="styled-input">
							<input type="password" name="password" required=""> <label>Mot
								de passe</label> <span></span>
						</div>
						<div class="styled-input">
							<input type="password" name="passwordConfirm" required="">
							<label>Confirmation du mot de passe</label> <span></span>
						</div>
						<input type="submit" value="Valider">
					</form>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<!-- //Modal content-->
	</div>
</div>
<!-- //Modal2 -->
<script>
    $(document).ready(function(){
      dynInput();
    })

function dynInput(){
	 
	 var c1=document.getElementById('c1');
	 var c2=document.getElementById('c2');
	
	
	if(document.getElementById('Check1').checked == true){
		c1.style.display ='block';
		c2.style.display='block';
	}
	if(document.getElementById('Check1').checked == false){
		c1.style.display ='none';
		c2.style.display='none';
       
	}
}
</script>