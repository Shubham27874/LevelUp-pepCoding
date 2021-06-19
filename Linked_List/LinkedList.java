public class LinkedList {
    
    public class Node {
        private int data = 0;
        private Node next = null;

        Node(int data){
            this.data = data;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int noOfNodes = 0;

    public int size(){
        return this.noOfNodes;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    protected void handleZeroSize(Node node){
        this.head = node;
        this.tail = node;
    }

    protected void addFirstNode(Node node){
        if(size() == 0)
            handleZeroSize(node);
        else {
            node.next = this.head;
            this.head = node;
        }

        this.noOfNodes++;
    }

    public void addFirst(int data){
        Node node = new Node(data);
        addFirstNode(node);
    }

    public void addLastNode(Node node){
        if(size() == 0){
            handleZeroSize(node);
        } else {
            this.tail.next = node;
            this.tail = node;
        }

        this.noOfNodes++;
    }

    public void addLast(int data){
        Node node = new Node(data);
        addLastNode(node);
    }

    public void addNodeAt(Node node, int idx){
        if(idx == 0){
            addFirstNode(node);
        } else if(idx == size() - 1){
            addLastNode(node);  
        } else {
            Node nodeAt = getNodeAt(idx - 1);
            Node forw = nodeAt.next;

            nodeAt.next = node;
            node.next = forw;
            this.noOfNodes++;
        }
    }

    public void addAt(int data, int idx) throws Exception{
        if(idx < 0 || idx > size()){
            throw new Exception("Invalid Index");
        }

        Node node = new Node(data);
        addNodeAt(node, idx);
    }

    public Node getNodeAt(int idx){
        Node temp = this.head;
        
        while(idx-- > 0){
            temp = temp.next;
        }

        return temp;
    }

    public void handleSizeOne(){
        this.head = null;
        this.tail = null;
    }

    public Node removeFirstNode(){
        Node temp = head;
        if(size() == 1){
            handleSizeOne();
        } else {
            this.head = this.head.next;
        }

        temp.next = null;
        this.noOfNodes--;

        return temp;
    }

    public int removeFirst() throws Exception{
        if(size() == 0) {
            throw new Exception("LinkedList is Empty");
        } 
        Node node = removeFirstNode();
        return node.data;        
    }

    public Node removeLastNode(){
        Node temp = tail;
        if(size() == 1){
            handleSizeOne();
        } else {
            Node getNode = getNodeAt(size() - 2);
            getNode.next = null;
            this.tail = getNode;
        }

        this.noOfNodes--;
        return temp;
    }

    public int removeLast() throws Exception{
        if(size() == 0)
            throw new Exception("LinkedList is Empty");
        
        Node node = removeLastNode();
        return node.data;
    }

    protected Node removeNodeAt(int idx){
        if(idx == 0)
            return removeFirstNode();
        else if(idx == size() - 1)
            return removeLastNode();
        else {
            Node prevNode = getNodeAt(idx - 1);
            Node removeNode = prevNode.next;
            Node forwNode = removeNode.next;

            prevNode.next = forwNode;
            removeNode.next = null;

            this.noOfNodes++;
            return removeNode;
        }    
    }

    public int removeAt(int idx) throws Exception{
        if(size() == 0)
            throw new Exception("LinkedList is Empty");
        else if(idx < 0 || idx >= size())
            throw new Exception("Invalid Index");

        Node node = removeNodeAt(idx);
        return node.data;
    }

    public int getFirst() throws Exception {
        if (size() == 0)
            throw new Exception("LinkedList Is Empty");

        return this.head.data;

    }

    public int getLast() throws Exception {
        if (size() == 0)
            throw new Exception("LinkedList Is Empty");
    
        return this.tail.data;
    }

    public int getAt(int idx) throws Exception {
        if (size() == 0)
            throw new Exception("LinkedList Is Empty");
        else if (idx < 0 || idx >= size())
            throw new Exception("Invalid Index");

        return getNodeAt(idx).data;
    }
}
