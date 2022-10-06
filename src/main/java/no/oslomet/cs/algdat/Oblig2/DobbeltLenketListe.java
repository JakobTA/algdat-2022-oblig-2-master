package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import org.w3c.dom.ls.LSOutput;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class DobbeltLenketListe<T> implements Liste<T> {
//hei
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

        Node<T> q = hode;            // hjelpepekere

        while (q != null)                         // q skal finne verdien t
        {
            if (q.verdi.equals(verdi)) break;       // verdien funnet
            q = q.neste;                     // p er forgjengeren til q
        }

        if (q == null) return false;              // fant ikke verdi

        else if (antall==1){           //lengde lik 1
            hode=hale=null;
        }

        else if (q == hode ){ //fjerne først
            hode = hode.neste;
            hode.forrige = null;

        }

        else if (q == hale) {  //fjerne sist
            hale = hale.forrige;
            hale.neste = null;
        }

        else{                  // fjerne i midten
            q.forrige.neste = q.neste;
            q.neste.forrige = q.forrige;
        }

        q.verdi = null;               //husk å sette pekerne lik null!
        q.forrige = q.neste = null;

        antall--;                                 // en node mindre i listen
        endringer++;

        return true;                              // vellykket fjerning
    }

    @Override
    public T fjern(int indeks) {

        //throw new UnsupportedOperationException();

        indeksKontroll(indeks, false);  // Se Liste, false: indeks = antall er ulovlig

        Node<T> peker = hode;                             // hjelpevariabel PEKER FJERNER

        if (antall == 1) {          //tilfelle 1
            hode = hale = null;    // det var kun en verdi i listen
        }
                                              //tilfelle 2
        else if (indeks == 0)                     // skal første verdi fjernes?
        {

            hode = hode.neste;              // hode flyttes til neste node
            hode.forrige=null;              //hode sin forrige settes til null
        }

                                     //tilfelle 3
        else if (indeks == antall-1){    //fjerner siste verdi i listen
            peker = hale;
            hale = hale.forrige;
            hale.neste = null;

        }

       else{
                                                             //tilfelle 4
            peker = finnNode(indeks);       //fjerner verdi som er midt i lista, verken først, sist eller kun 1 element i lista
            peker.forrige.neste = peker.neste; //forrige peker
            peker.neste.forrige = peker.forrige; //setter opp neste peker
        }

        T verdi = peker.verdi;       //husk å sette pekerne lik null!!
        peker.verdi = null;
        peker.forrige = peker.neste = null;
        antall--;                            // reduserer antallet
        endringer++;
        return verdi;                         // returner fjernet verdi
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

        // Node<T> p = hode;
        //Så lenge den lenkede listen ikke er tom gjør følgende.
        if (!tom())
        {
            //Begynn med noden som hodet peker på, legg verdien inn i strengen

            s.append(hode.verdi);

            //Valgt node endres til neste node i listen
            //p = p.neste;

            //Så lenge noden ikke er null, legg resten av listens elementer til i strengen.

            Node<T> p = hode.neste;
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

    //oppgave 10
    //bruker inpserasjon fra kompendiet Kap1.
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {

        if(liste.antall() > 1){
            int antall_elementer = liste.antall();
            int maksindeks = 0;

            for(int i = antall_elementer; i > 1; i--){

                T maksverdi = liste.hent(0);
                for(int j = 1; j < i; j++){
                    if(c.compare(liste.hent(j),maksverdi)> 0){
                        maksverdi = liste.hent(j);
                        maksindeks = j;
                    }
                }
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


} // class DobbeltLenketListe

