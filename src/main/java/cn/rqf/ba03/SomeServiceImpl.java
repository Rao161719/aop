package cn.rqf.ba03;

public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome(String name,Integer age) {
        //给doSome方法增加一个功能,在doSome()执行之前
        System.out.println("=====目标方法doSome()=====");
    }

    @Override
    public void doOther(String name, Integer age) {
        System.out.println("===========目标方法doOther==========");
    }

    @Override
    public String doFirst(String name, Integer age) {
        System.out.println("=======目标方法doFirst======");
        return name;
    }

    @Override
    public Student doFirst2(String name, Integer age) {
        System.out.println("=======目标方法doFirst======");
        Student student = new Student(name,age);
        student.setName("张三");
        student.setAge(18);
        return student;
    }
    @Override
    public int doFirst3(int age) {
        System.out.println("=======目标方法doFirst======");
        return age;
    }

}
