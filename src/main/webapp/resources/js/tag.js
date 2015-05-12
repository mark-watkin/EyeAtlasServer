$(document).ready(function () {

    var loadTags = function() {
        getTemplate('template/tag_display', function (template) {
            $.ajax({
                url: '/rest/tag',
                success: function(json) {
                    $("#tags").html(template(json));
                }
            });
        });
    }

    loadTags();

    $('#upload-button').click(function () {
        var tag = {
            name: $('#upload-form .tag-name').val()
        };

        $.ajax({
            url: '/rest/tag',
            type: 'post',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(tag),
            success: function (data) {
                alert(data);
                loadTags();
            },
            error: function(data) {
                alert(data);
            }
        });

        name: $('#upload-form .tag-name').val("");
    });

    $('#delete-button').click(function () {
            var tag = {
                name: $('#delete-form .tag-name').val()
            };

            $.ajax({
                url: '/rest/tag/' + tag.name,
                type: 'delete',
                success: function (data) {
                    alert(data);
                    loadTags();
                },
                error: function(data) {
                    alert(data);
                }
            });

            name: $('#delete-form .tag-name').val("");
        });
});

