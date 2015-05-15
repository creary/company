/*模板方法模式
*
* 这种模式关注这样一类行为：这类行为在执行时拥有大致相同的动作序列，只是具体的动作在实现细节上有所差异；
*/

//抽象模板
public abstract class AbstractClass
{
    protected abstract void doAnyting();

    protected abstract void doSomething();

    public void templateMethod()
    {
        doAnyting();
        doSomething();
    }
}

public class ConcreteClass1 extends AbstractClass
{
    @Override
    protected void doAnyting()
    {
        System.out.println("do class1 anything");
    }

    @Override
    protected void doSomething()
    {
        System.out.println("do class1 something");
    }
}

public class ConcreteClass2 extends AbstractClass
{
    @Override
    protected void doAnyting()
    {
        System.out.println("do class2 anything");
    }

    @Override
    protected void doSomething()
    {
        System.out.println("do class2 something");
    }
}

/*————————————————————————————————————————————*/

public class Client
{
    public static void main(final String[] args)
    {
        final AbstractClass c1 = new ConcreteClass1();
        final AbstractClass c2 = new ConcreteClass2();
        c1.templateMethod();
        c2.templateMethod();
    }
}
