import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VenuePage {
    public static void showVenuePage() {
        SwingUtilities.invokeLater(() -> new VenuePage());
    }
    public VenuePage() {
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

        // Tombol Beranda tanpa garis bawah
        JButton berandaButton = new JButton("Beranda");
        styleNavigationButton(berandaButton, new Color(255, 140, 0));  // Updated color
        navPanel.add(berandaButton);

        // Tombol Venue dengan garis bawah
        JButton venueButton = new JButton("Venue");
        styleNavigationButton(venueButton, new Color(12, 34, 64));  // Updated color

        // Membuat panel untuk menambahkan garis bawah pada "Venue"
        JPanel venuePanel = new JPanel(new BorderLayout());
        venuePanel.setOpaque(false); // Panel transparan
        venuePanel.add(venueButton, BorderLayout.CENTER);
        venuePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 34, 64))); // Garis bawah

        navPanel.add(venuePanel);


        // Tombol Profile
        JButton profileButton = new JButton("Profile");
        styleNavigationButton(profileButton, new Color(255, 140, 0));  // Updated color
        navPanel.add(profileButton);

        mainPanel.add(navPanel);

        // Section Rekomendasi Venue
        JPanel venueTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Menggunakan FlowLayout LEFT
        venueTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Tambahkan margin

        JLabel venueTitle = new JLabel("Temukan venue terbaik untuk event anda");
        venueTitle.setFont(new Font("Poppins", Font.BOLD, 25));
        venueTitle.setHorizontalAlignment(SwingConstants.LEFT); // Ratakan ke kiri
        venueTitlePanel.add(venueTitle);
        mainPanel.add(venueTitlePanel);

        // Menggunakan GridLayout 2 baris dan 4 kolom untuk lebih baik dalam penataan horizontal
        JPanel venueGridPanel = new JPanel(new GridLayout(0, 4, 20, 20)); // 0 baris berarti jumlah baris akan disesuaikan otomatis
        venueGridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margin

        // Tambahkan venue card
        venueGridPanel.add(createVenueCard("Taman Sangkareang", "Mataram", "asset/Sangkareang.jpg"));
        venueGridPanel.add(createVenueCard("Pantai Senggigi", "Mataram", "asset/Pantai_Senggigi.jpg"));
        venueGridPanel.add(createVenueCard("Grand Imperial", "Mataram", "asset/Grand_Imperial.jpg"));
        venueGridPanel.add(createVenueCard("Hotel Lombok Raya", "Mataram", "asset/Hotel_Lombok_Raya.jpg"));
        venueGridPanel.add(createVenueCard("Islamic Center", "Mataram", "asset/Islamic_Center.jpg"));
        venueGridPanel.add(createVenueCard("Senggigi Hotel", "Mataram", "asset/Senggigi_Hotel.jpg"));
        venueGridPanel.add(createVenueCard("Prime Park", "Mataram", "asset/Prime_Park.jpg"));
        venueGridPanel.add(createVenueCard("Narmada Convention Hall", "Mataram", "asset/Narmada_Convention_Hall.jpg"));
        venueGridPanel.add(createVenueCard("Gelanggang Pemuda", "Mataram", "asset/Gelanggang_Pemuda.png"));
        mainPanel.add(venueGridPanel);

        // JScrollPane untuk scrolling
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  // Scroll vertikal
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Nonaktifkan scroll horizontal
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Frame otomatis diperbesar
        frame.setVisible(true);
    }

    private static JPanel createVenueCard(String name, String location, String imagePath) {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(300, 400)); // Ukuran card venue
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    
        // Gambar Venue
        JLabel image = new JLabel(new ImageIcon(imagePath));
        card.add(image, BorderLayout.CENTER);
    
        // Panel untuk Nama Venue (diletakkan di atas lokasi, di bawah gambar)
        JPanel namePanel = new JPanel();
        namePanel.setBackground(Color.WHITE);
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Menggunakan FlowLayout agar mudah diatur
        JLabel nameLabel = new JLabel("<html><center>" + name + "</center></html>", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Memperbesar font nama venue
        nameLabel.setPreferredSize(new Dimension(300, 50)); // Menyediakan ruang lebih agar nama terlihat dengan baik
        namePanel.add(nameLabel);
        card.add(namePanel, BorderLayout.NORTH); // Menempatkan Nama Venue di bagian atas
    
        // Panel untuk Lokasi dan Ikon Lokasi
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel locationIcon = new JLabel(resizeIcon(new ImageIcon("asset/location_vanue.png"), 20, 20)); // Ikon Lokasi
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

class ProfileView extends JFrame {

    public ProfileView() {
        // Set up the Profile View Frame
        setTitle("Profile");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(new Color(12, 34, 64));
        profilePanel.setLayout(new BorderLayout());

        // Create a top panel to hold the back button aligned to the left
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(12, 34, 64));
        JLabel backButton = new JLabel(new ImageIcon(new ImageIcon("asset/backProfile.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Close the profile view
            }
        });
        topPanel.add(backButton);

        // Add the top panel to the profile panel
        profilePanel.add(topPanel, BorderLayout.NORTH);

        // Center Panel for Profile Image and Info
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(12, 34, 64));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        // centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Profile picture
        JLabel profilePic = new JLabel(new ImageIcon(new ImageIcon("asset/Zoro.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        profilePic.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(profilePic);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Name label
        JLabel nameLabel = new JLabel("Zoro");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(nameLabel);

        // Add the center panel to the profile panel
        profilePanel.add(centerPanel, BorderLayout.CENTER);

        // Add the profile panel to the frame
        add(profilePanel);
        setVisible(true);
    }

    // Method to show the Profile view when the profile icon is clicked
    public static void showProfileView() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProfileView();
            }
        });
    }
}
