package Landing_Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import User.*; 

public class ULoginForm {

    // Verifikasi username dan password
    private boolean verifyLogin(String username, String password) {
        try (Connection conn = DbConnection.getConnection()) { // Menggunakan DbConnection
            if (conn != null) {
                String query = "SELECT * FROM user WHERE gmail = ? AND password = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, username);
                    stmt.setString(2, password);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            return true; // Jika ada data yang cocok, login berhasil
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Jika login gagal
    }

    public static void main(String[] args) {
        // Buat frame
        JFrame frame = new JFrame("Login User");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 650); // Ukuran frame diperbesar
        frame.getContentPane().setBackground(new Color(12, 34, 64)); // Background warna biru gelap
        frame.setLayout(new GridBagLayout());

        // Panel utama untuk isi konten
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
        roundedPanel.setPreferredSize(new Dimension(350, 500));
        roundedPanel.setLayout(null);

        // Icon astronaut
        ImageIcon astronautIconImage = new ImageIcon("../asset/user2.png");
        Image scaledAstronautIcon = astronautIconImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); // Resize astronaut icon
        JLabel astronautIcon = new JLabel(new ImageIcon(scaledAstronautIcon));
        astronautIcon.setBounds(135, 50, 80, 80); // Posisikan lebih tengah
        roundedPanel.add(astronautIcon);

        // Title "Login"
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(105, 140, 140, 30); // Posisikan lebih tinggi
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        roundedPanel.add(loginLabel);

        // Email Label
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(70, 200, 40, 30);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        roundedPanel.add(emailLabel);

        // Email field
        JTextField emailField = new JTextField();
        emailField.setBounds(70, 230, 220, 30);
        emailField.setBackground(new Color(12, 34, 64));
        emailField.setForeground(Color.WHITE);
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        emailField.setCaretColor(Color.WHITE);
        roundedPanel.add(emailField);

        // Password Label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(70, 280, 80, 30);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        roundedPanel.add(passwordLabel);

        // Password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(70, 310, 220, 30);
        passwordField.setBackground(new Color(12, 34, 64));
        passwordField.setForeground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        passwordField.setCaretColor(Color.WHITE);
        roundedPanel.add(passwordField);

        // Login Button
        JButton loginButton = new JButton("Log In");
        loginButton.setBounds(110, 400, 200, 40);
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(new Color(12, 34, 64));
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Poppins", Font.BOLD, 14));

        // Action listener untuk login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Verifikasi login
                ULoginForm loginForm = new ULoginForm();
                if (loginForm.verifyLogin(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                    new home_user(); // Buka halaman home_user jika login berhasil
                    frame.dispose(); // Tutup form login
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        roundedPanel.add(loginButton);

        // Sign Up
        JLabel signUpLabel = new JLabel("Don't have an account? Sign Up");
        signUpLabel.setBounds(110, 460, 200, 20); // Posisikan lebih bawah untuk keseimbangan
        signUpLabel.setForeground(Color.WHITE);
        signUpLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        signUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new USignUpForm();  // Open the sign-up form
                    }
                });
                frame.dispose();  // Close login form
            }
        });
        roundedPanel.add(signUpLabel);

        // Add panel to frame
        frame.add(roundedPanel);

        // Center the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
