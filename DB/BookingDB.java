package DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDB {

    // Method untuk mendapatkan semua booking berdasarkan id venue
    public static List<Booking> getBookingsForVenue(int venueId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM booking WHERE id_venue = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, venueId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Booking booking = new Booking();
                    booking.setId(rs.getInt("id"));
                    booking.setUserId(rs.getInt("user_id"));
                    booking.setVenueId(rs.getInt("id_venue"));
                    booking.setStartDate(rs.getDate("start_date").toLocalDate());
                    booking.setEndDate(rs.getDate("end_date").toLocalDate());
                    booking.setStatus(rs.getString("status"));
                    bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // Method untuk menyimpan booking baru ke database
    public static boolean saveBooking(int venueId, LocalDate startDate, LocalDate endDate, String paymentMethod, int userId) {
        String query = "INSERT INTO booking (id_venue, start_date, end_date, metode_pembayaran, status, user_id) VALUES (?, ?, ?, ?, 'waiting', ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, venueId);
            stmt.setDate(2, Date.valueOf(startDate));
            stmt.setDate(3, Date.valueOf(endDate));
            stmt.setString(4, paymentMethod);
            stmt.setInt(5, userId);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

