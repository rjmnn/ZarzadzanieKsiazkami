
CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    year_published INT,
    genre VARCHAR(255)
);


INSERT INTO books (title, author, year_published, genre) VALUES
('To Kill a Mockingbird', 'Harper Lee', 1960, 'Fiction'),
('1984', 'George Orwell', 1949, 'Dystopian'),
('Pride and Prejudice', 'Jane Austen', 1813, 'Romance'),
('The Great Gatsby', 'F. Scott Fitzgerald', 1925, 'Classic'),
('The Catcher in the Rye', 'J.D. Salinger', 1951, 'Coming-of-age'),
('Animal Farm', 'George Orwell', 1945, 'Satire'),
('Brave New World', 'Aldous Huxley', 1932, 'Dystopian'),
('The Lord of the Rings', 'J.R.R. Tolkien', 1954, 'Fantasy'),
('Harry Potter and the Philosopher\'s Stone', 'J.K. Rowling', 1997, 'Fantasy'),
('The Hobbit', 'J.R.R. Tolkien', 1937, 'Fantasy'),
('Fahrenheit 451', 'Ray Bradbury', 1953, 'Dystopian'),
('The Hitchhiker\'s Guide to the Galaxy', 'Douglas Adams', 1979, 'Science Fiction'),
('Frankenstein', 'Mary Shelley', 1818, 'Gothic'),
('Moby-Dick', 'Herman Melville', 1851, 'Adventure'),
('The Picture of Dorian Gray', 'Oscar Wilde', 1890, 'Gothic'),
('Jane Eyre', 'Charlotte Brontë', 1847, 'Gothic'),
('Wuthering Heights', 'Emily Brontë', 1847, 'Gothic'),
('The Adventures of Sherlock Holmes', 'Arthur Conan Doyle', 1892, 'Mystery'),
('One Hundred Years of Solitude', 'Gabriel García Márquez', 1967, 'Magical Realism'),
('Crime and Punishment', 'Fyodor Dostoevsky', 1866, 'Psychological Fiction'),
('The Road', 'Cormac McCarthy', 2006, 'Post-apocalyptic'),
('Lord of the Flies', 'William Golding', 1954, 'Allegory'),
('The Grapes of Wrath', 'John Steinbeck', 1939, 'Realistic Fiction'),
('Catch-22', 'Joseph Heller', 1961, 'Satire'),
('The Bell Jar', 'Sylvia Plath', 1963, 'Autobiographical');
