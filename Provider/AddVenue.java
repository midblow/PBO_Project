package Provider;

import javax.swing.*;

import DB.VenueDB;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddVenue {
    // Add static reference to navbarPanel to fix the setActivePage method
    private static JPanel navbarPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Frame Utama
            JFrame frame = new JFrame("My Venue");
            frame.setSize(1200, 800);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            // Panel Utama untuk Header dan Navbar
            JPanel mainContainerPanel = new JPanel();
            mainContainerPanel.setLayout(new BorderLayout()); // Untuk tata letak header dan navbar

            // Header Panel
            JPanel headerPanel = new JPanel();
            createHeader(headerPanel);

            // Navbar Panel
            navbarPanel = createNavbar(); // Assign to static reference

            // Tambahkan Header dan Navbar ke mainContainerPanel
            mainContainerPanel.add(headerPanel, BorderLayout.NORTH);
            mainContainerPanel.add(navbarPanel, BorderLayout.CENTER);

            // Panel Form Utama
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridBagLayout());
            mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
            mainPanel.setBackground(new Color(245, 245, 245)); // Background warna abu
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;

            // Styling for form labels
            Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
            Color labelColor = new Color(45, 55, 72);

            // Nama Venue
            JLabel namaVenueLabel = createFormLabel("Nama Venue", labelFont, labelColor);
            JTextField namaVenueField = createTextField(20);

            // Deskripsi dan Fasilitas
            JLabel deskripsiLabel = createFormLabel("Deskripsi dan Fasilitas", labelFont, labelColor);
            JTextArea deskripsiArea = createTextArea(5, 40);
            JScrollPane deskripsiScroll = new JScrollPane(deskripsiArea);

            // Alamat
            JLabel alamatLabel = createFormLabel("Alamat", labelFont, labelColor);
            JTextField alamatField = createTextField(20);

            // Kota
            JLabel kotaLabel = createFormLabel("Kota", labelFont, labelColor);
            JTextField kotaField = createTextField(20);

            // Nama Penanggung Jawab
            JLabel penanggungJawabLabel = createFormLabel("Nama Penanggung Jawab", labelFont, labelColor);
            JTextField penanggungJawabField = createTextField(20);

            // Kapasitas dan Harga Panel
            JLabel kapasitasLabel = createFormLabel("Kapasitas", labelFont, labelColor);
            JTextField kapasitasField = createTextField(10);
            JLabel hargaLabel = createFormLabel("Harga Rp.", labelFont, labelColor);
            JTextField hargaField = createTextField(10);

            JPanel kapasitasHargaPanel = new JPanel(new GridLayout(1, 4, 10, 0));
            kapasitasHargaPanel.add(kapasitasLabel);
            kapasitasHargaPanel.add(kapasitasField);
            kapasitasHargaPanel.add(hargaLabel);
            kapasitasHargaPanel.add(hargaField);

            // Tambahkan Foto Label
            JLabel tambahFotoLabel = createFotoLabel();

            // Tipe Venue
            JLabel tipeVenueLabel = createFormLabel("Tipe Venue", labelFont, labelColor);
            JRadioButton pemerintahButton = new JRadioButton("Pemerintah");
            JRadioButton swastaButton = new JRadioButton("Swasta");
            ButtonGroup tipeVenueGroup = new ButtonGroup();
            tipeVenueGroup.add(pemerintahButton);
            tipeVenueGroup.add(swastaButton);

            JPanel tipeVenuePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
            pemerintahButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            swastaButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            tipeVenuePanel.add(pemerintahButton);
            tipeVenuePanel.add(swastaButton);

            // Checkbox Main Venue
            JCheckBox mainVenueCheckbox = new JCheckBox("Jadikan Sebagai Main Venue");
            mainVenueCheckbox.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            // Submit Button
            JButton submitButton = createSubmitButton(namaVenueField, deskripsiArea, alamatField, kotaField, 
            penanggungJawabField, kapasitasField, hargaField, 
            tipeVenueGroup, mainVenueCheckbox);

            // Add components to main panel with GridBagLayout
            mainPanel.add(namaVenueLabel, gbc);
            mainPanel.add(namaVenueField, gbc);
            mainPanel.add(deskripsiLabel, gbc);
            mainPanel.add(deskripsiScroll, gbc);
            mainPanel.add(alamatLabel, gbc);
            mainPanel.add(alamatField, gbc);
            mainPanel.add(kotaLabel, gbc);
            mainPanel.add(kotaField, gbc);
            mainPanel.add(penanggungJawabLabel, gbc);
            mainPanel.add(penanggungJawabField, gbc);
            mainPanel.add(kapasitasHargaPanel, gbc);
            mainPanel.add(tambahFotoLabel, gbc);
            mainPanel.add(tipeVenueLabel, gbc);
            mainPanel.add(tipeVenuePanel, gbc);
            mainPanel.add(mainVenueCheckbox, gbc);

            // Set special constraints for submit button to add more bottom margin
            GridBagConstraints submitGbc = (GridBagConstraints) gbc.clone();
            submitGbc.insets = new Insets(10, 10, 50, 10); // Increased bottom inset to 50 pixels
            mainPanel.add(submitButton, submitGbc);

            // Tambahkan mainContainerPanel (Header + Navbar) dan mainPanel ke Frame
            frame.setLayout(new BorderLayout());
            frame.add(mainContainerPanel, BorderLayout.NORTH); // Header dan Navbar di bagian atas
            frame.add(new JScrollPane(mainPanel), BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }

    private static JButton createSubmitButton(JTextField namaVenueField, JTextArea deskripsiArea,
    JTextField alamatField, JTextField kotaField,
    JTextField penanggungJawabField, JTextField kapasitasField,
    JTextField hargaField, ButtonGroup tipeVenueGroup,
    JCheckBox mainVenueCheckbox) {
    JButton button = new JButton("Submit");
    button.setFont(new Font("Segoe UI", Font.BOLD, 14));
    button.setBackground(new Color(66, 133, 244));
    button.setForeground(Color.WHITE);
    button.setFocusPainted(false);

    button.addActionListener(e -> {
        try {
            // Ambil nilai dari field input
            String namaVenue = namaVenueField.getText();
            String deskripsi = deskripsiArea.getText();
            String alamat = alamatField.getText();
            String kota = kotaField.getText();
            String penanggungJawab = penanggungJawabField.getText();
            boolean isMain = mainVenueCheckbox.isSelected();

            // Validasi input wajib
            if (namaVenue.isEmpty() || deskripsi.isEmpty() || alamat.isEmpty() || kota.isEmpty() ||
            penanggungJawab.isEmpty() || tipeVenueGroup.getSelection() == null) {
            JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }

            // Validasi kapasitas (hanya angka bulat)
            int kapasitas;
            try {
                kapasitas = Integer.parseInt(kapasitasField.getText());
                if (kapasitas <= 0) {
                    JOptionPane.showMessageDialog(null, "Kapasitas harus berupa angka positif!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Kapasitas harus berupa angka bulat!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validasi harga (hanya angka bulat)
            int harga;
            try {
                harga = Integer.parseInt(hargaField.getText());
                if (harga <= 0) {
                    JOptionPane.showMessageDialog(null, "Harga harus berupa angka positif!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Harga harus berupa angka bulat!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Ambil tipe venue
            String tipeVenue = tipeVenueGroup.getSelection().getActionCommand();

            // Insert ke database
            boolean success = VenueDB.insertVenue(namaVenue, deskripsi, alamat, kota, penanggungJawab,
                        kapasitas, harga, tipeVenue, isMain);

            // Tampilkan pesan berdasarkan hasil
            if (success) {
                JOptionPane.showMessageDialog(null, "Venue berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menambahkan venue.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        return button;
    }


    // Header setup
    public static void createHeader(JPanel headerPanel) {
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(24, 49, 83)); // Blue-900 background
        headerPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16)); // Padding
        headerPanel.setPreferredSize(new Dimension(1000, 80)); // Height of the header

        // Panel for Logo and Revenue
        JPanel logoRevenuePanel = new JPanel();
        logoRevenuePanel.setBackground(new Color(24, 49, 83));
        logoRevenuePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

        // Logo
        JLabel logoLabel = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon("asset/logo.png");
            if (originalIcon.getIconWidth() != -1) {
                // Resize logo to a smaller, fixed size (e.g., 40x40 pixels)
                Image resizedImage = originalIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImage);
                logoLabel.setIcon(resizedIcon);
            } else {
                logoLabel.setText("LOGO");
                logoLabel.setForeground(Color.WHITE);
            }
        } catch (Exception e) {
            logoLabel.setText("LOGO");
            logoLabel.setForeground(Color.WHITE);
        }

        // Revenue Text
        JLabel revenueLabel = new JLabel("REVENUE");
        revenueLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        revenueLabel.setForeground(Color.WHITE);

        // Add Logo and Revenue to logoRevenuePanel
        logoRevenuePanel.add(logoLabel);
        logoRevenuePanel.add(revenueLabel);

        // Add logoRevenuePanel to the header
        headerPanel.add(logoRevenuePanel, BorderLayout.WEST);
    }

    public static JPanel createNavbar() {
        // Navbar Panel
        JPanel navbarPanel = new JPanel();
        navbarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10)); // Horizontal layout
        navbarPanel.setBackground(new Color(255, 255, 255)); // White background for navbar
        navbarPanel.setPreferredSize(new Dimension(1000, 50)); // Navbar height

        // Navbar Links
        JLabel myVenueLabel = createNavLink("Home");
        JLabel bookingConfirmationLabel = createNavLink("Booking Confirmation");
        JLabel profileLabel = createNavLink("Profile");

        // Add links to navbar
        navbarPanel.add(myVenueLabel);
        navbarPanel.add(bookingConfirmationLabel);
        navbarPanel.add(profileLabel);

        // Add mouse listeners to change active state and hover effects
        addNavbarLinkMouseListeners(myVenueLabel, "Home", navbarPanel);
        addNavbarLinkMouseListeners(bookingConfirmationLabel, "Booking Confirmation", navbarPanel);
        addNavbarLinkMouseListeners(profileLabel, "Profile", navbarPanel);

        return navbarPanel;
    }

    private static JLabel createNavLink(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setForeground(new Color(255, 120, 45)); // Orange color when not hovered
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor to hand on hover
        return label;
    }

    private static void addNavbarLinkMouseListeners(JLabel label, String page, JPanel navbarPanel) {
        // Hover effect: Change color to dark blue when mouse is over the label
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(new Color(66, 133, 244)); // Dark blue on hover
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(66, 133, 244))); // Underline on
                                                                                                       // hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Revert to original color when mouse exits
                if (!page.equals(activePage)) { // Only revert if it's not the active page
                    label.setForeground(new Color(255, 120, 45)); // Orange color
                    label.setBorder(BorderFactory.createEmptyBorder()); // Remove underline
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                setActivePage(page, navbarPanel); // Set this page as active on click
            }
        });
    }

    private static String activePage = "Home"; // Default active page

    // Method to set active page and update navbar state
    private static void setActivePage(String page, JPanel navbarPanel) {
        activePage = page;
        // Update navbar link states
        for (Component comp : navbarPanel.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                if (label.getText().equals(page)) {
                    label.setForeground(new Color(66, 133, 244)); // Dark blue for active page
                    label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(66, 133, 244))); // Underline
                } else {
                    label.setForeground(new Color(255, 120, 45)); // Orange color for inactive links
                    label.setBorder(BorderFactory.createEmptyBorder()); // Remove underline
                }
            }
        }
    }

    private static JLabel createFormLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    private static JTextField createTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return textField;
    }

    private static JTextArea createTextArea(int rows, int columns) {
        JTextArea textArea = new JTextArea(rows, columns);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }

    private static JLabel createFotoLabel() {
        JLabel tambahFotoLabel = new JLabel("+ Tambah Foto");
        tambahFotoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tambahFotoLabel.setForeground(new Color(60, 120, 216));
        tambahFotoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Menjadikan cursor berbentuk tangan

        // Tambahkan aksi ketika label diklik
        tambahFotoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Membuka file chooser
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Pilih Foto"); // Judul dialog
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Hanya file, bukan folder

                // Filter agar hanya menampilkan file gambar
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                        "Gambar (*.jpg, *.jpeg, *.png)", "jpg", "jpeg", "png"));

                int result = fileChooser.showOpenDialog(null); // Tampilkan dialog

                if (result == JFileChooser.APPROVE_OPTION) {
                    // Ambil file yang dipilih
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    System.out.println("File dipilih: " + filePath);

                    // Tampilkan pesan sukses atau update UI jika perlu
                    tambahFotoLabel.setText("Foto Dipilih: " + selectedFile.getName());
                }
            }
        });

        return tambahFotoLabel;
    }
}