$(document).ready(function () {

    $('#upload-button').click(function () {
        $("#upload-form").ajaxSubmit({
            url: '/rest/image',
            type: 'POST',
            success: function() {
                alert("DONE!");
            },

            fail: function() {
                alert("NOOO!");
            }
        });
    });
});

