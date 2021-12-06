package cn.review;

public interface SomeService {
    //普通：Before After(最终通知)
    void doSome();
    //前置通知
    String doOther(String s);
    //后置通知
    String doView(String s);
    //环绕通知
    String doAround(String s);
    Student doAround2(String s,Integer i);
    //异常通知
    void doSecond();
    void doGood();
}
