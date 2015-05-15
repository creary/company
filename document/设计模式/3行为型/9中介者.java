/*中介者模式
*
* 用一个中介对象来封装一系列的对象之间的交互（多个P2P的对象->多个对象对一个中介，将网状结构变为星状结构）；
*/

//有两个类A和B，类中各有一个数字，并且要保证类B中的数字永远是类A中数字的100倍。
//也就是说，当修改类A的数时，将这个数字乘以100赋给类B，而修改类B时，要将数除以100赋给类A。
//类A类B互相影响，就称为同事类。

//抽象同事
abstract class AbstractColleague 
{  
	protected int number;  
   
    public int getNumber() 
	{  
        return number;  
    }  
   
    public void setNumber(int number)
	{  
        this.number = number;  
    }  

	//未使用中介者的抽象方法，修改数字时同时修改关联对象  
	//public abstract void setNumber(int number, AbstractColleague coll); 

    //注意这里的参数不再是同事类，而是一个中介者  
    public abstract void setNumber(int number, AbstractMediator am);  
}  
   
public class ColleagueA extends AbstractColleague
{   
	public void setNumber(int number, AbstractMediator am) 
	{  
        this.number = number;  
        am.AaffectB();  
    }  
}  
   
public class ColleagueB extends AbstractColleague{  
   
    @Override  
    public void setNumber(int number, AbstractMediator am) 
	{  
        this.number = number;  
        am.BaffectA();  
    }  
}  

/*————————————————————————————————————————————*/

//抽象中介者
abstract class AbstractMediator 
{  
	protected AbstractColleague A;  
    protected AbstractColleague B;  
      
    public AbstractMediator(AbstractColleague a, AbstractColleague b) 
	{  
        A = a;  
        B = b;  
    }  
  
    public abstract void AaffectB();  
      
    public abstract void BaffectA();  
  
}  

public class Mediator extends AbstractMediator 
{    
    public Mediator(AbstractColleague a, AbstractColleague b) 
	{  
        super(a, b);  
    }  
  
    //处理A对B的影响  
    public void AaffectB() 
	{  
        int number = A.getNumber();  
        B.setNumber(number*100);  
	}  
 
    //处理B对A的影响  
    public void BaffectA() 
	{  
		int number = B.getNumber();  
        A.setNumber(number/100);  
    }  
}  
  
/*————————————————————————————————————————————*/

public class Client 
{  
    public static void main(String[] args)
	{  
        AbstractColleague collA = new ColleagueA();  
        AbstractColleague collB = new ColleagueB();  
          
        AbstractMediator am = new Mediator(collA, collB);  
          
        System.out.println("==========通过设置A影响B==========");  
        collA.setNumber(1000, am);  
        System.out.println("collA的number值为："+collA.getNumber());  
        System.out.println("collB的number值为A的10倍："+collB.getNumber());  
  
        System.out.println("==========通过设置B影响A==========");  
        collB.setNumber(1000, am);  
        System.out.println("collB的number值为："+collB.getNumber());  
        System.out.println("collA的number值为B的0.1倍："+collA.getNumber());  
          
    }  
}  