package domain.authorization;

import java.util.UUID;

import domain.event.DomainEvent;

public class Authorization {

	private final UUID id;
	private AuthorizationStatus status;
	
	public Authorization(
			final UUID id,
			final AuthorizationStatus status) {
		this.id = id;
		this.status = status;
	}
	
	public UUID getId() {
		return id;
	}
	
	public AuthorizationStatus getStatus() {
		return status;
	}
	
	public DomainEvent approved() {
		if(status == null || status == AuthorizationStatus.APPROVED)
			throw new IllegalStateException();
		this.status = AuthorizationStatus.APPROVED;
		return new AuthorizationApprovedEvent(id);
	}
	
	public DomainEvent rejected(
			final String reason) {
		if(status == null || status == AuthorizationStatus.PENDING)
			throw new IllegalStateException();
		this.status = AuthorizationStatus.REJECTED;
		return new AuthorizationRejectedEvent(id, reason);
	}
	
}
