<#-- get values -->

<#assign hash = { "name": "mouse", "price": 50 }>

<#list hash?values as value>
${value}
</#list>


<#-- keys -->

<#list hash?keys as key>
${key}
</#list>