<%--
  Created by IntelliJ IDEA.
  User: jicl
  Date: 16/5/5
  Time: 下午5:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.User"%>
<%
    String username=((User)session.getAttribute("user")).getUsername();
%>
<html>
<head>
    <title>Deniece Bookstore-Books</title>
    <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="basic.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Deniece Bookstore</a>
        </div>

        <ul class="nav navbar-nav">
            <li><a href="Books.jsp">Book</a></li>
        </ul>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="MyOrder.jsp">My Order</a></li>
                <li><a href="MyCart.jsp">My Cart</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=username%><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="Logout.jsp">Log Out</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<table id="Bookdg" title="Books" class="easyui-datagrid" style="width:700px;height:250px"
       url="/ShowBook"
       toolbar="#Bookbar" pagination="true"
       rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="bookID" width="50">Book ID</th>
        <th field="title" width="50">Title</th>
        <th field="author" width="50">Author</th>
        <th field="price" width="50">Price($)</th>
        <th field="description" width="50">Description</th>
        <th field="category" width="50">Category</th>
    </tr>
    </thead>
</table>
<div id="Bookbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="AddBookToCart()">Add To Cart</a>
</div>
    </thead>
</table>
<script type="text/javascript">
    var url;
    function AddBookToCart(){
        var row = $('#Bookdg').datagrid('getSelected');
        if (row){
            console.log(row);
            url='/AddBookToCart';
            $.post(url,{BookID:row.bookID});
            $.messager.alert('Notice','Book added to your shopping cart successfully!','info');
        }
    }
</script>
</body>
</html>
