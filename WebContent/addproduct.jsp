<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Utilisateur"%>
<%@ page import="model.DAO"%>



	
</head>
<body class="hold-transition skin-blue sidebar-mini">
 <style>
        .jumbotron h1, .jumbotron p {
            padding-left: 60px;
            padding-right: 60px;
        }

        .col-md {
            margin: 0 auto;
            max-width: 500px
        }
        .jumbotron{
          background-color:#3c8dbc33;
        }
    </style>
<!--L'accès à cette page doit etre protégé, un client ne peut pas ajouter de produit, vérification de variable session (ou autre) une fois lié au back -->

<div class="wrapper">

 <div id="header"></div>
 <div id="sidebar"></div>
 <div class="content-wrapper">
   <form class="form-horizontal" action="manageProduct?page=addproduct" method="post">
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


<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_price">Prix HT</label>  
  <div class="col-md-4">
  <input id="product_name" name="product_name" class="form-control input-md" required="" type="number" required>
    
  </div>
</div>


<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_brand">Marque du produit</label>  
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
    <option value="saab">Sardine</option>
    <option value="volvo">Thon</option>
    <option value="mercedes">Saumon</option>
    <option value="audi">Coffret</option> 
    </select>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_place">Place rayon</label>  
  <div class="col-md-4">
  <input id="available_quantity" name="available_quantity" class="form-control input-md" required="" type="text">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_weight">Poids</label>  
  <div class="col-md-4">
  <input id="product_weight" name="product_weight" class="form-control input-md" required="" type="number">
    
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
</div>
 <div id="footer"></div>
 </div>
 <script src="bower_components/jquery/dist/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="bower_components/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<script src="dist/js/modernizr.custom.js"></script>
  <!-- Custom-JavaScript-File-Links --> 
  <!-- cart-js -->
  <script src="dist/js/minicart.min.js"></script>
<script>
  // Mini Cart
  paypal.minicart.render({
    action: '#'
  });

  if (~window.location.search.indexOf('reset=true')) {
    paypal.minicart.reset();
  }
</script>



  <!-- //cart-js --> 
<!-- script for responsive tabs -->           
<script src="dist/js/easy-responsive-tabs.js"></script>
<script>
  $(document).ready(function () {
  $('#horizontalTab').easyResponsiveTabs({
  type: 'default', //Types: default, vertical, accordion           
  width: 'auto', //auto or any width like 600px
  fit: true,   // 100% fit in a container
  closed: 'accordion', // Start closed if in accordion view
  activate: function(event) { // Callback function if tab is switched
  var $tab = $(this);
  var $info = $('#tabInfo');
  var $name = $('span', $info);
  $name.text($tab.text());
  $info.show();
  }
  });
  $('#verticalTab').easyResponsiveTabs({
  type: 'vertical',
  width: 'auto',
  fit: true
  });
  });
</script>
  <script src="dist/js/jquery.waypoints.min.js"></script>
  <script src="dist/js/jquery.countup.js"></script>
  <script>
    $('.counter').countUp();
  </script>
<!-- //stats -->
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="dist/js/move-top.js"></script>
<script type="text/javascript" src="dist/js/jquery.easing.min.js"></script>
<script type="text/javascript">
  jQuery(document).ready(function($) {
    $(".scroll").click(function(event){   
      event.preventDefault();
      $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
    });
  });
</script>
<!-- here stars scrolling icon -->
  <script type="text/javascript">
    $(document).ready(function() {
      /*
        var defaults = {
        containerID: 'toTop', // fading element id
        containerHoverID: 'toTopHover', // fading element hover id
        scrollSpeed: 1200,
        easingType: 'linear' 
        };
      */
                
      $().UItoTop({ easingType: 'easeOutQuart' });
                
      });
  </script>
<!-- Bootstrap 3.3.7 -->
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="bower_components/select2/dist/js/select2.full.min.js"></script>
<!-- Morris.js charts -->
<script src="bower_components/raphael/raphael.min.js"></script>
<script src="bower_components/morris.js/morris.min.js"></script>
<!-- Sparkline -->
<script src="bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="bower_components/jquery-knob/dist/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="bower_components/moment/min/moment.min.js"></script>
<script src="bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="dist/js/pages/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->

<script src="dist/js/demo.js"></script>
<script type="text/javascript">
  
  $("#sidebar").load("sidebar.html");
 
  $("#header").load("header.html");  
  $("#footer").load("footer.html"); 
  
</script>
</body>