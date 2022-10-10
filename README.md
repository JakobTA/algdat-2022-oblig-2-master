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
I oppgave 3b ...

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

6...

7...

8...

Oppgave 9 minte litt om oppgave 5, bare at vi skulle fjerne en node
istedenfor å sette inn. Sjekker først hva valgt node, eller "denne", er.
Lager en navigerings-node som avhenger av hva "denne" var. Utfører sjekk
for å se om vi har lov til å gå videre med operasjonen. Så er det bare å
gjøre motsatt av oppgave 5. Vi ordner pekere slik at noden som skal fjernes
mister kobling til listen.

10...

