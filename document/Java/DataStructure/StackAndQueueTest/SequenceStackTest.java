package StackAndQueueTest;

import StackAndQueue.SequenceStack;

public class SequenceStackTest {
	public static void main(String[] args) {
		SequenceStack<String> stack=new SequenceStack<String>();
		stack.push("aaaa");
		stack.push("bbbb");
		stack.push("cccc");
		stack.push("dddd");
		System.out.println(stack);
		System.out.println("栈顶元素:"+stack.peek());
		System.out.println("第一次弹出栈顶元素:"+stack.pop());
		System.out.println("第二次弹出栈顶元素:"+stack.pop());
		System.out.println("弹出后的结果:"+stack);
		
	}
}
