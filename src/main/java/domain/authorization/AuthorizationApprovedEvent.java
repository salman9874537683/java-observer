package domain.authorization;

import java.time.Instant;
import java.util.UUID;

import domain.event.DomainEvent;

public class AuthorizationApprovedEvent implements DomainEvent {

	private final UUID authorizationId;
	private final Instant occurredOn;
	
	public AuthorizationApprovedEvent(
			final UUID authorizationId) {
		this.authorizationId = authorizationId;
		this.occurredOn = Instant.now();
	}
	
	public UUID getAuthorizationId() {
		return authorizationId;
	}
	
	@Override
	public Instant occurredOn() {
		return occurredOn;
	}

}
