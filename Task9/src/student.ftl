<?xml version="1.0" encoding="UTF-8"?>
<students>
 <#list students?values as value>
 	<student>
	  <rollno>${value.get("rollno")}</rollno>
	  <studentname>${value.get("studentname")?replace('"','')}</studentname>
	  <dept>${value.get("dept")?replace('"','')}</dept>
	  <phonenumber>${value.get("phonenumber")?replace('"','')}</phonenumber>
	</student>
 </#list>
</students>
