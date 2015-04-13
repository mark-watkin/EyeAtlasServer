<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" async></script>
        <script src="http://malsup.github.com/jquery.form.js" async></script>

        <script src="<c:url value='/resources/js/upload.js' />" async></script>
        <script src="<c:url value='/resources/js/dynamicContent.js' />" async></script>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="javascript:void(0)">Eye Atlas</a>
                </div>
            </div>
        </nav>

        <div class="jumbotron">
            <div class="container">
                <h1>Add or remove categories</h1>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Create</h2>
                    <form id="upload-form" action="/rest/image" method="post" enctype="multipart/form-data">
                        <div class="input-group input-group-lg">
                            <input type="text" class="form-control" placeholder="Category Name">
                        </div>
                        <div class="input-group input-group-lg">
                            <input type="text" class="form-control" placeholder="Category Parent">
                        </div>
                    </form>
                    <a class="btn btn-info" id="upload-button" href="javascript:void(0)" role="button">Create Category &raquo;</a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <h2>Delete</h2>
                    <form id="delete-form" action="/rest/image" method="post" enctype="multipart/form-data">
                        <div class="input-group input-group-lg">
                            <input type="text" class="form-control" placeholder="Category Name">
                        </div>
                    </form>
                    <a class="btn btn-danger" id="delete-button" href="javascript:void(0)" role="button">Delete Category &raquo;</a>
                </div>
            </div>
        </div>
    </body>
</html>
