package application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import domain.authorization.Authorization;
import domain.authorization.AuthorizationApprovedEvent;
import domain.authorization.AuthorizationStatus;
import domain.event.DomainEventPublisher;
import domain.event.DomainEventSubscriber;
import infrastructure.publisher.InMemoryDomainEventPublisher;

class AuthorizationApprovedUseCaseTest {

	class FakeAuthorizationApprovedSubcriber implements DomainEventSubscriber<AuthorizationApprovedEvent> {

		boolean called = false;
		
		@Override
		public void handle(AuthorizationApprovedEvent event) {
			called = true;
		}

		@Override
		public Class<AuthorizationApprovedEvent> subscribedTo() {
			return AuthorizationApprovedEvent.class;
		}
		
	}
	
	@Test
	void shouldApprovedAuthorizationAndPublishApprovedEvent() {
		FakeAuthorizationApprovedSubcriber subscriber = new FakeAuthorizationApprovedSubcriber();
		List<DomainEventSubscriber<?>> subcribers = List.of(subscriber);
		DomainEventPublisher publisher = new InMemoryDomainEventPublisher(subcribers);
		AuthorizationApprovedUseCase usecase = new AuthorizationApprovedUseCase(publisher);
		Authorization authorization = new Authorization(UUID.randomUUID(), AuthorizationStatus.PENDING);
		assertFalse(subscriber.called);
		usecase.execute(authorization);
		assertTrue(subscriber.called);
	}

}
