public class DynamicStack extends stack{
    public DynamicStack(){
        super();
    }

    public DynamicStack(int size){
        super(size);
    }

    public DynamicStack(int[] arr){
        int n = arr.length;
        super.intialize(2 * n);

        for(int ele : arr)
            super.push_(ele);
    }

    @Override
    public void push(int data) throws Exception{
        if(super.size() == super.Capacity()) {
            int n = super.size();
            int[] temp = new int[n];
            int i = n - 1;
            while(super.size() != 0)
                temp[i--] = super.pop_();

            super.intialize(2 * n);

            for(int ele : temp)
                super.push_(ele);
        }

        super.push(data);
    }




}
