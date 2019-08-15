package demo.concurrent;

/**
 * 不可见性
 * 在没有同步的情况下共享变量
 * 书上说因为没有同步状态变量，程序可能会输出0，ReaderThread也可能一直读取不到主线程写入的ready值，而一直循环下去
 * 虽然我本人测试输出1314没问题，但是，他说有就有吧
 * 
 * 在没有同步的情况下，编译器，处理器，以及运行时都有可能对操作的执行顺序进行一些意想不到的调整。
 * 在缺乏足够同步的多线程程序中，想要对内存的操作的执行顺序进行判断，几乎无法得出正确结论。（恐怖😱!!!）
 * @author lgd
 *
 */
public class NoVisibility {

	private static boolean ready;
	
	private static int number;
	
	/**
	 * 读者线程，待主线程将ready状态改变后输出number值
	 * @author lgd
	 *
	 */
	private static class ReaderThread extends Thread {
		@Override
		public void run() {
			super.run();
			while(!ready) {
				ReaderThread.yield();
			}
			System.out.println(number);
		}
	}
	
	public static void main(String[] args) {
		new ReaderThread().start();
		number = 1314;
		ready = true;
	}
	
}
