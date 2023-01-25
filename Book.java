public class Book {
    Trie.TrieNode root;

    Book(){
        this.root = new Trie.TrieNode();
    }

    public void addNewBook(String name, int count, Book allBookBorrowed) { //O(len(name))
        Trie.TrieNode tmp = Trie.TrieNode.insert(root, name);
        Trie.TrieNode tmp2 = Trie.TrieNode.insert(allBookBorrowed.root, name);
        tmp.numberOfBooks += count;
        tmp2.numberOfBooks += count;
        BookStore.time++;
    }
}
