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
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        JPanel footerPanel = createFooterPanel();
        mainPanel.add(footerPanel);

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

        JButton profileButton = createNavButton("Profil", new Color(255, 140, 0));
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

        JLabel venueTitle = new JLabel("Temukan venue terbaik untuk acara anda");
        venueTitle.setFont(new Font("Poppins", Font.BOLD, 25));
        venueTitle.setHorizontalAlignment(SwingConstants.LEFT);
        venueTitlePanel.add(venueTitle);

        return venueTitlePanel;
    }

    private JPanel createVenueGridPanel() {
        JPanel venueGridPanel = new JPanel(new GridLayout(0, 4, 20, 20));
        venueGridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        List<Venue> venues = VenueDB.getAllVenues(); 
        for (Venue venue : venues) {
            venueGridPanel.add(createVenueCard(venue));
        }

        return venueGridPanel;
    }

    private JPanel createVenueCard(Venue venue) {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(300, 300));
        card.setBackground(Color.WHITE); 
        
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        imagePanel.setBackground(Color.WHITE); 
        
        JLabel image = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(venue.getGambar());
            image.setIcon(resizeIcon(icon, 280, 180)); 
        } catch (Exception e) {
            image.setText("Gambar Tidak Tersedia");
            image.setHorizontalAlignment(SwingConstants.CENTER);
            image.setOpaque(true);
            image.setBackground(Color.WHITE); 
        }
        imagePanel.add(image);
        card.add(imagePanel, BorderLayout.CENTER);
        
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        namePanel.setBackground(Color.WHITE); 
        namePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        
        JLabel nameLabel = new JLabel("<html><center>" + venue.getNamaVenue() + "</center></html>");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setForeground(new Color(34, 34, 34)); 
        nameLabel.setBackground(Color.WHITE);
        nameLabel.setOpaque(true); 
        namePanel.add(nameLabel);
        card.add(namePanel, BorderLayout.NORTH);
        
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        locationPanel.setBackground(Color.WHITE);
        locationPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        
        JLabel locationIcon = new JLabel(resizeIcon(new ImageIcon("asset/location_vanue.png"), 20, 20));
        locationIcon.setBackground(Color.WHITE);
        locationIcon.setOpaque(true); 
        locationPanel.add(locationIcon);
        
        JLabel locationLabel = new JLabel(venue.getKota());
        locationLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        locationLabel.setForeground(new Color(100, 100, 100));
        locationLabel.setBackground(Color.WHITE);
        locationLabel.setOpaque(true);
        locationPanel.add(locationLabel);
        card.add(locationPanel, BorderLayout.SOUTH);

        card.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frame.dispose(); 
                new DetailVenue(venue.getIdVenue()); 
            }
        });
            
        return card;
    }
 
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new BorderLayout()); 
        footerPanel.setBackground(Color.WHITE);
        ImageIcon footerImage = new ImageIcon("asset/Footer.png");
        int width = frame.getWidth(); 
        int height = (int) ((double) footerImage.getIconHeight() / footerImage.getIconWidth() * width); 
        Image resizedImage = footerImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel footerLabel = new JLabel(resizedIcon);
        footerPanel.add(footerLabel, BorderLayout.CENTER); 
        footerPanel.setPreferredSize(new Dimension(width, height));

        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                int newWidth = frame.getWidth();
    
                int newHeight = (int) ((double) footerImage.getIconHeight() / footerImage.getIconWidth() * newWidth);
    
                Image newResizedImage = footerImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                ImageIcon newResizedIcon = new ImageIcon(newResizedImage);
    
                footerLabel.setIcon(newResizedIcon);
                footerPanel.setPreferredSize(new Dimension(newWidth, newHeight));
                footerPanel.revalidate();
                footerPanel.repaint();
            }
        });
    
        return footerPanel;
    }    
        
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}
