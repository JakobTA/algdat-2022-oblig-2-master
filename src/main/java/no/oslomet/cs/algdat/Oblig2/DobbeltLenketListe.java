package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import org.w3c.dom.ls.LSOutput;

import java.util.*;


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
        //Kontroll av indeks
        indeksKontroll(indeks, false);

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

            int i = antall-1;
            while(i != indeks){
                node = node.forrige;
                i--;
            }
            return node;
        }
    }

    public Liste<T> subliste(int fra, int til) {

        fratilKontroller(antall, fra, til);

        Liste<T> liste = new DobbeltLenketListe<>();
        int tablengde = til-fra;

        if (tablengde < 1) {
            return liste;

        } else{

            Node<T> node = finnNode(fra);

            for (int i = fra; i <=til; i++){

                if (tablengde > 0) {
                    liste.leggInn(node.verdi);
                    node = node.neste;
                    tablengde--;
                }

            }
            return liste;
        }
        //throw new UnsupportedOperationException();
       // return liste;
    }


        private void fratilKontroller(int antall, int fra, int til){
            if (fra < 0)                                  // fra er negativ
                throw new IndexOutOfBoundsException
                        ("fra(" + fra + ") er negativ!");

            if (til > antall)                          // til er utenfor tabellen
                throw new IndexOutOfBoundsException
                        ("til(" + til + ") > antall(" + antall + ")");

            if (fra > til)                                // fra er større enn til
                throw new IllegalArgumentException
                        ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
        }




    @Override
    public int antall() {
        //Bare returnerer antallet
        return antall;

        //throw new UnsupportedOperationException();
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
            antall++;
            endringer++;
            return true;
        //Hvis ikke, begynn på slutten, sett nodes pekere etter hale og flytt halens pekere til noden og flytt halen
        }else{
            node.forrige = hale;
            hale.neste = node;
            hale = node;
            antall++;
            endringer++;
            return true;
        }
        //throw new UnsupportedOperationException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        //Sjekker om verdi er null
        Objects.requireNonNull(verdi, "Verdi kan ikke være null!");
        //Sjekker om indeks er innenfor grensene. Å bruke indeksKontroll ga feil i testing. Gjøres derfor slik.
        if(indeks > antall)  throw new IndexOutOfBoundsException("Indeks kan ikke være større enn antall noder!");
        if(indeks < 0) throw new IndexOutOfBoundsException("Indeks kan ikke være mindre enn 0!");

        //Oppretter den nye noden med inn-verdien
        Node<T> node = new Node<>(verdi, null, null);

        if(tom()){ //Tilfelle 1 - lista er tom
            hode = hale = node;
        } else if(indeks == 0){ //Tilfelle 2 - skal legges først
            hode.forrige = node; node.neste = hode;
            hode = node;
        } else if(indeks == antall){ //Tilfelle 3 - skal legges bakerst
            hale.neste = node; node.forrige = hale;
            hale = node;
        } else{ //Tilfelle 4 - skal legges mellom to noder. Koden under er lånt og tilpasset fra kompendiet.
            //Begynner på begynnelsen av listen
            node = hode;
            //Går igjennom listen og finner noden før indeksen
            for (int i = 0; i < indeks; i++) node = node.neste;{
                //Setter noden som skal inn, mellom to noder underveis
                node = new Node<T>(verdi, node.forrige, node);
            }
            //Setter til slutt noden på indeks og setter korrekt pekere bak og frem
            node.neste.forrige = node.forrige.neste = node;
        }
        antall++; endringer++;
        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        if(indeksTil(verdi) == -1){
            return false;
        }else{
            return true;
        }
        //throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        //Kontroll av indeks. Ikke egt nødvendig siden indeks sjekkes i finnNode(), men oppgaven spør etter det.
        indeksKontroll(indeks, false);

        Node<T> node = finnNode(indeks);
        return node.verdi;
        //throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        if(verdi == null)
            return -1;

        //Variabler som lagrer valgt node
        Node<T> node = hode;
        //Går igjennom listen, sammenlign verdier, og går til neste indeks/node
        for(int i = 0; i < antall; i++){
            if(node.verdi.equals(verdi))
                return i;
            node = node.neste;
        }
        //Hvis verdien ikke finnes i listen, returner -1
        return -1;
        // throw new UnsupportedOperationException();
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
        //throw new UnsupportedOperationException();
        if (verdi == null) return false;          // ingen nullverdier i listen

        Node<T> q = hode, p = null;               // hjelpepekere

        while (q != null)                         // q skal finne verdien t
        {
            if (q.verdi.equals(verdi)) break;       // verdien funnet
            p = q; q = q.neste;                     // p er forgjengeren til q
        }

        if (q == null) return false;              // fant ikke verdi
        else if (q == hode) hode = hode.neste;    // går forbi q
        else p.neste = q.neste;                   // går forbi q

        if (q == hale) hale = p;                  // oppdaterer hale

        q.verdi = null;                           // nuller verdien til q
        q.neste = null;                           // nuller nestepeker

        antall--;                                 // en node mindre i listen

        return true;                              // vellykket fjerning
    }

    @Override
    public T fjern(int indeks) {

        //throw new UnsupportedOperationException();

        indeksKontroll(indeks, false);  // Se Liste, false: indeks = antall er ulovlig

        T temp;                              // hjelpevariabel

        if (indeks == 0)                     // skal første verdi fjernes?
        {
            temp = hode.verdi;                 // tar vare på verdien som skal fjernes
            hode = hode.neste;                 // hode flyttes til neste node
            if (antall == 1) hale = null;      // det var kun en verdi i listen
        }
        else
        {
            Node<T> p = finnNode(indeks - 1);  // p er noden foran den som skal fjernes
            Node<T> q = p.neste;               // q skal fjernes
            temp = q.verdi;                    // tar vare på verdien som skal fjernes

            if (q == hale) hale = p;           // q er siste node
            p.neste = q.neste;                 // "hopper over" q
        }

        antall--;                            // reduserer antallet
        return temp;                         // returner fjernet verdi
    }

    @Override
    public void nullstill() {

        //throw new UnsupportedOperationException();

        Node<T> p = hode, q = null;

        while(p!=null){
            q = p.neste;
            p.forrige = p.neste = null;
            p.verdi = null;
            p = q;
        }

        hode = hale = null;
        antall = 0;
        endringer++;
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
        //insperiasjon fra kompendiet programkode Programkode 3.3.4 e)

        //Lag metoden Iterator<T> iterator(). Den skal returnere en instans av iteratorklassen.
        return new DobbeltLenketListeIterator();

        //throw new UnsupportedOperationException();
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
            denne = finnNode(indeks); // Den skal sette pekeren denne til den noden som hører til den oppgitte indeksen.
            //. Resten skal være som i den konstruktøren som er ferdigkodet
            fjernOK = false;
            iteratorendringer = endringer;

            //throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {

            if(!hasNext()){
                throw new NoSuchElementException("Tomt eller ingen verider igjen"); // Så en NoSuchElementException hvis det ikke er flere igjen i listen (dvs. hvis hasNext() ikke er sann/true).
            }
            //Lag metoden T next(). Den skal først sjekke om iteratorendringer er lik endringer.
            if(iteratorendringer != endringer){
                throw new ConcurrentModificationException(); //Hvis ikke, kastes en ConcurrentModificationException.
            }
            //henter inspirasjon fra kompendiet programkode 3.2.4 c) public T next()


            // Deretter
            //settes fjernOK til sann/true, verdien til denne returneres og denne flyttes til den neste node.
            fjernOK = true;
            denne = denne.neste;
            return denne.verdi;

            //throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    //oppgave 10
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {

        if(liste.antall() > 1){ // sjekker om antall elementer i listen er mer enn 1. Hvis den er det, så kjøres koden.
            int antall_elementer = liste.antall(); // tilordner antall elementer


            //har kodet utvalgssortering
            for(int i = antall_elementer; i > 1; i--){
                //maks metoden
                int maksindeks = 0;
                T maksverdi = liste.hent(0);
                for(int j = 1; j < i; j++){
                    if(c.compare(liste.hent(j),maksverdi)> 0){
                        maksverdi = liste.hent(j);
                        maksindeks = j;
                    }
                }
                //bytt metoden.
                T tempMaks = liste.hent(maksindeks);
                T tempSiste = liste.hent(i-1);
                liste.oppdater(maksindeks,tempSiste);
                liste.oppdater(i-1,maksverdi);

            }

        }
        else{
            return;//throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Integer[] s = {8, 5, 2, 11, 7, 3, 15, 14, 10, 17, 18, 9, 4, 12, 13, 19, 20, 1, 16, 6};
        String [] navn = {"G", "B", "F", "C", "E", "D", "A"};
        Liste<Integer> l1 = new DobbeltLenketListe<>(s);
        Liste<String> l2 = new DobbeltLenketListe<>(navn);
        System.out.println(l1);
        DobbeltLenketListe.sorter(l1,Comparator.naturalOrder());
        System.out.println(l1);
        System.out.println("");
        System.out.println(l2);
        DobbeltLenketListe.sorter(l2,Comparator.naturalOrder());
        System.out.println(l2);
    }

} // class DobbeltLenketListe

