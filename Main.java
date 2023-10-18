class Matrix {
    private int[][] matrix;

    public Matrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix add(Matrix other) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[i][j] + other.matrix[i][j];
            }
        }

        return new Matrix(result);
    }

    public Matrix subtract(Matrix other) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[i][j] - other.matrix[i][j];
            }
        }

        return new Matrix(result);
    }

    public Matrix multiply(Matrix other) {
        int rows = matrix.length;
        int cols = other.matrix[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int sum = 0;
                for (int k = 0; k < matrix[0].length; k++) {
                    sum += matrix[i][k] * other.matrix[k][j];
                }
                result[i][j] = sum;
            }
        }

        return new Matrix(result);
    }

    public Matrix divide(Matrix other) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (other.matrix[i][j] != 0) {
                    result[i][j] = matrix[i][j] / other.matrix[i][j];
                } else {
                    // Handle division by zero, for example, set result[i][j] to a special value or throw an exception.
                }
            }
        }

        return new Matrix(result);
    }

    public Matrix dot(Matrix other) {
        int rows = matrix.length;
        int cols = other.matrix[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int sum = 0;
                for (int k = 0; k < matrix[0].length; k++) {
                    sum += matrix[i][k] * other.matrix[k][j];
                }
                result[i][j] = sum;
            }
        }

        return new Matrix(result);
    }

    public Matrix transpose() {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return new Matrix(result);
    }

    public Matrix inverse() {
        // Check if the matrix is square
        if (matrix.length != matrix[0].length) {
            throw new UnsupportedOperationException("Cannot calculate inverse for non-square matrix");
        }

        int n = matrix.length;
        int[][] augmentedMatrix = new int[n][2 * n];

        // Create an augmented matrix [matrix | identity]
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, augmentedMatrix[i], 0, n);
            augmentedMatrix[i][i + n] = 1;
        }

        // Apply Gaussian Elimination to transform the left side into the identity matrix
        for (int i = 0; i < n; i++) {
            // Find pivot
            int pivotRow = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(augmentedMatrix[j][i]) > Math.abs(augmentedMatrix[pivotRow][i])) {
                    pivotRow = j;
                }
            }

            // Swap rows
            int[] temp = augmentedMatrix[i];
            augmentedMatrix[i] = augmentedMatrix[pivotRow];
            augmentedMatrix[pivotRow] = temp;

            // Scale the pivot row to make the pivot element 1
            double pivotElement = augmentedMatrix[i][i];
            for (int j = 0; j < 2 * n; j++) {
                augmentedMatrix[i][j] /= pivotElement;
            }

            // Eliminate other rows
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double factor = augmentedMatrix[j][i];
                    for (int k = 0; k < 2 * n; k++) {
                        augmentedMatrix[j][k] -= factor * augmentedMatrix[i][k];
                    }
                }
            }
        }

        // Extract the inverse matrix from the augmented matrix
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(augmentedMatrix[i], n, result[i], 0, n);
        }

        return new Matrix(result);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(matrix[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static int cariFPB(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

class Pecahan {
    int numerator;
    int denominator;

    public Pecahan(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Pecahan add(Pecahan other) {
        int commonDenominator = this.denominator * other.denominator;
        int sumNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        return new Pecahan(sumNumerator, commonDenominator);
    }

    public Pecahan subtract(Pecahan other) {
        int commonDenominator = this.denominator * other.denominator;
        int diffNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        return new Pecahan(diffNumerator, commonDenominator);
    }

    public Pecahan multiply(Pecahan other) {
        int resultNumerator = this.numerator * other.numerator;
        int resultDenominator = this.denominator * other.denominator;
        return new Pecahan(resultNumerator, resultDenominator);
    }

    public Pecahan divide(Pecahan other) {
        int resultNumerator = this.numerator * other.denominator;
        int resultDenominator = this.denominator * other.numerator;
        return new Pecahan(resultNumerator, resultDenominator);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    public static int cariFPB(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] data1 = {{1, 2}, {3, 4}};
        int[][] data2 = {{5, 6}, {7, 8}};

        Matrix matrix1 = new Matrix(data1);
        Matrix matrix2 = new Matrix(data2);

        // Perform matrix operations
        Matrix additionResult = matrix1.add(matrix2);
        System.out.println("Matrix Addition Result:");
        System.out.println(additionResult);

        Matrix subtractionResult = matrix1.subtract(matrix2);
        System.out.println("Matrix Subtraction Result:");
        System.out.println(subtractionResult);

        Matrix multiplicationResult = matrix1.multiply(matrix2);
        System.out.println("Matrix Multiplication Result:");
        System.out.println(multiplicationResult);

        Matrix divisionResult = matrix1.divide(matrix2);
        System.out.println("Matrix Division Result:");
        System.out.println(divisionResult);

        Matrix dotResult = matrix1.dot(matrix2);
        System.out.println("Matrix Dot Result:");
        System.out.println(dotResult);

        Matrix transposeResult = matrix1.transpose();
        System.out.println("Matrix Transpose Result:");
        System.out.println(transposeResult);

        Matrix inverseResult = matrix1.inverse();
        System.out.println("Matrix Inversion Result:");
        System.out.println(inverseResult);

        // Perform fraction operations
        int numerator1 = 3;
        int denominator1 = 4;
        int numerator2 = 1;
        int denominator2 = 2;

        Pecahan pecahan1 = new Pecahan(numerator1, denominator1);
        Pecahan pecahan2 = new Pecahan(numerator2, denominator2);

        Pecahan fractionAdditionResult = pecahan1.add(pecahan2);
        System.out.println("Fraction Addition Result: " + fractionAdditionResult);

        Pecahan fractionSubtractionResult = pecahan1.subtract(pecahan2);
        System.out.println("Fraction Subtraction Result: " + fractionSubtractionResult);

        Pecahan fractionMultiplicationResult = pecahan1.multiply(pecahan2);
        System.out.println("Fraction Multiplication Result: " + fractionMultiplicationResult);

        Pecahan fractionDivisionResult = pecahan1.divide(pecahan2);
        System.out.println("Fraction Division Result: " + fractionDivisionResult);

        // Calculate and display FPB
        int bilangan1 = 12;
        int bilangan2 = 18;
        int fpb = Matrix.cariFPB(bilangan1, bilangan2);

        System.out.println("FPB dari " + bilangan1 + " dan " + bilangan2 + " adalah: " + fpb);
    }
}