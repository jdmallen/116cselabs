package lab042;

public class ACLinkedList {

	private Aircraft front;   // refers to first node in list (null if empty)                                    
    
    // Constructs a new empty list.                                            
    public ACLinkedList() {                                         
        front = null;  // null front means empty                                                   
    } 

    // copy constructor
    public ACLinkedList(ACLinkedList list){
    	Aircraft frontCraft = list.get(0);
    	front = new Aircraft(frontCraft);
    	Aircraft cur = front;
    	int size = list.size();
    	for (int i = 1; i < size; i++){
    		cur.set_next(new Aircraft(list.get(i)));
    		cur = cur.get_next();
    	}
    }
                                                   
    // Adds the given value to the end of this list.  

    public void add(Aircraft value) {                                 
        if (front == null) {                                                  
            // empty list                                     
            front = value;                               
        } else {                                       
            // non-empty list; walk to last node                                               
            Aircraft current = front;                                         
            while (current.get_next() != null) {                                              
                current = current.get_next();                                       
            }                               	                                                    
            // add new node at end of list                                               
            current.set_next(value);                           
        }                                        
    }                                                     

    // Inserts the given value into this list at the specified index.                                                    
    // Precondition: 0 <= index <= size                               
    // Throws a NullPointerException if index > size.  
   
    public void insert(int index, Aircraft value) {                                               
        if (index == 0) {                                                     
            // insert at front of list
        	value.set_next(front);
            front = value;                               
        } else {
            Aircraft current = front;                                 
            for (int i = 0; i < index - 1; i++) {                                         
                current = current.get_next();                                              
            }
            value.set_next(current.get_next());
            current.set_next(value);
            
            // current.next = new ListNode(value, current.next);
        }
    }

    // Returns the element at the specified index from the list.
    // Precondition: 0 <= index < size
    // Throws a NullPointerException if index >= size.
    public Aircraft get(int index) {
        Aircraft current = front;
        for (int i = 0; i < index; i++) {
            current = current.get_next();
        }
        return current;
    }
    public String toString() {
        if (front == null) {
            return "[]";
        } else {
            String result = "[" + front;
            Aircraft current = front.get_next();
            while (current != null) {
                result += ", " + current;
                current = current.get_next();
            }
            result += "]";
            return result;
        }
    }
    public void delete(int index) {
        if (index == 0) {
            // removing from the front
            front = front.get_next();
        } else {
            Aircraft current = goTo(index - 1);
            current.set_next(current.get_next().get_next());   // deletes the node
        }
    }

    private Aircraft goTo(int index) {
		
    	 Aircraft current = front;
         for (int i = 0; i < index; i++) {
             current = current.get_next();
         }
         return current;
	}

	public int size() {
        int count = 0;
        Aircraft current = front;
        while (current != null) {
            current = current.get_next();
            count++;
        }
        return count;
    }
	
	public void clear(){
		front = null;
	}
	
	public int searchByName(String name){
		int size = this.size();
		for (int i = 0; i < size; i++){
			Aircraft node = goTo(i);
			if (node.get_name().equals(name)){
				return i;
			}
		}
		return -1; // -1 indicates not found
	}
	
	public void sortByAlt() {
		if (front == null || front.get_next() == null){
			return; // 0 or 1 element, already sorted
		}
		int size = this.size();
		for (int i = 1; i < size; i++) {
			int j = i;
			Aircraft first = get(j-1),
					second = get(j);
			int firstAlt = first.get_alt(), secondAlt = second.get_alt();
			while (j > 0 && firstAlt > secondAlt){
				swap (get(j-2), get(j-1), get(j));
				j--;
				first = get(j-1);
				second = get(j);
				firstAlt = first.get_alt();
				secondAlt = second.get_alt();
			}			
		}
	}
	
	public void swap(Aircraft head, Aircraft a, Aircraft b){
		Aircraft temp = b.get_next();
		
		b.set_next(a);
		a.set_next(temp);
		if (head != null && head != a){
			head.set_next(b);
		} else {
			front = b;
		}
	}
}
