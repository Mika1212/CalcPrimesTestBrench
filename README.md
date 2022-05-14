# CalcPrimesTestBrench

A simple bencmark to test 2 sieve methods:
Sieve of Eratosthenes
Sieve of Euler

Eratosthenes test:
java -jar target/benchmarks.jar eratosthenes -f 1 -i 5 -wi 5

Euler test:
java -jar target/benchmarks.jar euler -f 1 -i 5 -wi 5

-----------------------------
Also there are 2 methods to test different data structures:
boolean[]
int[]

Boolean test:
java -jar target/benchmarks.jar testBool -f 1 -i 5 -wi 5

Int test:
java -jar target/benchmarks.jar testInt -f 1 -i 5 -wi 5
