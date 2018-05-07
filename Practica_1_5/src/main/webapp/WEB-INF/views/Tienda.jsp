<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="st" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
  <title>Tienda</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default rounded borders and increase the bottom margin */ 
    .navbar {
      margin-bottom: 50px;
      border-radius: 0;
    }
    
    /* Remove the jumbotron's default bottom margin */ 
     .jumbotron {
      margin-bottom: 0;
    }
   
    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
    }
  </style>
</head>
<body>

<div class="jumbotron">
  <div class="container text-center">
    <h1>Telema Store</h1>      
    <p>Merchandising</p>
  </div>
</div>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Inicio</a></li>
        <li><a href="Productos">Productos</a></li>
        <li><a href="#">Deals</a></li>
        <li><a href="#">Stores</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="CambioDatos"><span class="glyphicon glyphicon-user"></span> <st:out value="${nusuario.nombre}"/></a></li>
        <li><a href="carrito"><span class="glyphicon glyphicon-shopping-cart"></span> Carrito ${tam}</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="container">    
  <div class="row">
  <st:forEach items= "${lista}" var="producto">
  <st:if test= "${producto.id == 1}">
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading"><st:out value="${producto.nombre}"/></div>
        <div class="panel-body"><img src="resources/imagenes/img1.png" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Precio:${producto.precio}</div>
        <div><form action="añadir" method=post>
		 <input type="hidden" name="articulo" value="1">
<input type=submit value=Añadir></form></div>
      </div>
    </div>
    </st:if>
      <st:if test= "${producto.id == 2}">
    <div class="col-sm-4"> 
      <div class="panel panel-danger">
        <div class="panel-heading">${producto.nombre}</div>
        <div class="panel-body"><img src="resources/imagenes/img2.png" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Precio:${producto.precio}</div>
              <div><form action="añadir" method=post>
		 <input type="hidden" name="articulo" value="2">
<input type=submit value=Añadir></form></div>
      </div>
    </div>
    </st:if>
    <st:if test= "${producto.id == 3}">
    <div class="col-sm-4"> 
      <div class="panel panel-success">
        <div class="panel-heading">${producto.nombre}</div>
        <div class="panel-body"><img src="resources/imagenes/img3.png" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Precio:${producto.precio}</div>
              <div><form action="añadir" method=post>
		 <input type="hidden" name="articulo" value="3">
<input type=submit value=Añadir></form></div>
      </div>
    </div>
    </st:if>
    </st:forEach>
  </div>
</div><br>
<div class="container">    
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">Pack Figuras Coleccionables</div>
        <div class="panel-body"><img src="resources/imagenes/img4.png" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
    <div class="col-sm-4"> 
      <div class="panel panel-primary">
        <div class="panel-heading">Pack Figuras POP</div>
        <div class="panel-body"><img src="resources/imagenes/img5.png" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
    <div class="col-sm-4"> 
      <div class="panel panel-primary">
        <div class="panel-heading">Pack Juegos de Mesa</div>
        <div class="panel-body"><img src="resources/imagenes/img6.png" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
  </div>

</div><br><br>
</body>
</html>
