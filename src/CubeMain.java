// simplify ie refactor
// add leaderboard
// key binds - h,l,s and be able to select card with arrows and enter
// signin = real fname & lname, username, password, confirm password
// login = username and password
import javax.swing.*;

public class CubeMain {
    public static void main(String[] args) {
        CubeModel model = new CubeModel();
        CubeView view = new CubeView();


        JFrame frame = new JFrame("Cube Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        view.cubeView();
        frame.add(view.cardPanel);

        frame.setVisible(true);

        CubeController controller = new CubeController(model, view);
    }
}
