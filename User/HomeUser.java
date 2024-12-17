package User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import DB.*;

public class HomeUser {
    private static JFrame frame;
    public static void main(String[] args) {
        frame = new JFrame("Revenue");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());

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
        profile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ProfileViewUser.showProfileView();
            }
        });
        header.add(profile, BorderLayout.EAST);

        frame.add(header, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); 
        mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
        navPanel.setOpaque(false); 
        navPanel.setPreferredSize(new Dimension(1200, 50)); 

        JButton berandaButton = new JButton("Beranda");
        styleNavigationButton(berandaButton, new Color(12, 34, 64));  

        JPanel berandaPanel = new JPanel(new BorderLayout());
        berandaPanel.setOpaque(false); 
        berandaPanel.add(berandaButton, BorderLayout.CENTER);
        berandaPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 34, 64))); 
        navPanel.add(berandaPanel);

        JButton venueButton = new JButton("Venue");
        styleNavigationButton(venueButton, new Color(255, 140, 0)); 
        navPanel.add(venueButton);

        venueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); 
                VenuePage.showVenuePage(); 
            }
        });

        JButton profileButton = new JButton("Profil");
        styleNavigationButton(profileButton, new Color(255, 140, 0));  
        navPanel.add(profileButton);
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); 
                UProfilePage.main(new String[]{});
            }
        });

        mainPanel.add(navPanel);

        JPanel venueTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        venueTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        JLabel venueTitle = new JLabel("Rekomendasi Venue");
        venueTitle.setFont(new Font("Poppins", Font.BOLD, 23));
        venueTitle.setHorizontalAlignment(SwingConstants.LEFT); 
        venueTitlePanel.add(venueTitle);
        mainPanel.add(venueTitlePanel);

        List<Venue> venues = VenueDB.getAllVenues();

        int maxVenues = Math.min(7, venues.size()); 

        JPanel venuePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20)); 
        venuePanel.setPreferredSize(new Dimension(250 * maxVenues, 400)); 

        for (int i = 0; i < maxVenues; i++) {
            Venue venue = venues.get(i);
            venuePanel.add(createVenueCard(venue)); 
        }

        JScrollPane venueScrollPane = new JScrollPane(venuePanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        venueScrollPane.setPreferredSize(new Dimension(1200, 400)); 
        mainPanel.add(venueScrollPane);

        JPanel tataCaraTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        tataCaraTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        JLabel tataCaraTitle = new JLabel("Tata Cara Reservasi Venue");
        tataCaraTitle.setFont(new Font("Poppins", Font.BOLD, 23));
        tataCaraTitle.setHorizontalAlignment(SwingConstants.LEFT); 
        tataCaraTitlePanel.add(tataCaraTitle);
        mainPanel.add(tataCaraTitlePanel);

        JPanel tataCaraPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        tataCaraPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        tataCaraPanel.add(createDetailedStepCard(
            "1. Pilih Venue",
            "Mulailah perayaan yang tak terlupakan Anda dengan memilih venue sempurna untuk merayakan momen spesial Anda.",
            "asset/1cara.jpg"
        ));
        tataCaraPanel.add(createDetailedStepCard(
            "2. Mengisi Identitas",
            "Kami ingin tahu lebih banyak tentang acara Anda. Mohon berikan kami informasi yang diperlukan untuk memastikan semuanya berjalan dengan lancar.",
            "asset/2cara.jpg"
        ));
        tataCaraPanel.add(createDetailedStepCard(
            "3. Melengkapi form SOP",
            "Kenyamanan Anda adalah prioritas kami. Mohon pastikan semuanya tersusun rapi sesuai kebutuhan Anda.",
            "asset/3cara.jpg"
        ));
        tataCaraPanel.add(createDetailedStepCard(
            "4. Melakukan Pembayaran",
            "Dengan persiapan yang teliti, saatnya membayar dan memastikan semuanya siap untuk perayaan yang tak terlupakan.",
            "asset/4cara.jpg"
        ));
        mainPanel.add(tataCaraPanel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        JLabel footerImage = new JLabel(new ImageIcon("asset/Footer.png"));
        footerImage.setHorizontalAlignment(SwingConstants.CENTER);
        footerPanel.add(footerImage, BorderLayout.CENTER);
        footerPanel.setPreferredSize(new Dimension(frame.getWidth(), 200)); 
        mainPanel.add(footerPanel, BorderLayout.SOUTH); 

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setVisible(true);
    }

    private static JPanel createVenueCard(Venue venue) {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(250, 350));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        JLabel image = new JLabel(new ImageIcon(venue.getGambar()));
        image.setPreferredSize(new Dimension(250, 200));
        card.add(image, BorderLayout.CENTER);
        
        JPanel namePanel = new JPanel();
        namePanel.setBackground(Color.WHITE);
        JLabel nameLabel = new JLabel("<html><center>" + venue.getNamaVenue() + "</center></html>", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        namePanel.add(nameLabel);
        card.add(namePanel, BorderLayout.NORTH);

        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel locationIcon = new JLabel(resizeIcon(new ImageIcon("asset/location_vanue.png"), 15, 15));
        locationPanel.add(locationIcon);

        String locationText = venue.getAlamat();
        String[] words = locationText.split(" "); 
        if (words.length > 3) {
            locationText = String.join(" ", java.util.Arrays.copyOfRange(words, 0, 3)) + "..."; 
        }

        JLabel locationLabel = new JLabel(locationText, SwingConstants.LEFT);
        locationLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        locationPanel.add(locationLabel);
        card.add(locationPanel, BorderLayout.SOUTH);

    
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new DetailVenue(venue.getIdVenue()); 
                    frame.dispose(); 
                });
            }
        });
    
        return card;
    }
    
    private static JPanel createDetailedStepCard(String title, String description, String imagePath) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(255, 231, 207)); 
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel image = new JLabel(new ImageIcon(imagePath));
        card.add(image, BorderLayout.WEST);

        JPanel detailPanel = new JPanel(new BorderLayout());
        detailPanel.setBackground(new Color(255, 231, 207));

        JLabel titleLabel = new JLabel(title, SwingConstants.LEFT);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        detailPanel.add(titleLabel, BorderLayout.NORTH);

        JLabel descriptionLabel = new JLabel("<html><p style='width:250px;'>" + description + "</p></html>");
        descriptionLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        detailPanel.add(descriptionLabel, BorderLayout.CENTER);

        card.add(detailPanel, BorderLayout.CENTER);

        return card;
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
