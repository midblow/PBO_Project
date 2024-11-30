import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class USignUpForm {
    public USignUpForm() {
        // Create the frame
        JFrame frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 750); // Ukuran frame
        frame.getContentPane().setBackground(new Color(12, 34, 64)); // Background warna biru gelap
        frame.setLayout(new BorderLayout());  // Ganti ke BorderLayout agar scroll pane dapat ditambahkan dengan mudah
        
        // Panel utama untuk isi konten dengan padding
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false); // Set agar tidak ada background pada contentPanel
        
        // Panel utama rounded
        JPanel roundedPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        roundedPanel.setBackground(new Color(20, 44, 84)); // Panel dalam warna biru gelap
        roundedPanel.setPreferredSize(new Dimension(350, 1000)); // Atur ukuran panel agar lebih panjang
        roundedPanel.setLayout(null);
        roundedPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding di sekitar panel

        // Icon astronaut
        ImageIcon astronautIconImage = new ImageIcon("PBO_Project/iconLoginU/hallo_user.2.png");
        Image scaledAstronautIcon = astronautIconImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); // Resize astronaut icon
        JLabel astronautIcon = new JLabel(new ImageIcon(scaledAstronautIcon));
        astronautIcon.setBounds(135, 30, 80, 80); // Posisikan lebih tengah
        roundedPanel.add(astronautIcon);

        // Title "Sign Up"
        JLabel signUpLabel = new JLabel("Sign Up");
        signUpLabel.setBounds(105, 120, 140, 30); // Posisikan lebih tinggi
        signUpLabel.setForeground(Color.WHITE);
        signUpLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        signUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        roundedPanel.add(signUpLabel);

        // Email Label
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(70, 170, 40, 30);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        roundedPanel.add(emailLabel);

        // Email field
        JTextField emailField = new JTextField();
        emailField.setBounds(70, 200, 220, 30);
        emailField.setBackground(new Color(12, 34, 64));
        emailField.setForeground(Color.WHITE);
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        emailField.setCaretColor(Color.WHITE);
        roundedPanel.add(emailField);

        // Email Icon
        ImageIcon emailIconImage = new ImageIcon("PBO_Project/iconLoginU/email.png");
        Image scaledEmailIcon = emailIconImage.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel emailIcon = new JLabel(new ImageIcon(scaledEmailIcon));
        emailIcon.setBounds(300, 200, 20, 20); // Ikon sejajar dengan input field
        roundedPanel.add(emailIcon);

        // Nama Label
        JLabel namaLabel = new JLabel("Nama");
        namaLabel.setBounds(70, 240, 80, 30);
        namaLabel.setForeground(Color.WHITE);
        namaLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        roundedPanel.add(namaLabel);

        // Nama field
        JTextField namaField = new JTextField();
        namaField.setBounds(70, 270, 220, 30);
        namaField.setBackground(new Color(12, 34, 64));
        namaField.setForeground(Color.WHITE);
        namaField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        namaField.setCaretColor(Color.WHITE);
        roundedPanel.add(namaField);

        // Nama Icon
        ImageIcon namaIconImage = new ImageIcon("PBO_Project/iconLoginU/nama.png");
        Image scaledNamaIcon = namaIconImage.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel namaIcon = new JLabel(new ImageIcon(scaledNamaIcon));
        namaIcon.setBounds(300, 270, 20, 20); // Ikon sejajar dengan input field
        roundedPanel.add(namaIcon);

        // Password Label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(70, 310, 80, 30);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        roundedPanel.add(passwordLabel);

        // Password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(70, 340, 220, 30);
        passwordField.setBackground(new Color(12, 34, 64));
        passwordField.setForeground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        passwordField.setCaretColor(Color.WHITE);
        roundedPanel.add(passwordField);

        // Password Icon
        ImageIcon passwordIconImage = new ImageIcon("PBO_Project/iconLoginU/password.png");
        Image scaledPasswordIcon = passwordIconImage.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel passwordIcon = new JLabel(new ImageIcon(scaledPasswordIcon));
        passwordIcon.setBounds(300, 340, 20, 20); // Ikon sejajar dengan input field
        roundedPanel.add(passwordIcon);

        // Gender Label
        JLabel genderLabel = new JLabel("Jenis Kelamin");
        genderLabel.setBounds(70, 380, 100, 30);
        genderLabel.setForeground(Color.WHITE);
        genderLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        roundedPanel.add(genderLabel);

        // Gender ComboBox
        String[] genderOptions = {"Laki-Laki", "Perempuan"};
        JComboBox<String> genderCombo = new JComboBox<>(genderOptions);
        genderCombo.setBounds(70, 410, 220, 30);
        genderCombo.setBackground(new Color(12, 34, 64));
        genderCombo.setForeground(Color.WHITE);
        genderCombo.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        roundedPanel.add(genderCombo);

        // Gender Icon
        ImageIcon genderIconImage = new ImageIcon("PBO_Project/iconLoginU/gender.png");
        Image scaledGenderIcon = genderIconImage.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel genderIcon = new JLabel(new ImageIcon(scaledGenderIcon));
        genderIcon.setBounds(300, 410, 20, 20); // Ikon sejajar dengan input field
        roundedPanel.add(genderIcon);

        // Phone Label
        JLabel phoneLabel = new JLabel("Nomor Telepon");
        phoneLabel.setBounds(70, 450, 120, 30);
        phoneLabel.setForeground(Color.WHITE);
        phoneLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        roundedPanel.add(phoneLabel);

        // Phone field
        JTextField phoneField = new JTextField();
        phoneField.setBounds(70, 480, 220, 30);
        phoneField.setBackground(new Color(12, 34, 64));
        phoneField.setForeground(Color.WHITE);
        phoneField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        phoneField.setCaretColor(Color.WHITE);
        roundedPanel.add(phoneField);

        // Phone Icon
        ImageIcon phoneIconImage = new ImageIcon("PBO_Project/iconLoginU/phone.png");
        Image scaledPhoneIcon = phoneIconImage.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel phoneIcon = new JLabel(new ImageIcon(scaledPhoneIcon));
        phoneIcon.setBounds(300, 480, 20, 20); // Ikon sejajar dengan input field
        roundedPanel.add(phoneIcon);

        // Alamat Label
        JLabel alamatLabel = new JLabel("Alamat");
        alamatLabel.setBounds(70, 520, 80, 30);
        alamatLabel.setForeground(Color.WHITE);
        alamatLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        roundedPanel.add(alamatLabel);

        // Alamat field
        JTextField alamatField = new JTextField();
        alamatField.setBounds(70, 550, 220, 30);
        alamatField.setBackground(new Color(12, 34, 64));
        alamatField.setForeground(Color.WHITE);
        alamatField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        alamatField.setCaretColor(Color.WHITE);
        roundedPanel.add(alamatField);

        // Alamat Icon
        ImageIcon alamatIconImage = new ImageIcon("PBO_Project/iconLoginU/location.png");
        Image scaledAlamatIcon = alamatIconImage.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel alamatIcon = new JLabel(new ImageIcon(scaledAlamatIcon));
        alamatIcon.setBounds(300, 550, 20, 20); // Ikon sejajar dengan input field
        roundedPanel.add(alamatIcon);

        // Terms and Conditions checkbox
        JCheckBox termsCheckBox = new JCheckBox("I Agree to the Terms & Conditions");
        termsCheckBox.setBounds(70, 600, 250, 30);
        termsCheckBox.setForeground(Color.WHITE);
        termsCheckBox.setBackground(new Color(15, 32, 55));
        roundedPanel.add(termsCheckBox);

        // Sign Up Button
        JButton signUpButton = new JButton("Sign Up");
        // signUpButton.setBounds(110, 620, 200, 40);
        signUpButton.setBounds(110, 650, 200, 40);
        signUpButton.setBackground(Color.WHITE);
        signUpButton.setForeground(new Color(12, 34, 64));
        signUpButton.setFocusPainted(false);
        signUpButton.setFont(new Font("Poppins", Font.BOLD, 14));
        roundedPanel.add(signUpButton);

        // Sign In Link
        JLabel loginLabel = new JLabel("Already have an account? Log In");
        loginLabel.setBounds(110, 700, 200, 20); // Posisikan lebih bawah untuk keseimbangan
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Close current frame and show login form
                frame.dispose(); // Close the current sign-up form
                
                // Create a new instance of the login form
                new ULoginForm(); // Assuming ULoginForm is your login form class
            }
        });
        roundedPanel.add(loginLabel);

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