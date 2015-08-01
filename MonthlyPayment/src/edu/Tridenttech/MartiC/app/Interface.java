package edu.Tridenttech.MartiC.app;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/***
 * This Class is the interface that the user uses. this interface in charge of sending information to 
 * to the monthsCalc class as well as grabbing information from the MonthsCalc 
 * @author Calos Marti
 *
 */
public class Interface extends JFrame implements OutputMonthsCalc
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int FRAME_WIDTH = 313;
	final int FRAME_HEIGHT = 550;
	static Interface userIn = new Interface();
	private MonthsCalc salaryCalc = new MonthsCalc();
	private JLabel intro = new JLabel("Monthly Salary Projector");
	private JLabel amount = new JLabel("Enter amount paid per week");
	private JLabel selectMonths = new JLabel("Select amount of months");
	private JLabel outputMessege = new JLabel("File outputed");
	private JLabel expences = new JLabel("Enter amount of Expenses per week");
	private JButton submit = new JButton("Submit");
	private JButton callOutFile = new JButton("Save to outputfile");
	private JTextField num1 = new JTextField(12);
	private JTextField num2 = new JTextField(24);
	private JTextArea area = new JTextArea(10,24);
	private String[] months = {"4","6","12"}; 
	private JComboBox selectMenu = new JComboBox(months);
	final String OUTPUT_FILE = "C:\\CabRecords\\records.csv";
	
	public Interface()
	{
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		intro.setFont(new Font("Arial", Font.BOLD, 16));
		setLayout(new FlowLayout());
		add(intro);
		add(selectMonths);
		add(selectMenu);
		add(amount);
		add(num1);
		add(expences);
		add(num2);
		add(submit);
		add(area);
		add(callOutFile);
		outputMessege.setVisible(false);
		add(outputMessege);
		
		submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				double value = Double.parseDouble(num1.getText());
				double secondValue = Double.parseDouble(num2.getText());
				num1.setText("");
				num2.setText("");
				int count = 1;
	
				String content = (String) selectMenu.getSelectedItem();
				
				if(content == "4")
				{
					salaryCalc.setMonths(Integer.parseInt(content));
				}
				if(content == "6")
				{
					salaryCalc.setMonths(Integer.parseInt(content));
				}
				if (content == "12")
				{
					salaryCalc.setMonths(Integer.parseInt(content));
				}
				
				salaryCalc.setwekklyExpenses(secondValue);
				salaryCalc.setSalaryCalc(value);
				for(int i = 0; i < salaryCalc.getMonthsValues().size(); i++)
				{	
					
					area.append("Projected Gross Amount in Month " + count + ": " + salaryCalc.getMonthsValues().get(i) + "\n");
					count++;
				}
				
				
			}
			
		});
		
		callOutFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				salaryCalc.setOutputValues(userIn);
				outputInfoToFile();
			}
			
		});
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		userIn.setVisible(true);
	}
	@Override
	public void outputInfoToFile() {
		// TODO Auto-generated method stub
		if(salaryCalc.getoutputWorked() == true)
		{
			outputMessege.setForeground(Color.BLUE);
			outputMessege.setVisible(true);
		}
		
	}

}
