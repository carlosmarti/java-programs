/**
 * The MonthsCalc class keeps track of the amount of months the user has selected and also adds up
 * all the money made in a month as well as accumulate all the months to output the amount to a file and
 * show them the amount of gross made and how much will be theres after taxes has been taken off
 * 
 * @author CalosMarti
 */
package edu.Tridenttech.MartiC.app;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class MonthsCalc {

	private ArrayList<Integer> amountMonths = new ArrayList<Integer>();
	private double monthTotal;
	public ArrayList<Double> monthsValue = new ArrayList<Double>();
	private OutputMonthsCalc outputHandler = null;
	final String OUTPUT_FILE = "C:\\tmp\\records.csv";
	boolean outputWorked = false;
	double expenses = 0.0;
	
	/**
	 * This puts the number of months inside an arraylist
	 * @param months
	 */
	public void setMonths(int months)
	{
		for(int i = 0; i < months; i++)
		{
			amountMonths.add(i);
		
		}
		
	}
	
	public void setwekklyExpenses(double amount)
	{
		expenses = amount;
	}
	
	/*
	 * This method calculates all of the values for the months
	 */
	public void setSalaryCalc(double pay)
	{
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		
		for(int i = 0; i < amountMonths.size(); i++)
		{
			for(int j = 0; j <= 3; j++)
			{
				monthTotal += pay; 
			}
			
			monthsValue.add(monthTotal);
			monthTotal = 0;
		}
		
	}
	
	public ArrayList<Double> getMonthsValues()
	{
		return monthsValue;
		
	}
	
	public void setOutputValues(OutputMonthsCalc handler)
	{
		outputHandler = handler;
		
		if(outputHandler != null)
		{
			OutputValuesToFile();
		}
	}
	
	public boolean getoutputWorked()
	{
		return outputWorked;
	}
	
	private void OutputValuesToFile() 
	{
		int count = 1;
		double tax = 0.08;
		double amountAfterTax = 0;
		double amountTotal = 0;
		double grandTotal = 0.0;
		double monthTotalExpense = expenses * 4;
		double grandTotalExpense = 0.0;
		DecimalFormat df = new DecimalFormat("$0.00");
		df.setMaximumFractionDigits(2);
		
		try {
			FileWriter file = new FileWriter(OUTPUT_FILE);
			outputWorked = true;
			//Start writing to file
			file.write("Your monthly Gross.\n");
			for(int i = 0; i < monthsValue.size(); i++)
			{	
				
				file.write("Projected Gross Amount in Month " + count + ":, " + df.format(monthsValue.get(i)) + "\n");
				amountTotal += monthsValue.get(i);
				count++;
			}
			amountAfterTax = amountTotal * tax;
			grandTotalExpense = monthTotalExpense * count;
			grandTotal = amountTotal - amountAfterTax - grandTotalExpense;
			file.write("\n");
			file.write("Amount taken away from Expenses per month:, " + df.format(monthTotalExpense) + "\n");
			file.write("Projected Gross Amount:, " + df.format(amountTotal) + "\n");
			file.write("Projected Amount being taken from Tax: " + "," + df.format(amountAfterTax) + "\n");
			file.write("Projected Amount left for savings:, " + df.format(grandTotal));
			
			
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
