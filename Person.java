public class Person {
    Trie.TrieNode root;

    Person(){
        this.root = new Trie.TrieNode();
    }

    public void arrive(String name){
        Trie.TrieNode tmp = Trie.TrieNode.insert(root, name);
        tmp.timeInList.insertToList(BookStore.time);
        BookStore.time++;
    }

    public void exit(String name){
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

    public void totalTimeInLib(String name, int start, int end){
        Trie.TrieNode tmp = Trie.TrieNode.search(root, name);
        if (tmp == null){
            System.out.println("No such person");
        }
        else {
            int indexOfStart = findIndexOfStart(tmp, start);
            int indexOfEnd = findIndexOfEnd(tmp, end);
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
        if (end > timeOutList.array[avg]){
            return avg;
        }
        else{ //end is even smaller that the smallest timeOut
            return avg - 1;
        }
    }
}
