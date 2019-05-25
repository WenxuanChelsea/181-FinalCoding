package app.controller;

import java.net.URL;
import java.time.LocalDate;

import com.sun.xml.rpc.encoding.Initializable;

import app.StudentCalc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoanCalcViewController implements Initializable   {

	private StudentCalc SC = null;
	
	@FXML
	private TextField LoanAmount;
	
	@FXML
	private Label lblTotalPayemnts;
	
	@FXML
	private DatePicker PaymentStartDate;
	
	@FXML
	private TextField TotalPayments;
	
	@FXML
	private TextField TotalInterest;
	
	@FXML
	private TextField AdditionalPayments;
	
	@FXML
	private TextField InterestRate;
	
	@FXML
	private TextField NbrOfYears;
	
	@FXML
	private TextField NbrOfPayments;
	
	@FXML
	private TextField MonthlyPayment;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */

	
	
	@FXML
	private void btnCalcLoan(ActionEvent event) {

		
		String dLoanAmount = LoanAmount.getText();
		String dAdditionalPayments =  AdditionalPayments.getText();
		double  dInterestRate =  Double.parseDouble(InterestRate.getText());
		double  dNbrOfYears = Double.parseDouble(NbrOfYears.getText());
		LocalDate localDate = PaymentStartDate.getValue();
		
		LoanCaculator loancaculator = new LoanCaculator();
		loancaculator.GetAd_Payment(dAdditionalPayments);
		loancaculator.GetInterest(dInterestRate/100.00);
		loancaculator.Getpaymentinitial(dNbrOfYears*12.00);
		loancaculator.GetPayment(dLoanAmount);
		loancaculator.GetBalance(dLoanAmount);


		TotalPayments.setText((Double.toString(loancaculator.GetTotalPayment())));
		TotalInterest.setText((Double.toString(loancaculator.GetTotalInterest())));
		MonthlyPayment.setText((Double.toString(loancaculator.GetPayment())));
		NbrOfPayments.setText((Double.toString(loancaculator.Getpaymentinitial())));		
		
		System.out.println(localDate);
	}

}