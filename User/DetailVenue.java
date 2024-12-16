package User;
import javax.swing.*;

import DB.*;

import java.util.Calendar;
import java.util.Date;
import java.awt.event.*;
import java.time.LocalDate;
import java.awt.*;
import java.util.List;

public class DetailVenue {
    private Venue venue;

    public DetailVenue(int idVenue) {
        this.venue = VenueDB.getVenueById(idVenue); // Ambil data venue berdasarkan id
        showDetailVenue();
    }

   private void showDetailVenue() {
        // Frame utama
        JFrame frame = new JFrame("Detail Venue");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 1000);
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Memaksimalkan jendela

        // Header dari DetailVenue
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
                ProfileViewUser.showProfileView();
            }
        });
        header.add(profile, BorderLayout.EAST);

        frame.add(header, BorderLayout.NORTH);

        // Panel utama untuk DetailVenue dan DetailVenueUI
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Elemen disusun vertikal

         // Konten dari DetailVenue
         JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
         navPanel.setOpaque(false); // Transparan
         navPanel.setPreferredSize(new Dimension(1200, 50));
 
         // Tombol navigasi
         JButton berandaButton = new JButton("Beranda");
         styleNavigationButton(berandaButton, new Color(255, 140, 0));
         navPanel.add(berandaButton);
         berandaButton.addActionListener(e -> {
            frame.dispose();
            HomeUser.main(new String[]{});
        });
 
         JButton venueButton = new JButton("Venue");
         styleNavigationButton(venueButton, new Color(12, 34, 64));
         venueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Tutup HomeUser
                VenuePage.showVenuePage(); // Buka VenuePage
            }
        });
 
         JPanel venuePanel = new JPanel(new BorderLayout());
         venuePanel.setOpaque(false);
         venuePanel.add(venueButton, BorderLayout.CENTER);
         venuePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 34, 64)));
         navPanel.add(venuePanel);
 
         JButton profileButton = new JButton("Profile");
         styleNavigationButton(profileButton, new Color(255, 140, 0));
         navPanel.add(profileButton);
         profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Tutup HomeUser
                UProfilePage.main(new String[]{});
            }
        });
 
         mainPanel.add(navPanel);

        // Panel Detail Venue
        JPanel detailVenuePanel = new JPanel(new GridLayout(1, 4, 20, 20));
        detailVenuePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Mengisi data venue ke kartu
        JPanel detailVenueCard = createVenueCard(
            venue.getNamaVenue(),
            venue.getDeskripsiFasilitas(),
            venue.getGambar(), // Path gambar diambil dari database
            false
        );
        detailVenuePanel.add(detailVenueCard);

        mainPanel.add(detailVenuePanel);

        // UI Panel untuk Detail Venue
        JPanel uiPanel = new JPanel(null);
        uiPanel.setPreferredSize(new Dimension(1200, 450));

        RoundedPanel leftPanel = new RoundedPanel(30);
        leftPanel.setLayout(null);
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setBounds(60, 20, 750, 400); // Sesuaikan lebar panel kiri

        JLabel venueName = new JLabel(venue.getNamaVenue());
        venueName.setFont(new Font("Poppins", Font.BOLD, 20));
        venueName.setBounds(20, 10, 710, 30); // Sesuaikan posisi venueName
        leftPanel.add(venueName);

        JLabel descLabel = new JLabel("Deskripsi & Fasilitas:");
        descLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        descLabel.setBounds(20, 70, 200, 30);
        leftPanel.add(descLabel);

        RoundedLabel description = new RoundedLabel(venue.getDeskripsiFasilitas(), 20);
        description.setOpaque(false);
        description.setBackground(Color.WHITE);
        description.setBounds(20, 100, 710, 100); // Sesuaikan ukuran dan posisi description
        leftPanel.add(description);

        JLabel addressLabel = new JLabel("Alamat:");
        addressLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        addressLabel.setBounds(20, 210, 200, 30);
        leftPanel.add(addressLabel);

        RoundedLabel addressField = new RoundedLabel(venue.getAlamat(), 20);
        addressField.setOpaque(false);
        addressField.setBackground(Color.WHITE);
        addressField.setBounds(20, 240, 710, 60); // Sesuaikan ukuran dan posisi addressField
        leftPanel.add(addressField);

        JLabel capacityLabel = new JLabel("Kapasitas:");
        capacityLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        capacityLabel.setBounds(20, 310, 220, 30); // Posisi kapasitas
        leftPanel.add(capacityLabel);

        RoundedLabel capacityField = new RoundedLabel(venue.getKapasitas() + " Orang", 20);
        capacityField.setOpaque(false);
        capacityField.setBackground(Color.WHITE);
        capacityField.setBounds(20, 340, 350, 40); // Posisi kapasitas field
        leftPanel.add(capacityField);

        JLabel priceLabel = new JLabel("Harga:");
        priceLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        priceLabel.setBounds(380, 310, 350, 30); // Posisi harga di sebelah kapasitas
        leftPanel.add(priceLabel);

        RoundedLabel priceField = new RoundedLabel("Rp " + String.format("%,d", venue.getHarga()), 20);
        priceField.setOpaque(false);
        priceField.setBackground(Color.WHITE);
        priceField.setBounds(380, 340, 350, 40); // Posisi harga field
        leftPanel.add(priceField);

        uiPanel.add(leftPanel);

        // Ambil ID user yang sedang login dari Session
        int currentUserId = Session.loggedInUserId;

        // Ambil data booking dari database untuk venue ini
        List<Booking> bookings = BookingDB.getBookingsForVenue(venue.getIdVenue());

        // Panel kanan untuk tanggal dan tombol booking
        RoundedPanel rightPanel = new RoundedPanel(30);
        rightPanel.setLayout(null);
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setBounds(830, 20, 380, 400); // Sesuaikan lebar panel kanan

        JLabel startDateLabel = new JLabel("Tanggal Mulai:");
        startDateLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        startDateLabel.setBounds(20, 20, 180, 20); // Posisi Tanggal Mulai
        rightPanel.add(startDateLabel);

        JLabel endDateLabel = new JLabel("Tanggal Selesai:");
        endDateLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        endDateLabel.setBounds(20, 110, 180, 20);
        rightPanel.add(endDateLabel);

        JSpinner startDateSpinner = new JSpinner(new CustomDateModel(new Date(), null, null, bookings));
        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDateSpinner, "dd/MM/yyyy");
        startDateSpinner.setEditor(startDateEditor);

        JSpinner endDateSpinner = new JSpinner(new CustomDateModel(new Date(), null, null, bookings));
        JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDateSpinner, "dd/MM/yyyy");
        endDateSpinner.setEditor(endDateEditor);
        
        startDateSpinner.setBounds(20, 50, 340, 40); // Atur posisi dan ukuran spinner untuk Tanggal Mulai
        endDateSpinner.setBounds(20, 140, 340, 40); // Atur posisi dan ukuran spinner untuk Tanggal Selesai
        
        rightPanel.add(startDateSpinner);
        rightPanel.add(endDateSpinner);

        // Tombol "Book"
        RoundedButton bookButton = new RoundedButton("Book", 20);
        bookButton.setBackground(Color.GRAY);
        bookButton.setForeground(Color.WHITE);
        bookButton.setBounds(20, 200, 340, 40);
        rightPanel.add(bookButton);

        // Tombol "Book"
        bookButton.setBackground(Color.GRAY);
        bookButton.setForeground(Color.WHITE);
        bookButton.setBounds(20, 200, 340, 40);
        bookButton.setEnabled(false); // Default disabled
        rightPanel.add(bookButton);

        // Tambahkan listener untuk validasi tanggal
        startDateSpinner.addChangeListener(e -> 
            validateDates(startDateSpinner, endDateSpinner, bookButton, bookings)
        );
        endDateSpinner.addChangeListener(e -> 
            validateDates(startDateSpinner, endDateSpinner, bookButton, bookings)
        );

        bookButton.addActionListener(e -> {
            // Buat dialog untuk metode pembayaran
            JDialog paymentDialog = new JDialog(frame, "Pilih Metode Pembayaran", true);
            paymentDialog.setSize(400, 250);
            paymentDialog.setLayout(new GridBagLayout());
            paymentDialog.setLocationRelativeTo(frame);
        
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Margin antar komponen
            gbc.fill = GridBagConstraints.HORIZONTAL;
        
            // Label untuk kategori
            JLabel categoryLabel = new JLabel("Pilih Kategori:");
            gbc.gridx = 0;
            gbc.gridy = 0;
            paymentDialog.add(categoryLabel, gbc);
        
            // Dropdown untuk kategori pembayaran
            String[] categories = {"Pilih Kategori", "Bank", "Gerai", "E-Wallet"};
            JComboBox<String> categoryDropdown = new JComboBox<>(categories);
            gbc.gridx = 1;
            gbc.gridy = 0;
            paymentDialog.add(categoryDropdown, gbc);
        
            // Label untuk detail pembayaran
            JLabel detailLabel = new JLabel("Pilih Metode:");
            gbc.gridx = 0;
            gbc.gridy = 1;
            paymentDialog.add(detailLabel, gbc);
        
            // Dropdown untuk detail pembayaran
            JComboBox<String> detailDropdown = new JComboBox<>();
            detailDropdown.setEnabled(false); // Default tidak aktif hingga kategori dipilih
            gbc.gridx = 1;
            gbc.gridy = 1;
            paymentDialog.add(detailDropdown, gbc);
        
            // Tambahkan listener untuk kategori dropdown
            categoryDropdown.addActionListener(event -> {
                String selectedCategory = (String) categoryDropdown.getSelectedItem();
                detailDropdown.removeAllItems(); // Hapus semua item sebelumnya
                detailDropdown.setEnabled(true); // Aktifkan dropdown detail
        
                if ("Bank".equals(selectedCategory)) {
                    detailDropdown.addItem("BRI");
                    detailDropdown.addItem("BCA");
                    detailDropdown.addItem("Mandiri");
                    detailDropdown.addItem("BNI");
                } else if ("Gerai".equals(selectedCategory)) {
                    detailDropdown.addItem("Alfamart");
                    detailDropdown.addItem("Indomart");
                } else if ("E-Wallet".equals(selectedCategory)) {
                    detailDropdown.addItem("Dana");
                    detailDropdown.addItem("OVO");
                    detailDropdown.addItem("ShopeePay");
                    detailDropdown.addItem("LinkAja");
                } else {
                    detailDropdown.setEnabled(false); // Nonaktifkan jika kategori tidak valid
                }
            });
        
            // Tombol Konfirmasi
            JButton confirmButton = new JButton("Konfirmasi");
            confirmButton.setEnabled(false); // Default tidak aktif
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2; // Tombol konfirmasi memakan seluruh lebar
            gbc.anchor = GridBagConstraints.CENTER;
            paymentDialog.add(confirmButton, gbc);
        
            // Aktifkan tombol konfirmasi hanya jika detail dropdown valid
            detailDropdown.addActionListener(event -> {
                if (detailDropdown.getSelectedItem() != null) {
                    confirmButton.setEnabled(true);
                }
            });
        
            confirmButton.addActionListener(event -> {
                String paymentMethod = (String) detailDropdown.getSelectedItem();
                if (paymentMethod != null) {

                    java.util.Date startDateUtil = (Date) startDateSpinner.getValue();
                    java.util.Date endDateUtil = (Date) endDateSpinner.getValue();
                    LocalDate startDate = startDateUtil.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                    LocalDate endDate = endDateUtil.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

                    int userId = Session.loggedInUserId; // Ambil ID pengguna dari sesi
                    boolean success = BookingDB.saveBooking(venue.getIdVenue(), startDate, endDate, paymentMethod, userId);
        
                    if (success) {
                        JOptionPane.showMessageDialog(frame, "Booking berhasil disimpan!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        paymentDialog.dispose(); // Tutup dialog
                
                        // Reload halaman Detail Venue
                        frame.dispose();
                        new DetailVenue(venue.getIdVenue()); // Membuka halaman DetailVenue yang sama
                    } else {
                        JOptionPane.showMessageDialog(frame, "Terjadi kesalahan saat menyimpan booking.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        
            // Tampilkan dialog
            paymentDialog.setVisible(true);
        });

        // Tombol "WhatsApp"
        RoundedButton waButton = new RoundedButton("WhatsApp", 20);
        waButton.setBackground(new Color(12, 34, 64));
        waButton.setForeground(Color.WHITE);
        waButton.setBounds(20, 260, 340, 40);
        rightPanel.add(waButton);

        uiPanel.add(rightPanel);
        mainPanel.add(uiPanel);

        // Tambahkan CalendarApp dengan data booking
        CalendarApp calendarApp = new CalendarApp(bookings, currentUserId);
        mainPanel.add(calendarApp);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Jarak vertikal 20px

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        JLabel footerImage = new JLabel(new ImageIcon("asset/Footer.png"));
        footerImage.setHorizontalAlignment(SwingConstants.CENTER);
        footerPanel.add(footerImage, BorderLayout.CENTER);
        footerPanel.setPreferredSize(new Dimension(frame.getWidth(), 200)); // Sesuaikan lebar dengan frame
        mainPanel.add(footerPanel, BorderLayout.SOUTH); // Atau gunakan frame.add jika ingin langsung ke frame

        // Scroll panel untuk mainPanel
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane, BorderLayout.CENTER);

        
        frame.setVisible(true);
    }

    private static JPanel createVenueCard(String name, String description, String imagePath, boolean showLocation) {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(1000, 300)); // Atur ukuran tetap card sesuai kebutuhan
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); // Tambahkan border opsional
        
        // Gambar Venue
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Resize gambar
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image resizedImage = originalIcon.getImage().getScaledInstance(1000, 200, Image.SCALE_SMOOTH); // Resize ke 400x200
        imageLabel.setIcon(new ImageIcon(resizedImage));
        
        card.add(imageLabel, BorderLayout.CENTER);

        
        return card;
    }
    

    private void validateDates(
        JSpinner startDateSpinner,
        JSpinner endDateSpinner,
        RoundedButton bookButton,
        List<Booking> bookings
    ) {
        Date startDate = (Date) startDateSpinner.getValue();
        Date endDate = (Date) endDateSpinner.getValue();
    
        LocalDate startLocalDate = new java.sql.Date(startDate.getTime()).toLocalDate();
        LocalDate endLocalDate = new java.sql.Date(endDate.getTime()).toLocalDate();
 
        boolean isOverlap = bookings.stream().anyMatch(booking -> {
            if (!"confirmed".equalsIgnoreCase(booking.getStatus())) {
                return false; 
            }
            LocalDate bookingStart = booking.getStartDate();
            LocalDate bookingEnd = booking.getEndDate();
            return !startLocalDate.isAfter(bookingEnd) && !endLocalDate.isBefore(bookingStart);
        });
    
        // Logika validasi tanggal
        if (!isOverlap && !startLocalDate.isAfter(endLocalDate)) {
            bookButton.setEnabled(true); 
            bookButton.setBackground(new Color(0, 120, 215)); 
        } else {
            bookButton.setEnabled(false); 
            bookButton.setBackground(Color.GRAY); 
        }
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

    static class RoundedPanel extends JPanel {
        private int cornerRadius;

        public RoundedPanel(int cornerRadius) {
            this.cornerRadius = cornerRadius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        }
    }

    static class RoundedLabel extends JLabel {
        private int cornerRadius;

        public RoundedLabel(String text, int cornerRadius) {
            super(text);
            this.cornerRadius = cornerRadius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            g2.setColor(getForeground());
            g2.drawString(getText(), 10, getHeight() / 2 + g2.getFontMetrics().getAscent() / 2 - 2);
        }
    }

    static class RoundedButton extends JButton {
        private int cornerRadius;

        public RoundedButton(String text, int cornerRadius) {
            super(text);
            this.cornerRadius = cornerRadius;
            setOpaque(false);
            setFocusPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            g2.setColor(getForeground());
            g2.setFont(getFont());
            g2.drawString(getText(), getWidth() / 2 - g2.getFontMetrics().stringWidth(getText()) / 2,
                    getHeight() / 2 + g2.getFontMetrics().getAscent() / 2 - 2);
        }
    }
}

class CustomDateModel extends SpinnerDateModel {
    private final List<Booking> bookings;

    public CustomDateModel(Date startDate, Date endDate, Date stepSize, List<Booking> bookings) {
        super(startDate, null, endDate, Calendar.DAY_OF_MONTH);
        this.bookings = bookings;
    }

    @Override
    public Object getNextValue() {
        Date nextDate = (Date) super.getNextValue();
        while (isDateBlocked(nextDate)) {
            nextDate = new Date(nextDate.getTime() + (24 * 60 * 60 * 1000));
        }
        return nextDate;
    }

    @Override
    public Object getPreviousValue() {
        Date previousDate = (Date) super.getPreviousValue();
        while (isDateBlocked(previousDate)) {
            previousDate = new Date(previousDate.getTime() - (24 * 60 * 60 * 1000));
        }
        return previousDate;
    }

    private boolean isDateBlocked(Date date) {
        LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();
        return bookings.stream().anyMatch(booking -> {
            if (!"confirmed".equalsIgnoreCase(booking.getStatus())) {
                return false; 
            }
            LocalDate bookingStart = booking.getStartDate();
            LocalDate bookingEnd = booking.getEndDate();
            return !localDate.isBefore(bookingStart) && !localDate.isAfter(bookingEnd);
        });
    }
}