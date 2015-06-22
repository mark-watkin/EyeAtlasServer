<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" defer></script>
        <script src="http://malsup.github.com/jquery.form.js" defer></script>

        <script src="<c:url value='/resources/js/handlebars.js'/>" defer></script>
        <script src="<c:url value='/resources/js/handlebars_helper.js'/>" defer></script>
        <script src="<c:url value='/resources/js/category.js' />" defer></script>

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
                        <li class="active"><a href="category">Category <span class="sr-only">(current)</span></a></li>
                        <li><a href="tag">Tag</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Settingss <span class="caret"></span></a>
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
                <h1>Add or remove categories</h1>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-12 top_10">
                    <h3>
                        Existing Categories
                        <span class="glyphicon glyphicon-plus create">
                            <input class="parentId" type="hidden" value="" />
                        </span>
                    </h3>

                    <div id="category"></div>
                </div>
            </div>
        </div>

        <div id="myModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Modal title</h4>
                    </div>
                    <div class="modal-body">
                        <form id="upload-form">
                            <input class="hiddenId" type="hidden"/>
                            <div class="input-group input-group-lg">
                                <input type="text" class="form-control category-name" placeholder="Category Name">
                            </div>
                        </form>
                        <textarea class="form-control category-description" placeholder="Description" rows="3"></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary saveChange">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>