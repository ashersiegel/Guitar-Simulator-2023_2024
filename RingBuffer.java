public class RingBuffer 
{
    private int size;
    private double[] ringBuffer;
    private int front, rear;
    private int capacity;

    public RingBuffer(int capacity) {
        this.capacity = capacity;
        clear();
    }

    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == ringBuffer.length;
    }
    
    //add to the end
    public void add(double value) {
        if(size < ringBuffer.length){
            if(rear > ringBuffer.length - 1){
                rear = 0;
            }
            ringBuffer[rear] = value;
            size++;
            rear++;
            if(rear > ringBuffer.length - 1){
                rear = 0;
            }
        }
    }
    
    public void clear() {
        ringBuffer = new double[capacity];
        size = 0;
        front = 0;
        rear = 0;
    }
    
    //look at the front
    public double peek() {
        if(isEmpty()){
            System.out.println("Empty");
        }
        return ringBuffer[front];
    }
    
    //remove from the front
    public double remove() {
        double num = ringBuffer[front];
        front++;
        if(front > ringBuffer.length - 1){
            front = 0;
        }  
        size--;
        if(size <= 0){
            clear();
        }
        return num;
    }
    
    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args)  {
        int capacity = 100;
        RingBuffer buffer = new RingBuffer(capacity);  
        for(int i = 1; i <= capacity; i++) 
            buffer.add(i);
      
        double t = buffer.remove();
        buffer.add(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while(buffer.size() >= 2) 
        {
            double x = buffer.remove();
            double y = buffer.remove();
            buffer.add(x + y);
        }
        System.out.println(buffer.peek());
    }

}
