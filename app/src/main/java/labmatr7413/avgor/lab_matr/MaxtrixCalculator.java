package labmatr7413.avgor.lab_matr;



public class MaxtrixCalculator {

    MaxtrixCalculator(){
    }




    public static Matrix swapLine(int swapLine, int targetLine, Matrix mt1) {
        int rows = mt1.getSize().getRows();
        int cols = mt1.getSize().getColumns();
        double tmp;
        int secondLine = targetLine;
        for (int firstLine=swapLine; swapLine<rows; swapLine++) {
            for (int j=0; j<cols; j++) {
                tmp = mt1.getElem(firstLine, j);
                mt1.setElem(firstLine, j, mt1.getElem(secondLine, j));
                mt1.setElem(secondLine, j, tmp);
            }
        }
        return mt1;
    }


    public static double gauss (Matrix mt1) {
        int rows = mt1.getSize().getRows();
        int cols = mt1.getSize().getColumns();
        double result = 1;
        int count;

        Matrix mt = new Matrix();
        mt.setSize(rows, cols);
        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
                mt.setElem(i, j, mt1.getElem(i, j));
            }
        }

        Matrix mt2 = new Matrix();
        mt2.setSize(rows, cols);
        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
                mt2.setElem(i, j, mt1.getElem(i, j));
            }
        }

        for (int i=0; i<cols; i++){


            if (mt.getElem(i, i)==0 && i<rows-1)
            {
                count = i;
                do{
                    mt.getElem(count, 0);
                    count++;
                }while(mt.getElem(count, 0) == 0);
                mt = swapLine(i, count, mt);
                result = -result;
            }


            for (int x=0; x<rows; x++){
                for (int y=0; y<cols; y++){
                    mt2.setElem(x, y, mt.getElem(x, y));
                }
            }


            for (int j=i+1; j<rows; j++){
                for (int k=i; k<cols; k++){
                    double tmp = mt.getElem(j, k)-((mt.getElem(i, k)*mt2.getElem(j, i))/mt.getElem(i, i));
                    mt.setElem(j, k, tmp);
                }
            }

        }
        for (int x=0; x<rows; x++){
            result = result*mt.getElem(x, x);
        }

        return result;
    }




}
