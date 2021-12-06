package cn.rqf.ba01;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Date;

/*
 @Aspect:是aspectj框架中的注解
        作用:表示当前类是切面类
        切面类:是用来给业务方法增加功能的类,在这个类中有切面的功能代码
        位置:在类定义的上面
 */
@Aspect
public class MyAspect {
    /*
    定义方法,方法时实现切面功能的
    方法的定义要求:
       1.公共方法public
       2.方法没有返回值
       3.方法名称自定义
       4.方法可以有参数,也可以没有参数
         如果有参数,参数不是自定义的,有几个参数类型可以使用
     */
    //@Before:前置通知注解
    //属性:value,是切入点表达式,表示切面的功能执行的位置
    //位置:在方法的上面
    /*特点:
        1.在目标方法之前先执行
        2.不会改变目标方法的执行结果
        3.不会影响
     */
    //完整写法:
    /*@Before(value = "execution(public void cn.rqf.ba01.SomeServiceImpl.doSome(String,Integer))")
    public void myBefore(){
        //这里就是切面要执行的功能代码
        System.out.println("切面功能:在目标方法之前输入执行时间:"+new Date());
    }*/
    //简便写法1.:前面的包用 "*.." 代替
    /*@Before(value = "execution(void *..SomeServiceImpl.doSome(String,Integer))")
    public void myBefore(){
        //这里就是切面要执行的功能代码
        System.out.println("1===切面功能:在目标方法之前输入执行时间:"+new Date());
    }*/
    //简便写法2.:返回值用 "*" 代替;参数类型用 ".."代替;方法名用 "*" 或 "do*"代替
    /*
        指定通知方法中的参数:JoinPoint
        JoinPoint:业务方法,要加入切面功能的业务方法
            作用是:可以在通知方法中获取方法执行时的信息,例如:方法名称,方法的实参
            如果你的切面功能中需要用到方法的信息,就加入JoinPoint
            这个JoinPoint参数的值是由框架赋予,必须是第一个位置的参数
     */
    @Before(value = "execution(* *..SomeServiceImpl.*(..))")
    /*当在切面方法中加入JoinPoint参数后
      在执行目标方法时会将方法的全部信息赋给这个参数
     */

    public void myBefore(JoinPoint jp){//org.aspectj.lang,多个参数是放在第一位
        //获取方法的完整定义
        System.out.println("方法的签名(定义):"+jp.getSignature());
        System.out.println("方法名称="+jp.getSignature().getName());
        //获取方法的实参
        Object args[] = jp.getArgs();
        for(Object obj : args){
            System.out.println("参数="+obj);
        }
        //这里就是切面要执行的功能代码
        System.out.println("3===切面功能:在目标方法之前输入执行时间:"+new Date());
    }
    //总结:execution()括号里任意位置都可以用*代替,但要指明你要实现的方法
    //    有多个相同的方法时就需要指明哪个类,类相同就要指定哪个包,写清楚就行

    /*
    @Before(value = "execution(* *..SomeServiceImpl.*(..))")
    当这里写错时,在执行配置文件里的:<aop:aspectj-autoproxy/>时
    去扫描切面类后,执行到这一步时,因为写错了,所以找不到目标对象,当找不到目标对象后就没有代理
    没有代理就不会有代理对象,最后只执行目标对象中的方法,代理对象中的切面功能没能加上
    但也不报错
     */
}
