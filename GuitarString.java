/**
 * This GuitarString object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarString 
{
    private RingBuffer ringBuffer;
    private final double samplingRate = 44100;
    private int ticCounter = 0;
    
    public GuitarString(double frequency) {
        int capacity = (int) Math.round((samplingRate / frequency));
        ringBuffer = new RingBuffer(capacity);
        for(int i = 0; i < capacity; i++){
            ringBuffer.add(0);
        }
    }

    public GuitarString(double[] array) {
        ringBuffer = new RingBuffer(array.length);
        for(int i = 0; i < array.length; i++){
            ringBuffer.add(array[i]);
        }
    }
    
    //remove and replace the 
    public void pluck() {        
        for(int i = 0; i < ringBuffer.size(); i++){
            ringBuffer.remove();
            double num = Math.random() - 0.5;
            ringBuffer.add(num);
        }
    }

    // advance the simulation one time step
    public void tic() {
        double first = ringBuffer.remove();
        double next = ringBuffer.peek();
        ringBuffer.add(0.994 * ((first + next) / 2));
        ticCounter++;
    }

    // return the current sample
    public double sample() {
        return ringBuffer.peek();
    }

    // return number of times tic was called
    public int ticCounter() {
        return ticCounter;
    }

    public static void main(String[] args) 
    {
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < 25; i++) 
        {
            int t = testString.ticCounter();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }
}
