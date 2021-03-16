package crackingthecoding.sol

import scala.util.Random
/*
8.1
Q:
 Design the data structures for a generic deck of cards. Explain how you would
 subclass the data structures to implement blackjack
A:
 We will define next classes:
 1. Abstract class Card with next API:
    properties - colo
    methods 
        a. compare(card: Card) -> 0 equal -1 less 1 is bigger
 2. Deck of cards - will contain card in a data structure (linked list - I will need to ask times) with next API:
    properties:  
    methods: 
        a. pop O(1)
        b. shuffle - shuffle of linked list it's hard. Do it with Array and coppy convert to linked list 
*/

object Suit extends Enumeration{
    type Suit = Value
    val Clubs, Diamonds, Hearts, Spades = Value
}

import Suit._

class Card(val suit: Suit, val rank: Int, val available: Boolean=true){
    def markUnavailable() = if (available) new Card(suit, rank, false) else this
    def markAvailable() = if (!available) new Card(suit, rank, true) else this
}

sealed trait DeckOfCards[+A]
case object EmptyDeckOfCards extends DeckOfCards[Nothing]
case class NonEmptyDeckOfCards[A](head: A, tail: DeckOfCards[A]) extends DeckOfCards[A]

object DeckOfCards{
    def apply[A](as: A*): DeckOfCards[A] = 
        if (as.isEmpty) EmptyDeckOfCards
        else NonEmptyDeckOfCards(as.head, apply(as.tail: _*))

    def dealHand[A](deck: DeckOfCards[A], n: Int): DeckOfCards[A] = deck match {
        case EmptyDeckOfCards => EmptyDeckOfCards
        case NonEmptyDeckOfCards(head, tail) if n <= 0 => EmptyDeckOfCards
        case NonEmptyDeckOfCards(head, tail) => NonEmptyDeckOfCards(head, dealHand(tail, n - 1))
    }

    def dealCard[A](deck: DeckOfCards[A]): Option[A] = dealHand(deck, 1) match {
        case EmptyDeckOfCards => None
        case NonEmptyDeckOfCards(head, tail) => Some(head)
    }

    def length[A](deck: DeckOfCards[A]): Int = deck match {
        case EmptyDeckOfCards => 0
        case NonEmptyDeckOfCards(head, tail) => 1 + length(tail)
    }
}

object Chapter8Q1 extends App {
    val doc = DeckOfCards("a", "b", "c")
    println(doc)
    // println(doc.length) # FIXME: need globaly other solution
}