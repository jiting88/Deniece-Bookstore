<%--
  Created by IntelliJ IDEA.
  User: jicl
  Date: 16/5/8
  Time: 上午9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.User"%>
<%
    String username=((User)session.getAttribute("user")).getUsername();
%>
<html>
<head>
    <title>Deniece Cartstore-My Cart</title>
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
<table id="Cartdg" title="My Cart" class="easyui-datagrid" style="width:700px;height:250px"
       url="/ShowCart"
       toolbar="#Cartbar" pagination="true"
       rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="bookID" width="50">Book ID</th>
        <th field="title" width="50">Title</th>
        <th field="author" width="50">Author</th>
        <th field="price" width="50">Price($)</th>
        <th field="description" width="50">Description</th>
        <th field="category" width="50">Category</th>
        <th field="quantity" width="50">Quantity</th>
    </tr>
    </thead>
</table>
<div id="Cartbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCart()">Edit Cart</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeCart()">Remove Cart</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="commitOrder()">Commit Order</a>
</div>

<div id="Cartdlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#Cartbuttons">
    <div class="ftitle">Cart Information</div>
    <form id="Cartfm" method="post" novalidate>
        <div class="fitem">
            <label>Quantity:</label>
            <input name="quantity" class="easyui-textbox" required="true">
        </div>
    </form>
</div>
<div id="Cartbuttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveCart()" style="width:90px">Save</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#Cartdlg').dialog('close')" style="width:90px">Cancel</a>
</div>
<script type="text/javascript">
    var url;
    function editCart(){
        var row = $('#Cartdg').datagrid('getSelected');
        if (row){
            console.log(row);
            $('#Cartdlg').dialog('open').dialog('setTitle','Edit Cart');
            $('#Cartfm').form('load',row);
            url = '/ModifyBookToCart?BookID='+row.bookID;
        }
    }
    function saveCart(){
        $('#Cartfm').form('submit',{
            url: url,
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(result){
                var result = eval('('+result+')');
                if (result.errorMsg){
                    $.messager.show({
                        title: 'Error',
                        msg: result.errorMsg
                    });
                } else {
                    $('#Cartdlg').dialog('close');
                    $('#Cartdg').datagrid('reload');
                }
            }
        });
    }
    function removeCart() {
        var row = $('#Cartdg').datagrid('getSelected');
        if (row) {
            $.messager.confirm('Confirm', 'Are you sure you want to destroy this cart?', function (r) {
                if (r) {
                    $.post('/DeleteBookFromCart', {BookID: row.bookID}, function (result) {
                        if (result.success) {
                            $('#Cartdg').datagrid('reload');
                        } else {
                            $.messager.show({
                                title: 'Error',
                                msg: result.errorMsg
                            });
                        }
                    }, 'json');
                }
            });
        }
    }
    function commitOrder(){
        var row=$('#Cartdg').datagrid('getSelected');
        if(row){
            url='/AddOrder?BookID='+row.bookID+'&total='+row.quantity*row.price;
            $.post(url);
            $.messager.alert('Notice','Order committed successfully!','info');
        }
    }
</script>
</body>
</html>
