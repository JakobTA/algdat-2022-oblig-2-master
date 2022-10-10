# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Sultan Aslanovitsj Avtajev, S199219, s199219@oslomet.no
* Diba Shishegar, S358980, s358980@oslomet.no
* Jakob Tekin Anderson, S348704, s348704@oslomet.no
* Ali Imran Anjum, S358976, s358976@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Diba har hatt hovedansvar for oppgave 3b, 6, 7.
* Jakob har hatt hovedansvar for oppgave 3a, 4, 5 og 9.
* Ali har hatt hovedansvar for oppgave 8, 10.
* Sultan har hatt hovedansvar for oppgave
* Vi har i fellesskap løst oppgave 1 og 2 / (vi diskuterte og hjalp hverandre på flere av de andre oppgavene også).

# Oppgavebeskrivelse

Oppgave 1 var ganske rett frem. Her fulgte vi fremgangsmåten i kompendiet,
men passet på å ha med pekere til forrige også. Dersom a ikke er null, finn
første element i tabellen som ikke er null. Begynn så fra dette elementet og
legg til node med valgt tabell-verdi. Gjør dette til vi er tomme for
elementer i a. tom() og antall() returnerer bare true om antall == 0 og
returnerer antallet selv.

I oppgave 2a begynner vi i toString()-metoden med å sette opp en ny String s med stringbuilder 
metoden. Legger til en klammeparentes og sjekker om listen er tom eller ikke. Er den tom, 
legges sluttparentesen til og s returneres. Hvis ikke tom, legg første nodes verdi 
inn i s og gå videre til neste node. Gå så igjennom resten, legg til komma mellom verdier, og 
legg til neste verdi. Metoden omvendtString() følger samme mønster, men bruker pekere til forrige 
istedenfor neste, og bygger strengen med innholdet reversjert.
2b begynner med en null-verdi-sjekk og en opprettelse av node med inn-verdien.
I denne metoden skal vi bare legge til noden bakerst og det holder derfor med å 
sjekke om listen er tom eller ikke. Er den tom, sett hale og hode = noden. Er den 
ikke tom, koble nodens pekere til halen og flytt halen. Oppdater antall og endringer.

I oppgave 3a utføres først en indeksKontroll, så sjekkes det om indeksen
ligger i første eller andre halvdel av listen. Avhengig av dette, begynner vi
enten på hode eller hale, og beveger oss innover til vi finner noden med
ønsket indeks. Returner denne.

I oppgave 3b skal vi returnere en liste som inneholder verdiene [fra:til>. Det første som skjer er
å sjekke en fratilKontroller for å sjekke om fra og til er lovlige. fratilKontrolleren er en metode jeg har limt inn fra
tidligere ukesoppgaver jeg har løst, byttet bare ut ArrayOutOfBound til IndexOutOfBoud. 
Videre oppretter jeg en ny liste og setter opp tablengde, om lengden på lista er mindre enn 1 returnerer
vi bare lista tilbake. Ellers oppretter vi en node som starter på fra, og looper gjennom en forløkke, 
altså resten av verdiene i lista. Hvis lengden er større enn 0, legger
vi inn node.verdi i lista, deretter setter vi noden lik noden sin neste, så er det en mindre verdi i tabellen
så begynner vi å loope gjennom forløkken igjen. Til slutt når alle verdiene er loopet gjennom returnerer vi lista.

Oppgave 4 går bare igjennom listen og returnerer indeks til første noden (fra
venstre) som inneholder verdien vi ser etter. Hvis verdi er null, eller den
ikke finnes i noen node, returner -1. Metoden inneholder() bare kaller på
metoden vi akkurat har laget og returnerer true om den finnes, og false om
den ikke finnes. Veldig enkelt og greit.

Oppgave 5 begynner med å sjekke at input er lovlig, deretter oppretter vi den
nye noden med verdien vi har fått. Så sjekker vi alle de fire tilfellene,
setter inn noden på indeksen og ordner pekerene så alt blir rett. Oppdaterer
til slutt antall og endringer. Forsøkte å bruke finnNode() metoden her
tidligere, men fikk det ikke til å bli rett.

Oppgave 6 går ut på å enten fjerne en verdi man oppgir eller fjerne selve indeksen man oppgir.
Metoden for å fjerne verdien man oppgir setter jeg først opp en if hvor verdien vi leter etter er en nullverdi, i det 
tilfellet returnerer vi false. Deretter oppretter jeg en peker til hode. Har en while for hvis pekeren q ikke er null, 
og i det tillfellet verdien vi leter etter allerede er den verdien pekeren q peker på. Hvis dette er tillfellet breaker
vi whilen, og setter pekeren q til å peke på q.neste, for å fjerne verdien. Må også huske å ha tillfellet hvis det veriden
vi leter etter ikke finnes i lista, opprettet da en if q==null return false. Videre har vi 4 tillfeller som kan skje.
Tilfelle 1: lengden til lista er lik 1, så setter vi hode og hale lik 0, slik at verdien ikke blir pekt på.
Tilfelle 2: fjerne første verdien i lista. da setter vi peker lik hode, og at hode til lista blir neste
og hode til forrige er lik null siden det første elemenet i lista peker på null som forrige.
Tilfelle 3: fjerne siste verdien i lista. Gjør akkurat slik jeg gjorde som å fjerne første verdi i lista 
bare nå sette q lik hale, og hale sin neste er null og hale er nå lik hale sin forrige slik at vi dropper verdien vi står 
på.
Tilfelle 4: fjerne en verdi som er midt i lista. Setter da pekeren til å peke på neste verdi for å da ikke peke på verdien
vi vil fjerne, altså pekeren hopper over verdien vi vil fjerne. Akkurat det samme for forrige peker

Så måtte jeg sette pekeren sin verdi lik null og sette neste og forrige pekerene lik null.
Og sette opp at det er en mindre node i lista og det har økt med en endring. Så returnerer vi true for hver av de fire 
tilfellene hvis en av dem har skjedd, hvis ikke returneres false pga enten er verdien vi lette etter null, verdien vi
vil fjerne finnes ikke i lista

Metoden for å fjerne indeksen til verdien vi oppgir starter ved at jeg bruker en indeksKontroller som allerede er gitt
til oss i obligen. Deretter oppretter jeg en peker som peker på hode. Her har vi 4 tilfeller.
Tilfelle 1: Er kun 1 verdi i lista, da setter vi hode og hale lik null for å hoppe over verdien helt
Tilfelle 2: Skal fjerne første indeks, indeks 0, da setter vi hode lik hode sin neste og hode sin forrige lik 0 
akkurat som vi gjorde i metoden for å fjerne verdien
Tilfelle 3: Fjerner siste indeks ved å sette pekeren vår til halen og så sette hale lik hale sin forrige og hale sin neste
lik null (likt som metoden for å fjerne verdi)
Tilfelle 4: Fjerne indeksen i midten, bruker finnNode metoden for å finne hvilken indeks det er snakk om.
Setter da peker lik finnNode(indeks), så setter vi peker sin forrige sin neste lik peker sin neste aka hopper
over indeksen vi vil fjerne. Så peker sin neste sin forrige er lik peker sin forrige, hopper slik også over indeksen.
Så må jeg sette peker sin verdi lik null og peker sin neste og forrige lik null. 
Huske antall-- fordi en mindre node i lista og antall++ fordi det har økt med en endring, så returnerer vi VERDIEN vi 
har fjerna.

Oppgave 7 som er nullstill metoden som skal nullstille lista.
Gikk frem med å opprette en peker som peker på hode og en q som er lik null, altså peker som peker på null.
HVIS p ikke er null setter vi q(peker på null) lik p sin neste slik at den blir null, så setter vi p sin forrige
(som er hode som da ikke har en forrige) 
lik p sin neste lik null slik at pekerene kun peker på null nå. Vi setter p.verdi lik null og p=q fordi de nå begge peker
på null. Må så sette hode=hale=null siden vi ikke har noe hode og hale lengere og antall blir lik 0
og det har nå økt en endring.

I opgpave 8 sjekkes det om hasNext() er true. HasNext() sjekker om neste peker peker til null eller ikke.
Videre var det bare å følge oppgave beskrivelsen i a) som var å retunere verdien fra noden 
etter å ha tilordnet denne til denne.neste. Fikk insperasjon fra programkode 3.2.4 c)
For oppgave b hentet jeg insperiasjon fra kompendiet programkode Programkode 3.3.4 e)
Oppgave c var å bare kopiere skildekode fra DobbeltLenkeListeIteroator() men bare tilordne denne = finnNode(indeks).
Videre i siste oppgave var det også bare å følge oppgave teksten. Det ble brukt indeksKontroll
for å sjekke om indeksen er lovlig. Deretter opprettes det en ny instans it. it.denne blir tilordnet noden gjennom finnNode(indeks)
og til slutt returneres it.

Oppgave 9 minte litt om oppgave 5, bare at vi skulle fjerne en node
istedenfor å sette inn. Sjekker først hva valgt node, eller "denne", er.
Lager en navigerings-node som avhenger av hva "denne" var. Utfører sjekk
for å se om vi har lov til å gå videre med operasjonen. Så er det bare å
gjøre motsatt av oppgave 5. Vi ordner pekere slik at noden som skal fjernes
mister kobling til listen.

I oppgave 10 sjekker jeg først at antall elementer av noder er mer enn 1. Hvis det gjelder;
1. Tilordnes antall_elementer = liste.antall() som oppgaven ber om. 
2. Videre bruker jeg utvalgssortering som inneholder en finnMaks og bytt metoden. 
finnMaks og bytt algoritmen har med metoder fra Liste interface og objekt fra Comparator.
3.Prosessen kjøres helt til for-løkken i utvalgssorteringen stopper når det kun er en element igjen.

Hvis antallet noder er tomt eler null, returners innholdet som tomt eller null. 
Oppgaven ber ikke om den mest effektive sorterings metoden. Utvalgssortering har kompliksitet O(n^2).

