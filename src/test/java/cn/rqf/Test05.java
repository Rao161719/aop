package cn.rqf;

import cn.rqf.ba05.SomeServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test05 {
    @Test
    public void test01(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        SomeServiceImpl proxy = (SomeServiceImpl) ctx.getBean("someService");
        /*
        目标类没有接口，使用了cglib动态代理，spring框架会自动应用cglib
         */
        System.out.println("proxy.getClass.getName:"+proxy.getClass().getName());
        proxy.doOther("1",1);
    }
}
