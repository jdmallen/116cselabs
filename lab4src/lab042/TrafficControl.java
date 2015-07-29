package lab042;

import javax.swing.JOptionPane;

public class TrafficControl {

	public static void main(String[] args) {
		ACLinkedList list = new ACLinkedList();
//		QuickSort q=new QuickSort();
		
		String cmd="start";
		String ac= "";
		String type="";
		String altitude="";
		String speed="";
		Aircraft plane = null;
//		String deleteMe="";
		int n=0;
		int altNumb; int s=0;
//		Array[] ArrayToBeSorted=new Array[200];
		
//		// For quick testing
//		list.add(new Aircraft("1st",15,200,"747"));
//		list.add(new Aircraft("2nd",30,200,"777"));
//		list.add(new Aircraft("3rd",10,200,"737"));
//		
//		ACLinkedList copy = new ACLinkedList(list);
//		copy.sortByAlt();
//		System.out.println(copy);
//		System.out.println(list);
		
		while (cmd.compareToIgnoreCase("quit")!=0)
		{
			plane=new Aircraft();
			cmd=JOptionPane.showInputDialog(null, "Please input a comand:");
			if (cmd.compareToIgnoreCase("e")==0)
			{
				while(true){
				
				ac=JOptionPane.showInputDialog("Please enter aircraft name:");  // want to set name to list
				type=JOptionPane.showInputDialog("Please enter aircraft type:");
				try{
					altitude=JOptionPane.showInputDialog("Please enter aircraft altitude:");
					altNumb=Integer.parseInt(altitude);
				}
				catch(NumberFormatException e)
				{
					System.out.println("Please input numbers only.");
					continue;
				}
				break;
				} // end of while loop for altitude
				while (true)
				{
					try{
						speed=JOptionPane.showInputDialog("Please enter aircraft speed:");
						s=Integer.parseInt(speed);
					}
					catch(NumberFormatException e){
						System.out.println("Please input numbers only.");
						continue;
					}
					break;
				}//end of while loop for speed
				
				plane.set_name(ac);
				plane.set_alt(altNumb);
				plane.set_speed(s);
				plane.set_type(type);
			

				list.add(plane);
				
				
				System.out.println(plane);
//				list.get(1);
				
//				ArrayToBeSorted=list;
//				list=qsort(ArrayToBeSorted);

				System.out.println(list);
			}
			try {
				if (cmd.compareToIgnoreCase("l") == 0) 
				{

					String deleteMe=JOptionPane.showInputDialog("What index do you want to delete?");
					 n=Integer.parseInt(deleteMe);
					list.delete(n);
					System.out.println(list);
				}
			} 
			catch (NullPointerException e) {
				System.out.println("Index doesn't exist.");
				continue;
			}
			try {
				if (cmd.compareToIgnoreCase("s") == 0) 
				{

					String showMe=JOptionPane.showInputDialog("What index do you want to show?");
					 n=Integer.parseInt(showMe);
					 Aircraft toShow = list.get(n);
					 if (toShow == null){
						 System.out.println("Index doesn't exist.");
					 } else{
						 System.out.println(toShow);
					 }
				}
			} 
			catch (NullPointerException e) {
				System.out.println("Index doesn't exist.");
				continue;
			}

			// "da" to display by ascending altitude values
			if (cmd.compareToIgnoreCase("da") == 0) {
				ACLinkedList copy = new ACLinkedList(list);
				copy.sortByAlt();
				System.out.println(copy);
			}

			// "d" to display remaining in original order
			if (cmd.compareToIgnoreCase("d") == 0) {
				System.out.println(list);
			}

		}
	} // end main

}
