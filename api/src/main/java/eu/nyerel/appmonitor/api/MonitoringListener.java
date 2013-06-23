package eu.nyerel.appmonitor.api;

import eu.nyerel.appmonitor.model.MonitoringEvent;

/**
 * @author Rastislav Papp (rastislav.papp@ibacz.eu))
 */
public interface MonitoringListener {

    void onMonitoringEvent(MonitoringEvent event);

}
