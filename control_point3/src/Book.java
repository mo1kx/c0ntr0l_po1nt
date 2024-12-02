import java.util.ArrayList;
import java.util.List;

// Перечисление Genre
enum Genre {
    FICTION, NON_FICTION
}

// Исключение для недоступной книги
class BookUnavailableException extends Exception {
    public BookUnavailableException(String message) {
        super(message);
    }
}

// Интерфейс Readable
interface Readable {
    void read();
}

// Абстрактный класс Book
abstract class Book implements Readable {
    protected String title;
    protected String author;
    protected int year;
    protected boolean isAvailable;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isAvailable = true; // Книга доступна по умолчанию
    }

    public abstract Genre getGenre();

    @Override
    public void read() {
        System.out.println("Чтение книги: '" + title + "' автор: " + author + ", год издания: " + year);
    }

    public void borrow() throws BookUnavailableException {
        if (!isAvailable) {
            throw new BookUnavailableException("Книга '" + title + "' недоступна для выдачи.");
        }
        isAvailable = false; // Книга выдана
    }

    public void returnBook() {
        isAvailable = true; // Книга возвращена
    }
}

// Класс FictionBook
class FictionBook extends Book {
    public FictionBook(String title, String author, int year) {
        super(title, author, year);
    }

    @Override
    public Genre getGenre() {
        return Genre.FICTION;
    }

    @Override
    public void read() {
        super.read();
        System.out.println("Это художественная книга.");
    }
}

// Класс NonFictionBook
class NonFictionBook extends Book {
    public NonFictionBook(String title, String author, int year) {
        super(title, author, year);
    }

    @Override
    public Genre getGenre() {
        return Genre.NON_FICTION;
    }

    @Override
    public void read() {
        super.read();
        System.out.println("Это научно-популярная книга.");
    }
}

// Класс Library
class Library {
    List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void borrowBook(String title) {
        try {
            for (Book book : books) {
                if (book.title.equals(title)) {
                    book.borrow();
                    System.out.println("Выдана книга: " + title);
                    return;
                }
            }
            throw new BookUnavailableException("Книга '" + title + "' не найдена в библиотеке.");
        } catch (BookUnavailableException e) {
            System.out.println(e.getMessage());
        }
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.title.equals(title)) {
                book.returnBook();
                System.out.println("Книга '" + title + "' возвращена.");
                return;
            }
        }
        System.out.println("Книга '" + title + "' не найдена в библиотеке.");
    }

    // Статический вложенный класс LibraryHelper
    static class LibraryHelper {
        public static Book findBookByTitle(List<Book> books, String title) {
            for (Book book : books) {
                if (book.title.equals(title)) {
                    return book;
                }
            }
            return null;
        }
    }

    public List<Book> findBooksByGenre(Genre genre) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre() == genre) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }
}
