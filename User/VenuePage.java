package User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VenuePage {
    
    private static final String[] VENUE_NAMES = {
        "Taman Sangkareang", "Pantai Senggigi", "Grand Imperial", "Hotel Lombok Raya",
        "Islamic Center", "Senggigi Hotel", "Prime Park", "Narmada Convention Hall",
        "Gelanggang Pemuda"
    };
    
    private static final String[] VENUE_LOCATIONS = {
        "Mataram", "Mataram", "Mataram", "Mataram", "Mataram", "Mataram",
        "Mataram", "Mataram", "Mataram"
    };
    
    private static final String[] VENUE_IMAGES = {
        "asset/Sangkareang.jpg", "asset/Pantai_Senggigi.jpg", "asset/Grand_Imperial.jpg", 
        "asset/Hotel_Lombok_Raya.jpg", "asset/Islamic_Center.jpg", "asset/Senggigi_Hotel.jpg",
        "asset/Prime_Park.jpg", "asset/Narmada_Convention_Hall.jpg", "asset/Gelanggang_Pemuda.png"
    };
    
    public static void showVenuePage() {
        SwingUtilities.invokeLater(() -> new VenuePage());
    }

    private JFrame frame;  // Declare the frame as an instance variable

    public VenuePage() {
        frame = createMainFrame();  // Initialize the frame here
        
        JPanel header = createHeader();
        frame.add(header, BorderLayout.NORTH);
        
        JScrollPane scrollPane = createMainPanel();
        frame.add(scrollPane, BorderLayout.CENTER);
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    private JFrame createMainFrame() {
        JFrame frame = new JFrame("Revenue");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());
        return frame;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(12, 34, 64));
        header.setPreferredSize(new Dimension(1200, 100));

        JPanel leftHeader = createLeftHeader();
        header.add(leftHeader, BorderLayout.WEST);

        JLabel profileIcon = createProfileIcon();
        header.add(profileIcon, BorderLayout.EAST);

        return header;
    }

    private JPanel createLeftHeader() {
        JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        leftHeader.setOpaque(false);

        JLabel logo = new JLabel(resizeIcon(new ImageIcon("asset/logo.png"), 50, 50));
        leftHeader.add(logo);

        JLabel revenueLabel = new JLabel("REVENUE", SwingConstants.LEFT);
        revenueLabel.setForeground(Color.WHITE);
        revenueLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        leftHeader.add(revenueLabel);

        return leftHeader;
    }

    private JLabel createProfileIcon() {
        JLabel profile = new JLabel(resizeIcon(new ImageIcon("asset/profil.png"), 50, 50));
        profile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ProfileView.showProfileView();
            }
        });
        return profile;
    }

    private JScrollPane createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel navPanel = createNavigationPanel();
        mainPanel.add(navPanel);

        JPanel venueTitlePanel = createVenueTitlePanel();
        mainPanel.add(venueTitlePanel);

        JPanel venueGridPanel = createVenueGridPanel();
        mainPanel.add(venueGridPanel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return scrollPane;
    }

    private JPanel createNavigationPanel() {
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
        navPanel.setOpaque(false);

        JButton berandaButton = createNavButton("Beranda", new Color(255, 140, 0));
        navPanel.add(berandaButton);
        berandaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Menutup halaman VenuePage
                HomeUser.main(new String[]{}); // Memanggil fungsi main dari HomeUser untuk membuka HomeUser
            }
        });

        JButton venueButton = createNavButton("Venue", new Color(12, 34, 64));
        JPanel venuePanel = new JPanel(new BorderLayout());
        venuePanel.setOpaque(false);
        venuePanel.add(venueButton, BorderLayout.CENTER);
        venuePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 34, 64)));
        navPanel.add(venuePanel);

        JButton profileButton = createNavButton("Profile", new Color(255, 140, 0));
        navPanel.add(profileButton);
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close VenuePage
                UProfilePage.main(new String[]{}); // Open ProfilePage
            }
        });

        return navPanel;
    }

    private JButton createNavButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Poppins", Font.PLAIN, 16));
        button.setForeground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    private JPanel createVenueTitlePanel() {
        JPanel venueTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        venueTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel venueTitle = new JLabel("Temukan venue terbaik untuk event anda");
        venueTitle.setFont(new Font("Poppins", Font.BOLD, 25));
        venueTitle.setHorizontalAlignment(SwingConstants.LEFT);
        venueTitlePanel.add(venueTitle);

        return venueTitlePanel;
    }

    private JPanel createVenueGridPanel() {
        JPanel venueGridPanel = new JPanel(new GridLayout(0, 4, 20, 20));
        venueGridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < VENUE_NAMES.length; i++) {
            venueGridPanel.add(createVenueCard(VENUE_NAMES[i], VENUE_LOCATIONS[i], VENUE_IMAGES[i]));
        }

        return venueGridPanel;
    }

    private JPanel createVenueCard(String name, String location, String imagePath) {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(300, 400));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel image = new JLabel(new ImageIcon(imagePath));
        card.add(image, BorderLayout.CENTER);

        JPanel namePanel = createNamePanel(name);
        card.add(namePanel, BorderLayout.NORTH);

        JPanel locationPanel = createLocationPanel(location);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(locationPanel, BorderLayout.WEST);
        card.add(bottomPanel, BorderLayout.SOUTH);

        return card;
    }

    private JPanel createNamePanel(String name) {
        JPanel namePanel = new JPanel();
        namePanel.setBackground(Color.WHITE);
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel nameLabel = new JLabel("<html><center>" + name + "</center></html>", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setPreferredSize(new Dimension(300, 50));
        namePanel.add(nameLabel);
        return namePanel;
    }

    private JPanel createLocationPanel(String location) {
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel locationIcon = new JLabel(resizeIcon(new ImageIcon("asset/location_vanue.png"), 20, 20));
        locationPanel.add(locationIcon);

        JLabel locationLabel = new JLabel(location, SwingConstants.LEFT);
        locationLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        locationPanel.add(locationLabel);

        return locationPanel;
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}

