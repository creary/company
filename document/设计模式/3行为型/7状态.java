/*状态模式
*
* 用一句话来表述，状态模式把所研究的对象的行为包装在不同的状态对象里，
* 每一个状态对象都属于一个抽象状态类的一个子类。
* 状态模式的意图是让一个对象在其内部状态改变的时候，其行为也随之改变。
*/

//环境角色类
public class Context 
{
    //持有一个State类型的对象实例
    private State state;

    public void setState(State state) 
	{
        this.state = state;
    }

    //用户感兴趣的接口方法
    public void request(String sampleParameter) 
	{
        //转调state来处理
        state.handle(sampleParameter);
    }
}

/*————————————————————————————————————————————*/

//抽象状态类
public interface State 
{
    //状态对应的处理
    public void handle(String sampleParameter);
}

//具体状态类
public class ConcreteStateA implements State 
{
    @Override
    public void handle(String sampleParameter) 
	{ 
        System.out.println("ConcreteStateA handle ：" + sampleParameter);
    }

}

public class ConcreteStateB implements State 
{
    @Override
    public void handle(String sampleParameter) 
	{      
        System.out.println("ConcreteStateB handle ：" + sampleParameter);
    }

}

/*————————————————————————————————————————————*/

public class Client 
{
    public static void main(String[] args)
	{
        //创建状态
        State state = new ConcreteStateB();

        //创建环境
        Context context = new Context();

        //将状态设置到环境中
        context.setState(state);

        //请求
        context.request("test");
    }
}