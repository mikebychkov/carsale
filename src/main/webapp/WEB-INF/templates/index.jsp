<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>TODO List</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" crossorigin="anonymous"></script>
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" crossorigin="anonymous">

    <script>

        let ActualItems = '';

        function updateItemList() {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/todolist/itemlist.do?list=' + ActualItems,
                dataType: 'json'
            }).done(function(data) {
                let content = "";
                for (let k in data) {
                    let d = data[k];
                    //
                    content += "<tr>";
                    //
                    content += "<td>";

                    content += "<a href='edit.do?id=" + d.id + "&check=1'>";
                    if (d.done == "") {
                        content += "<i class=\"fa fa-square-o\" aria-hidden=\"true\"></i>";
                    } else {
                        content += "<i class=\"fa fa-check-square-o\" aria-hidden=\"true\"></i>";
                    }
                    content += "</a>";

                    content += "</td>";
                    //
                    content += "<td>";
                    content += "<a href='edit.do?id=" + d.id + "'>" +
                                "<i class=\"fa fa-edit mr-3\"></i>" +
                                "</a>"
                    content += "</td>";
                    //
                    content += "<td>" + d.desc + "</td>";
                    content += "<td>" + d.author + "</td>";
                    content += "<td>" + d.created + "</td>";
                    content += "<td>" + d.done + "</td>";
                    //
                    content += "</tr>";
                }
                $('#tdata').html(content);
            });
        }

        function updateActualFlag() {
            if (document.getElementById("actual0").checked) {
                ActualItems = 'actual';
            } else {
                ActualItems = 'all';
            }
        }

        updateItemList();
    </script>

</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">TODO List</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="<%=request.getContextPath()%>/edit.do?id=new">New Item</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${author.name}</a></li>
            <li><a href="<%=request.getContextPath()%>/auth.do?log=0"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="checkbox">
        <label><input type="checkbox" name="actual0" id="actual0" onchange="updateActualFlag(); updateItemList()"> Show only actual items</label>
    </div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th><i class="fa fa-check-square-o" aria-hidden="true"></i></th>
            <th><i class="fa fa-edit mr-3"></i></th>
            <th>Description</th>
            <th>Author</th>
            <th>Created</th>
            <th>Done</th>
        </tr>
        </thead>
        <tbody id="tdata">
        </tbody>
    </table>
</div>

</body>
</html>
