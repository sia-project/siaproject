<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Utilisateur"%>
<%@ page import="model.DAO"%>
<jsp:include page="addcoffret.jsp" />
<%
		session = request.getSession();
		Utilisateur userSession = (Utilisateur) session.getAttribute("admin");
	%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <div id="header"></div>
 <div id="sidebar"></div>
 <div class="content-wrapper">
      <section class="content-header">
          <h1>
            Créer un coffret  
          </h1>
       
      </section>
      <section class="content">
          <div class="box box-primary " style="width: 80%">
            
            <!-- /.box-header -->
            <div class="box-body">
              <!-- /.form-group -->
              <div class="form-group">
                <label>Droits</label>
                <select class="form-control select2 " multiple="multiple" data-placeholder="Selectionner produit"
                        style="width: 100%;">
                  <option>Coffret sardines & sardines</option>
                  <option>Petite marmite noisettes de st-jacques coco</option>
                  <option>Rillettes de lieu aux baies de sichuan</option>
                  <option>Emietté de Sardine citron, olives et amandes</option>
                </select>

              </div>
                <div class="form-group">
                
                  <label for="nom" class="col-sm-2 control-label">Quantité</label>

                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="nom" placeholder="introduisez le nombre de coffrets">
                  </div>
                </div>
                <div class="form-group">
                
                  <label for="prix" class="col-sm-2 control-label">Prix</label>

                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="prix" placeholder="introduisez le prix du coffret">
                  </div>
                </div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
                <button  class="btn btn-primary pull-right">Créer</button>
            </div>
          </div>
      </section>
 </div>
 <div id="footer"></div>

 </div>