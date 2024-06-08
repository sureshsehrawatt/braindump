## Main HTTP status codes

### 1xx: Informational
- **100 Continue**: Request headers received, client should proceed with the request body.
- **101 Switching Protocols**: Server agrees to switch protocols as requested.

### 2xx: Success
- **200 OK**: Request succeeded.
- **201 Created**: Request succeeded and a new resource was created.
- **202 Accepted**: Request accepted but not yet processed.
- **204 No Content**: Request succeeded, no content returned.

### 3xx: Redirection
- **301 Moved Permanently**: Resource permanently moved to a new URL.
- **302 Found**: Resource temporarily moved to a different URL.
- **304 Not Modified**: Resource not modified since last request.

### 4xx: Client Errors
- **400 Bad Request**: Malformed request syntax.
- **401 Unauthorized**: Authentication required.
- **403 Forbidden**: Server refuses to fulfill the request.
- **404 Not Found**: Requested resource not found.
- **405 Method Not Allowed**: HTTP method not allowed for the resource.
- **408 Request Timeout**: Server timed out waiting for the request.
- **409 Conflict**: Request conflicts with the current state of the server.
- **410 Gone**: Resource no longer available.
- **418 I'm a teapot**: Intended as an April Fools' joke, not to be implemented.
- **429 Too Many Requests**: Rate limit exceeded.

### 5xx: Server Errors
- **500 Internal Server Error**: Generic server error.
- **501 Not Implemented**: Server does not support the request method.
- **502 Bad Gateway**: Invalid response from an upstream server.
- **503 Service Unavailable**: Server temporarily unavailable.
- **504 Gateway Timeout**: No timely response from upstream server.
- **505 HTTP Version Not Supported**: HTTP version not supported by the server.

These are the essential status codes, each indicating specific issues or results of an HTTP request.