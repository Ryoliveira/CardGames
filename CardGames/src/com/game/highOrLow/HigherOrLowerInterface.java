package com.game.highOrLow;

import java.util.*;

import com.deck.builder.Card;
import com.deck.builder.DeckBuilder;

public class HigherOrLowerInterface {

   final static DeckBuilder DECK = new DeckBuilder("Standard");
   final static Scanner INPUT = new Scanner(System.in);

   private Card currentCard;

   public HigherOrLowerInterface() {
      DECK.shuffleDeck();
      currentCard = DECK.drawCardFaceUp();
   }

   //Runs the game
   public void play() {
      boolean play = true;
      while (play) {
         boolean keepPlaying = true;
         int streak = 0;
         while (keepPlaying) {
            keepPlaying = takeTurn();
            if (keepPlaying) {
               streak++;
            }
         }
         System.out.println("Guessing Streak: " + streak);
         System.out.println("Would you like to play again? (Y/N)");
         INPUT.nextLine();
         String choice = INPUT.nextLine();
         play = (choice.equalsIgnoreCase("Y")) ? true : false;
         if(play) {
            DECK.resetDeck();
            DECK.shuffleDeck();
            currentCard = DECK.drawCardFaceUp();
         }
      }
      System.out.println("Thanks for playing! Good-Bye!");
   }

   //Displays current card and asks for players guess
   public boolean takeTurn() {
      Card nextCard = DECK.drawCardFaceDown();
      System.out.println("Current Card\n"
                       + "=-=-=-=-=-=-=");
      displayCards(currentCard, nextCard);
      int choice = getChoice();
      boolean outcome = compareCards(choice, currentCard, nextCard);
      nextCard.flipCard();
      displayCards(currentCard, nextCard);
      DECK.addToDiscardPile(currentCard);
      currentCard = nextCard;
      return outcome;
   }

   //Gets users choice of higher or lower
   public int getChoice() {
      int choice = -1;
      boolean valid = false;
      System.out.println("Will the next card be higher or lower?");
      while (!valid) {
         System.out.println("1) Higher\n2) Lower");
         choice = INPUT.nextInt();
         if (choice < 1 || choice > 2) {
            System.out.println("Please enter a valid option.\n");
         } else {
            valid = true;
         }
      }

      return choice;

   }

   public boolean compareCards(int choice, Card currentCard, Card nextCard) {
      boolean outcome = false;
      String outcomeText = "";
      int compareResult = currentCard.compareCard(nextCard);
      if (choice == 1 && compareResult < 0) {
         outcomeText = "\nCORRECT! Next card is Higher!";
         outcome = true;
      } else if (choice == 2 && compareResult > 0) {
         outcomeText = "\nCORRECT! Next card is Lower!";
         outcome = true;
      }else if(compareResult == 0) {
         outcomeText ="Its a tie! Free point!";
         outcome = true;
      }
      else {
         outcomeText = "\nSORRY!!\nYou guessed incorrectly!";
      }
      System.out.println(outcomeText);
      return outcome;
   }

   //Displays cards side by side
   public void displayCards(Card card1, Card card2) {
      Card[] cards = { card1, card2 };
      DECK.displayHand(cards);

   }

}
