package PersonalProject;

import java.awt.EventQueue;

public class Main {
    public static void main(String[] args)
    {
        EventQueue.invokeLater(Main::createFrame);
    }

    private static void createFrame() {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}