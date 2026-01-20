package infrastructure.publisher;

import java.util.List;

import domain.event.DomainEvent;
import domain.event.DomainEventPublisher;
import domain.event.DomainEventSubscriber;

public class InMemoryDomainEventPublisher implements DomainEventPublisher {

	private final List<DomainEventSubscriber<?>> subscribers;
	
	public InMemoryDomainEventPublisher(
			final List<DomainEventSubscriber<?>> subscribers) {
		this.subscribers = List.copyOf(subscribers);
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void publish(
			final DomainEvent event) {
		for(DomainEventSubscriber<?> s : this.subscribers) {
			if(s.subscribedTo().equals(event.getClass())) {
				((DomainEventSubscriber)s).handle(event);
			}
		}
	}

}
