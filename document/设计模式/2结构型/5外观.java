/*外观模式
*
* 所谓外观模式就是要求一个子系统的外部与其内部的通信必须通过一个统一的对象进行交互。
* 外观模式提供一个高层次的接口使得子系统更易于使用；
*/

//subSystem
public class Camara 
{  
	public void turnOn()  
    {  
		System.out.println("开启摄像头！");  
    }  
     
    public void turnOff()  
    {  
        System.out.println("关闭摄像头！");  
    }  
}  

//subSystem
public class Light 
{  
	public void turnOn()  
    {  
        System.out.println("开灯！");  
    }  
     
    public void turnOff()  
    {  
        System.out.println("关灯！");  
    }  
}  
  
//subSystem
public class Sensor 
{  
	public void activate()  
    {  
		System.out.println("开启感应器！");  
    }  
     
    public void deactivate()  
    {  
        System.out.println("关闭感应器！");  
    }  
}  

/*————————————————————————————————————————————*/

//外观类，可以实现一个interface
public class MyFacade 
{  
    private static Camara c1, c2;  
    private static Light l1, l2, l3;  
    private static Sensor s;  
      
	public MyFacade()
	{
		c1 = new Camara();  
        c2 = new Camara();  
        l1 = new Light();  
        l2 = new Light();  
        l3 = new Light();  
        s = new Sensor();
	}  
    
	//外观向客户端提供的统一接口
    public void activate()  
    {  
        c1.turnOn();  
        c2.turnOn();  
          
        l1.turnOn();  
        l2.turnOn();  
        l3.turnOn();  
          
        s.activate();  
    }  
      
	//外观向客户端提供的统一接口
    public void deactivate()  
    {  
        c1.turnOff();  
        c2.turnOff();  
          
        l1.turnOff();  
        l2.turnOff();  
        l3.turnOff();  
          
        s.deactivate();  
    } 
}  

/*————————————————————————————————————————————*/

public class Client 
{
    public static void main(String[] args) 
	{
        MyFacade facade = new MyFacade();

		//客户端调用一个操作代替了一系列操作
		facade.activate();

		facade.deactivate();
    }
}

