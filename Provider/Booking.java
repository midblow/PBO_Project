package Provider;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Booking {
    public static void showBooking() {
        SwingUtilities.invokeLater(() -> new Booking());
    }
    public Booking(){
        // Frame utama
        JFrame frame = new JFrame("Revenue");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(12, 34, 64)); // #0c2240
        header.setPreferredSize(new Dimension(1200, 100)); // Tinggi header tetap

        // Panel Kiri: Logo dan Judul
        JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        leftHeader.setOpaque(false);

        // Logo
        JLabel logo = new JLabel(resizeIcon(new ImageIcon("asset/logo.png"), 50, 50));
        leftHeader.add(logo);

        // Teks "REVENUE"
        JLabel revenueLabel = new JLabel("REVENUE", SwingConstants.LEFT);
        revenueLabel.setForeground(Color.WHITE);
        revenueLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        leftHeader.add(revenueLabel);

        header.add(leftHeader, BorderLayout.WEST);

        // Ikon Profil di Sebelah Kanan
        JLabel profile = new JLabel(resizeIcon(new ImageIcon("asset/profil.png"), 50, 50));
        profile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open the Profile View when the icon is clicked
                ProfileView.showProfileView();
            }
        });
        header.add(profile, BorderLayout.EAST);

        frame.add(header, BorderLayout.NORTH);

        // Panel utama untuk konten
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Elemen disusun vertikal
        mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Panel Navigasi Menu
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
        navPanel.setOpaque(false); // Transparan
        navPanel.setPreferredSize(new Dimension(1200, 50)); // Ukuran tetap

        //navigasi menu home
        JButton homeButton = new JButton("Home");
        styleNavigationButton(homeButton, new Color(255, 140, 0));  // Updated color
        navPanel.add(homeButton);

        // Tambahkan ActionListener untuk Home
        // homeButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         frame.dispose(); 
        //         HomeProvider.showHomeProvider(); 
        //     }
        // });

        // Tombol navigasi Booking
        JButton bookingButton = new JButton("Booking Confirmation");
        styleNavigationButton(bookingButton, new Color(12, 34, 64));  // Updated color

        // Membuat panel untuk menambahkan garis bawah pada "BOOKING"
        JPanel bookingPanel = new JPanel(new BorderLayout());
        bookingPanel.setOpaque(false); // Panel transparan
        bookingPanel.add(bookingButton, BorderLayout.CENTER);
        bookingPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 34, 64))); // Garis bawah

        navPanel.add(bookingPanel);

        JButton profileButton = new JButton("Profile");
        styleNavigationButton(profileButton, new Color(255, 140, 0));  // Updated color
        navPanel.add(profileButton);

        mainPanel.add(navPanel);

        // Section List Order
        JPanel orderTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Menggunakan FlowLayout LEFT
        orderTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Tambahkan margin

        JLabel orderTitle = new JLabel("Order List");
        orderTitle.setFont(new Font("Poppins", Font.BOLD, 25));
        orderTitle.setHorizontalAlignment(SwingConstants.LEFT); // Ratakan ke kiri
        orderTitlePanel.add(orderTitle);
        mainPanel.add(orderTitlePanel);

        JPanel orderPanel = new JPanel(new GridLayout(1, 4, 20, 20));
        orderPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margin
        
        // Section Order Cards
        JPanel orderCardsPanel = new JPanel(new GridLayout(0, 2, 20, 20)); // Grid untuk 2 baris, 2 kolom
        orderCardsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Margin

        // Contoh data kartu
        String[][] orders = {
            {"Taman Sangkareang", "erwin@gmail.com", "Erwin", "2024-11-29 - 2024-11-30", "SUCCESS"},
            {"Taman Sangkareang", "erwin@gmail.com", "Erwin", "2024-12-02 - 2024-12-04", "PENDING"},
            {"Hotel Lombok Raya", "alda@gmail.com", "Alda", "2024-11-29 - 2024-11-30", "PENDING" },
            {"Pantai Senggigi", "sagi@gmail.com", "Sagi", "2024-12-08 - 2024-12-09", "PENDING"},
            {"Hotel Lombok Raya", "alda@gmail.com", "Alda", "2024-11-29 - 2024-11-30", "PENDING" }
        };        

        for (String[] order : orders) {
            JPanel cardPanel = new JPanel();
            cardPanel.setLayout(new BorderLayout());
            cardPanel.setBackground(Color.WHITE);
            cardPanel.setPreferredSize(new Dimension(250, 150));
            cardPanel.setBorder(createRoundedBorder(3));  // Border membulat pada kartu
            cardPanel.setPreferredSize(new Dimension(250, 200));
            // Konten kartu
            JPanel contentPanel = new JPanel(new GridLayout(4, 1)); // 4 baris untuk konten
            contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

            // Tambahkan nama venue sebagai bagian dari konten
            JLabel venueLabel = new JLabel(order[0]); // Nama venue dari data
            venueLabel.setFont(new Font("Poppins", Font.BOLD, 18)); 
            venueLabel.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(venueLabel);

            // Tambahkan email, nama, dan tanggal
            JLabel emailLabel = new JLabel("Email: " + order[1]);
            emailLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
            contentPanel.add(emailLabel);

            JLabel nameLabel = new JLabel("Nama: " + order[2]);
            nameLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
            contentPanel.add(nameLabel);

            JLabel dateLabel = new JLabel("Date: " + order[3]);
            dateLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
            contentPanel.add(dateLabel);

            // Tambahkan contentPanel ke cardPanel
            cardPanel.add(contentPanel, BorderLayout.CENTER);

            JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Mengatur layout ke rata kanan
            if (order[4].equals("SUCCESS")) {
                JPanel successPanel = new JPanel();
                successPanel.setBackground(new Color(34, 139, 34)); // Warna hijau
                successPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Margin dalam
                successPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                JLabel successLabel = new JLabel("SUCCESS!!!");
                successLabel.setForeground(Color.WHITE); // Teks warna putih
                successPanel.add(successLabel);
                actionPanel.add(successPanel);
            } else {
                JButton approveButton = new JButton("Approve Payment");
                approveButton.setBackground(new Color(12, 34, 64)); // Warna tombol
                approveButton.setForeground(Color.WHITE); // Warna teks
                approveButton.setFocusPainted(false); // Hilangkan fokus
                approveButton.setOpaque(true);
                approveButton.setBorderPainted(false); // Hilangkan border
                approveButton.setBorder(createRoundedBorder(10)); // Tombol dengan sudut membulat

                JButton declineButton = new JButton("Decline");
                declineButton.setBackground(new Color(220, 53, 69)); // Warna tombol
                declineButton.setForeground(Color.WHITE); // Warna teks
                declineButton.setFocusPainted(false); // Hilangkan fokus
                declineButton.setOpaque(true);
                declineButton.setBorderPainted(false); // Hilangkan border
                declineButton.setBorder(createRoundedBorder(10)); // Tombol dengan sudut membulat

                actionPanel.add(approveButton);
                actionPanel.add(declineButton);
            }

            // Tambahkan actionPanel ke cardPanel
            cardPanel.add(actionPanel, BorderLayout.SOUTH);

            // Tambahkan cardPanel ke orderCardsPanel
            orderCardsPanel.add(cardPanel);
        }       

        mainPanel.add(orderCardsPanel);

        // JScrollPane untuk scrolling
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Menyembunyikan scroll bar vertikal
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Menyembunyikan scroll bar horizontal
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Frame otomatis diperbesar
        frame.setVisible(true);        
    }

    private static Border createRoundedBorder(int radius) {
        return new Border() {
            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(radius, radius, radius, radius);
            }

            @Override
            public boolean isBorderOpaque() {
                return true;
            }

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.setColor(new Color(12, 34, 64));
                g.fillRoundRect(x, y, width - 1, height - 1, radius, radius); // Membuat border dengan sudut membulat
            }
        };
    }

    private static void styleNavigationButton(JButton button, Color color) {
        button.setFont(new Font("Poppins", Font.PLAIN, 16));
        button.setForeground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
    }

    private static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
    
}

class ProfileView extends JFrame {

    public ProfileView() {
        // Set up the Profile View Frame
        setTitle("Profile");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(new Color(12, 34, 64));
        profilePanel.setLayout(new BorderLayout());

        // Create a top panel to hold the back button aligned to the left
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(12, 34, 64));
        JLabel backButton = new JLabel(new ImageIcon(new ImageIcon("asset/backProfile.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Close the profile view
            }
        });
        topPanel.add(backButton);

        // Add the top panel to the profile panel
        profilePanel.add(topPanel, BorderLayout.NORTH);

        // Center Panel for Profile Image and Info
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(12, 34, 64));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        // centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Profile picture
        JLabel profilePic = new JLabel(new ImageIcon(new ImageIcon("asset/Zoro.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        profilePic.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(profilePic);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Name label
        JLabel nameLabel = new JLabel("Zoro");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(nameLabel);

        // Add the center panel to the profile panel
        profilePanel.add(centerPanel, BorderLayout.CENTER);

        // Add the profile panel to the frame
        add(profilePanel);
        setVisible(true);
    }

    // Method to show the Profile view when the profile icon is clicked
    public static void showProfileView() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProfileView();
            }
        });
    }
}
