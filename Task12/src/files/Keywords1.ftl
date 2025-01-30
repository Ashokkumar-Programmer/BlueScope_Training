<#-- assign -->

<#assign name="Ashok">
${name}

<#assign name=["foo", "bar", "baz"]>

<#list name as n>
${n}
</#list>


<#-- attempt -->

<#assign name1="Kumar">
<#attempt>
${name1}
<#recover>
name1 is not found 
</#attempt>


<#-- compress -->

<#assign data1="      Ashok                 Kumar             ">
<#compress>


Hello

${data1}

</#compress>


<#assign l=[1,2,3,4,5]>

<#list l as num>
${num}
<#if num%2==0><#--<#stop "Even number found">${num}--></#if>
</#list>


<#t>${data1}