/**
 * Game Board for Triples
 * 
 * @Wandi Feng 
 * @2023/1/12
 */
public class Scorekeeper  
{
    private static long startTime = System.currentTimeMillis( );
    private static int deckSize;
    private static int score;
    
    public static void setDeckSize(int size)
    {
        deckSize = size; 
    }
    
    public static void upDateScore()
    {
    }
    
    public static int getScore()
    {
        return score; 
    }
    
    
        public static void updateScore()
    {
        int timePassed = (int)((System.currentTimeMillis() - startTime) / 1000);
        int points = deckSize - timePassed;  
        if(points > 0)
        {
            score += points;
        }
        startTime = System.currentTimeMillis();
    }
}