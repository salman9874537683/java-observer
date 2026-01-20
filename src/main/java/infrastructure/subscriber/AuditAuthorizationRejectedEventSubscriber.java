package infrastructure.subscriber;

import domain.authorization.AuthorizationRejectedEvent;
import domain.event.DomainEventSubscriber;

public class AuditAuthorizationRejectedEventSubscriber implements DomainEventSubscriber<AuthorizationRejectedEvent>{

	@Override
	public void handle(AuthorizationRejectedEvent event) {}

	@Override
	public Class<AuthorizationRejectedEvent> subscribedTo() {
		return AuthorizationRejectedEvent.class;
	}

}
