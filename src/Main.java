import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class Main {

    public static class TextEditor implements ActionListener {
        //       declaring frame
        JFrame frame;

        //       declaring menu bar
        JMenuBar menuBar;

        //       declaring text area
        JTextArea textArea;

        //       declaring menu
        JMenu file, edit;

        //       declaring file menu option
        JMenuItem newFile, newWindow, openFile, saveFile, printFile;

        //       declaring edit menu option
        JMenuItem cut, copy, paste, selectAll, close;

        TextEditor() {
//           initializing the frame
            frame = new JFrame();

//           initializing the menuBar
            menuBar = new JMenuBar();

            //           initializing the text Area
            textArea = new JTextArea();


//           initializing menu and settting name of menu
            file = new JMenu("File");
            edit = new JMenu("Edit");

//           initializing and giving name to options of file menu
            newFile = new JMenuItem("New File");
            openFile = new JMenuItem("Open File");
            saveFile = new JMenuItem("Save File");
            newWindow = new JMenuItem("Open New Window");
            printFile = new JMenuItem("Print");

//           adding action listener to the items of file menu
            newFile.addActionListener(this);
            openFile.addActionListener(this);
            saveFile.addActionListener(this);
            newWindow.addActionListener(this);
            printFile.addActionListener(this);


//           adding file menu options into frame
            file.add(newWindow);
            file.add(newFile);
            file.add(openFile);
            file.add(saveFile);
            file.add(printFile);

//           initializing and giving name to options of edit menu
            cut = new JMenuItem("Cut");
            copy = new JMenuItem("Copy");
            paste = new JMenuItem("Paste");
            close = new JMenuItem("Close");
            selectAll = new JMenuItem("Select All");

//            adding action listener to the items of edit menu
            cut.addActionListener(this);
            copy.addActionListener(this);
            paste.addActionListener(this);
            close.addActionListener(this);
            selectAll.addActionListener(this);

//           adding edit menu option in frame
            edit.add(cut);
            edit.add(copy);
            edit.add(paste);
            edit.add(selectAll);
            edit.add(close);


//            adding options in menu bar
            menuBar.add(file);
            menuBar.add(edit);

//           setting menu bar in the frame
            frame.setJMenuBar(menuBar);

//            taking a panel in which text area will be there and initializing panel
            JPanel panel = new JPanel();
//            setting border for panel
            panel.setBorder(new EmptyBorder(5, 5, 5, 5));
//            setting layout for panel
            panel.setLayout(new BorderLayout(0, 0));
//            adjusting layout at center
            panel.add(textArea, BorderLayout.CENTER);
//            adding scroll bar and setting its requirement on our requirement
            JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//            adding scroll panel to main panel
            panel.add(scrollPane);

//            adding panel to frame
            frame.add(panel);
//           frame.add(textArea);


//           setting the dimension of frame
            frame.setBounds(400, 100, 800, 500);
//           setting frame visibility(it makes frame open when we run our program)
            frame.setTitle("Text Editor By Ajay Chauhan");
            frame.setVisible(true);
            frame.setLayout(null);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {


//            logic for cut
            if (actionEvent.getSource() == cut) {
                textArea.cut();
            }



//            logic for copy
            if (actionEvent.getSource() == copy) {
                textArea.copy();
            }



//            logic for paste
            if (actionEvent.getSource() == paste) {
                textArea.paste();
            }



//            logic for select All
            if (actionEvent.getSource() == selectAll) {
                textArea.selectAll();
            }



//            logic for close
            if (actionEvent.getSource() == close) {
                System.exit(0);
            }




//            logic for open file
            if (actionEvent.getSource() == openFile) {
//                declaring directory from where file should open by default
                JFileChooser fileChooser = new JFileChooser("C:");
//                opening open dialog box and setting parameter null
                int chooseOption = fileChooser.showOpenDialog(null);

//                checking that choose option is getting appropriate value
                if (chooseOption == JFileChooser.APPROVE_OPTION) {

//                    selecting file
                    File file = fileChooser.getSelectedFile();
//                    collecting file path in the form of String
                    String filepath = file.getPath();

                    try {
//                        using buffer reader to to read the file by passing file path
                        FileReader fileReader = new FileReader(filepath);
//                        reading the file
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String intermediate = "", output = "";

//                        read content or coping from file line by line and storing in output and concatenating
                        while ((intermediate = bufferedReader.readLine()) != null) {
                            output += intermediate + "\n";
                        }
//                        after reading or coping the content, pasting in textArea
                        textArea.setText(output);

//                        handling all exception
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

            }






//            logic for save file
            if (actionEvent.getSource() == saveFile) {
//                declaring directory from where file should open by default
                JFileChooser fileChooser = new JFileChooser("C:");
//                opening open dialog box and setting parameter null
                int chooseOption = fileChooser.showSaveDialog(null);

//                checking that choose option is getting appropriate value
                if (chooseOption == JFileChooser.APPROVE_OPTION) {
//                    setting full path of file with extension(for example:- C://Untitled.txt)
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");

                    try {
//                        using writer to write the the file
                        FileWriter fileWriter = new FileWriter(file);
//                        it will save the file using buffer writer
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        textArea.write(bufferedWriter);
                        bufferedWriter.close();

//                        handling all exception
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }




//            logic for create new file
            if(actionEvent.getSource()==newFile){
                textArea.setText("");
            }




//            logic for print file
            if (actionEvent.getSource()==printFile){
                try {
                    textArea.print();
                }catch (PrinterException printerException){
                    printerException.printStackTrace();
                }
            }





//            logic for new window
            if (actionEvent.getSource() == newWindow) {
//                calling text editor for new window
                TextEditor newTextEditor = new TextEditor();
                frame.setBounds(450, 200, 800, 500);
            }

        }


    }

    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();
    }
}