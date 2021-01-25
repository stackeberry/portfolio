package BST_A2;

public class BST implements BST_Interface {
  public BST_Node root;
  int size;

  
  public BST(){ size=0; root=null; }
  
  @Override
  //used for testing, please leave as is
  public BST_Node getRoot(){ return root; }

  
public boolean contains(String s) {
	if (root == null || s == null) {
		return false;
	}
	return root.contains(s);
}

public boolean insert(String s) {
	if (contains(s)) {
		return false;
	}
	
	root = insertRecursive(s, root);
	size++;
	return true;
	
}

public BST_Node insertRecursive(String s, BST_Node node) {
	  if (node == null) {
		  return new BST_Node(s);
	  }
	  if (node.getData().compareTo(s) == 0) {
		  return node;
	  } else if (node.getData().compareTo(s) > 0) {
		  node.left = insertRecursive(s, node.left);
	  } else {
		  node.right = insertRecursive(s, node.right);
	  }
	  return node;
}



// keep 
public boolean remove(String s) {
	if (size() == 0 || !contains(s)) {
		return false;
	}
	root = removeRecursive(s, root);
	size--;
	return true;
}

// keep
public BST_Node removeRecursive(String s, BST_Node node) {
	  if (node == null) {
		  return node;
	  }
	  
	  int result = s.compareTo(node.getData());
	
	  
	  if (result < 0) {
		  node.left = removeRecursive(s, node.left);
	  } else if (result > 0) {
		  node.right = removeRecursive(s, node.right);
	  }  else if (node.left != null && node.right != null){
		  node.data = node.findMin(node.right);
		  node.right = removeRecursive(node.data, node.right);  
	  } else {
		  if (node.left != null) {
			  node = node.left;
		  } else {
			  node = node.right;
		  }
	  }
	  return node;
}	  

public String findMin() {
	return root.findMin(root);
}


public String findMax() {
	return root.findMax(root);
}


public boolean empty() {
	if (size() == 0) {
		return true;
	} else {
		return false;
	}
}

public int size() {
	return size;
}


public int height() {
	if (root == null) {
		return -1;
	} else {
		return heightRecursive(root);
		
	}
}

public int heightRecursive(BST_Node node) {
	  if (node == null) {
		  return -1;
	  }
	  
	  int leftHeight = heightRecursive(node.left);
	  int rightHeight = heightRecursive(node.right);
	  
	  if (leftHeight > rightHeight) {
		  return leftHeight + 1;
	  } else {
		  return rightHeight + 1;
	  }
}


}