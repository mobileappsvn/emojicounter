package robert.com.unittest;

/**
 * Factorial of a Number Using Recursion, https://www.programiz.com/java-programming/recursion
 */
class Factorial {

    static int factorial( int n ) {
        if (n != 0)
            return n * factorial(n-1); // recursive call

        return 1;
    }

    public static void main(String[] args) {
        int number = 4;
        System.out.println(number + " factorial = " + factorial(number));
    }
}