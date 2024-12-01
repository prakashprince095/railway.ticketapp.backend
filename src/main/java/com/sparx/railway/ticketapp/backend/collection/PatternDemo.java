package com.sparx.railway.ticketapp.backend.collection;

public class PatternDemo {
    public static void main(String [] args){
//      pattern1(5);
//		pattern2(5);
//		pattern3(5);
//		pattern4(5);
//		pattern5(5);
//		pattern6(5);
//		pattern7(5);
//		pattern8(5);
//		pattern9(5);
//		pattern10(5);
//		pattern11(5);
//		pattern12(4);
//		pattern13(5);
//		pattern14(5);
//		pattern15(5);
//		pattern16(5);
//		pattern17(4);
//		pattern18(5);
//		pattern19(5);
//		pattern20(5);
//		pattern21(4);
//		pattern22(7);
    }
    /*
     *
     * *****
     * *****
     * *****
     * *****
     * *****
     */
    public static void pattern1(int n) {
        for(int row=1;row<=n;row++) {
            for(int col =1;col<=n;col++) {
                System.out.print("* ");
            }
            System.out.println();

        }

    }

    /*
     * *
     * **
     * ***
     * ****
     * *****
     */
    public static void pattern2(int n) {
        for(int row=1;row<=n;row++) {
            for(int col=1;col<=row;col++) {
                System.out.print("* ");
            }
            System.out.println();
        }

    }

    /*
     * 1
     * 12
     * 123
     * 1234
     * 124345
     */
    public static void pattern3(int n) {
        for(int row=1;row<=n;row++) {
            for(int col=1;col<=row;col++) {
                System.out.print(col+" ");
            }
            System.out.println();
        }
    }
    /*
     * 1
     * 22
     * 333
     * 4444
     * 55555
     */
    public static void pattern4(int n) {
        for(int row=1;row<=n;row++) {
            for(int col=1;col<=row;col++) {
                System.out.print(row+" ");
            }
            System.out.println();
        }

    }
    /*
     * *****
     * ****
     * ***
     * **
     * *
     */
    public static void pattern5(int n) {
        for(int row=0;row<n;row++) {
            for(int col=n-row;col>0;col--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    /*
     * 12345
     * 1234
     * 123
     * 12
     * 1
     */
    public static void pattern6(int n) {
        for(int row=1;row<=n;row++) {
            for(int col= 1;col<=n-row+1;col++) {
                System.out.print(col);
            }
            System.out.println();
        }
    }
    /*
     *     *
     *    ***
     *   *****
     *  *******
     * *********
     */

    public static void pattern7(int n) {
        for(int row=1;row<=n;row++) {
            int size=((2*row)-1);
            for(int i=n-row;i>0;i--) {
                System.out.print(" ");
            }
            for(int col=1;col<=size; col++) {
                System.out.print("*");
            }
            System.out.println();
        }

    }
    /*
     * *********
     *  *******
     *   *****
     *    ***
     *     *
     */
    public static void pattern8(int n) {
        for(int row=n;row>0;row--) {
            int size=(2*row)-1;
            int spaceSize=n-row;
            for(int i= 1;i<=spaceSize;i++) {
                System.out.print(" ");
            }
            for(int col=1;col<=size;col++) {
                System.out.print("*");
            }
            System.out.println();
        }

    }
    /*
     *     *
     *    ***
     *   *****
     *  *******
     * *********
     *  *******
     *   *****
     *    ***
     *     *
     */
    public static void pattern9(int n) {
        int size=0;
        int spaceSize=0;

        for(int row=1;row<=2*n-1;row++) {

            if(row<=n) {
                size=2*row-1;
                spaceSize=n-row;
            }else {
                spaceSize = spaceSize+1;
                size=size-2;
            }
            for(int i=0;i<spaceSize;i++) {
                System.out.print(" ");
            }
            for(int col=1;col<=size;col++) {
                System.out.print("*");
            }System.out.println();
        }
    }
    /*
     * *
     * **
     * ***
     * ****
     * *****
     * ****
     * ***
     * **
     * *
     */
    public static void pattern10(int n) {
        int size=0;
        for(int row=1;row<2*n;row++) {
            if(row<=n) {
                size=row;
            }else {
                size=size-1;
            }
            for(int col=1;col<=size;col++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    /*
     * 1
     * 01
     * 101
     * 0101
     * 10101
     */
    public static void pattern11(int n) {
        for(int row=1;row<=n;row++) {
            for(int col=1 ;col<=row;col++) {
                if((row+col)%2==0) {
                    System.out.print("1");
                }else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }
    /*
     *1      1
     * 12    21
     * 123  321
     * 12344321
     */
    public static void pattern12(int n) {
        int numberTobePrinted=0;
        for(int row=1;row<=n;row++) {
            int printNo=row;
            for(int col=1;col<=2*n;col++) {
                if(col<=row) {
                    System.out.print(col);

                }else if(col >= (2*n-row)  ) {
                    System.out.println(row);

                }
                else {
                    System.out.print("@");
                }
            }
            System.out.println();
        }

    }
    /*
     * 1
     * 2 3
     * 4 5 6
     * 7 8 9 10
     * 11 12 13 14 15
     *
     */
    public static void pattern13(int n) {
        int printCounter=0;
        for(int row=1;row<=n;row++) {
            for(int col=1;col<=row;col++) {
                System.out.print(++printCounter+" ");
            }
            System.out.println();
        }
    }
    /*
     * A
     * A B
     * A B C
     * A B C D
     * A B C D E
     */
    public static void pattern14(int n) {
        for(int row=1;row<=n;row++) {
            char c='A';
            for(int col=1;col<=row;col++) {
                System.out.print(c++);

            }
            System.out.println();
        }
    }
    /*
     * ABCDE
     * ABCD
     * ABC
     * AB
     * A
     */
    public static void pattern15(int n) {
        for(int row=n;row>0;row--) {
            char c='A';
            for(int col=1;col<=row;col++) {
                System.out.print(c++);
            }
            System.out.println();
        }
    }
    /*
     * A
     * BB
     * CCC
     * DDDD
     * EEEEE
     */

    public static void pattern16(int n) {
        char c='A';
        for(int row=1;row<=n;row++) {
            for(int col=1;col<=row;col++) {
                System.out.print(c);
            }
            c++;
            System.out.println();
        }
    }
    /*
     *    A
     *   ABA
     *  ABCBA
     * ABCDCBA
     */
    public static void pattern17(int n) {
        for(int row=0;row<=n;row++) {
            char c ='A';
            int count=0;
            for(int col=1;col<n+row;col++) {
                if(row+col <=n) {
                    System.out.print(" ");
                }else {
                    if( col <=n) {
                        System.out.print(c);
                        c++;
                    }else {
                        if(count == 0) {
                            --c;
                            count++;
                        }
                        --c;
                        System.out.print(c);
                    }
                }
            }
            System.out.println();
        }

    }
    /*
     * E
     * DE
     * CDE
     * BCDE
     * ABCDE
     */
    public static void pattern18(int n) {
        char c='E';

        for(int row=1; row<=n; row++) {
            char d=c;
            for(int col=1;col<=row;col++) {
                System.out.print(d++);

            }
            c--;
            System.out.println();
        }

    }
    /*
     * **********
     * ****  ****
     * ***    ***
     * **      **
     * *        *
     * *        *
     * **      **
     * ***    ***
     * ****  ****
     * **********
     */
    public static void pattern19(int n) {
        int initialCount=n+1;
        int finalCount=n;
        for(int row=1;row<2*n;row++) {
            if(row <= n) {
                initialCount--;
                finalCount++;

            }else {
                initialCount++;
                finalCount--;

            }
            for(int col =1;col<=2*n;col++) {
                if(col > initialCount && col < finalCount) {
                    System.out.print(" ");
                }else {
                    System.out.print("*");

                }
            }
            System.out.println();
        }

    }
    /*
     * *        *
     * **      **
     * ***    ***
     * ****  ****
     * **********
     * ****  ****
     * ***    ***
     * **      **
     * *        *
     *
     */
    public static void pattern20(int n) {
        int initalCount=0;
        int finalCount=2*n+1;
        for(int row=1; row <(2*n); row++) {
            if(row <=n) {
                initalCount++;
                finalCount--;
            }else {
                initalCount--;
                finalCount++;
            }

            for(int col =1;col<=(2*n); col++) {
                if(col<=initalCount || col >= finalCount) {
                    System.out.print("*");
                }else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }

    /*
     * ****
     *
     * *  *
     *
     * *  *
     *
     * ****
     */
    public static void pattern21(int n) {
        for(int row =1;row<(2*n); row++ ) {
            for(int col =1;col<=n;col++) {
                if(row ==1 || row == (2*n-1)) {
                    System.out.print("*");
                }else if(row %2 !=0) {
                    if(col ==1 || col ==n) {
                        System.out.print("*");
                    }else {
                        System.out.print(" ");
                    }
                }else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }
    /*
     *
     */
    public static void pattern22(int n) {
        for(int row=1;row<=n;row++) {
            int count=4;
            for(int col=1;col<=n;col++) {
                if(row ==1 || col==1 || row == n || col == n) {
                    System.out.print("4");
                }else if(row ==2 || col ==2 || row == n-1 || col == n-1) {
                    System.out.print("3");
                }else if(row ==3 || col ==3 || row == n-2 || col == n-2) {
                    System.out.print("2");
                }
                else {
                    System.out.print("1");
                }
            }
            System.out.println();
        }
    }



}
