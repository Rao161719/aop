package cn.rqf.ba04;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyAspect {
    /*@After(value = "execution(* *..SomeServiceImpl.doOther(..))")
    public void myAfterReturning(){//这个res要和上面一样理
        System.out.println("后置通知(获取目标方法返回值):在目标方法之后执行的");
    }*/

    @After(value = "execution(* *..Testt.myTest())")
    public void myAfterTest(){
        System.out.println("后置通知(获取目标方法返回值):在目标方法之后执行的");
    }
}

