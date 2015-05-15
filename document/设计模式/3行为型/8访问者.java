/*访问者模式
*
* 对已有的类添加新的操作变得容易（不同的访问者对被访问者有不同的操作）；
*/

//抽象访问者，对于传入的不同的参数采取不同的访问方式（静态分派，多态）
public interface Visitor 
{
    //对应于NodeA的访问操作
    public void visit(NodeA node);

    //对应于NodeB的访问操作
    public void visit(NodeB node);
}

//具体访问者VisitorA类
public class VisitorA implements Visitor 
{
    //对应于NodeA的访问操作
    @Override
    public void visit(NodeA node) 
	{
        System.out.println(node.operationA());
    }

    //对应于NodeB的访问操作
    @Override
    public void visit(NodeB node) 
	{
        System.out.println(node.operationB());
    }

}

//具体访问者VisitorB类
public class VisitorB implements Visitor 
{
    //对应于NodeA的访问操作
    @Override
    public void visit(NodeA node) 
	{
        System.out.println(node.operationA());
    }

    //对应于NodeB的访问操作
    @Override
    public void visit(NodeB node) 
	{
        System.out.println(node.operationB());
    }

}

/*————————————————————————————————————————————*/

//抽象节点类
public abstract class Node 
{
    //接受操作
    public abstract void accept(Visitor visitor);
}

/*双重委派：//被访问者将自己完全交给了访问者，要怎么处理自己决定于访问者；
* public void accept(Visitor visitor) //接受一个访问者
* {
*	visitor.visit(this); //将自己委派给访问者
* }
*/

//具体节点类NodeA
public class NodeA extends Node
{
    //接受操作
    @Override
    public void accept(Visitor visitor) 
	{
        visitor.visit(this);
    }

    //NodeA特有的方法
    public String operationA()
	{
        return "NodeA";
    }

}

//具体节点类NodeB
public class NodeB extends Node
{
    //接受方法
    @Override
    public void accept(Visitor visitor) 
	{
        visitor.visit(this);
    }

    // NodeB特有的方法
    public String operationB()
	{
        return "NodeB";
    }
}

/*————————————————————————————————————————————*/

//结构对象角色类，这个结构对象角色持有一个聚集，并向外界提供add()方法作为对聚集的管理操作。
//通过调用这个方法，可以动态地增加一个新的节点。
public class ObjectStructure 
{ 
    private List<Node> nodes = new ArrayList<Node>();
    
    //执行方法操作
    public void action(Visitor visitor)
	{   
        for(Node node : nodes)
        {
            node.accept(visitor);
        }
        
    }

    //添加一个新元素
    public void add(Node node)
	{
        nodes.add(node);
    }
}

/*————————————————————————————————————————————*/

//客户端类
public class Client 
{
    public static void main(String[] args) 
	{
        //创建一个结构对象
        ObjectStructure os = new ObjectStructure();

        //给结构增加一个节点
        os.add(new NodeA());
        //给结构增加一个节点
        os.add(new NodeB());

        //创建一个访问者
        Visitor visitor = new VisitorA();

        os.action(visitor);
    }

}

