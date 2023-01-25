public class Person {
    Trie.TrieNode root;
    int alphabetSize = 26;
    Person(){
        this.root = new Trie.TrieNode();
    }

    public void arrive(String name){    //O(len(name))
        Trie.TrieNode tmp = Trie.TrieNode.insert(root, name);
        tmp.timeInList.insertToList(BookStore.time);
        BookStore.time++;
    }

    public void exit(String name){  //O(len(name))
        Trie.TrieNode tmp = Trie.TrieNode.insert(root, name);
        tmp.timeOutList.insertToList(BookStore.time);
        if (tmp.sumOfTime.index == 0){
            tmp.sumOfTime.insertToList(tmp.timeOutList.array[0] - tmp.timeInList.array[0]);
        }
        else{
            tmp.sumOfTime.insertToList(tmp.timeOutList.array[tmp.timeOutList.index - 1]
                    - tmp.timeInList.array[tmp.timeInList.index - 1] + tmp.sumOfTime.array[tmp.sumOfTime.index - 1]);
        }
        BookStore.time++;
    }

    public void isInLib(String name){   //O(len(name))
        Trie.TrieNode tmp = Trie.TrieNode.search(root, name);
        if (tmp == null){
            System.out.println("NO");
        }
        else if (tmp.timeInList.index > tmp.timeOutList.index){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
    }


    public void totalTimeInLib(String name, int start, int end){    //O(len(name) + log(number of entries and exits))
        Trie.TrieNode tmp = Trie.TrieNode.search(root, name);
        if (tmp == null){
            System.out.println("No such person");
        }
        else {
            int sum;
            int indexOfStart = findIndexOfStart(tmp, start);
            int startArr = tmp.timeInList.array[indexOfStart];
            int endArr;
            int sumArr;
            //checking if the person is still in the library (has not left)
            if (tmp.timeInList.index > tmp.timeOutList.index){
                endArr = BookStore.time;
                sumArr = tmp.sumOfTime.array[tmp.sumOfTime.index - 1] + endArr - startArr;
            }
            else {
                int indexOfEnd = findIndexOfEnd(tmp, end);
                endArr = tmp.timeOutList.array[indexOfEnd];
                sumArr = tmp.sumOfTime.array[indexOfEnd];
            }
            //calculating the sum
            int endOfPeriod = endArr - end;
            if (endOfPeriod < 0){
                endOfPeriod = 0;
            }
            int startOfPeriod = start - startArr;
            if (startOfPeriod < 0){
                startOfPeriod = 0;
            }
            int beforeSum = 0;
            if (indexOfStart > 0){
                beforeSum = tmp.sumOfTime.array[indexOfStart - 1];
            }
            sum = sumArr - beforeSum - endOfPeriod - startOfPeriod;
            System.out.println(name + " has been in the library for " + sum + " time units");
        }
        BookStore.time++;
    }


    public void shouldBring(String bookName, String personName, Book book, Book allBookBorrowed){    //O(len(bookName) + len(personName))
        // O(len(personName + bookName))
        Trie.TrieNode person = Trie.TrieNode.search(root, personName);
        Trie.TrieNode bookNode = Trie.TrieNode.search(book.root, bookName);
        Trie.TrieNode bookNode2 = Trie.TrieNode.search(allBookBorrowed.root, bookName);

        if (person == null || bookNode == null || bookNode2 == null){
//            System.out.println("No such person");
//            System.out.println("No such book");
            return;
        }
        //if is in lib
        if (person.timeInList.index > person.timeOutList.index){    // O(len(personName + bookName))
            if (bookNode.numberOfBooks - bookNode.numberOfBooksTaken > 0){
                bookNode.numberOfBooksTaken++;
                if (bookNode.personsOfBook == null){
                    bookNode.personsOfBook = new Trie.TrieNode();
                }
                if (bookNode2.personsOfBook == null){
                    bookNode2.personsOfBook = new Trie.TrieNode();
                }
                Trie.TrieNode.insert(bookNode.personsOfBook, personName);
                Trie.TrieNode.insert(bookNode2.personsOfBook, personName);
                if (person.booksOfPerson == null){
                    person.booksOfPerson = new Trie.TrieNode();
                }
                Trie.TrieNode.insert(person.booksOfPerson, bookName);
            }
        }
    }

    public void borrowBook(String bookName, String personName, Book book, Book allBookBorrowed){    //O(len(bookName) + len(personName))
        // O(len(personName + bookName))
        Trie.TrieNode person = Trie.TrieNode.search(root, personName);
        Trie.TrieNode bookNode = Trie.TrieNode.search(book.root, bookName);
        Trie.TrieNode bookNode2 = Trie.TrieNode.search(allBookBorrowed.root, bookName);

        if (person == null || bookNode == null || bookNode2 == null){
//            System.out.println("No such person");
//            System.out.println("No such book");
            return;
        }
        //if is in lib
        if (bookNode.numberOfBooks - bookNode.numberOfBooksTaken > 0){
            bookNode.numberOfBooksTaken++;
            if (bookNode.personsOfBook == null){
                bookNode.personsOfBook = new Trie.TrieNode();
            }
            if (bookNode2.personsOfBook == null){
                bookNode2.personsOfBook = new Trie.TrieNode();
            }
            Trie.TrieNode.insert(bookNode.personsOfBook, personName);
            Trie.TrieNode.insert(bookNode2.personsOfBook, personName);
            if (person.booksOfPerson == null){
                person.booksOfPerson = new Trie.TrieNode();
            }
            Trie.TrieNode.insert(person.booksOfPerson, bookName);
        }

    }

    public void returnBook(String bookName, String personName, Book book) {    //O(len(bookName) + len(personName))
        // O(len(personName + bookName))
        Trie.TrieNode person = Trie.TrieNode.search(root, personName);  // O(len(personName))
        Trie.TrieNode bookNode = Trie.TrieNode.search(book.root, bookName); // O(len(bookName))

        if (person == null || bookNode == null){
            return;
        }
        //if is in lib
        if (person.timeInList.index > person.timeOutList.index){// O(len(personName + bookName))
//            Trie.TrieNode personOfBook = Trie.TrieNode.search(bookNode.personsOfBook, personName);
//            Trie.TrieNode bookOfPerson = Trie.TrieNode.search(person.booksOfPerson, bookName);
//            if (personOfBook != null && bookOfPerson != null){ //todo: check
//                bookNode.numberOfBooksTaken--;
//            }
            //delete personName from bookNode.personsOfBook and bookName from person.booksOfPerson
            if (bookNode.personsOfBook != null && person.booksOfPerson != null){
                Trie.TrieNode.remove(bookNode.personsOfBook, personName, 0);    //O(len(personName))
                Trie.TrieNode.remove(person.booksOfPerson, bookName, 0);    //O(len(bookName))
            }
        }
    }


    public void allPersonCurrentBook(String name){
        Trie.TrieNode person = Trie.TrieNode.search(root, name);
        if (person == null){
//            System.out.println("No such person");
            System.out.println("empty");
        }
        else {

            printPreOrder(person.booksOfPerson);
        }
    }

    public void printPreOrder(Trie.TrieNode root){
        Trie.TrieNode tmp = root;
        if (tmp == null){
            return;
        }

    }



    public int findIndexOfStart(Trie.TrieNode tmp, int start){ // O(log n)
        MyArrayList timeInList = tmp.timeInList;
        int least = 0;
        int most = timeInList.index - 1;
        int avg = (least + most) / 2;
        while (least <= most){
            if (timeInList.array[avg] == start){
                return avg;
            }
            else if (timeInList.array[avg] < start){
                least = avg + 1;
            }
            else{
                most = avg - 1;
            }
            avg = (least + most) / 2;
        }
        if (start > timeInList.array[avg]){
            if (tmp.timeOutList.array[avg] < start){
                return avg + 1;
            }
            else{
                return avg;
            }
        }
        else{ //start is even bigger that the biggest timeIn
            return avg;
        }
    }

    public int findIndexOfEnd(Trie.TrieNode tmp, int end){ // O(log n)
        MyArrayList timeOutList = tmp.timeOutList;
        int least = 0;
        int most = timeOutList.index - 1;
        int avg = (least + most) / 2;
        while (least <= most){
            if (timeOutList.array[avg] == end){
                return avg;
            }
            else if (timeOutList.array[avg] < end){
                least = avg + 1;
            }
            else{
                most = avg - 1;
            }
            avg = (least + most) / 2;
        }
//        return timeOutList.index - 1;
//        if (end < tim)
        return avg;
    }

}
