import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import newhttp.Callback;

/**
 * Created by CTAS on 2017/12/30.
 */
public class MethodInvocation implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(args==null||args.length==0){
            throw new IllegalArgumentException("args 不能为空");
        }
        // 最后一个参数必须是callback类型
        if(!(args[args.length-1] instanceof Callback){
            throw new IllegalArgumentException("args 不能为空");
        }

        for (Annotation annotation : method.getAnnotations()) {

        }
        return null;
    }
}
