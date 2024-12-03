package User;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

public class home_user extends JFrame {
    private JPanel mainPanel;
    private JLabel profileLabel;
    private JPanel venuePanel;
    private List<Venue> venues;

    public home_user() {
        setTitle("Edoroli - Reservasi Venue Online");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Profile Panel
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        profileLabel = createProfileImage("Erwin");  // Use updated method without "name"
        profilePanel.add(profileLabel);
        mainPanel.add(profilePanel, BorderLayout.NORTH);

        // Navigation
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new FlowLayout());
        JButton allStayButton = new JButton("All Stay");
        JButton venueButton = new JButton("Venue");
        JButton profileButton = new JButton("Profile");
        navPanel.add(allStayButton);
        navPanel.add(venueButton);
        navPanel.add(profileButton);
        mainPanel.add(navPanel, BorderLayout.CENTER);

        // Venue List
        venuePanel = new JPanel();
        venuePanel.setLayout(new GridLayout(0, 2, 10, 10));
        mainPanel.add(new JScrollPane(venuePanel), BorderLayout.SOUTH);
        
        // Fetch venues from database
        fetchVenues();

        setVisible(true);
    }

    private JLabel createProfileImage(String userName) {
        // Ambil path gambar dari database
        String userPath = getUserImagePathFromDatabase(userName);  
        String imagePath = (userPath != null && !userPath.isEmpty()) ? userPath : "asset/Zoro.jpg";  // Gunakan Zoro.jpg jika tidak ada
    
        // Periksa apakah gambar dapat ditemukan di classpath
        URL imageUrl = getClass().getClassLoader().getResource(imagePath);
    
        // Jika tidak ditemukan, log dan gunakan gambar default
        if (imageUrl == null) {
            System.out.println("Image not found: " + imagePath);
            imageUrl = getClass().getClassLoader().getResource("asset/Zoro.jpg");
        }
    
        // Jika imageUrl masih null setelah percakapan di atas, maka benar-benar tidak ada gambar di classpath
        if (imageUrl == null) {
            System.out.println("Gambar tidak ditemukan di classpath, pastikan asset ada dan bisa diakses.");
            return new JLabel("No Image Found");  // Tampilkan label alternatif jika gambar tidak ditemukan
        }
    
        // Membuat ImageIcon untuk gambar profil
        ImageIcon icon = new ImageIcon(imageUrl);
    
        // Konversikan gambar menjadi bentuk lingkaran menggunakan ImageProfile.createCircular()
        Image circularImage = ImageProfile.createCircular(icon.getImage(), 100); // Diameter 100px
    
        // Buat label baru hanya dengan gambar lingkaran (tanpa teks)
        ImageIcon circularIcon = new ImageIcon(circularImage);
        JLabel label = new JLabel(circularIcon);  // Tidak perlu teks
        label.setPreferredSize(new Dimension(100, 100));  // Tentukan ukuran tetap untuk label
        label.setHorizontalAlignment(JLabel.CENTER);  // Posisi gambar di tengah
    
        return label;
    }
    
    
    
    private String getUserImagePathFromDatabase(String userName) {
        String imagePath = null;
        try (Connection conn = DbConnection.getConnection()) {
            String query = "SELECT user_path FROM user WHERE name = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, userName);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        imagePath = rs.getString("user_path");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imagePath;
    }
    
    private void fetchVenues() {
        try (Connection conn = DbConnection.getConnection()) {  // Use DbConnection class here
            String query = "SELECT * FROM venue WHERE main_venue = 1 LIMIT 10";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                venues = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id_venue");
                    String name = rs.getString("nama_venue");
                    String city = rs.getString("kota");
                    String image = rs.getString("gambar");
                    venues.add(new Venue(id, name, city, image));
                }
                displayVenues();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayVenues() {
        venuePanel.removeAll();
        for (Venue venue : venues) {
            JPanel venueCard = new JPanel();
            venueCard.setLayout(new BorderLayout());
            venueCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel venueImage = new JLabel(new ImageIcon(getClass().getResource("/path_to_images/" + venue.getImage())));
            JLabel venueName = new JLabel(venue.getName());
            venueName.setFont(new Font("Arial", Font.BOLD, 16));

            JLabel venueCity = new JLabel(venue.getLoc());
            venueCity.setFont(new Font("Arial", Font.ITALIC, 12));

            venueCard.add(venueImage, BorderLayout.CENTER);
            venueCard.add(venueName, BorderLayout.NORTH);
            venueCard.add(venueCity, BorderLayout.SOUTH);

            venuePanel.add(venueCard);
        }
        venuePanel.revalidate();
        venuePanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new home_user());
    }
}
