import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class UProfileView {
    // Palet Warna yang Lebih Modern
    private static final Color DARK_BLUE = new Color(27, 47, 71);
    private static final Color SOFT_BLUE = new Color(57, 86, 124);
    private static final Color LIGHT_GRAY = new Color(238, 238, 238);
    private static final Color ACCENT_ORANGE = new Color(255, 127, 39);
    private static final Color BACKGROUND_WHITE = new Color(250, 250, 255);

    // Font Kustom
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);

    private static JPanel reservationPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        try {
            // Set look and feel modern
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = createMainFrame();
        JPanel headerPanel = createHeaderPanel();
        JPanel contentPanel = createContentPanel();

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(contentPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JFrame createMainFrame() {
        JFrame frame = new JFrame("Kelola Akun Anda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);
        frame.getContentPane().setBackground(BACKGROUND_WHITE);
        frame.setLayout(new BorderLayout(10, 10));
        return frame;
    }

    private static JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(DARK_BLUE);
        headerPanel.setPreferredSize(new Dimension(1024, 70));

        JPanel logoTitlePanel = createLogoTitlePanel();
        JLabel userGreeting = createUserGreetingLabel();

        headerPanel.add(logoTitlePanel, BorderLayout.WEST);
        headerPanel.add(userGreeting, BorderLayout.EAST);

        return headerPanel;
    }

    private static JPanel createLogoTitlePanel() {
        ImageIcon logoIcon = createScaledIcon("aset/logo.png", 40, 40);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBorder(new EmptyBorder(0, 10, 0, 0));

        JLabel titleLabel = new JLabel("REVENUE");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(TITLE_FONT);

        JPanel logoTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoTitlePanel.setBackground(DARK_BLUE);
        logoTitlePanel.add(logoLabel);
        logoTitlePanel.add(titleLabel);

        return logoTitlePanel;
    }

    private static JLabel createUserGreetingLabel() {
        JLabel userGreeting = new JLabel("Hallo Erwin", new ImageIcon("aset/erwin.png"), SwingConstants.LEFT);
        userGreeting.setForeground(Color.WHITE);
        userGreeting.setFont(SUBTITLE_FONT);
        userGreeting.setBorder(new EmptyBorder(0, 0, 0, 20));
        return userGreeting;
    }

    private static JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        contentPanel.setBackground(BACKGROUND_WHITE);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel sidebarPanel = createSidebarPanel();
        JPanel profilePanel = createProfilePanel();

        contentPanel.add(sidebarPanel);
        contentPanel.add(profilePanel);

        return contentPanel;
    }

    private static JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel(new BorderLayout());
        sidebarPanel.setBackground(BACKGROUND_WHITE);
        sidebarPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Foto profil
        JLabel profileImage = createProfileImageLabel("erwin.png", 150, 150); // Path foto profil
        profileImage.setHorizontalAlignment(SwingConstants.CENTER);

        // Nama dan email
        JLabel nameLabel = createCenteredLabel("Erwin", Font.BOLD, 18);
        JLabel emailLabel = createCenteredLabel("erwin@gmail.com", Font.PLAIN, 14);

        // Menu sidebar
        JPanel menuPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        menuPanel.setBackground(BACKGROUND_WHITE);
        menuPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JButton profileButton = createSidebarButton("Profile", "aset/icon_profile.png");
        JButton historyButton = createSidebarButton("Riwayat Reservasi", "aset/icon_history.png");
        historyButton.addActionListener(e -> showReservationHistory());

        menuPanel.add(profileButton);
        menuPanel.add(historyButton);

        sidebarPanel.add(profileImage, BorderLayout.NORTH);
        sidebarPanel.add(nameLabel, BorderLayout.CENTER);
        sidebarPanel.add(emailLabel, BorderLayout.SOUTH);
        sidebarPanel.add(menuPanel, BorderLayout.AFTER_LAST_LINE);

        return sidebarPanel;
    }

    private static JLabel createProfileImageLabel(String profilePath, int width, int height) {
        ImageIcon profileIcon;
        try {
            // Cek apakah file foto profil user ada
            profileIcon = new ImageIcon(profilePath);
        } catch (Exception e) {
            // Jika tidak ditemukan, gunakan foto profil default
            profileIcon = new ImageIcon("aset/erwin.png");
        }

        // Skalakan gambar ke ukuran tertentu
        Image scaledImage = profileIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaledImage));
    }

    private static JPanel createProfilePanel() {
        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.setBackground(BACKGROUND_WHITE);
        profilePanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel profileTitle = new JLabel("Profile");
        profileTitle.setFont(TITLE_FONT);
        profileTitle.setBorder(new EmptyBorder(0, 0, 20, 0));
        profilePanel.add(profileTitle, BorderLayout.NORTH);

        JPanel formPanel = createProfileFormPanel();
        profilePanel.add(formPanel, BorderLayout.CENTER);

        JButton editButton = createRoundedButton("Edit", ACCENT_ORANGE, Color.WHITE);
        profilePanel.add(editButton, BorderLayout.SOUTH);

        return profilePanel;
    }

    private static JButton createRoundedButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
                super.paintComponent(g);
                g2.dispose();
            }
        };
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        return button;
    }

    private static JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(SUBTITLE_FONT);
        textField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(SOFT_BLUE, 1, true),
                new EmptyBorder(5, 10, 5, 10)));
        return textField;
    }

    private static JPanel createProfileFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(BACKGROUND_WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = { "Nama", "Email", "Jenis Kelamin", "Nomor Telepon", "Alamat" };
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.3;
            JLabel label = new JLabel(labels[i]);
            label.setFont(SUBTITLE_FONT);
            formPanel.add(label, gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.7;
            if (labels[i].equals("Jenis Kelamin")) {
                JComboBox<String> genderComboBox = new JComboBox<>(new String[] { "Laki-laki", "Perempuan" });
                genderComboBox.setFont(SUBTITLE_FONT);
                formPanel.add(genderComboBox, gbc);
            } else {
                JTextField textField = createStyledTextField();
                textField.setText(getDefaultValue(labels[i]));
                formPanel.add(textField, gbc);
            }
        }

        return formPanel;
    }

    private static String getDefaultValue(String label) {
        switch (label) {
            case "Nama":
                return "Erwin";
            case "Email":
                return "erwin@gmail.com";
            case "Nomor Telepon":
                return "99999";
            case "Alamat":
                return "Rembigan";
            default:
                return "";
        }
    }

    private static JButton createSidebarButton(String text, String iconPath) {
        JButton button = new JButton(text, new ImageIcon(iconPath));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBackground(BACKGROUND_WHITE);
        button.setForeground(DARK_BLUE);
        button.setFocusPainted(false);
        button.setFont(SUBTITLE_FONT);
        button.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(LIGHT_GRAY, 1),
                new EmptyBorder(10, 10, 10, 10)));
        return button;
    }

    private static JLabel createCenteredLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", style, size));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private static ImageIcon createScaledIcon(String path, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(path);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private static void showReservationHistory() {
        // Cek apakah panel riwayat sudah ada
        if (reservationPanel == null) {
            reservationPanel = new JPanel(new BorderLayout());
            reservationPanel.setBackground(BACKGROUND_WHITE);
            reservationPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

            // Judul Riwayat Reservasi
            JLabel reservationTitle = new JLabel("Riwayat Reservasi");
            reservationTitle.setFont(TITLE_FONT);
            reservationTitle.setBorder(new EmptyBorder(0, 0, 20, 0));
            reservationTitle.setHorizontalAlignment(SwingConstants.CENTER);
            reservationPanel.add(reservationTitle, BorderLayout.NORTH);

            // Panel riwayat reservasi
            JPanel historyListPanel = new JPanel();
            historyListPanel.setLayout(new BoxLayout(historyListPanel, BoxLayout.Y_AXIS));
            historyListPanel.setBackground(BACKGROUND_WHITE);

            // Simulasi riwayat reservasi (dapat diubah dengan data dinamis)
            String[] reservationHistory = {
                    "Reservasi #1: 10 Desember 2024 - Hotel ABC",
                    "Reservasi #2: 15 Desember 2024 - Hotel XYZ",
                    "Reservasi #3: 20 Desember 2024 - Hotel MNO"
            };

            for (String history : reservationHistory) {
                JLabel historyLabel = new JLabel(history);
                historyLabel.setFont(SUBTITLE_FONT);
                historyListPanel.add(historyLabel);
            }

            JScrollPane scrollPane = new JScrollPane(historyListPanel);
            reservationPanel.add(scrollPane, BorderLayout.CENTER);
        }

        // Ganti panel konten dengan panel riwayat
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(reservationPanel);
        frame.getContentPane().removeAll(); // Menghapus semua komponen yang ada
        frame.add(reservationPanel, BorderLayout.CENTER); // Menambahkan panel riwayat
        frame.revalidate(); // Memastikan tampilan diperbarui
        frame.repaint(); // Memastikan tampilan di-render ulang
    }
}