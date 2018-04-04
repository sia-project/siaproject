<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Utilisateur"%>
<%@ page import="model.DAO"%>
<jsp:include page="addNouveauUser.jsp" />
<%
		session = request.getSession();
		Utilisateur userSession = (Utilisateur) session.getAttribute("admin");
	%>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <div id="header"></div>
 <div id="sidebar"></div>
 
<!-- ./wrapper -->

<div class="content-wrapper">
            <section class="content-header">
              <h1>
               Créer un utilisateur
              </h1>
         
            </section>
           
                       <!-- /.box-header -->
            <!-- form start -->
            <section class="content">
            <div class="box box-primary"   style="width: 80%">
            <form class="form-horizontal">
              <div class="box-body">


            
              

                <div class="form-group">
                
                  <div class="col-sm-2">
                  </div>

                  <div class="col-sm-10">
                      <div  type="radio">
                      <label ><input  type="radio" name="civilite" required="">
                      M</label>
                      <label><input  type="radio" name="civilite" required="">
                      Mme</label>
                
                  </div>
                  </div>
                </div>

                <div class="form-group">
                
                  <label for="nom" class="col-sm-2 control-label">Nom</label>

                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="nom" placeholder="Nom">
                  </div>
                </div>

                <div class="form-group">
                
                  <label for="prenom" class="col-sm-2 control-label">Prénom</label>

                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="prenom" placeholder="Prénom">
                  </div>
                </div>


                <div class="form-group">
                
                  <label for="date" class="col-sm-2 control-label">Date de naissance</label>

                  <div class="col-sm-10">
                    <input  id="date" name="date" class="form-control" type="text" placeholder="Date de naissance"/>
                  </div>
                </div>

                <div class="form-group">
                
                  <label for="adr" class="col-sm-2 control-label">Adresse</label>

                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="adr" placeholder="Adresse">
                  </div>
                </div>
                  <div class="form-group">
                
                  <label for="codepost" class="col-sm-2 control-label">Code Postal</label>

                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="adr" placeholder="Code Postal">
                  </div>
                </div>

                <div class="form-group">
                
                  <label for="ville" class="col-sm-2 control-label">Ville</label>

                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="ville" placeholder="Ville">
                  </div>
                </div>

                 <div class="form-group">
                
                  <label for="tel" class="col-sm-2 control-label">Tel</label>

                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="tel" placeholder="N° de Téléphone">
                  </div>
                </div>

                <div class="form-group">

                  <label for="inputEmail3" class="col-sm-2 control-label">Email</label>

                  <div class="col-sm-10">
                    <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputPassword3" class="col-sm-2 control-label">Mot de passe</label>

                  <div class="col-sm-10">
                    <input type="password" class="form-control" id="inputPassword3" placeholder="Mot de passe">
                  </div>
                </div>
                
              <!-- /.box-body -->
              <div class="box-footer">
                <button type="submit" class="btn btn-primary pull-right">Ajouter</button>
              </div>
              <!-- /.box-footer -->
            </form>
            </div>
            </section>
          

</div>
<div id="footer"></div>

</div>