import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Interface implements ActionListener {
    final int frameWidth = 300;
    final int frameHeight = 250;
    JFrame frame = new JFrame();
    JPanel mainPanel = new JPanel(new BorderLayout());
    JList<String> leftList = new JList<>();
    JPanel centerPanel = new JPanel(new BorderLayout());
    JList<String> rightList = new JList<>();
    JPanel homePanel = new JPanel(new BorderLayout());
    JPanel guestPanel = new JPanel(new BorderLayout());
    JButton btnAdd1Home = new JButton("+1");
    JButton btnAdd1Guest = new JButton("+1");
    JButton btnRemove1Home = new JButton("-1");
    JButton btnRemove1Guest = new JButton("-1");

    FileManager fileManager;


    public Interface(FileManager fileManager){
        this.fileManager = fileManager;
    }

    public void createFrame(){
        frame.setTitle("ScoreTracker");
        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // stop esecution when frame is closed
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                int width = frame.getWidth() - frame.getInsets().left - frame.getInsets().right;
                int height = frame.getHeight() - frame.getInsets().top - frame.getInsets().bottom;
                mainPanel.setBounds(0, 0, width, height);
            }
        });
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
        frame.add(mainPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        leftList.setListData(new String[] {
                "Primo"
                , "Secondo"
                , "Terzo"
                , "Quarto"
                , "Quinto"
                , "Sesto"
                , "Settimo"
        });
        rightList.setListData(new String[] {
                "Primo"
                , "Secondo"
                , "Terzo"
                , "Quarto"
                , "Quinto"
                , "Sesto"
                , "Settimo"
        });
        mainPanel.add(leftList, BorderLayout.WEST);
        mainPanel.add(rightList, BorderLayout.EAST);
        centerPanel.add(homePanel, BorderLayout.WEST);
        centerPanel.add(guestPanel, BorderLayout.EAST);
    }

    public void setPointsTracker(){
        // Home Label
        JLabel labelHome = new JLabel();
        labelHome.setText("Home");
        homePanel.add(labelHome, BorderLayout.NORTH);

        // button +1 home
        btnAdd1Home.addActionListener(this);    //Registering ActionListener to the button
        homePanel.add(btnAdd1Home, BorderLayout.CENTER);

        // Guest Label
        JLabel labelGuest = new JLabel();
        labelGuest.setText("Guest");
        guestPanel.add(labelGuest, BorderLayout.NORTH);

        // button +1 guest
        btnAdd1Guest.addActionListener(this);   //Registering ActionListener to the button
        guestPanel.add(btnAdd1Guest, BorderLayout.CENTER);

        // button -1 home
        btnRemove1Home.addActionListener(this);   //Registering ActionListener to the button
        homePanel.add(btnRemove1Home, BorderLayout.SOUTH);

        // button -1 guest
        btnRemove1Guest.addActionListener(this);   //Registering ActionListener to the button
        guestPanel.add(btnRemove1Guest, BorderLayout.SOUTH);
    }

    @Override
    // when one button is pressed:
    public void actionPerformed(ActionEvent e) {
        // checks which buttons has been pressed
        if(e.getSource() == btnAdd1Home){
            fileManager.add1Home();
        }else if(e.getSource() == btnAdd1Guest){
            fileManager.add1Guest();
        }else if(e.getSource() == btnRemove1Home){
            fileManager.remove1Home();
        }else if(e.getSource() == btnRemove1Guest){
            fileManager.remove1Guest();
        }
    }
}
