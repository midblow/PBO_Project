package Provider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import DB.*;
import java.util.List;

public class HomeProvider {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Revenue");
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
                ProfileView.showProfileView(); 
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

        JButton homeButton = new JButton("Home");
        styleNavigationButton(homeButton, new Color(12, 34, 64));

        JButton bookingButton = new JButton("Booking Confirmation");
        styleNavigationButton(bookingButton, new Color(255, 140, 0));

        JButton profileButton = new JButton("Profile");
        styleNavigationButton(profileButton, new Color(255, 140, 0));

        navPanel.add(homeButton);
        navPanel.add(bookingButton);
        navPanel.add(profileButton);
        mainPanel.add(navPanel);

        bookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                BookingConfirm.showBooking();
            }
        });

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                PProfilePage.main(new String[]{});
            }
        });

        int loggedInProviderId = Session.loggedInProviderId;

        List<Venue> venues = VenueDB.getVenuesByProvider(loggedInProviderId);

        JPanel venueTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        venueTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        JLabel venueTitle = new JLabel("My Main Venue");
        venueTitle.setFont(new Font("Poppins", Font.BOLD, 25));
        venueTitle.setHorizontalAlignment(SwingConstants.LEFT); 
        venueTitlePanel.add(venueTitle);
        mainPanel.add(venueTitlePanel);

        JPanel venuePanel = new JPanel(new GridLayout(1, 4, 20, 20));
        venuePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        Venue mainVenue = null;

        for (Venue venue : venues) {
            if (mainVenue == null && venue.isMainVenue()) {
                mainVenue = venue; 
                venuePanel.add(createVenueCard(
                    venue.getNamaVenue(),
                    venue.getKota(),
                    venue.getGambar(),
                    false 
                ));
                break; 
            }
        }
        mainPanel.add(venuePanel);

        JPanel listVenueTitlePanel = new JPanel(new BorderLayout()); 
        listVenueTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        JLabel listVenueTitle = new JLabel("List Venue");
        listVenueTitle.setFont(new Font("Poppins", Font.BOLD, 25));
        listVenueTitle.setHorizontalAlignment(SwingConstants.LEFT); 
        listVenueTitlePanel.add(listVenueTitle, BorderLayout.WEST);

        ImageIcon addVenueIcon = resizeIcon(new ImageIcon("asset/addVenue.png"), 20, 20);
        JButton addVenueButton = new JButton("Add Venue", addVenueIcon);
        addVenueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                AddVenue.main(new String[]{});
            }
        });

        styleNavigationButton(addVenueButton, new Color(255, 140, 0));
        listVenueTitlePanel.add(addVenueButton, BorderLayout.EAST); 
        mainPanel.add(listVenueTitlePanel);

        JPanel venueGridPanel = new JPanel(new GridLayout(0, 4, 20, 20)); 
        venueGridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        for (Venue venue : venues) {
            if (mainVenue == null || venue.getIdVenue() != mainVenue.getIdVenue()) {
                venueGridPanel.add(createVenueCard(
                    venue.getNamaVenue(),
                    venue.getKota(),
                    venue.getGambar(),
                    true 
                ));
            }
        }
        mainPanel.add(venueGridPanel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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

    private static JPanel createVenueCard(String name, String location, String imagePath, boolean showLocation) {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(300, 400)); 
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel image = new JLabel(new ImageIcon(imagePath));
        card.add(image, BorderLayout.CENTER);

        JPanel namePanel = new JPanel();
        namePanel.setBackground(Color.WHITE);
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel nameLabel = new JLabel("<html><center>" + name + "</center></html>", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        namePanel.add(nameLabel);
        card.add(namePanel, BorderLayout.NORTH);

        if (showLocation) {
            JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel locationIcon = new JLabel(resizeIcon(new ImageIcon("asset/location_vanue.png"), 20, 20));
            JLabel locationLabel = new JLabel(location);
            locationLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
            locationPanel.add(locationIcon);
            locationPanel.add(locationLabel);

            card.add(locationPanel, BorderLayout.SOUTH);
        }

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
