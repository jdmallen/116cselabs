package lab03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class MTQueue {

	protected final static int MAX_RECORD_LENGTH = 71;
	protected final static int RECORD_COUNT = 20;

	public static String padLeft(String str, int x) {
		return padLeft(str, x, ' ');
	}

	public static String padRight(String str, int x) {
		return padRight(str, x, ' ');
	}

	public static String padZeros(String str, int x) {
		return padLeft(str, x, '0');
	}

	public static String padLeft(String str, int x, char c) {
		String padding = new String(new char[x - str.length()]).replace('\0',
				c);
		return (str.length() >= x) ? str : padding + str;
	}

	public static String padRight(String str, int x, char c) {
		String padding = new String(new char[x - str.length()]).replace('\0',
				c);
		return (str.length() >= x) ? str : str + padding;
	}

	public static String repeatChar(int freq, char c) {
		return new String(new char[freq]).replace('\0', c);
	}

	private Semaphore _sem = new Semaphore(1);
	private Queue<String> _statQ;

	public MTQueue() {
		_statQ = new LinkedList<String>();

	}

	public void MTPut(String qe) {
		try {
			_sem.acquire();
			_statQ.offer(qe);
		} catch (InterruptedException ex) {
			System.out.println("Put interrupted.");
		} finally {
			_sem.release();
		}
	}

	public String MTGet() {
		String retVal = new String();
		try {
			_sem.acquire();
			retVal = _statQ.poll();
		} catch (InterruptedException ex) {
			System.out.println("Get interrupted.");
		} finally {
			_sem.release();
		}
		return retVal;
	}

	public static void main(String[] args) {
		MTQueue masterQ = new MTQueue();

		Thread reading = new Thread(new ReadUserInput(masterQ));
		Thread writing = new Thread(new WriteToFile(masterQ));

		reading.start();
		writing.start();

	}

}
