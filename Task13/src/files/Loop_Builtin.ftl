<#assign list = ['a', 'b', 'c', 'd', 'e']>

<#-- counter -->

<#list list as value>
${value?counter}
</#list>

<#-- has_next -->

<#list list as value>
${value?has_next?c}
</#list>

<#-- index -->

<#list list as value>
${value?index}
</#list>

<#-- is_even_item -->

<#list list as value>
${value?is_even_item?c}
</#list>

<#-- is_odd_item -->

<#list list as value>
${value?is_odd_item?c}
</#list>

<#-- is_first -->

<#list list as value>
${value?is_first?c}
</#list>

<#-- is_last -->

<#list list as value>
${value?is_last?c}
</#list>

<#-- item_cycle -->

<#list list as value>
${value?item_cycle('1', '2', '3', '4')}: ${value}
</#list>

<#-- item_parity -->

<#list list as value>
${value?item_parity_cap}: ${value}
</#list>