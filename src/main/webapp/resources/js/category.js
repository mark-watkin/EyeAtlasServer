$(document).ready(function () {

    var removeCategory = function(id) {
        var category = {
            id: id
        };

        $.ajax({
            url: '/rest/category/' + category.id,
            type: 'delete',
            success: function (data) {
                alert(data);
                loadCategories();
            },
            error: function(data) {
                alert(data);
            }
        });

        $('#delete-form .category-name').val("");
    }

    var loadCategories = function() {
        getTemplate('template/category_display', function (template) {
            Handlebars.registerPartial("recursion", template);
            $.ajax({
                url: '/rest/category',
                success: function(json) {
                    $("#category").html(template({children: json}));
                    $('.create').click(function () {
                        $('#myModal .hiddenId').val($(event.target).find(".parentId").val());
                        $('#myModal').modal('show');
                    });

                    $('.remove').click(function () {
                        if(confirm("Delete " + $(event.target).find(".parentId").val() + "?")) {
                            removeCategory($(event.target).find(".parentId").val());
                        }
                    });
                }
            });
        });
    }

    loadCategories();

    $('#myModal .saveChange').click(function () {
        var categoryUpload = {
            parentId: $('#myModal .hiddenId').val(),
            category: {
                name: $('#myModal #upload-form .category-name').val(),
                description: $('#myModal .category-description').val(),
                children: []
            }
        };

        $.ajax({
            url: '/rest/category',
            type: 'post',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(categoryUpload),
            success: function (data) {
                $("#myModal").modal('hide');
                alert(data);
                loadCategories();
            },
            error: function(data) {
                alert(data);
            }
        });


        $('#upload-form .category-name').val("");
        $('#upload-form .category-parent').val("");
        $('.category-description').val("");
    });
});

