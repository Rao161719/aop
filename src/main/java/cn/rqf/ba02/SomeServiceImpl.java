package cn.rqf.ba02;

public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome(String name, Integer age) {
        //给doSome方法增加一个功能,在doSome()执行之前
        System.out.println("=====目标方法doSome()=====");
    }

    @Override
    public String doOther(String name, Integer age) {
        System.out.println("=====目标方法doOther()=======");
        return name;
    }
    @Override
    public Student doOther2(String s,int i) {
        System.out.println("=====目标方法doOther()=======");
        Student student = new Student();
        student.setName(s);
        student.setAge(i);
        return student;
    }
    @Override
    public String doOther3(String s) {
        System.out.println("=====目标方法doOther()=======");
        return s;
    }
    public void Test(){


    }
}
