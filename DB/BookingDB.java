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

    public static boolean saveBooking(int venueId, LocalDate startDate, LocalDate endDate, String paymentMethod, int userId) {
        String query = """
            INSERT INTO booking (id_venue, start_date, end_date, metode_pembayaran, status, user_id) 
            VALUES (?, ?, ?, ?, 'waiting', ?)
        """;
    
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
    
            stmt.setInt(1, venueId);
            stmt.setDate(2, Date.valueOf(startDate));
            stmt.setDate(3, Date.valueOf(endDate));
            stmt.setString(4, paymentMethod);
            stmt.setInt(5, userId);
    
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int bookingId = generatedKeys.getInt(1);
    
                        return InvoiceDB.createInvoice(bookingId, venueId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Object[]> getAllBookingsForUser(int userId) {
        List<Object[]> bookings = new ArrayList<>();
        String query = """
            SELECT b.id, 
                   v.nama_venue, 
                   b.start_date, b.end_date, 
                   p.lembaga, p.gmail
            FROM booking b
            INNER JOIN venue v ON b.id_venue = v.id_venue
            INNER JOIN provider p ON v.id_provider = p.id_provider
            WHERE b.user_id = ? AND b.status = 'confirmed';
        """;
    
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, userId);
    
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    bookings.add(new Object[]{
                        rs.getInt("id"),               
                        rs.getString("nama_venue"),           
                        rs.getDate("start_date").toLocalDate(), 
                        rs.getDate("end_date").toLocalDate(),   
                        rs.getString("lembaga"),              
                        rs.getString("gmail")                 
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return bookings;
    }

    public static List<Object[]> getBookingsForProvider(int providerId) {
        List<Object[]> bookings = new ArrayList<>();
        String query = """
            SELECT b.id, v.nama_venue, b.start_date, b.end_date, u.name AS user_name, u.gmail
            FROM booking b
            INNER JOIN venue v ON b.id_venue = v.id_venue
            INNER JOIN user u ON b.user_id = u.id
            WHERE v.id_provider = ?;
        """;
    
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, providerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    bookings.add(new Object[]{
                        rs.getInt("id"),              
                        rs.getString("nama_venue"),   
                        rs.getDate("start_date").toLocalDate(), 
                        rs.getDate("end_date").toLocalDate(),  
                        rs.getString("user_name"),    
                        rs.getString("gmail")         
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    public static boolean updateBookingStatus(int bookingId, String newStatus) {
        String updateStatusQuery = "UPDATE booking SET status = ? WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateStatusQuery)) {
    
            stmt.setString(1, newStatus); 
            stmt.setInt(2, bookingId); 
    
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteBookingById(int bookingId) {
        Connection conn = null;
        PreparedStatement deleteInvoiceStmt = null;
        PreparedStatement deleteBookingStmt = null;
    
        try {
            conn = DbConnection.getConnection();
            conn.setAutoCommit(false); 
    
            String deleteInvoiceSQL = "DELETE FROM invoice WHERE booking_id = ?";
            deleteInvoiceStmt = conn.prepareStatement(deleteInvoiceSQL);
            deleteInvoiceStmt.setInt(1, bookingId);
            deleteInvoiceStmt.executeUpdate();
    
            String deleteBookingSQL = "DELETE FROM booking WHERE id = ?";
            deleteBookingStmt = conn.prepareStatement(deleteBookingSQL);
            deleteBookingStmt.setInt(1, bookingId);
            deleteBookingStmt.executeUpdate();
    
            conn.commit(); 
            System.out.println("Booking dan invoice yang berelasi berhasil dihapus.");
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); 
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (deleteInvoiceStmt != null) deleteInvoiceStmt.close();
                if (deleteBookingStmt != null) deleteBookingStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String getBookingStatusById(int bookingId) {
        String status = null;
        String query = "SELECT status FROM booking WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static Object[] getInvoiceByBookingId(int bookingId) {
        String query = "SELECT id_invoice, date, total_amount, service_fee FROM invoice WHERE booking_id = ?";
        try (Connection conn = DbConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, bookingId); 
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                return new Object[]{
                    rs.getInt("id_invoice"),
                    rs.getDate("date"),
                    rs.getDouble("total_amount"),
                    rs.getDouble("service_fee")
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

    public static boolean isBookingIdValid(int bookingId) {
        String query = "SELECT COUNT(*) FROM booking WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getVenueIdByBookingId(int bookingId) {
        String query = "SELECT id_venue FROM booking WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_venue");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; 
    }

    public static String getMetodePembayaranByBookingId(int bookingId) {
        String metodePembayaran = null;
        String query = "SELECT metode_pembayaran FROM booking WHERE id = ?";
        
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    metodePembayaran = rs.getString("metode_pembayaran");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return metodePembayaran; 
    }

    public static boolean isDateOverlap(int venueId, LocalDate startDate, LocalDate endDate) {
        String query = """
            SELECT COUNT(*) FROM booking 
            WHERE id_venue = ? AND status = 'confirmed'
            AND (start_date <= ? AND end_date >= ?)
        """;
    
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, venueId);
            stmt.setDate(2, Date.valueOf(endDate));
            stmt.setDate(3, Date.valueOf(startDate));
    
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }

}


