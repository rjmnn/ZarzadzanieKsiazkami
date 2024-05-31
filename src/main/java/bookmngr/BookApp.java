package bookmngr;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BookApp extends JFrame {

    // Database connection settings
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USER = "root";
    private static final String PASS = "password";

    // Predefined list of genres
    private static final String[] GENRES = {
            "Science Fiction", "Satire", "Romance", "Realistic Fiction",
            "Psychological Fiction", "Post-apocalyptic", "Mystery", "Magical Realism",
            "Gothic", "Fiction", "Fantasy", "Dystopian", "Coming-of-age",
            "Classic", "Autobiographical", "Allegory", "Adventure"
    };

    // GUI Components for search
    private JComboBox<String> searchGenreComboBox;
    private JList<String> searchResultList;

    // GUI Components for add
    private JTextField addTitleField;
    private JTextField addAuthorField;
    private JComboBox<String> addGenreComboBox;
    private JTextField addYearField;

    public BookApp() {
        // Set up the frame
        setTitle("Book Management App");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up the panels
        JPanel searchPanel = createSearchPanel();
        JPanel addPanel = createAddPanel();

        // Set up the container and layout
        Container container = getContentPane();
        container.setLayout(new GridLayout(2, 1));
        container.setBackground(Color.WHITE);
        container.add(searchPanel);
        container.add(addPanel);
    }

    private JPanel createSearchPanel() {
        // Set up the panel for the search components
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        searchPanel.setBackground(Color.WHITE);

        // Set up the genre combo box for search
        searchGenreComboBox = new JComboBox<>(GENRES);
        searchGenreComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        searchGenreComboBox.setPreferredSize(new Dimension(200, 30));
        searchGenreComboBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // Set up the result list
        searchResultList = new JList<>();
        searchResultList.setFont(new Font("Arial", Font.PLAIN, 14));
        searchResultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        searchResultList.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        JScrollPane scrollPane = new JScrollPane(searchResultList);

        // Set up the search button
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchButtonListener());

        // Set up the delete button
        JButton deleteButton = new JButton("Delete Selected Book");
        deleteButton.addActionListener(new DeleteButtonListener());

        // Add components to the search panel
        JPanel searchInputPanel = new JPanel(new FlowLayout());
        searchInputPanel.add(new JLabel("Select Genre:"));
        searchInputPanel.add(searchGenreComboBox);
        searchInputPanel.add(searchButton);

        searchPanel.add(searchInputPanel, BorderLayout.NORTH);
        searchPanel.add(scrollPane, BorderLayout.CENTER);
        searchPanel.add(deleteButton, BorderLayout.SOUTH);

        return searchPanel;
    }

    private JPanel createAddPanel() {
        // Set up the panel for the add components
        JPanel addPanel = new JPanel(new BorderLayout());
        addPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        addPanel.setBackground(Color.WHITE);

        // Set up input fields for adding a book
        addTitleField = new JTextField(20);
        addAuthorField = new JTextField(20);
        addGenreComboBox = new JComboBox<>(GENRES);
        addYearField = new JTextField(10);

        // Set up the add button
        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(new AddButtonListener());

        // Add components to the add panel
        JPanel addInputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        addInputPanel.add(new JLabel("Title:"));
        addInputPanel.add(addTitleField);
        addInputPanel.add(new JLabel("Author:"));
        addInputPanel.add(addAuthorField);
        addInputPanel.add(new JLabel("Genre:"));
        addInputPanel.add(addGenreComboBox);
        addInputPanel.add(new JLabel("Year Published:"));
        addInputPanel.add(addYearField);

        addPanel.add(addInputPanel, BorderLayout.CENTER);
        addPanel.add(addButton, BorderLayout.SOUTH);

        return addPanel;
    }

    // ActionListener for Search Button
    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedGenre = (String) searchGenreComboBox.getSelectedItem(); // Get the selected genre
            if (!selectedGenre.isEmpty()) { // Check if genre is selected
                searchBooks(selectedGenre); // Pass selected genre to searchBooks method
            } else {
                JOptionPane.showMessageDialog(null, "Please select a genre.");
            }
        }
    }

    // ActionListener for Add Button
    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = addTitleField.getText().trim();
            String author = addAuthorField.getText().trim();
            String genre = (String) addGenreComboBox.getSelectedItem();
            String yearText = addYearField.getText().trim();

            if (title.isEmpty() || author.isEmpty() || yearText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                return;
            }

            try {
                int year = Integer.parseInt(yearText);

                addBook(title, author, genre, year);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid year format.");
            }
        }
    }

    // ActionListener for Delete Button
    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = searchResultList.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedBook = searchResultList.getSelectedValue();
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this book?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    deleteBook(selectedBook);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a book to delete.");
            }
        }
    }

    private void deleteBook(String selectedBook) {
        // Extract title and author from the selected book string
        int indexOfAuthor = selectedBook.lastIndexOf(", Author: ");
        int indexOfYear = selectedBook.lastIndexOf(", Year:");
        String title = selectedBook.substring(7, indexOfAuthor).trim();
        String author = selectedBook.substring(indexOfAuthor + 9, indexOfYear).trim();

        // Print extracted title and author for debugging
        System.out.println("Title: " + title + ", Author: " + author);

        String sql = "DELETE FROM books WHERE title=? AND author=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, author);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Book deleted successfully.");
                // Refresh the search results after deletion
                String selectedGenre = (String) searchGenreComboBox.getSelectedItem();
                if (!selectedGenre.isEmpty()) {
                    searchBooks(selectedGenre);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Book not found.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting book from the database.");
        }
    }


    // Method to search books in the database by genre
    private void searchBooks(String selectedGenre) {
        String sql = "SELECT title, author, year_published FROM books WHERE genre LIKE ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + selectedGenre + "%");

            ResultSet rs = pstmt.executeQuery();

            DefaultListModel<String> model = new DefaultListModel<>();

            boolean found = false; // Flag to indicate if any matching records were found

            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                int yearPublished = rs.getInt("year_published");

                // Display only records that match the selected genre
                model.addElement("Title: " + title + ", Author: " + author + ", Year: " + yearPublished);
                found = true;
            }

            searchResultList.setModel(model);

            // If no matching records were found, display a message
            if (!found) {
                JOptionPane.showMessageDialog(null, "No books found for the selected genre.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the database.");
        }
    }

 // Method to add a new book to the database
    private void addBook(String title, String author, String genre, int year) {
        String sql = "INSERT INTO books (title, author, genre, year_published) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, genre);
            pstmt.setInt(4, year);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Book added successfully.");

                // Clear input fields after adding a book
                addTitleField.setText("");
                addAuthorField.setText("");
                addGenreComboBox.setSelectedIndex(0);
                addYearField.setText("");

                // Refresh the search results after adding a new book
                String selectedGenre = (String) searchGenreComboBox.getSelectedItem();
                if (!selectedGenre.isEmpty()) {
                    searchBooks(selectedGenre);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add book.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding book to the database.");
        }
    }


    public static void main(String[] args) {
        // Ensure the MySQL driver is available
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "MySQL Driver not found.");
            return;
        }

        // Run the GUI application
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BookApp().setVisible(true);
            }
        });
    }
}

