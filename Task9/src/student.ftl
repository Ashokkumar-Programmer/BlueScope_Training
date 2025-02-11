<?xml version="1.0" encoding="UTF-8"?>
<students>
 <#list students?values as value>
 	<student>
 	  <#if value.get("rollno")?number!=0>
	  <rollno>${value.get("rollno")}</rollno>
	  </#if>
	  <#if value.get("studentname")?replace('"','')?has_content>
	  <studentname>${value.get("studentname")?replace('"','')}</studentname>
	  </#if>
	  <#if value.get("dept")?replace('"','')?has_content>
	  <dept>${value.get("dept")?replace('"','')}</dept>
	  </#if>
	  <#if value.get("phonenumber")?replace('"','')?number!=0>
	  <phonenumber>${value.get("phonenumber")?replace('"','')}</phonenumber>    
	  </#if>
	</student>
 </#list>
</students>
