package cn.rqf;

import cn.rqf.ba03.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test03 {
    @Test
    public void test01(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        //从容器中获取目标对象
        SomeService proxy = (SomeService) ctx.getBean("someService");

        //通过代理的对象执行方法,实现目标方法执行时,增强了功能
//        String str =  proxy.doFirst("张三",12);
//        System.out.println(str);
//        System.out.println(proxy.doFirst("原名字",11));
        System.out.println(proxy.doFirst3(111));
//        System.out.println(proxy.doFirst2("掌声",18));
    }
}
