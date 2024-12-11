import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class UProfileViewWithHistory {
    // Refined Color Palette
    private static final Color DEEP_NAVY = new Color(17, 29, 48);
    private static final Color SOFT_NAVY = new Color(36, 65, 97);
    private static final Color LIGHT_GRAY = new Color(240, 242, 245);
    private static final Color ACCENT_ORANGE = new Color(255, 127, 39);
    private static final Color SOFT_WHITE = new Color(252, 253, 255);
    private static final Color TEXT_DARK = new Color(27, 47, 71);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UProfileViewWithHistory::createAndShowGUI);
    }

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
        JPanel navbarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10)); // Rata tengah dengan jarak 50px
        navbarPanel.setBackground(SOFT_WHITE);

        navbarPanel.setBorder(new EmptyBorder(10, 40, 10, 40));

        JButton profileButton = createNavbarButton("Profile");
        profileButton.addActionListener(e -> {
            JPanel profileContent = createProfileContentPanel(frame, mainPanel);
            mainPanel.removeAll();
            JPanel sidebar = createSidebarPanel(frame, mainPanel);
            mainPanel.add(sidebar, BorderLayout.WEST);
            mainPanel.add(profileContent, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        JButton reservationButton = createNavbarButton("Booking Confirmation");
        reservationButton.addActionListener(e -> {
            JPanel historyContent = createHistoryContentPanel(frame, mainPanel);
            mainPanel.removeAll();
            JPanel sidebar = createSidebarPanel(frame, mainPanel);
            mainPanel.add(sidebar, BorderLayout.WEST);
            mainPanel.add(historyContent, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        JButton logoutButton = createNavbarButton("Logout");
        logoutButton.addActionListener(e -> {
            // Logika untuk logout
        });

        navbarPanel.add(profileButton);
        navbarPanel.add(reservationButton);
        navbarPanel.add(logoutButton);

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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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

        // Right side: Greeting dan gambar nama.png
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5)); // Spasi lebih kecil
        rightPanel.setBackground(DEEP_NAVY);

        JLabel greetingLabel = new JLabel("Halo, Erwin");
        greetingLabel.setForeground(Color.WHITE);
        greetingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12)); // Ukuran font lebih kecil

        JLabel profilePictureLabel = new JLabel();
        try {
            ImageIcon profilePictureIcon = new ImageIcon("asset/nama.png");
            Image scaledImage = profilePictureIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH); // Ukuran
                                                                                                             // gambar
                                                                                                             // lebih
                                                                                                             // kecil
            profilePictureLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            profilePictureLabel.setText(""); // Fallback jika gambar gagal dimuat
        }

        rightPanel.add(greetingLabel);
        rightPanel.add(profilePictureLabel);

        headerPanel.add(rightPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private static JPanel createSidebarPanel(JFrame frame, JPanel mainPanel) {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(SOFT_NAVY);
        sidebar.setPreferredSize(new Dimension(250, 700));
        sidebar.setBorder(new EmptyBorder(20, 10, 20, 10));

        // Add profile picture and name with improved styling
        JLabel profilePicture = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon("asset/erwin.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            profilePicture.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            profilePicture.setText("Profile Pic");
        }
        profilePicture.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Erwin", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailLabel = new JLabel("erwin@gmail.com", SwingConstants.CENTER);
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailLabel.setForeground(LIGHT_GRAY);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons to sidebar with improved styling
        JButton profileButton = createSidebarButton("Profil");
        profileButton.addActionListener(e -> {
            JPanel profileContent = createProfileContentPanel(frame, mainPanel);
            // Logika untuk menampilkan panel profil
        });

        JButton historyButton = createSidebarButton("Booking Confirmation");
        historyButton.addActionListener(e -> {
            JPanel historyContent = createHistoryContentPanel(frame, mainPanel);
            // Logika untuk menampilkan panel riwayat reservasi
        });

        JButton logoutButton = createSidebarButton("Keluar");

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(Box.createVerticalStrut(30));
        sidebar.add(profilePicture);
        sidebar.add(Box.createRigidArea(new Dimension(0, 15)));
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
        JLabel titleLabel = new JLabel("Profile User", SwingConstants.LEFT);
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
        String[] labels = { "Nama:", "Email:", "Jenis Kelamin:", "Nomor Telepon:", "Alamat:" };
        String[] values = { "Erwin", "erwin@gmail.com", "Laki-laki", "99999", "Rembigan" };

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

    private static JPanel createHistoryContentPanel(JFrame frame, JPanel mainPanel) {
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBackground(SOFT_WHITE);
        historyPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        JLabel titleLabel = new JLabel("Riwayat Reservasi", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(DEEP_NAVY);
        historyPanel.add(titleLabel, BorderLayout.NORTH);

        // Table for reservation history with improved styling
        String[] columnNames = { "No", "Tanggal", "Waktu", "Keterangan" };
        Object[][] data = {
                { 1, "2024-12-01", "10:00", "Reservasi Dokter Gigi" },
                { 2, "2024-12-02", "14:30", "Reservasi Fisioterapi" }
        };

        JTable historyTable = new JTable(data, columnNames);
        historyTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        historyTable.setRowHeight(30);
        historyTable.setBackground(SOFT_WHITE);
        historyTable.setSelectionBackground(new Color(230, 240, 250));

        // Center align table columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < historyTable.getColumnCount(); i++) {
            historyTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(historyTable);
        scrollPane.getViewport().setBackground(SOFT_WHITE);

        historyPanel.add(scrollPane, BorderLayout.CENTER);
        return historyPanel;
    }

    private static JButton createNavbarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(ACCENT_ORANGE);
        button.setBackground(SOFT_WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0)); // Bottom space for underline
        button.setFocusPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(DEEP_NAVY);
                button.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, DEEP_NAVY)); // Underline effect
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(ACCENT_ORANGE);
                button.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
            }
        });

        return button;
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