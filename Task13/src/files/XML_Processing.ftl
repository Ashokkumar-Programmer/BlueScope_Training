<#-- access data -->

${data.book.title}

${data.book.chapter[0].title}

${data.book.chapter[1].title}

${data.book.chapter[0].para[1]}

${data.book.chapter[1].para[1]}

<#list data.book.chapter[0] as chapter>
${chapter.para[(chapter?counter)-1]}
</#list>

<#-- node_name -->

${data.book?node_name}

<#-- node_type -->

${data.book?node_type}

<#-- children -->

<#list data.book?children as c>
${c?node_type} ${c?node_name}
</#list>

<#-- parent -->

${data.book.chapter[0].para[0]?parent?node_name}
${data.book.chapter[0].para[0]?parent?parent?node_name}

${data?root.book.title}