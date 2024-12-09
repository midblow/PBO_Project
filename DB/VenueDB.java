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

            // Debugging: Log apakah koneksi berhasil
            System.out.println("Koneksi ke database berhasil.");

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
}
