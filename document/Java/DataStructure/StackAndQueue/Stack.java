package StackAndQueue;

public class Stack {
	private Object [] elementData;
	private int size=0;
	private int capacityIncrement;
	public Stack(int initialCapacity) {
		elementData=new Object[initialCapacity];
	}
	public Stack(int initialCapacity,int capacityIncrement) {
		this(initialCapacity);
		this.capacityIncrement=capacityIncrement;
	}
	public void push(Object object){
		ensureCapacity();
		elementData[size++]=object;
	}
	public Object pop(){
		if(size==0){
			throw new RuntimeException("空栈异常");
		}
//		return elementData[--size]; // 内存泄露
		Object ele=elementData[--size];
		elementData[size]=null;
		return ele;
	}
	
	public int size(){
		return size;
	}
	
	private void ensureCapacity() {
		if(elementData.length==size){
			Object [] oldElements=elementData;
			int newLength=0;
			if(capacityIncrement>0){
				newLength=elementData.length+capacityIncrement;
			}else{
				newLength=(int)(elementData.length*1.5);
			}
			elementData=new Object[newLength];
			System.arraycopy(oldElements, 0, elementData, 0, size);
		}
	}
	
	public static void main(String[] args) {
		Stack stack=new Stack(10);
		for (int i = 0; i < 10; i++) {
			stack.push("元素"+i);
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(stack.pop());
		}
	}
}
