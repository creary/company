/*享元模式
*
* 享元模式采用一个共享来避免大量拥有相同内容对象的开销；
* 享元对象能做到共享的关键是区分内蕴状态(Internal State)和外蕴状态(External State)。
*     一个内蕴状态是存储在享元对象内部的，并且是不会随环境的改变而有所不同。
* 因此，一个享元可以具有内蕴状态并可以共享。
*　　 一个外蕴状态是随环境的改变而改变的、不可以共享的。
* 享元对象的外蕴状态必须由客户端保存，并在享元对象被创建之后，在需要使用的时候再传入到享元对象内部。
* 外蕴状态不可以影响享元对象的内蕴状态，它们是相互独立的。
*/

//抽象享元角色类
public interface Flyweight 
{
    //一个示意性方法，参数state是外蕴状态
    public void operation(String state);
}

public class ConcreteFlyweight implements Flyweight 
{
    private Character intrinsicState = null;

    //构造函数，内蕴状态作为参数传入
    public ConcreteFlyweight(Character state)
	{
        this.intrinsicState = state;
    }
    
    
    //外蕴状态作为参数传入方法中，改变方法的行为，但是并不改变对象的内蕴状态。
    @Override
    public void operation(String state)
	{
        System.out.println("Intrinsic State = " + this.intrinsicState);
        System.out.println("Extrinsic State = " + state);
    }

}

/*————————————————————————————————————————————*/

//享元工厂角色类，必须指出的是，客户端不可以直接将具体享元类实例化，而必须通过一个工厂对象，利用一个factory()方法得到享元对象。
//一般而言，享元工厂对象在整个系统中只有一个，因此也可以使用单例模式。
public class FlyweightFactory 
{
    private Map<Character,Flyweight> files = new HashMap<Character,Flyweight>();
    
    public Flyweight factory(Character state)
	{
        //先从缓存中查找对象
        Flyweight fly = files.get(state);

        if(fly == null)
		{
            //如果对象不存在则创建一个新的Flyweight对象
            fly = new ConcreteFlyweight(state);

            //把这个新的Flyweight对象添加到缓存中
            files.put(state, fly);
        }
        return fly;
    }
}

/*————————————————————————————————————————————*/

public class Client {

    public static void main(String[] args) 
	{
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fly = factory.factory(new Character('a'));
        fly.operation("First Call");
        
        fly = factory.factory(new Character('b'));
        fly.operation("Second Call");
        
        fly = factory.factory(new Character('a'));
        fly.operation("Third Call");
    }

} 