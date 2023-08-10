//
// 
//
// Note: You are allowed to add additional methods if you need.
// Name: 
// Student ID: 
//
// Time Complexity and explanation: 
// f denotes initial cabinet size
// n denotes the total number of requests 
// d denotes number of distinct requests
// You can use any of the above notations or define additional notation as you wish.
// 
// appendIfMiss():
// 	
// freqCount():
// 

class Cab {

	public Node head, tail;

	public Cab() {
		head = null;
		tail = null;
	}

	public CabOutput appendIfMiss(int rArray[], int rSize) {
		CabOutput output = new CabOutput(rSize); // Initialize pageFaults to 0
		int comparisons = 1;
		for (int i = 0; i < rSize; i++) {
			comparisons = 0;
			boolean found = false;
			Node curr = head;
			while (curr != null) {
				comparisons++;
				if (curr.data == rArray[i]) {
					found = true;
					break;
				}
				curr = curr.next;


			}
			if (!found) {
				Node newNode = new Node(rArray[i]);
				insertTail(newNode);
				output.missCount++; // Increment missCount when a new node is inserted
			} else {
				output.hitCount++; // Increment hitCount when the requested node is already in the list
			}
			output.compare[i] = comparisons;
		}
		output.cabFromHead = headToTail();
		output.cabFromTail = tailToHead();
		return output;
	}

	public CabOutput freqCount(int rArray[], int rSize) {
		CabOutput output = new CabOutput(rSize);
		for (int i = 0; i < rSize; i++) {
			int comparisons = 0;
			boolean found = false;
			Node current = head;
			while (current != null) {
				comparisons++;
				if (current.data == rArray[i]) {
					found = true;
					current.freq++;
					break;
				}
				current = current.next;
			}
			if (found) {
				output.hitCount++;
				Node temp = head;
				while(temp.next != null){
					if(temp == current)
						break;
					if(temp.freq < current.freq){

						if(current.next != null ){
							current.prev.next = current.next;
							current.next.prev = current.prev;
						}
						else{
							current.prev.next = null;
							tail = current.prev;
						}
						if(temp.prev != null){
							temp.prev.next = current;
							current.prev = temp.prev;
							current.next = temp;
							temp.prev = current;
						}else{
							insertHead(current);
						}
						break;
					}
					temp = temp.next;
				}
			} else {
				output.missCount++;
				Node newNode = new Node(rArray[i]);
				newNode.freq = 1;
				insertTail(newNode);
			}
			output.compare[i] = comparisons;
		}
		output.cabFromHead = headToTail();
		output.cabFromTail = tailToHead();
		output.cabFromHeadFreq = headToTailFreq();
		return output;
	}

	// DO NOT change this method
	// insert newNode to head of list
	public void insertHead(Node newNode) {

		newNode.next = head;
		newNode.prev = null;
		if (head == null)
			tail = newNode;
		else
			head.prev = newNode;
		head = newNode;
	}

	// DO NOT change this method
	// insert newNode to tail of list
	public void insertTail(Node newNode) {

		newNode.next = null;
		newNode.prev = tail;
		if (tail != null)
			tail.next = newNode;
		else head = newNode;
		tail = newNode;
	}

	// DO NOT change this method
	// delete the node at the head of the linked list
	public Node deleteHead() {
		Node curr;

		curr = head;
		if (curr != null) {
			head = head.next;
			if (head == null)
				tail = null;
			else
				head.prev = null;
		}
		return curr;
	}

	// DO NOT change this method
	// empty the cabinet by repeatedly removing head from the list
	public void emptyCab() {
		while (head != null)
			deleteHead();
	}


	// DO NOT change this method
	// this will turn the list into a String from head to tail
	// Only to be used for output, do not use it to manipulate the list
	public String headToTail() {
		Node curr;
		String outString="";

		curr = head;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.next;
		}
		return outString;
	}

	// DO NOT change this method
	// this will turn the list into a String from tail to head
	// Only to be used for output, do not use it to manipulate the list
	public String tailToHead() {
		Node curr;
		String outString="";

		curr = tail;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.prev;
		}
		return outString;
	}

	// DO NOT change this method
	// this will turn the frequency of the list nodes into a String from head to tail
	// Only to be used for output, do not use it to manipulate the list
	public String headToTailFreq() {
		Node curr;
		String outString="";

		curr = head;
		while (curr != null) {
			outString += curr.freq;
			outString += ",";
			curr = curr.next;
		}
		return outString;
	}

}
