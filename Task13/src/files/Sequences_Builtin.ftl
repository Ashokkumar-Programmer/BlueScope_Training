<#assign seq1 = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j']>

<#-- chunk -->

<#list seq1?chunk(4) as list1>
<#list list1 as list2>${list2}</#list>
</#list>

<#list seq1?chunk(4, "-") as list1>
<#list list1 as list2>${list2}</#list>
</#list>

<#-- drop_while, filter, take_while -->

<#assign num = [1, 2, 3, -4, -5, 6]>

<#list num?drop_while(x -> x > 0) as x>${x} </#list>

<#list num?take_while(x -> x > 0) as x>${x} </#list>

<#list num?filter(x -> x > 0) as x>${x} </#list>

<#-- join -->

${seq1?join(", ")}

<#-- map -->

<#assign users = [
  {"name": "Alice", "age": 25, "email": "alice@example.com"},
  {"name": "Bob", "age": 30, "email": "bob@example.com"},
  {"name": "Charlie", "age": 28, "email": "charlie@example.com"}
]>

<#assign userNames = users?map(user -> user.name)>

${userNames?join(", ")}

<#-- min, max -->

${[1,2,3]?min}
${[1,2,3]?max}
${[]?max!"No values"}

<#-- reverse -->

${seq1?reverse?join(", ")}

<#-- seq_contains -->

${seq1?seq_contains("b")?string("yes", "no")}

<#-- seq_index_of -->

${seq1?seq_index_of("c")}

<#-- seq_last_index_of -->

${[1,2,3,4,1,5,6]?seq_last_index_of(1)}

<#-- size -->

${seq1?size}

<#-- sort -->

${num?sort?join(", ")}

<#-- sort_by -->

${users?sort_by("age")?map(x->x.age)?join(", ")}

