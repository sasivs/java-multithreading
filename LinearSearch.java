class NewThread implements Runnable{
    String name;
    int rIndex, lIndex, key;
    int fIndex = -1;
    int[] arr;
    Thread t;
    Callme targ;
    NewThread(String n, int r, int l, int k, int[] num, Callme target){
        name = n;
        rIndex = r;
        lIndex = l;
        key = k;
        arr = num;
        targ = target;
        t = new Thread(this, name);
        t.start();
    }

    public void run(){
        fIndex=targ.search(arr, lIndex, rIndex, key);
    }
}

class Callme {
    private volatile boolean found = false;
    public int search(int[] arr, int lIndex, int rIndex, int key){
        for (int i=lIndex; i<=rIndex; i++){
            if(found==true){
                return -1;
            }
            if (arr[i]==key){
                found=true;
                return i;
            }
        }
        return -1;
    }
}


public class LinearSearch {
    public static void linearSearch(int [] arr, int key, int size){
        int n = 1;
        NewThread [] t = new NewThread[n];
        int found=0;
        Callme t1 = new Callme();
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
            if(t[i].fIndex!=-1){
                found = 1;
                System.out.println("Found at index: "+t[i].fIndex+" in thread "+(i+1));
            }
        }
        if(found==0){
            System.out.println("Not found");
        }
    }
    public static void main(String [] args){
        int size = 1000000;
        int[] arr = new int[size];
        int key = arr[8];
        System.out.println("Key is : "+key);
        long start = System.nanoTime();
        linearSearch(arr, key, size);
        long end = System.nanoTime();
        long execution = (end - start);
        System.out.println("Execution time of Linear search Method is");
        System.out.println(execution + " nanoseconds");
    }
}
