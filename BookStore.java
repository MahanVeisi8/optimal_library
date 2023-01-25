public class BookStore {
    static int time;

    public BookStore(){
        time = 0;
    }

    public static void main(String[] args) {
        Person person = new Person();
        Book book = new Book();
        Book allBookBorrowed = new Book();
        person.arrive("ab"); //0
        book.addNewBook("zz", 1, allBookBorrowed);// 1
        book.addNewBook("zz", 1, allBookBorrowed);// 2
        person.arrive("aa"); // 2
        person.shouldBring("zz", "ab", book, allBookBorrowed); // 3
        person.shouldBring("zz", "aa", book, allBookBorrowed); // 4
        person.returnBook("zz", "ab", book); // 5
        person.exit("ab"); // 5
        person.exit("aa"); // 6
        person.returnBook("zz", "aa", book); // 7

    }
}
