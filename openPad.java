import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/*** OPENPAD TEXT EDITOR
 COPYRIGHT(C) 2020 SHAIKH AQUIB */
class openPad extends JFrame implements ActionListener {
  JFrame frame = new JFrame("openPad");
  JMenu menu = new JMenu("File");
  JMenuBar bar = new JMenuBar();
  JTextArea area = new JTextArea();
  JScrollPane scrollpane = new JScrollPane(area);
  JMenuItem open, save, saveas;
  JFileChooser ch = new JFileChooser();

  //constructor for the gui
  openPad() {
    //creating menu options
    open = new JMenuItem("Open");
    save = new JMenuItem("Save");
    saveas = new JMenuItem("SaveAs");

    open.addActionListener(this);
    save.addActionListener(this);
    saveas.addActionListener(this);

    //adding options to menu
    menu.add(open);
    menu.add(save);
    menu.add(saveas);

    //setting textArea
    area.setSize(800,600);
    area.setLineWrap(true);
    area.setWrapStyleWord(true);

    //adding menu to bar
    bar.add(menu);

    //adding elements
    frame.setJMenuBar(bar);
    frame.add(scrollpane, BorderLayout.CENTER);

    //setting frame settings
    frame.setSize(800,600);
    frame.setVisible(true);
    frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

//for saving
  public void saveAs() {
    final JFileChooser fc = new JFileChooser();
    fc.setApproveButtonText("Save");
    int actionDialog = fc.showOpenDialog(this);
    if(actionDialog != JFileChooser.APPROVE_OPTION){ return; }

    File fileName = new File(fc.getSelectedFile() + ".txt");
    BufferedWriter outFile = null;
    try{
      outFile = new BufferedWriter(new FileWriter(fileName));
      area.write(outFile);
    }catch (IOException e) {
      e.printStackTrace();
    }finally {
         if (outFile != null) {
            try {
               outFile.close();
            } catch (IOException e) {}
         }
       }
  }

//for opening an existing file
  public void open() throws IOException {
    int returnVal = ch.showOpenDialog(null);
    File file;
    if(returnVal != JFileChooser.APPROVE_OPTION) {
      return;
    }
    area.setText("");
    file = ch.getSelectedFile();
    BufferedReader in = new BufferedReader(new FileReader(file));
    String line = in.readLine();
    while(line != null) {
      area.append(line + "\n");
      line = in.readLine();
    }


  }
  //actions for menu items
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == open) {
      try{ open(); }
      catch(IOException ee){}
    }
    else if(e.getSource() == save) {
      ch.showSaveDialog(null);
    }
    else if(e.getSource() == saveas) {saveAs();}
  }

/**MAIN FUNCTION*/
  public static void main(String args[]){
    openPad p = new openPad();

  }
}
