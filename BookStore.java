public class BookStore {
    static int time;

    public BookStore(){
        time = 0;
    }

    public static void main(String[] args) {
        Person person = new Person();
        Book book = new Book();
        person.arrive("john"); //0
        book.addNewBook("booka", 1);// 1
        person.arrive("jane"); // 2
        person.shouldBring("booka", "john", book); // 3
        person.shouldBring("booka", "jane", book); // 4
        person.exit("john"); // 5
        person.exit("jane"); // 6


    }
}
