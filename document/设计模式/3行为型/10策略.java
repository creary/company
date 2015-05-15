/*策略模式
*
* 不同算法可以相互替代；
* 好比商店对于不同的会员有不同的折扣安排；
*/

//一个算法接口  
public interface Calculate 
{  
    public int calculate(int a, int b);  
}  
  
//一个加法策略类，实现算法接口  
public class AddCalculate implements Calculate  
{  
    public int calculate(int a, int b)  
    {  
        return a + b;  
    }  
}  

//一个除法策略类，实现算法接口  
public class DivideCalculate implements Calculate  
{  
    public int calculate(int a, int b)  
    {  
        return a / b;  
    }  
}  

/*————————————————————————————————————————————*/

//一个环境角色类，持有对接口的引用  
public class Environment  
{  
    private Calculate cal;  
       
    public Environment(Calculate cal)  
    {  
        this.cal = cal;  
    }  
   
    public Calculate getCalculate()  
    {  
        return cal;  
    }  
 
    public void setCalculate(Calculate cal)  
    {  
        this.cal = cal;  
    }  

	//环境角色中的接口方法，其具体结果依赖于策略引用指向的具体策略角色对象  
    public int calculate(int a ,int b)  
    {  
        return cal.calculate(a , b);  
    }  
}  

/*————————————————————————————————————————————*/
  
public class Client  
{  
    public static void main(String[] args)  
    {  
        AddCalculate add = new AddCalculate();  
        Environment environment = new Environment(add);  
        System.out.println(environment.calculate(3, 4));  
  
        DivideCalculate divide = new DivideCalculate();   
        Environment environment = new Environment(divide);   
        System.out.println(environment.calculate(3, 4));        
    }  
} 
