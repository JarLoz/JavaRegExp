Säännöllisten Lauseiden Tulkki
Kimmo Heikkinen
jarloz.kimmo@gmail.com


1. Johdanto

Säännölliset lausekkeet (Engl. Regular Expressions, usein regexp) ovat käytännöllinen tapa kuvata säännöllistä kieltä. Esimerkiksi säännöllinen lauseke "ab|cd" kuvaa kieltä, johon kuuluvat sanat "ab" ja "cd". Säännöllisiä lausekkeita käytetään paljon ohjelmissa, joiden avulla etsitään tekstin joukosta tiettyjä rivejä tai sanoja, esimerkkinä unix-työkalu grep, joka etsii annetusta tekstivirrasta rivejä, jotka sopivat annettuun säännölliseen lausekkeeseen.

Koska säännöllinen lauseke kuvaa säännöllistä kieltä, on luonnollinen tapa tarkistaa jonkin sanan kuuluvuus kyseiseen kieleen on rakentaa lausekkeesta tila-automaatti, ja tarkistaa voidaanko käyttämällä sanaa syötteenä päästä kyseisen automaatin hyväksyvään tilaan. Harjoitustyönäni olen toteuttanut ohjelman, joka luo annetusta säännöllisestä lausekkeesta NFA:n (Non-deterministic Finite Automata, epädeterministinen rajattu tila-automaatti), ja tämän jälkeen tarkistaa hyväksyykö tämä NFA annetun sanan, eli sopiiko annettu sana säännöllisen lausekkeen kuvaamaan kieleen.

2. Käyttöohje:

Ohjelma toimitetaan .tar.gz pakattuna NetBeans-projektina. Ohjelman voi kääntää joko käynnistämällä projektin NetBeans IDE:hen ja valitsemalla run -> Clean And Build Main Project, tai käsin suuntaamalla projektikansion sisältämään src/ kansioon, ja käynnistämällä kääntäjä komennoilla

javac /regexp/*.java
jar -cmf RegexpManifest RegExp.jar regexp/
rm /regexp/*.class

Joka tuottaa src-kansioon RegExp.jar tiedoston ja putsaa ylimääräiset .class tiedostot pois lähdekoodin seasta.

Ohjelmaa käytetään komentoriviltä käynnistämällä .jar paketti, ja antamalla parametreinä ensiksi säännöllinen lauseke, ja sitten
sana, jonka sopivuutta lausekkeeseen testataan:

java -jar RegExp.jar "säännöllinen lauseke" "sana"

Esimerkiksi:

java -jar RegExp.jar (ab|bc) ab

Ohjelma tukee seuraavia säännöllisten lauseiden operaattoreita:

| : "tai"
* : "Nolla tai rajaton määrä"
() : Sulkeet

On huomattavaa, että merkillä "." on erityinen merkitys ohjelman toiminnassa, jossa se merkitsee kahden lausekkeen konkatenaatiota. Niinpä käyttäjän ei tulisi käyttää kyseistä merkkiä säännöllisessä lausekkeessaan, koska se tulkitaan operaattoriksi, ei merkiksi.

Ohjelma tulostaa "It's a match!" jos annettu sana sopii säännölliseen lausekkeeseen, muussa tapauksessa "No match". On huomattavaa, että ohjelma ei tee virheentarkistusta annetulle säännölliselle lausekkeelle, joten epävalidi säännöllinen lauseke johtaa määrittelemättömään toimintaan.


3. Toteutus:

Ohjelman toteutus seuraa hyvin tarkasti Russ Coxin artikkelissa "Regular Expression Matching Can Be Simple And Fast" esittelemää implementaatiota Ken Thompsonin vuonna 1968 esittelemästä NFA-pohjaisesta algoritmista. Artikkeli löytyy osoitteesta http://swtch.com/~rsc/regexp/regexp1.html . Tarkempi selvitys omasta implementaatiostani löytyy lähdekoodin javadoc-dokumentaatiosta, jossa esittelen kuinka olen toteuttanut algoritmin javalla. Javadoc löytyy projektikansion alta dist/javadoc kansiosta.


4. Testitulokset:

Ohjelma on testattu käyttämällä hyväksi JUnit-testejä, joilla testasin algoritmien oikeellisuutta. Kyseiset testit on liitetty lähdekoodin mukaan.

5. Lähteet:

http://swtch.com/~rsc/regexp/regexp1.html
http://en.wikipedia.org/wiki/Reverse_Polish_notation
http://en.wikipedia.org/wiki/Shunting-yard_algorithm
