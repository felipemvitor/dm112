<%--
  Created by IntelliJ IDEA.
  User: nsd_jsouza
  Date: 10/05/2020
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LogisticaDM112</title>
</head>
<body>
<h1>File index.html ok!</h1> <br/> Serviços disponíveis: <br/>
<a href="http://localhost:8080/LogisticaDM112/api/orders">Lista os pedidos disponíveis para entrega</a>
<br/>
<a href="http://localhost:8080/LogisticaDM112/api/delivery/start">[PUT]Seleciona o pedido para a entrega</a>
<br/>
<a href="http://localhost:8080/LogisticaDM112/api/delivery/finish">[POST]Registra entrega do pedido</a>
</body>
</html>
