

/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
  Node headCell; //this will be the entry point to your linked list (the head)
  Node lastCell; // this is the Node at the end of the list... the starting place
                 // if you wanted to traverse the list backwards
  int size;
  
  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
    headCell = null; //Note that the root's data is not a true part of your data set!
    lastCell = null;
    size = 0;
  }
  
  //implement all methods in interface, and include the getRoot method we made for testing 
  // purposes. Feel free to implement private helper methods!

 // add the fields you need to add to make it work... like a 
  
  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
    return headCell;
  }
  public Node getLast(){ //leave this method as is, used by the grader to grab your linkedList easily.
    return lastCell;
  }

@Override
public boolean insert(double elt, int index) {
	Node newNode = new Node(elt);
	if (index < 0 || index > size) {	
		return false;
	} else {
		if (index == 0) {
			if (isEmpty()) {
				headCell = newNode;
				lastCell = newNode;
			} else {
				newNode.prev = lastCell;
				newNode.next = headCell;
				headCell.prev = newNode;
				lastCell.next = newNode;
				headCell = newNode;
			}
		} else if (index == size) {
			newNode.prev = lastCell;
			newNode.next = headCell;
			headCell.prev = newNode;
			lastCell.next = newNode;
			lastCell = newNode;
		} else {
			newNode.prev = getNode(index - 1);
			newNode.next = getNode(index);
			getNode(index).prev = newNode;
			getNode(index - 1).next = newNode;
		}
		
	}
	size++;
	return true;
}

@Override
public boolean insort(double elt) {
	if (isEmpty()) {
		insert(elt, 0);
		return true;
	}
	
	Node currentNode = headCell;
	int i;
	
	for (i = 0; i <= size; i++) {
		if (currentNode == null) {
				insert(elt, size);
				return true;	
		} else if (headCell.getData() > elt || currentNode.getData() > elt) {
			break;
		} else {
			currentNode = currentNode.next;
		}
	}
		
	if (currentNode == headCell && headCell.getData() > elt) {
		insert(elt, 0);
	} else if (currentNode == lastCell) {
		if (lastCell.getData() > elt) {
			insert(elt, size - 1);
			return true;
		}
		insert(elt, size);
	} else {
		insert(elt, i);
	}
	return true;
	
}
	

@Override
public boolean remove(int index) {
	if (index < 0 || index > size) {
		return false;
	} else {
		if (index == 0) {
			headCell = headCell.next;
			headCell.prev = lastCell;
		} else if (index == size - 1) {
			lastCell = lastCell.prev;
			lastCell.next = headCell;
		} else {
			getNode(index + 1).prev = getNode(index - 1);
			getNode(index - 1).next = getNode(index + 1);
		}
	}
	size--;
	return true;
}

@Override
public double get(int index) {
	Node current = headCell;
	if (index < 0 || index > size - 1) {
		return Double.NaN;
	} else {
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
	}
	return current.data;
}

@Override
public int size() {
	int s = size;
	return s;
}

@Override
public boolean isEmpty() {
	if (size == 0) {
		return true;
	} else {
		return false;
	}
}

@Override
public void clear() {
	size = 0;
	headCell = null;
	lastCell = null;
}

private Node getNode(int index) {
	Node current = headCell;
	if (index < 0 || index >= size) {
		throw new NullPointerException();
	} else {
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}
}
}