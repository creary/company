package StackAndQueueTest;

import StackAndQueue.LinkQueue;

public class LinkQueueTest {
	public static void main(String[] args) {
		LinkQueue<String> queue=new LinkQueue<String>("aaaa");
		queue.add("bbbb");
		queue.add("cccc");
		System.out.println(queue);
		queue.remove();
		System.out.println("删除一个元素后的队列："+queue);
		System.out.println("dddd");
		System.out.println("再次添加元素后的队列:"+queue);
		queue.remove();
		queue.add("eeee");
		System.out.println(queue);
	}
}
