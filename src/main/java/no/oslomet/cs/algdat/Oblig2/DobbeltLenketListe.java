package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import org.w3c.dom.ls.LSOutput;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


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
        //Setter startverdier til null og 0.
        hode = hale = null;
        antall = 0;
        endringer = 0;
        // throw new UnsupportedOperationException();
    }

    public DobbeltLenketListe(T[] a) {
        //Dersom a == null, gi brukeren feilmelding
        Objects.requireNonNull(a,"Ikke tillatt med null verdier");

        //Finne første element i a som ikke er null.
        int i = 0;
        for(;i<a.length && a[i] == null; i++);

        if(i < a.length){
            //Oppretter den første noden ved hode og setter pekere til null
            Node<T> nyNode = hode = new Node<>(a[i],null,null);
            antall ++;
            endringer++;

            //Går igjennom resten av elementer i a, dersom de finnes/ikke er null
            //og oppretter nye noder med elementet i a og setter pekere
            for(i++;i<a.length;i++){
                if(a[i] != null){
                    nyNode = nyNode.neste = new Node<>(a[i],nyNode,null);
                    antall++;
                    endringer++;
                }
                hale = nyNode;
                hale.neste = null;
                hode.forrige = null;
            }
        }
        //throw new UnsupportedOperationException();
    }

    //Hjelpemetode for å returnere noden med gitt indeks
    private Node<T> finnNode(int indeks){
        //Hvis index er mindre enn antall/2 begynn fra hodet
        if(indeks < antall/2){
            Node<T> node = hode;

            //Gå igjennom én og én node helt til vi finner den rette, returner denne
            int i = 0;
            while(i != indeks){
                node = node.neste;
                i++;
            }
            return node;
        } else{
        //Hvis index er større eller lik antall/2 begynn fra halen. Gå bakover til noden er funnet
            Node<T> node = hale;

            int i = 0;
            while(i != indeks){
                node = node.forrige;
                i--;
            }
            return node;
        }
    }

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        //Bare returnerer antallet
        return antall;//throw new UnsupportedOperationException();
    }

    @Override
    public boolean tom() {
        //Returner true dersom antall noder er 0.
        return antall == 0;
        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean leggInn(T verdi) {
        //Sjekker først at elementet ikke er null, hvis ja, send feilmelding
        Objects.requireNonNull(verdi,"Ikke tillatt med null verdier");

        //Oppretter ny node
        Node<T> node = new Node<>(verdi);

        //Hvis listen er tom, la hode og hale peke til den første nye noden
        if(tom()){
            hode = hale = node;
            antall++; endringer++;
            return true;
        //Hvis ikke, begynn på slutten, sett nodes pekere etter hale og flytt halens pekere til noden og flytt halen
        }else{
            node.forrige = hale;
            hale.neste = node;
            hale = node;
            antall++; endringer++;
            return true;
        }
        //throw new UnsupportedOperationException();
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
        //Kontroll av indeks
        indeksKontroll(indeks, false);

        Node<T> node = finnNode(indeks);
        return node.verdi;
        //throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {

        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi, "Kan ikke være null!");
        indeksKontroll(indeks, false);

        //Lagrer nodens gamle verdi
        T gammel = hent(indeks);

        //Går setter ny verdi på noden
        finnNode(indeks).verdi = nyverdi;

        endringer++;
        return gammel;
        // throw new UnsupportedOperationException();
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
        //Bruker stringbuilder for å bygge ut-strengen. Starter med '['.
        StringBuilder s = new StringBuilder();
        s.append('[');

        //Så lenge den lenkede listen ikke er tom gjør følgende.
        if (!tom())
        {
            //Begynn med noden som hodet peker på, legg verdien inn i strengen
            Node<T> p = hode;
            s.append(p.verdi);

            //Valgt node endres til neste node i listen
            p = p.neste;

            //Så lenge noden ikke er null, legg resten av listens elementer til i strengen.
            while (p != null)
            {
                s.append(',').append(' ').append(p.verdi);
                p = p.neste;
            }
        }
        //Legg til slutt-bracket og returner den konstruerte strengen
        s.append(']');
        return s.toString();
        //throw new UnsupportedOperationException();
    }

    //Metoden gjør akkurat det samme som toString, men går baklengs istedenfor.
    public String omvendtString() {
        StringBuilder s = new StringBuilder();
        s.append('[');

        if (!tom())
        {
            Node<T> p = hale;
            s.append(p.verdi);

            p = p.forrige;

            while (p != null)
            {
                s.append(',').append(' ').append(p.verdi);
                p = p.forrige;
            }
        }
        s.append(']');
        return s.toString();
        //throw new UnsupportedOperationException();
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

} // class DobbeltLenketListe

