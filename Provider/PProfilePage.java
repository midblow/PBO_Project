package Provider;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import DB.*;
import Landing_Page.PLoginForm;

public class PProfilePage {
    private static final Color DEEP_NAVY = new Color(17, 29, 48);
    private static final Color SOFT_NAVY = new Color(12, 34, 64);
    private static final Color LIGHT_GRAY = new Color(240, 242, 245);
    private static final Color ACCENT_ORANGE = new Color(255, 127, 39);
    private static final Color SOFT_WHITE = new Color(252, 253, 255);
    private static final Color TEXT_DARK = new Color(27, 47, 71);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PProfilePage::createAndShowGUI);
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
            HomeProvider.main(new String[]{});
            updateActiveButtonNavbar(berandaButton);
        });

        JButton venueButton = createNavbarButton("Booking Confirmation");
        venueButton.addActionListener(e -> {
            JPanel venueContent = createVenueContentPanel();
            mainPanel.removeAll();
            JPanel sidebar = createSidebarPanel(frame, mainPanel);
            mainPanel.add(sidebar, BorderLayout.WEST);
            mainPanel.add(venueContent, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
            frame.dispose();
            BookingConfirm.showBooking();
            updateActiveButtonNavbar(venueButton);
        });

        JButton profileButton = createNavbarButton("Profil");
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

        Provider provider = ProviderDB.getProviderByEmail(Session.loggedInProviderEmail);

        JLabel profilePicture = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon("asset/erwin.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            profilePicture.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            profilePicture.setText("Profile Pic");
        }
        profilePicture.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(provider.getLembaga(), SwingConstants.CENTER); // Nama Lembaga
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailLabel = new JLabel(provider.getGmail(), SwingConstants.CENTER); // Email
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
        JButton logoutButton = createSidebarButton("Keluar");
        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Logout Berhasil", "Logout", JOptionPane.INFORMATION_MESSAGE);
            Session.clearSessionProv();
            frame.dispose();
        });

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(profilePicture);
        sidebar.add(Box.createRigidArea(new Dimension(0, 15)));
        sidebar.add(nameLabel);
        sidebar.add(emailLabel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));
        sidebar.add(profileButton);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(logoutButton);
        sidebar.add(Box.createVerticalStrut(20));

        return sidebar;
    }

    private static JPanel createProfileContentPanel(JFrame frame, JPanel mainPanel) {
        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.setBackground(SOFT_WHITE);
    
        String loggedInProviderEmail = Session.loggedInProviderEmail;
    
        if (loggedInProviderEmail == null) {
            JOptionPane.showMessageDialog(frame, "Sesi telah berakhir. Silahkan login kembali.", "Error", JOptionPane.ERROR_MESSAGE);
            frame.dispose();
            PLoginForm.main(new String[]{}); 
            return null;
        }
    
        Provider provider = ProviderDB.getProviderByEmail(loggedInProviderEmail);
        if (provider == null) {
            JOptionPane.showMessageDialog(frame, "Data Provider tidak ditemukan. Silahkan login kembali.", "Error", JOptionPane.ERROR_MESSAGE);
            frame.dispose();
            PLoginForm.main(new String[]{}); 
            return null;
        }

        String[] providerData = new String[] {
            provider.getUsername(),
            provider.getGmail(),
            provider.getLembaga(),
            String.valueOf(provider.getNomorHp()),
            provider.getAlamat()
        };
    
        String[] labels = { "Username:", "Email:", "Lembaga", "Nomor Telepon:", "Alamat:" };
    
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(SOFT_WHITE);
        contentPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
    
        JLabel titleLabel = new JLabel("Profile Provider", SwingConstants.LEFT);
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
    
            JTextField textField = createTextField(providerData[i] != null ? providerData[i] : "");
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
                textFields[i].setText(providerData[i] != null ? providerData[i] : "");
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
                
                if (ProviderDB.isEmailExisting(email, loggedInProviderEmail)) {
                    JOptionPane.showMessageDialog(frame, "Email sudah digunakan oleh pengguna lain. Mohon gunakan email lain.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }

                boolean isUpdated = ProviderDB.updateProvider(
                    loggedInProviderEmail,
                    textFields[0].getText(), 
                    email, 
                    textFields[2].getText(), 
                    textFields[3].getText(), 
                    textFields[4].getText()
                );
        
                if (isUpdated) {
                    JOptionPane.showMessageDialog(frame, "Data berhasil diperbarui!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Session.loggedInProviderEmail = email; 
                } else {
                    JOptionPane.showMessageDialog(frame, "Gagal untuk memperbarui data.", "Error", JOptionPane.ERROR_MESSAGE);
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

    private static final JButton[] activeNavbarButton = { null };

    private static JButton createNavbarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        button.setForeground(ACCENT_ORANGE);
        button.setBackground(SOFT_WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0)); 
        button.setFocusPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button != activeNavbarButton[0]) {
                    button.setForeground(DEEP_NAVY);
                    button.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, DEEP_NAVY)); 
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
        button.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, DEEP_NAVY)); 
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
                if (button != activeSidebarButton[0]) { 
                    button.setBackground(ACCENT_ORANGE);
                }
            }
    
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button != activeSidebarButton[0]) { 
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