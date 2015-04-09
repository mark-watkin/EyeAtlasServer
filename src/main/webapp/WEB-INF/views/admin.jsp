<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="http://malsup.github.com/jquery.form.js"></script>
        <script src="<c:url value='/resources/js/upload.js' />"></script>
    </head>
    <body>
        <h2>Hello World!</h2>
        <form id="upload-form" action="/rest/image" method="post" enctype="multipart/form-data">
            Select image to upload:
            <input type="file" name="file" id="file">
            <input id="upload-button" type="button" value="Upload Image" name="submit">
        </form>
    </body>
</html>
