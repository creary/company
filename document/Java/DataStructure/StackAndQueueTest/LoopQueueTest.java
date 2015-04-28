package StackAndQueueTest;

import StackAndQueue.LoopQueue;

public class LoopQueueTest {
	public static void main(String[] args) {
		LoopQueue<String> queue=new LoopQueue<String>("aaaa",3);
		queue.add("bbbb");
		queue.add("cccc");
		System.out.println(queue);
		queue.remove();
		System.out.println("删除一个元素后的队列:"+queue);
		queue.add("dddd");
		System.out.println(queue);
		System.out.println("队列满时的长度:"+queue.length());
		queue.remove();
		queue.add("eeee");
		System.out.println(queue);
	}
}
