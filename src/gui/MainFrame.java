package gui;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class MainFrame extends JFrame {
    private JButton btn;
    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;
    private JFileChooser fileChooser;
    private Controller controller;
    //private TablePanel tablePanel;


    Calendar now = Calendar.getInstance();


    public MainFrame(){
        super("Age 21 Checker, promised not to F you over");

        setLayout(new BorderLayout());

        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        //tablePanel = new TablePanel();

        controller = new Controller();

        //tablePanel.setData(controller.getPeople());

        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());



        btn = new JButton("21 years from Today's Date");
        setJMenuBar(createMenuBar());

        toolbar.setStringListener(new StringListener(){

            @Override
            public void textEmitted(String text){
                textPanel.appendText(text);

            }
        });

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                now = Calendar.getInstance();
                now.add(Calendar.YEAR, -21);


                textPanel.appendText("Do not sell if born AFTER: " + (now.get(Calendar.MONTH) + 1) + "-"
                        + now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR)+"\n\n");






            }
        });
        formPanel.setFormListener(new FormListener() {
            public void formEventOccured(FormEvent e){
                String name = e.getName();
                String occupation = e.getOccupation();
                //int ageCat = e.getAgeCategory();

                //textPanel.appendText(ageCat + "\n\n");


                SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
                Date date2 = null;
                Date date1 = null;
                try {

                    now = Calendar.getInstance();
                    now.add(Calendar.YEAR, -21);
                    date2 = dateFormat.parse(occupation);
                    date1 = dateFormat.parse((now.get(Calendar.MONTH) + 1) + "/"
                            + now.get(Calendar.DATE) + "/" + now.get(Calendar.YEAR));


                } catch (ParseException f){

                    f.printStackTrace();
                }


                if (date1.compareTo(date2)>=0){

                    textPanel.appendText(name + " is over the age of 21,\ntake his/her money give him/her the liquor.\n\n");
                    controller.addPerson(e);
                    //tablePanel.refresh();
                }

               else
                   textPanel.appendText(name + " is either under aged \nand/or \"forgot\" his/her licence, DO NOT SELL\n\n");


            }


        });


        add(formPanel, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        //add(tablePanel, BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);

        setSize(600, 500);
        setMinimumSize(new Dimension(500, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();




        JMenu fileMenu= new JMenu("File");
        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        JMenuItem importDataItem = new JMenuItem("Import Data...");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show");

        JMenuItem showFormItem = new JCheckBoxMenuItem("Manual Age Checker");
        showFormItem.setSelected(true);

        showMenu.add(showFormItem);
        windowMenu.add(showMenu);


        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        showFormItem.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ev){
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ev.getSource();

                formPanel.setVisible(menuItem.isSelected());
            }
        });

        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);

        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        importDataItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                    System.out.println(fileChooser.getSelectedFile());

                }

            }
        });

        exportDataItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                    System.out.println(fileChooser.getSelectedFile());

                }

            }
        });


        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // JOptionPane.showInputDialog(MainFrame.this,
                       // "Enter your User Name", "Enter User Name", JOptionPane.OK_CANCEL_OPTION|JOptionPane.QUESTION_MESSAGE);



                int action = JOptionPane.showConfirmDialog(MainFrame.this,
                        "Do you really want to close?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION ){
                    System.exit(0);
                }

            }
        });
        return menuBar;

    }

}
