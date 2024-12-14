package DB;
import java.sql.*;

public class UserDB {
    // Metode untuk mengambil data pengguna berdasarkan email
    public static String[] getUserDataByEmail(String email) {
        String[] userData = new String[5]; // Nama, Email, Gender, Nomor HP, Alamat
        try (Connection conn = DbConnection.getConnection()) { // Asumsi ada kelas DbConnection untuk koneksi database
            if (conn != null) {
                String query = "SELECT name, gmail, gender, nomorhp, alamat FROM user WHERE gmail = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, email);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            userData[0] = rs.getString("name");
                            userData[1] = rs.getString("gmail");
                            userData[2] = rs.getString("gender");
                            userData[3] = rs.getString("nomorhp");
                            userData[4] = rs.getString("alamat");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userData;
    }

    public static boolean updateUserData(String email, String name, String gender, String phone, String address) {
        try (Connection conn = DbConnection.getConnection()) {
            if (conn != null) {
                String query = "UPDATE user SET name = ?, gender = ?, nomorhp = ?, alamat = ? WHERE gmail = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, name);
                    stmt.setString(2, gender);
                    stmt.setString(3, phone);
                    stmt.setString(4, address);
                    stmt.setString(5, email);
    
                    int rowsUpdated = stmt.executeUpdate();
                    return rowsUpdated > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
