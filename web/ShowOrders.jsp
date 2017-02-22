<%--
  Created by IntelliJ IDEA.
  User: jicl
  Date: 16/5/7
  Time: 下午4:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deniece Bookstore-Orderlist</title>
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

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="ShowUsers.jsp">User</a></li>
                <li><a href="ShowBooks.jsp">Book</a></li>
                <li><a href="ShowOrders.jsp">Order</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="Logout.jsp">Log Out</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<table id="Orderdg" title="My Orders" class="easyui-datagrid" style="width:700px;height:250px"
       url="/ShowOrder"
       toolbar="#Orderbar" pagination="true"
       rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="orderID" width="50">Order ID</th>
        <th field="userID" width="50">User ID</th>
        <th field="bookID" width="50">Book ID</th>
        <th field="total" width="50">Total($)</th>
        <th field="time" width="50">Time</th>
        <th field="isPaid" width="50">Is Paid</th>
    </tr>
    </thead>
</table>
<div id="Orderbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="ByUser()">By User</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="ByDate()">By Date</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="ByCategory()">By Category</a>
</div>
<div id="Orderdlg1" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#Orderbuttons1">
    <div class="ftitle">Statistic By User</div>
    <form id="Orderfm1" method="post" novalidate>
        <div class="fitem">
            <label>User ID:</label>
            <input name="UserID" class="easyui-textbox" >
        </div>
    </form>
</div>
<div id="Orderdlg2" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#Orderbuttons2">
    <div class="ftitle">Statistic By Date</div>
    <form id="Orderfm2" method="post" novalidate>
        <div class="fitem">
            <label>Date:</label>
            <input name="time" class="easyui-textbox" >
        </div>
    </form>
</div>
<div id="Orderdlg3" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#Orderbuttons3">
    <div class="ftitle">Statistic By Category</div>
    <form id="Orderfm3" method="post" novalidate>
        <div class="fitem">
            <label>Category:</label>
            <input name="category" class="easyui-textbox" >
        </div>
    </form>
</div>
<div id="Orderbuttons1">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="save1()" style="width:90px">Confirm</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#Orderdlg1').dialog('close')" style="width:90px">Cancel</a>
</div>
<div id="Orderbuttons2">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="save2()" style="width:90px">Confirm</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#Orderdlg2').dialog('close')" style="width:90px">Cancel</a>
</div>
<div id="Orderbuttons3">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="save3()" style="width:90px">Confirm</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#Orderdlg3').dialog('close')" style="width:90px">Cancel</a>
</div>
</body>
</html>
<script type="text/javascript">
    function ByUser(){
        $('#Orderdlg1').dialog('open').dialog('setTitle','By User');
        $('#Orderfm1').form('clear');
        url='/UserOrder';
    }
    function ByDate(){
        $('#Orderdlg2').dialog('open').dialog('setTitle','By Date');
        $('#Orderfm2').form('clear');
        url='/DateOrder';
    }
    function ByCategory(){
        $('#Orderdlg3').dialog('open').dialog('setTitle','By Category');
        $('#Orderfm3').form('clear');
        url='/CategoryOrder';
    }
    function save1(){
        $('#Orderfm1').form('submit',{
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
                    $('#Userdlg1').dialog('close');
                    $.messager.alert('Notice','The result is '+result.UserCount+'.','info');
                }
            }
        });
    }
    function save2(){
        $('#Orderfm2').form('submit',{
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
                    $('#Userdlg2').dialog('close');
                    $.messager.alert('Notice','The result is '+result.DateCount+'.','info');
                }
            }
        });
    }
    function save3(){
        $('#Orderfm3').form('submit',{
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
                    $('#Userdlg3').dialog('close');
                    $.messager.alert('Notice','The result is '+result.CategoryCount+'.','info');
                }
            }
        });
    }
</script>