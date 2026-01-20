package infrastructure.subscriber;

import domain.authorization.AuthorizationApprovedEvent;
import domain.event.DomainEventSubscriber;

public class AuditAuthorizationApprovedEventSubscriber implements DomainEventSubscriber<AuthorizationApprovedEvent>{

	@Override
	public void handle(AuthorizationApprovedEvent event) {}

	@Override
	public Class<AuthorizationApprovedEvent> subscribedTo() {
		return AuthorizationApprovedEvent.class;
	}

}
