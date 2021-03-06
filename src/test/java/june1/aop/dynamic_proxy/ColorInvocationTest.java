package june1.aop.dynamic_proxy;

import june1.aop.trace.LogTrace;
import june1.aop.trace.LogTraceImpl;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

class ColorInvocationTest {

    @Test
    void test() {
        LogTrace logTrace = new LogTraceImpl();
        ColorInterface target = new RedImpl();
        InvocationHandler handler = new ColorInvocation(target, logTrace);
        ColorInterface proxy = (ColorInterface) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                new Class[]{ColorInterface.class},
                handler);

        String ret = proxy.color("black");
        System.out.println(ret);
    }
}