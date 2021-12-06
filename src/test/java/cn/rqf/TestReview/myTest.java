package cn.rqf.TestReview;

import cn.review.NotImpl;
import cn.review.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
  总结：1.实现接口时：采用JDK代理
       2.没有实现接口：采用cglib代理
       3.实现接口任想用JDK代理：<aop:aspectj-autoproxy proxy-target-class="true"/>
       注意：当没有实现接口时：proxy-target-class 为true或false都是采用cglib代理
            当实现接口时：proxy-target-class 为true是cglib代理
                       proxy-target-class 为false是JDK代理
 */
public class myTest {
    @Test
    public void test01(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("myApplicationContext.xml");
        /*JDK代理*/
        SomeService proxy = (SomeService) ctx.getBean("doSome");
        System.out.println("proxy代理："+proxy.getClass().getName());
        /*cglib代理*/
        NotImpl not = (NotImpl) ctx.getBean("noImpl");
        /*接口实现时，采用JDK代理*/
//        proxy.doSome();
//        proxy.doOther("张三");
//        proxy.doView("李四");
//        proxy.doAround("王五");
//        System.out.println(proxy.doAround2("张三",18));
//        proxy.doSecond();

        /*没有接口实现时，采用的是cglib代理*/
        System.out.println(not.getClass().getName());
        not.noImpl();

        /*实现接口，任然想用cglib代理
        * 需要在xml文件里：<aop:aspectj-autoproxy proxy-target-class="true"/>
        *proxy-target-class：为true时采用cglib
        *                    为false时采用JDK */
//        SomeService proxy2 = (SomeService) ctx.getBean("doSome");
//        System.out.println(proxy2.getClass().getName());
//        proxy2.doGood();

    }
}
