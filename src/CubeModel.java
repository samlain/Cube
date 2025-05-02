import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;

public class CubeModel {
    public List<String> firstNine;
    public List<String> deck = new ArrayList<>(Arrays.asList(
            "AH","2H","3H","4H","5H","6H","7H","8H","9H","10H","JH","QH","KH",
            "AD","2D","3D","4D","5D","6D","7D","8D","9D","10D","JD","QD","KD",
            "AS","2S","3S","4S","5S","6S","7S","8S","9S","10S","JS","QS","KS",
            "AC","2C","3C","4C","5C","6C","7C","8C","9C","10C","JC","QC","KC"
    ));


    public Map<String, ImageIcon> cardImages = new HashMap<>();

    public void loadImages() {
        for (String card : deck) {
            try {
                BufferedImage originalImage = ImageIO.read(new File("deck/" + card + ".png"));
                Image scaledImage = getScaledImage(originalImage, 150, 150); // Scale the image to fill the square
                cardImages.put(card, new ImageIcon(scaledImage)); // store the scaled image
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Image getScaledImage(BufferedImage originalImage, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();
        return scaledImage;
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public List<String> deal() {
        firstNine = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            String topCard = deck.remove(0);
            firstNine.add(topCard);
        }
        return firstNine;
    }

    public String nextCard() {
        if (deck.isEmpty()) {
            return null;
        }
        return deck.remove(0);
    }

    public int cardsRemaining() {
        return deck.size();
    }

    public void restart() {
        deck = new ArrayList<>(Arrays.asList(
                "AH", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH",
                "AD", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD",
                "AS", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS",
                "AC", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC"
        ));
        shuffle();
    }
}

