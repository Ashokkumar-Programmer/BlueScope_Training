<#assign num1 = 5>

${(num1==5)?string("yes", "no")}
${(num1==6)?string("yes", "no")}


${(num1==6)?then("yes", "no")}