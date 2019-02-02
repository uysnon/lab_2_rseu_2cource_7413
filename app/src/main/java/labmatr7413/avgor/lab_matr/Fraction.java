package labmatr7413.avgor.lab_matr;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
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

    public int gcm(Fraction fraction){
        int a = fraction.numerator;
        int b = fraction.denominator;
        return b == 0 ? a : gcm(new Fraction(b, a%b));
    }

    public Fraction simplify(Fraction fraction){
        int gcm = gcm(fraction);
        return new Fraction(fraction.numerator/gcm, fraction.denominator/gcm);
    }

    public Fraction mult(Fraction fraction1, Fraction fraction2){
        return simplify( new Fraction(fraction1.numerator* fraction2.numerator, fraction1.denominator * fraction2.denominator));
    }

    public Fraction division(Fraction fraction1, Fraction fraction2){
        return simplify( new Fraction(fraction1.numerator* fraction2.denominator, fraction1.denominator * fraction2.numerator));
    }

}
