package eu.nyerel.appmonitor.model;

import eu.nyerel.appmonitor.util.Validate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Rastislav Papp (rastislav.papp@ibacz.eu))
 */
public class MethodCall {

    private final Method method;
    private final MethodCall parent;
    private final List<MethodCall> innerMethodCalls = new ArrayList<MethodCall>();
    private final long startTime;
    private long duration;

    public MethodCall(Method method) {
        this(method, null);
    }

    public MethodCall(Method method, MethodCall parent) {
        Validate.notNull(method);
        this.method = method;
        this.parent = parent;
        this.startTime = System.currentTimeMillis();
    }

    public long getStartTime() {
        return startTime;
    }

    public long getDuration() {
        return duration;
    }

    public long getSelfDuration() {
        return duration - getInnerMethodsDuration();
    }

    public long getInnerMethodsDuration() {
        long sum = 0;
        for (MethodCall innerMethodCall : innerMethodCalls) {
            sum += innerMethodCall.getDuration();
        }
        return sum;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Method getMethod() {
        return method;
    }

    public MethodCall getParent() {
        return parent;
    }

    public List<MethodCall> getInnerMethodCalls() {
        return Collections.unmodifiableList(innerMethodCalls);
    }

    public void addInnerMethodCall(MethodCall call) {
        innerMethodCalls.add(call);
    }

    public String toString(int level) {
        StringBuilder sb = new StringBuilder();
        indent(sb, level);
        sb.append(method.toString());
        if (!innerMethodCalls.isEmpty()) {
            sb.append("\n");
        }
        for (int i = 0; i < innerMethodCalls.size(); i++) {
            sb.append(innerMethodCalls.get(i).toString(level + 1));
            if (i < innerMethodCalls.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private void indent(StringBuilder sb, int level) {
        for (int i = 0; i < level; i++) {
            sb.append("    ");
        }
    }

    @Override
    public String toString() {
        return toString(0);
    }

}
