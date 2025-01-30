<#-- Conditional Statement -->

<#assign num=0>
<#import "Functions.ftl" as data>

${data.func("Ashok")}

<#include "Functions.ftl">

<#if num==0>
Enter valid number
<#elseif num%2==0>
${num} is even
<#else>
${num} is odd
</#if>

<#switch num>
<#case 0>
num value is 0
<#break>
<#case 1>
num value is 1
<#break>
<#default>
num value is incorrect
</#switch>