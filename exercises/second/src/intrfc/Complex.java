package intrfc;

public interface Complex extends Comparable{
    double real();
    double complex();
    double mod();
    double arg();
    default boolean isReal(){
        return this.complex()==0;
    }
    /// comparison
    @Override
    public int compareTo(Object ot);
    /// factory methods
    static Complex fromRect(double re, double im){
        return new ComplexRect(re,im);
    }
    static Complex fromPolar(double mod, double arg){
        return new ComplexPolar(mod, arg);
    }
    class ComplexRect implements Complex{
        private double im,re;
        ComplexRect(double im, double re){
            this.im = im; this.re = re;
        }
        @Override
        public int compareTo(Object ot){
            Complex other = (Complex) ot;
            return (int) (this.mod()-other.mod());
        }
        @Override
        public double real() {
            return re;
        }

        @Override
        public double complex() {
            return im;
        }

        @Override
        public double mod() {
            return Math.sqrt(re*re + im*im);
        }

        @Override
        public double arg() {
            return Math.atan2(im, re);
        }
    }
    class ComplexPolar implements Complex{
        private double mod, arg;
        ComplexPolar(double mod, double arg){
            this.mod = mod; this.arg = arg;
        }
        @Override
        public int compareTo(Object ot){
            Complex other = (Complex) ot;
            return (int) (this.mod()-other.mod());
        }
        @Override
        public double real() {
            return mod*Math.cos(arg);
        }

        @Override
        public double complex() {
            return mod*Math.sin(arg);
        }

        @Override
        public double mod() {
            return this.mod;
        }

        @Override
        public double arg() {
            return this.arg;
        }
    }
}
