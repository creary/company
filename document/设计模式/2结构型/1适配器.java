/*适配器模式
*
* 例如，给出计算三角形面积的计算方法，要求计算四边形的面积；
*/

/*类适配器模式*/

// 已存在的、具有特殊功能、但不符合我们既有的标准接口的类   
public class Adaptee 
{  
    public void specificRequest() 
	{  
        System.out.println("被适配类具有 特殊功能...");  
    }  
}  
  
/*————————————————————————————————————————————*/

// 目标接口，或称为标准接口   
public interface Target 
{  
    public void request();  
}  
  
// 具体目标类，只提供普通功能   
class ConcreteTarget implements Target 
{  
    public void request() 
	{  
        System.out.println("普通类 具有 普通功能...");  
    }  
}   
  
// 适配器类，继承了被适配类，同时实现标准接口   
class Adapter extends Adaptee implements Target
{  
    public void request() 
	{  
        super.specificRequest();  
    }  
}   
  
/*————————————————————————————————————————————*/
  
// 测试类   
public class Client 
{  
    public static void main(String[] args) 
	{  
        // 使用普通功能类   
        Target concreteTarget = new ConcreteTarget();  
        concreteTarget.request();  
          
        // 使用特殊功能类，即适配类   
        Target adapter = new Adapter();  
        adapter.request();  
    }  
}  

/**——————————————————————————————————————————————————————————————————————————————————*/

/*对象适配器*/

// 适配器类，直接关联被适配类，同时实现标准接口   
public class Adapter implements Target
{  
    // 直接关联被适配类   
    private Adaptee adaptee;  
     
    // 可以通过构造函数传入具体需要适配的被适配类对象   
    public Adapter (Adaptee adaptee) 
    {  
       this.adaptee = adaptee;  
    }  
      
    public void request() 
	{  
        // 这里是使用委托的方式完成特殊功能   
        this.adaptee.specificRequest();  
    }  
}  
  
/*————————————————————————————————————————————*/
  
// 测试类   
public class Client 
{  
    public static void main(String[] args) 
	{  
        // 使用普通功能类   
        Target concreteTarget = new ConcreteTarget();  
        concreteTarget.request();  
          
        // 使用特殊功能类，即适配类，   
        // 需要先创建一个被适配类的对象作为参数   
        Target adapter = new Adapter(new Adaptee());  
        adapter.request();  
    }  
}  
