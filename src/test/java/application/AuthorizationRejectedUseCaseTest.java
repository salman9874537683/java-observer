package application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import domain.authorization.Authorization;
import domain.authorization.AuthorizationRejectedEvent;
import domain.authorization.AuthorizationStatus;
import domain.event.DomainEventPublisher;
import domain.event.DomainEventSubscriber;
import infrastructure.publisher.InMemoryDomainEventPublisher;

class AuthorizationRejectedUseCaseTest {

	class FakeAuthorizationRejectedSubcriber implements DomainEventSubscriber<AuthorizationRejectedEvent> {

		boolean called = false;

		@Override
		public void handle(AuthorizationRejectedEvent event) {
			called = true;
		}

		@Override
		public Class<AuthorizationRejectedEvent> subscribedTo() {
			return AuthorizationRejectedEvent.class;
		}
		
	}
	
	@Test
	void shouldRejectedAuthorizationAndPublishRejectedEvent() {
		FakeAuthorizationRejectedSubcriber subscriber = new FakeAuthorizationRejectedSubcriber();
		List<DomainEventSubscriber<?>> subcribers = List.of(subscriber);
		DomainEventPublisher publisher = new InMemoryDomainEventPublisher(subcribers);
		AuthorizationRejectedUseCase usecase = new AuthorizationRejectedUseCase(publisher);
		Authorization authorization = new Authorization(UUID.randomUUID(), AuthorizationStatus.APPROVED);
		assertFalse(subscriber.called);
		usecase.execute(authorization);
		assertTrue(subscriber.called);
	}
}
