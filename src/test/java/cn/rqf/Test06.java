package cn.rqf;

import cn.rqf.ba06.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test06 {
    @Test
    public void test01(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        SomeService proxy = (SomeService) ctx.getBean("someService");
        System.out.println("proxy:"+proxy.getClass().getName());
        proxy.doOther();
    }
}
