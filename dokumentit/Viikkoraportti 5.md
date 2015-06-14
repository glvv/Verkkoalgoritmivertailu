#Viikkoraportti 5

Tein painollisia verkkoja varten testikuvia. Suurensin kuvia monistamalla samaa testikuvaa. Tein näin myös painottomia labyrintteja luodessani. Onko näin luotu testiaineisto yhtä vaikea eri kokoisina? Kenttäni ovat vaikeakulkuisia. Onko syytä kokeilla myös kentillä, joissa on vain vähän esteitä? Tällöin A* ja Dijkstra -algoritmien ero tulisi selvemmin näkyviin.

Aloin toteuttamaan fibonaccikekoa. Seurasin Introduction To Algorithms -kirjan pseudokoodia. Pseudokoodissa oli joitain aukkoja, jotka olivat vaikeita täydentää. Miten käydään läpi linkitetty lista, josta poistetaan samalla alkioita? Mitään solmua ei voi käyttää merkkinä, sillä Fibonaccikeon vakauttamisoperaatiossa minimialkio ei ole tiedossa, sillä se on juuri poistettu. Lisäsin FibonacciKekoSolmuihin boolean-arvon, joka kertoo onko linkitetyn listan alkio käyty läpi vai ei. Vakauttamisoperaation alussa kaikki juurilistan alkiot käydään ensin läpi ja asetetaan läpikäyty = false. Tämä ei muuta operaation aikavaativuusluokkaa, sillä kaikki juurilistan alkiot käydään joka tapauksessa läpi vakauttamisoperaatiossa.

Tämä ratkaisu toimi jossain tilanteissa, mutta kaikissa ei. Sain Fibonacci-kekoni toimimaan siirtämällä viitteen kaikista juurilistan alkioista Lista-luokkaan. Tarvitsin siis Listaa muuhuhunkin kuin kaarien tallettamiseen. Tämän takia minun täytyi tehdä luokasta geneerinen, jossa meni aikaa noin 10 minuuttia. Tietorakenteen toteuttaminen geneerisenä oli yllättävän helppoa. Jatkossa voin varmasti toteuttaa kaikki tietorakenteet geneerisinä. Lista-luokka täytyi myös muuttaa kasvavaksi.

Testasin Fibonacci-keon suorituskykyä. Huomaan, että testeissä näkyy virheitä. Joskus polun hakeminen pienemmästä kuvasta kestää kauemmin. Onko syy esimerkiksi javan roskienkeruussa? Kannattaako joka testikerta suorittaa erillisenä ohjelmana? Miten testaus kannattaa toteuttaa? Onko riittävää, että testit suoritetaan useamman kerrran ja tuloksista otetaan keskiarvo? Lisäsin testikoodiin System.gc -komennot juuri ennen ajanottoa. Toivottavasti tämä vähentää testien epäkohtia. Testaus selvästi nopeutui tämän myötä.

Refaktoroin SuoritusKykyTesti -luokan metodeja. testaaLabyrintti on nyt testaaKentta. Sillä voi testata kaikki testikuvat. testaaKentta- saa nyt PolunetsintäAlgoritmi-rajapinnan toteuttavan olion parametrina. Ratkaisujen piirtämisen voi ottaa päälle ja pois parametrin luoRatkaisu-avulla. Painottomat verkot luodaan mustavalkoisesta kuvasta asettamalla parametriin luoKaaretMustiinRuutuihin = false.

Tein suorituskykytestit labyrinteilla. Miten esitän ne koordinaatistossa? Jos syötteen kooksi asettaa |V| + |E|, niin silloin syötteen koko on jo maksimissaan muutamia kymmeniä miljoonia. Testitulokset ovat kuitenkin korkeintaan noin 2000ms luokkaa. Täytyykö piirtää myös |V|+|E| kuvaaja samaan kuvaan? Täytyykö piirtää (|V| + |E|) log |V| kuvaaja? Jos |V|+|E| -kuvaajan asettaa samaan kuvaan tulosten kanssa, niin tulokset ovat vaakasuorana viivana alareunassa.

Aloin tekemään O-analyysiä toteutetuista algoritmeista ja tietorakenteista. Mielestäni tilavaativuuteen ei lasketa mukaan syötettä. Miksi sitten Bellman-Ford -algoritmin tilavaativuus on lähteiden mukaan O(|V|)? Algoritmihan ainoastaan käy läpi solmuja ja kaaria, tallettamatta niitä mihinkään.














