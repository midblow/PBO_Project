package Provider;

import javax.swing.*;

import DB.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddVenue {
    private static JPanel navbarPanel;
    private static String selectedImagePath = null;
    private static JFrame frame;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Add Venue");
            frame.setSize(1200, 800);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JPanel mainContainerPanel = new JPanel();
            mainContainerPanel.setLayout(new BorderLayout()); 

            JPanel headerPanel = new JPanel();
            createHeader(headerPanel);

            navbarPanel = createNavbar(frame); 

            mainContainerPanel.add(headerPanel, BorderLayout.NORTH);
            mainContainerPanel.add(navbarPanel, BorderLayout.CENTER);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridBagLayout());
            mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
            mainPanel.setBackground(new Color(245, 245, 245)); 
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;

            Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
            Color labelColor = new Color(45, 55, 72);

            JLabel namaVenueLabel = createFormLabel("Nama Venue", labelFont, labelColor);
            JTextField namaVenueField = createTextField(20);

            JLabel deskripsiLabel = createFormLabel("Deskripsi dan Fasilitas", labelFont, labelColor);
            JTextArea deskripsiArea = createTextArea(5, 40);
            JScrollPane deskripsiScroll = new JScrollPane(deskripsiArea);

            JLabel alamatLabel = createFormLabel("Alamat", labelFont, labelColor);
            JTextField alamatField = createTextField(20);

            JLabel kotaLabel = createFormLabel("Kota", labelFont, labelColor);
            JTextField kotaField = createTextField(20);

            JLabel penanggungJawabLabel = createFormLabel("Nama Penanggung Jawab", labelFont, labelColor);
            JTextField penanggungJawabField = createTextField(20);

            JLabel kapasitasLabel = createFormLabel("Kapasitas", labelFont, labelColor);
            JTextField kapasitasField = createTextField(10);
            JLabel hargaLabel = createFormLabel("Harga Rp.", labelFont, labelColor);
            JTextField hargaField = createTextField(10);

            JPanel kapasitasHargaPanel = new JPanel(new GridLayout(1, 4, 10, 0));
            kapasitasHargaPanel.add(kapasitasLabel);
            kapasitasHargaPanel.add(kapasitasField);
            kapasitasHargaPanel.add(hargaLabel);
            kapasitasHargaPanel.add(hargaField);

            JLabel tambahFotoLabel = createFotoLabel();

            JLabel tipeVenueLabel = createFormLabel("Tipe Venue", labelFont, labelColor);
            JRadioButton pemerintahButton = new JRadioButton("Pemerintah");
            JRadioButton swastaButton = new JRadioButton("Swasta");
            
            pemerintahButton.setActionCommand("Pemerintah");
            swastaButton.setActionCommand("Swasta");
            
            ButtonGroup tipeVenueGroup = new ButtonGroup();
            tipeVenueGroup.add(pemerintahButton);
            tipeVenueGroup.add(swastaButton);

            JPanel tipeVenuePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
            pemerintahButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            swastaButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            tipeVenuePanel.add(pemerintahButton);
            tipeVenuePanel.add(swastaButton);

            JCheckBox mainVenueCheckbox = new JCheckBox("Jadikan Sebagai Main Venue");
            mainVenueCheckbox.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            JButton submitButton = createSubmitButton(namaVenueField, deskripsiArea, alamatField, kotaField, 
            penanggungJawabField, kapasitasField, hargaField, 
            tipeVenueGroup, mainVenueCheckbox);

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

            GridBagConstraints submitGbc = (GridBagConstraints) gbc.clone();
            submitGbc.insets = new Insets(10, 10, 50, 10); 
            mainPanel.add(submitButton, submitGbc);

            frame.setLayout(new BorderLayout());
            frame.add(mainContainerPanel, BorderLayout.NORTH); 
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
                String namaVenue = namaVenueField.getText();
                String deskripsi = deskripsiArea.getText();
                String alamat = alamatField.getText();
                String kota = kotaField.getText();
                String penanggungJawab = penanggungJawabField.getText();
                boolean isMain = mainVenueCheckbox.isSelected();

                if (namaVenue.isEmpty() || deskripsi.isEmpty() || alamat.isEmpty() || kota.isEmpty() ||
                    penanggungJawab.isEmpty() || tipeVenueGroup.getSelection() == null) {
                    JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int kapasitas = Integer.parseInt(kapasitasField.getText());
                int harga = Integer.parseInt(hargaField.getText());

                String tipeVenue = tipeVenueGroup.getSelection().getActionCommand();

                if (selectedImagePath == null || selectedImagePath.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Harap pilih gambar venue!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int idProvider = Session.loggedInProviderId;

                boolean success = VenueDB.insertVenue( namaVenue, deskripsi, alamat, kota, penanggungJawab,
                kapasitas, harga, tipeVenue, isMain, selectedImagePath, idProvider);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Venue berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose(); 
                    HomeProvider.main(new String[]{});
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menambahkan venue.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Kapasitas dan Harga harus berupa angka positif!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return button;
    }

    public static void createHeader(JPanel headerPanel) {
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(24, 49, 83)); 
        headerPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16)); 
        headerPanel.setPreferredSize(new Dimension(1000, 80)); 

        JPanel logoRevenuePanel = new JPanel();
        logoRevenuePanel.setBackground(new Color(24, 49, 83));
        logoRevenuePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

        JLabel logoLabel = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon("asset/logo.png");
            if (originalIcon.getIconWidth() != -1) {
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

        JLabel revenueLabel = new JLabel("REVENUE");
        revenueLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        revenueLabel.setForeground(Color.WHITE);

        logoRevenuePanel.add(logoLabel);
        logoRevenuePanel.add(revenueLabel);

        headerPanel.add(logoRevenuePanel, BorderLayout.WEST);
    }

    public static JPanel createNavbar(JFrame frame) {
        JPanel navbarPanel = new JPanel();
        navbarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        navbarPanel.setBackground(Color.WHITE);
    
        JLabel myVenueLabel = createNavLink("Home");
        JLabel bookingConfirmationLabel = createNavLink("Booking Confirmation");
        JLabel profileLabel = createNavLink("Profile");
    
        addNavbarLinkMouseListeners(myVenueLabel, "Home", navbarPanel);
        addNavbarLinkMouseListeners(bookingConfirmationLabel, "Booking Confirmation", navbarPanel);
        addNavbarLinkMouseListeners(profileLabel, "Profile", navbarPanel);
    
        navbarPanel.add(myVenueLabel);
        navbarPanel.add(bookingConfirmationLabel);
        navbarPanel.add(profileLabel);
    
        return navbarPanel;
    }

    private static JLabel createNavLink(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setForeground(new Color(255, 120, 45)); 
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
        return label;
    }

    private static void addNavbarLinkMouseListeners(JLabel label, String page, JPanel navbarPanel) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(new Color(66, 133, 244)); 
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(66, 133, 244))); // Underline on hover
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                if (!page.equals(activePage)) {
                    label.setForeground(new Color(255, 120, 45)); 
                    label.setBorder(BorderFactory.createEmptyBorder()); 
                }
            }
    
            @Override
            public void mouseClicked(MouseEvent e) {
                setActivePage(page, navbarPanel); 
                frame.dispose(); 
    
                if (page.equals("Home")) {
                    HomeProvider.main(new String[]{}); 
                } else if (page.equals("Profile")) {
                    PProfilePage.main(new String[]{}); 
                } else if (page.equals("Booking Confirmation")) {
                    BookingConfirm.showBooking();
                }
            }
        });
    }

    private static String activePage = "Home"; 

    private static void setActivePage(String page, JPanel navbarPanel) {
        activePage = page;
        for (Component comp : navbarPanel.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                if (label.getText().equals(page)) {
                    label.setForeground(new Color(66, 133, 244)); 
                    label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(66, 133, 244))); // Underline
                } else {
                    label.setForeground(new Color(255, 120, 45)); 
                    label.setBorder(BorderFactory.createEmptyBorder()); 
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
        tambahFotoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
        tambahFotoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Pilih Foto Venue");
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Gambar (*.jpg, *.jpeg, *.png)", "jpg", "jpeg", "png"));
    
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    tambahFotoLabel.setText("Foto Dipilih: " + selectedFile.getName());
                    selectedImagePath = selectedFile.getAbsolutePath(); 
                }
            }
        });
    
        return tambahFotoLabel;
    }
}