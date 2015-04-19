$(document).ready(function () {
    var getTemplate,
        tagTemplate,
        base64;

    Handlebars.registerHelper("fourth", function(count, block) {
        if(parseInt(count)%4 === 0){
            return block.fn(this);
        }
    });

    getTemplate = function(templatePath, success) {
        $.ajax({
            url: templatePath,
            cache: true,
            success: function(rawTemplate) {
                var template = Handlebars.compile(rawTemplate);
                success(template);
            }
        });
    }

    base64 = function (input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#prev').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#file").change(function(){
        base64(this);
    });

    getTemplate('template/tag', function (template) {
        $.ajax({
            url: '/rest/tags',
            success: function(json) {
                $("#tags").html(template(json));
            }
        });
    });

    getTemplate('template/category', function (template) {
        Handlebars.registerPartial("recursion", template);
        $.ajax({
            url: '/rest/category',
            success: function(json) {
                $("#category").html(template({children: json}));
            }
        });
    });

    $('#upload-button').click(function () {
        var entityUpload = {
            name: $('#upload-form .entity-name').val(),
            category: $("input[type='radio'][name='category']:checked"),
            tags: $("input[type='checkbox']:checked"),
            image: $('#prev').attr('src')
        };

        $.ajax({
            url: '/rest/infoentity',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(entityUpload),
            success: function(data) {
                alert(data);
            },
            fail: function(data) {
                alert(data);
            }
        });
    });
});

