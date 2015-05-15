/*备忘录模式
*
* 恢复到之前保存过的状态；
*/

/**白箱备忘录*/

//发起人角色类，发起人角色利用一个新创建的备忘录对象将自己的内部状态存储起来。
public class Originator 
{
    private String state;

    //工厂方法，返回一个新的备忘录对象
    public Memento createMemento()
	{
        return new Memento(state);
    }

    //将发起人恢复到备忘录对象所记载的状态  
    public void restoreMemento(Memento memento)
	{
        this.state = memento.getState();
    }
    
    public String getState() 
	{
        return state;
    }
    
    public void setState(String state) 
	{
        this.state = state;
        System.out.println("当前状态：" + this.state);
    }
    
}

//备忘录角色类，备忘录对象将发起人对象传入的状态存储起来。
public class Memento 
{    
    private String state;
    
    public Memento(String state)
	{
        this.state = state;
    }

    public String getState() 
	{
        return state;
    }

    public void setState(String state) 
	{
        this.state = state;
    }
    
}

//保管员角色类，负责保存备忘录对象，但是从不修改（甚至不查看）备忘录对象的内容。
public class Caretaker 
{
    private Memento memento;

    //备忘录的取值方法
    public Memento retrieveMemento()
	{
        return this.memento;
    }

    //备忘录的赋值方法
    public void saveMemento(Memento memento)
	{
        this.memento = memento;
    }
}

/*————————————————————————————————————————————*/

public class Client 
{
    public static void main(String[] args) 
	{      
        Originator o = new Originator();
        Caretaker c = new Caretaker();

        //改变负责人对象的状态
        o.setState("On");

        //创建备忘录对象，并将发起人对象的状态储存起来
        c.saveMemento(o.createMemento());

        //修改发起人的状态
        o.setState("Off");

        //恢复发起人对象的状态
        o.restoreMemento(c.retrieveMemento());
        
        System.out.println(o.getState());
    }

}