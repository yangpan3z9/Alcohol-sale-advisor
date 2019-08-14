package gui;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.nio.file.SimpleFileVisitor;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    private FormListener formListener;
    private JList ageList;
    private JComboBox empCombo;

    Calendar now = Calendar.getInstance();




    public FormPanel(){
        Dimension dim = getPreferredSize();
        dim.width= 250;
        setPreferredSize(dim);

        nameLabel = new JLabel("Name");
        occupationLabel = new JLabel("mm/dd/yyyy ");
        nameField= new JTextField(10);
        occupationField = new JTextField(10);
        ageList = new JList();
        empCombo = new JComboBox();

        okBtn = new JButton ("OK");

        //set up list box
       // DefaultListModel ageModel = new DefaultListModel();
        //ageModel.addElement(new AgeCategory(0,"Under 18"));
        //ageModel.addElement(new AgeCategory(1, "18 to 21"));
        //ageModel.addElement(new AgeCategory(2, "Over 21"));
        //ageList.setModel(ageModel);

        //ageList.setPreferredSize(new Dimension(126, 62));
        //ageList.setBorder(BorderFactory.createEtchedBorder());
        //ageList.setSelectedIndex(1);

        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("Employed");
        empModel.addElement("Self-Employed");
        empModel.addElement("Un-Employed");
        empCombo.setModel(empModel);


        //set up mneumonics for ok button
        okBtn.setMnemonic(KeyEvent.VK_O);

        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setLabelFor(nameField);

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String occupation = occupationField.getText();
                AgeCategory ageCat = (AgeCategory)ageList.getSelectedValue();


                FormEvent ev= new FormEvent(this, name, occupation); // FormEvent ev= new FormEvent(this, name, occupation, ageCat.getId());




                if (formListener != null){
                    formListener.formEventOccured(ev);


                }
                                    }
         });



                Border innerBorder = BorderFactory.createTitledBorder("Manual Age Checker");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    layoutComponents();


    }


    public void layoutComponents() {
        setLayout (new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints ();
        gc.gridy=0;


        //First Row/////////
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx=0;


        gc.fill = GridBagConstraints.NONE;
        gc.anchor= GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(nameLabel, gc);

        gc.gridx=1;
        gc.gridy=0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor= GridBagConstraints.LINE_START;
        add(nameField, gc);

        /////SECOND ROW/////
        gc.gridy ++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;

        gc.insets = new Insets(0,0,0,5);
        gc.anchor= GridBagConstraints.LINE_START;
        add(occupationLabel, gc);

        gc.gridy =1;
        gc.gridx=1;

        gc.anchor= GridBagConstraints.LINE_START;
        add(occupationField, gc);

        //////THIRD ROW //////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx =1;
        gc.anchor= GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(ageList, gc);


        //////Fourth ROW //////////

        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridx =1;
        gc.anchor= GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(okBtn, gc);


    }

    public void setFormListener(FormListener listener){
        this.formListener = listener;
    }
}

class AgeCategory{
    private int id;
    private String text;

    public AgeCategory(int id, String text){
        this.id = id;
        this.text = text;
    }

    public String toString() {

        return text;
    }
    public int getId(){

        return id;
    }
}