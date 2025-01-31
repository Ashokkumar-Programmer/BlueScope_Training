<#ftl output_format="HTML">

<#-- blank_to_null -->

<#assign str1 = "">

${str1???c}

<#-- cap_first -->

<#assign str2 = "ashok">

${str2?cap_first}

<#-- upper_case -->

${str2?upper_case}

<#-- lower_case -->

<#assign str2 = "Ashok kumar">

${str2?lower_case}

<#-- capitalize -->

${str2?capitalize}

<#-- contains -->

<#if str2?contains("hok")>${str2} contains "hok"</#if>

<#-- date, time -->

<#assign date = "Jan 31, 2025"?date>

<#assign date = "31/12/2025"?date("DD/MM/YYYY")>

<#assign time = "12:10:30"?time("HH:MM:SS")>

${date}

${time}

<#-- starts-with -->

${str2?starts_with("Ash")?c}

<#-- ends_with -->

${str2?ends_with("ar")?c}

<#-- ensure_ends_with -->

${str2?ensure_ends_with("a")}

<#-- ensure_start-with -->

${str2?ensure_starts_with(" ")}

<#-- index_of -->

${str2?index_of("ar")}

<#-- j_string -->

<#assign str3 = 'Hi "Ashok Kumar"'>

${str3?j_string}

<#-- js_string -->

${str3?js_string}

<#-- keep_after -->

${str2?keep_after("ok")}

<#-- keep_after-last -->

${str2?lower_case?keep_after_last("k")}

<#-- keep_before -->

${str2?lower_case?keep_before("k")}

<#-- keep_before_last -->

${str2?lower_case?keep_before_last("k")}

<#-- last_index_of -->

${str2?lower_case?last_index_of("k")}

<#-- left_pad -->

[${"a"?left_pad(5)}]
[${"    a"?left_pad(5)}]
[${"a"?left_pad(5, "-")}]
[${"a"?left_pad(5, "000")}]

<#-- right_pad -->

[${"a"?right_pad(5)}]
[${"a"?right_pad(5, "-")}]
[${"a    "?right_pad(5)}]
[${"a"?right_pad(5, "000")}]

<#-- no_esc -->

<#assign htmlstr = "<b>AK & Softwares</b>">

${htmlstr}
${htmlstr?esc}
${htmlstr?no_esc}

<#-- replace -->

${str2?replace("kumar", "Kumar")}

<#-- remove_beginning -->

${str2?remove_beginning("Ash")}

<#-- remove_ending -->

${str2?remove_ending("mar")}

<#-- split -->

<#list str2?split("hok") as s>
${s}
</#list>

<#-- substring -->

${str2?substring(1)}
${str2?substring(0,3)}

<#-- trim -->

<#assign str2 = "        Ashok Kumar             ">
<#assign str2 = str2?trim>

${str2}

<#-- trim_to_null -->

${"        "?trim???c}

<#-- url -->

<#assign x = 'a/b c'>
${x?url('ISO-8859-1')}