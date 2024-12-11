package User;

import DB.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VenuePage {

    public static void showVenuePage() {
        SwingUtilities.invokeLater(() -> new VenuePage());
    }

    private JFrame frame;

    public VenuePage() {
        frame = createMainFrame();

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

        JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        leftHeader.setOpaque(false);

        JLabel logo = new JLabel(resizeIcon(new ImageIcon("asset/logo.png"), 50, 50));
        leftHeader.add(logo);

        JLabel revenueLabel = new JLabel("REVENUE", SwingConstants.LEFT);
        revenueLabel.setForeground(Color.WHITE);
        revenueLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        leftHeader.add(revenueLabel);

        header.add(leftHeader, BorderLayout.WEST);

        JLabel profile = new JLabel(resizeIcon(new ImageIcon("asset/profil.png"), 50, 50));
        profile.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Ganti dengan halaman Profile View
                JOptionPane.showMessageDialog(frame, "Profile View");
            }
        });
        header.add(profile, BorderLayout.EAST);

        return header;
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
        berandaButton.addActionListener(e -> {
            frame.dispose();
            HomeUser.main(new String[]{});
        });

        JButton venueButton = createNavButton("Venue", new Color(12, 34, 64));
        JPanel venuePanel = new JPanel(new BorderLayout());
        venuePanel.setOpaque(false);
        venuePanel.add(venueButton, BorderLayout.CENTER);
        venuePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 34, 64)));
        navPanel.add(venuePanel);

        JButton profileButton = createNavButton("Profile", new Color(255, 140, 0));
        navPanel.add(profileButton);
        profileButton.addActionListener(e -> {
            frame.dispose();
            UProfilePage.main(new String[]{}); 
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

        List<Venue> venues = VenueDB.getAllVenues(); // Ambil data venue dari database
        for (Venue venue : venues) {
            venueGridPanel.add(createVenueCard(venue));
        }

        return venueGridPanel;
    }

    private JPanel createVenueCard(Venue venue) {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(300, 400));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Gambar venue
        JLabel image = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(venue.getGambar());
            image.setIcon(resizeIcon(icon, 300, 200));
        } catch (Exception e) {
            image.setText("Gambar Tidak Tersedia");
            image.setHorizontalAlignment(SwingConstants.CENTER);
        }
        card.add(image, BorderLayout.CENTER);

        // Nama venue
        JPanel namePanel = new JPanel();
        namePanel.setBackground(Color.WHITE);
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel nameLabel = new JLabel("<html><center>" + venue.getNamaVenue() + "</center></html>", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        namePanel.add(nameLabel);
        card.add(namePanel, BorderLayout.NORTH);

        // Lokasi venue
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel locationLabel = new JLabel(venue.getKota(), SwingConstants.LEFT);
        locationLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        locationPanel.add(locationLabel);
        card.add(locationPanel, BorderLayout.SOUTH);

        return card;
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}
