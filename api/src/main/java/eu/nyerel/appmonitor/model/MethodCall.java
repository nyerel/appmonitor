package eu.nyerel.appmonitor.model;

/**
 * @author Rastislav Papp (rastislav.papp@ibacz.eu))
 */
public class MethodCall extends BaseMethodCall<MethodCall> {

    private final long startTime;

    public MethodCall(Method method) {
        this(method, null);
    }

    public MethodCall(Method method, MethodCall parent) {
        super(method, parent);
        this.startTime = System.currentTimeMillis();
    }

    public long getStartTime() {
        return startTime;
    }

}
