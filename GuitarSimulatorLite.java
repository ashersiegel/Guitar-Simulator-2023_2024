/*****************************************************************************
 *
 *  Plays two guitar strings (concert A and concert C) when the user
 *  types the lowercase letters 'a' and 'c', respectively in the 
 *  standard drawing window.
 *
 ****************************************************************************/

public class GuitarSimulatorLite 
{
    public static void main(String[] args) throws Exception
    {
        // Create two guitar strings, for concert A and C
        double CONCERT_A = 440.0;
        double CONCERT_C = CONCERT_A * Math.pow(2, 3.0/12.0);
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] notes = new GuitarString[keyboard.length()];
        
        for(int i = 0; i < keyboard.length(); i++) {
            double frequency = 400 * Math.pow(1.05956, i - 24);
            notes[i] = new GuitarString(frequency);
        }
        // the main input loop
        while (true) 
        {
            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) 
            {
                // the user types this character
                char key = StdDraw.nextKeyTyped();
                int i = keyboard.indexOf(key);
                
                if(i > -1) {
                    notes[i].pluck();
                }
                // pluck the corresponding string
            }

            // compute the superposition of the samples
            double sample = stringA.sample() + stringC.sample();
            for(GuitarString s: notes) {
                sample += s.sample();
            }
            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for(GuitarString s: notes) {
                s.tic();
            }
        }
    }
}