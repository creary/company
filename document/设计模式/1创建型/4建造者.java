/*建造者模式
*
* 产品的内部结构的生成由指导者完成；
* 产品中各个部件相互依赖，指导者可以强迫生成顺序；
* 如果有变动，不再需要更改客户端的代码，只需要修改指导者内部的代码；
*/

public interface Product { }
public interface Part { }

/*————————————————————————————————————————*/

//抽象建造者
public interface Builder 
{ 
	void buildPartA(); 
	void buildPartB();  
　　
	Product getResult(); 
} 

//具体建造工具
public class ConcreteBuilder implements Builder 
{ 
　　Part partA, partB; 

　　public void buildPartA() 
	{
　　　　//这里是具体如何构建partA的代码
　　}; 

　　public void buildPartB() 
	{ 
　　　　//这里是具体如何构建partB的代码
　　}; 

　　public Product getResult() 
	{ 
　　　　//返回最后组装成品结果
　　}; 
}

/*————————————————————————————————————————*/

//建造者
public class Director 
{
　　private Builder builder; 
　　
　　public Director( Builder builder ) 
	{ 
　　　　this.builder = builder; 
　　} 

　　public Object construct() 
	{ 
　　　　builder.buildPartA();
　　　　builder.buildPartB();

		return builder.getResult();
　　} 
} 

/*————————————————————————————————————————*/

//客户端
public class Client
{
	public static void main(String[ ] arg)
	{
		//builder内部结构对于客户端来所完全未知
　　	Director director = new Director(new ConcreteBuilder()); 
　　
　　	Product product = director.construct(); 
	}
}