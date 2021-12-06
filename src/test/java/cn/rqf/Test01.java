package cn.rqf;

import cn.rqf.ba02.SomeService;
import cn.rqf.ba02.test;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
    @Test
    public void test01(){
        String config = "applicationContext.xml";
        //从容器中获取目标对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        SomeService proxy = (SomeService) ctx.getBean("someService");
        //com.sun.proxy.$Proxy6:jdk动态代理
        System.out.println("proxy:"+proxy.getClass().getName());
        //通过代理的对象执行方法,实现目标方法时,增强的功能
        proxy.doSome("lisi",20);

        test t =(test) ctx.getBean("test");
        t.test();
    }
}
