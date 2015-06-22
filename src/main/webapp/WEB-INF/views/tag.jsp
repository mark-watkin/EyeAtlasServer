<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" defer></script>
        <script src="http://malsup.github.com/jquery.form.js" defer></script>

        <script src="<c:url value='/resources/js/handlebars.js'/>" defer></script>
        <script src="<c:url value='/resources/js/handlebars_helper.js' />" defer></script>
        <script src="<c:url value='/resources/js/tag.js' />" defer></script>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js" defer></script>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/">Eye Atlas</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">
                        <li><a href="condition">Condition</a></li>
                        <li><a href="category">Category</a></li>
                        <li class="active"><a href="tag">Tag <span class="sr-only">(current)</span></a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Settings <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>

            </div>
        </nav>

        <div class="jumbotron">
            <div class="container">
                <h1>Add or remove tags</h1>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h2>Create</h2>
                    <form id="upload-form">
                        <div class="input-group input-group-lg">
                            <input type="text" class="form-control tag-name" placeholder="Tag Name">
                        </div>
                    </form>
                    <a class="btn btn-info" id="upload-button" href="javascript:void(0)" role="button">Create Tag &raquo;</a>
                </div>
                <div class="col-md-6">
                    <h2>Delete</h2>
                    <form id="delete-form">
                        <div class="input-group input-group-lg">
                            <input type="text" class="form-control tag-name" placeholder="Tag Name">
                        </div>
                    </form>
                    <a class="btn btn-danger" id="delete-button" href="javascript:void(0)" role="button">Delete Tag &raquo;</a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 top_10">
                    <h3>Existing Tags</h3>
                    <div id="tags"></div>
                </div>
            </div>
        </div>
    </body>
</html>