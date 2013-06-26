package eu.nyerel.appmonitor.core;

import eu.nyerel.appmonitor.api.MonitoringListener;
import eu.nyerel.appmonitor.api.MonitoringListenerRegistry;
import eu.nyerel.appmonitor.core.service.MonitoringService;
import eu.nyerel.appmonitor.model.MonitoringEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Rastislav Papp (rastislav.papp@ibacz.eu))
 */
@Component
public class MonitoringListenerImpl implements MonitoringListener {

    @Autowired
    private MonitoringService monitoringService;

    @PostConstruct
    public void init() throws Exception {
        MonitoringListenerRegistry.registerListener(this);
    }

    public void onMonitoringEvent(MonitoringEvent event) {
        monitoringService.addMethodCall(event.getMethodCall());
    }

    @PreDestroy
    public void cleanUp() {
        MonitoringListenerRegistry.unregisterListener(this);
    }

}
