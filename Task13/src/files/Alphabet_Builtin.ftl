<#-- Alphabetic Built-in functions -->

<#-- abs -->

<#assign num1=-5>

${num1?abs}

<#-- is_infinte -->

<#assign num2 = 3.3333>

${num2?is_infinite?c}

<#-- is_nan -->

${3.3?is_nan?c}

<#-- lower_abc -->

<#list 1..26 as n>${n?lower_abc} </#list>

<#-- upper_abc -->

<#list 1..26 as n>${n?upper_abc} </#list>

<#-- floor, ceil, round -->

${num2?floor}
${num2?ceiling}
${num2?round}

<#-- convert to string -->

${num2?string}
${num2?string.number}
${num2?string.currency}
${num2?string.percent}
${num2?string["0"]}
${num2?string["0.#"]}

<#-- number_format -->

<#setting number_format="0.##">

${num2}