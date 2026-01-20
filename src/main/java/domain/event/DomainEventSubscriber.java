package domain.event;

public interface DomainEventSubscriber<T extends DomainEvent> {

	void handle(T event);
	
	Class<T> subscribedTo();
}
