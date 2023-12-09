import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class myUI extends JFrame {

    JButton startButton = new JButton("Start timer");
    JButton stopButton = new JButton("Stop & save session");
    JButton downloadResults = new JButton("Download results");
    JLabel label = new JLabel();
    File file = new File("savedData.txt");
    JLabel label2 = new JLabel("Your saved data is stored in " + file.getAbsolutePath(), 10);

    public myUI() {
        setUI();
        startButton.addActionListener(e -> {
            if ("Start timer".equals(startButton.getText())) {
                Timer.timeStart = System.currentTimeMillis();
                startButton.setText("Pause");
                label.setText("Work hard! I'm recording time");
            } else /*if  ((startButton.getText()).equals("Pause"))*/{
                startButton.setText("Start timer");
                label.setText("You've been working for " + Timer.Pause());
            }
        });
        stopButton.addActionListener(e -> {
            if(Timer.result!=0 && startButton.getText().equals("Start timer")){
                Timer.stopRecord();
                Timer.clearResult();
                label.setText("Data has been saved");
                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                label.setText("Pause the timer before saving!");
            }
            else {
                label.setText("Pause the timer before saving!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                label.setText("Work hard! I'm recording time");
            }
        });
        downloadResults.addActionListener(e -> {
            try {
                db.saveDataTofIle();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void setUI() {
        this.setSize(650, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Working timer");

        ImageIcon icon = new ImageIcon("timer.png");
        this.setIconImage(icon.getImage());
        centeringFrame(this.getWidth(), this.getHeight(), this);

        label.setOpaque(true);
        label.setText("Pause the timer before saving!");
        label.setFont(new Font("Whisper", Font.BOLD, 30));

        label2.setOpaque(true);
        label2.setFont(new Font("Whisper", Font.BOLD, 13));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 20, 20));
        panel.add(startButton);
        panel.add(stopButton);
        panel.add(downloadResults);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        add(label, gbc);

        gbc.gridy = 1;
        add(panel, gbc);

        gbc.gridy = 2;
        add(label2, gbc);

        this.getContentPane().setBackground(new Color(208, 126, 126));
        panel.setBackground(new Color(208, 126, 126));
        label.setBackground(new Color(208, 126, 126));
        label2.setBackground(new Color(208, 126, 126));
        this.setVisible(true);
    }

    private static void centeringFrame(int sizeWidth, int sizeHeight, JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int X = (screenSize.width - sizeWidth) / 2;
        int Y = (screenSize.height - sizeHeight) / 2;
        frame.setBounds(X, Y, sizeWidth, sizeHeight);
    }
}
