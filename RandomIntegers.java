class PrimeNumberIsGenerated extends Exception{
    int index;
    PrimeNumberIsGenerated(int num){
        index = num;
    }
    public String toString(){
        return Integer.toString(this.index);
    }
}

class RandomIntegers{
    public int isPrime(int n){
        if(n==1 || n==0){
            return 0;
        }
        for (int i=2; i<=(n/2); i++){
            if(n%i==0){
                return 0;
            }
        }
        return 1;
    }
    public static void main(String[] args){
        int[] integer = new int[500];
        RandomIntegers r1 = new RandomIntegers();
        for(int i=0; i<500; i++){
            int n1 = (int)(Math.random()*87);
            try{
                if(r1.isPrime(n1)==1){
                    throw new PrimeNumberIsGenerated(i);
                }
                integer[i] = n1;
            }
            catch (PrimeNumberIsGenerated e){
                integer[i] = n1+2;
                System.out.println("Exception at index: "+e);
            }
        }
        System.out.println("{");
        for (int i=0; i<500; i++){
            System.out.print(integer[i]+",");
        }
        System.out.print("}"+"\n");
    }
}