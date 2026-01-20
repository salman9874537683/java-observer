package domain.authorization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import domain.event.DomainEvent;

class AuthorizationTest {

	@Test
	void shouldApprovedEvent() {
		Authorization authorization = new Authorization(UUID.randomUUID(), AuthorizationStatus.PENDING);
		DomainEvent event = authorization.approved();
		assertEquals(AuthorizationStatus.APPROVED, authorization.getStatus());
		assertNotNull(event);
		assertTrue(event instanceof AuthorizationApprovedEvent);
	}

	@Test
	void shouldNotPossibleToApproveAnAuthorizationThatHasAlreadyBeenApproved() {
		Authorization authorization = new Authorization(UUID.randomUUID(), AuthorizationStatus.APPROVED);
		assertThrows(IllegalStateException.class, () -> {
			authorization.approved();
		});
	}

	@Test
	void shouldRejectedEvent() {
		Authorization authorization = new Authorization(UUID.randomUUID(), AuthorizationStatus.APPROVED);
		DomainEvent event = authorization.rejected("Any");
		assertEquals(AuthorizationStatus.REJECTED, authorization.getStatus());
		assertNotNull(event);
		assertTrue(event instanceof AuthorizationRejectedEvent);
	}
	
	@Test
	void shouldNotBePossibleToRejectedAPendingAuthorization() {
		Authorization authorization = new Authorization(UUID.randomUUID(), AuthorizationStatus.PENDING);
		assertThrows(IllegalStateException.class, () -> {
			authorization.rejected("");
		});
	}

}



