<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Utilisateur"%>
<%@ page import="model.DAO"%>
<jsp:include page="listproduit.jsp" />
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
          Liste des produits
        </h1>
       
      </section>

      <!-- Main content -->
      <section class="content">
      <div class="box box-primary">
      <div class="box-body">
      <table id="example" class="table table-striped table-bordered" style="width:100%">
        <thead>
            <tr>
                <th>Réf</th>
                <th>Désignation</th>
                <th>Famille</th>
                <th>Gamme</th>
                <th>Marque</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>762</td>
                <td>Emietté de Sardine citron, olives et amandes</td>
                <td>Classique</td>
                <td>Sardine</td>
                <td>Tradition Océan</td>
                <td>
                  <a href="#" class="view" title="View" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>
                  <a href="#" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                  <a href="#" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                </td>
            </tr>
            <tr>
                <td>4852</td>
                <td>Coffret sardines & sardines</td>
                <td>Assortiment</td>
                <td>Sardine</td>
                <td>Tradition Océan</td>
                <td>
                  <a href="#" class="view" title="View" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>
                  <a href="#" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                  <a href="#" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                </td>
            </tr>
            <tr>
                <td>755</td>
                <td>Petite marmite noisettes de st-jacques coco et gingembre</td>
                <td>Spécialité</td>
                <td>Coquillage</td>
                <td>Nouveauté Océan</td>
                <td>
                  <a href="#" class="view" title="View" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>
                  <a href="#" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                  <a href="#" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                </td>
            </tr>
             <tr>
                <td>813</td>
                <td>Rillettes de lieu aux baies de sichuan</td>
                <td>Emiétté</td>
                <td>Lieu</td>
                <td>Nouveauté Océan</td>
                <td>
                  <a href="#" class="view" title="View" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>
                  <a href="#" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                  <a href="#" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                </td>
            </tr>

            
           
            </tbody>
             <tfoot>
            <tr>
                <th>Réf</th>
                <th>Désignation</th>
                <th>Famille</th>
                <th>Gamme</th>
                <th>Marque</th>
                <th>Actions</th>
            </tr>
        </tfoot>
        </table>
        </div>
        </div>

      </section>
</div>
 <div id="footer"></div>
</div>