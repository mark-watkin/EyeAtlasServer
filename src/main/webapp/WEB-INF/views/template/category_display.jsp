{{#each children}} {{! Each item is an "li" }}
    <li>
        <label>{{name}}</label>
        {{#if children}} {{! Within the context of the current item }}
        <ul>
            {{> recursion}} {{! Recursively render the partial }}
        </ul>
        {{/if}}
    </li>
{{/each}}
