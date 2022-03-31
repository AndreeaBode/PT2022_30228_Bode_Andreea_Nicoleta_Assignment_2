import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class View extends JFrame {

    private JTextField nrClientiField;
    private JTextField nrCoziField;
    private JTextField timpSosire1Field;
    private JTextField timpServire1Field;
    private JTextField timpSosire2Field;
    private JTextField timpServire2Field;
    private JTextField timeLimitField;
    private JLabel titluLabel;
    private JLabel clientiLabel;
    private JLabel queueLabel;
    private JLabel timpSosireLabel;
    private JLabel timeLimitLabel;
    private JLabel timpServireLabel;
    private JTextArea rezultatArea;
    private JButton startButton;
    private JScrollPane scroll;
    private View view = this;

    public View() {


        this.setBounds(100, 100, 766, 492);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);


        titluLabel = new JLabel("QUEUES MANAGEMENT");
        titluLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titluLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
        titluLabel.setBounds(224, 27, 306, 44);
        this.getContentPane().add(titluLabel);

        clientiLabel = new JLabel("Nr. clienti");
        clientiLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clientiLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        clientiLabel.setBounds(10, 103, 129, 44);
        this.getContentPane().add(clientiLabel);

        queueLabel = new JLabel("Nr. cozi");
        queueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        queueLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        queueLabel.setBounds(10, 163, 129, 44);
        this.getContentPane().add(queueLabel);

        timpSosireLabel = new JLabel("Timp sosire");
        timpSosireLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timpSosireLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        timpSosireLabel.setBounds(17, 217, 139, 44);
        this.getContentPane().add(timpSosireLabel);

        timpServireLabel = new JLabel("Timp servire");
        timpServireLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timpServireLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        timpServireLabel.setBounds(17, 271, 139, 45);
        this.getContentPane().add(timpServireLabel);

        nrClientiField = new JTextField();
        nrClientiField.setBounds(137, 115, 80, 29);
        this.getContentPane().add(nrClientiField);
        nrClientiField.setColumns(10);

        nrCoziField = new JTextField();
        nrCoziField.setColumns(10);
        nrCoziField.setBounds(137, 179, 80, 29);
        this.getContentPane().add(nrCoziField);

        timpSosire1Field = new JTextField();
        timpSosire1Field.setColumns(10);
        timpSosire1Field.setBounds(137, 228, 80, 29);
        this.getContentPane().add(timpSosire1Field);

        timpServire1Field = new JTextField();
        timpServire1Field.setColumns(10);
        timpServire1Field.setBounds(137, 287, 80, 29);
        this.getContentPane().add(timpServire1Field);

        timpServire2Field = new JTextField();
        timpServire2Field.setColumns(10);
        timpServire2Field.setBounds(223, 287, 80, 29);
        this.getContentPane().add(timpServire2Field);

        timpSosire2Field = new JTextField();
        timpSosire2Field.setColumns(10);
        timpSosire2Field.setBounds(223, 228, 80, 29);
        this.getContentPane().add(timpSosire2Field);


        rezultatArea = new JTextArea();
        rezultatArea.setBounds(321, 89, 421, 356);

         JPanel panel=new JPanel(null);
        scroll = new JScrollPane ( rezultatArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );

       scroll.setBounds(321, 89, 421, 356);
       panel.add(scroll);
       this.getContentPane().add(panel);


        this.getContentPane().add(scroll);

        startButton = new JButton("Start");
        startButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        startButton.setBounds(68, 393, 188, 38);
        this.getContentPane().add(startButton);

        timeLimitLabel = new JLabel("Timp limita");
        timeLimitLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLimitLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        timeLimitLabel.setBounds(36, 336, 103, 29);
        this.getContentPane().add(timeLimitLabel);

        timeLimitField = new JTextField();
        timeLimitField.setBounds(137, 339, 80, 29);
        this.getContentPane().add(timeLimitField);
        timeLimitField.setColumns(10);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimulationManager gen = null;
                try {
                    gen = new SimulationManager(
                            view
                           /* getNrCoziField(),
                            getNrClientiField(),
                            getTimpSosire1Field(),
                            getTimpSosire2Field(),
                            getTimpServire1Field(),
                            getTimpServire2Field()*/
                    );
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Thread t = new Thread(gen);
                t.start();
            }
        });
        this.setVisible(true);
    }

    public int getNrClientiField() {
        return Integer.parseInt(nrClientiField.getText());
    }


    public int getNrCoziField() {
        return Integer.parseInt(nrCoziField.getText());
    }

    public int getTimpSosire1Field() {
        return Integer.parseInt(timpSosire1Field.getText());
    }

    public int getTimpServire1Field() {
        return Integer.parseInt(timpServire1Field.getText());
    }

    public int getTimpSosire2Field() {
        return Integer.parseInt(timpSosire2Field.getText());
    }

    public int getTimpServire2Field() {
        return Integer.parseInt(timpServire2Field.getText());
    }

    public String getRezultatArea() {
        return rezultatArea.getText();
    }

    public void setRezultatArea(String rezultatArea) {
        this.rezultatArea.append(String.valueOf(rezultatArea));
    }

    public int getTimeLimitField() {
        return Integer.parseInt(timeLimitField.getText());
    }
}
