public class stack {
    private int[] arr;
    private int tos;
    private int noOfElements;
    private int MaxCapacity;

    protected void intialize(int size){
        this.arr = new int[size];
        this.tos = -1;
        this.noOfElements = 0;
        this.MaxCapacity = size;
    }

    public stack(){
        intialize(10);
    }

    public stack(int size){
        intialize(size);
    }

    public int Capacity(){
        return this.MaxCapacity;
    }

    public int size(){
        return this.noOfElements;
    }

    public boolean isEmpty(){
        return this.noOfElements == 0;
    }

    protected void StackUnderFlowException() throws Exception {
        if(this.noOfElements == 0)
            throw new Exception("StackUnderFlow");
    }

    protected void StackOverFlowException() throws Exception {
        if(this.noOfElements == this.MaxCapacity)
            throw new Exception("StackOverFlow");
    }

    protected int top_(){
        return this.arr[this.tos];
    }

    public int top() throws Exception{
        StackUnderFlowException();
        return top_();
    }

    protected void push_(int data){
        this.tos++;
        this.arr[this.tos] = data;
        this.noOfElements++;
    }

    public void push(int data) throws Exception{
        StackOverFlowException();
        push_(data);
    }

    protected int pop_(){
        int rv = this.arr[this.tos];
        this.arr[this.tos] = 0;
        this.tos--;
        this.noOfElements--;

        return rv;
    }

    public int pop() throws Exception{
        StackUnderFlowException();
        return pop_();
    }

}
