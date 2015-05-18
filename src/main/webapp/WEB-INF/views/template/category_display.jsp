{{#each children}} {{! Each item is an "li" }}
    <li>
        <label>{{name}}</label>
        {{#unless children}}
            <span class="glyphicon glyphicon-trash remove">
                <input class="parentId" type="hidden" value="{{id}}" />
            </span>
        {{/unless}}
        <span class="glyphicon glyphicon-plus create">
            <input class="parentId" type="hidden" value="{{id}}" />
        </span>
        {{#if children}} {{! Within the context of the current item }}
        <ul>
            {{> recursion}} {{! Recursively render the partial }}
        </ul>
        {{/if}}
    </li>
{{/each}}
