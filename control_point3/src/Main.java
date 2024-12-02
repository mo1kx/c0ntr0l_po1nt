import java.util.List;

// Главный класс для тестирования
public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Создание книг
        Book fictionBook = new FictionBook("1984", "Джордж Оруэлл", 1949);
        Book nonFictionBook = new NonFictionBook("Сапиенс: Краткая история человечества", "Юваль Ной Харари", 2011);

        // Добавление книг в библиотеку
        library.addBook(fictionBook);
        library.addBook(nonFictionBook);

        // Чтение книг
        fictionBook.read();
        nonFictionBook.read();

        // Выдача книги
        library.borrowBook("1984");
        library.borrowBook("Сапиенс: Краткая история человечества");

        // Попытка повторной выдачи
        library.borrowBook("1984");

        // Возврат книги
        library.returnBook("1984");
        library.returnBook("Сапиенс: Краткая история человечества");

        // Поиск книги по жанру
        List<Book> fictionBooks = library.findBooksByGenre(Genre.FICTION);
        System.out.println("Найденные художественные книги:");
        for (Book book : fictionBooks) {
            System.out.println("- " + book.title);
        }

        List<Book> nonFictionBooks = library.findBooksByGenre(Genre.NON_FICTION);
        System.out.println("Найденные научно-популярные книги:");
        for (Book book : nonFictionBooks) {
            System.out.println("- " + book.title);
        }

        // Поиск книги по названию с помощью LibraryHelper
        Book foundBook = Library.LibraryHelper.findBookByTitle(library.books, "1984");
        if (foundBook != null) {
            System.out.println("Книга найдена: " + foundBook.title);
        } else {
            System.out.println("Книга не найдена.");
        }
    }
}