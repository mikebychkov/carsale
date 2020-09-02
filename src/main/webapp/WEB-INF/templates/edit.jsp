<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="carsale.model.Item" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>CAR SALE</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <script>
        $(document).ready(function() {

            $("#but_upload").click(function() {

                let fd = new FormData();
                let files = $('#file')[0].files[0];
                fd.append('file',files);

                $.ajax({
                    url: 'http://localhost:8080/carsale/photo.do',
                    type: 'post',
                    data: fd,
                    contentType: false,
                    processData: false
                }).done(function(response) {
                    $("#photo").attr("value", response);
                    let photo_url = "http://localhost:8080/carsale/photo.do?name=" + response;
                    $("#img").attr("src", photo_url); //response
                    $(".preview img").show(); // Display image element
                }).fail(function(err) {
                    alert('file not uploaded: ' + err);
                });

            });
        });
    </script>

</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">CAR SALE</a>
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

<div class="container-fluid">
    <div class="row">

        <div class="col-sm-8" >

            <div class="container col-sm-12">
                <form class="form-horizontal" method="post" action='<%=request.getContextPath()%>/edit.do?id=${item.id}'>
                    <input type="hidden" id="photo" name="photo" value="${item.photo}">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="desc">Description:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="desc" placeholder="Description" name="desc" value="${item.desc}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="brand">Brand:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="brand" placeholder="Brand" name="brand" value="${item.brand}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="model">Model:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="model" placeholder="Model" name="model" value="${item.model}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="body">Body:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="body" placeholder="Body" name="body" value="${item.body}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="color">Color:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="color" placeholder="Color" name="color" value="${item.color}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form>
            </div>

        </div>

        <div class="col-sm-4" >

            <div class="container col-sm-12">
                <form class="form" action="" method="post" enctype="multipart/form-data" id="photoform">
                    <div class="form-group">
                        <img id="img" src="<c:url value='/photo.do?name=${item.photo}'/>" class="img-responsive" width="200" height="200" /><br/>
                    </div>
                    <div class="form-group">
                        <input type="file" name="file" id="file" class="checkbox">
                    </div>
                    <div class="form-group">
                        <input type="button" class="btn btn-default" value="Upload" id="but_upload" >
                    </div>
                </form>

            </div>

        </div>

    </div>
</div>

</body>
</html>
