package cn.rqf.ba03;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

@Aspect
public class MyAspect {
    /*
    环绕通知方法的定义格式
      1.public
      2.必须有一个返回值,推荐使用Object
      3.方法名称自定义
      4.方法有参数,固定的参数ProceedingJoinPoint
     */
    /*
     @Around:环绕通知
            属性:value切入点表达式
            位置:在方法的定义上面
         特点:
           1.它是功能最强的通知
           2.可以在目标方法的前和后都能增强功能
           3.控制目标方法是否被调用执行
           4.修改原来目标方法的执行结果,影响最后的调用结果

         环绕通知,等同于jdk动态代理的InvocationHandler接口

         参数: ProceedingJoinPoint 就等于 Method
                作用:执行目标方法的
         返回值: 就是目标方法的执行结果,可以被修改
     */
    @Around(value = "execution(* *..SomeServiceImpl.doFirst(..))")
    public Object myAround(ProceedingJoinPoint pjp) throws Throwable {
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        Object obj [] = pjp.getArgs();
        //从容器中获取目标对象
        BeforeTest before = (BeforeTest) ctx.getBean("beforeTest");
        AfterTest after = (AfterTest) ctx.getBean("afterTest");

        //实现环绕通知
        Object result = null;
        System.out.println("环绕通知:在目标方法之前,输出时间:"+new Date());
        before.before();
        //1.目标方法调用
        for(Object o : obj){
            if(o.equals("张三")){
                result = pjp.proceed();//相当于jdk动态代理里的method.invoke();  Object result = doFirst();
                if(result != null){
                    /*可以对目标方法返回结果进行修改
                    * 包括简单类型和引用类型*/
                    result = "将目标方法执行结果进行了修改";
                }
            }
        }

        System.out.println("环绕通知:在目标方法之后,提交事务");
        //2.在目标方法前或者后加入功能
        after.after();
        //返回目标方法的执行结果
        return result;
    }
    @Around(value = "execution(* *..SomeServiceImpl.doFirst2(..))")
    public Object myAround2(ProceedingJoinPoint pjp) throws Throwable {
        /*
         环绕通知：可以对目标方法返回值（引用类型）进行修改，并印象到原返回值内容
         */
        System.out.println("切面方法：myAround2");
        Student student = (Student) pjp.proceed();
        student.setName("修改名字");
        student.setAge(200);
        return student;

    }
    @Around(value = "execution(* *..SomeServiceImpl.doFirst3(..))")
    public Object doFirst3(ProceedingJoinPoint pjp) throws Throwable {
        int i =(int) pjp.proceed();
        i = 666;
        return i;
    }


    /*目标类可以不是接口实现类
      目标方法a可以添加切面方法b
      而切面方法b还可以再添加它的切面方法c
      当目标方法a执行时,会执行切面方法b,然后再执行切面方法c
     */
    @After(value = "execution(* *..AfterTest.after())")
    public void test(){
        System.out.println("不是接口实现方法实现aop");
    }
}
