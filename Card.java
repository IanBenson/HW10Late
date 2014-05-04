// CS 110
// HW 10
// IAN BENSON


// CARD CLASS REPRESENTS CARD IN THE GAME WAR

public class Card
{
   //FIELDS
   private String imageString;
   private int rank;
   
  
   // CONSTRUCTOR RECIEVES STRING AND INT AND ASSIGNS 
   // THEM TO IMAGESTRING AND RANK
   
   public Card(String s, int r)
   {
      imageString = s;
      rank = r;
   }
   
 
   //COPY CONSTRUCTOR
   public Card(Card c)
   {
   this.rank=c.rank;
   this.imageString=c.imageString;
   }
   
 // RETURNS IMAGESTRING VARIABLE
   public String getImage()
   {
      return imageString;
   }
   
 // RETURNS RANK VARIABLE
   public int getRank()
   {
      return rank;
   }
   
}