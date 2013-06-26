package eu.nyerel.appmonitor.model;

import eu.nyerel.appmonitor.util.Validate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Rastislav Papp (rastislav.papp@ibacz.eu))
 */
public abstract class BaseMethodCall<T extends BaseMethodCall> {

    private long id;
    private final Method method;
    private final T parent;
    private final List<T> children = new ArrayList<T>();
    private long duration;

    public BaseMethodCall(Method method) {
        this(method, null);
    }

    public BaseMethodCall(Method method, T parent) {
        Validate.notNull(method);
        this.method = method;
        this.parent = parent;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public long getDuration() {
        return duration;
    }

    public long getSelfDuration() {
        return duration - getChildrenDuration();
    }

    public long getChildrenDuration() {
        long sum = 0;
        for (T child : children) {
            sum += child.getDuration();
        }
        return sum;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Method getMethod() {
        return method;
    }

    public T getParent() {
        return parent;
    }

    public List<T> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(T child) {
        children.add(child);
    }

    public String toString(int level) {
        StringBuilder sb = new StringBuilder();
        indent(sb, level);
        sb.append(method.toString());
        if (!children.isEmpty()) {
            sb.append("\n");
        }
        for (int i = 0; i < children.size(); i++) {
            sb.append(children.get(i).toString(level + 1));
            if (i < children.size() - 1) {
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
