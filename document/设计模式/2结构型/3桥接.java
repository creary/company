/*桥接模式
*
* 在多对多的关系中实现抽象与实现分离，这样就不必穷举所有组合类型（对象爆炸）；
*/

//使用A、B两种模板打印本科生、硕士生的学位证

public abstract class IStudent
{
	protected PrintWithTemplateImpl templateImpl;

	public void setTemplateImpl(PrintWithTemplateImpl templateImpl)
	{
		this.templateImpl = templateImpl;
	}

	public void printStudent(String student)
	{
		templateImpl.printTemplate(student);
	}
}

//本科生
public class Bachelor extends IStudent
{
	public void printStudent(String student)
	{
		super.templateImpl.printTemplate(student);
	}
}

//硕士生
public class Master extends IStudent
{
	public void printStudent(String student)
	{
		super.templateImpl.printTemplate(student);
	}
}

/*————————————————————————————————————————————*/

public abstract class PrintTemplateImpl
{
	public void printTemplate(String msg);
}

public class PrintWithTemplateA extends PrintTemplateImpl
{
	@Override
	public void printTemplate(String msg)
	{
		System.out.println("使用样板A打印" + msg + "学位证");
	}
}

public class PrintWithTemplateB extends PrintTemplateImpl
{
	@Override
	public void printTemplate(String msg)
	{
		System.out.println("使用样板B打印" + msg + "学位证");
	}
}

/*————————————————————————————————————————————*/

public class Client 
{  
	public static void main(String[] args) 
	{  
		//用样板A打印本科生学位证
		IStudent a = new Bachelor();
		a.setTemplateImpl(new PrintWithTemplateA());
		a.printStudent("本科生");
    }  
}  
