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
                getWorld().addObject(deck.getTopCard(), col * 130 + 83, row * 83 +60);
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
        
    }
    
    protected void checkIfTriple(Card cardsOnBoard, Card cardsSelected, int selectedCardsIndex)
    {
        
    }
    
    private void removeAndReplaceTriple(Card cardsOnBoard, Card cardsSelected, int selectedCardIndex)
    {
        
    }

}
