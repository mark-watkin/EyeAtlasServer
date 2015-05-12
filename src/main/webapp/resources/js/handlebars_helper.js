function getTemplate (templatePath, success) {
    $.ajax({
        url: templatePath,
        cache: true,
        success: function (rawTemplate) {
            var template = Handlebars.compile(rawTemplate);
            success(template);
        }
    });
}

Handlebars.registerHelper("fourth", function(count, block) {
    if(parseInt(count)%4 === 0){
        return block.fn(this);
    }
});