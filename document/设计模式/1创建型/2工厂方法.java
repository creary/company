/*工厂方法模式
*
* 多种工厂继承自一个抽象工厂，可扩展性好；
*/

//抽象的解码器类
public abstract class Decoder
{
	public abstract void decode(String fileName);
}  

//具体解码器类
public class Mp3Decoder extends Decoder
{
	//@ override
	public void decode(String fileName){};
}

public class WmaDecoder extends Decoder
{
	//@ override
	public void decode(String filename){};
}

/*————————————————————————————————————————*/

//抽象工厂类
public abstract class Factory
{
	public decoder createrDecoder();
}

//Mp3工厂类
public class Mp3DecodeFactory extends Factory
{
	//@ override
	public Decoder createDecoder()
	{
		return new Mp3Decoder();
	}
}

//Wma工厂类
public class WmaDecodeFactory extends Factory
{
	//@ override
	public Decoder createDecoder()
	{
		return new WmaDecoder();
	}
}

/*————————————————————————————————————————*/

//客户端
public class Client
{
	public static void main(String[ ] arg)
	{
		String fileName = "花海.mp3";

		//选择了工厂就是选择了产品
		Factory factory = new Mp3DecoderFactory();

		Decoder decoder = factory.createDecoder();
		decoder.decode(fileName);
	}
}