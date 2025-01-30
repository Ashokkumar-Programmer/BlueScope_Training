<#-- Macro -->

<#macro mac name dept rollno>

Rollno: ${rollno}
Name: ${name}
Dept: ${dept}

</#macro>

<@mac rollno="95072352007" name="Ashok Kumar" dept="MCA" />


<#macro nest>

1. <#nested>
2. <#nested>
3. <#nested>

</#macro>

<@nest>Ashok</@nest>


<#macro nest1>

<#nested "Ashok">
<#nested "Hari">
<#nested "Joeswa">

</#macro>

<@nest1 ;x>
${x}
</@nest1>

<#macro mac name dept rollno>

Rollno: ${rollno}
<#return>
Name: ${name}
Dept: ${dept}

</#macro>

<@mac rollno="95072352007" name="Ashok Kumar" dept="MCA" />