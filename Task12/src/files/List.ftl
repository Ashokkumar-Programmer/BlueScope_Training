<#-- List -->

<#assign name=["foo", "bar", "baz"]>

<#list name as n>
${n}
</#list>

<#list name>

Name:
<#items as i>
${i}
</#items>
<#else>
No Names
</#list>


<#list name as n>${n}<#sep>, </#list>


<#list name as n>
<#if n=="bar">
<#break>
</#if>
${n}
</#list>

<#list name as n>
<#if n=="bar">
<#continue>
</#if>
${n}
</#list>


<#list name as n>

${n?item_parity}
${n?counter}
</#list>