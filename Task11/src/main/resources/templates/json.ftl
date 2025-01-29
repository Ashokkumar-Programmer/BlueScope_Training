{
	"Remittance": {
		"value": {
			"sendersReferenceId": "${data.value.sendersReferenceId}",
			"channelId": "${data.value.channelId?string?replace(",","")}",
			"masterReferenceId": "${data.value.masterReferenceId?string?replace(",","")}",
			"status": "${data.value.status}",
			"message": "${data.value.message}"
		}
	}
}