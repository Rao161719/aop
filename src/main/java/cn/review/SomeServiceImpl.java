package cn.review;

public class SomeServiceImpl implements SomeService{
    @Override
    public void doSome() {
        System.out.println("目标doSome方法");
    }

    @Override
    public String doOther(String s) {
        System.out.println("目标doOther方法");
        return s;
    }

    @Override
    public String doView(String s) {
        System.out.println("目标doView方法");
        return s;
    }

    @Override
    public String doAround(String s) {
        System.out.println("目标doAround方法");
        return s;
    }

    @Override
    public Student doAround2(String s, Integer i) {
        //先赋值
        Student s2 = new Student(s,i);
        //进行第一次修改
        s2.setName("原名字");
        s2.setAge(1);
        return s2;
    }

    @Override
    public void doSecond() {
        System.out.println("目标doSecond方法"+10/0);
    }

    @Override
    public void doGood() {
        System.out.println("===目标doGood方法====");
    }
}
