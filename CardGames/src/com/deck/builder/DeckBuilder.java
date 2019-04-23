package com.deck.builder;

import java.util.*;

public class DeckBuilder {

   public enum CardRanks {
      TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
   }

   public enum CardSuits {
      CLUBS, DIAMONDS, HEARTS, SPADES
   }

   final static String[] SUITS = { "Clubs", "Diamonds", "Hearts", "Spades" };
   final static String[] SUIT_SYMBOLS = { "\u2663", "\u2666", "\u2665", "\u2660" };// clubs, diamonds, hears, spades in
                                                                                   // unicode
   final static String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };

   private ArrayList<Card> cardDeck;
   private ArrayList<Card> discardPile;

   public DeckBuilder(String deckType) {
      if (deckType.equals("Standard")) {
         standardDeck();
      }

   }

   // Builds standard 52 card deck
   public void standardDeck() {
      cardDeck = new ArrayList<Card>();
      discardPile = new ArrayList<Card>();
      for (CardSuits suit : CardSuits.values()) {
         for (CardRanks rank : CardRanks.values()) {
            Card newCard = new Card(suit, rank);
            cardDeck.add(newCard);
         }
      }
   }

   // Returns current size of card deck
   public int getDeckSize() {
      return cardDeck.size();
   }

   // Returns current size of discard pile
   public int getDiscardPileSize() {
      return discardPile.size();
   }

   // Adds card to top of discard pile
   public void addToDiscardPile(Card card) {
      if (!card.isFaceDown()) {
         card.flipCard();
      }
      discardPile.add(0, card);
   }

   // Prints out each card currently in the deck
   public void displayDeck() {
      int num = 1;
      for (Card card : cardDeck) {
         System.out.println("Card #" + num++);
         card.displayCard();
      }
   }

   // Shuffles card deck
   public void shuffleDeck() {
      Collections.shuffle(cardDeck);
   }

   // Adds discard pile back into deck
   public void resetDeck() {
      cardDeck.addAll(discardPile);
      discardPile.clear();
   }

   // Draw card from top of deck with card face down
   public Card drawCardFaceDown() {
      Card nextCard = cardDeck.remove(0);
      return nextCard;
   }

   // Draw card from top of deck with card face up
   public Card drawCardFaceUp() {
      Card nextCard = drawCardFaceDown();
      nextCard.flipCard();
      return nextCard;
   }

   // Draw from bottom of deck with card face up
   public Card drawFromBottomFaceDown() {
      return cardDeck.remove(getDeckSize() - 1);
   }

   // Draw from bottom of deck with card face up
   public Card drawFromBottomFaceUp() {
      Card card = cardDeck.remove(getDeckSize() - 1);
      card.flipCard();
      return card;
   }

   // Displays card hand to console with text card art
   public void displayHand(Card[] hand) {
      String[] cardArt = { " ┎-------┒ ", " |       | ", " ┖-------┙ " };
      for (int i = 0; i < hand.length; i++) {// Top of card
         System.out.print(cardArt[0]);
      }
      System.out.println();
      for (Card card : hand) {
         if (card.isFaceDown()) {
            System.out.print(" |#######| ");
         } else {// Prints card rank on top right of card
            String rank = RANKS[CardRanks.valueOf(card.getRank().toString()).ordinal()];
            if (rank.length() > 2) {
               rank = rank.substring(0, 1);
            }
            System.out.printf(" |   %4s| ", rank);
         }
      }
      System.out.println();
      for (int i = 0; i < hand.length; i++) {
         System.out.print(cardArt[1]);
      }
      System.out.println();
      for (Card card : hand) {
         if (card.isFaceDown()) {
            System.out.print(" |#######| ");
         } else {// Prints card symbol in middle of card
            String cardSymbol = SUIT_SYMBOLS[CardSuits.valueOf(card.getSuit().toString()).ordinal()];
            System.out.printf(" |   %-2s  | ", cardSymbol);
         }
      }
      System.out.println();
      for (int i = 0; i < hand.length; i++) {
         System.out.print(cardArt[1]);//Blank row
      }
      System.out.println();
      for (Card card : hand) {
         if (card.isFaceDown()) {
            System.out.print(" |#######| ");
         } else {// Prints card rank on bottom left of card
            String rank = RANKS[CardRanks.valueOf(card.getRank().toString()).ordinal()];
            if (rank.length() > 2) {
               rank = rank.substring(0, 1);
            }
            System.out.printf(" |%-4s   | ", rank);
         }
      }
      System.out.println();
      for (int i = 0; i < hand.length; i++) {
         System.out.print(cardArt[2]);//Bottom of card
      }
      System.out.println();
   }

}
