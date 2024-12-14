package Provider;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeProvider {

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
        JButton homeButton = new JButton("Home");
        styleNavigationButton(homeButton, new Color(12, 34, 64));  // Updated color

        // Membuat panel untuk menambahkan garis bawah pada "Beranda"
        JPanel homePanel = new JPanel(new BorderLayout());
        homePanel.setOpaque(false); // Panel transparan
        homePanel.add(homeButton, BorderLayout.CENTER);
        homePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 34, 64))); // Garis bawah

        navPanel.add(homePanel);

        JButton bookingButton = new JButton("Booking Confirmation");
        styleNavigationButton(bookingButton, new Color(255, 140, 0));  // Updated color
        navPanel.add(bookingButton);

        JButton profileButton = new JButton("Profile");
        styleNavigationButton(profileButton, new Color(255, 140, 0));  // Updated color
        navPanel.add(profileButton);

        mainPanel.add(navPanel);

        // Tambahkan ActionListener untuk Booking
        bookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); 
                BookingConfirm.showBooking(); 
            }
        });

        // Section Rekomendasi Venue
        JPanel venueTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Menggunakan FlowLayout LEFT
        venueTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Tambahkan margin

        JLabel venueTitle = new JLabel("My Main Venue");
        venueTitle.setFont(new Font("Poppins", Font.BOLD, 25));
        venueTitle.setHorizontalAlignment(SwingConstants.LEFT); // Ratakan ke kiri
        venueTitlePanel.add(venueTitle);
        mainPanel.add(venueTitlePanel);

        JPanel venuePanel = new JPanel(new GridLayout(1, 4, 20, 20));
        venuePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margin

        // Tambahkan venue card tanpa lokasi
        JPanel venueCard = createVenueCard("Taman Sangkareang", null, "asset/Sangkareang.jpg", false);

        // Dapatkan komponen gambar dari card
        JLabel imageLabel = (JLabel) venueCard.getComponent(0); // Mengambil gambar pertama dalam card
        ImageIcon originalIcon = (ImageIcon) imageLabel.getIcon();  // Mendapatkan ikon gambar asli

        // Resize gambar agar memenuhi lebar panel
        Image img = originalIcon.getImage();
        Image resizedImg = img.getScaledInstance(1200, -1, Image.SCALE_SMOOTH); // Menyesuaikan lebar saja
        imageLabel.setIcon(new ImageIcon(resizedImg)); // Menetapkan gambar yang sudah diresize ke label

        venuePanel.add(venueCard);

        mainPanel.add(venuePanel);
                
        // List
        JPanel listVenueTitlePanel = new JPanel(new BorderLayout()); // Menggunakan BorderLayout
        listVenueTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Tambahkan margin

        // Label "List Venue"
        JLabel listVenueTitle = new JLabel("List Venue");
        listVenueTitle.setFont(new Font("Poppins", Font.BOLD, 25));
        listVenueTitle.setHorizontalAlignment(SwingConstants.LEFT); // Ratakan ke kiri
        listVenueTitlePanel.add(listVenueTitle, BorderLayout.WEST); // Tambahkan ke sisi kiri panel

        // Button "Add Venue" dengan ikon dan teks
        ImageIcon addVenueIcon = resizeIcon(new ImageIcon("asset/addVenue.png"), 20, 20);  // Menyesuaikan ukuran ikon
        JButton addVenueButton = new JButton("Add Venue", addVenueIcon);

        // Mengatur font dan warna teks tombol
        addVenueButton.setFont(new Font("Poppins", Font.PLAIN, 16));
        addVenueButton.setForeground(new Color(255, 140, 0)); // Warna teks putih
        addVenueButton.setBackground(new Color(255, 140, 0)); // Warna latar belakang tombol
        addVenueButton.setOpaque(true);  // Agar background terlihat
        addVenueButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Margin untuk oval

        // Membuat tombol berbentuk oval
        addVenueButton.setFocusPainted(false);
        addVenueButton.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0), 2, true));  // Border untuk oval
        addVenueButton.setContentAreaFilled(false);

        listVenueTitlePanel.add(addVenueButton, BorderLayout.EAST); // Tambahkan tombol di sisi kanan panel

        mainPanel.add(listVenueTitlePanel);

        JPanel venueGridPanel = new JPanel(new GridLayout(0, 4, 20, 20)); // 0 baris berarti jumlah baris akan disesuaikan otomatis
        venueGridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margin

        // Tambahkan venue card dengan lokasi
        venueGridPanel.add(createVenueCard("Taman Sangkareang", "Mataram", "asset/Sangkareang.jpg",true));
        venueGridPanel.add(createVenueCard("Pantai Senggigi", "Mataram", "asset/Pantai_Senggigi.jpg", true));
        venueGridPanel.add(createVenueCard("Grand Imperial", "Mataram", "asset/Grand_Imperial.jpg", true));
        venueGridPanel.add(createVenueCard("Hotel Lombok Raya", "Mataram", "asset/Hotel_Lombok_Raya.jpg", true));
        venueGridPanel.add(createVenueCard("Islamic Center", "Mataram", "asset/Islamic_Center.jpg", true));
        venueGridPanel.add(createVenueCard("Senggigi Hotel", "Mataram", "asset/Senggigi_Hotel.jpg", true));
        venueGridPanel.add(createVenueCard("Prime Park", "Mataram", "asset/Prime_Park.jpg", true));
        venueGridPanel.add(createVenueCard("Narmada Convention Hall", "Mataram", "asset/Narmada_Convention_Hall.jpg", true));
        venueGridPanel.add(createVenueCard("Gelanggang Pemuda", "Mataram", "asset/Gelanggang_Pemuda.png", true));
        mainPanel.add(venueGridPanel);

        // JScrollPane untuk scrolling
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Menyembunyikan scroll bar vertikal
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Menyembunyikan scroll bar horizontal
        frame.add(scrollPane, BorderLayout.CENTER);
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Frame otomatis diperbesar
        frame.setVisible(true);        
    }

    private static JPanel createVenueCard(String name, String location, String imagePath, boolean showLocation) {
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
        nameLabel.setFont(new Font("Poppins", Font.BOLD, 16)); // Memperbesar font nama venue
        nameLabel.setPreferredSize(new Dimension(300, 50)); // Menyediakan ruang lebih agar nama terlihat dengan baik
        namePanel.add(nameLabel);
        card.add(namePanel, BorderLayout.NORTH); // Menempatkan Nama Venue di bagian atas

        if (showLocation) {
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
        }

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