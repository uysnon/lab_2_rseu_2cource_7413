package labmatr7413.avgor.lab_matr;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(Fraction fraction){
        this.numerator = fraction.numerator;
        this.denominator = fraction.denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public static int gcm(int a, int b){
        return b == 0 ? a : gcm(b, a%b);
    }

    public static Fraction simplify(Fraction fraction){
        int gcm = gcm(fraction.numerator, fraction.denominator);
        if (fraction.numerator == 0) return  new Fraction(0, 1);
        Fraction fraction1 = new Fraction(fraction.numerator/gcm, fraction.denominator/gcm);
        if (fraction1.numerator*fraction1.denominator>=0) {
            fraction1.numerator = Math.abs(fraction1.numerator);
            fraction1.denominator = Math.abs(fraction1.denominator);
        }else {
            fraction1.numerator = -Math.abs(fraction1.numerator);
            fraction1.denominator = Math.abs(fraction1.denominator);
        }
        return fraction1;
    }

    public static Fraction multiply(Fraction fraction1, Fraction fraction2){
        return simplify( new Fraction(fraction1.numerator* fraction2.numerator, fraction1.denominator * fraction2.denominator));
    }

    public static Fraction division(Fraction fraction1, Fraction fraction2){
        return simplify( new Fraction(fraction1.numerator* fraction2.denominator, fraction1.denominator * fraction2.numerator));
    }

    public static Fraction add(Fraction fraction1, Fraction fraction2){
        return simplify( new Fraction(
                fraction1.numerator* fraction2.denominator + fraction2.numerator* fraction1.denominator,
                fraction1.denominator * fraction2.denominator));
    }

    public static Fraction subtraction(Fraction fraction1, Fraction fraction2){
        return simplify( new Fraction(
                fraction1.numerator* fraction2.denominator - fraction2.numerator* fraction1.denominator,
                fraction1.denominator * fraction2.denominator));
    }





}
