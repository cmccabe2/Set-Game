//Chris McCabe
//CS 110 Final: Set Game: Deck Class

import java.util.Random;
import java.util.ArrayList;

public class Deck
{
	private ArrayList<Card> deck;
	private int ct;
	public final int MAX_CARDS=81;
	
	
	/**
	 * default constructor for a deck that adds 81 different cards to the deck 
	 */
	public Deck()
	{
		deck = new ArrayList<>(MAX_CARDS);
		ct = 0;
		
		for (int c = 0;c <= Card.Color.RED.ordinal();c++)
			for (int f =0;f <= Card.Fill.HATCHED.ordinal();f++)
				for (int s = 0;s <= Card.Shape.DIAMOND.ordinal();s++)
					for (int n = 0;n <= Card.Num.THREE.ordinal();n++)
						{
							deck.add(new Card(Card.Color.values()[c],Card.Fill.values()[f],Card.Shape.values()[s],Card.Num.values()[n]));
							ct++;
						}
		
	}
	
	/**
	 * shuffles the deck by randomly selecting an index value in the deck and swaps them
	 */
	public void shuffle()
	{
		int randNum;
		Random r = new Random();
		Card temp;
		for (int i = 0 ; i< ct; i++)
		{
			randNum = r.nextInt(ct);
			temp = deck.get(i);
			deck.set(i,deck.get(randNum));
			deck.set(randNum, temp);
		}
	}
	
	/**
	 * "Deals" the top card(index 0) 
	 * @return topCard Card object
	 */
	public Card getTopCard()
	{
		Card topCard= deck.get(0);
		deck.remove(0);
		return topCard;
	}
	
	
	/**
	 * Overrides toString method and returns the values of all cards in the deck 
	 */
	@Override
	public String toString()
	{
		String deckString="";
		for(int i =0;i<deck.size();i++)
		{
			deckString += deck.get(i).toString()+", ";
		}
		return deckString;
	}
	
	/**
	 * Returns true or false if the deck is empty or not
	 * @return boolean 
	 */
	public boolean isEmpty()
	{
		return ct == 0;
	}
	
	

	public static void main(String []args)
	{
		Deck d = new Deck();
		
		System.out.println(d);
	}
}