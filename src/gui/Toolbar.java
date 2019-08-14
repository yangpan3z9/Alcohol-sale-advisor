package gui;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.util.Calendar;

public class Toolbar extends JPanel implements ActionListener {

    private JButton helloButton;
    private JButton goodbyeButton;
    private JButton touchme;

    private StringListener textListener;

    //private TextPanel textPanel;
    Calendar now = Calendar.getInstance();
    public Toolbar(){

        setBorder(BorderFactory.createEtchedBorder());


        helloButton = new JButton("Today's Date");
        goodbyeButton = new JButton("Goodbye");
        touchme = new JButton("Touch Me!");

        helloButton.addActionListener(this);
        goodbyeButton.addActionListener(this);
        touchme.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(helloButton);
        add(goodbyeButton);
        add(touchme);

    }

    public void setStringListener(StringListener listener){
       //textListener.textEmitted("Today's date: "+ (now.get(Calendar.MONTH) + 1) + "-"
                //+ now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR)+ "\n");
        this.textListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        JButton clicked = (JButton)e.getSource();

        if (clicked == helloButton)
        {
            if(textListener != null){
                textListener.textEmitted("Today's date: "+ (now.get(Calendar.MONTH) + 1) + "-"
                        + now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR)+ "\n\n");
            }

        }

        else if (clicked ==touchme) {
            if (textListener != null) {
                textListener.textEmitted("oooo, ahhh, love it when you touch me there\n\n");
            }
        }
        else if (clicked == goodbyeButton)
           {
                   if(textListener !=null) {
                       textListener.textEmitted("Goodbye \n\n");
                   }

           }

    }
}



