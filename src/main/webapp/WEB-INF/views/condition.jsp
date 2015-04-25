<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" defer></script>
        <script src="<c:url value='/resources/js/handlebars.js'/>" defer></script>
        <script src="<c:url value='/resources/js/condition.js'/>" defer></script>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js" defer></script>
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
                <h1>Add remove or update entries</h1>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <h2>Upload</h2>
                    <form id="upload-form">
                        <img id="prev" src="#"/>
                        <input type="file" name="file" id="file"></br>
                        <div class="input-group input-group-lg">
                            <input type="text" class="form-control entity-name" placeholder="Title">
                        </div>
                        <textarea class="form-control entity-description" placeholder="Description" rows="3"></textarea>
                        <div id="tags"></div>
                        <h3>Categories</h3>
                        <div id="category"></div>
                    </form>
                    <a class="btn btn-info" id="upload-button" href="javascript:void(0)" role="button">Upload Image &raquo;</a>
                </div>
                <div class="col-md-4">
                    <h2>Delete</h2>
                    <form id="delete-form">
                        <div class="input-group input-group-lg">
                            <input type="text" class="form-control" placeholder="Title">
                        </div>
                    </form>
                    <a class="btn btn-danger" id="delete-button" href="javascript:void(0)" role="button">Delete Image &raquo;</a>
                </div>
            </div>
        </div>
    </body>
</html>