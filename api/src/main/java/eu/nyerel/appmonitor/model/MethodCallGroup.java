package eu.nyerel.appmonitor.model;

import eu.nyerel.appmonitor.util.Validate;

import java.util.Collection;

/**
 * @author Rastislav Papp (rastislav.papp@ibacz.eu))
 */
public class MethodCallGroup extends BaseMethodCall<MethodCallGroup> {

    private int count = 0;

    public MethodCallGroup(Method method) {
        this(method, null);
    }

    public MethodCallGroup(Method method, MethodCallGroup parent) {
        super(method, parent);
    }

    public void addCall(MethodCall call) {
        Validate.isTrue(call.getMethod().equals(getMethod()));
        increaseDuration(call.getDuration());
        count++;
    }

    public void addCalls(Collection<MethodCall> calls) {
        for (MethodCall call : calls) {
            addCall(call);
        }
    }

    private void increaseDuration(long amount) {
        setDuration(getDuration() + amount);
    }

}
