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

        static TrieNode remove(Trie.TrieNode root, String name, int stage){ //O(len(name))
            if (root == null){
                return null;
            }
            if (stage == name.length()){

                root.isEndOfWord = false;
                if (root.isEmpty(root)){//O(1)
                    root = null;
                }
                return root;
            }
            else {
                int index = name.charAt(stage) - 'a';
                root.children[index] = remove(root.children[index], name, stage + 1);
                if (root.isEmpty(root) && !root.isEndOfWord){
                    root = null;
                }
                return root;
            }
        }

        static boolean isEmpty(TrieNode root){
            for (int i=0; i<alphabetSize; i++){//if all children are null then it is empty
                if (root.children[i] != null){
                    return false;
                }
            }
            return true;
        }
    }

}

