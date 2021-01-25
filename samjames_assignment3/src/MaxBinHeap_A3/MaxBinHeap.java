package MaxBinHeap_A3;

public class MaxBinHeap implements Heap_Interface {
  private double[] array; //load this array
  private int size;
  private static final int arraySize = 10000; //Everything in the array will initially 
                                              //be null. This is ok! Just build out 
                                              //from array[1]

  public MaxBinHeap() {
    this.array = new double[arraySize];
    array[0] = Double.NaN;  //0th will be unused for simplicity 
    size = 0;                        //of child/parent computations...
                            //the book/animation page both do this.
  }
    
  //Please do not remove or modify this method! Used to test your entire Heap.
  @Override
  public double[] getHeap() { 
    return this.array;
  }

	public void insert(double element) {
		
		int hole = size + 1;
		for (; element > array[hole / 2]; hole /=2) {
			if (hole == 0) {
				return;
			}
			array[hole] = array[Math.floorDiv(hole, 2)];
		}
		array[hole] = element;
		size++;
	}
	
	public void delMax() {
		if (size == 0) {
			return;
		}
		array[1] = array[size];
		array[size] = 0;
		size--;
		percolateDown(1);

	}
	
	public void percolateDown(int hole) {
		int child;
		double temp = array[hole];
		for (; hole * 2 <= size; hole = child) {
			child = hole * 2;
			if (child != size &&
					array[child + 1] > array[child]) {
				child++;
			}
			if (array[child] > temp) {
				array[hole] = array[child];
			} else {
				break;
			}
		}
		
		
		array[hole] = temp;
		
	}
	
	
	public double getMax() {
		if (size == 0) {
			return Double.NaN;
		}
		return array[1];
	}
	
	public void clear() {
		array = new double[arraySize];
		array[0] = Double.NaN;
		size = 0;
		
	}
	
	public int size() {
		return size;
	}
	
	public void build(double[] elements) {
		clear();
		for (int i = 0; i < elements.length; i++) {
			array[i + 1] = elements[i];
		}
		size = elements.length;
		for (int i = size / 2; i > 0; i--) {
			percolateDown(i);
		}
	}
	
	public double[] sort(double[] elements) {
		clear();
		double[] sorted = new double[elements.length];
		for (int i = 0; i < elements.length; i++) {
			insert(elements[i]);
		}
		
		size = elements.length;
		
		for (int i = size - 1; i > -1; i--) {
			sorted[i] = getMax();
			delMax();
		}
		return sorted;
	}
	
	

}