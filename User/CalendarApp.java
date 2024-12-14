package User;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.List;
import DB.*;

public class CalendarApp extends JPanel {
    private JPanel calendarPanel;
    private JLabel monthYearLabel;
    private int currentMonth;
    private int currentYear;
    private List<Booking> bookings; // Data booking untuk venue
    private int currentUserId; // ID user yang sedang login

    public CalendarApp(List<Booking> bookings, int currentUserId) {
        this.bookings = bookings;
        this.currentUserId = currentUserId;
        setLayout(new BorderLayout());

        // Panel untuk navigasi bulan
        JPanel navigationPanel = new JPanel();
        JButton prevButton = new JButton("Prev");
        JButton nextButton = new JButton("Next");

        // Navigasi bulan: styling tombol
        navigationPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        Color buttonColor = new Color(12, 34, 64);
        prevButton.setBackground(buttonColor);
        nextButton.setBackground(buttonColor);
        prevButton.setForeground(Color.WHITE);
        nextButton.setForeground(Color.WHITE);
        prevButton.setPreferredSize(new Dimension(80, 30));
        nextButton.setPreferredSize(new Dimension(80, 30));

        monthYearLabel = new JLabel("", SwingConstants.CENTER);
        monthYearLabel.setFont(new Font("Arial", Font.BOLD, 16));

        navigationPanel.setLayout(new BorderLayout());
        navigationPanel.add(prevButton, BorderLayout.WEST);
        navigationPanel.add(monthYearLabel, BorderLayout.CENTER);
        navigationPanel.add(nextButton, BorderLayout.EAST);
        add(navigationPanel, BorderLayout.NORTH);

        // Panel untuk kalender
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(0, 7, 5, 5)); // 7 kolom untuk hari dalam seminggu
        calendarPanel.setBackground(Color.LIGHT_GRAY);
        add(calendarPanel, BorderLayout.CENTER);

        // Set bulan dan tahun saat ini
        LocalDate currentDate = LocalDate.now();
        currentMonth = currentDate.getMonthValue();
        currentYear = currentDate.getYear();

        // Update kalender pertama kali
        updateCalendar();

        // Listener tombol navigasi
        prevButton.addActionListener(e -> {
            currentMonth--;
            if (currentMonth < 1) {
                currentMonth = 12;
                currentYear--;
            }
            updateCalendar();
        });

        nextButton.addActionListener(e -> {
            currentMonth++;
            if (currentMonth > 12) {
                currentMonth = 1;
                currentYear++;
            }
            updateCalendar();
        });
    }

    /**
     * Update kalender dengan data booking.
     */
    private void updateCalendar() {
        calendarPanel.removeAll();
        monthYearLabel.setText(String.format("%s %d", Month.of(currentMonth), currentYear));
    
        // Hari-hari dalam seminggu
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : days) {
            JLabel label = new JLabel(day, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            label.setOpaque(true);
            label.setBackground(new Color(12, 34, 64));
            label.setForeground(Color.WHITE);
            calendarPanel.add(label);
        }
    
        // Kalender
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, currentMonth - 1);
        calendar.set(Calendar.YEAR, currentYear);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
    
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    
        // Kosongkan grid sebelum hari pertama bulan
        for (int i = 0; i < firstDayOfWeek; i++) {
            calendarPanel.add(new JLabel());
        }
    
        // Isi grid dengan tanggal
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = LocalDate.of(currentYear, currentMonth, day);
    
            JLabel label = new JLabel(String.valueOf(day), SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, 12));
            label.setOpaque(true);
            label.setBackground(getStatusColor(date));
            calendarPanel.add(label);
        }
    
        calendarPanel.revalidate();
        calendarPanel.repaint();
    }
    
    private Color getStatusColor(LocalDate date) {
        for (Booking booking : bookings) {
            if (!date.isBefore(booking.getStartDate()) && !date.isAfter(booking.getEndDate())) {
                if (booking.getUserId() == currentUserId) {
                    if ("waiting".equalsIgnoreCase(booking.getStatus())) {
                        return Color.YELLOW; // Waiting (User saat ini)
                    } else if ("confirmed".equalsIgnoreCase(booking.getStatus())) {
                        return Color.GREEN; // Confirmed (User saat ini)
                    }
                } else if ("confirmed".equalsIgnoreCase(booking.getStatus())) {
                    return Color.RED; // Confirmed (User lain)
                }
            }
        }
        return Color.WHITE; // Default jika tidak ada booking
    }
}
