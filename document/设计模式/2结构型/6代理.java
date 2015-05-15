/*代理模式
*
* 保护代理（使用java提供的proxy设置动态权限）：
* http://blog.csdn.net/shuangde800/article/details/10381495；
*/

//抽象对象角色
public abstract class AbstractObject 
{
    //操作
    public abstract void operation();
}

//目标对象角色
public class RealObject extends AbstractObject 
{
    @Override
    public void operation() 
	{
        //一些操作
        System.out.println("一些操作");
    }
}

//代理对象角色
public class ProxyObject extends AbstractObject
{
    RealObject realObject = new RealObject();

    @Override
    public void operation() 
	{
        //调用目标对象之前可以做相关操作
        System.out.println("before"); 
		
        realObject.operation(); 
		
        //调用目标对象之后可以做相关操作
        System.out.println("after");
    }
}

/*————————————————————————————————————————————*/

public class Client 
{
    public static void main(String[] args) 
	{
        AbstractObject obj = new ProxyObject();
        obj.operation();
    }
}
