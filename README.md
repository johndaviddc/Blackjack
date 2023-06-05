# Blackjack Game
This is a simple implementation of the Blackjack card game using Java and Swing for the graphical user interface (GUI).

## How to Play
1. Run the program and a GUI window will appear.
2. The window will display two sections: "Player's Hand" and "Dealer's Hand".
3. The player's initial hand and the dealer's initial hand will be dealt automatically.
4. The player's hand will be displayed in the "Player's Hand" section.
5. The player can click the "Hit" button to draw an additional card from the deck.
6. The player's hand value will be updated on each hit.
7. If the player's hand value exceeds 21, the player busts and the dealer wins.
8. If the player's hand value reaches 21, it's automatically the dealer's turn.
9. The dealer will draw cards until their hand value is at least 17.
10. The dealer's hand and value will be displayed in the "Dealer's Hand" section.
11. After the dealer's turn, the game will determine the winner.
12. If the dealer busts (hand value > 21), the player wins.
13. If the player's hand value is greater than the dealer's hand value, the player wins.
14. If the dealer's hand value is greater than the player's hand value, the dealer wins.
15. If both hands have the same value, it's a tie.
16. The game will display the winner or tie message in a popup dialog.
17. The "Hit" and "Stand" buttons will be disabled after the game ends.
18. To play again, close the popup dialog and run the program again.

## Code Description
* The code is divided into several classes: **`Card`**, **`Deck`**, **`Hand`**, and **`BlackjackGameGUI`**.
* The **`Card`** class represents a playing card with properties such as rank and suit.
* The **`Deck`** class represents a deck of cards, which is initialized with 52 cards and can be shuffled.
* The **`Hand`** class represents a player's hand or the dealer's hand, which can store cards and calculate the hand value.
* The **`BlackjackGameGUI`** class extends JFrame and implements the graphical user interface for the game using Swing.
* The GUI consists of a window with two sections for displaying the player's hand and the dealer's hand, along with "Hit" and "Stand" buttons.
* The game logic is implemented in the **`startGame`**, **`dealerTurn`**, and **`endGame`** methods, which handle the card dealing, player actions, dealer actions, and determining the winner.
* The **`updateUI`** method is responsible for updating the GUI based on the current state of the game.
* The **`main`** method creates an instance of the **`BlackjackGameGUI`** class and sets it as visible.

Enjoy playing the game of Blackjack!
