{
  "students": [
	<#list data?values as value>
	{
		"rollno": ${value.rollno},
		"studentname": "${value.studentname}",
		"dept": "${value.dept}",
		"phonenumber": ${value.phonenumber}
	}<#if value?is_last><#else>,</#if>
	</#list>
  ]
 }