<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Utilisateur"%>
<%@ page import="model.DAO"%>
<jsp:include page="addproduct.jsp" />
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
<!--L'accès à cette page doit etre protégé, un client ne peut pas ajouter de produit, vérification de variable session (ou autre) une fois lié au back -->


<div id="header"> </div>

<!--Add Product -->
  

<form class="form-horizontal">
<fieldset>

<!-- Form Name -->
<center><legend>Ajouter un produit</legend></center>



<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_name">Nom du produit</label>  
  <div class="col-md-4">
  <input id="product_name" name="product_name" class="form-control input-md" required="" type="text" required>
    
  </div>
</div>

<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_description">Description</label>
  <div class="col-md-4">                     
    <textarea class="form-control" id="product_description" name="product_description" required></textarea>
  </div>
</div>

<!-- Textarea -->

<!-- Select Basic : A rajouter dynamiquement les options du select : les récupérer de la bdd et les insérer ici, à faire une fois nos travaux mis en commun-->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_categorie">Catégorie</label>
  <div class="col-md-4">
    <select id="product_categorie" name="product_categorie" class="form-control" required> 
    </select>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="available_quantity">Quantité</label>  
  <div class="col-md-4">
  <input id="available_quantity" name="available_quantity" class="form-control input-md" required="" type="text">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_weight">Poids</label>  
  <div class="col-md-4">
  <input id="product_weight" name="product_weight" class="form-control input-md" required="" type="text">
    
  </div>
</div>
<div class="jumbotron">
      <div class="col-md">
        <div class="dropzone"></div>
      </div>
    </div>

    <script type="text/javascript" src="imgur-master/build/imgur.min.js"></script>
    <script type="text/javascript">
        var feedback = function (res) {
            if (res.success === true) {
                var status = document.querySelector('.status');
                var p = document.createElement('p');
                var t = document.createTextNode('Image url: ' + res.data.link);

                p.appendChild(t);

                status.classList.add('bg-success');
                status.appendChild(p);
                // document.querySelector('.status').innerHTML = 'Image url: ' + res.data.link;
            }
        };

        new Imgur({
            clientid: 'cc86a8de0e7c459',
            callback: feedback
        });
    </script>


<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="singlebutton"></label>
  <div class="col-md-4">
    <center><button id="singlebutton" name="singlebutton" class="btn btn-primary">Valider</button></center>
  </div>
  </div>

</fieldset>
</form>


  