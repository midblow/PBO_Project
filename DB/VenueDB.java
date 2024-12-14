package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VenueDB {

    public static List<Venue> getAllVenues() {
        List<Venue> venues = new ArrayList<>();
        
        try (Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM venue")) {

            while (rs.next()) {
                int idVenue = rs.getInt("id_venue");
                String namaVenue = rs.getString("nama_venue");
                String deskripsiFasilitas = rs.getString("deskripsi_fasilitas");
                String alamat = rs.getString("alamat");
                String penanggungJawab = rs.getString("penanggung_jawab");
                int kapasitas = rs.getInt("kapasitas");
                int harga = rs.getInt("harga");
                String kota = rs.getString("kota");
                String gambar = rs.getString("gambar");
                String jenisInstansi = rs.getString("jenis_instansi");
                int idProvider = rs.getInt("id_provider");
                boolean mainVenue = rs.getBoolean("main_venue");

                // Debugging: Log data venue yang dibaca
                // System.out.println("Membaca venue: " + namaVenue + " di " + alamat);

                Venue venue = new Venue(idVenue, namaVenue, deskripsiFasilitas, alamat, penanggungJawab,
                        kapasitas, harga, kota, gambar, jenisInstansi, idProvider, mainVenue);
                venues.add(venue);
            }

            // Cek jumlah venue yang berhasil diambil
            // System.out.println("Jumlah venue yang ditemukan: " + venues.size());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return venues;
    }
    
    public static Venue getVenueById(int idVenue) {
        Venue venue = null;
        String query = "SELECT * FROM venue WHERE id_venue = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, idVenue);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    venue = new Venue(
                        rs.getInt("id_venue"),
                        rs.getString("nama_venue"),
                        rs.getString("deskripsi_fasilitas"),
                        rs.getString("alamat"),
                        rs.getString("penanggung_jawab"),
                        rs.getInt("kapasitas"),
                        rs.getInt("harga"),
                        rs.getString("kota"),
                        rs.getString("gambar"),
                        rs.getString("jenis_instansi"),
                        rs.getInt("id_provider"),
                        rs.getBoolean("main_venue")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venue;
    }

    public static List<Venue> getVenuesByProvider(int idProvider) {
        List<Venue> venues = new ArrayList<>();
        
        String query = "SELECT * FROM venue WHERE id_provider = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, idProvider);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venue venue = new Venue(
                        rs.getInt("id_venue"),
                        rs.getString("nama_venue"),
                        rs.getString("deskripsi_fasilitas"),
                        rs.getString("alamat"),
                        rs.getString("penanggung_jawab"),
                        rs.getInt("kapasitas"),
                        rs.getInt("harga"),
                        rs.getString("kota"),
                        rs.getString("gambar"),
                        rs.getString("jenis_instansi"),
                        rs.getInt("id_provider"),
                        rs.getBoolean("main_venue")
                    );
                    venues.add(venue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venues;
    }

}
