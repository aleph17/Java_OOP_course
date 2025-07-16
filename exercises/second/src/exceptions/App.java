package exceptions;

public class App {
    public static void main(String[] args) throws Exception{
        String[] numbers = {"1","2","twenty", "4"};
//        int sum1 = sumNumbers(numbers);
        int array[] = new int[] {1,2,3};
        for(int i: array)
            System.out.println(i);
//        System.out.println(sum1);
    }
    public static int sumNumbers(String[] numbers) throws MyException{
        int sum =0;
        try {
            for(String n:numbers)
                sum += Integer.valueOf(n);
            } catch(Exception e){
                System.out.println(e);
        }

        return sum;
    }
    public static void some(Integer a){
        System.out.println(a);
    }
}
