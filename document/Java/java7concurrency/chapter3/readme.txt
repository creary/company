基本的同步机制：
synchronized
Lock ReentrantLock ReentrantReadWriteLock.ReadLock ReentrantReadWriteLock.WriteLock

高级同步机制：
1.信号量（Semaphore）：是一种计数器，用来保护一个或者多个共享资源的访问。
它是并发编程的一种基本工具，大多数编程语言都提供了这个机制.在程序中任何时候都可以使用其来保护临界区。
2.CountDownLatch：Java与语言的同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许线程一只等待。
3.CyclicBarrier：Java语言提供的同步辅助类，它允许多个线程在某个集合点处进行相互等待
4.Phaser：Java语言同步辅助类，把并发任务分成多个阶段运行，在开始下一阶段之前，当前阶段中的所有线程都必须执行完成。
5.Exchanger：Java同步辅助类，提供了两个线程之间的数据交换点。
