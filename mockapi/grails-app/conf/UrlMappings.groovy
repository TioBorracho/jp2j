class UrlMappings {
	
	static mappings = {
		"/alive"(controller:"api", action:"alive")
		"/content_length"(controller:"api", action = [
				GET:"conContentLength"
			])
		"/echo"(controller:"api", action = [
				GET:"echo",
				POST:"echoPost", 
				PUT:"echoPut",
				DELETE:"echoDelete"
			])
		"/echoHeaders"(controller:"api", action = [
				GET:"echoHeaders"
			])
		"/echoParams"(controller:"api", action = [
			GET:"echoParams", 
			POST:"echoParams"
		])
		"/exception"(controller:"api", action = [
				GET:"exception"
			])
		"/mockapi/$action"(controller:"api")
		"500"(controller:"api", action = [
				GET:"errorHandler"
			])
	}
}
