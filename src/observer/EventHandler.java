package observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHandler {
    Map<String, List<EventListener>> listeners = new HashMap<>();

    public EventHandler(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String status, String eventType) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.update(status, eventType);
        }
    }
}