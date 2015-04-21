$(document).ready(function () {
    $('#upload-button').click(function () {
        var tag = {
            name: $('#upload-form .tag-name').val()
        };

        $.ajax({
            url: '/rest/tags',
            type: 'post',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(tag),
            success: function (data) {
                alert(data);
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
                url: '/rest/tags/' + tag.name,
                type: 'delete',
                success: function (data) {
                    alert(data);
                },
                error: function(data) {
                    alert(data);
                }
            });

            name: $('#delete-form .tag-name').val("");
        });
});

