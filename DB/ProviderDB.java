package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProviderDB {

    // Method untuk mengambil semua provider
    public static List<Provider> getAllProviders() {
        List<Provider> providers = new ArrayList<>();
        String query = "SELECT * FROM provider";

        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Provider provider = new Provider(
                        rs.getInt("id_provider"),
                        rs.getString("gmail"),
                        rs.getString("username"),
                        rs.getString("lembaga"),
                        rs.getString("password"),
                        rs.getLong("nomorhp"),
                        rs.getString("alamat")
                );
                providers.add(provider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return providers;
    }

    // Method untuk mengambil provider berdasarkan ID
    public static Provider getProviderByEmail(String gmail) {
        Provider provider = null;
        try (Connection conn = DbConnection.getConnection()) {
            if (conn != null) {
                String query = "SELECT * FROM provider WHERE gmail = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, gmail);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            provider = new Provider(
                                rs.getInt("id_provider"),
                                rs.getString("gmail"),
                                rs.getString("username"),
                                rs.getString("lembaga"),
                                rs.getString("password"),
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
        return provider;
    }
    
    // Method untuk menambahkan provider baru
    public static boolean addProvider(String gmail, String username, String lembaga, String password, long nomorHp, String alamat) {
        String query = "INSERT INTO provider (gmail, username, lembaga, password, nomorhp, alamat) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, gmail);
            stmt.setString(2, username);
            stmt.setString(3, lembaga);
            stmt.setString(4, password);
            stmt.setLong(5, nomorHp);
            stmt.setString(6, alamat);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method untuk memperbarui provider berdasarkan ID
    public static boolean updateProvider(String oldEmail, String username, String newEmail, String lembaga, String nomorHp, String alamat) {
        try (Connection conn = DbConnection.getConnection()) {
            if (conn != null) {
                String query = "UPDATE provider SET  username = ?, gmail = ?, lembaga = ?, nomorhp = ?, alamat = ? WHERE gmail = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, username);
                    stmt.setString(2, newEmail); // Email baru
                    stmt.setString(3, lembaga);
                    stmt.setString(4, nomorHp);
                    stmt.setString(5, alamat);
                    stmt.setString(6, oldEmail); // Email lama
    
                    int rowsUpdated = stmt.executeUpdate();
                    return rowsUpdated > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isEmailExisting(String email, String currentProviderEmail) {
        if (email == null || email.trim().isEmpty()) {
            return false; // Jika email kosong, anggap belum ada
        }
    
        String query = "SELECT COUNT(*) FROM provider WHERE gmail = ? AND gmail != ?";
        try (Connection conn = DbConnection.getConnection()) {
            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, email); // Email baru yang ingin dicek
                    stmt.setString(2, currentProviderEmail); // Email pengguna saat ini (dikecualikan)
    
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            return rs.getInt(1) > 0; // Jika ada lebih dari 0 baris, email sudah ada
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Jika terjadi error atau tidak ada hasil
    }

    // Method untuk menghapus provider berdasarkan ID
    public static boolean deleteProvider(int idProvider) {
        String query = "DELETE FROM provider WHERE id_provider = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idProvider);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}