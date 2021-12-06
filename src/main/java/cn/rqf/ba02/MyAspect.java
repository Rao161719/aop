package cn.rqf.ba02;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyAspect {
    /*
    后置通知定义方法,方法时实现切面功能的
    方法的定义要求:
        1.公共方法public
        2.方法没有返回值
        3.方法名称自定义
        4.方法有参数,推荐是Object,参数名自定义
     */
    /*
    @AfterReturning:后置通知
        属性:1.value 切入点表达式
            2.returning 自定义的变量,表示目标方法的返回值
              自定义变量名必须和通知方法的形参名一样
        位置:在方法定义的上面
        特点:
            1.在目标方法之后执行的
            2.能够获取到目标方法的返回值,可以根据这个返回值做不同的处理功能
              Object res = doOther();
            3.可以修改这个返回值

          后置通知的执行
             Object res = doOther();
             myAfterReturning(res);
     */
    @AfterReturning(value = "execution(String *..SomeServiceImpl.doOther(..))", returning = "res")
    public void myAfterReturning(Object res){//这个res要和上面一样
        //Object res:目标方法执行后的返回值,根据返回值做你的切面功能处理
        System.out.println("后置通知(获取目标方法返回值):在目标方法之后执行的"+res);
        //简单类型的数据res不会被影响
        res = "修改zs";
        System.out.println("切面方法res="+res);
    }
    //前置通知也可以执行在目标方法执行之后
    @After(value = "execution(String *..SomeServiceImpl.doOther(..))")
    public void myAfter(JoinPoint jp){//这个res要和上面一样
        //获取方法的完整定义
        System.out.println("myAfter---");
        System.out.println("方法的签名(定义):"+jp.getSignature());
        System.out.println("方法名称="+jp.getSignature().getName());
        //获取方法的实参
        Object args[] = jp.getArgs();
        for(Object obj : args){
            System.out.println("参数="+obj);
        }
    }

    @AfterReturning(value = "execution(* *..SomeServiceImpl.doOther2(..))", returning = "res")
    public void myStudent(Object res){//这个res要和上面一样
        //Object res:目标方法执行后的返回值,根据返回值做你的切面功能处理
       /*如果修改了res的内容,属性值,是不是会影响最后的调用结果?
       而引用类型会被影响
         结果:会
        */
        System.out.println("在doOther方法后执行");
        Student s =(Student) res;
        s.setName("李四");
        s.setAge(20);
    }

    @AfterReturning(value = "execution(* *..SomeServiceImpl.doOther3(..))", returning = "res")
    public void doOther3(Object res){//这个res要和上面一样
        //Object res:目标方法执行后的返回值,根据返回值做你的切面功能处理
       /*如果修改了res的内容,属性值,是不是会影响最后的调用结果?
       而引用类型会被影响
         结果:会
        */
        System.out.println("在doOther3后执行");
        res = "AfterReturning对String类型进行了修改";//对String类型 不能修改
    }

    //测试切面方法
    @After(value = "execution(* *.test())")
    public void mytest(){
        System.out.println("测试方法,执行在目标测试方法之后");
    }
}






