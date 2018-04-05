<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Utilisateur"%>
<%@ page import="model.DAO"%>
<jsp:include page="addgammeproduct.jsp" />
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
  

<form class="form-horizontal">
<fieldset>

<!-- Form Name -->
<center><legend>Ajouter une gamme produit</legend></center>



<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="gamme_product_name">Libelé de la gamme du produit</label>  
  <div class="col-md-4">
  <input id="gamme_product_name" name="gamme_product_name" class="form-control input-md" required="" type="text" required>
    
  </div>
</div>

<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="familly_product_description">Description de la famille du produit</label>
  <div class="col-md-4">                     
    <textarea class="form-control" id="gamme_product_description" name="gamme_product_description" required></textarea>
  </div>
</div>

<!-- Textarea -->

<!-- Select Basic : A rajouter dynamiquement les options du select : les récupérer de la bdd et les insérer ici, à faire une fois nos travaux mis en commun-->





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


  