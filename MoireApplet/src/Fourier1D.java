/**
 this class implements the fourier transformation in one dimension
 */
public class Fourier1D {

    /** size of vector */
    private int N_2P;

    /** largest exponent, so that 2^maxPot <= (N_2P-1) */
    private int maxPot;

    /** return the bitreversal-number to k */
    private int bitrev(int k) {
        int rev = 0;

        for (int i=0; i <= maxPot; i++) {
            rev = (rev << 1) | (k & 1);
            k = k >> 1;
        }

        return rev;
    }


    /** constructor for the use of the 1D transformation routines */
    public Fourier1D(int N) {
        int N_tmp;

        // check for power of 2
        N_tmp = N;
        while( (N_tmp >>= 1) != 0 ) {
            if ( ((N_tmp & 0x1) == 0x1) && (N_tmp>>1 != 0) ) {
                System.out.println("Fourier1D: We need a power of 2!");
                System.exit(1);
            }
        }

        N_2P = N;
        maxPot=0;
        while ( (1<<maxPot) <= (N-1) ) {
            maxPot++;
        }
        maxPot--;
    }


    /** iterative version of fft */
    public Complex[] fft (Complex[] v) {

        Complex[] vf;
        Complex omg, tmp;
        int m;

        vf = new Complex[N_2P];
        for(int i =0; i < N_2P; i++) {
            vf[bitrev(i)] = new Complex(v[i]);
        }

        int j = 1;
        for (int k = 0; k <= maxPot; k++) {
            m = j;
            j = 2*m;
            for (int L = 0; L < m; L++) {
                omg = Complex.expi(-L*Math.PI/m);
                for (int M = 0; M <= N_2P-j; M+=j) {
                    tmp = Complex.mult(omg,vf[L+M+m]);
                    vf[L+M+m] = Complex.sub(vf[L+M],tmp);
                    vf[L+M] = Complex.add(vf[L+M],tmp);
                }
            }
        }

        for(int i =0; i < N_2P; i++)
            (vf[i]).mult(1.0/N_2P);

        return vf;
    }


    /** iterative version ifft */
    public Complex[] ifft (Complex[] v) {

        Complex[] vf;
        Complex omg, tmp;
        int m;

        vf = new Complex[N_2P];
        for(int i =0; i < N_2P; i++) {
            vf[bitrev(i)] = new Complex(v[i]);
        }

        int j = 1;
        for (int k = 0; k <= maxPot; k++) {
            m = j;
            j = 2*m;
            for (int L = 0; L < m; L++) {
                omg = Complex.expi(L*Math.PI/m);
                for (int M = 0; M <= N_2P-j; M+=j) {
                    tmp = Complex.mult(omg,vf[L+M+m]);
                    vf[L+M+m] = Complex.sub(vf[L+M],tmp);
                    vf[L+M] = Complex.add(vf[L+M],tmp);
                }
            }
        }

        return vf;
    }
}