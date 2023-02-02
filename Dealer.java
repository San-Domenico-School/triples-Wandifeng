import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Game Board for Triples
 * 
 * @Wandi Feng 
 * @2023/1/21
 */
import java.util.ArrayList;
public class Dealer extends Actor
{
    public void addedToWorld(World world)
    {
        dealBoard();
    }
    private Deck deck;
    private int triplesRemaining;
    
    public Dealer(int numCardsInDeck)
    {
        deck = new Deck(numCardsInDeck);
        triplesRemaining = numCardsInDeck / 3;
        Scorekeeper.setDeckSize(numCardsInDeck);
    }
    
    protected void dealBoard()
    {
        for (int row = 0; row < 5; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                getWorld().addObject(deck.getTopCard(), col * 130 + 83, row * 83 + 60);
            }
        }
        
    }
    
    private void setUI()
    {
        String cardsRemainingText = new Integer(triplesRemaining * 3).toString();
        String scoreText = new Integer(Scorekeeper.getScore()).toString();
        getWorld().showText(cardsRemainingText, 310, 470);
        getWorld().showText(scoreText, 310, 504);  
    }
    
    protected void checkIfEndGame()
    {
        if (triplesRemaining == 0)
        {
            Greenfoot.stop();
        }
    }
    
    protected void checkIfTriple(ArrayList<Card> cardsOnBoard, Card[] cardsSelected, ArrayList<Integer>selectedCardsIndex)
    {
        int shapes = cardsSelected[0].getShape().ordinal() + cardsSelected[1].getShape().ordinal() + cardsSelected[2].getShape().ordinal();
        int colors = cardsSelected[0].getColor().ordinal() + cardsSelected[1].getColor().ordinal() + cardsSelected[2].getColor().ordinal();
        int numOfShapes = cardsSelected[0].getNumberOfShapes() + cardsSelected[1].getNumberOfShapes() + cardsSelected[2].getNumberOfShapes();
        int shading = cardsSelected[0].getShading() + cardsSelected[1].getShading() + cardsSelected[2].getShading();
        
        if(shapes % 3 == 0 && colors % 3 == 0 && numOfShapes % 3 == 0 && shading % 3 == 0 )
        {
           removeAndReplaceTriple(cardsOnBoard, cardsSelected, selectedCardsIndex); 
        }
        else
        {
           Animations.wobble(Card[] card);
        }
    }
    
    private void removeAndReplaceTriple(Card cardsOnBoard, Card cardsSelected, int selectedCardIndex)
    {
        
    }
    
    private void removeAndReplaceTriple(ArrayList<Card> cardsOnBoard, Card[] cardsSelected, 
                                    ArrayList<Integer> selectedCardsIndex)
{
       // Set position of triple cards by coordinate, 3 cards by 2 coordinates 
       int[][] cardsXYCoordinate = new int[3][2];  
       for(int card = 0; card < 3; card++)
       {
            cardsXYCoordinate[card][0] = cardsSelected[card].getX();
            cardsXYCoordinate[card][1] = cardsSelected[card].getY();
       }
       // Begin card animation off scene view     
       Animations.slideAndTurn(cardsSelected);      

       // Remove and replace triple cards
       for(int card = 0; card < 3; card++)
       { 
           getWorld().removeObject(cardsSelected[card]);
           if(deck.getNumCardsInDeck() > 0)
           {
               cardsOnBoard.set(selectedCardsIndex.get(card),deck.getTopCard());
               getWorld().addObject(cardsOnBoard.get(selectedCardsIndex.get(card)), 
                                                     cardsXYCoordinate[card][0], 
                                                     cardsXYCoordinate[card][1]);
           }
       }
       
       // UI Housekeeping
       triplesRemaining--;
       Scorekeeper.updateScore();
       setUI();
       checkIfEndGame(); 

    }

}
