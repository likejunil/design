package june1.aop.dynamic_proxy;

import june1.aop.trace.LogTrace;
import june1.aop.trace.TraceStatus;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class ColorInvocation implements InvocationHandler {

    private final ColorInterface target;
    private final LogTrace logTrace;

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        String title = method.getDeclaringClass() + "." + method.getName() + "()";
        TraceStatus status = null;
        try {
            status = logTrace.begin(title);
            //------------------------------------------
            Object ret = method.invoke(target, objects);
            //------------------------------------------
            logTrace.end(status);
            return ret;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
