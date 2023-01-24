public class BookStore {
    static int time;

    public BookStore(){
        time = 0;
    }

    public static void main(String[] args) {
        Person person = new Person();
        Book book = new Book();
        person.arrive("john"); //0
        person.arrive("jane"); //1
        book.addNewBook("booka", 3);//2
        person.exit("john"); //3
        person.arrive("john"); //4
        person.exit("john"); //5
        person.exit("jane"); //6
        person.totalTimeInLib("john", 2, 8); //7
    }
}
