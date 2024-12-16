package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class InvoiceDB {

    // Method utama untuk membuat invoice
    public static boolean createInvoice(int bookingId, int venueId) {
        // Step 1: Ambil data start_date dan end_date dari booking
        LocalDate[] dates = getStartAndEndDate(bookingId);
        if (dates == null) {
            System.out.println("Tanggal booking tidak ditemukan.");
            return false;
        }

        LocalDate startDate = dates[0];
        LocalDate endDate = dates[1];

        // Step 2: Hitung lama waktu (jumlah hari)
        int lamaWaktu = calculateLamaWaktu(startDate, endDate);

        // Step 3: Ambil harga per hari dari venue
        double hargaPerHari = getHargaPerHari(venueId);
        if (hargaPerHari <= 0) {
            System.out.println("Harga venue tidak valid.");
            return false;
        }

        // Step 4: Hitung total_amount
        double serviceFee = 50000.0; // Static Service Fee
        double totalAmount = (hargaPerHari * lamaWaktu) + serviceFee;

        // Step 5: Simpan invoice ke database
        return saveInvoice(bookingId, totalAmount, serviceFee);
    }

    // Helper: Ambil start_date dan end_date berdasarkan bookingId
    private static LocalDate[] getStartAndEndDate(int bookingId) {
        String query = "SELECT start_date, end_date FROM booking WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new LocalDate[] {
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate()
                    };
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Helper: Ambil harga venue dari tabel venue
    private static double getHargaPerHari(int venueId) {
        String query = "SELECT harga FROM venue WHERE id_venue = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, venueId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("harga");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    // Helper: Hitung lama waktu antara dua tanggal
    private static int calculateLamaWaktu(LocalDate startDate, LocalDate endDate) {
        return (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    // Helper: Simpan invoice ke database
    private static boolean saveInvoice(int bookingId, double totalAmount, double serviceFee) {
        String query = """
            INSERT INTO invoice (booking_id, date, total_amount, service_fee) 
            VALUES (?, CURRENT_DATE, ?, ?)
        """;
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            stmt.setDouble(2, totalAmount);
            stmt.setDouble(3, serviceFee);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Object[] getInvoiceDetails(int bookingId) {
        String query = """
            SELECT i.id_invoice, i.date, v.nama_venue, p.lembaga, v.penanggung_jawab,
                   DATEDIFF(b.end_date, b.start_date) + 1 AS lama_waktu, v.harga, i.service_fee, i.total_amount,
                   u.name, u.gmail, u.nomorhp
            FROM invoice i
            JOIN booking b ON i.booking_id = b.id
            JOIN venue v ON b.id_venue = v.id_venue
            JOIN provider p ON v.id_provider = p.id_provider
            JOIN user u ON b.user_id = u.id
            WHERE i.booking_id = ?
        """;
    
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Object[]{
                    rs.getString("id_invoice"), rs.getString("date"), rs.getString("nama_venue"),
                    rs.getString("lembaga"), rs.getString("penanggung_jawab"), rs.getInt("lama_waktu"),
                    rs.getDouble("harga"), rs.getDouble("service_fee"), rs.getDouble("total_amount"),
                    rs.getString("name"), rs.getString("gmail"), rs.getString("nomorhp")
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
