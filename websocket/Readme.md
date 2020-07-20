> Uses the TCP connections.
> The first request is similar to http but gets upgraded to websocket to have a persistent connection.

# Spring-boot Elements:

> STOMP Message
> Message-handling controller
	> @MessageMapping - (just like /path), mapped to a specific end-point (to receive messages from clients).
	> @SendTo - send reply to a specific destination. Client subscribe to this destination to receive message from server.
> WebsocketMessageBroker
	> @EnableWebSocketMessageBroker - to enable websocket messaging. The configuration class to enable message brokers, specify destinations and message prefixes, if any. Also, enables and registers the stomp end-points, and fall-back mechanism.

	
	 

 