<#-- Functions -->

<#function func name...>
<#local data="">
<#list name as n>
	<#local data+=n?counter>
	<#local data+=" "+n+"\n">
</#list>
<#return data>
</#function>

${func("Ashok", "Hari", "Joeswa")}
