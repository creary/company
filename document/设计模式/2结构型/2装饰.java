/*装饰模式
*
* 动态地且透明地增加或减少某些对象的职能；
*/

 
//抽象接口，规范准备接收附加责任的对象 
public interface Component 
{  
    public void operation();  
}

//接收附加责任, 此类型的类可以有多个, 只对应一个Decorator类 
public class ConcreteComponent implements Component 
{  
	public ConcreteComponent()
	{
	}  

    public void operation()  
    {  
		System.out.println("能开车");  
    }  
}

//装饰角色，持有一个构件（Component）对象的实例，并定义一个与抽象构件接口一致的接口
public class Decorator implements Component 
{  
	private Component component;  

    public Decorator()
	{
	}  
  
    public Decorator(Component component)  
    {  
		this.component = component;  
    }  
  
    public void operation() 
	{  
		component.operation();  
    }  
}  

//添加附加责任
public class ConcreteDecorator extends Decorator 
{  
	public ConcreteDecorator()
	{
	}  
  
    public ConcreteDecorator(Component component)  
    {  
         super(component);  
    }  
  
    public void operation()  
    {  
         this.addedOperation();  
         super.operation();  
    }  
  
    public void addedOperation()  
    {  
         System.out.println("在晚上");  
    }  
}  

/*————————————————————————————————————————————*/

public class Client 
{  
	public static void main(String[] args) 
	{  
		Component component = new ConcreteComponent();  
		Decorator decorator = new ConcreteDecorator(component);  

		//客户端不变, 但已增加了责任  
		decorator.operation();                 
    }  
}  
