<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="carsale.model.Item" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>TODO List</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">TODO List</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="<%=request.getContextPath()%>/index.do">Home</a></li>
            <li class="active"><a href="#">New Item</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${author.name}</a></li>
            <li><a href="<%=request.getContextPath()%>/auth.do?log=0"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <form class="form-horizontal" method="post" action='<%=request.getContextPath()%>/edit.do?id=${item.id}'>
        <div class="form-group">
            <label class="control-label col-sm-2" for="desc">Description:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="desc" placeholder="Description" name="desc" value="${item.desc}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Save</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
