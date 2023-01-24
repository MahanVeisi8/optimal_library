public class Book {
    Trie.TrieNode root;

    Book(){
        this.root = new Trie.TrieNode();
    }

    public void addNewBook(String name, int count){
        Trie.TrieNode tmp = Trie.TrieNode.insert(root, name);
        tmp.numberOfBooks = count;
    }
}
