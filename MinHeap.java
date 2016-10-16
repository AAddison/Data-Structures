
public class MinHeap {
	
	// data
	private int[] Heap;
	private int size;
	private int maxSize;
	
	private static final int FRONT = 1;
	
	// basic ctor, specify the max # of elements your min-heap can hold
	public MinHeap(int maxsize) {
		this.maxSize = maxsize;
		this.size = 0;
		Heap = new int[this.maxSize + 1]; // the heap is stored as an array of ints
		Heap[0] = Integer.MIN_VALUE;
	}
	
	// methods to refer to the parent and child nodes using the definition of how nodes are stored in the array
	private int parent(int pos) {
		return pos/2; // property of minheap, if parent at position k, its child nodes are at 2k and 2k+1
	}
	
	private int leftChild(int pos) {
		return 2*pos;
	}
	
	private int rightChild(int pos) {
		return (2*pos) + 1;
	}
	
	// we know we have a leaf if the 2*pos > size of min-heap since 2*pos and 2*pos+1 are where the child nodes would be stored
	private boolean isLeaf(int pos) {  
		//if (pos >= (size/2) && pos <= size) {
		//	return true;
		//}
		//return false;
		return (pos >= (size/2) && pos <= size);
	}
	
	private void swap(int fpos, int spos) {
		int tmp;
		tmp = Heap[fpos];
		Heap[fpos] = Heap[spos];
		Heap[spos] = tmp;
	}
	
	private void minHeapify(int pos) {
		if (!isLeaf(pos)) {
			// if heap element is greater than either of its child nodes then we need to make a swap
			int leftChild = leftChild(pos);
			int rightChild = rightChild(pos);
			if (Heap[pos]>Heap[leftChild] || Heap[pos]>Heap[rightChild]) {
				// we want to swap with the smaller of the two child nodes
				// if leftChild value < rightChild, swap parent with leftChild 
				// then recursively call function to push big value down
				
				int smallChild = ( (Heap[leftChild] < Heap[rightChild])?leftChild:rightChild );
				swap(pos, smallChild);
				minHeapify(smallChild); // keep repeating until we reach a leaf node
			}
		}
	}
	
	// when we insert a node it goes at the end of the array then works its way up
	public void insert(int element) {
		Heap[++size] = element;
		int current = size;
		while (Heap[current] < Heap[parent(current)]) {
			swap(current, parent(current));
			current = parent(current);
		}
	}
	
	public void print() {
		for (int i=1; i<=size/2; i++) {
			System.out.println(" PARENT :" + Heap[i] + " LEFT CHILD : " + Heap[2*i] + " RIGHT CHILD : " + Heap[2*i+1]);
		}
	}
	
	
	public void minHeap()
	{
		for (int pos = (size/2); pos >=1; pos--) {
			minHeapify(pos);
		}
	}
	
	// we can remove the root node 
	// then place last node in position 1 and compare/swap it down through the levels as necessary
	public int remove() {
		int popped = Heap[FRONT];
		Heap[FRONT] = Heap[size--];
		minHeapify(FRONT);
		return popped;
	}
	
	
	// method to demonstrate the min-heap
	public static void main(String...arg) {
		System.out.println("The Min Heap is");
		MinHeap minHeap = new MinHeap(15);
		minHeap.insert(5);
		minHeap.insert(3);
		minHeap.insert(17);
		minHeap.insert(10);
		minHeap.insert(84);
		minHeap.insert(19);
		minHeap.insert(6);
		minHeap.insert(22);
		minHeap.insert(9);

		minHeap.minHeap();
		minHeap.print();
		System.out.println("The Min Val is " + minHeap.remove());
		minHeap.print();

		
	}
	
	
	
}
