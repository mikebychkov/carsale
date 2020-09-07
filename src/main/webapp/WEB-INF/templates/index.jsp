<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>CAR SALE</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" crossorigin="anonymous"></script>
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" crossorigin="anonymous">

    <script>

        let ItemFilter = '';

        function updateItemList() {
            $.ajax({
                type: 'GET',
                //url: 'https://localhost:8443/carsale/itemlist.do' + ItemFilter,
                url: 'http://localhost:8080/carsale/itemlist.do' + ItemFilter,
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
                    content += "<td>" + d.brand + "</td>";
                    content += "<td>" + d.model + "</td>";
                    content += "<td>" + d.body + "</td>";
                    content += "<td>" + d.color + "</td>";
                    content += "<td>" + "<img src='photo.do?name=" + d.photo + "' width='200' height='200' class=\"img-responsive\"/></td>";
                    content += "<td>" + d.author + "</td>";
                    content += "<td>" + d.created + "</td>";
                    content += "<td>" + d.done + "</td>";
                    //
                    content += "</tr>";
                }
                $('#tdata').html(content);
            });
        }

        function updateItemFilter() {
            ItemFilter = '?list=';
            if (document.getElementById("actual0").checked) {
                ItemFilter += 'actual';
            } else {
                ItemFilter += 'all';
            }
            let filterVal = document.getElementById("selectFilter").value;
            ItemFilter += '&filter=' + filterVal;
            if (filterVal = "brand") {
                let brandVal = document.getElementById("selectBrand").value;
                ItemFilter += '&brand=' + brandVal;
            }
        }

        function brandFilerVisibility() {
            let filter = document.getElementById("selectFilter").value;
            if (filter == "brand") {
                //document.getElementById("brandFilter").style.visibility = "visible";
                $('.collapse').collapse("show");
                updateBrandList();
            } else {
                //document.getElementById("brandFilter").style.visibility = "collapse";
                $('.collapse').collapse("hide");
            };
        }

        function updateBrandList() {
            $.ajax({
                type: 'GET',
                //url: 'https://localhost:8443/carsale/brandlist.do',
                url: 'http://localhost:8080/carsale/brandlist.do',
                dataType: 'json'
            }).done(function(data) {
                let content = "";
                for (let k in data) {
                    let d = data[k];
                    content += "<option value='"+ d +"'>";
                    content += d;
                    content += "</option>";
                }
                $('#selectBrand').html(content);
            });
        }

        function onStart() {
            brandFilerVisibility();
            updateItemList();
            updateBrandList();
        }

        $(document).ready(function() {onStart()});

    </script>

</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">CAR SALE</a>
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
    <div class="form-group">
        <div class="input-group">
            <select class="form-control" id="selectFilter" onchange="brandFilerVisibility()">
                <option value="all">All items</option>
                <option value="day">By last day</option>
                <option value="brand">By brand</option>
                <option value="pic">With picture</option>
            </select>
            <div class="input-group-btn">
                <button class="btn btn-default" type="button" onclick="updateItemFilter(); updateItemList()">
                    <i class="glyphicon glyphicon-search"></i>
                </button>
            </div>
        </div>
    </div>
    <div class="collapse" id="brandFilter" >
        <div class="form-group">
            <select class="form-control" id="selectBrand">
<%--                <option >Mercedes</option>--%>
            </select>
        </div>
    </div>
</div>

<div class="container">
    <div class="form-group">
        <div class="checkbox">
            <label><input type="checkbox" name="actual0" id="actual0" onchange="updateItemFilter(); updateItemList()"> Show only actual items</label>
        </div>
    </div>
    <div class="form-group">
        <table class="table table-hover">
            <thead>
            <tr>
                <th><i class="fa fa-check-square-o" aria-hidden="true"></i></th>
                <th><i class="fa fa-edit mr-3"></i></th>
                <th>Description</th>
                <th>Brand</th>
                <th>Model</th>
                <th>Body</th>
                <th>Color</th>
                <th>Photo</th>
                <th>Author</th>
                <th>Created</th>
                <th>Done</th>
            </tr>
            </thead>
            <tbody id="tdata">
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
