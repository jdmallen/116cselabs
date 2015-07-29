package lab041;

public class Node {

	public int v;
	public Node next;

	public Node(int v) {
		this.v = v;
		this.next = null;
	}

	public Node(int v, Node next) {
		this.v = v;
		this.next = next;
	}
}