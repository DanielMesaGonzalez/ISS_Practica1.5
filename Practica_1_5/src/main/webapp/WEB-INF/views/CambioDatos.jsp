<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MODIFICAR</title>
</head>
<body>
<h1>MODIFICAR DATOS</h1>
<form action="/proyectoMVC/cambioOK" method=post>
Name: <input type=text name=username maxlength="30" required/>
Surname: <input type=text name=surname maxlength="30" required/>
Email: <input type=text name=email maxlength="30" required/>
Contraseņa: <input type=text name=pass maxlength="30" required/>
<input type=submit value=Send>
</form>
${respuesta}${email}
</body>
</html>