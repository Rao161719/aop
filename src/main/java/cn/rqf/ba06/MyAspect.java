package cn.rqf.ba06;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyAspect {
    @After(value = "execution(* *..SomeServiceImpl.doOther())")
    public void myAfterReturning(){
        System.out.println("后置通知(获取目标方法返回值):在目标方法之后执行的");
    }
}

