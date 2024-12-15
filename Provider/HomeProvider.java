package Provider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import DB.*;
import java.util.List;

public class HomeProvider {

    public static void main(String[] args) {
        // Frame utama
        JFrame frame = new JFrame("Revenue");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
                ProfileView.showProfileView(); // Navigasi ke tampilan profil
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
        styleNavigationButton(homeButton, new Color(12, 34, 64));

        JButton bookingButton = new JButton("Booking Confirmation");
        styleNavigationButton(bookingButton, new Color(255, 140, 0));

        JButton profileButton = new JButton("Profile");
        styleNavigationButton(profileButton, new Color(255, 140, 0));

        navPanel.add(homeButton);
        navPanel.add(bookingButton);
        navPanel.add(profileButton);
        mainPanel.add(navPanel);

        // Tambahkan ActionListener untuk Booking dan Profile
        bookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                BookingConfirm.showBooking();
            }
        });

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                PProfilePage.main(new String[]{});
            }
        });

        // Ambil ID provider yang login dari session
        int loggedInProviderId = Session.loggedInProviderId;

        // Ambil daftar venue dari database berdasarkan ID provider yang login
        List<Venue> venues = VenueDB.getVenuesByProvider(loggedInProviderId);

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

        // Tampilkan venue utama (misalnya main venue)
        Venue mainVenue = null;

        for (Venue venue : venues) {
            if (mainVenue == null && venue.isMainVenue()) {
                mainVenue = venue; // Simpan venue utama pertama kali
                venuePanel.add(createVenueCard(
                    venue.getNamaVenue(),
                    venue.getKota(),
                    venue.getGambar(),
                    false // Tidak perlu menampilkan lokasi
                ));
                break; // Hanya tampilkan venue utama pertama
            }
        }
        mainPanel.add(venuePanel);

        // Section List Venue
        JPanel listVenueTitlePanel = new JPanel(new BorderLayout()); // Menggunakan BorderLayout
        listVenueTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Tambahkan margin

        JLabel listVenueTitle = new JLabel("List Venue");
        listVenueTitle.setFont(new Font("Poppins", Font.BOLD, 25));
        listVenueTitle.setHorizontalAlignment(SwingConstants.LEFT); // Ratakan ke kiri
        listVenueTitlePanel.add(listVenueTitle, BorderLayout.WEST); // Tambahkan ke sisi kiri panel

        // Tombol "Add Venue"
        ImageIcon addVenueIcon = resizeIcon(new ImageIcon("asset/addVenue.png"), 20, 20);
        JButton addVenueButton = new JButton("Add Venue", addVenueIcon);
        addVenueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                AddVenue.main(new String[]{});
            }
        });

        styleNavigationButton(addVenueButton, new Color(255, 140, 0));
        listVenueTitlePanel.add(addVenueButton, BorderLayout.EAST); // Tombol di sisi kanan panel
        mainPanel.add(listVenueTitlePanel);

        JPanel venueGridPanel = new JPanel(new GridLayout(0, 4, 20, 20)); // Grid untuk daftar venue
        venueGridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margin

        // Tampilkan semua venue kecuali venue utama pertama
        for (Venue venue : venues) {
            if (mainVenue == null || venue.getIdVenue() != mainVenue.getIdVenue()) {
                venueGridPanel.add(createVenueCard(
                    venue.getNamaVenue(),
                    venue.getKota(),
                    venue.getGambar(),
                    true // Tampilkan lokasi
                ));
            }
        }
        mainPanel.add(venueGridPanel);

        // JScrollPane untuk scrolling
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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

        // Panel untuk Nama Venue
        JPanel namePanel = new JPanel();
        namePanel.setBackground(Color.WHITE);
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel nameLabel = new JLabel("<html><center>" + name + "</center></html>", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        namePanel.add(nameLabel);
        card.add(namePanel, BorderLayout.NORTH);

        // Lokasi (opsional)
        if (showLocation) {
            JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel locationIcon = new JLabel(resizeIcon(new ImageIcon("asset/location_vanue.png"), 20, 20));
            JLabel locationLabel = new JLabel(location);
            locationLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
            locationPanel.add(locationIcon);
            locationPanel.add(locationLabel);

            card.add(locationPanel, BorderLayout.SOUTH);
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
