package eu.nyerel.appmonitor.monitoring;

import eu.nyerel.appmonitor.api.MonitoringListenerRegistry;
import eu.nyerel.appmonitor.model.Method;
import eu.nyerel.appmonitor.model.MethodCall;
import eu.nyerel.appmonitor.model.MonitoringEvent;
import eu.nyerel.appmonitor.util.Validate;

/**
 * @author Rastislav Papp (rastislav.papp@ibacz.eu))
 */
public class CallStack {

    private MethodCall root = null;
    private MethodCall current = null;

    public MethodCall getCurrentMethodCall() {
        return current;
    }

    public void enterMethod(Method method) {
        Validate.notNull(method);
        MethodCall call;
        if (root == null) {
            call = new MethodCall(method);
            root = call;
        } else {
            Validate.notNull(current);
            call = new MethodCall(method, current);
            current.addChild(call);
        }
        current = call;
    }

    public void exitMethod(long duration) {
        Validate.notNull(root);
        Validate.notNull(current);
        current.setDuration(duration);
        if (current == root) {
            notifyRootMethodFinished();
            root = null;
            current = null;
        } else {
            current = current.getParent();
        }
    }

    private void notifyRootMethodFinished() {
        MonitoringEvent event = new MonitoringEvent(root);
        MonitoringListenerRegistry.notifyListeners(event);
    }

}
