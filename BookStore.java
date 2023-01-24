public class BookStore {
    static int time;

    public BookStore(){
        time = 0;
    }

    public static void main(String[] args) {
        Person person = new Person();
        Book book = new Book();
        person.arrive("john");
        person.arrive("jane");
        book.addNewBook("booka", 3);
        person.exit("john");
        person.arrive("john");
        person.exit("john");
        person.exit("jane");
    }
}
