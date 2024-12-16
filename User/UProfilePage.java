package User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.List;
import DB.*;
import Landing_Page.Dashboard;
import Landing_Page.ULoginForm;
import java.awt.*;
import java.time.LocalDate;


public class UProfilePage {
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
            JPanel venueContent = createVenueContentPanel(); 
            mainPanel.removeAll();
            JPanel sidebar = createSidebarPanel(frame, mainPanel);
            mainPanel.add(sidebar, BorderLayout.WEST);
            mainPanel.add(venueContent, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
            frame.dispose();
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
        headerPanel.setPreferredSize(new Dimension(1100, 50));

        JLabel logoLabel = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon("asset/logo.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            logoLabel.setText("LOGO");
        }
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 5)); 
        headerPanel.add(logoLabel, BorderLayout.WEST);

        JLabel revenueLabel = new JLabel("REVENUE", SwingConstants.LEFT);
        revenueLabel.setForeground(Color.WHITE);
        revenueLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); 
        headerPanel.add(revenueLabel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
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
            updateActiveButtonSidebar(historyButton);
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
            ULoginForm.main(new String[]{}); 
            return null;
        }

        User user = UserDB.getUserDataByEmail(loggedInUserEmail);
        if (user == null) {
            JOptionPane.showMessageDialog(frame, "User data not found. Please login again.", "Error", JOptionPane.ERROR_MESSAGE);
            frame.dispose();
            ULoginForm.main(new String[]{}); 
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
    
        JTextField[] textFields = new JTextField[labels.length];
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
            textFields[i] = textField; 
            formPanel.add(textField, gbc);
        }
    
        contentPanel.add(formPanel);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(SOFT_WHITE);

        JButton editButton = new JButton("Edit");
        editButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        editButton.setBackground(ACCENT_ORANGE);
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);

        JButton cancelButton = new JButton("Batal");
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelButton.setBackground(new Color(220, 53, 69));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setVisible(false); 

        cancelButton.addActionListener(e -> {
            for (int i = 0; i < textFields.length; i++) {
                textFields[i].setText(userData[i] != null ? userData[i] : "");
                textFields[i].setEditable(false);
                textFields[i].setBackground(LIGHT_GRAY);
            }
            editButton.setText("Edit");
            cancelButton.setVisible(false); 
        });
             
        editButton.addActionListener(e -> {
            if (editButton.getText().equals("Edit")) {
                for (JTextField textField : textFields) {
                    textField.setEditable(true);
                    textField.setBackground(Color.WHITE);
                }
                editButton.setText("Save");
                cancelButton.setVisible(true);
            } else {
                String email = textFields[1].getText();
                
                if (UserDB.isEmailExisting(email, loggedInUserEmail)) {
                    JOptionPane.showMessageDialog(frame, "Email sudah digunakan oleh pengguna lain. Mohon gunakan email lain.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
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
                    Session.loggedInUserEmail = email; 
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to update data.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                for (JTextField textField : textFields) {
                    textField.setEditable(false);
                    textField.setBackground(LIGHT_GRAY);
                }
                editButton.setText("Edit");
                cancelButton.setVisible(false);
            }
        });

        buttonPanel.add(editButton);
        buttonPanel.add(cancelButton);
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

        int loggedInUserId = Session.loggedInUserId;
        List<Object[]> bookings = BookingDB.getAllBookingsForUser(loggedInUserId);
        if (bookings.isEmpty()) {
            JLabel noBookingLabel = new JLabel("Belum ada reservasi.");
            noBookingLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            noBookingLabel.setForeground(TEXT_DARK);
            noBookingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            reservationContainer.add(noBookingLabel);
        } else {
            for (Object[] booking : bookings) {
                int bookingId = (int) booking[0]; 
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
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(DEEP_NAVY);
        cardPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
    
        // Panel atas: Nama Venue (kiri), Tanggal (kanan), dan Garis di bawah
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
    
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        nameLabel.setForeground(Color.WHITE);
        topPanel.add(nameLabel, BorderLayout.WEST);
    
        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateLabel.setForeground(LIGHT_GRAY);
        topPanel.add(dateLabel, BorderLayout.EAST);
    
        // Garis Horizontal dengan Padding Top
        JPanel separatorPanel = new JPanel(new BorderLayout());
        separatorPanel.setOpaque(false);
        separatorPanel.setBorder(new EmptyBorder(10, 0, 0, 0)); // Padding atas 10px

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setForeground(Color.LIGHT_GRAY);
        separator.setPreferredSize(new Dimension(Integer.MAX_VALUE, 3)); // Garis panjang penuh

        separatorPanel.add(separator, BorderLayout.CENTER); // Tambahkan garis ke panel
        topPanel.add(separatorPanel, BorderLayout.SOUTH);

        // Panel tengah: Informasi
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(new EmptyBorder(5, 0, 10, 0));
    
        JLabel orgLabel = new JLabel("Nama Lembaga: " + organization);
        orgLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        orgLabel.setForeground(LIGHT_GRAY);
    
        JLabel emailLabel = new JLabel("Email Provider: " + email);
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailLabel.setForeground(LIGHT_GRAY);
    
        infoPanel.add(orgLabel);
        infoPanel.add(emailLabel);
    
        // Panel bawah: Tombol "See Invoice"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
    
        JButton invoiceButton = new JButton("See Invoice");
        invoiceButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        invoiceButton.setBackground(new Color(34, 177, 76));
        invoiceButton.setForeground(Color.WHITE);
        invoiceButton.setFocusPainted(false);
        invoiceButton.addActionListener(e -> showInvoiceDialog(bookingId));
        buttonPanel.add(invoiceButton);
    
        // Susun ke dalam cardPanel
        cardPanel.add(topPanel, BorderLayout.NORTH);      // Nama Venue, Tanggal, Garis
        cardPanel.add(infoPanel, BorderLayout.CENTER);    // Informasi Nama Lembaga dan Email
        cardPanel.add(buttonPanel, BorderLayout.SOUTH);   // Tombol di kanan bawah
    
        return cardPanel;
    }    

    private static void showInvoiceDialog(int bookingId) {
        Object[] invoiceData = InvoiceDB.getInvoiceDetails(bookingId);
        if (invoiceData == null) {
            JOptionPane.showMessageDialog(null, "Invoice tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Data Invoice
        String invoiceNumber = (String) invoiceData[0];
        String invoiceDate = (String) invoiceData[1];
        String venueName = (String) invoiceData[2];
        String lembaga = (String) invoiceData[3];
        String penanggungJawab = (String) invoiceData[4];
        int lamaWaktu = (int) invoiceData[5];
        double hargaPerHari = (double) invoiceData[6];
        double serviceFee = (double) invoiceData[7];
        double totalAmount = (double) invoiceData[8];
        String userName = (String) invoiceData[9];
        String userEmail = (String) invoiceData[10];
        String nomorhp = (String) invoiceData[11];
        String metodePembayaran = BookingDB.getMetodePembayaranByBookingId(bookingId);
    
        JFrame invoiceFrame = new JFrame("Invoice Detail");
        invoiceFrame.setSize(750, 700);
        invoiceFrame.setLocationRelativeTo(null);
        invoiceFrame.setLayout(new BorderLayout());
    
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new EmptyBorder(10, 20, 10, 20));
        container.setBackground(Color.WHITE);
    
        // INVOICE dan LUNAS di tengah
        JLabel titleLabel = new JLabel("INVOICE");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(titleLabel);
    
        JLabel statusLabel = new JLabel("LUNAS");
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        statusLabel.setForeground(new Color(34, 177, 76));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(statusLabel);
    
        container.add(Box.createVerticalStrut(10)); // Jarak
    
        // Informasi User dan Invoice
        String[][] userInfo = {
            {"<html><b>Nama:</b></html>", userName, "<html><b>No Invoice:</b></html>", invoiceNumber},
            {"<html><b>Email:</b></html>", userEmail, "<html><b>Tanggal Diterima:</b></html>", invoiceDate},
            {"<html><b>No Telp:</b></html>", nomorhp, "<html><b>Venue:</b></html>", venueName}
        };
        container.add(wrapInPanel(createCustomTable(userInfo, new String[]{"", "", "", ""}), 2));
    
        // Detail Booking
        String[][] bookingDetail = {
            {venueName, penanggungJawab, lembaga, lamaWaktu + " days", formatCurrency(hargaPerHari), formatCurrency(hargaPerHari * lamaWaktu)},
            {"<html><b>Biaya Layanan</b></html>", "", "", "", "", formatCurrency(serviceFee)},
            {"<html><b>Total</b></html>", "", "", "", "", formatCurrency(totalAmount)}
        };
        String[] bookingColumns = {"<html><b>Nama Venue</b></html>", "<html><b>Nama PJ</b></html>", "<html><b>Instansi</b></html>",
            "<html><b>Lama Waktu</b></html>", "<html><b>Harga per Hari (Rp)</b></html>", "<html><b>Jumlah (Rp)</b></html>"};
        container.add(wrapInPanel(createCustomTable(bookingDetail, bookingColumns), 2));
    
        // Metode Pembayaran
        String[][] paymentDetail = {
            {"<html><b>Metode:</b></html>", classifyPaymentMethod(metodePembayaran)},
            {"<html><b>Nama " + classifyPaymentMethod(metodePembayaran) + ":</b></html>", formatPaymentName(metodePembayaran)},
            {"<html><b>Nominal:</b></html>", formatCurrency(totalAmount)},
            {"<html><b>Atas Nama:</b></html>", lembaga}
        };
        container.add(wrapInPanel(createCustomTable(paymentDetail, new String[]{"", ""}), 2));
    
        // Tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton downloadButton = new JButton("Unduh Invoice");
        styleButton(downloadButton, new Color(34, 177, 76));
        JButton closeButton = new JButton("Tutup");
        styleButton(closeButton, new Color(220, 53, 69));
        closeButton.addActionListener(e -> invoiceFrame.dispose());
        buttonPanel.add(downloadButton);
        buttonPanel.add(closeButton);
        container.add(buttonPanel);
    
        invoiceFrame.add(container, BorderLayout.CENTER);
        invoiceFrame.setVisible(true);
    }
    
    // Membuat tabel dengan grid
    private static JTable createCustomTable(String[][] data, String[] columns) {
        JTable table = new JTable(data, columns);
        table.setEnabled(false);
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(230, 230, 250));
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true); // Tampilkan grid
        return table;
    }
    
    // Membungkus panel dengan jarak tambahan
    private static JPanel wrapInPanel(JTable table, int padding) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(padding, 0, padding, 0)); // Tambahkan jarak atas dan bawah
        panel.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        return panel;
    }
    // Helper: Format Mata Uang
    private static String formatCurrency(double amount) {
        return String.format("Rp %,d", (int) amount);
    }
    private static String classifyPaymentMethod(String metodePembayaran) {
        if (metodePembayaran == null) return "Tidak Diketahui";
        metodePembayaran = metodePembayaran.toLowerCase();
    
        // Pengecekan berdasarkan jenis pembayaran
        if (metodePembayaran.equals("bri") || metodePembayaran.equals("bni") || 
            metodePembayaran.equals("bca") || metodePembayaran.equals("mandiri")) {
            return "Bank";
        } else if (metodePembayaran.equals("alfamart") || metodePembayaran.equals("indomart")) {
            return "Gerai";
        } else if (metodePembayaran.equals("ovo") || metodePembayaran.equals("dana") || 
                   metodePembayaran.equals("shopeepay") || metodePembayaran.equals("linkaja")) {
            return "E-Wallet";
        }
        return "Lainnya";
    }
    
    private static String formatPaymentName(String metodePembayaran) {
        if (metodePembayaran == null) return "";
    
        // Khusus nama ShopeePay dan LinkAja
        if (metodePembayaran.equalsIgnoreCase("linkaja")) return "LinkAja";
        if (metodePembayaran.equalsIgnoreCase("shopeepay")) return "ShopeePay";
    
        // Jika panjangnya 3 huruf maka uppercase
        if (metodePembayaran.length() == 3) {
            return metodePembayaran.toUpperCase();
        }
    
        // Capitalize each word
        return capitalizeEachWord(metodePembayaran);
    }
    
    private static String capitalizeEachWord(String input) {
        String[] words = input.split("\\s+");
        StringBuilder capitalized = new StringBuilder();
    
        for (String word : words) {
            if (word.length() > 0) {
                capitalized.append(Character.toUpperCase(word.charAt(0)))
                           .append(word.substring(1).toLowerCase());
            }
            capitalized.append(" ");
        }
        return capitalized.toString().trim();
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

    private static void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
}