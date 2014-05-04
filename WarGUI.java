import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random; 
import java.util.Collections;
import java.util.*;

//cs110 Homework # 10
//Ian Benson
//WarGUI - this is a program simulates the card game War and 
//implements a GUI with jpegs of Card Images


public class WarGUI extends JFrame
{
   //declare fields
   private War roundOfGame; 
   private JPanel topPanel,leftPanel, rightPanel, bottomPanel;  
   private JLabel topStatus, leftStatus, leftImageLabel, rightStatus, rightImageLabel;   
   private JButton flip;
   private Card card1, card2;
   private ArrayList<Card> list = new ArrayList<Card>();

   /**
      This method constructs the GUI.
   */
   public WarGUI()
   {
      //frame settings
      roundOfGame = new War();      
      setTitle("Let's Play A Game Of War");
      setLayout(new FlowLayout());
      setBackground(Color.red);
      
      //top panel settings
      topPanel = new JPanel();
      topPanel.setBackground(Color.black);
      topStatus = new JLabel("Get the game started. Press FLIP.");
      topStatus.setForeground(Color.white);
      topPanel.add(topStatus);
      add(topPanel);
      
      //player one panel settings
      leftPanel = new JPanel();
      leftPanel.setBackground(Color.black);
      leftImageLabel = new JLabel(new ImageIcon("cardPics/back.jpg"));
      leftPanel.add(leftImageLabel);
      leftStatus = new JLabel("26");
      leftStatus.setForeground(Color.white);
      leftPanel.add(leftStatus);
      add(leftPanel);

      //player two panel settings
      rightPanel = new JPanel();
      rightPanel.setBackground(Color.black);
      rightImageLabel = new JLabel(new ImageIcon("cardPics/back.jpg"));
      rightPanel.add(rightImageLabel);
      rightStatus = new JLabel("26");
      rightStatus.setForeground(Color.white);
      rightPanel.add(rightStatus);
      add(rightPanel);
      
      //push button panel settings
      bottomPanel = new JPanel();
      bottomPanel.setBackground(Color.black);
      flip = new JButton("FLIP");
      flip.addActionListener(new ButtonListener()); 
      flip.setVisible(true);
      bottomPanel.add(flip);
      add(bottomPanel);
   }  
   
   /**
      WHEN THE BUTTONLISTENER IS TRIGGERED, IT CREATES A TURN OR ROUND OF ACTION
      IN THE GAME. IT ALSO ACCEPTS SETTINGS AND VALUES FROM THE WAR OBJECT
      CHANGES THE GUI SETTINGS ACCORDINGLY  
   */ 
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {     

         // CHECKS TO ENSURE EACH PLAYER HAS A HAND (THAT IS NOT EMPTY) THEN THE 
         // FOLLOWING CODE WILL EXCECUTE
         
         if(roundOfGame.playerOneSize()!=0 && roundOfGame.playerTwoSize()!=0)
         {

            
            //TWO OBJECTS OF THE CARD CLASS ARE CREATED
            card1 = new Card(roundOfGame.getPlayerOne());
            leftImageLabel.setIcon(new ImageIcon(card1.getImage()));
            card2 = new Card(roundOfGame.getPlayerTwo());
            rightImageLabel.setIcon(new ImageIcon(card2.getImage()));
            list.add(card1);
            list.add(card2);
            
            //If player one wins the turn add cards to their hand.
           
            if(roundOfGame.fight(roundOfGame.getPlayerOne(),roundOfGame.getPlayerTwo())==1)
            {
               topStatus.setText("Player 1 wins this round, press FLIP again.");
               while(list.isEmpty() == false)
               {
                  roundOfGame.addPlayerOne(list.remove(0));
               }
               leftStatus.setText(Integer.toString(roundOfGame.playerOneSize()));
               rightStatus.setText(Integer.toString(roundOfGame.playerTwoSize()));
            }
            
          
            //IF PLAYER ONE WINS THE TURN CARDS ARE ADDED TO PLAYER ONE'S HAND
            else
            {
               topStatus.setText("Player 2 wins this round, press FLIP again.");
               while(list.isEmpty()==false)
               {
                  roundOfGame.addPlayerTwo(list.remove(0));
               }
               rightStatus.setText(Integer.toString(roundOfGame.playerTwoSize()));
               leftStatus.setText(Integer.toString(roundOfGame.playerOneSize()));
            }
         }
         
         //PLAYER 2 WINS IF PLAYER 1'S HAND IS EMPTIED
         else if(roundOfGame.playerOneSize()==0)
         {
            topStatus.setText("Winner of the game: Player 2!");
            flip.setVisible(false);
         }
         
         //PLAYER 1 WINS IF PLAYER 2'S HAND IS EMPTIED
         else
         {
            topStatus.setText("Winner of the game: Player 1!");
            flip.setVisible(false);
         }
      }
   } 
   
   
   // DRIVER METHOD FOR WarGUI
   
   public static void main(String [] args)
   {
      JFrame frame = new WarGUI();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800,800);
      frame.validate();
      frame.setVisible(true);
   }    
   

   
   //THE WAR CLASS, METHODS FROM THIS CLASS ARE ENGAGED WHEN BUTTON'S
   //ON THE GUI ARE CLICKED
   
   public class War
   {
      //FIELDS
      private ArrayList<Card> playerOne = new ArrayList<Card>();
      private ArrayList<Card> playerTwo = new ArrayList<Card>();
      private ArrayList<Card> deck = new ArrayList<Card>();
      private Random r = new Random();
      

      //CONSTRUCTOR ADDS 52 NEW CARD OBJECTS
      public War()
      {     
         deck.add(new Card("cardPics/aces.jpg",14));
         deck.add(new Card("cardPics/acec.jpg",14));
         deck.add(new Card("cardPics/aced.jpg",14));
         deck.add(new Card("cardPics/aceh.jpg",14));
         deck.add(new Card("cardPics/2s.jpg",2));
         deck.add(new Card("cardPics/2c.jpg",2));
         deck.add(new Card("cardPics/2d.jpg",2));
         deck.add(new Card("cardPics/2h.jpg",2));
         deck.add(new Card("cardPics/3s.jpg",3));
         deck.add(new Card("cardPics/3c.jpg",3));
         deck.add(new Card("cardPics/3d.jpg",3));
         deck.add(new Card("cardPics/3h.jpg",3));
         deck.add(new Card("cardPics/4s.jpg",4));
         deck.add(new Card("cardPics/4c.jpg",4));
         deck.add(new Card("cardPics/4d.jpg",4));
         deck.add(new Card("cardPics/4h.jpg",4));   
         deck.add(new Card("cardPics/5s.jpg",5));     
         deck.add(new Card("cardPics/5c.jpg",5));
         deck.add(new Card("cardPics/5d.jpg",5));
         deck.add(new Card("cardPics/5h.jpg",5));
         deck.add(new Card("cardPics/6s.jpg",6));
         deck.add(new Card("cardPics/6c.jpg",6));
         deck.add(new Card("cardPics/6d.jpg",6));
         deck.add(new Card("cardPics/6h.jpg",6));
         deck.add(new Card("cardPics/7s.jpg",7));
         deck.add(new Card("cardPics/7c.jpg",7));
         deck.add(new Card("cardPics/7d.jpg",7));
         deck.add(new Card("cardPics/7h.jpg",7));
         deck.add(new Card("cardPics/8s.jpg",8));
         deck.add(new Card("cardPics/8c.jpg",8));
         deck.add(new Card("cardPics/8d.jpg",8));
         deck.add(new Card("cardPics/8h.jpg",8));
         deck.add(new Card("cardPics/9s.jpg",9));
         deck.add(new Card("cardPics/9c.jpg",9));
         deck.add(new Card("cardPics/9d.jpg",9));
         deck.add(new Card("cardPics/9h.jpg",9));
         deck.add(new Card("cardPics/10s.jpg",10));
         deck.add(new Card("cardPics/10c.jpg",10));
         deck.add(new Card("cardPics/10d.jpg",10));
         deck.add(new Card("cardPics/10h.jpg",10));
         deck.add(new Card("cardPics/jacks.jpg",11));
         deck.add(new Card("cardPics/jackc.jpg",11));
         deck.add(new Card("cardPics/jackd.jpg",11));
         deck.add(new Card("cardPics/jackh.jpg",11));
         deck.add(new Card("cardPics/queens.jpg",12));
         deck.add(new Card("cardPics/queenc.jpg",12));
         deck.add(new Card("cardPics/queend.jpg",12));
         deck.add(new Card("cardPics/queenh.jpg",12));
         deck.add(new Card("cardPics/kings.jpg",13));
         deck.add(new Card("cardPics/kingc.jpg",13));
         deck.add(new Card("cardPics/kingd.jpg",13));
         deck.add(new Card("cardPics/kingh.jpg",13));
         newDeck();         
      } 
      

      
      //FRESHDECK SHUFFLES THE DECK, SPLITS DECK IN HALF TO PLAYERONE ARRAYLIST
      //HALF GOES TO PLAYERTWO ARRAYLIST.
      //BOTHE ARRAYLISTS GET SHUFFLED
      
      public void freshDeck()  
      {
         Collections.shuffle(deck);
         for(int i = 0; deck.isEmpty() == false; i++)
         {
            playerOne.add(deck.remove(0));
            i++;
            playerTwo.add(deck.remove(0));
         }
         Collections.shuffle(playerOne);
         Collections.shuffle(playerTwo);
      } 
      

      // RETURNS CARD OBJECT and removes it FROM PLAYERONE ARRAYLIST
      public Card getPlayerOne()
      {
         int n = r.nextInt(playerOne.size());
         return playerOne.remove(n);
      }
      

      public Card getPlayerTwo()
      {
         int n = r.nextInt(playerTwo.size());
         return playerTwo.remove(n);
      }
     

      public void addPlayerOne(Card c)
      {
         playerOne.add(c);
      }
      
      
  
      public void addPlayerTwo(Card c)
      {
         playerTwo.add(c);
      }
      
   
      public int playerOneSize()
      {
         return playerOne.size();
      }

      public int playerTwoSize()
      {
         return playerTwo.size();
      }


      public int fight(Card p1, Card p2)
      {
         int victor = 3;
         if(p1.getRank() > p2.getRank())
            victor = 1;
         else if(p1.getRank() < p2.getRank())
            victor = 2;
         else
            victor = 0;
         return victor;
      }

   }
}