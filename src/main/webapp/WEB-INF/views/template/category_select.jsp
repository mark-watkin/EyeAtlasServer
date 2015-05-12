{{#each children}} {{! Each item is an "li" }}
<li>
    {{name}}
    {{#if children}} {{! Within the context of the current item }}
    <ul>
        {{> recursion}} {{! Recursively render the partial }}
    </ul>
    {{else}}
    <input type="radio" name="category" value="{{name}}">
    {{/if}}
</li>
{{/each}}