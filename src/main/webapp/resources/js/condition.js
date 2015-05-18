$(document).ready(function () {
    var getValues,
        base64;

    base64 = function (input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#prev').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    getValues = function(elements) {
        var selected = [];
        elements.each(function() {
            selected.push($(this).val());
        });
        return selected;
    }

    $("#file").change(function(){
        base64(this);
    });

    getTemplate('template/tag_select', function (template) {
        $.ajax({
            url: '/rest/tag',
            success: function(json) {
                $("#tags").html(template(json));
            }
        });
    });

    getTemplate('template/category_select', function (template) {
        Handlebars.registerPartial("recursion", template);
        $.ajax({
            url: '/rest/category',
            success: function(json) {
                $("#category").html(template({children: json}));
            }
        });
    });

    $('#upload-button').click(function () {
        var conditionUpload = {

            condition: {
                title: $('#upload-form .entity-name').val(),
                description: $('#upload-form .entity-description').val()
            },

            category: $("input[type='radio'][name='category']:checked").val(),
            tags: getValues($("input[type='checkbox']:checked")),
            image: $('#prev').attr('src')
        };

        $.ajax({
            url: '/rest/condition',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(conditionUpload),
            success: function(data) {
                alert(data);
            },
            fail: function(data) {
                alert(data);
            }
        });

        $('#upload-form')[0].reset();
        $('#prev').attr('src', 'http://placehold.it/450x300');
    });

    $('#delete-button').click(function () {
            var category = {
                name: $('#delete-form .condition-name').val()
            };

            $.ajax({
                url: '/rest/condition/' + category.name,
                type: 'delete',
                success: function (data) {
                    alert(data);
                },
                error: function(data) {
                    alert(data);
                }
            });

            name: $('#delete-form .condition-name').val("");
        });
});

