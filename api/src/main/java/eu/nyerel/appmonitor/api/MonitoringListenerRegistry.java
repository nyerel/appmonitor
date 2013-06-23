package eu.nyerel.appmonitor.api;

import eu.nyerel.appmonitor.model.MonitoringEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rastislav Papp (rastislav.papp@ibacz.eu))
 */
public class MonitoringListenerRegistry {

    private static final List<MonitoringListener> LISTENERS = new ArrayList<MonitoringListener>();

    private MonitoringListenerRegistry() {
    }

    public static void registerListener(MonitoringListener listener) {
        LISTENERS.add(listener);
    }

    public static void unregisterListener(MonitoringListener listener) {
        if (!LISTENERS.contains(listener)) {
            throw new IllegalArgumentException("Supplied listener is not registered!");
        }
        LISTENERS.remove(listener);
    }

    public static void notifyListeners(MonitoringEvent event) {
        for (MonitoringListener listener : LISTENERS) {
            listener.onMonitoringEvent(event);
        }
    }

}
