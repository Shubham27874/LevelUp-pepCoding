import java.util.LinkedList;

public class stackUsingLL{
    private LinkedList<Integer> list = new LinkedList<>();

    public void push(int data){
        list.addFirst(data);
    }

    public int top() {
        return list.getFirst();
    }

    public int pop() {
        return list.removeFirst();
    }
}