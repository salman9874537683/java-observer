package domain.authorization;

import java.time.Instant;
import java.util.UUID;

import domain.event.DomainEvent;

public class AuthorizationRejectedEvent implements DomainEvent {

	private final UUID authorizationId;
	private final String cause;
	private final Instant occurredOn;
	
	public AuthorizationRejectedEvent(
			 final UUID authorizationId,
			 final String cause) {
		this.authorizationId = authorizationId;
		this.cause = cause;
		occurredOn = Instant.now();
	}
	
	public UUID getAuthorizationId() {
		return authorizationId;
	}
	
	public String getCause() {
		return cause;
	}
	
	@Override
	public Instant occurredOn() {
		return occurredOn;
	}

}
