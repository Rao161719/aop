package cn.rqf;

import cn.rqf.ba04.Testt;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test04 {
    @Test
    public void test01(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        //从容器中获取目标对象
        /*SomeService proxy = (SomeService) ctx.getBean("someService");
        proxy.doOther("张三",12);*/

        /*Testt t = (Testt)ctx.getBean("myTest");
        t.myTest();*///不实现某个接口的类也可以代理，这个时候就不是JDK动态代理了
    }
}
