/*原型模式
*
* 为大量相同或相似实例的创建提供克隆已存在实例的方法；
*/

//实现Cloneable接口。
//在java语言有一个Cloneable接口，它的作用只有一个，
//就是在运行时通知虚拟机可以安全地在实现了此接口的类上使用clone方法。
//在java虚拟机中，只有实现了这个接口的类才可以被拷贝，
//否则在运行时会抛出CloneNotSupportedException异常。
class Prototype implements Cloneable 
{  
    public Prototype clone()
	{   
        Prototype prototype = null;  

        try
		{  
            prototype = (Prototype)super.clone();  
        }
		catch(CloneNotSupportedException e)
		{  
            e.printStackTrace();  
        }  

        return prototype;   
    }  
}  
  
//具体原型
class ConcretePrototype  extends Prototype
{  
    public void show()
	{  
        System.out.println("原型模式实现类");  
    }  
}  
  
/*————————————————————————————————————————*/

public class Client 
{  
    public static void main(String[] args)
	{  
        ConcretePrototype cp = new ConcretePrototype();
		
        for(int i=0; i< 10; i++)
		{  
            ConcretePrototype clonecp = (ConcretePrototype)cp.clone();  
            clonecp.show();  
        }  
    }  
}  

/**————————————————————————————————————————————————————————————————————————————————*/

//Object类的clone方法只会拷贝对象中的基本的数据类型，
//对于数组、容器对象、引用对象等都不会拷贝，这就是浅拷贝。
//如果要实现深拷贝，必须将原型模式中的数组、容器对象、引用对象等另行拷贝。
public class Prototype implements Cloneable 
{  
    private ArrayList list =  new ArrayList();  

    public Prototype clone(){  
        Prototype prototype = null;  

        try
		{  
            prototype = (Prototype)super.clone();  
			//java对容器类也提供了克隆方法
            prototype.list = (ArrayList)this.list.clone();  
        }
		catch(CloneNotSupportedException e)
		{  
            e.printStackTrace();  
        }  

        return prototype;   
    }  
}  
