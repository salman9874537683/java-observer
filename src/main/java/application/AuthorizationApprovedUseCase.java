package application;

import domain.authorization.Authorization;
import domain.event.DomainEvent;
import domain.event.DomainEventPublisher;

public class AuthorizationApprovedUseCase {

	private final DomainEventPublisher domainEventPublisher;

	public AuthorizationApprovedUseCase(
			final DomainEventPublisher domainEventPublisher) {
		this.domainEventPublisher = domainEventPublisher;
	}

	public void execute(
			final Authorization authorization) {
		DomainEvent event = authorization.approved();
		domainEventPublisher.publish(event);
	}
	
}
