public class Trie {

    static int alphabetSize = 26;

    static class TrieNode {

        TrieNode[] children = new TrieNode[alphabetSize];
        boolean isEndOfWord;
        MyArrayList timeInList = new MyArrayList();
        MyArrayList timeOutList = new MyArrayList();
//        int endTime;
        MyArrayList sumOfTime = new MyArrayList();
        int numberOfBooks = 0;
        int numberOfBooksTaken = 0;
        TrieNode booksOfPerson;
        TrieNode personsOfBook;
        boolean isDeleted = false;

        TrieNode() {
            isEndOfWord = false;
            for (int i = 0; i < alphabetSize; i++) {
                children[i] = null;
            }
        }

        static TrieNode insert(TrieNode root, String value){
            TrieNode tmp = root;
            for (int i = 0; i < value.length(); i++) {
                int index = value.charAt(i) - 'a';
                if (index < 0 || index >= alphabetSize) {
                    throw new IllegalArgumentException("Invalid character: " + value.charAt(i)
                            + "\nplease enter only lowercase letters");
                }
                if (tmp.children[index] == null) {
                    tmp.children[index] = new TrieNode();
                }
                tmp = tmp.children[index];
            }
            tmp.isEndOfWord = true;
            return tmp;
        }

        static TrieNode search(TrieNode root, String value){
            TrieNode tmp = root;
            for (int i = 0; i < value.length(); i++) {
                int index = value.charAt(i) - 'a';
                if (index < 0 || index >= alphabetSize) {
                    throw new IllegalArgumentException("Invalid character: " + value.charAt(i)
                            + "\nplease enter only lowercase letters");
                }
                if (tmp.children[index] == null) {
                    return null;
                }
                tmp = tmp.children[index];
            }
            if (tmp != null && tmp.isEndOfWord) {
                return tmp;
            }
            return null;
        }



    }



}
