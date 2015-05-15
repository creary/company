/*抽象工厂模式
*
* 一个工厂同时创建一系列类对象（音频和字幕）；
* 只是比工厂方法多封装了一些方法；
*/

//抽象音频类
public abstract class Audio
{
	public abstract void playback();
}  

//具体音频类
public class MandarinAudio extends Audio
{
	//@ override
	public void playback(){};
}

public class CantoneseAudio extends Audio
{
	//@ override
	public void playback(){};
}

/*————————————————————————————————————————*/

//抽象字幕类
public abstract class Subtitle
{
	public abstract void show();
}  

//具体字幕类
public class MandarinSubtitle extends Subtitle
{
	//@ override
	public void show(){};
}

public class CantoneseSubtitle extends Subtitle
{
	//@ override
	public void show(){};
}

/*————————————————————————————————————————*/

//抽象工厂类
public abstract class Factory
{
	public abstract Audio createAudio();
	public abstract Subtitle createSubtitle();
}

//国语工厂类
public class MandarinFactory extends Factory
{
	//@ override
	public Audio createAudio()
	{
		return new MandarinAudio();
	}

	//@ override
	public Subtitle createSubtitle()
	{
		return new MandarinSubtitle();
	}
}

//粤语工厂类
public class CantoneseFactory extends Factory
{
	//@ override
	public Audio createAudio()
	{
		return new CantoneseAudio();
	}

	//@ override
	public Subtitle createSubtitle()
	{
		return new CantoneseSubtitle();
	}
}

/*————————————————————————————————————————*/

//客户端
public class Client
{
	public static void main(String[ ] arg)
	{
		//选择了工厂就是选择了产品
		Factory factory = new MandarinFactory();

		//音频对象
		Audio audio = factory.createAudio();
		//字幕对象
		Subtitle subtitle = factory.createSubtitle();

		audio.playback();
		subtitle.show();
	}
}