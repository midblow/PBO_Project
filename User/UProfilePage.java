package User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.List;
import DB.*;
import Landing_Page.Dashboard;
import Landing_Page.ULoginForm;
import java.awt.*;
import java.time.LocalDate;
import java.util.Date;

public class UProfilePage {
    // Refined Color Palette
    private static final Color DEEP_NAVY = new Color(17, 29, 48);
    private static final Color SOFT_NAVY = new Color(36, 65, 97);
    private static final Color LIGHT_GRAY = new Color(240, 242, 245);
    private static final Color ACCENT_ORANGE = new Color(255, 127, 39);
    private static final Color SOFT_WHITE = new Color(252, 253, 255);
    private static final Color TEXT_DARK = new Color(27, 47, 71);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UProfilePage::createAndShowGUI);
    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Kelola Akun Anda");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(SOFT_WHITE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(SOFT_WHITE);

        JPanel headerPanel = createHeaderPanel();
        frame.add(headerPanel, BorderLayout.NORTH);

        JPanel navbarPanel = new JPanel(new BorderLayout());
        navbarPanel.setBackground(SOFT_WHITE);
        navbarPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        JPanel leftNavbarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        leftNavbarPanel.setBackground(SOFT_WHITE);
        leftNavbarPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JButton berandaButton = createNavbarButton("Beranda");
        berandaButton.addActionListener(e -> {
            JPanel homeContent = createHomeContentPanel();
            mainPanel.removeAll();
            JPanel sidebar = createSidebarPanel(frame, mainPanel);
            mainPanel.add(sidebar, BorderLayout.WEST);
            mainPanel.add(homeContent, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
            frame.dispose();
            HomeUser.main(new String[]{});
            updateActiveButtonNavbar(berandaButton);
        });

        JButton venueButton = createNavbarButton("Venue");
        venueButton.addActionListener(e -> {
            JPanel venueContent = createVenueContentPanel(); // Now defined
            mainPanel.removeAll();
            JPanel sidebar = createSidebarPanel(frame, mainPanel);
            mainPanel.add(sidebar, BorderLayout.WEST);
            mainPanel.add(venueContent, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
            frame.dispose(); // Tutup halaman UProfilePage saat ini
            VenuePage.showVenuePage();
            updateActiveButtonNavbar(venueButton);
        });

        JButton profileButton = createNavbarButton("Profile");
        profileButton.addActionListener(e -> {
            JPanel profileContent = createProfileContentPanel(frame, mainPanel);
            mainPanel.removeAll();
            JPanel sidebar = createSidebarPanel(frame, mainPanel);
            mainPanel.add(sidebar, BorderLayout.WEST);
            mainPanel.add(profileContent, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
            updateActiveButtonNavbar(profileButton);
        });

        updateActiveButtonNavbar(profileButton);
        leftNavbarPanel.setLayout(new BoxLayout(leftNavbarPanel, BoxLayout.X_AXIS));
        leftNavbarPanel.add(berandaButton);
        leftNavbarPanel.add(Box.createHorizontalStrut(20));
        leftNavbarPanel.add(venueButton);
        leftNavbarPanel.add(Box.createHorizontalStrut(20));
        leftNavbarPanel.add(profileButton);
        navbarPanel.add(leftNavbarPanel, BorderLayout.WEST);

        JPanel sidebar = createSidebarPanel(frame, mainPanel);
        JPanel profileContent = createProfileContentPanel(frame, mainPanel);

        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(profileContent, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(SOFT_WHITE);
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(navbarPanel, BorderLayout.SOUTH);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        Dimension sidebarPreferredSize = new Dimension(250, frame.getHeight());
        sidebar.setPreferredSize(sidebarPreferredSize);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createHomeContentPanel() {
        JPanel homePanel = new JPanel();
        homePanel.setBackground(SOFT_WHITE);
        return homePanel;
    }

    private static JPanel createVenueContentPanel() {
        JPanel venuePanel = new JPanel();
        venuePanel.setBackground(SOFT_WHITE);
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

        JLabel revenueLabel = new JLabel("REVENUE", SwingConstants.LEFT);
        revenueLabel.setForeground(Color.WHITE);
        revenueLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Ukuran font lebih kecil
        headerPanel.add(revenueLabel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5)); // Spasi lebih kecil
        rightPanel.setBackground(DEEP_NAVY);
        headerPanel.add(rightPanel, BorderLayout.EAST);
        return headerPanel;
    }

    private static JPanel createSidebarPanel(JFrame frame, JPanel mainPanel) {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(SOFT_NAVY);
        sidebar.setPreferredSize(new Dimension(250, 700));
        sidebar.setBorder(new EmptyBorder(20, 10, 20, 10));

        User user = UserDB.getUserDataByEmail(Session.loggedInUserEmail);
        JLabel profilePicture = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon("asset/erwin.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            profilePicture.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            profilePicture.setText("Profile Pic");
        }
        profilePicture.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(user.getName(), SwingConstants.CENTER); 
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailLabel = new JLabel(user.getGmail(), SwingConstants.CENTER);
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailLabel.setForeground(LIGHT_GRAY);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton profileButton = createSidebarButton("Profil");
        profileButton.addActionListener(e -> {
            JPanel profileContent = createProfileContentPanel(frame, mainPanel);
            mainPanel.removeAll();
            JPanel newSidebar = createSidebarPanel(frame, mainPanel);
            mainPanel.add(newSidebar, BorderLayout.WEST);
            mainPanel.add(profileContent, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
            updateActiveButtonSidebar(profileButton);
        });

        updateActiveButtonSidebar(profileButton);

        JButton historyButton = createSidebarButton("Riwayat Reservasi");
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
            Session.clearSession();
            JOptionPane.showMessageDialog(frame, "Logout berhasil.", "Logout", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            Dashboard.main(new String[]{});
        });

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
    
        String loggedInUserEmail = Session.loggedInUserEmail;
    
        if (loggedInUserEmail == null) {
            JOptionPane.showMessageDialog(frame, "Session expired. Please login again.", "Error", JOptionPane.ERROR_MESSAGE);
            frame.dispose();
            ULoginForm.main(new String[]{}); // Kembali ke halaman login
            return null;
        }

        User user = UserDB.getUserDataByEmail(loggedInUserEmail);
        if (user == null) {
            JOptionPane.showMessageDialog(frame, "User data not found. Please login again.", "Error", JOptionPane.ERROR_MESSAGE);
            frame.dispose();
            ULoginForm.main(new String[]{}); // Kembali ke halaman login
            return null;
        }
    
        String[] userData = new String[] {
            user.getName(),
            user.getGmail(),
            user.getGender(),
            String.valueOf(user.getNomorhp()),
            user.getAlamat()
        };
        String[] labels = { "Nama:", "Email:", "Jenis Kelamin", "Nomor Telepon:", "Alamat:" };
    
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(SOFT_WHITE);
        contentPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
    
        JLabel titleLabel = new JLabel("Profile User", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(DEEP_NAVY);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(20));
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(SOFT_WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        JTextField[] textFields = new JTextField[labels.length]; // Untuk menyimpan referensi JTextField

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

            JTextField textField = createTextField(userData[i] != null ? userData[i] : "");
            textFields[i] = textField; // Simpan referensi ke array
            formPanel.add(textField, gbc);
        }
    
        contentPanel.add(formPanel);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(SOFT_WHITE);
    
        // Tombol Edit
        JButton editButton = new JButton("Edit");
        editButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        editButton.setBackground(ACCENT_ORANGE);
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);

        // Tombol Batal
        JButton cancelButton = new JButton("Batal");
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelButton.setBackground(new Color(220, 53, 69)); // Warna merah
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setVisible(false); // Awalnya disembunyikan

        cancelButton.addActionListener(e -> {
            // Batalkan perubahan, reset nilai JTextField
            for (int i = 0; i < textFields.length; i++) {
                textFields[i].setText(userData[i] != null ? userData[i] : "");
                textFields[i].setEditable(false);
                textFields[i].setBackground(LIGHT_GRAY);
            }
            editButton.setText("Edit");
            cancelButton.setVisible(false); // Sembunyikan tombol Batal
        });
             
        editButton.addActionListener(e -> {
            if (editButton.getText().equals("Edit")) {
                // Ubah semua JTextField menjadi editable
                for (JTextField textField : textFields) {
                    textField.setEditable(true);
                    textField.setBackground(Color.WHITE);
                }
                editButton.setText("Save");
                cancelButton.setVisible(true); // Tampilkan tombol Batal
            } else {
                String email = textFields[1].getText();
                
                if (UserDB.isEmailExisting(email, loggedInUserEmail)) {
                    JOptionPane.showMessageDialog(frame, "Email sudah digunakan oleh pengguna lain. Mohon gunakan email lain.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Hentikan proses jika email sudah ada
                }

                boolean isUpdated = UserDB.updateUserData(
                    loggedInUserEmail,
                    textFields[0].getText(), // Nama
                    email, // Email baru
                    textFields[2].getText(), // Jenis Kelamin
                    textFields[3].getText(), // Nomor Telepon
                    textFields[4].getText()  // Alamat
                );
        
                if (isUpdated) {
                    JOptionPane.showMessageDialog(frame, "Data updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Session.loggedInUserEmail = email; // Update session email jika berhasil
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to update data.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                for (JTextField textField : textFields) {
                    textField.setEditable(false);
                    textField.setBackground(LIGHT_GRAY);
                }
                editButton.setText("Edit");
                cancelButton.setVisible(false); // Sembunyikan tombol Batal
            }
        });

        buttonPanel.add(editButton);
        buttonPanel.add(cancelButton); // Tambahkan tombol Batal ke panel
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(buttonPanel);
        profilePanel.add(contentPanel, BorderLayout.CENTER);
        return profilePanel;
    }

   private static JPanel createBookingContentPanel() {
        JPanel bookingPanel = new JPanel(new BorderLayout());
        bookingPanel.setBackground(SOFT_WHITE);
        bookingPanel.setBorder(new EmptyBorder(20, 40, 40, 40));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(SOFT_WHITE);
        titlePanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        JLabel titleLabel = new JLabel("Riwayat Reservasi");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(DEEP_NAVY);
        titlePanel.add(titleLabel, BorderLayout.WEST);

        JPanel reservationContainer = new JPanel();
        reservationContainer.setLayout(new BoxLayout(reservationContainer, BoxLayout.Y_AXIS));
        reservationContainer.setBackground(SOFT_WHITE);

        int loggedInUserId = Session.loggedInUserId; // User ID dari sesi
        List<Object[]> bookings = BookingDB.getAllBookingsForUser(loggedInUserId);

        if (bookings.isEmpty()) {
            JLabel noBookingLabel = new JLabel("Belum ada reservasi.");
            noBookingLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            noBookingLabel.setForeground(TEXT_DARK);
            noBookingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            reservationContainer.add(noBookingLabel);
        } else {
            for (Object[] booking : bookings) {
                int bookingId = (int) booking[0]; // Ambil bookingId
                String venueName = (String) booking[1];
                LocalDate startDate = (LocalDate) booking[2];
                LocalDate endDate = (LocalDate) booking[3];
                String organization = (String) booking[4];
                String email = (String) booking[5];
        
                String dateRange = startDate + " - " + endDate;
                reservationContainer.add(createEnhancedReservationCard(venueName, dateRange, organization, email, bookingId));
                reservationContainer.add(Box.createVerticalStrut(20)); // Spasi antar kartu
            }
        }

        JScrollPane scrollPane = new JScrollPane(reservationContainer);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        bookingPanel.add(titlePanel, BorderLayout.NORTH);
        bookingPanel.add(scrollPane, BorderLayout.CENTER);
        return bookingPanel;
    }

    private static JPanel createEnhancedReservationCard(String name, String date, String organization, String email, int bookingId) {
        JPanel cardPanel = new JPanel(new BorderLayout(10, 10));
        cardPanel.setBackground(DEEP_NAVY);
        cardPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
    
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);
    
        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateLabel.setForeground(LIGHT_GRAY);
    
        JButton invoiceButton = new JButton("See Invoice");
        invoiceButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        invoiceButton.setBackground(new Color(34, 177, 76));
        invoiceButton.setForeground(Color.WHITE);
        invoiceButton.addActionListener(e -> showInvoiceDialog(bookingId));
    
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.add(invoiceButton);
        cardPanel.add(nameLabel, BorderLayout.NORTH);
        cardPanel.add(dateLabel, BorderLayout.CENTER);
        cardPanel.add(actionPanel, BorderLayout.SOUTH);
    
        return cardPanel;
    }
    private static void showInvoiceDialog(int bookingId) {
        Object[] invoiceData = BookingDB.getInvoiceByBookingId(bookingId); // Ambil data invoice
        if (invoiceData == null) {
            JOptionPane.showMessageDialog(null, "Invoice tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        int invoiceId = (int) invoiceData[0];
        Date invoiceDate = (Date) invoiceData[1];
        double totalAmount = (double) invoiceData[2];
        double serviceFee = (double) invoiceData[3];
    
        JFrame invoiceFrame = new JFrame();
        invoiceFrame.setTitle("Invoice Detail");
        invoiceFrame.setSize(1000, 800);
        invoiceFrame.setLocationRelativeTo(null);
    
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setBorder(new EmptyBorder(30, 40, 30, 40));
    
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
    
        JLabel invoiceTitleLabel = new JLabel("INVOICE #" + invoiceId);
        invoiceTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        invoiceTitleLabel.setForeground(new Color(36, 65, 97));
        headerPanel.add(invoiceTitleLabel, BorderLayout.WEST);
        mainContainer.add(headerPanel);
        JPanel infoPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        infoPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font valueFont = new Font("Segoe UI", Font.PLAIN, 14);
    
        addInfoPair(infoPanel, "Tanggal Invoice:", invoiceDate.toString(), labelFont, valueFont);
        addInfoPair(infoPanel, "Total Amount:", "Rp " + totalAmount, labelFont, valueFont);
        addInfoPair(infoPanel, "Service Fee:", "Rp " + serviceFee, labelFont, valueFont);
        addInfoPair(infoPanel, "Grand Total:", "Rp " + (totalAmount + serviceFee), labelFont, valueFont);
        mainContainer.add(infoPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton closeButton = createStyledButton("Tutup", new Color(220, 53, 69));
        closeButton.addActionListener(e -> invoiceFrame.dispose());
        buttonPanel.add(closeButton);
        mainContainer.add(buttonPanel);
    
        JScrollPane scrollPane = new JScrollPane(mainContainer);
        scrollPane.setBorder(null);
        invoiceFrame.setContentPane(scrollPane);
        invoiceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        invoiceFrame.setVisible(true);
    }

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

    private static final JButton[] activeNavbarButton = { null };
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
                if (button != activeNavbarButton[0]) {
                    button.setForeground(DEEP_NAVY);
                    button.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, DEEP_NAVY)); // Underline effect
                }
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button != activeNavbarButton[0]) {
                    button.setForeground(ACCENT_ORANGE);
                    button.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
                }
            }
        });
        return button;
    }

    private static void updateActiveButtonNavbar(JButton button) {
        if (activeNavbarButton[0] != null) {
            activeNavbarButton[0].setForeground(ACCENT_ORANGE);
            activeNavbarButton[0].setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        }

        activeNavbarButton[0] = button;
        button.setForeground(DEEP_NAVY);
        button.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, DEEP_NAVY)); // Underline effect
    }

    private static final JButton[] activeSidebarButton = { null };
    private static void updateActiveButtonSidebar(JButton button) {
        if (activeSidebarButton[0] != null) {
            activeSidebarButton[0].setBackground(SOFT_NAVY);
            activeSidebarButton[0].setForeground(Color.WHITE);
        }
        activeSidebarButton[0] = button;
        button.setBackground(ACCENT_ORANGE);
        button.setForeground(Color.WHITE);
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
                if (button != activeSidebarButton[0]) { // Hanya ubah gaya jika bukan tombol aktif
                    button.setBackground(ACCENT_ORANGE);
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button != activeSidebarButton[0]) { // Hanya ubah gaya jika bukan tombol aktif
                    button.setBackground(SOFT_NAVY);
                }
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