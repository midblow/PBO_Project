package Provider;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class PProfilePage {
    // Refined Color Palette
    private static final Color DEEP_NAVY = new Color(17, 29, 48);
    private static final Color SOFT_NAVY = new Color(36, 65, 97);
    private static final Color LIGHT_GRAY = new Color(240, 242, 245);
    private static final Color ACCENT_ORANGE = new Color(255, 127, 39);
    private static final Color SOFT_WHITE = new Color(252, 253, 255);
    private static final Color TEXT_DARK = new Color(27, 47, 71);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PProfilePage::createAndShowGUI);
    }

    // Method untuk membuat dan menampilkan GUI
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Kelola Akun Anda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(SOFT_WHITE);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(SOFT_WHITE);

        // Header Panel
        JPanel headerPanel = createHeaderPanel();
        frame.add(headerPanel, BorderLayout.NORTH);

        // Navbar Panel
        JPanel navbarPanel = new JPanel(new BorderLayout());
        navbarPanel.setBackground(SOFT_WHITE);
        navbarPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        JPanel leftNavbarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        leftNavbarPanel.setBackground(SOFT_WHITE);
        leftNavbarPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Button "Beranda"
        JButton berandaButton = createNavbarButton("Beranda");
        berandaButton.addActionListener(e -> {
            JPanel homeContent = createHomeContentPanel();
            mainPanel.removeAll();
            JPanel sidebar = createSidebarPanel(frame, mainPanel);
            mainPanel.add(sidebar, BorderLayout.WEST);
            mainPanel.add(homeContent, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
            updateActiveButton(berandaButton);
        });

        // Button "Venue"
        JButton venueButton = createNavbarButton("Venue");
        venueButton.addActionListener(e -> {
            JPanel venueContent = createVenueContentPanel(); // Now defined
            mainPanel.removeAll();
            JPanel sidebar = createSidebarPanel(frame, mainPanel);
            mainPanel.add(sidebar, BorderLayout.WEST);
            mainPanel.add(venueContent, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
            updateActiveButton(venueButton);
        });

        // Button "Profile"
        JButton profileButton = createNavbarButton("Profile");
        profileButton.addActionListener(e -> {
            JPanel profileContent = createProfileContentPanel(frame, mainPanel);
            mainPanel.removeAll();
            JPanel sidebar = createSidebarPanel(frame, mainPanel);
            mainPanel.add(sidebar, BorderLayout.WEST);
            mainPanel.add(profileContent, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
            updateActiveButton(profileButton);
        });

        // Set default active button to "Beranda"
        updateActiveButton(berandaButton);

        // Atur layout tombol pada navbar
        leftNavbarPanel.setLayout(new BoxLayout(leftNavbarPanel, BoxLayout.X_AXIS));
        leftNavbarPanel.add(berandaButton);
        leftNavbarPanel.add(Box.createHorizontalStrut(20));
        leftNavbarPanel.add(venueButton);
        leftNavbarPanel.add(Box.createHorizontalStrut(20));
        leftNavbarPanel.add(profileButton);

        navbarPanel.add(leftNavbarPanel, BorderLayout.WEST);

        // Sidebar and Default Content
        JPanel sidebar = createSidebarPanel(frame, mainPanel);
        JPanel profileContent = createProfileContentPanel(frame, mainPanel);

        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(profileContent, BorderLayout.CENTER);

        // Add navbar below header
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(SOFT_WHITE);
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(navbarPanel, BorderLayout.SOUTH);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        // Adjust sidebar width to align with navbar
        Dimension sidebarPreferredSize = new Dimension(250, frame.getHeight());
        sidebar.setPreferredSize(sidebarPreferredSize);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Method to create the Home content panel
    private static JPanel createHomeContentPanel() {
        JPanel homePanel = new JPanel();
        homePanel.setBackground(SOFT_WHITE);
        // You can add your custom components here instead of a label
        return homePanel;
    }

    // Method to create the Venue content panel
    private static JPanel createVenueContentPanel() {
        JPanel venuePanel = new JPanel();
        venuePanel.setBackground(SOFT_WHITE);
        // You can add your custom components here instead of a label
        return venuePanel;
    }

    private static JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(DEEP_NAVY);
        headerPanel.setPreferredSize(new Dimension(1100, 50)); // Tinggi header lebih kecil

        // Left side: Logo dengan ukuran lebih kecil
        JLabel logoLabel = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon("asset/logo.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Ukuran logo
                                                                                                       // lebih kecil
            logoLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            logoLabel.setText("LOGO");
        }
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 5)); // Padding lebih kecil
        headerPanel.add(logoLabel, BorderLayout.WEST);

        // Center: Tulisan REVENUE dengan ukuran lebih kecil
        JLabel revenueLabel = new JLabel("REVENUE", SwingConstants.LEFT);
        revenueLabel.setForeground(Color.WHITE);
        revenueLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Ukuran font lebih kecil
        headerPanel.add(revenueLabel, BorderLayout.CENTER);

        // Right side: Remove Greeting and profile picture
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5)); // Spasi lebih kecil
        rightPanel.setBackground(DEEP_NAVY);

        // Remove the following lines to delete greeting and profile picture
        // JLabel greetingLabel = new JLabel("Halo, Erwin");
        // greetingLabel.setForeground(Color.WHITE);
        // greetingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12)); // Ukuran font
        // lebih kecil
        // rightPanel.add(greetingLabel);

        // JLabel profilePictureLabel = new JLabel();
        // try {
        // ImageIcon profilePictureIcon = new ImageIcon("asset/nama.png");
        // Image scaledImage = profilePictureIcon.getImage().getScaledInstance(25, 25,
        // Image.SCALE_SMOOTH); // Ukuran
        // // gambar
        // // lebih
        // // kecil
        // profilePictureLabel.setIcon(new ImageIcon(scaledImage));
        // } catch (Exception e) {
        // profilePictureLabel.setText(""); // Fallback jika gambar gagal dimuat
        // }
        // rightPanel.add(profilePictureLabel);

        headerPanel.add(rightPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private static JPanel createSidebarPanel(JFrame frame, JPanel mainPanel) {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(SOFT_NAVY);
        sidebar.setPreferredSize(new Dimension(250, 700));
        sidebar.setBorder(new EmptyBorder(20, 10, 20, 10));

        // Profile Picture and Name
        JLabel profilePicture = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon("asset/erwin.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            profilePicture.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            profilePicture.setText("Profile Pic");
        }
        profilePicture.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("PT. Makmur Jaya Sentosa", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailLabel = new JLabel("erwin@gmail.com", SwingConstants.CENTER);
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailLabel.setForeground(LIGHT_GRAY);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons in Sidebar
        JButton profileButton = createSidebarButton("Profil");
        profileButton.addActionListener(e -> {
            JPanel profileContent = createProfileContentPanel(frame, mainPanel);
            mainPanel.removeAll();
            JPanel newSidebar = createSidebarPanel(frame, mainPanel);
            mainPanel.add(newSidebar, BorderLayout.WEST);
            mainPanel.add(profileContent, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        JButton historyButton = createSidebarButton("Booking Confirmation");
        historyButton.addActionListener(e -> {
            JPanel historyContent = createBookingContentPanel();
            mainPanel.removeAll();
            JPanel newSidebar = createSidebarPanel(frame, mainPanel);
            mainPanel.add(newSidebar, BorderLayout.WEST);
            mainPanel.add(historyContent, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        JButton logoutButton = createSidebarButton("Keluar");
        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Logout Berhasil", "Logout", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
        });

        // Add Components to Sidebar
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(profilePicture);
        sidebar.add(Box.createRigidArea(new Dimension(0, 15)));
        sidebar.add(nameLabel);
        sidebar.add(emailLabel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));
        sidebar.add(profileButton);
        sidebar.add(historyButton);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(logoutButton);
        sidebar.add(Box.createVerticalStrut(20));

        return sidebar;
    }

    private static JPanel createProfileContentPanel(JFrame frame, JPanel mainPanel) {
        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.setBackground(SOFT_WHITE);

        // Content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(SOFT_WHITE);
        contentPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        // Title label with improved styling
        JLabel titleLabel = new JLabel("Profile Provider", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(DEEP_NAVY);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(titleLabel);

        contentPanel.add(Box.createVerticalStrut(20));

        // Form panel with improved layout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(SOFT_WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels and text fields
        String[] labels = { "Nama:", "Email:", "Lembaga:", "Nomor Telepon:", "Alamat:" };
        String[] values = { "Rewind", "erwin@gmail.com", "PT. Makmur Jaya Sentosa", "99999",
                "Rembigan" };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.3;
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Segoe UI", Font.BOLD, 16));
            label.setForeground(TEXT_DARK);
            formPanel.add(label, gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.7;
            JTextField textField = createTextField(values[i]);
            formPanel.add(textField, gbc);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(SOFT_WHITE);

        contentPanel.add(formPanel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(buttonPanel);

        profilePanel.add(contentPanel, BorderLayout.CENTER);
        return profilePanel;
    }

    private static JPanel createBookingContentPanel() {
        JPanel bookingPanel = new JPanel(new BorderLayout());
        bookingPanel.setBackground(SOFT_WHITE);
        bookingPanel.setBorder(new EmptyBorder(20, 40, 40, 40)); // Maintained bottom padding

        // Title Panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(SOFT_WHITE);
        titlePanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        JLabel titleLabel = new JLabel("Riwayat Reservasi");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(DEEP_NAVY);
        titlePanel.add(titleLabel, BorderLayout.WEST);

        // Reservation container with fixed size
        JPanel reservationContainer = new JPanel();
        reservationContainer.setLayout(new BoxLayout(reservationContainer, BoxLayout.Y_AXIS));
        reservationContainer.setBackground(SOFT_WHITE);

        // Add reservation cards with reduced width
        reservationContainer.add(createEnhancedReservationCard(
                "Taman Sangkareang",
                "2024-11-29 - 2024-11-30",
                "PT. Makmur Jaya Sentosa",
                "erwin@gmail.com"));
        reservationContainer.add(Box.createVerticalStrut(20));
        reservationContainer.add(createEnhancedReservationCard(
                "Taman Sangkareang",
                "2024-12-02 - 2024-12-04",
                "PT. Makmur Jaya Sentosa",
                "erwin@gmail.com"));

        // Scrollpane with clean look
        JScrollPane scrollPane = new JScrollPane(reservationContainer);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Add components to main booking panel
        bookingPanel.add(titlePanel, BorderLayout.NORTH);
        bookingPanel.add(scrollPane, BorderLayout.CENTER);

        return bookingPanel;
    }

    private static JPanel createEnhancedReservationCard(String name, String date, String organization, String email) {
        JPanel cardPanel = new JPanel(new BorderLayout(10, 10));
        cardPanel.setBackground(DEEP_NAVY); // Kembali ke warna biru tua
        cardPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Maksimalkan lebar card
        Dimension maxSize = new Dimension(Integer.MAX_VALUE, 200); // Batasi tinggi
        cardPanel.setMaximumSize(maxSize);
        cardPanel.setPreferredSize(new Dimension(600, 180)); // Atur ukuran preferred

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);

        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateLabel.setForeground(LIGHT_GRAY);
        dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        headerPanel.add(nameLabel, BorderLayout.WEST);
        headerPanel.add(dateLabel, BorderLayout.EAST);

        // Detail Panel
        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        detailPanel.setOpaque(false);

        JLabel organizationLabel = new JLabel("Lembaga: " + organization);
        organizationLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        organizationLabel.setForeground(LIGHT_GRAY);

        JLabel emailLabel = new JLabel("Email: " + email);
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailLabel.setForeground(LIGHT_GRAY);

        detailPanel.add(organizationLabel);
        detailPanel.add(Box.createVerticalStrut(5));
        detailPanel.add(emailLabel);

        // Action Panel
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.setOpaque(false);

        JButton invoiceButton = new JButton("SUCCESS!!!");
        invoiceButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        invoiceButton.setBackground(new Color(34, 177, 76)); // Hijau terang
        invoiceButton.setForeground(Color.WHITE);
        invoiceButton.setFocusPainted(false);
        invoiceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Action listener for invoice button
        invoiceButton.addActionListener(e -> showInvoiceDialog());

        actionPanel.add(invoiceButton);

        // Combine all panels
        cardPanel.add(headerPanel, BorderLayout.NORTH);
        cardPanel.add(detailPanel, BorderLayout.CENTER);
        cardPanel.add(actionPanel, BorderLayout.SOUTH);

        return cardPanel;
    }

    // [Previous code remains the same

    private static void showInvoiceDialog() {
        // Create JFrame with more modern styling
        JFrame invoiceFrame = new JFrame();
        invoiceFrame.setTitle("Invoice Detail");
        invoiceFrame.setSize(1000, 800);
        invoiceFrame.setLocationRelativeTo(null);

        // Main container panel
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setBorder(new EmptyBorder(30, 40, 30, 40)); // Padding di semua sisi ditingkatkan

        // Invoice Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        JLabel invoiceTitleLabel = new JLabel("INVOICE");
        invoiceTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        invoiceTitleLabel.setForeground(new Color(36, 65, 97));

        headerPanel.add(invoiceTitleLabel, BorderLayout.WEST);
        mainContainer.add(headerPanel);

        // Customer Information Panel
        JPanel infoPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        infoPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font valueFont = new Font("Segoe UI", Font.PLAIN, 14);

        addInfoPair(infoPanel, "Nama:", "Erwin", labelFont, valueFont);
        addInfoPair(infoPanel, "Email:", "erwin@gmail.com", labelFont, valueFont);
        addInfoPair(infoPanel, "No Invoice:", "000012", labelFont, valueFont);
        addInfoPair(infoPanel, "Tanggal:", "28 November 2024", labelFont, valueFont);
        addInfoPair(infoPanel, "Venue:", "Taman Sangkareang", labelFont, valueFont);
        addInfoPair(infoPanel, "Instansi:", "PT. Makmur Jaya Sentosa", labelFont, valueFont);

        mainContainer.add(infoPanel);

        // Invoice Details Table
        String[] columnNames = { "Deskripsi", "Durasi", "Harga Satuan", "Total" };
        Object[][] data = {
                { "Sewa Venue Taman Sangkareang", "2 hari", "Rp 1.000.000", "Rp 2.000.000" }
        };

        JTable invoiceTable = new JTable(new DefaultTableModel(data, columnNames)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Custom table styling
        invoiceTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        invoiceTable.setRowHeight(35);
        invoiceTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        invoiceTable.getTableHeader().setBackground(new Color(36, 65, 97));
        invoiceTable.getTableHeader().setForeground(Color.WHITE);
        invoiceTable.setBorder(BorderFactory.createEmptyBorder()); // Remove table border

        JScrollPane tableScrollPane = new JScrollPane(invoiceTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove scroll pane border

        mainContainer.add(tableScrollPane);

        // Cost Summary Panel
        JPanel costSummaryPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        costSummaryPanel.setBorder(new EmptyBorder(20, 0, 20, 0)); // Padding lebih besar di sekitar elemen

        addCostRow(costSummaryPanel, "Biaya Sewa", "Rp 2.000.000", labelFont, valueFont);
        addCostRow(costSummaryPanel, "Biaya Layanan", "Rp 50.000", labelFont, valueFont);
        addCostRow(costSummaryPanel, "Total Tagihan", "Rp 2.050.000",
                new Font("Segoe UI", Font.BOLD, 16),
                new Font("Segoe UI", Font.BOLD, 16));

        mainContainer.add(costSummaryPanel);

        // Add extra vertical space before buttons
        mainContainer.add(Box.createVerticalStrut(20)); // Jarak ekstra sebelum tombol

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10)); // Added consistent padding
        buttonPanel.setBorder(new EmptyBorder(20, 0, 20, 0)); // Padding lebih besar di sekitar tombol

        JButton viewInvoiceButton = createStyledButton("Unduh Invoice", new Color(0, 153, 51));
        JButton closeButton = createStyledButton("Tutup", new Color(220, 53, 69));

        closeButton.addActionListener(e -> invoiceFrame.dispose());

        buttonPanel.add(viewInvoiceButton);
        buttonPanel.add(closeButton);
        mainContainer.add(buttonPanel);

        // Scroll Pane for entire content
        JScrollPane scrollPane = new JScrollPane(mainContainer);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        invoiceFrame.setContentPane(scrollPane);
        invoiceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        invoiceFrame.setVisible(true);
        invoiceFrame.revalidate();
        invoiceFrame.repaint();
    }

    // Helper method to create styled buttons
    private static JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        return button;
    }

    // Helper method to add info pair to panel
    private static void addInfoPair(JPanel panel, String label, String value, Font labelFont, Font valueFont) {
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(labelFont);
        labelComponent.setForeground(new Color(70, 70, 70));

        JLabel valueComponent = new JLabel(value);
        valueComponent.setFont(valueFont);
        valueComponent.setForeground(new Color(30, 30, 30));

        panel.add(labelComponent);
        panel.add(valueComponent);
    }

    // Helper method to add cost row
    private static void addCostRow(JPanel panel, String label, String value, Font labelFont, Font valueFont) {
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(labelFont);
        labelComponent.setForeground(new Color(70, 70, 70));

        JLabel valueComponent = new JLabel(value);
        valueComponent.setFont(valueFont);
        valueComponent.setHorizontalAlignment(SwingConstants.RIGHT);
        valueComponent.setForeground(new Color(30, 30, 30));

        panel.add(labelComponent);
        panel.add(valueComponent);
    }

    private static JPanel createReservationPanel(String name, String date, String status) {
        JPanel reservationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        reservationPanel.setBackground(LIGHT_GRAY);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setForeground(DEEP_NAVY);

        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateLabel.setForeground(TEXT_DARK);

        JLabel statusLabel = new JLabel(status);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        statusLabel.setForeground(ACCENT_ORANGE);

        reservationPanel.add(nameLabel);
        reservationPanel.add(Box.createHorizontalStrut(16));
        reservationPanel.add(dateLabel);
        reservationPanel.add(Box.createHorizontalStrut(16));
        reservationPanel.add(statusLabel);

        return reservationPanel;
    }

    // Declare activeButton as a class field, not inside a method
    private static final JButton[] activeButton = { null }; // Static field to track active button

    // Method untuk membuat tombol navbar
    private static JButton createNavbarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Slightly increased font size
        button.setForeground(ACCENT_ORANGE);
        button.setBackground(SOFT_WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0)); // Bottom space for underline
        button.setFocusPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button != activeButton[0]) {
                    button.setForeground(DEEP_NAVY);
                    button.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, DEEP_NAVY)); // Underline effect
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button != activeButton[0]) {
                    button.setForeground(ACCENT_ORANGE);
                    button.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
                }
            }
        });

        return button;
    }

    // Helper method to update active button styles
    private static void updateActiveButton(JButton button) {
        if (activeButton[0] != null) {
            // Revert style of the previously active button to default
            activeButton[0].setForeground(ACCENT_ORANGE);
            activeButton[0].setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        }
        // Update the active button
        activeButton[0] = button;
        button.setForeground(DEEP_NAVY);
        button.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, DEEP_NAVY)); // Underline active button
    }

    private static JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(SOFT_NAVY);
        button.setForeground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setMaximumSize(new Dimension(200, 40));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(ACCENT_ORANGE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(SOFT_NAVY);
            }
        });
        return button;
    }

    private static JTextField createTextField(String text) {
        JTextField textField = new JTextField(text);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setEditable(false);
        textField.setBackground(LIGHT_GRAY);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(TEXT_DARK, 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return textField;
    }
}