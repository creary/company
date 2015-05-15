/*简单工厂模式
*
* 通过引入一个专门负责创建具体对象的工厂类，使得客户端不需要了解具体类的有关细节即可生成该类的实例;
* 工厂内部根据不同的条件决定生产什么对象;
* 不符合"开放-封闭"原则：新加入产品类后需要修改工厂代码;
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

//简单工厂类，工厂方法会根据传入的参数返回相应的解码器对象
public class DecodeFactory
{
	public static Decoder createDecoder(String fileName)
	{
		//获取文件扩展名
		String fileNameExt = ...getExtension(fileName);
		switch(fileNameExt)
		{
			case ".mp3":
				return new Mp3Decoder();
			case ".wma":
				return new WmaDecoder();
			default:
				return null;
		}
	}
}

/*————————————————————————————————————————*/

//客户端
public class Client
{
	public static void main(String[ ] arg)
	{
		String fileName = "花海.mp3";

		//静态方法
		Decoder decoder = DecoderFactory.createDecoder(fileName);
		decoder.decode(fileName);
	}
}