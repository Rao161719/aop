package cn.rqf;

import cn.rqf.ba02.SomeService;
import cn.rqf.ba02.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test02 {
    @Test
    public void test01(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        //从容器中获取目标对象
        SomeService proxy = (SomeService) ctx.getBean("someService");
        /*//通过代理的对象执行方法,实现目标方法执行时,增强了功能
        String str = proxy.doOther("zs",28);
        System.out.println("str==="+str);
        System.out.println("=================");
         Student s = proxy.doOther2();
        System.out.println("主方法:"+s);*/
//        proxy.doOther("a",1);
//        System.out.println(proxy.doOther2("张三",10));
//        System.out.println(proxy.doOther3("掌声"));
    }
}
