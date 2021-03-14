import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;
 import java.util.Scanner;
 import javax.swing.*;
 import javax.swing.table.DefaultTableCellRenderer;
 import javax.swing.table.DefaultTableModel;

 public class CSVTable extends JFrame implements ActionListener{
    JTable table;
    static DefaultTableModel model;
    JButton closeButton, SaveButton, FileButton,AddButton;
  
  public CSVTable(String title, String source) {
    super(title);
    table = new JTable();
    JScrollPane scroll = new JScrollPane(table);
    String[] colNames = { "ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};
    model = new DefaultTableModel(colNames, 0);
    InputStream is;
    try {
                 File f = new File(source);
            is = new FileInputStream(f);
        insertData(is);
    }
    catch(IOException ioe) {
        
    }

    JPanel buttonPanel = new JPanel();
    closeButton = new JButton("Close");
    AddButton = new JButton("add Attendance");
    SaveButton = new JButton("Save");
    FileButton = new JButton("Load a Roster");
    buttonPanel.add(closeButton);
    buttonPanel.add(AddButton);
    buttonPanel.add(SaveButton);
    buttonPanel.add(new JLabel("   You can pick a file from the button: "));
    buttonPanel.add(FileButton);
    getContentPane().add(scroll, BorderLayout.CENTER);
    getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    
    //background color and font
    Color c = new Color(255, 0, 0, 100); 
    table.setBackground(c);
    table.getTableHeader().setForeground(Color.BLACK);
    //table.getTableHeader().setBackground(c);
    table.setForeground(Color.BLACK);
    Font font = new Font("",0,20);
    table.setFont(font);
    table.setRowHeight(30);
    pack();
    //buttons
    closeButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
});
  FileButton.addActionListener(new ActionListener() {
	  InputStream newFile;
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		table.setModel(model);
		JFileChooser j = new JFileChooser(new File("F:\\\\\\\\Documents\\\\\\\\school\\\\\\\\CSC 230\\\\\\\\cse 360 project\\\\\\\\src"));
		j.showSaveDialog(null);
		File selectedFile = j.getSelectedFile();
		
		try {
			newFile = new FileInputStream(selectedFile);
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}
        insertData(newFile);
	}
});
  SaveButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser(new File("F:\\\\\\\\Documents\\\\\\\\school\\\\\\\\CSC 230\\\\\\\\cse 360 project\\\\\\\\src"));
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(FileButton);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    exportToCSV(table,fileToSave.getAbsolutePath());
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		}
	}
});
  //implement add attendance here
  AddButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	});}
  
  //used to save
  public static boolean exportToCSV(JTable table, String path) {
	    try {
	        FileWriter csv = new FileWriter(new File(path));

	        for (int i = 0; i < model.getColumnCount(); i++) {
	            csv.write(model.getColumnName(i) + ",");
	        }

	        csv.write("\n");

	        for (int row = 0; row < model.getRowCount(); row++) {
	            for (int colum = 0; colum < model.getColumnCount(); colum++) {
	                csv.write(model.getValueAt(row, colum).toString() + ",");
	            }
	            csv.write("\n");
	        }

	        csv.close();
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

  // adds rows to table
void insertData(InputStream is) {
    Scanner scan = new Scanner(is);
    String[] array;
    while (scan.hasNextLine()) {
        String line = scan.nextLine();
        if(line.indexOf(",")>-1)
            array = line.split(",");
        else
            array = line.split("\t");
        Object[] data = new Object[array.length];
        for (int i = 0; i < array.length; i++)
            data[i] = array[i];

        model.addRow(data);
    }
    table.setModel(model);
} 

public static void main(String args[]) {
    CSVTable frame = new CSVTable("Students","F:\\\\Documents\\\\school\\\\CSC 230\\\\cse 360 project\\\\src\\\\Students.csv");
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}}


