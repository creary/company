/*单件模式
*
* 保证一个类仅有一个实例；
*/

public class Singleton
{ 
　　private static Singleton sqlInstance;

	//私有的构造函数，不允许外部new
	private Singleton();

	//静态方法调用静态实例，注意线程安全性
	public static synchronized Singleton getInstance()
	{
		//检测实例是否存在
		if(sqlInstance == null)
		{
			sqlInstance = new Singleton();
		}
		else
		{
			System.out.println("该实例已经存在，返回已存在实例");
		}

		return sqlInstance;
	}
}

/*————————————————————————————————————————*/

//客户端
public class Client
{
	public static void main(String[ ] arg)
	{
		//使用静态方法返回实例
		Singleton link = Singleton.getInstance(); 
	}
} 

