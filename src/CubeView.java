import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CubeView {
    public JPanel cardPanel, gamePanel, cardsPanel, infoPanel, titlePanel, mainMenuPanel, menuPanel;
    public CardLayout cardLayout;
    // Change restart button to new game
    public JButton higherButton, lowerButton, sameButton, exitButton, restartButton, leaderBoardButton,
            rulesButton, signUpButton, loginButton, newGameButton, playButton, menuButton;
    public JLabel titleLabel, card1, card2, card3, card4, card5, card6, card7, card8, card9, counterLabel,
            greenCardLabel;
    public List<JLabel> cardLabels;

    public void cubeView() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        //cardPanel.add(createSignUpOrLogin(), "SignUpOrLogin");
        //cardLayout.show(cardPanel, "SignUpOrLogin");
        cardPanel.add(createGame(), "Cube");
        //cardPanel.add(createMainMenu(), "MainMenu");
        cardLayout.show(cardPanel, "Cube");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /*public JPanel createMainMenu() {
        mainMenuPanel = new JPanel(new BorderLayout());
        titleLabel = new JLabel("Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        mainMenuPanel.add(titleLabel, BorderLayout.NORTH);

        menuPanel = new JPanel(new BorderLayout());
        playButton = new JButton("Play");
        newGameButton = new JButton("New Game");
        rulesButton = new JButton("Rules");
        menuPanel.add(playButton, BorderLayout.NORTH);
        menuPanel.add(newGameButton, BorderLayout.CENTER);
        menuPanel.add(rulesButton, BorderLayout.SOUTH);

        mainMenuPanel.add(menuPanel, BorderLayout.CENTER);
        return mainMenuPanel;
    }*/
    ////////////////////////////////////////////////////////////////////////////////////////

    public JPanel createGame() {
        gamePanel = new JPanel(new BorderLayout());

        // Title panel
        titlePanel = new JPanel(new GridLayout(1,3));
        menuButton = new JButton("Menu");
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cardPanel.add(createMainMenu(), "MainMenu");
                cardLayout.show(cardPanel, "MainMenu");
            }
        });
        titleLabel = new JLabel("CUBE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        leaderBoardButton = new JButton("Leaderboard");
        /* rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLeaderboard();
            }
        }); */

        titlePanel.add(menuButton);
        titlePanel.add(titleLabel);
        titlePanel.add(leaderBoardButton);
        gamePanel.add(titlePanel, BorderLayout.NORTH);

        // 9x9 of cards
        cardsPanel = new JPanel(new GridLayout(3, 3));

        card1 = createCard("");
        card2 = createCard("");
        card3 = createCard("");
        card4 = createCard("");
        card5 = createCard("");
        card6 = createCard("");
        card7 = createCard("");
        card8 = createCard("");
        card9 = createCard("");


        cardsPanel.add(card1);
        cardsPanel.add(card2);
        cardsPanel.add(card3);
        cardsPanel.add(card4);
        cardsPanel.add(card5);
        cardsPanel.add(card6);
        cardsPanel.add(card7);
        cardsPanel.add(card8);
        cardsPanel.add(card9);

        cardLabels = Arrays.asList(card1, card2, card3, card4, card5, card6, card7, card8, card9);

        gamePanel.add(cardsPanel, BorderLayout.CENTER);

        // Options panel
        infoPanel = new JPanel(new GridLayout(1,6));
        counterLabel = new JLabel("Remaining Cards: 43");
        higherButton = new JButton("Higher");
        lowerButton = new JButton("Lower");
        sameButton = new JButton("Same");
        restartButton = new JButton("Restart Game");
        exitButton = new JButton("Exit");

        // Exit the game
        exitButton.addActionListener(e -> System.exit(0));

        infoPanel.add(counterLabel);
        infoPanel.add(higherButton);
        infoPanel.add(lowerButton);
        infoPanel.add(sameButton);
        infoPanel.add(restartButton);
        infoPanel.add(exitButton);

        // keyBinds();

        gamePanel.add(infoPanel, BorderLayout.SOUTH);

        // Return the game
        return gamePanel;
    }

    public void showRules() {
        JDialog dialog = new JDialog((JFrame) null, "Game Rules", true);
        dialog.setSize(600, 400);  // Make the dialog large
        dialog.setLayout(new BorderLayout());
        JTextArea rulesTextArea = new JTextArea(10, 50);
        rulesTextArea.setText(gameRules());
        rulesTextArea.setEditable(false);
        rulesTextArea.setWrapStyleWord(true);
        rulesTextArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(rulesTextArea);
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private String gameRules() {
        return "Game Rules:\n\n" +
                "1. The game starts with a shuffled deck of cards.\n" +
                "2. You need to select a pile and guess whether the next card will be higher, lower, or the same.\n" +
                "3. For each correct guess, you proceed to the next card.\n" +
                "4. If you guess wrong, the pile will go red and you will need to choose a new pile.\n" +
                "5. If all piles are red the game is over and you lose. \n" +
                "7. The objective is to get rid of all the cards. \n" +
                "8. You can restart the game at any time.\n" +
                "9. Have fun and try to beat your high score!\n\n" +
                "More detailed rules can be added here based on your game...";
    }

    public void showLeaderboard() {
    }

    public void updateCards(List<ImageIcon> cardIcons, List<String> cardNames) {
        card1.setIcon(cardIcons.get(0));
        card1.setToolTipText(cardNames.get(0));
        card2.setIcon(cardIcons.get(1));
        card2.setToolTipText(cardNames.get(1));
        card3.setIcon(cardIcons.get(2));
        card3.setToolTipText(cardNames.get(2));
        card4.setIcon(cardIcons.get(3));
        card4.setToolTipText(cardNames.get(3));
        card5.setIcon(cardIcons.get(4));
        card5.setToolTipText(cardNames.get(4));
        card6.setIcon(cardIcons.get(5));
        card6.setToolTipText(cardNames.get(5));
        card7.setIcon(cardIcons.get(6));
        card7.setToolTipText(cardNames.get(6));
        card8.setIcon(cardIcons.get(7));
        card8.setToolTipText(cardNames.get(7));
        card9.setIcon(cardIcons.get(8));
        card9.setToolTipText(cardNames.get(8));
    }

    private JLabel createCard(String text) {
        JLabel card = new JLabel(text, SwingConstants.CENTER);
        card.setPreferredSize(new Dimension(150, 150));
        card.setFont(new Font("Arial", Font.BOLD, 48));
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        card.setOpaque(true);
        card.setBackground(Color.WHITE);
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (card.getBackground().equals(Color.WHITE)) {
                    card.setBackground(Color.GREEN);
                    greenCardLabel = card;
                    disableOtherCards(card);
                }
            }
        });
        return card;
    }

    public void setHigherLowerSameButton(ActionListener listener) {
        higherButton.addActionListener(listener);
        lowerButton.addActionListener(listener);
        sameButton.addActionListener(listener);
    }

    public void setRestartButton(ActionListener listener) {
        restartButton.addActionListener(listener);
    }

    public void resetCardBackgrounds() {
        for (JLabel card : cardLabels) {
            card.setBackground(Color.WHITE); // Reset to white background
            card.setEnabled(true); // Enable the card
            addCardMouseListener(card);
        }
    }

    public void updateCard(JLabel cardLabel, ImageIcon cardIcon, String cardName) {
        cardLabel.setIcon(cardIcon);
        cardLabel.setToolTipText(cardName);
        cardLabel.setBackground(Color.GREEN);
    }

    public void disableCards(JLabel cardLabel) {
        cardLabel.removeMouseListener(cardLabel.getMouseListeners()[0]);
        cardLabel.setOpaque(true);
        cardLabel.setBackground(Color.RED);
        // for loop enabling the cards
    }

    public void disableOtherCards(JLabel selectedCard) {
        for (JLabel card : cardLabels) {
            if (!card.equals(selectedCard) && !card.getBackground().equals(Color.RED)) {
                card.setEnabled(false);
                card.setBackground(Color.LIGHT_GRAY);
            }
        }
    }

    public void enableAllCards() {
        for (JLabel card : cardLabels) {
            if (card.getBackground().equals(Color.RED)) {
                card.setEnabled(false); // Red cards remain disabled
            } else {
                card.setEnabled(true);  // Re-enable all cards except those that are red
                if (!card.getBackground().equals(Color.GREEN)) {
                    card.setBackground(Color.WHITE);  // Reset background to white unless it's green or red
                }
            }
        }
    }

    public void updateRemainingCards(int remaining) {
        counterLabel.setText("Remaining Cards: " + remaining);
    }

    public boolean areAllCardsRed() {
        for (JLabel card : cardLabels) {
            if (!card.getBackground().equals(Color.RED)) {
                return false; // If any card is not red, return false
            }
        }
        return true;
    }

    private void addCardMouseListener(JLabel card) {
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (card.getBackground().equals(Color.WHITE)) {
                    card.setBackground(Color.GREEN);
                    greenCardLabel = card;
                    disableOtherCards(card);
                }
            }
        });
    }

    public void showResult(String result) {
        JOptionPane.showMessageDialog(null, result);
    }
}
