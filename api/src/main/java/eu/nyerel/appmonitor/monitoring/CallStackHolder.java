package eu.nyerel.appmonitor.monitoring;

/**
 * @author Rastislav Papp (rastislav.papp@ibacz.eu))
 */
public class CallStackHolder {

    private static final ThreadLocal<CallStack> STACK_TL = new ThreadLocal<CallStack>();

    private CallStackHolder() {

    }

    public static CallStack getCallStack() {
        CallStack cs = STACK_TL.get();
        if (cs == null) {
            cs = new CallStack();
            STACK_TL.set(cs);
            return cs;
        } else {
            return cs;
        }
    }

}
