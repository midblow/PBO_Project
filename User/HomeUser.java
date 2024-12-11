package User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import DB.*;

public class HomeUser {
    
    public static void main(String[] args) {
        // Frame utama
        JFrame frame = new JFrame("Revenue");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(12, 34, 64)); // #0c2240
        header.setPreferredSize(new Dimension(1200, 100)); // Tinggi header tetap

        // Panel Kiri: Logo dan Judul
        JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        leftHeader.setOpaque(false);

        // Logo
        JLabel logo = new JLabel(resizeIcon(new ImageIcon("asset/logo.png"), 50, 50));
        leftHeader.add(logo);

        // Teks "REVENUE"
        JLabel revenueLabel = new JLabel("REVENUE", SwingConstants.LEFT);
        revenueLabel.setForeground(Color.WHITE);
        revenueLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        leftHeader.add(revenueLabel);

        header.add(leftHeader, BorderLayout.WEST);

        // Ikon Profil di Sebelah Kanan
        JLabel profile = new JLabel(resizeIcon(new ImageIcon("asset/profil.png"), 50, 50));
        profile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open the Profile View when the icon is clicked
                ProfileView.showProfileView();
            }
        });
        header.add(profile, BorderLayout.EAST);

        frame.add(header, BorderLayout.NORTH);

        // Panel utama untuk konten
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Elemen disusun vertikal
        mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Panel Navigasi Menu
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
        navPanel.setOpaque(false); // Transparan
        navPanel.setPreferredSize(new Dimension(1200, 50)); // Ukuran tetap

        // Tombol navigasi
        JButton berandaButton = new JButton("Beranda");
        styleNavigationButton(berandaButton, new Color(12, 34, 64));  // Updated color

        // Membuat panel untuk menambahkan garis bawah pada "Beranda"
        JPanel berandaPanel = new JPanel(new BorderLayout());
        berandaPanel.setOpaque(false); // Panel transparan
        berandaPanel.add(berandaButton, BorderLayout.CENTER);
        berandaPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 34, 64))); // Garis bawah
        navPanel.add(berandaPanel);

        JButton venueButton = new JButton("Venue");
        styleNavigationButton(venueButton, new Color(255, 140, 0));  // Updated color
        navPanel.add(venueButton);

        // Tambahkan ActionListener untuk venueButton
        venueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Tutup HomeUser
                VenuePage.showVenuePage(); // Buka VenuePage
            }
        });

        JButton profileButton = new JButton("Profile");
        styleNavigationButton(profileButton, new Color(255, 140, 0));  // Updated color
        navPanel.add(profileButton);
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Tutup HomeUser
                UProfilePage.main(new String[]{}); // Buka VenuePage
            }
        });

        mainPanel.add(navPanel);

        // Section Rekomendasi Venue
        JPanel venueTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Menggunakan FlowLayout LEFT
        venueTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Tambahkan margin

        JLabel venueTitle = new JLabel("Rekomendasi Venue");
        venueTitle.setFont(new Font("Poppins", Font.BOLD, 23));
        venueTitle.setHorizontalAlignment(SwingConstants.LEFT); // Ratakan ke kiri
        venueTitlePanel.add(venueTitle);
        mainPanel.add(venueTitlePanel);

        // Ambil data venue dari database (hanya 6 venue pertama)
        List<Venue> venues = VenueDB.getAllVenues();
        System.out.println("Jumlah venue yang didapat: " + venues.size());
        int maxVenues = Math.min(6, venues.size()); // Hanya tampilkan maksimal 6 venue

        // Panel untuk venue yang dinamis (horizontal)
        JPanel venuePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20)); // Menggunakan FlowLayout dengan scroll horizontal
        venuePanel.setPreferredSize(new Dimension(1200, 400)); // Ukuran panel yang cukup besar
        
        // Loop untuk menampilkan venue-card dari data yang didapat
        for (int i = 0; i < maxVenues; i++) {
            Venue venue = venues.get(i);
            venuePanel.add(createVenueCard(venue.getNamaVenue(), venue.getAlamat(), venue.getGambar()));
        }
        
        // Scroll panel untuk venue (menambahkan scroll horizontal, menghilangkan vertikal)
        JScrollPane venueScrollPane = new JScrollPane(venuePanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        venueScrollPane.setPreferredSize(new Dimension(1200, 400)); // Ukuran scroll pane
        mainPanel.add(venueScrollPane);

        // Section Tata Cara Reservasi
        JPanel tataCaraTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Menggunakan FlowLayout LEFT
        tataCaraTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Tambahkan margin

        JLabel tataCaraTitle = new JLabel("Tata Cara Reservasi Venue");
        tataCaraTitle.setFont(new Font("Poppins", Font.BOLD, 23));
        tataCaraTitle.setHorizontalAlignment(SwingConstants.LEFT); // Ratakan ke kiri
        tataCaraTitlePanel.add(tataCaraTitle);
        mainPanel.add(tataCaraTitlePanel);

        JPanel tataCaraPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        tataCaraPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margin

        tataCaraPanel.add(createDetailedStepCard(
            "1. Pilih Venue",
            "Mulailah perayaan yang tak terlupakan Anda dengan memilih venue sempurna untuk merayakan momen spesial Anda.",
            "asset/1cara.jpg"
        ));
        tataCaraPanel.add(createDetailedStepCard(
            "2. Mengisi Identitas",
            "Kami ingin tahu lebih banyak tentang acara Anda. Mohon berikan kami informasi yang diperlukan untuk memastikan semuanya berjalan dengan lancar.",
            "asset/2cara.jpg"
        ));
        tataCaraPanel.add(createDetailedStepCard(
            "3. Melengkapi form SOP",
            "Kenyamanan Anda adalah prioritas kami. Mohon pastikan semuanya tersusun rapi sesuai kebutuhan Anda.",
            "asset/3cara.jpg"
        ));
        tataCaraPanel.add(createDetailedStepCard(
            "4. Melakukan Pembayaran",
            "Dengan persiapan yang teliti, saatnya membayar dan memastikan semuanya siap untuk perayaan yang tak terlupakan.",
            "asset/4cara.jpg"
        ));
        mainPanel.add(tataCaraPanel);

        // JScrollPane untuk scrolling (hanya mengaktifkan scroll horizontal di venue)
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Menghilangkan scroll horizontal
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Frame otomatis diperbesar
        frame.setVisible(true);
    }

    private static JPanel createVenueCard(String name, String location, String imagePath) {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(250, 350)); // Ukuran card venue
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    
        // Gambar Venue
        JLabel image = new JLabel(new ImageIcon(imagePath));
        image.setPreferredSize(new Dimension(250, 200)); // Set ukuran gambar lebih kecil
        card.add(image, BorderLayout.CENTER);
    
        // Panel untuk Nama Venue (diletakkan di atas lokasi, di bawah gambar)
        JPanel namePanel = new JPanel();
        namePanel.setBackground(Color.WHITE);
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Menggunakan FlowLayout agar mudah diatur
        JLabel nameLabel = new JLabel("<html><center>" + name + "</center></html>", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Memperbesar font nama venue
        nameLabel.setPreferredSize(new Dimension(250, 50)); // Menyediakan ruang lebih agar nama terlihat dengan baik
        namePanel.add(nameLabel);
        card.add(namePanel, BorderLayout.NORTH); // Menempatkan Nama Venue di bagian atas
    
        // Panel untuk Lokasi dan Ikon Lokasi
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel locationIcon = new JLabel(resizeIcon(new ImageIcon("asset/location_vanue.png"), 15, 15)); // Ikon Lokasi
        locationPanel.add(locationIcon);
    
        JLabel locationLabel = new JLabel(location, SwingConstants.LEFT);
        locationLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        locationPanel.add(locationLabel);
    
        // Menggunakan BorderLayout untuk penempatan lokasi di bagian bawah
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(locationPanel, BorderLayout.WEST);
        card.add(bottomPanel, BorderLayout.SOUTH); // Menambahkan lokasi di bawah nama venue
    
        return card;
    }
    
    private static JPanel createDetailedStepCard(String title, String description, String imagePath) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(255, 231, 207)); // #ffe7cf
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel image = new JLabel(new ImageIcon(imagePath));
        card.add(image, BorderLayout.WEST);

        JPanel detailPanel = new JPanel(new BorderLayout());
        detailPanel.setBackground(new Color(255, 231, 207));

        JLabel titleLabel = new JLabel(title, SwingConstants.LEFT);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        detailPanel.add(titleLabel, BorderLayout.NORTH);

        JLabel descriptionLabel = new JLabel("<html><p style='width:250px;'>" + description + "</p></html>");
        descriptionLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        detailPanel.add(descriptionLabel, BorderLayout.CENTER);

        card.add(detailPanel, BorderLayout.CENTER);

        return card;
    }

    private static void styleNavigationButton(JButton button, Color color) {
        button.setFont(new Font("Poppins", Font.PLAIN, 16));
        button.setForeground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
    }

    private static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}
