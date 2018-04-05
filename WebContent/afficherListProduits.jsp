<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Utilisateur"%>
<%@ page import="model.DAO"%>
<jsp:include page="afficherListProduits.jsp" />
<%
		session = request.getSession();
		Utilisateur userSession = (Utilisateur) session.getAttribute("admin");
	%>
	</head>
<body>


    <style>
        .jumbotron h1, .jumbotron p {
            padding-left: 60px;
            padding-right: 60px;
        }

        .col-md {
            margin: 0 auto;
            max-width: 500px
        }
    </style>
<!--L'accès à cette page doit etre protégé, un client ne peut pas ajouter de famille produit, vérification de variable session (ou autre) une fois lié au back -->


<div id="header"> </div>

<!--Add Product -->
  
  
  <div class="">
          <div class="page-title">
            <div class="title_left">
              <h3>La liste des produits </h3>
            </div>

            <div class="title_right">
              <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="Search for...">
                  <span class="input-group-btn">
                            <button class="btn btn-default" type="button">Go!</button>
                        </span>
                </div>
              </div>
            </div>
          </div>
        <!-- top tiles -->
        <div class="row tile_count">

            <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel">
                <div class="x_title">
                  <h2> </h2>
                  
                  <div class="clearfix"></div>
                </div>

				
                <div class="x_content">

                  <strong>Liste de tous les produits disponibles dans le stock</strong>
                  <div class="clearfix"></div>

                  <table class="table table-striped responsive-utilities jambo_table bulk_action">
                    <thead>
                      <tr class="headings">
                        <th class="column-title">ID PRODUIT </th>
	                    <th class="column-title">Libelé </th>
						<th class="column-title">Marque </th>
	                    <th class="column-title">Description </th>
	                    <th class="column-title">Poids </th>
	                    <th class="column-title">Prix HT </th>
	                    <th class="column-title">famille du produit </th>
					    <th class="column-title">gamme du  produit </th>

						<th class="column-title no-link last"><span class="nobr">Action</span>
                      </tr>
                    </thead>
					<tbody>
                      <c:forEach items="${listProduit}" var="produit" >
		        		<tr>
						<td>${produit.prodId}</td>
						<td>${produit.libelle}</td>
						<td>${produit.marque}</td>
						<td>${produit.description}</td>
						<td>${produit.poids}</td>
						<td>${produit.prixHT}</td>
						<td>${produit.familleProduitId}</td>
						<td>${produit.gammeProduitId}</td>

						
						<td>
							<a href="produit_detail.html?id=${produit.prodId}">Afficher</a>
						</td>					
	        			</tr>
					</c:forEach>
                     
                    </tbody>

                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
  
  
  



  