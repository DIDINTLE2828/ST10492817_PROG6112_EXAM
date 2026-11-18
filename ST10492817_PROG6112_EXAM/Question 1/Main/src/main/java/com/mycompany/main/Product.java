
package com.mycompany.main;


public class Product implements IProduct {

    private final String[] productNames;   // single-dimensional array
    private final int[][] sales;           // two-dimensional array [product][month]

    public Product(String[] productNames, int[][] sales) {
        if (productNames == null || sales == null || productNames.length != sales.length) {
            throw new IllegalArgumentException("Product names and sales data must match.");
        }
        this.productNames = productNames;
        this.sales = sales;
    }

    private int GetProductTotal(int productIndex) {
        int total = 0;
        for (int sale : sales[productIndex]) {
            total += sale;
        }
        return total;
    }

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int i = 0; i < sales.length; i++) {
            total += GetProductTotal(i);
        }
        return total;
    }

    @Override
    public double GetAverageProductSold() {
        int count = 0;
        for (int[] row : sales) {
            count += row.length;
        }
        return count == 0 ? 0 : (double) GetTotalProductsSold() / count;
    }

    @Override
    public String GetMostPopularProduct() {
        int bestIndex = 0;
        int bestTotal = Integer.MIN_VALUE;
        for (int i = 0; i < sales.length; i++) {
            int total = GetProductTotal(i);
            if (total > bestTotal) {
                bestTotal = total;
                bestIndex = i;
            }
        }
        return productNames[bestIndex] + " (" + bestTotal + ")";
    }
}
