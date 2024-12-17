package DB;
import java.sql.*;

public class UserDB {
    public static User getUserDataByEmail(String gmail) {
        User user = null; 
        try (Connection conn = DbConnection.getConnection()) { 
            if (conn != null) {
                String query = "SELECT * FROM user WHERE gmail = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, gmail);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            user = new User(
                                rs.getInt("id"),
                                rs.getString("gmail"),
                                rs.getString("name"),
                                rs.getString("gender"),
                                rs.getLong("nomorhp"),
                                rs.getString("alamat")
                            );
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static boolean updateUserData(String oldEmail, String name, String newEmail, String gender, String phone, String address) {
        try (Connection conn = DbConnection.getConnection()) {
            if (conn != null) {
                String query = "UPDATE user SET name = ?, gmail = ?, gender = ?, nomorhp = ?, alamat = ? WHERE gmail = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, name);
                    stmt.setString(2, newEmail); 
                    stmt.setString(3, gender);
                    stmt.setString(4, phone);
                    stmt.setString(5, address);
                    stmt.setString(6, oldEmail); 
    
                    int rowsUpdated = stmt.executeUpdate();
                    return rowsUpdated > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isEmailExisting(String email, String currentUserEmail) {
        if (email == null || email.trim().isEmpty()) {
            return false; 
        }
    
        String query = "SELECT COUNT(*) FROM user WHERE gmail = ? AND gmail != ?";
        try (Connection conn = DbConnection.getConnection()) {
            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, email);
                    stmt.setString(2, currentUserEmail); 
    
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            return rs.getInt(1) > 0;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }

    public static boolean addUser(String gmail, String name, String password, String gender, long nomorHp, String alamat) {
        String query = "INSERT INTO user (gmail, name, password, gender, nomorhp, alamat) VALUES (?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setString(1, gmail);
            stmt.setString(2, name);
            stmt.setString(3, password);
            stmt.setString(4, gender);
            stmt.setLong(5, nomorHp);
            stmt.setString(6, alamat);
    
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; 
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
