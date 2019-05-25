package app.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import org.apache.poi.ss.formula.functions.FinanceLib;
import java.time.LocalDate;

public class LoanCaculator {
	private final ObservableList<PaymentElement> element = FXCollections.observableArrayList();
   
    
    public static double Roundingdecimal(double number) {
        return ((double)Math.round(number*100))/100;
    }
    
    private double totalpayment;
    private double totalinterest;
    
    public double GetTotalPayment() {
        totalpayment=0;
        for(int i=0;i<element.size();i++){
            totalpayment+=Double.parseDouble(element.get(i).GetPayment());
        }
        totalpayment=Roundingdecimal(totalpayment);
        return totalpayment;
    }

    public double GetTotalInterest() {
        totalinterest=0;
        for(int i=0;i<element.size();i++){
            totalinterest+=Double.parseDouble(element.get(i).GetInterest());
        }
        totalinterest=Roundingdecimal(totalinterest);
        return totalinterest;
    }

    public ObservableList<PaymentElement> CalculatePayment(double loanAmount, double interestRate, double numberOfYears, double additionalPayment, LocalDate date){
        double balance=Roundingdecimal(loanAmount-additionalPayment);
        element.add(new PaymentElement(null,null,null,null,null,null,String.format("%.2f",balance)));
        double PMT = Roundingdecimal(Math.abs(FinanceLib.pmt(interestRate/12.00, numberOfYears*12.00, loanAmount, 0, false)));

        int paymentinitial=0;
        double lowestbalance=-0.01;
        while(balance>0){
            double interest=Roundingdecimal(balance*interestRate/12.00);
            double principal;
            double payment;
            if(Roundingdecimal(PMT-(interest+balance))>=lowestbalance){
                payment=Roundingdecimal(interest+balance);
                principal=balance;
                balance=0;
            }else{
            	payment=PMT;
                principal=Roundingdecimal(PMT-interest);
                balance=Roundingdecimal(balance-principal);
            }
            paymentinitial++;
            element.add(
                    new PaymentElement(Integer.toString(paymentinitial),date.toString(),String.format("%.2f",payment),null,String.format("%.2f",interest),String.format("%.2f",principal),String.format("%.2f",balance)));
            date=date.plusMonths(1);
        }
        return element;
    }
    
    

    public static class PaymentElement {
        private final SimpleStringProperty paymentinitial;
        private final SimpleStringProperty ad_payment;
        private final SimpleStringProperty interest;
        private final SimpleStringProperty principle;
        private final SimpleStringProperty balance;
        private final SimpleStringProperty date;
        private final SimpleStringProperty payment;

        public PaymentElement(String paymentinitial, String ad_payment, String interest, String principle, String balance, String date, String payment) {
            this.paymentinitial = new SimpleStringProperty(paymentinitial);
            this.ad_payment = new SimpleStringProperty(ad_payment);
            this.interest = new SimpleStringProperty(interest);
            this.principle = new SimpleStringProperty(principle);
            this.balance = new SimpleStringProperty(balance);
            this.date = new SimpleStringProperty(date);
            this.payment = new SimpleStringProperty(payment);
        }

   
        public String Getpaymentinitial() {
            return this.paymentinitial.get();
        }

        public void Setpaymentinitial(String paymentinitial) {
            this.paymentinitial.set(paymentinitial);
        }

        public String GetAd_Payment() {
            return this.ad_payment.get();
        }

        public void SetAd_Payment(String ad_payment) {
            this.ad_payment.set(ad_payment);
        }

        public String GetInterest() {
            return this.interest.get();
        }

        public void SetInterest(String interest) {
            this.interest.set(interest);
        }

        public String GetPrinciple() {
            return this.principle.get();
        }

        public void SetPrinciple(String principle) {
            this.principle.set(principle);
        }

        public String GetBalance() {
            return this.balance.get();
        }

        public void SetBalance(String balance) {
            this.balance.set(balance);
        }
        public String GetDate() {
            return this.date.get();
        }

        public void SetDate(String date) {
            this.date.set(date);
        }

        public String GetPayment() {
            return this.payment.get();
        }

        public void SetPayment(String payment) {
            this.payment.set(payment);
        }
    }
}