import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int m = input.nextInt();

        int[][] watches = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                watches[i][j] = input.nextInt();
            }
        }

        int maxAlign = 0;

        for (int rowMask = 0; rowMask < (1 << n); rowMask++) {
            int[][] adjusted = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    adjusted[i][j] = watches[i][j];
                }
            }


            for (int i = 0; i < n; i++) {
                if ((rowMask & (1 << i)) != 0) {
                    for (int j = 0; j < m; j++) {
                        adjusted[i][j] = (adjusted[i][j] + 1) % 12;
                    }
                }

            }

            for (int colMask = 0; colMask < (1 << m); colMask++) {
                int[][] tempAdjusted = new int[n][m];
                for(int i = 0; i < n; i++) {
                    System.arraycopy(adjusted[i], 0, tempAdjusted[i], 0, m);
                }

                for (int j = 0; j < m; j++) {
                    if ((colMask & (1 << j)) != 0) {
                        for (int i = 0; i < n; i++) {
                            tempAdjusted[i][j] = (tempAdjusted[i][j] + 1) % 12;
                        }
                    }
                }

                int count = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        int value = tempAdjusted[i][j];

                        if (value == 0 || value == 3 || value == 6 || value == 9) {
                            count++;
                        }
                    }
                }
                maxAlign = Math.max(maxAlign, count);
            }
        }
        System.out.println(maxAlign);

        input.close();
    }
}