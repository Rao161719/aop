package cn.review;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Aspect
public class MyAspect {

    /*doSome方法*/
    //@Before:在目标方法前执行
    @Before(value = "execution(* *..SomeServiceImpl.doSome())")
    public void aspectBefore(){
        System.out.println("Before:执行在doSome/doAround方法之前");
    }

    @After(value = "execution(* *..SomeServiceImpl.doSome())")
    public void aspectAfter(){
        System.out.println("After:执行在doSome/doAround方法之后");
    }

    /*doOther*/
    //@After：在目标方法之后执行
    //前置通知可以也可以放在目标方法的后面执行
    /*After：最终通知，一定会执行，相当于finally*/
    @Before(value = "execution(* *..SomeServiceImpl.doOther(..))")
    public void Before(JoinPoint jp){
        System.out.println("前置通知：");
        System.out.println(jp.getSignature());//获取：方法返回值类型  完整类名.方法名(参数类型)
        System.out.println(jp.getSignature().getDeclaringType());//获取：文件类型（类/接口/抽象类）  完整 类/接口 名
        System.out.println(jp.getSignature().getName());//获取：方法名
        Object obj[] = jp.getArgs();//获取参数
        for(Object o : obj){
            System.out.print(o+" \n");
        }
    }

    /*doView*/
    //AfterReturning:在目标方法之后执行
    @AfterReturning(value = "execution(* *..SomeServiceImpl.doView(..))",returning = "obj")
    public void View(Object obj){//参数名obj要和上面returning的值一样
        System.out.println("后置通知：");
        System.out.println("目标方法返回值："+obj);
        /*
         返回值：
            引用类型在这里修改会改变原返回值
            普通类型在这里修改不会对原返回值有所影响
         */
    }

    /*doAround*/
    //可以在目标方法 前/后 执行，还能决定目标方法执不执行
    @Around(value = "execution(* *..SomeServiceImpl.doAround(..))")
    public Object Around(ProceedingJoinPoint pjp){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("myApplicationContext.xml");
        MyAspect m = (MyAspect)ctx.getBean("myAspect");
        Object result = null;
        Object objs [] = pjp.getArgs();
        try {
            System.out.println("环绕通知：");

            m.aspectBefore();//在切面类里调用切面方法

            /*
              可以控制目标方法 执不执行
             */
            for (Object obj : objs){
                if(obj.equals("王五")){
                    result = pjp.proceed();//执行目标方法
                }
            }

            m.aspectAfter();//在切面类里调用切面方法

            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    /*doAround2*/
    //环绕通知可以对 目标返回值进行修改（包括简单类型/引用类型）
    @Around(value = "execution(* *..SomeServiceImpl.doAround2(..))")
    public Object doAround2(ProceedingJoinPoint pjp) throws Throwable {
        Student student = (Student) pjp.proceed();
        //第二次对目标方法返回值进行修改
        student.setName("修改了名字");
        student.setAge(200);
        return student;
    }

    /*异常通知方法的定义格式
      1.public
      2.没有返回值
      3.方法名称自定义
      4.方法有一个参数(Exception)，如果还有的话，是JoinPoint
    * */
    /*@AfterThrowing:异常通知
*     属性：1.value 切入点表达式
*          2.throwing 自定义变量，表示目标方法抛出的异常对象
*            变量名必须和方法的参数一样
*     特点：
*       1.在目标方法抛出异常时执行的
*       2.可以做异常的监控程序，监控目标方法执行时是不是有异常
*         如果有异常，可以发送邮件，短信进行通知
*         相当于finally
    * */
    @AfterThrowing(value = "execution(* *..SomeServiceImpl.doSecond(..))",
    throwing = "e")
    public void myAfterThrowing(Exception e){
        System.out.println("切面方法@AfterThrowing:"+e.getMessage());

    }

    /*
     @Pointcut:定义和管理切入点，如果项目中有多个切入点表达式是重复的，可以复用的。
                可以使用@Pointcout
           属性：value切入点表达式
           位置：在自定义的方法上面
       特点：
            使用@Pointcut定义才方法的上面，此时这个方法的名称就是切入点表达式的别名。
            其他的通知中，value属性就可以使用这个方法名称，代替切入点表达式
     */

    @Pointcut(value = "execution(* *..SomeServiceImpl.doSome())")
    public void mypt(){
        /*无需代码*/
    }
    //这个时候使用mypt()方法名，就相当于：execution(* *..SomeServiceImpl.doSome())
    @Before(value = "mypt()")
    public void myBefore(){
        System.out.println("Before:执行在doSome/doAround方法之前");
    }

    /*没有接口时，采用cglib代理*/
    @After(value = "execution(* *..NotImpl.noImpl())")
    public void cglib(){
        System.out.println("没有实现接口时，采用cglib代理");
    }

    /*实现接口，任想采用cglib代理*/
    @After(value = "execution(* *..SomeServiceImpl.doGood())")
    public void doGood(){
        System.out.println("实现接口时，任采用cglib代理");
    }



}
