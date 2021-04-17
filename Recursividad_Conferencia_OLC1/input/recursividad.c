/**
 * RECURSION SIMPLE.
 */
int factorial(int n) {
    if(n == 0) {
        return 1;
    } 
    return n * factorial(n - 1);
}

/**
 * RECURSION MULTIPLE.
 */

int fibonacci(int n) {
    if (n <= 1) {
        return n;
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
}

/**
 * RECURSION CRUZADA.
 */

int par(int num) {
    if (num == 0) {
        return 1;
    }
    return impar(num - 1);
}

int impar(int num) {
    if (num == 0) {
        return 0;
    }
    return par(num - 1);
}

/**
 * RECURSION ANIDADA.
 */

int ackermann(int m, int n) {
    if (m == 0) {
        return (n + 1);
    }
    if (m > 0 && n == 0) {
        return ackermann(m - 1, 1);
    }
    return ackermann(m - 1, ackermann(m, n - 1));
}

println(factorial(12));
println(fibonacci(25));
println(par(250));
println(ackermann(3, 4));
