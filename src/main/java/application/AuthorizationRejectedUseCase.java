package application;

import domain.authorization.Authorization;
import domain.event.DomainEvent;
import domain.event.DomainEventPublisher;

public class AuthorizationRejectedUseCase {

	private final DomainEventPublisher domainEventPublisher;

	public AuthorizationRejectedUseCase(
			final DomainEventPublisher domainEventPublisher) {
		this.domainEventPublisher = domainEventPublisher;
	}
	
	public void execute(
			final Authorization authorization) {
		DomainEvent event = authorization.rejected("Rejected");
		domainEventPublisher.publish(event);
	}
	
}
