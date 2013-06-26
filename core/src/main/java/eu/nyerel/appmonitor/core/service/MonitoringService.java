package eu.nyerel.appmonitor.core.service;

import com.google.common.collect.Lists;
import eu.nyerel.appmonitor.model.MethodCall;
import eu.nyerel.appmonitor.util.Validate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author Rastislav Papp (rastislav.papp@ibacz.eu))
 */
@Component
public class MonitoringService {

    private List<MethodCall> calls = Lists.newArrayList();

    private long idSequence = 0;

    public List<MethodCall> getMonitoredMethodCalls() {
        return Collections.unmodifiableList(calls);
    }

    public void addMethodCall(MethodCall call) {
        Validate.notNull(call);
        setIds(call);
        calls.add(call);
    }

    private void setIds(MethodCall call) {
        call.setId(nextId());
        for (MethodCall child : call.getChildren()) {
            setIds(child);
        }
    }

    private long nextId() {
        return idSequence++;
    }

}
