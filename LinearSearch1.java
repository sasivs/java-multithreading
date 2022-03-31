class NewThread implements Runnable{
    String name;
    int rIndex, lIndex, key;
    int[] arr;
    Thread t;
    CallMe targ;
    NewThread(String n, int r, int l, int k, int[] num, CallMe tar){
        name = n;
        rIndex = r;
        lIndex = l;
        key = k;
        arr = num;
        targ = tar;
        t = new Thread(this, name);
        t.start();
    }
    public void run(){
        targ.search(arr,lIndex,rIndex,key);
    }
}

class CallMe {
    public int count;
    CallMe(){
        count=0;
    }
    public void search(int[] arr, int lIndex, int rIndex, int key){
        for (int i=lIndex; i<=rIndex; i++){
            if (arr[i]==key){
                count++;
            }
        }
    }
}


public class LinearSearch1 {
    public static int linearSearch(int [] arr, int key, int size){
        int n = 1;
        NewThread [] t = new NewThread[n];
        CallMe t1 = new CallMe();
        for (int i=1; i<=n; i++){
            t[i-1] = new NewThread("one", (i*size/n)-1, (i-1)*(size/n), key, arr, t1);
        }
        for (int i=0; i<n; i++){
            try{
                t[i].t.join();
            }
            catch (InterruptedException e){
                System.out.println("Interrupted "+e);
            }
        }
        return t1.count;
    }
    public static void main(String [] args){
        int size = 1000000;
        int[] arr = new int[size];
        System.out.print("{");
        for (int i=0; i<size; i++){
            arr[i] = (int)(Math.random()*99);
        }
        System.out.print("}"+"\n");
        // int key = (int) (Math.random()*99);
        int key = arr[8];
        System.out.println("Key is : "+key);
        int count1 = 0;
        long start1 = System.nanoTime();
        for(int i=0; i<size; i++){
            if(arr[i]==key){
                count1++;
            }
        }
        long end1 = System.nanoTime();
        long exec = end1-start1;
        System.out.println("Count value from without threads: "+count1);
        System.out.println("Execution time without threads is");
        System.out.println(exec + " nanoseconds");
        long start = System.nanoTime();
        int count = linearSearch(arr, key, size);
        long end = System.nanoTime();
        long execution = (end - start);
        System.out.println("The key appeared: "+count+" times");
        System.out.println("Execution time with threads is");
        System.out.println(execution + " nanoseconds");
    }
}
