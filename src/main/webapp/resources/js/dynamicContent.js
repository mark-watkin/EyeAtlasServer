$(document).ready(function () {
    $.ajax({
        url: "/rest/tags",
        method: "GET"
    }) .done(function() {
        alert( "success" );
    })
});
