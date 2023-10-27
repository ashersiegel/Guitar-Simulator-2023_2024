/**
 * This GuitarSimulator object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarSimulator
{
    public static void main(String[] args){
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        
        GuitarString[] guitar = new GuitarString[37];
        
        for (int i = 0; i < guitar.length; i++){
            double frequency = 440 * Math.pow(1.05956, (i - 24));
            
            guitar[i] = new GuitarString(frequency);
        }
        
        while (true){
            char key = '0';
            if (StdDraw.hasNextKeyTyped()){
                key = StdDraw.nextKeyTyped();
                
                if (keyboard.indexOf(key) > -1)
                    guitar[keyboard.indexOf(key)].pluck();
            }
            
            double sample = 0;
            
            for (int i = 0; i < guitar.length; i++){
                sample += guitar[i].sample();
            }
            
            StdAudio.play(sample);
            
            for (int i = 0; i < guitar.length; i++){
                guitar[i].tic();
            }
        }
    }
}
