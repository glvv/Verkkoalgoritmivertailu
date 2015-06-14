##Toteutusdokumentaatio

#Ohjelman rakenne

Ohjelma lukee bmp-muotoisia kuvia ja hakee niissä lyhimpiä polkuja. Oletuksena polut haetaan vasemmasta yläkulmasta vasempaan alakulmaan. Tarkoituksen on vertailla erilaisten algortimien suorituskykyä. Bittikartta-luokan avulla voidaan lukea BMP-kuva char[][] -taulukoksi. Ohjelma tukee tiettyjä värejä, jotka on määritelty Bittikartta-luokassa. Char[][]-taulukosta voidaan luoda verkko Verkko-luokan avulla. Eri värien painot on määritelty Verkko-luokassa. On mahdollista luoda painoton verkko. Tällöin annetaan Bittikartta-luokalle mustavalkoinen kuva ja annetaan Verkko-luokalle parametri seinienLäpiSaaLiikkua = false;

Verkko pitää sisällään kaksiulotteisen taulukon Solmu-olioita. Jokaisella Solmu-olioilla on lista Kaari-olioita. Jokaisella kaarella on kohdesolmu ja paino. Lista, johon kaaret on talletettu on järjestyksessä täyttyvä taulukko.

Verkko voidaan antaa algoritmien metodeille, jotka hakevat ja luovat lyhimmän polun annetusta aloitussolmusta annettuun loppusolmuun. Kun algoritmi on hakenut lyhimmän polun, niin ratkaisu on mahdollista piirtää uuteen tiedostoon Bittikartta-luokan avulla.

Dijkstra ja A* -algoritmit vaativat toimiakseen Minimikeko-rajapinnan toteuttavan keon. Minimikekona voi käyttää fibonaccikekoa tai binaarikekoa. Binaarikeko-luokka pitää sisällään BinaariKekosolmu-luokan olioita, jotka sisältävät viitteen algoritmien käyttämiin solmuihin. Kekosolmuihin on myös talletettu niiden indeksi binaarikeon sisäisessä taulukossa. Fibonaccikeko tallettaa Solmujen viitteet Fibonaccikekosolmu-olioihin. FibonaccikekoSolmu ja BinaarikekoSolmu toteuttavat kekoSolmu rajapinnan. Jokaisella solmulla on viite kekosolmuun.

BFS ja Bidirectional -algoritmit käyttävät aputietorakenteena jonoa. Jono-luokka on taulukkopohjainen automaattisesti kasvava jono, joka pitää sisällään Solmu-luokan olioita. Lista on automaattisesti kasvava taulukko.

Algoritmien vertailu on mahdollista Suorituskykytesti-luokan metodeilla. testaakentta-metodi vertailee algoritmien suoritusta erilaisissa verkoissa. Metodi hakee lyhimmän polun eri algoritmeilla kuvan vasemmasta yläkulmasta kuvan oikeaan alakulmaan. Metodi mittaa suoritukseen kulunutta aikaa.

#Saavutetut tila- ja aikavaativuudet

Merkitään |V| = solmujen määrä, |E| kaarten määrä.

Dijkstran algoritmi toimii ajassa O((|V| + |E|) log |V|). (1)
Omassa toteutuksessani rivit 39 ja 44 käyvät läpi kaikki solmut ja asettavat ne kekoon. Näiden rivien aikavaativuus on O(|V|) ja tilavaativuus on O(|V|). Rivillä 46 kutsutaan keon delMin -operaatiota, joka toimii Fibonacci- ja binäärikeossa ajassa O(log |V|). Toistolauseessa jokainen solmu haetaan keosta, joten tästä aikavaativuudeksi tulee yhteensä O(|V|log|V|). Jokaisen solmun jokaiselle kaarelle tehdään decreaseKey-operaatio. Binaarikeossa se toimii ajassa O(log |V|). (3) Fibonaccikeossa se on keskimäärin vakioaikainen, mutta pahimmassa tapauksessa logaritminen. (2) Binäärikeolla decreaseKey-operaation kutsumisesta seuraa O(|E| * log |V|) aikavaativuus. Fibonaccikeolla decreaseKeyn kutsumisesta kaarten määrän verran seuraa aikavaativuus O(E * log |V|). Rivillä 45 alkavan toistolauseen aikavaativuus on siis binäärikeolla O(|V|log|V| + |E|log|V|) eli O((|V| + |E|)log |V|). Fibonaccikeolla aikavaativuus on sama O(|V|log|V| + |E|log|V|) eli O((|V| + |E|)log |V|). Saavutettu aikavaativuus on siis sama kuin tavoiteltu. Tilavaativuus on O(|V|) koska aputietorakenteena käytetään kekoa, johon kaikki solmut laitetaan.

A* on muokattu Dijkstran algoritmista.(4) Ainoa ero on heuristiikan käyttö solmuja valitessa. Tästä seuraa se, että myös A* toimii ajassa O((|V| + |E|) log |V|). Loppuetäisyyden arviointi tehdään samalla kun kaikki solmut käydään läpi ja lisätään kekoon. Arviointi on vakioaikainen operaatio, joten se ei aiheuta muutosta aikavaativuuteen. Toteuttamani A*-algoritmin aikavaativuus on siis sama kuin Dijkstran algoritmin aikavaativuus O((|V| + |E|)log |V|). Myös tilavaativuus on sama O(|V|).

Bellman-Ford algoritmin aikavaativuus on O(|V||E|). (5) Toteuttamassani algoritmissa kutsutaan vakioaikaista relax-operaatiota jokaisella kaarelle |V| - 1 kertaa. Aikavaativuus on on siis O((|V| - 1)|E|) eli O(|V||E|).










#Lähteet
1. https://en.wikipedia.org/?title=Dijkstra%27s_algorithm, katsottu 14.6.2015
2. https://en.wikipedia.org/?title=Fibonacci_heap, katsottu 14.6.2015
3. https://en.wikipedia.org/wiki/Binary_heap, katsottu 14.6.2015
4. https://en.wikipedia.org/wiki/A*_search_algorithm
5. https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm


