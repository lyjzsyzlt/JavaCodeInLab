import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class clientGUI {
    private JPanel panel1;
    private JButton 确定Button;
    private JTextField textField1;
    private JTextField textField2;
    private JButton 发送Button;

    public clientGUI() {
        确定Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("clientGUI");
        frame.setContentPane(new clientGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JTextArea textArea1;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
