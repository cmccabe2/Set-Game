
public class tester
{
	public static void main(String [] args)
	{
		//color fill shape num
		Card c1=new Card(Card.Color.PURPLE,Card.Fill.OUTLINE,Card.Shape.DIAMOND,Card.Num.THREE);
		Card c2=new Card(Card.Color.PURPLE,Card.Fill.HATCHED,Card.Shape.DIAMOND,Card.Num.THREE);
		Card c3=new Card(Card.Color.PURPLE,Card.Fill.SOLID,Card.Shape.DIAMOND,Card.Num.THREE);

		System.out.println(Card.isSet(c1,c2,c3));
		Deck d = new Deck();
		
		System.out.println(d);
	}
}