package robert.com.unittest;

/**
 * Ref to the link https://www.baeldung.com/java-recursion for more infomation
 */
public class RecursionExample {

    /**
     * F(n) = 1, if n=1 or n=2
     * F(n) = F(n-1) + F(n-2), if n>=3
     * @param n
     * @return
     */
    public static long fibonaci(int n) {
        if (n < 3) {
            return 1;
        }
        return fibonaci(n - 1) + fibonaci(n - 2);
    }

    static int count = 0;
      
    static void welcome() {
        count++;
        if (count <= 5) {
            System.out.println(count + ". Hello Robert Hoang");
            welcome();
        }
    }

    public static int sum(int n) {
        if (n >= 1) {
            return sum(n - 1) + n;
        }
        return n;
    }

    public static int tailSum(int currentSum, int n) {
        if (n <= 1) {
            return currentSum + n;
        }
        return tailSum(currentSum + n, n - 1);
    }

    //Converting from Decimal to Binary
    public static String toBinary(int n) {
        if (n <= 1 ) {
            return String.valueOf(n);
        }
        return toBinary(n / 2) + String.valueOf(n % 2);
    }


    public static void main(String[] args) {
        welcome();
        System.out.println("\n======================\n");
        for (int i = 0; i < 10; i++) {
            System.out.println("Fibonaci(" + i + ") = " + fibonaci(i));
        }
        System.out.println("\n======================\n");

    }
 
}