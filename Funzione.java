import java.util.List;
import java.util.ArrayList;

public class Funzione {

    private int dimensioneDominio;
    private Parametrizzazione parametrizzazione;

    public class Vettore {
        int numComponenti;
        double[] componenti;

        public Vettore(double[] componenti) {
            this.numComponenti = componenti.length;
            this.componenti = new double[numComponenti];

            for(int i = 0; i < numComponenti; i++) {
                this.componenti[i] = componenti[i];
            }
        }
    }
    
    public class Parametrizzazione {
        private List<Funzione> funzioni;

        public Parametrizzazione(Funzione[] funzioni) {
            int numFunzioni = funzioni.length;

            for(int i = 0; i < numFunzioni; i++) {
                if(funzioni[i].getDimensioneDominio() != 1) {
                    throw new IllegalArgumentException("Le funzioni devono essere definite in un sottoinsieme di R1.");
                }
            }

            this.funzioni = new ArrayList<>();
            for(Funzione funzione : funzioni) {
                this.funzioni.add(funzione);
            }
        }

        public Vettore calcolaVettore(double t) {
            double[] componenti = new double[funzioni.size()];
            Vettore parametro = new Vettore(new double[] {t});

            for (int i = 0; i < componenti.length; i++) {
                componenti[i] = this.funzioni.get(i).calcolaIn(parametro);
            }

            return new Vettore(componenti);
        }

        public Parametrizzazione getDerivata() {
            if(isDerivabile()) {
                // TO DO: creare il metodo per il calcolo della derivata della parametrizzazione
                return this;
            }
            return null;
        }

        public boolean isDerivabile(double t0) {
            // TO DO: creare il metodo per determinare se è derivabile
            return true;
        }
    }

    public double chainRule(double t0) {
        if(this.parametrizzazione == null) {
            throw new IllegalAccessException("Parametrizzazione assente.");
        }

        // this deve essere di classe C1 e la parametrizzazione deve essere derivabile.
        if(!this.isClasseC(1) || !this.parametrizzazione.isDerivabile(t0)) {
            return Double.NaN;
        }

        double valore = 0;

        // In R2: F'(t0) = ∂f/∂x (x(t0), y(t0)) * x'(t0) + ∂f/∂y (x(t0), y(t0)) * y'(t0)
        for(int i = 0; i < this.dimensioneDominio; i++) {
            valore += this.getDerivataParziale(i).calcolaIn(t0) * this.parametrizzazione.getDerivata().calcolaVettore(t0);
        }

        return valore;
    }

    public double calcolaIn(Vettore x) {
        // TO DO: creare il metodo per il calcolo della funzione nel punto
        return 0;

        // dovrà restituire NaN se x non appartiene al dominio
    }

    public Funzione getDerivataParziale(int numeroComponente) {
        // TO DO: creare il metodo per il calcolo della derivata
        return this;

        // dovrà restituire null se non è derivabile
    }

    public boolean isClasseC(int n) {
        boolean isClasseC = true;
        // TO DO: creare il metodo per determinare la classe Cn

        return isClasseC;
    }

    public int getDimensioneDominio() {
        return dimensioneDominio;
    }
}
