import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DBeeMakerGUI {
	public static void main(String[] args) {
		Color darkGray = new Color(0.3f, 0.3f, 0.3f);
		Color lightGray = new Color(0.5f, 0.5f, 0.5f);
		Color beeYellow = new Color(1f, 218f / 255, 48f / 255);
		Font smallFont = new Font("", Font.PLAIN, 15);
		Font mediumFont = new Font("", Font.PLAIN, 20);
		Font bigFont = new Font("", Font.PLAIN, 45);
		ImageIcon icon = new ImageIcon("icon.png");
		
		JFrame jf = new JFrame("DBeeMaker");
		jf.setSize(600, 540);
		jf.setResizable(false);
		jf.setLocation(300, 200);
		jf.setIconImage(icon.getImage());
		
		JPanel main = new JPanel();
		main.setBackground(darkGray);
		jf.getContentPane().add(main);
		
		JPanel jp1 = new JPanel();
		jp1.setBackground(darkGray);
		main.add(jp1);
		
		JLabel jl1 = new JLabel(icon);
		
		JLabel jl2 = new JLabel("DBeeMaker");
		jl2.setFont(bigFont);
		jl2.setForeground(beeYellow);
		
		JPanel jp2 = new JPanel();
		jp2.setBackground(darkGray);
		main.add(jp2);
		
		JLabel jl3 = new JLabel("Simple, lightweight tool to append game entries to a beeShop .csv database.");
		jl3.setFont(smallFont);
		jl3.setForeground(beeYellow);
		
		JPanel jp3 = new JPanel();
		jp3.setBackground(darkGray);
		main.add(jp3);
		
		JLabel jl4 = new JLabel("Title:");
		jl4.setFont(smallFont);
		jl4.setForeground(beeYellow);
		
		JTextField jtf1 = new JTextField(25);
		jtf1.setFont(smallFont);
		
		JPanel jp4 = new JPanel();
		jp4.setBackground(darkGray);
		main.add(jp4);
		
		JLabel jl5 = new JLabel("Link:");
		jl5.setFont(smallFont);
		jl5.setForeground(beeYellow);
		
		JTextField jtf2 = new JTextField(25);
		jtf2.setFont(smallFont);
		
		JPanel jp5 = new JPanel();
		jp5.setBackground(darkGray);
		main.add(jp5);
		
		JLabel jl6 = new JLabel("File name:");
		jl6.setFont(mediumFont);
		jl6.setForeground(beeYellow);
		
		JLabel jl10 = new JLabel();
		jl10.setFont(mediumFont);
		jl10.setForeground(beeYellow);
		
		JFileChooser jfc = new JFileChooser();
		jfc.addActionListener(l -> { jl10.setText(jfc.getSelectedFile().getAbsolutePath().substring(
				jfc.getSelectedFile().getAbsolutePath().lastIndexOf('\\') + 1)); });
		
		JButton jb2 = new JButton("Choose a file.");
		jb2.setFont(mediumFont);
		jb2.setBackground(lightGray);
		jb2.setForeground(beeYellow);
		jb2.addActionListener((ActionEvent ae) -> { jfc.showOpenDialog(jp5); });
		
		JPanel jp6 = new JPanel();
		jp6.setBackground(darkGray);
		main.add(jp6);
		
		JButton jb1 = new JButton("Append entry!");
		jb1.setFont(bigFont);
		jb1.setBackground(lightGray);
		jb1.setForeground(beeYellow);
		jb1.addActionListener((ActionEvent ae) -> { writeRow(jfc, jtf1, jtf2); });
		
		JPanel jp7 = new JPanel();
		jp7.setBackground(darkGray);
		main.add(jp7);
		
		JLabel jl7 = new JLabel("The title and link cannot contain commas. None of the fields can be blank.");
		jl7.setFont(smallFont);
		jl7.setForeground(beeYellow);
		
		JPanel jp8 = new JPanel();
		jp8.setBackground(darkGray);
		main.add(jp8);
		
		JLabel jl8 = new JLabel("The file must also be a .csv file.");
		jl8.setFont(smallFont);
		jl8.setForeground(beeYellow);
		
		JPanel jp9 = new JPanel();
		jp9.setBackground(darkGray);
		main.add(jp9);
		
		JLabel jl9 = new JLabel("If one or more of these conditions is not met, you will be prompted to fix the error.");
		jl9.setFont(smallFont);
		jl9.setForeground(beeYellow);
		
		JPanel jp10 = new JPanel();
		jp10.setBackground(darkGray);
		main.add(jp10);
		
		JLabel jl11 = new JLabel("Don't have a .csv file?");
		jl11.setFont(mediumFont);
		jl11.setForeground(beeYellow);
		
		JButton jb3 = new JButton("Create a file.");
		jb3.setFont(mediumFont);
		jb3.setBackground(lightGray);
		jb3.setForeground(beeYellow);
		jb3.addActionListener((ActionEvent ae) -> { createFile(); });
		
		jp1.add(jl1);
		jp1.add(jl2);
		jp2.add(jl3);
		jp3.add(jl4);
		jp3.add(jtf1);
		jp4.add(jl5);
		jp4.add(jtf2);
		jp5.add(jl6);
		jp5.add(jb2);
		jp5.add(jl10);
		jp6.add(jb1);
		jp7.add(jl7);
		jp8.add(jl8);
		jp9.add(jl9);
		jp10.add(jl11);
		jp10.add(jb3);
		
		jf.setVisible(true);
	}
	
	public static void writeRow(JFileChooser file, JTextField key, JTextField value) {
		if (key.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Your title is blank.");
		} else if (value.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Your link is blank.");
		} else if (file.getSelectedFile() == null) {
			JOptionPane.showMessageDialog(null, "You haven't selected a file.");
		} else if (key.getText().contains(",")) {
			JOptionPane.showMessageDialog(null, "Your title contains a comma. Please try a different title.");
			key.setText(null);
		} else if (value.getText().contains(",")) {
			JOptionPane.showMessageDialog(null, "Your link contains a comma. Please try a different link.");
			value.setText(null);
		} else if (!file.getSelectedFile().getAbsolutePath().substring(file.getSelectedFile().getAbsolutePath().length() - 4).equals(".csv")) {
			JOptionPane.showMessageDialog(null, "This is not a .csv file.");
			file.setSelectedFile(null);
		} else {
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(new FileWriter(file.getSelectedFile(), true), true);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "An unknown error occurred. Run the program as admin, and then try again.");
			}
			pw.println(key.getText() + "," + value.getText());
			pw.close();
			JOptionPane.showMessageDialog(null, "Entry written successfully!");
			key.setText(null);
			value.setText(null);
		}
	}
	
	public static void createFile() {
		String fileName = JOptionPane.showInputDialog("Enter the absolute or relative path to your new file.\n"
				+ "The .csv extension is not required, but can be inputted.");
		boolean fileCreated;
		if (fileName.substring(fileName.length() - 4).equals(".csv")) {
			File f = new File(fileName);
			try {
				fileCreated = f.createNewFile();
			} catch (IOException e) {
				fileCreated = false;
			}
		} else {
			File f = new File(fileName + ".csv");
			try {
				fileCreated = f.createNewFile();
			} catch (IOException e) {
				fileCreated = false;
			}
		}
		if (fileCreated) {
			JOptionPane.showMessageDialog(null, "The file was created successfully!");
		} else {
			JOptionPane.showMessageDialog(null, "Some error occurred while trying to create the file.\n"
					+ "Maybe the file already exists, or you inputted an invalid name.");
		}
	}
}