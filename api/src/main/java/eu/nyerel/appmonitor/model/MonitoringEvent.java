package eu.nyerel.appmonitor.model;

/**
 * @author Rastislav Papp (rastislav.papp@ibacz.eu))
 */
public class MonitoringEvent {

    private final MethodCall methodCall;

    public MonitoringEvent(MethodCall methodCall) {
        this.methodCall = methodCall;
    }

    public MethodCall getMethodCall() {
        return methodCall;
    }

}
