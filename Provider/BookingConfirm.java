package Provider;

import DB.BookingDB;
import DB.InvoiceDB;
import DB.Session;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class BookingConfirm {

    // Warna konstan
    private static final Color DEEP_NAVY = new Color(17, 29, 48);
    private static final Color ACCENT_ORANGE = new Color(255, 127, 39);
    private static final Color SUCCESS_GREEN = new Color(34, 177, 76);
    private static final Color REMOVE_RED = new Color(220, 53, 69);
    private static final Color BUTTON_BLUE = new Color(12, 34, 64);
    private static JButton activeNavbarButton = null;

    public static void showBooking() {
        SwingUtilities.invokeLater(() -> new BookingConfirm(Session.loggedInProviderId));
    }

    public BookingConfirm(int providerId) {
        JFrame frame = new JFrame("Booking Confirmation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Header
        JPanel header = createHeaderPanel();
        frame.add(header, BorderLayout.NORTH);

        // Navbar
        JPanel navbar = createNavbarPanel(frame);
        frame.add(navbar, BorderLayout.CENTER);

        // Main Content
        JPanel mainContent = createMainContentPanel();
        frame.add(mainContent, BorderLayout.CENTER);

        JPanel footer = createFooterPanel();
        frame.add(footer, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DEEP_NAVY);
        header.setPreferredSize(new Dimension(1200, 70));

        // Logo dan Title
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        leftPanel.setOpaque(false);
        JLabel logo = new JLabel(resizeIcon(new ImageIcon("asset/logo.png"), 40, 40));
        JLabel title = new JLabel("REVENUE");
        title.setFont(new Font("Poppins", Font.BOLD, 20));
        title.setForeground(Color.WHITE);

        leftPanel.add(logo);
        leftPanel.add(title);

        // Profile Icon
        JLabel profileIcon = new JLabel(resizeIcon(new ImageIcon("asset/profil.png"), 40, 40));
        profileIcon.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        header.add(leftPanel, BorderLayout.WEST);
        header.add(profileIcon, BorderLayout.EAST);

        return header;
    }

    private JPanel createNavbarPanel(JFrame frame) {
        JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 10));
        navbar.setBackground(Color.WHITE);
    
        JButton homeButton = createNavbarButton("Home");
        JButton bookingButton = createNavbarButton("Booking Confirmation");
        JButton profileButton = createNavbarButton("Profile");
    
        // Default Active Button
        updateActiveButtonNavbar(bookingButton);
    
        homeButton.addActionListener(e -> {
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(homeButton);
            if (currentFrame != null) {
                currentFrame.dispose(); // Tutup frame saat ini
            }
            HomeProvider.main(new String[]{}); // Buka laman "Home"
        });
        
        bookingButton.addActionListener(e -> {
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(bookingButton);
            if (currentFrame != null) {
                currentFrame.dispose(); // Tutup frame saat ini
            }
            BookingConfirm.showBooking(); // Buka laman "Booking Confirmation"
        });
        
        profileButton.addActionListener(e -> {
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(profileButton);
            if (currentFrame != null) {
                currentFrame.dispose(); // Tutup frame saat ini
            }
            PProfilePage.main(new String[]{}); // Buka laman "Profile"
        });
    
        navbar.add(homeButton);
        navbar.add(bookingButton);
        navbar.add(profileButton);
    
        return navbar;
    }

    private JPanel createMainContentPanel() {
        // Panel utama dengan BoxLayout untuk menyusun elemen secara vertikal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(255, 255, 255));
    
        // Tambahkan Navbar di bagian atas
        JPanel navbar = createNavbarPanel(new JFrame());
        navbar.setAlignmentX(Component.LEFT_ALIGNMENT); // Align ke kiri
        mainPanel.add(navbar);
    
        // Title "Booking Confirmation"
        JLabel titleLabel = new JLabel("Booking Confirmation", SwingConstants.LEFT); // SwingConstants.LEFT
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 0)); // Tambahkan padding kiri
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align ke kiri
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setOpaque(true);
        mainPanel.add(titleLabel);
    
        JPanel cardPanel = new JPanel(new GridLayout(0, 2, 10, 20));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        cardPanel.setBackground(Color.WHITE);
        
        // Scroll Pane untuk booking cards
        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(0, 800)); 
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
    
        // Ambil data booking
        List<Object[]> bookings = BookingDB.getBookingsForProvider(Session.loggedInProviderId);
        if (bookings.isEmpty()) {
            JLabel noDataLabel = new JLabel("No Bookings Available", SwingConstants.CENTER);
            noDataLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
            noDataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(noDataLabel);
        } else {
            for (Object[] booking : bookings) {
                int bookingId = (int) booking[0];
                String venue = (String) booking[1];
                LocalDate start = (LocalDate) booking[2];
                LocalDate end = (LocalDate) booking[3];
                String name = (String) booking[4];
                String email = (String) booking[5];
                String status = BookingDB.getBookingStatusById(bookingId);
    
                JPanel card = createBookingCard(bookingId, venue, start, end, name, email, status);
                cardPanel.add(card);
            }
        }
    
        mainPanel.add(scrollPane);
    
        return mainPanel;
    }

    private JPanel createBookingCard(int bookingId, String venue, LocalDate start, LocalDate end, String name, String email, String status) {
        JPanel card = new JPanel(new BorderLayout());
        // card.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
        card.setBackground(Color.WHITE);

        // Content
        JPanel content = new JPanel(new GridLayout(4, 1));
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        content.add(new JLabel("<html><b>" + venue + "</b></html>"));
        content.add(new JLabel("Email: " + email));
        content.add(new JLabel("Name: " + name));
        content.add(new JLabel("Date: " + start + " - " + end));

        card.add(content, BorderLayout.CENTER);

        // Action Buttons
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        if ("waiting".equals(status)) {
            JButton approve = createActionButton("Approve Payment", BUTTON_BLUE);
            JButton decline = createActionButton("Decline", REMOVE_RED);

            approve.addActionListener(e -> {
                int venueId = BookingDB.getVenueIdByBookingId(bookingId);
                if (venueId == -1) {
                    JOptionPane.showMessageDialog(null, "Venue tidak ditemukan untuk booking ini.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            
                // Ambil tanggal booking untuk pengecekan
                LocalDate startDate = start; // Tanggal mulai booking
                LocalDate endDate = end;     // Tanggal selesai booking
            
                // Pengecekan overlap sebelum mengubah status
                if (BookingDB.isDateOverlap(venueId, startDate, endDate)) {
                    JOptionPane.showMessageDialog(null, 
                        "Tanggal booking bertabrakan dengan booking yang sudah di-approve.", 
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            
                // Jika tidak ada overlap, update status ke 'confirmed'
                boolean updated = BookingDB.updateBookingStatus(bookingId, "confirmed");
                if (updated) {
                    boolean invoiceCreated = InvoiceDB.createInvoice(bookingId, venueId);
                    if (invoiceCreated) {
                        JOptionPane.showMessageDialog(null,
                            "Booking dikonfirmasi dan invoice berhasil dibuat.",
                            "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                            "Booking dikonfirmasi, tetapi gagal membuat invoice.",
                            "Peringatan", JOptionPane.WARNING_MESSAGE);
                    }
                    refresh(); // Perbarui UI
                } else {
                    JOptionPane.showMessageDialog(null,
                        "Gagal mengonfirmasi booking.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            
            decline.addActionListener(e -> {
                BookingDB.deleteBookingById(bookingId);
                refresh();
            });

            actions.add(approve);
            actions.add(decline);
        } else if ("confirmed".equals(status)) {
            // SUCCESS label
            JLabel successLabel = new JLabel("SUCCESS!!!", SwingConstants.CENTER);
            successLabel.setOpaque(true); // Membuat background terlihat
            successLabel.setBackground(SUCCESS_GREEN); // Warna hijau
            successLabel.setForeground(Color.WHITE); // Teks putih
            successLabel.setFont(new Font("Poppins", Font.BOLD, 12));
            successLabel.setPreferredSize(new Dimension(100, 30));
            successLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Padding
        
            // Remove button
            JButton removeButton = createActionButton("Remove", REMOVE_RED);
            removeButton.addActionListener(e -> {
                BookingDB.deleteBookingById(bookingId);
                refresh();
            });
        
            // Tambahkan SUCCESS label dan Remove button ke panel actions
            actions.add(successLabel);
            actions.add(removeButton);
        }
        card.add(actions, BorderLayout.SOUTH);
        return card;
    }

    private JButton createNavbarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Poppins", Font.PLAIN, 16));
        button.setForeground(ACCENT_ORANGE);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        return button;
    }

    private void updateActiveButtonNavbar(JButton button) {
        if (activeNavbarButton != null) {
            activeNavbarButton.setForeground(ACCENT_ORANGE);
            activeNavbarButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        }
        button.setForeground(DEEP_NAVY);
        button.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, DEEP_NAVY));
        activeNavbarButton = button;
    }

    private JButton createActionButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Poppins", Font.BOLD, 12));
        button.setFocusPainted(false);
        return button;
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width) {
        Image img = icon.getImage();
        int height = (int) ((double) img.getHeight(null) / img.getWidth(null) * width); // Menyesuaikan tinggi agar proporsional
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
    
    private class LoadImageWorker extends SwingWorker<ImageIcon, Void> {
        private final String imagePath;
        private final JLabel label;

        public LoadImageWorker(String imagePath, JLabel label) {
            this.imagePath = imagePath;
            this.label = label;
        }

        @Override
        protected ImageIcon doInBackground() throws Exception {
            // Pemuatan gambar di latar belakang
            ImageIcon icon = new ImageIcon(imagePath);
            int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;  // Ambil lebar layar
            return resizeIcon(icon, screenWidth); // Resize gambar sesuai lebar layar
        }

        @Override
        protected void done() {
            try {
                // Setelah gambar selesai dimuat, perbarui label dengan gambar baru
                ImageIcon icon = get();
                label.setIcon(icon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private JPanel createFooterPanel() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(Color.WHITE);
    
        // Label untuk footer yang akan diubah setelah gambar dimuat
        JLabel footerImageLabel = new JLabel();
        footer.add(footerImageLabel, BorderLayout.CENTER);
    
        // Menjalankan SwingWorker untuk memuat gambar footer di latar belakang
        new LoadImageWorker("asset/Footer.png", footerImageLabel).execute();
    
        return footer;
    }
    
    
    private void refresh() {
        SwingUtilities.invokeLater(BookingConfirm::showBooking);
    }
}
