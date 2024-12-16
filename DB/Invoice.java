package DB;

import java.time.LocalDate;

public class Invoice {
    private int idInvoice;
    private int bookingId;
    private LocalDate date;
    private double totalAmount;
    private double serviceFee;

    // Constructor
    public Invoice(int idInvoice, int bookingId, LocalDate date, double totalAmount, double serviceFee) {
        this.idInvoice = idInvoice;
        this.bookingId = bookingId;
        this.date = date;
        this.totalAmount = totalAmount;
        this.serviceFee = serviceFee;
    }

    // Getter and Setter
    public int getIdInvoice() { return idInvoice; }
    public void setIdInvoice(int idInvoice) { this.idInvoice = idInvoice; }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public double getServiceFee() { return serviceFee; }
    public void setServiceFee(double serviceFee) { this.serviceFee = serviceFee; }
}
