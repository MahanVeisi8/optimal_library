public class MyArrayList {
    int size;
    int[] array;
    int index = 0;
    MyArrayList(){
        this.size = 1;
        this.array = new int[size];
    }

    public void insertToList(int value){
        if(index == size){
            int[] tmp = new int[size*2];
            for(int i = 0; i < size; i++){
                tmp[i] = array[i];
            }
            array = tmp;
            size = size*2;
        }
        array[index] = value;
        index++;
    }
}
