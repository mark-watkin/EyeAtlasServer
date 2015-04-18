$(document).ready(function () {
    $('#upload-button').click(function () {
        var categoryUpload = {
            parentName: $('#upload-form .category-parent').val(),
            category: {
                name: $('#upload-form .category-name').val(),
                children: []
            }
        };

        $.ajax({
            url: '/rest/category',
            type: 'post',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(categoryUpload),
            success: function (data) {
                alert(data);
            },
            error: function(data) {
                alert(data);
            }
        });

        name: $('#upload-form .category-name').val("");
        name: $('#upload-form .category-parent').val("");
    });

    $('#delete-button').click(function () {
        var category = {
            name: $('#delete-form .category-name').val()
        };

        $.ajax({
            url: '/rest/category/' + category.name,
            type: 'delete',
            success: function (data) {
                alert(data);
            },
            error: function(data) {
                alert(data);
            }
        });

        name: $('#delete-form .category-name').val("");
    });
});

