<%--
  Created by IntelliJ IDEA.
  User: jicl
  Date: 16/5/5
  Time: 下午5:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="manager.*,entity.*,java.util.*" contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Deniece Bookstore-Userlist</title>
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

<table id="Userdg" title="My Users" class="easyui-datagrid" style="width:700px;height:250px"
       url="/ShowUser"
       toolbar="#Userbar" pagination="true"
       rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="userID" width="50">User ID</th>
        <th field="username" width="50">Username</th>
        <th field="password" width="50">Password</th>
        <th field="balance" width="50">Balance($)</th>
    </tr>
    </thead>
</table>
<div id="Userbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">Remove User</a>
</div>

<div id="Userdlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#Userbuttons">
    <div class="ftitle">User Information</div>
    <form id="Userfm" method="post" novalidate>
        <div class="fitem">
            <label>Username:</label>
            <input name="username" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>Password:</label>
            <input name="password" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>Balance:</label>
            <input name="balance" class="easyui-textbox" >
        </div>
    </form>
</div>
<div id="Userbuttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">Save</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#Userdlg').dialog('close')" style="width:90px">Cancel</a>
</div>
<script type="text/javascript">
    var url;
    function newUser(){
        $('#Userdlg').dialog('open').dialog('setTitle','New User');
        $('#Userfm').form('clear');
        url = '/AddUser';
    }
    function editUser(){
        var row = $('#Userdg').datagrid('getSelected');
        if (row){
            console.log(row);
            $('#Userdlg').dialog('open').dialog('setTitle','Edit User');
            $('#Userfm').form('load',row);
            url = '/ModifyUser?UserID='+row.userID;
        }
    }
    function saveUser(){
        $('#Userfm').form('submit',{
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
                    $('#Userdlg').dialog('close');
                    $('#Userdg').datagrid('reload');
                }
            }
        });
    }
    function removeUser() {
        var row = $('#Userdg').datagrid('getSelected');
        if (row) {
            $.messager.confirm('Confirm', 'Are you sure you want to destroy this user?', function (r) {
                if (r) {
                    $.post('/DeleteUser', {UserID: row.userID}, function (result) {
                        if (result.success) {
                            $('#Userdg').datagrid('reload');
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
</script>
</body>
</html>