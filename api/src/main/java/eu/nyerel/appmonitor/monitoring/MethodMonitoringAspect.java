package eu.nyerel.appmonitor.monitoring;

import eu.nyerel.appmonitor.model.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Rastislav Papp (rastislav.papp@ibacz.eu))
 */
@Aspect
public abstract class MethodMonitoringAspect {

    @Pointcut("")
    protected abstract void monitoredMethods();

    @Around("monitoredMethods()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        CallStack callStack = CallStackHolder.getCallStack();
        Method method = createMethod(pjp);
        callStack.enterMethod(method);
        long start = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } finally {
            callStack.exitMethod(System.currentTimeMillis() - start);
        }
    }

    private Method createMethod(ProceedingJoinPoint pjp) {
        return new Method(pjp.getTarget().getClass(), pjp.getSignature().getName());
    }

}
