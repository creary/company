/*迭代器模式
*
* 遍历集合的职责分离出来；
*/

/**白箱聚合+外迭代子*/

public interface Iterator
{
    public Object first();

    public Object next();

	//得到当前对象
    public Object currentItem();

	//是否到了结尾
    public boolean isDone();
}

//正序迭代器
public class ConcreteIterator implements Iterator
{
    private int currentIndex = 0;

	//定义一个具体集合对象
    private Aggregate aggregate = null;

    public ConcreteIterator(Aggregate aggregate)
    {
        this.aggregate = aggregate;
    }

	//重写父类方法
    @Override
    public Object first()
    {
        currentIndex = 0;
        return vector.get(currentIndex);
    }

    @Override
    public Object next()
    {
		if(currentIndex < aggregate.count())
			currentIndex++;
        return vector.get(currentIndex);
    }

    @Override
    public Object currentItem()
    {
        return aggregate.getAt(currentIndex);
    }

    @Override
    public boolean isDone()
    {
		return (currentIndex >= aggregate.count());
    }
}

/*————————————————————————————————————————————*/

//白箱聚合要求集合类向外界提供访问自己内部元素的接口
public interface Aggregat
{
    public Iterator createIterator();

	//获取集合内部元素总数
	public int count();

	//获取指定位置元素
	public Object getAt(int index);
}

/*————————————————————————————————————————————*/

//具体的集合类
public class ConcreteAggregat implements Aggregat
{
    private Vector vector = null;

    public Vector getVector()
    {
        return vector;
    }

    public void setVector(final Vector vector)
    {
        this.vector = vector;
    }

    public ConcreteAggregat()
    {
        vector = new Vector();
        vector.add("item 1");
        vector.add("item 2");
    }

	//获取集合内部元素总数
	@Override
	public int count()
	{
		return vector.size();
	}

	//获取指定位置元素
	@Override
	public Object getAt(int index)
	{
		if(0 <= index && index < vector.size())
			return vector[index];
		else
			return null;
	}

	//创建一个具体迭代器对象，并把该集合对象自身交给该迭代器
    @Override
    public Iterator createIterator()
    {
		//这里可以使用简单工厂模式
        return new ConcreteIterator(this);
    }
}

/*————————————————————————————————————————————*/

public class Client
{
    public static void main(final String[] args)
    {
        Aggregat agg = new ConcreteAggregat();
        final Iterator iterator = agg.createIterator();

        System.out.println(iterator.first());

        while (!iterator.isDone())
        {
			//Item item = (Item)iterator.currentItem();
            System.out.println(iterator.next());
        }
    }
}


