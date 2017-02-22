<%--
  Created by IntelliJ IDEA.
  User: jicl
  Date: 16/5/8
  Time: 下午9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="manager.*,entity.*,java.util.*" contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Deniece Bookstore-Booklist</title>
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

<table id="Bookdg" title="My Books" class="easyui-datagrid" style="width:700px;height:250px"
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
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newBook()">New Book</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editBook()">Edit Book</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeBook()">Remove Book</a>
</div>

<div id="Bookdlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#Bookbuttons">
    <div class="ftitle">Book Information</div>
    <form id="Bookfm" method="post" novalidate>
        <div class="fitem">
            <label>Title:</label>
            <input name="title" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>Author:</label>
            <input name="author" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>Price($):</label>
            <input name="price" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>Description:</label>
            <input name="description" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>Category:</label>
            <input name="category" class="easyui-textbox" required="true">
        </div>
    </form>
</div>
<div id="Bookbuttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveBook()" style="width:90px">Save</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#Bookdlg').dialog('close')" style="width:90px">Cancel</a>
</div>
<script type="text/javascript">
    var url;
    function newBook(){
        $('#Bookdlg').dialog('open').dialog('setTitle','New Book');
        $('#Bookfm').form('clear');
        url = '/AddBook';
    }
    function editBook(){
        var row = $('#Bookdg').datagrid('getSelected');
        if (row){
            console.log(row);
            $('#Bookdlg').dialog('open').dialog('setTitle','Edit Book');
            $('#Bookfm').form('load',row);
            url = '/ModifyBook?BookID='+row.bookID;
        }
    }
    function saveBook(){
        $('#Bookfm').form('submit',{
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
                    $('#Bookdlg').dialog('close');
                    $('#Bookdg').datagrid('reload');
                }
            }
        });
    }
    function removeBook() {
        var row = $('#Bookdg').datagrid('getSelected');
        if (row) {
            $.messager.confirm('Confirm', 'Are you sure you want to destroy this book?', function (r) {
                if (r) {
                    $.post('/DeleteBook', {BookID: row.bookID}, function (result) {
                        if (result.success) {
                            $('#Bookdg').datagrid('reload');
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
