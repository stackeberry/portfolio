
package LinkedList_A1;

public class LinkedListPlayground {

  public static void main(String[] args) { 
    /*
     here you can instantiate your LinkedList and play around with it to check
     correctness. We've graciously also provided you a bit of extra test data for debugging.
     It doesn't matter what you have in here. We will not grade it. This is for your use in testing your implementation.
      */
    test1();
    test2();

  }
  
  public static void test1(){
    // example test cases
    LinkedListImpl L= new LinkedListImpl();
    L.insort(14);
    printList(L);
    L.insort(21);
    printList(L);
    L.insert(10, 1);
    printList(L);
    L.remove(2);
    printList(L);
    L.insort(8);
    printList(L);
    L.insert(20, 3);
    printList(L);
    L.insort(17);
    printList(L);
    L.insort(14);
    printList(L);
    System.out.println("8 14 10 14 17 20");
  }

  public static void test2(){
    // example test cases
    LinkedListImpl L= new LinkedListImpl();
    L.insort(14);
    printList(L);
    L.insort(21);
    printList(L);
    L.insort(5);
    printList(L);
    L.insort(17);
    printList(L);
    L.insort(67);
    printList(L);
    System.out.println("5 14 17 21 67");
  }
  
  public static void printList(LinkedListImpl L){ 
    //note that this is a good example of how to iterate through your linked list
    // since we know how many elements are in the list we can use a for loop
    Node curr=L.headCell; // the first data node in the list... might be null
    System.out.print("List: ");
    for(int i=0; i<L.size(); i++) { 
      System.out.print(" --> " + curr.data);
      curr=curr.next;
    }
    System.out.println();
  }
}