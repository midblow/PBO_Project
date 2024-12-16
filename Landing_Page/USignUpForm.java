package Landing_Page;

import javax.swing.*;

import DB.UserDB;

import java.awt.*;
import java.awt.event.*;

public class USignUpForm {
    public USignUpForm() {
        // Create the frame
        JFrame frame = new JFrame("Sign Up User");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 700); // Ukuran frame yang sesuai
        frame.getContentPane().setBackground(new Color(12, 34, 64)); // Background warna biru gelap
        frame.setLayout(new BorderLayout());

        // Panel utama untuk konten
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);

        // Panel rounded untuk form
        JPanel roundedPanel = new JPanel(new GridBagLayout());
        roundedPanel.setBackground(new Color(20, 44, 84)); // Panel dengan warna biru gelap
        roundedPanel.setPreferredSize(new Dimension(350, 600)); // Mengurangi tinggi panel
        roundedPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // GridBagConstraints untuk penataan komponen
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;


        // Astronaut Icon
        ImageIcon astronautIconImage = new ImageIcon("asset/hallo_user.2.png");
        Image scaledAstronautIcon = astronautIconImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); // Resize astronaut icon
        JLabel astronautIcon = new JLabel(new ImageIcon(scaledAstronautIcon));
        astronautIcon.setBounds(135, 150, 80, 80); // Tempatkan ikon di atas label Sign Up
        roundedPanel.add(astronautIcon);

        // Title "Sign Up"
        JLabel signUpLabel = new JLabel("Sign Up");
        signUpLabel.setBounds(105, 240, 140, 30); // Tempatkan label tepat di bawah ikon
        signUpLabel.setForeground(Color.WHITE);
        signUpLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        signUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        roundedPanel.add(signUpLabel);


        // Email Label and Field with Icon inside
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        roundedPanel.add(emailLabel, gbc);

        // Panel untuk email field dengan ikon di kanan
        JPanel emailPanel = new JPanel(new BorderLayout());
        emailPanel.setOpaque(false); // Menonaktifkan background panel, agar transparan
        JTextField emailField = new JTextField();
        emailField.setBackground(new Color(12, 34, 64));
        emailField.setForeground(Color.WHITE);
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        emailField.setCaretColor(Color.WHITE);
        emailField.setPreferredSize(new Dimension(200, 30)); // Ukuran input field
        emailPanel.add(emailField, BorderLayout.CENTER);

        ImageIcon emailIcon = new ImageIcon("asset/email.png"); // Path to email icon
        JLabel emailIconLabel = new JLabel(new ImageIcon(emailIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        emailPanel.add(emailIconLabel, BorderLayout.EAST); // Letakkan ikon di sebelah kanan

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        roundedPanel.add(emailPanel, gbc);

        // Nama Label and Field with Icon inside
        JLabel namaLabel = new JLabel("Nama");
        namaLabel.setForeground(Color.WHITE);
        namaLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        roundedPanel.add(namaLabel, gbc);

        JPanel namaPanel = new JPanel(new BorderLayout());
        namaPanel.setOpaque(false); // Menonaktifkan background panel, agar transparan
        JTextField namaField = new JTextField();
        namaField.setBackground(new Color(12, 34, 64));
        namaField.setForeground(Color.WHITE);
        namaField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        namaField.setCaretColor(Color.WHITE);
        namaPanel.add(namaField, BorderLayout.CENTER);

        ImageIcon namaIcon = new ImageIcon("asset/nama.png"); // Path to nama icon
        JLabel namaIconLabel = new JLabel(new ImageIcon(namaIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        namaPanel.add(namaIconLabel, BorderLayout.EAST); // Letakkan ikon di sebelah kanan

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        roundedPanel.add(namaPanel, gbc);

        // Password Label and Field with Icon inside
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        roundedPanel.add(passwordLabel, gbc);

        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setOpaque(false); // Menonaktifkan background panel, agar transparan
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBackground(new Color(12, 34, 64));
        passwordField.setForeground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        passwordField.setCaretColor(Color.WHITE);
        passwordPanel.add(passwordField, BorderLayout.CENTER);

        ImageIcon passwordIcon = new ImageIcon("asset/password.png"); // Path to password icon
        JLabel passwordIconLabel = new JLabel(new ImageIcon(passwordIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        passwordPanel.add(passwordIconLabel, BorderLayout.EAST); // Letakkan ikon di sebelah kanan

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        roundedPanel.add(passwordPanel, gbc);

        // Gender Label and ComboBox with Icon inside
        JLabel genderLabel = new JLabel("Jenis Kelamin");
        genderLabel.setForeground(Color.WHITE);
        genderLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        roundedPanel.add(genderLabel, gbc);

        JPanel genderPanel = new JPanel(new BorderLayout());
        genderPanel.setOpaque(false); // Menonaktifkan background panel, agar transparan
        JComboBox<String> genderCombo = new JComboBox<>(new String[] {"Laki-Laki", "Perempuan"});
        genderCombo.setBackground(new Color(12, 34, 64));
        genderCombo.setForeground(Color.WHITE);
        genderCombo.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        genderPanel.add(genderCombo, BorderLayout.CENTER);

        ImageIcon genderIcon = new ImageIcon("asset/gender.png");
        JLabel genderIconLabel = new JLabel(new ImageIcon(genderIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        genderPanel.add(genderIconLabel, BorderLayout.EAST); 

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        roundedPanel.add(genderPanel, gbc);

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
                String name = namaField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String gender = (String) genderCombo.getSelectedItem();
                String nomorHpStr = phoneField.getText().trim();
                String alamat = alamatField.getText().trim();

                if (email.isEmpty() || name.isEmpty() || password.isEmpty() || 
                    gender.isEmpty() || nomorHpStr.isEmpty() || alamat.isEmpty()) {
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

                if (UserDB.isEmailExisting(email, "")) {
                    JOptionPane.showMessageDialog(null, "Email sudah digunakan!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean isAdded = UserDB.addUser(email, name, password, gender, nomorHp, alamat);

                if (isAdded) {
                    JOptionPane.showMessageDialog(null, "Pendaftaran berhasil!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose(); 
                    ULoginForm.main(new String[]{});
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
                ULoginForm.main(new String[]{}); 
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 3;
        roundedPanel.add(loginLabel, gbc);

        // Wrap rounded panel in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(roundedPanel);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the contentPanel to the frame
        frame.add(contentPanel, BorderLayout.CENTER);

        // Center the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}