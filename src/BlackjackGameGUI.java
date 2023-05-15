import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Card {
	private final String rank;
	private final String suit;
	
	public Card(String rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public String getRank() {
		return rank;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public int getValue() {
		if (rank.equals("Ace")) {
			return 11;
		} else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
			return 10;
		} else {
			return Integer.parseInt(rank);
		}
	}
}

class Deck {
	private final List<Card> cards;
	
	public Deck() {
		String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "King", "Queen", "Jack"};
		String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
		
		cards = new ArrayList<>();
		for (String suit : suits) {
			for (String rank : ranks) {
				cards.add(new Card(rank, suit));
			}
		}
	}
	
	public void shuffle() {
		Random random = new Random();
		for (int i = cards.size() - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			Card temp = cards.get(i);
			cards.set(i, cards.get(j));
			cards.set(j,  temp);
		}
	}
	
	public Card drawCard() {
		if (cards.isEmpty()) {
			return null;
		}
		return cards.remove(0);
	}
}

class Hand {
	private final List<Card> cards;
	
	public Hand() {
		cards = new ArrayList<>();
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public int getValue() {
		int value = 0;
		int numAces = 0;
		
		for (Card card : cards) {
			value += card.getValue();
			if (card.getRank().equals("Ace")) {
				numAces++;
			}
		}
		
		while (value > 21 && numAces > 0) {
			value -= 10;
			numAces--;
		}
		
		return value;
	}
	public List<Card> getCards() {
		return cards;
	}
}

public class BlackjackGameGUI extends JFrame {
	private final Deck deck;
	private final Hand playerHand;
	private final Hand dealerHand;
	private static final long serialVersionUID = 1L;
	
	private JButton hitButton;
	private JButton standButton;
	
	public BlackjackGameGUI() {
		setTitle("Blackjack");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		deck = new Deck();
		playerHand = new Hand();
		dealerHand = new Hand();
		
		hitButton = new JButton("Hit");
		standButton = new JButton("Stand");
		
		hitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerHand.addCard(deck.drawCard());
				updateUI();
				
				int playerValue = playerHand.getValue();
				if (playerValue > 21) {
					endGame("Player busts. Dealer wins!");
				} else if (playerValue == 21) {
					dealerTurn();
				}
			}
		});
		standButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dealerTurn();
			}
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(hitButton);
		buttonPanel.add(standButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
		
		startGame();
	}
	
	private void startGame() {
		deck.shuffle();
		
		playerHand.addCard(deck.drawCard());
		playerHand.addCard(deck.drawCard());
		dealerHand.addCard(deck.drawCard());
		dealerHand.addCard(deck.drawCard());
		
		updateUI();
		
		int playerValue = playerHand.getValue();
		if (playerValue == 21) {
			dealerTurn();
		}
	}
	
	private void dealerTurn() {
		int dealerValue = dealerHand.getValue();
		int playerValue = playerHand.getValue();
		
		while (dealerHand.getValue() < 17) {
			dealerHand.addCard(deck.drawCard());
			dealerValue = dealerHand.getValue();
		}
		
		if (dealerValue > 21) {
			endGame("Dealer busts. Player wins!");
		} else if (dealerValue > playerValue) {
			endGame("Dealer wins!");
		} else if (dealerValue < playerValue) {
			endGame("Player wins!");
		} else {
			endGame("It's a tie!");
		}
	}
	
	private void endGame(String message) {
		hitButton.setEnabled(false);
		standButton.setEnabled(false);
		updateUI();
		JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);	
	}
	
	private void updateUI() {
		getContentPane().removeAll();
		
		JPanel playerPanel = new JPanel();
		JLabel playerLabel = new JLabel("Player's Hand:");
		playerPanel.add(playerLabel);
		
		List<Card> playerCards = playerHand.getCards();
		for (Card card : playerCards) {
			playerPanel.add(new JLabel(card.getRank() + " of " + card.getSuit()));
		}
		
		JPanel dealerPanel = new JPanel();
		JLabel dealerLabel = new JLabel("Dealer's Hand:");
		dealerPanel.add(dealerLabel);
		
		List<Card> dealerCards = dealerHand.getCards();
		for (Card card : dealerCards) {
			dealerPanel.add(new JLabel(card.getRank() + " of " + card.getSuit()));	
		}
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(hitButton);
		buttonPanel.add(standButton);
		
		add(playerPanel, BorderLayout.NORTH);
		add(dealerPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		revalidate();
		repaint();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BlackjackGameGUI game = new BlackjackGameGUI();
				game.setVisible(true);
			}
		});
	}

}
