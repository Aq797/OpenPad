import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/*** OPENPAD TEXT EDITOR
 COPYRIGHT(C) 2020 SHAIKH AQUIB */
class openPad extends JFrame implements ActionListener {

  JFrame frame = new JFrame("openPad");

  JMenu menu = new JMenu("File");
  JMenu option_menu = new JMenu("Options");
  JMenu font_menu = new JMenu("Fonts");
  JMenu background_menu = new JMenu("Background");
  JMenu theme_menu = new JMenu("Themes");
  JMenu color_menu = new JMenu("Color");
  JMenu size_menu = new JMenu("Size");
  JMenu style_menu = new JMenu("Style");

  JMenuBar bar = new JMenuBar();
  JMenuBar option_bar = new JMenuBar();
  //JMenuBar font_bar = new JMenuBar();
  //JMenuBar themes_bar = new JMenuBar();

  JTextArea area = new JTextArea();
  JScrollPane scrollpane = new JScrollPane(area);
  JFileChooser ch = new JFileChooser();

  JMenuItem open, save, saveas, font, background_color, black_back;
  JMenuItem blue_back, white_back, white_text, black_text, red_text ;
  JMenuItem dos_mode, dark_mode, normal_mode;
  JMenuItem size1, size2, size3, size4, size5;
  JMenuItem font1, font2, font3, font4, font5;

  String DEFAULT_FONT_NAME = "Baghdad";
  int DEFAULT_FONT_SIZE = 15;
  Font DEFAULT_FONT = new Font(DEFAULT_FONT_NAME, Font.BOLD, DEFAULT_FONT_SIZE);

  //constructor for the gui
  openPad() {
    //setting font
    area.setFont(DEFAULT_FONT);

    //creating menu1 options
    open = new JMenuItem("Open");
    save = new JMenuItem("Save");

    //adding listener to menu1 objects
    open.addActionListener(this);
    save.addActionListener(this);

    //adding options to menu
    menu.add(open);
    menu.add(save);

    //creating menu2 options
    font = new JMenuItem("Font");
    background_color = new JMenuItem("Background Color");

    //creating font color options
    white_text = new JMenuItem("White");
    black_text = new JMenuItem("Black");
    red_text = new JMenuItem("Red");

    //adding listeners to above objects
    white_text.addActionListener(this);
    black_text.addActionListener(this);
    red_text.addActionListener(this);

    //adding the above objects to color menu
    color_menu.add(white_text);
    color_menu.add(black_text);
    color_menu.add(red_text);

    //creating size objects
    size1 = new JMenuItem("10");
    size2 = new JMenuItem("15");
    size3 = new JMenuItem("20");
    size4 = new JMenuItem("25");
    size5 = new JMenuItem("30");

    //adding action listeners to the above objects
    size1.addActionListener(this);
    size2.addActionListener(this);
    size3.addActionListener(this);
    size4.addActionListener(this);
    size5.addActionListener(this);

    //adding the above objects to size menu
    size_menu.add(size1);
    size_menu.add(size2);
    size_menu.add(size3);
    size_menu.add(size4);
    size_menu.add(size5);

    //creating style objects
    font1 = new JMenuItem("Arial");
    font2 = new JMenuItem("Courier");
    font3 = new JMenuItem("Comic Sans MS");
    font4 = new JMenuItem("Serif");
    font5 = new JMenuItem("Times New Roman");

    //adding action listener to above objects
    font1.addActionListener(this);
    font2.addActionListener(this);
    font3.addActionListener(this);
    font4.addActionListener(this);
    font5.addActionListener(this);

    //adding the above objects to style menu
    style_menu.add(font1);
    style_menu.add(font2);
    style_menu.add(font3);
    style_menu.add(font4);
    style_menu.add(font5);

    //adding the above objects to font menu
    font_menu.add(color_menu);
    font_menu.add(size_menu);
    font_menu.add(style_menu);

    //creating background color option
    black_back = new JMenuItem("Black");
    blue_back = new JMenuItem("Blue");
    white_back = new JMenuItem("White");

    //adding action listener to the above objects
    black_back.addActionListener(this);
    blue_back.addActionListener(this);
    white_back.addActionListener(this);

    //adding the above objects to background menu
    background_menu.add(black_back);
    background_menu.add(blue_back);
    background_menu.add(white_back);

    //creating theme options
    dos_mode = new JMenuItem("DOS");
    dark_mode = new JMenuItem("Dark");
    normal_mode = new JMenuItem("Normal");

    //adding listeners to the above objects
    dos_mode.addActionListener(this);
    dark_mode.addActionListener(this);
    normal_mode.addActionListener(this);

    //adding the above objects to themes menu
   theme_menu.add(dos_mode);
   theme_menu.add(dark_mode);
   theme_menu.add(normal_mode);

   //adding the font, background and theme menu to the option menu
   option_menu.add(font_menu);
   option_menu.add(background_menu);
   option_menu.add(theme_menu);

    //setting textArea
    area.setSize(800,600);
    area.setLineWrap(true);
    area.setWrapStyleWord(true);

    //adding menu to bar
    bar.add(menu);
    bar.add(option_menu);

    //adding elements
    frame.setJMenuBar(bar);

    frame.add(scrollpane, BorderLayout.CENTER);

    //setting frame settings
    frame.setSize(800,600);
    frame.setFont(DEFAULT_FONT);
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

    File fileName = new File(fc.getSelectedFile() +"");
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
    if (returnVal != JFileChooser.APPROVE_OPTION) {
      return;
    }
    area.setText("");
    file = ch.getSelectedFile();
    BufferedReader in = new BufferedReader(new FileReader(file));
    String line = in.readLine();
    while (line != null) {
      area.append(line + "\n");
      line = in.readLine();
    }
  }

    //changing font size
    public void changeSize(int size) {
      Font temp = new Font(DEFAULT_FONT.getFontName(), DEFAULT_FONT.getStyle(), size );
      DEFAULT_FONT = temp;
      area.setFont(DEFAULT_FONT);
    }

    //changing font style
    public void changeStyle(String name) {
      Font temp = new Font(name, DEFAULT_FONT.getStyle(), DEFAULT_FONT.getSize());
      DEFAULT_FONT = temp;
      area.setFont(DEFAULT_FONT);
    }

    //changing theme
    public void setTheme(String style) {
      if(style.equals("dos")) {
        area.setForeground(Color.WHITE);
        area.setBackground(Color.BLUE);
      }
      else if(style.equals("dark mode")) {
        area.setForeground(Color.WHITE);
        area.setBackground(Color.BLACK);
      }
      else if(style.equals("normal mode")) {
        area.setForeground(Color.BLACK);
        area.setBackground(Color.WHITE);
      }
    }

  //actions for menu items
  public void actionPerformed(ActionEvent e) {
    //file options
    if (e.getSource() == open) {
      try { open(); } catch (IOException ee) { }
    }
    else if (e.getSource() == save) { saveAs(); }

    //text options
    else if (e.getSource() == white_text) { area.setForeground(Color.WHITE);}
    else if (e.getSource() == red_text) { area.setForeground(Color.RED);}
    else if (e.getSource() == black_text) { area.setForeground(Color.BLACK);}

    //background options
    else if(e.getSource() == black_back) { area.setBackground(Color.BLACK); }
    else if(e.getSource() == blue_back) { area.setBackground(Color.BLUE); }
    else if(e.getSource() == white_back) {area.setBackground(Color.WHITE); }

    //theme options
    else if(e.getSource() == dos_mode) { setTheme("dos"); }
    else if(e.getSource() == dark_mode) { setTheme("dark mode"); }
    else if(e.getSource() == normal_mode) { setTheme("normal mode"); }

    //size options
    else if(e.getSource() == size1) { changeSize(10); }
    else if(e.getSource() == size2) { changeSize(15); }
    else if(e.getSource() == size3) { changeSize(20); }
    else if(e.getSource() == size4) { changeSize(25); }
    else if(e.getSource() == size5) { changeSize(30); }

    //font style options
    else if(e.getSource() == font1 ) { changeStyle("Arial"); }
    else if(e.getSource() == font2 ) { changeStyle("Courier"); }
    else if(e.getSource() == font3 ) { changeStyle("Comic Sans MS"); }
    else if(e.getSource() == font4 ) { changeStyle("Serif"); }
    else if(e.getSource() == font5 ) { changeStyle("Times New Roman"); }


  }

/**MAIN FUNCTION*/
  public static void main(String args[]){
    openPad p = new openPad();

  }
}
