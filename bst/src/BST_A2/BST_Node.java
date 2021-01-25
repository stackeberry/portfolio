package BST_A2;

public class BST_Node {
 
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node parent;
  
  BST_Node(String data){ this.data=data; }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------
  
  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  public boolean contains(String s) {
	  if (data == null) {
		  return false;
	  } 
		  
	  if (data.compareTo(s) == 0) {
			  return true;
	  } else if (data.compareTo(s) > 0) {
		  if (left == null) {
			  return false;
		  }
		  return left.contains(s);
	  } else {
		  if (right == null) {
			  return false;
		  }
		  return right.contains(s);
	  }
  }
  
  public BST_Node findNode(String s, BST_Node node) {
	  if (!node.contains(s)) {
		  return null;
	  } else {
		  if (s.compareTo(node.data) == 0) {
			  return node;
		  } else if (s.compareTo(node.data) > 0){
			  return findNode(s, node.right);
		  } else {
			  return findNode(s, node.left);
		  }
	  }
  }
  
  public String findMin(BST_Node node) {
	  if (node == null) {
		  return null;
	  } else if (node.left == null) {
		  return node.getData();
	  } else {
		  return findMin(node.left);
	  }
  }
  
  public String findMax(BST_Node node) {
	  if (node == null) {
		  return null;
	  } else if (node.right == null) {
		  return node.getData();
	  } else {
		  return findMax(node.right);
	  }
  }
  
  
  
  
  public String toString(){
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }
}