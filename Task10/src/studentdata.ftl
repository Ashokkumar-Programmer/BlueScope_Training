{
  "students": [
  	<#list students?values as student>
	  {
	  	"rollno": ${student["rollno"]},
	  	"studentname": "${student["studentname"]}",
	  	"dept": "${student["dept"]}",
	  	"phonenumber": ${student["phonenumber"]}
	  }<#if student?is_last>
	  <#else>,
	  </#if>
	</#list>
  ]
 }