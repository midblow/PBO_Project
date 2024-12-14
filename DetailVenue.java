import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.time.*;
import java.util.Calendar;

public class DetailVenue {
    public static void main(String[] args) {
        // Frame utama
        JFrame frame = new JFrame("Detail Venue");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1000);
        frame.setLayout(new BorderLayout());

        // Menampilkan jendela dalam mode fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Memaksimalkan jendela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layout dan ukuran frame
        frame.setLayout(new BorderLayout());

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
                ProfileView.showProfileView();
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

        JButton venueButton = new JButton("Venue");
        styleNavigationButton(venueButton, new Color(12, 34, 64));

        JPanel venuePanel = new JPanel(new BorderLayout());
        venuePanel.setOpaque(false);
        venuePanel.add(venueButton, BorderLayout.CENTER);
        venuePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 34, 64)));
        navPanel.add(venuePanel);

        JButton profileButton = new JButton("Profile");
        styleNavigationButton(profileButton, new Color(255, 140, 0));
        navPanel.add(profileButton);

        mainPanel.add(navPanel);

        JPanel detailVenuePanel = new JPanel(new GridLayout(1, 4, 20, 20));
        detailVenuePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel detailVenueCard = createVenueCard("Taman Sangkareang", null, "asset/Sangkareang.jpg", false);
        detailVenuePanel.add(detailVenueCard);

        mainPanel.add(detailVenuePanel);

        // Konten dari DetailVenueUI
        VenueInfo venueInfo = new VenueInfo("TAMAN SANGKAREANG", "Sangat cocok untuk segala acara", "Jl. Priskila", "10.000 Orang", "Rp 1.000.000");

        JPanel uiPanel = new JPanel(null);
        uiPanel.setPreferredSize(new Dimension(1200, 450));

        RoundedPanel leftPanel = new RoundedPanel(30);
        leftPanel.setLayout(null);
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setBounds(60, 20, 750, 400); // Sesuaikan lebar panel kiri

        JLabel venueName = new JLabel(venueInfo.getName());
        venueName.setFont(new Font("Poppins", Font.BOLD, 20));
        venueName.setBounds(20, 10, 710, 30); // Sesuaikan posisi venueName
        leftPanel.add(venueName);

        // LeftPanel components
        JLabel descLabel = new JLabel("Deskripsi & Fasilitas:");
        descLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        descLabel.setBounds(20, 70, 200, 30);
        leftPanel.add(descLabel);

        RoundedLabel description = new RoundedLabel(venueInfo.getDescription(), 20);
        description.setOpaque(false);
        description.setBackground(Color.WHITE);
        description.setBounds(20, 100, 710, 100); // Sesuaikan ukuran dan posisi description
        leftPanel.add(description);

        JLabel addressLabel = new JLabel("Alamat:");
        addressLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        addressLabel.setBounds(20, 210, 200, 30);
        leftPanel.add(addressLabel);

        RoundedLabel addressField = new RoundedLabel(venueInfo.getAddress(), 20);
        addressField.setOpaque(false);
        addressField.setBackground(Color.WHITE);
        addressField.setBounds(20, 240, 710, 60); // Sesuaikan ukuran dan posisi addressField
        leftPanel.add(addressField);

        JLabel capacityLabel = new JLabel("Kapasitas:");
        capacityLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        capacityLabel.setBounds(20, 310, 220, 30); // Posisi kapasitas
        leftPanel.add(capacityLabel);

        RoundedLabel capacityField = new RoundedLabel(venueInfo.getCapacity(), 20);
        capacityField.setOpaque(false);
        capacityField.setBackground(Color.WHITE);
        capacityField.setBounds(20, 340, 350, 40); // Posisi kapasitas field
        leftPanel.add(capacityField);

        JLabel priceLabel = new JLabel("Harga:");
        priceLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        priceLabel.setBounds(380, 310, 350, 30); // Posisi harga di sebelah kapasitas
        leftPanel.add(priceLabel);

        RoundedLabel priceField = new RoundedLabel(venueInfo.getPrice(), 20);
        priceField.setOpaque(false);
        priceField.setBackground(Color.WHITE);
        priceField.setBounds(380, 340, 350, 40); // Posisi harga field
        leftPanel.add(priceField);

        uiPanel.add(leftPanel);

        // Panel kanan (rightPanel)
        RoundedPanel rightPanel = new RoundedPanel(30);
        rightPanel.setLayout(null);
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setBounds(830, 20, 380, 400); // Sesuaikan lebar panel kanan

        // Label untuk Tanggal Mulai
        JLabel startDateLabel = new JLabel("Tanggal Mulai:");
        startDateLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        startDateLabel.setBounds(20, 20, 180, 20); // Posisi Tanggal Mulai
        rightPanel.add(startDateLabel);

        // Ikon Kalender untuk Tanggal Mulai
        ImageIcon startDateIconImage = new ImageIcon(new ImageIcon("asset/kalender.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        JButton startDateIcon = new JButton(startDateIconImage);
        startDateIcon.setBounds(320, 50, 40, 40); // Posisi ikon kalender
        startDateIcon.setContentAreaFilled(false);
        startDateIcon.setBorderPainted(false);
        rightPanel.add(startDateIcon);

        // Spinner untuk Tanggal Mulai
        JSpinner startDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDateSpinner, "dd/MM/yyyy");
        startDateSpinner.setEditor(startDateEditor);
        startDateSpinner.setBounds(20, 50, 290, 40); // Sesuaikan posisi dan ukuran startDateSpinner
        rightPanel.add(startDateSpinner);

        // Label untuk Tanggal Selesai
        JLabel endDateLabel = new JLabel("Tanggal Selesai:");
        endDateLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        endDateLabel.setBounds(20, 110, 180, 20); // Posisi Tanggal Selesai
        rightPanel.add(endDateLabel);

        // Ikon Kalender untuk Tanggal Selesai
        ImageIcon endDateIconImage = new ImageIcon(new ImageIcon("asset/kalender.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        JButton endDateIcon = new JButton(endDateIconImage);
        endDateIcon.setBounds(320, 140, 40, 40); // Posisi ikon kalender
        endDateIcon.setContentAreaFilled(false);
        endDateIcon.setBorderPainted(false);
        rightPanel.add(endDateIcon);

        // Spinner untuk Tanggal Selesai
        JSpinner endDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDateSpinner, "dd/MM/yyyy");
        endDateSpinner.setEditor(endDateEditor);
        endDateSpinner.setBounds(20, 140, 290, 40); // Sesuaikan posisi dan ukuran endDateSpinner
        rightPanel.add(endDateSpinner);

        // Tombol "Book"
        RoundedButton bookButton = new RoundedButton("Book", 20);
        bookButton.setBackground(Color.GRAY);
        bookButton.setForeground(Color.WHITE);
        bookButton.setBounds(20, 200, 340, 40); // Sesuaikan posisi dan ukuran bookButton
        rightPanel.add(bookButton);

        // Tombol "WhatsApp"
        RoundedButton waButton = new RoundedButton("WhatsApp", 20);
        waButton.setBackground(new Color(12, 34, 64));
        waButton.setForeground(Color.WHITE);
        waButton.setBounds(20, 260, 340, 40); // Sesuaikan posisi dan ukuran waButton
        rightPanel.add(waButton);

        // Menambahkan panel kanan ke uiPanel
        uiPanel.add(rightPanel);

        mainPanel.add(uiPanel);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Add the calendar at the bottom
        JPanel calendarPanel = new JPanel(new BorderLayout());
        CalendarApp calendarApp = new CalendarApp();
        calendarPanel.add(calendarApp, BorderLayout.CENTER);
        mainPanel.add(calendarPanel);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Tambahkan JScrollPane ke frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Kode untuk CalendarApp
    static class CalendarApp extends JPanel {
        private JPanel calendarPanel;
        private JLabel monthYearLabel;
        private int currentMonth;
        private int currentYear;

        public CalendarApp() {
            setLayout(new BorderLayout());

            // Panel untuk navigasi bulan
            JPanel navigationPanel = new JPanel();
            JButton prevButton = new JButton("Prev");
            JButton nextButton = new JButton("Next");

            // Menambahkan jarak antara tombol dan kalender
            navigationPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Menambahkan jarak vertikal di atas dan bawah tombol

            Color buttonColor = new Color(12, 34, 64);
            prevButton.setBackground(buttonColor);
            nextButton.setBackground(buttonColor);
            prevButton.setForeground(Color.WHITE);
            nextButton.setForeground(Color.WHITE);

            prevButton.setPreferredSize(new Dimension(80, 30));
            nextButton.setPreferredSize(new Dimension(80, 30));

            monthYearLabel = new JLabel("", SwingConstants.CENTER);
            monthYearLabel.setFont(new Font("Arial", Font.BOLD, 16));

            navigationPanel.setLayout(new BorderLayout());
            navigationPanel.add(prevButton, BorderLayout.WEST);
            navigationPanel.add(monthYearLabel, BorderLayout.CENTER);
            navigationPanel.add(nextButton, BorderLayout.EAST);
            add(navigationPanel, BorderLayout.NORTH);

            // Panel untuk kalender
            calendarPanel = new JPanel();
            calendarPanel.setLayout(new GridLayout(0, 7, 5, 5));
            calendarPanel.setBackground(Color.LIGHT_GRAY);
            add(calendarPanel, BorderLayout.CENTER);

            // Set bulan dan tahun saat ini
            LocalDate currentDate = LocalDate.now();
            currentMonth = currentDate.getMonthValue();
            currentYear = currentDate.getYear();

            // Update kalender pertama kali
            updateCalendar();

            prevButton.addActionListener(e -> {
                currentMonth--;
                if (currentMonth < 1) {
                    currentMonth = 12;
                    currentYear--;
                }
                updateCalendar();
            });

            nextButton.addActionListener(e -> {
                currentMonth++;
                if (currentMonth > 12) {
                    currentMonth = 1;
                    currentYear++;
                }
                updateCalendar();
            });
        }

        private void updateCalendar() {
            calendarPanel.removeAll();
            monthYearLabel.setText(String.format("%s %d", Month.of(currentMonth), currentYear));

            // Hari-hari dalam seminggu
            String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
            for (String day : days) {
                JLabel label = new JLabel(day, SwingConstants.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 12));
                label.setOpaque(true);
                label.setBackground(new Color(12, 34, 64));
                label.setForeground(Color.WHITE);
                calendarPanel.add(label);
            }

            // Kalender
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, currentMonth - 1);
            calendar.set(Calendar.YEAR, currentYear);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            // Kosongkan grid sebelum hari pertama bulan
            for (int i = 0; i < firstDayOfWeek; i++) {
                calendarPanel.add(new JLabel());
            }

            // Isi grid dengan tanggal
            for (int day = 1; day <= daysInMonth; day++) {
                JLabel label = new JLabel(String.valueOf(day), SwingConstants.CENTER);
                label.setFont(new Font("Arial", Font.PLAIN, 12));
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                calendarPanel.add(label);
            }

            calendarPanel.revalidate();
            calendarPanel.repaint();
        }
    }

    private static JPanel createVenueCard(String name, String location, String imagePath, boolean showLocation) {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(1200, 400)); // Menyesuaikan ukuran lebar card
    
        // Gambar Venue
        JLabel imageLabel = new JLabel();
        ImageIcon originalIcon = new ImageIcon(imagePath);
        
        // Add a listener to resize the image after the panel is fully laid out
        card.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                // Resize image dynamically when the panel size is determined
                int panelWidth = card.getWidth(); // Get the actual panel width
                if (panelWidth > 0) { // Ensure width is valid
                    // Resize the image to the panel width while maintaining aspect ratio
                    Image img = originalIcon.getImage();
                    int height = (int) (img.getHeight(null) * ((double) panelWidth / img.getWidth(null)));
                    Image resizedImg = img.getScaledInstance(panelWidth, height, Image.SCALE_SMOOTH); // Resize image
                    imageLabel.setIcon(new ImageIcon(resizedImg)); // Set resized image
                    card.revalidate(); // Ensure layout is updated
                }
            }
        });
    
        // Initial image resizing before layout is done (may be scaled incorrectly but won't throw error)
        int initialWidth = card.getWidth() > 0 ? card.getWidth() : 1200;
        Image img = originalIcon.getImage();
        int height = (int) (img.getHeight(null) * ((double) initialWidth / img.getWidth(null)));
        Image resizedImg = img.getScaledInstance(initialWidth, height, Image.SCALE_SMOOTH); // Resize image
        imageLabel.setIcon(new ImageIcon(resizedImg)); // Set resized image
        card.add(imageLabel, BorderLayout.CENTER);
        
        // Panel Nama Venue
        JPanel namePanel = new JPanel();
        namePanel.setBackground(Color.WHITE);
        JLabel nameLabel = new JLabel("<html><center>" + name + "</center></html>", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        namePanel.add(nameLabel);
        card.add(namePanel, BorderLayout.NORTH);
        
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

    static class VenueInfo {
        private String name;
        private String description;
        private String address;
        private String capacity;
        private String price;

        public VenueInfo(String name, String description, String address, String capacity, String price) {
            this.name = name;
            this.description = description;
            this.address = address;
            this.capacity = capacity;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getAddress() {
            return address;
        }

        public String getCapacity() {
            return capacity;
        }

        public String getPrice() {
            return price;
        }
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

