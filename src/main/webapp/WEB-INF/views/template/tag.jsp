<h3>Tags</h3>
<div>
{{#each this}}
    {{#fourth @index}}
        </div>
        <div class="row">
    {{/fourth}}
        <div class="col-md-3">
            <label>
                <input type="checkbox" value="{{name}}"> {{name}}
            </label>
        </div>
{{/each}}
</div>