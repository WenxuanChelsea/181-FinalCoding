package app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import org.apache.poi.ss.formula.functions.FinanceLib;
import java.time.LocalDate;

public class LoanCaculator {
	private final ObservableList<PaymentList> item = FXCollections.observableArrayList();
    private double totalpayment;
    private double totalinterest;
    
    
    public static double Roundingdecimal(double value) {
        return ((double)Math.round(value*100))/100;
    }
    
    public ObservableList<PaymentList> CalculatePayment(double loanAmount, double interestRate, double numberOfYears, double additionalPayment, LocalDate date){
        double balance=Roundingdecimal(loanAmount-additionalPayment);
        item.add(new PaymentList(null,null,null,null,null,null,String.format("%.2f",balance)));
        double PMT = Roundingdecimal(Math.abs(FinanceLib.pmt(interestRate/12.00, numberOfYears*12.00, loanAmount, 0, false)));

        int paymentinitial=0;
        double lowestbalance=-0.01;
        while(balance>0){
            double interest=Roundingdecimal(balance*interestRate/12.00);
            double principal;
            double payment;
            if(Roundingdecimal(PMT-(interest+balance))>=lowestbalance){
                principal=balance;
                payment=Roundingdecimal(interest+balance);
                balance=0;
            }else{
                principal=Roundingdecimal(PMT-interest);
                payment=PMT;
                balance=Roundingdecimal(balance-principal);
            }
            paymentinitial++;
            item.add(
                    new PaymentList(Integer.toString(paymentinitial),date.toString(),String.format("%.2f",payment),null,String.format("%.2f",interest),String.format("%.2f",principal),String.format("%.2f",balance)));
            date=date.plusMonths(1);
        }
        return item;
    }
    
    

    public static class PaymentList {
        private final SimpleStringProperty paymentinitial;
        private final SimpleStringProperty date;
        private final SimpleStringProperty payment;
        private final SimpleStringProperty additionalPayment;
        private final SimpleStringProperty interest;
        private final SimpleStringProperty principle;
        private final SimpleStringProperty balance;

        public PaymentList(String paymentinitial, String date, String payment, String additionalPayment, String interest, String principle, String balance) {
            this.paymentinitial = new SimpleStringProperty(paymentinitial);
            this.date = new SimpleStringProperty(date);
            this.payment = new SimpleStringProperty(payment);
            this.additionalPayment = new SimpleStringProperty(additionalPayment);
            this.interest = new SimpleStringProperty(interest);
            this.principle = new SimpleStringProperty(principle);
            this.balance = new SimpleStringProperty(balance);
        }

    public double GetTotalPayment() {
        totalpayment=0;
        for(int i=0;i<item.size();i++){
            totalpayment+=Double.parseDouble(item.get(i).GetPayment());
        }
        totalpayment=Roundingdecimal(totalpayment);
        return totalpayment;
    }

    public double GetTotalInterest() {
        totalinterest=0;
        for(int i=0;i<item.size();i++){
            totalinterest+=Double.parseDouble(item.get(i).GetInterest());
        }
        totalinterest=Roundingdecimal(totalinterest);
        return totalinterest;
    }

  

    public static double Roundingdecimal(double value) {
        return ((double)Math.round(value*100))/100;
    }

    public static class PaymentList {
        private final SimpleStringProperty paymentinitial;
        private final SimpleStringProperty date;
        private final SimpleStringProperty payment;
        private final SimpleStringProperty additionalPayment;
        private final SimpleStringProperty interest;
        private final SimpleStringProperty principle;
        private final SimpleStringProperty balance;

        public PaymentList(String paymentinitial, String date, String payment, String additionalPayment, String interest, String principle, String balance) {
            this.paymentinitial = new SimpleStringProperty(paymentinitial);
            this.date = new SimpleStringProperty(date);
            this.payment = new SimpleStringProperty(payment);
            this.additionalPayment = new SimpleStringProperty(additionalPayment);
            this.interest = new SimpleStringProperty(interest);
            this.principle = new SimpleStringProperty(principle);
            this.balance = new SimpleStringProperty(balance);
        }

        public String Getpaymentinitial() {
            return this.paymentinitial.get();
        }

        public void Setpaymentinitial(String paymentinitial) {
            this.paymentinitial.set(paymentinitial);
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

        public String GetAdditionalPayment() {
            return this.additionalPayment.get();
        }

        public void SetAdditionalPayment(String additionalPayment) {
            this.additionalPayment.set(additionalPayment);
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
    }
}
}