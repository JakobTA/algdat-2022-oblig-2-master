package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        //throw new UnsupportedOperationException();
    }

    public DobbeltLenketListe(T[] a) {
        this();  // alle variabelene er nullet

        /*
        if(tom()){
            hode =null;
            hale =null;
            throw new NullPointerException("Tabellen er null!");
        }

         */

        //skal loope gjennom gjennom listen a. For å legge verdiene i a i LenketListe.
        // Vi sjekker også om noen av verdiene i tabellen a er null.

        //Setter hode som startverdi "current"
        //Node<T> current = hode;

        //Går igjennom a
        for(int i = 0; i < a.length; i++){
            //Hvis element i a er null, gå videre til neste element
            if(a[i] == null){
                continue;
            }
            else{
                hode = new Node<>(a[i]);
                //hale = hale.neste = new Node<>(a[i]);
            }
            //Hvis ikke null, opprett ny node med elementets verdi og sett denne som neste node i listen
            /*
            Node<T> node = new Node<>(a[i]);
            antall++;
            node.forrige = current;
            current.neste = node;
            current = node;

             */
        }

        /*
        // Finner den første i a som ikke er null
        int i = 0; for (; i < a.length && a[i] == null; i++);


        if (i < a.length)
        {
            Node<T> p = hode = new Node<>(a[i]);  // den første noden
            antall = 1;                                 // vi har minst en node

            for (i++; i < a.length; i++)
            {
                if (a[i] != null)
                {
                    p = p.neste = new Node<>(a[i]);   // en ny node
                    antall++;
                }
            }
            hale = a[i];
        } */



        //throw new UnsupportedOperationException();
    }

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        return antall;

        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean tom() {

        return antall == 0;
        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean leggInn(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }

    public String omvendtString() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        String[] s = {"Ole","Per", "Kari"};
        Liste<String> liste = new DobbeltLenketListe<>(s);
        System.out.println(liste.antall() + " " + liste.tom());

    }

} // class DobbeltLenketListe


