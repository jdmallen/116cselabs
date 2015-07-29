package lab041;

public class BetterArray {
	private Node front;   // refers to first node in list (null if empty)                                    

    
    // Constructs a new empty list.                                            
    public BetterArray() {                                         
        front = null;  // null front means empty                                                   
    }                
                                                   
    // Adds the given value to the end of this list.  
    
    public void add(int value) {                                 
        if (front == null) {                                                  
            // empty list                                     
            front = new Node(value);                               
        } else {                                       
            // non-empty list; walk to last node                                               
            Node current = front;                                         
            while (current.next != null) {                                              
                current = current.next;                                       
            }                               
                                                    
            // add new node at end of list                                               
            current.next = new Node(value);                               
        }                                        
    }                                                     

    // Inserts the given value into this list at the specified index.                                                    
    // Precondition: 0 <= index <= size                               
    // Throws a NullPointerException if index > size.  
   
    public void insert(int index, int value) {                                               
        if (index == 0) {                                                     
            // insert at front of list                                                  
            front = new Node(value, front);                               
        } else {                                  
            // walk to node before the one to insert                                                  
            Node current = front;                                 
            for (int i = 0; i < index - 1; i++) {                                         
                current = current.next;                                              
            }
            
           Node newNode = new Node(value, current.next);
            current.next = newNode;
            
            // shorter version of the above code
            // current.next = new ListNode(value, current.next);
        }
    }
   
    // Returns the element at the specified index from the list.
    // Precondition: 0 <= index < size
    // Throws a NullPointerException if index >= size.
    public int get(int index) {
        Node current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.v;
    }
    public String toString() {
        if (front == null) {
            return "[]";
        } else {
            String result = "[" + front.v;
            Node current = front.next;
            while (current != null) {
                result += ", " + current.v;
                current = current.next;
            }
            result += "]";
            return result;
        }
    }
    
    public void delete(int index) {
        if (index == 0) {
            // removing from the front
            front = front.next;
        } else {
            Node current = goTo(index - 1);
            current.next = current.next.next;   // deletes the node
        }
    }
    
    private Node goTo(int index) {
		// TODO Auto-generated method stub
    	 Node current = front;
         for (int i = 0; i < index; i++) {
             current = current.next;
         }
         return current;
	}
    
	public int size() {
        int count = 0;
        Node current = front;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }
}
