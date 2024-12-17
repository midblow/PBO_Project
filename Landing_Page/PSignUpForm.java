package Landing_Page;

import javax.swing.*;

import DB.ProviderDB;

import java.awt.*;
import java.awt.event.*;

public class PSignUpForm {
    public PSignUpForm() {
        JFrame frame = new JFrame("Sign Up Provider");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 700); 
        frame.getContentPane().setBackground(new Color(12, 34, 64)); 
        frame.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);

        JPanel roundedPanel = new JPanel(new GridBagLayout());
        roundedPanel.setBackground(new Color(20, 44, 84)); 
        roundedPanel.setPreferredSize(new Dimension(350, 600)); 
        roundedPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;


        ImageIcon astronautIconImage = new ImageIcon("asset/astroAwal.png");
        Image scaledAstronautIcon = astronautIconImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); // Resize astronaut icon
        JLabel astronautIcon = new JLabel(new ImageIcon(scaledAstronautIcon));
        astronautIcon.setBounds(135, 150, 80, 80); 
        roundedPanel.add(astronautIcon);

        JLabel signUpLabel = new JLabel("Sign Up");
        signUpLabel.setBounds(105, 240, 140, 30); 
        signUpLabel.setForeground(Color.WHITE);
        signUpLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        signUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        roundedPanel.add(signUpLabel);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        roundedPanel.add(emailLabel, gbc);

        JPanel emailPanel = new JPanel(new BorderLayout());
        emailPanel.setOpaque(false); 
        JTextField emailField = new JTextField();
        emailField.setBackground(new Color(12, 34, 64));
        emailField.setForeground(Color.WHITE);
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        emailField.setCaretColor(Color.WHITE);
        emailField.setPreferredSize(new Dimension(200, 30)); 
        emailPanel.add(emailField, BorderLayout.CENTER);

        ImageIcon emailIcon = new ImageIcon("asset/email.png"); 
        JLabel emailIconLabel = new JLabel(new ImageIcon(emailIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        emailPanel.add(emailIconLabel, BorderLayout.EAST);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        roundedPanel.add(emailPanel, gbc);

        JLabel namaLabel = new JLabel("Username");
        namaLabel.setForeground(Color.WHITE);
        namaLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        roundedPanel.add(namaLabel, gbc);

        JPanel namaPanel = new JPanel(new BorderLayout());
        namaPanel.setOpaque(false); 
        JTextField namaField = new JTextField();
        namaField.setBackground(new Color(12, 34, 64));
        namaField.setForeground(Color.WHITE);
        namaField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        namaField.setCaretColor(Color.WHITE);
        namaPanel.add(namaField, BorderLayout.CENTER);

        ImageIcon namaIcon = new ImageIcon("asset/nama.png"); 
        JLabel namaIconLabel = new JLabel(new ImageIcon(namaIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        namaPanel.add(namaIconLabel, BorderLayout.EAST); 
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        roundedPanel.add(namaPanel, gbc);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        roundedPanel.add(passwordLabel, gbc);

        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setOpaque(false); 
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBackground(new Color(12, 34, 64));
        passwordField.setForeground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        passwordField.setCaretColor(Color.WHITE);
        passwordPanel.add(passwordField, BorderLayout.CENTER);

        ImageIcon passwordIcon = new ImageIcon("asset/password.png"); 
        JLabel passwordIconLabel = new JLabel(new ImageIcon(passwordIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        passwordPanel.add(passwordIconLabel, BorderLayout.EAST); 

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        roundedPanel.add(passwordPanel, gbc);

        JLabel lembagaLabel = new JLabel("Lembaga");
        lembagaLabel.setForeground(Color.WHITE);
        lembagaLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        roundedPanel.add(lembagaLabel, gbc);

        JPanel lembagaPanel = new JPanel(new BorderLayout());
        lembagaPanel.setOpaque(false); 
        JTextField lembagaField = new JTextField();
        lembagaField.setBackground(new Color(12, 34, 64));
        lembagaField.setForeground(Color.WHITE);
        lembagaField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        lembagaField.setCaretColor(Color.WHITE);
        lembagaPanel.add(lembagaField, BorderLayout.CENTER); 

        ImageIcon lembagaIcon = new ImageIcon("asset/lembaga.png"); 
        JLabel lembagaIconLabel = new JLabel(new ImageIcon(lembagaIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        lembagaPanel.add(lembagaIconLabel, BorderLayout.EAST); 

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        roundedPanel.add(lembagaPanel, gbc);

        JLabel phoneLabel = new JLabel("Nomor Telepon");
        phoneLabel.setForeground(Color.WHITE);
        phoneLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        roundedPanel.add(phoneLabel, gbc);

        JPanel phonePanel = new JPanel(new BorderLayout());
        phonePanel.setOpaque(false); 
        JTextField phoneField = new JTextField();
        phoneField.setBackground(new Color(12, 34, 64));
        phoneField.setForeground(Color.WHITE);
        phoneField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        phoneField.setCaretColor(Color.WHITE);
        phonePanel.add(phoneField, BorderLayout.CENTER);

        ImageIcon phoneIcon = new ImageIcon("asset/phone.png"); 
        JLabel phoneIconLabel = new JLabel(new ImageIcon(phoneIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        phonePanel.add(phoneIconLabel, BorderLayout.EAST); 

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        roundedPanel.add(phonePanel, gbc);

        JLabel alamatLabel = new JLabel("Alamat");
        alamatLabel.setForeground(Color.WHITE);
        alamatLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 7; 
        gbc.gridwidth = 1;
        roundedPanel.add(alamatLabel, gbc);

        JPanel alamatPanel = new JPanel(new BorderLayout());
        alamatPanel.setOpaque(false); 
        JTextField alamatField = new JTextField();
        alamatField.setBackground(new Color(12, 34, 64));
        alamatField.setForeground(Color.WHITE);
        alamatField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        alamatField.setCaretColor(Color.WHITE);
        alamatField.setPreferredSize(new Dimension(200, 30)); 
        alamatPanel.add(alamatField, BorderLayout.CENTER);

        ImageIcon alamatIconImage = new ImageIcon("asset/location.png"); 
        JLabel alamatIconLabel = new JLabel(new ImageIcon(alamatIconImage.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        alamatPanel.add(alamatIconLabel, BorderLayout.EAST); 

        gbc.gridx = 1;
        gbc.gridy = 7; 
        gbc.gridwidth = 2;
        roundedPanel.add(alamatPanel, gbc);

        JCheckBox termsCheckBox = new JCheckBox("I Agree to the Terms & Conditions");
        termsCheckBox.setForeground(Color.WHITE);
        termsCheckBox.setBackground(new Color(15, 32, 55));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        roundedPanel.add(termsCheckBox, gbc);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(Color.WHITE);
        signUpButton.setForeground(new Color(12, 34, 64));
        signUpButton.setFocusPainted(false);
        signUpButton.setFont(new Font("Poppins", Font.BOLD, 14));
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String username = namaField.getText().trim();
                String lembaga = lembagaField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String nomorHpStr = phoneField.getText().trim();
                String alamat = alamatField.getText().trim();

                if (email.isEmpty() || username.isEmpty() || lembaga.isEmpty() || 
                    password.isEmpty() || nomorHpStr.isEmpty() || alamat.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                long nomorHp;
                try {
                    nomorHp = Long.parseLong(nomorHpStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Nomor telepon harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (ProviderDB.isEmailExisting(email, "")) {
                    JOptionPane.showMessageDialog(null, "Email sudah digunakan!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean isAdded = ProviderDB.addProvider(email, username, lembaga, password, nomorHp, alamat);

                if (isAdded) {
                    JOptionPane.showMessageDialog(null, "Pendaftaran berhasil!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    PLoginForm.main(new String[]{});
                } else {
                    JOptionPane.showMessageDialog(null, "Pendaftaran gagal. Silakan coba lagi.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        roundedPanel.add(signUpButton, gbc);

        JLabel loginLabel = new JLabel("Already have an account? Log In");
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                PLoginForm.main(new String[]{}); 
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 3;
        roundedPanel.add(loginLabel, gbc);

        JScrollPane scrollPane = new JScrollPane(roundedPanel);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(contentPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}