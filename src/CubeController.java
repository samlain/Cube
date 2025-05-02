import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.*;

public class CubeController {
    public CubeModel model;
    public CubeView view;
    public String currentCard, nextCard;
    public boolean guessCorrectly;
    public int currentCardValue, nextCardValue;
    public ImageIcon nextCardIcon;

    public CubeController(CubeModel model, CubeView view) {
        this.model = model;
        this.view = view;
        model.loadImages();
        dealCards();

        this.view.setHigherLowerSameButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compare(e);
            }
        });

        this.view.setRestartButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame(e);
            }
        });
    }

    public void dealCards() {
        model.shuffle();
        List<String> dealtCards = model.deal();
        List<ImageIcon> cardIcons = new ArrayList<>();
        for (String card : dealtCards) {
            cardIcons.add(model.cardImages.get(card));
        }
        view.updateCards(cardIcons, dealtCards);
    }

    public int cardValue(String card) {
        char rank = card.charAt(0);
        switch (rank) {
            case 'A': return 1;
            case 'J': return 11;
            case 'Q': return 12;
            case 'K': return 13;
            default:
                try {
                    return Integer.parseInt(card.substring(0, card.length() - 1));
                } catch (NumberFormatException e) {
                    return -1;
                }
        }
    }

    public static void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void compare(ActionEvent e) {
        if (view.greenCardLabel == null || !view.greenCardLabel.getBackground().equals(Color.GREEN)) {
            view.showResult("No card selected");
            return;
        }

        currentCard = view.greenCardLabel.getToolTipText();
        // currentCard = view.greenCardLabel.getText();
        nextCard = model.nextCard();

        if (nextCard == null) {
            view.showResult("You won! Congratulations.");
            return;
        }

        currentCardValue = cardValue(currentCard);
        nextCardValue = cardValue(nextCard);

        String actionCommand = e.getActionCommand(); // Get the button label ("Higher", "Lower", "Same")

        switch (actionCommand) {
            case "Higher":
                guessCorrectly = nextCardValue > currentCardValue;
                break;

            case "Lower":
                guessCorrectly = nextCardValue < currentCardValue;
                break;

            case "Same":
                guessCorrectly = nextCardValue == currentCardValue;
                break;
        }

        if (guessCorrectly) {
            playSound("sound/correct.wav");
            nextCardIcon = model.cardImages.get(nextCard);
            view.updateCard(view.greenCardLabel, nextCardIcon, nextCard);
            view.greenCardLabel.setBackground(Color.GREEN);
        }
        else {
            playSound("sound/wrong.wav");
            nextCardIcon = model.cardImages.get(nextCard);
            view.updateCard(view.greenCardLabel, nextCardIcon, nextCard);
            view.greenCardLabel.setBackground(Color.RED); // Turn red if incorrect
            view.enableAllCards();
            view.disableCards(view.greenCardLabel);
            view.greenCardLabel = null;
        }
        int remaining = model.cardsRemaining();
        view.updateRemainingCards(remaining);
        if (view.areAllCardsRed()) {
            view.showResult("Game Over! All piles are red.");
        }
    }

    public void restartGame(ActionEvent e) {
        model.restart();
        dealCards();
        view.enableAllCards();
        view.updateRemainingCards(model.cardsRemaining());
        view.resetCardBackgrounds();
    }
}

