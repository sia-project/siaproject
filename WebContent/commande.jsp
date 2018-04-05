<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Utilisateur"%>
<%@ page import="model.DAO"%>
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


<div id="header"> </div>

<!--Add Product -->
  
  
   <div class="">
       <div class="page-title">
         <div class="title_left">
           <h3>
                 <a href="commande.html">Commande</a>
                 <small>
                     / Passer une commande
                 </small>
             </h3>
         </div>

</div>
     
					
						 
               </div>
               <!-- /.col -->
             </div>
             <!-- info row -->
             <div class="row invoice-info">
               <div class="col-sm-4 invoice-col">               
                 Client N° : ${utilisateur.uId}
                 <address>                 
                     <br> Nom: ${utilisateur.nom}
                     <br>Telephone: ${utilisateur.telPrincipal}
                     <br>Mail: ${utilisateur.adrMail}
                  </address>
               </div>
             </div>
             <!-- /.row -->

             <!-- Table row -->
             <div class="row">
               <div class="col-xs-12 table">
                 <c:forEach items="${produit.list}" var="produit" >            
                 	<div class="x_panel" >
   			        	<br> <strong> <a href="produit_detail.html?id=${produit.id}"> Produit N° : 65 </a></strong>,
			        	<br>  <strong> quantité   : 5 </strong>
                        
			        	<br> <h2>Total  :  Euro</h2>
					</div>
				 </c:forEach>
               </div>
               <!-- /.col -->
             </div>
             <!-- /.row -->
             
           </section>
       </div>
         </div>
         
     <div class="x_panel">
         <form method="POST">
         	<div class="col-xs-12">
         	   <button class="btn btn-default pull-right" onclick="printdiv('commandePrint');"><i class="fa fa-print"></i> Imprimer</button>
					<c:if test="${commande.etatCmd == 'EN COURS'}">         	   	
	         	   		<button type="submit" name="clickedButton" value="confirmerenvoi" class="btn btn-success pull-left" ><i class="fa fa-credit-card"></i> Confirmer l'envoi</button>
					</c:if>
					<c:if test="${commande.etatCmd == 'EN PREPARATION' }">         	   	
         	   			<button type="submit" name="clickedButton" value="confirmepaiement" class="btn btn-success pull-left" ><i class="fa fa-credit-card"></i> Confirmer le paiement</button>
					</c:if>
	        </div>
         </form>   
     	
     	</div>
     
     
     </div>
   </div>
 </div>
 
 <script>
function printdiv(printpage)
{
var headstr = "<html><head><title></title></head><body>";
var footstr = "</body>";
var newstr = document.all.item(printpage).innerHTML;
var oldstr = document.body.innerHTML;
document.body.innerHTML = newstr+footstr;
window.print();
document.body.innerHTML = oldstr;
return false;
}
</script>

  



  