/*观察者模式
*
* 观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。
* 这个主题对象在状态上发生变化时，会通知所有观察者对象，使它们能够自动更新自己；
*/

/** “推”模式 */

//抽象主题角色类
public abstract class Subject 
{
    //用来保存注册的观察者对象
    private List<Observer> list = new ArrayList<Observer>();

    //注册观察者对象
    public void attach(Observer observer)
	{    
        list.add(observer);
        System.out.println("Attached an observer");
    }

    //删除观察者对象
    public void detach(Observer observer)
	{    
        list.remove(observer);
    }

    //通知所有注册的观察者对象
    public void nodifyObservers(String newState)
	{     
        for(Observer observer : list)
		{
            observer.update(newState);
        }
    }
}

//具体主题角色类
public class ConcreteSubject extends Subject
{   
    private String state;
    
    public String getState() 
	{
        return state;
    }

    public void change(String newState)
	{
        state = newState;

        System.out.println("主题状态为：" + state);

        //状态发生改变，通知各个观察者
        this.nodifyObservers(state);
    }
}

/*————————————————————————————————————————————*/

//抽象观察者角色类
public interface Observer 
{
    //更新接口
    public void update(String state);
}

//具体观察者角色类
public class ConcreteObserver implements Observer 
{
    //观察者的状态
    private String observerState;
    
    @Override
    public void update(String state) 
	{
        // 更新观察者的状态，使其与目标的状态保持一致
        observerState = state;
        System.out.println("状态为："+observerState);
    }

}

/*————————————————————————————————————————————*/

public class Client 
{
    public static void main(String[] args) 
	{
        //创建主题对象
        ConcreteSubject subject = new ConcreteSubject();
        //创建观察者对象
        Observer observer = new ConcreteObserver();

        //将观察者对象登记到主题对象上
        subject.attach(observer);
        //改变主题对象的状态
        subject.change("new state");
    }

}

/*——————————————————————————————————————————————————————————————————————————————————————*/

/** “拉”模式 */

//拉模型的抽象观察者类
//拉模型通常都是把主题对象当做参数传递。
public interface Observer 
{
    //更新接口，传入主题对象，方便获取相应的主题对象的状态  
    public void update(Subject subject);
}

//拉模型的具体观察者类
public class ConcreteObserver implements Observer 
{
    //观察者的状态
    private String observerState;
    
    @Override
    public void update(Subject subject) 
	{
        //更新观察者的状态，使其与目标的状态保持一致 
        observerState = ((ConcreteSubject)subject).getState();
        System.out.println("观察者状态为："+observerState);
    }

}

/*————————————————————————————————————————————*/

//拉模型的抽象主题类
//拉模型的抽象主题类主要的改变是nodifyObservers()方法。
//在循环通知观察者的时候，也就是循环调用观察者的update()方法的时候，传入的参数不同了。
public abstract class Subject 
{
    //用来保存注册的观察者对象
    private    List<Observer> list = new ArrayList<Observer>();

    //注册观察者对象
    public void attach(Observer observer)
	{     
        list.add(observer);
        System.out.println("Attached an observer");
    }

    //删除观察者对象
    public void detach(Observer observer)
	{ 
        list.remove(observer);
    }

    //通知所有注册的观察者对象
    public void nodifyObservers()
	{       
        for(Observer observer : list){
            observer.update(this);
        }
    }
}

//拉模型的具体主题类
//跟推模型相比，有一点变化，就是调用通知观察者的方法的时候，不需要传入参数了。
public class ConcreteSubject extends Subject
{   
    private String state;
    
    public String getState() 
	{
        return state;
    }

    public void change(String newState)
	{
        state = newState;
        System.out.println("主题状态为：" + state);

        //状态发生改变，通知各个观察者
        this.nodifyObservers();
    }
}