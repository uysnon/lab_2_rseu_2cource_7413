package labmatr7413.avgor.lab_matr;



public class Matrix extends MainActivity implements Comparable<Matrix> {
    private double[][] matrix;


    //Default constructor
    Matrix(){
    }

    //Setting size of matrix
    public void setSize(int rows, int columns){
        this.matrix = new double[rows][columns];

    }

    //Getting size of matrix, type of this method is MatrixSize
    public MatrixSize getSize(){
        int rows = matrix.length;
        int columns = matrix[0].length;
        return new MatrixSize(rows, columns);
    }

    //Setting value to an element WE DON'T NEED
    public void setElem(int rowNum, int colNum, double value) {
        matrix[rowNum][colNum] = value;
    }

    //Getting element value, type of this method is double WE HAVE TO GET ELEMENT VALUE
    public double getElem(int rowNum, int colNum) {
        return matrix[rowNum][colNum];
    }

    public static Matrix createMatrix(int rows, int columns){
        Matrix mt = new Matrix();
        mt.setSize(rows, columns);
        return mt;
    }



    @Override
    public int compareTo(Matrix mat) {
        return 1;
    }

    //Working with sizes of matrices
    public class MatrixSize{
        private int rows, columns;

        public MatrixSize(int rows, int columns){
            this.rows = rows;
            this.columns = columns;
        }

        //Getting amount of rows in matrix
        public int getRows(){
            return this.rows;
        }


        //Getting amount of cols in matrix
        public int getColumns(){
            return this.columns;
        }
    }
}
