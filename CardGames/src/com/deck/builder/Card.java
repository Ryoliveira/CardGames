package com.deck.builder;

import com.deck.builder.DeckBuilder.*;

public class Card {
   private CardSuits suit;
   private CardRanks rank;
   private boolean faceDown;

   final static String[] SUITS = { "Clubs", "Diamonds", "Hearts", "Spades" };
   final static String[] SUIT_SYMBOLS = { "\u2663", "\u2666", "\u2665", "\u2660" };// clubs, diamonds, hears, spades in
                                                                                   // unicode
   final static String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };

   public Card(CardSuits suit, CardRanks rank) {
      this.suit = suit;
      this.rank = rank;
      faceDown = true;
   }

   // Sets suit of card
   public void setSuit(CardSuits suit) {
      this.suit = suit;
   }

   // Sets rank of card
   public void setRank(CardRanks rank) {
      this.rank = rank;
   }

   // Returns suit of card
   public CardSuits getSuit() {
      return suit;
   }

   // Returns card rank
   public CardRanks getRank() {
      return rank;
   }

   // Returns if card is face down
   public boolean isFaceDown() {
      return faceDown;
   }

   // Change card to face up/face down
   public void setFaceDown(boolean bool) {
      faceDown = bool;
   }

   // Flips card
   public void flipCard() {
      faceDown = (isFaceDown()) ? false : true;
   }

   // Displays suit and rank of card in text format
   public void displayCard() {
      String suit = SUITS[CardSuits.valueOf(this.suit.toString()).ordinal()];
      String rank = RANKS[CardRanks.valueOf(this.rank.toString()).ordinal()];
      System.out.printf("%s of %s\n\n", rank, suit);
   }

   // Displays card in text art format
   public void displayCardArt() {
      String rank = RANKS[CardRanks.valueOf(this.rank.toString()).ordinal()];
      String cardSymbol = SUIT_SYMBOLS[CardSuits.valueOf(this.suit.toString()).ordinal()];
      if (rank.length() > 2) {
         rank = rank.substring(0, 1);
      }
      if (!this.isFaceDown()) {
         System.out.printf("┎-------┒\n" 
                         + "|   %4s|\n" 
                         + "|       |\n" 
                         + "|   %-2s  |\n" 
                         + "|       |\n"
                         + "|%-4s   |\n" 
                         + "┖-------┙\n", rank, cardSymbol, rank);
      } else {
         System.out.println("┎-------┒");
         for (int i = 0; i < 5; i++) {
            System.out.println("|#######|");
         }
         System.out.println("┖-------┙\n");
      }
   }

   // Compares ranking on cards
   public int compareCard(Card otherCard) {
      return this.rank.compareTo(otherCard.rank);
   }

}
