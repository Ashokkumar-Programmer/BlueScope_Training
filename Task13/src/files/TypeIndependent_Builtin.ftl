<#assign list = ['a', 'b', 'h', 'd', 'e']>

<#-- switch  -->

<#list list as value>
${value?switch('a',"Ashok", 'b', "Bala", 'h', "Hari", "Unknown value")}
</#list>

<#-- #{} -->

<#assign x=2.345, y=2>

#{x}
#{x; M1}
#{x; M2}
#{y; M3}
#{y; m1}
#{y; m2}
#{y; m3}
#{y; m1M2}
#{y; m2M3}