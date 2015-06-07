##Toteutusdokumentaatio

#Ohjelman rakenne

Ohjelma lukee bmp-muotoisia kuvia ja hakee niissä lyhimpiä polkuja. Oletuksena polut haetaan vasemmasta yläkulmasta vasempaan alakulmaan. Tarkoituksen on vertailla erilaisten algortimien suorituskykyä. Bittikartta-luokan avulla voidaan lukea BMP-kuva char[][] -taulukoksi. Ohjelma tukee tiettyjä värejä, jotka on määritelty Bittikartta-luokassa. Char[][]-taulukosta voidaan luoda verkko Verkko-luokan avulla. Eri värien painot on määritelty Verkko-luokassa. On mahdollista luoda painoton verkko. Tällöin annetaan Bittikartta-luokalle mustavalkoinen kuva ja annetaan Verkko-luokalle parametri seinienLäpiSaaLiikkua = false;

Verkko pitää sisällään kaksiulotteisen taulukon Solmu-olioita. Jokaisella Solmu-olioilla on lista Kaari-olioita. Jokaisella kaarella on kohdesolmu ja paino. Lista, johon kaaret on talletettu on järjestyksessä täyttyvä taulukko.

Verkko voidaan antaa algoritmien metodeille, jotka hakevat ja luovat lyhimmän polun annetusta aloitussolmusta annettuun loppusolmuun. Kun algoritmi on hakenut lyhimmän polun, niin ratkaisu on mahdollista piirtää uuteen tiedostoon Bittikartta-luokan avulla.

Dijkstra ja A* -algoritmit vaativat toimiakseen Minimikeko-rajapinnan toteuttavan keon. Binaarikeko-luokka pitää sisällään Kekosolmu-luokan olioita, jotka sisältävät viitteen algoritmien käyttämiin solmuihin. Kekosolmuihin on myös talletettu niiden indeksi binaarikeon sisäisessä taulukossa.

BFS ja Bidirectional -algoritmit käyttävät aputietorakenteena jonoa. Jono-luokka on taulukkopohjainen automaattisesti kasvava jono, joka pitää sisällään Solmu-luokan olioita.

Algoritmien vertailu on mahdollista Suorituskykytesti-luokan metodeilla. testaaLabyrintti-metodi vertailee algoritmien suoritusta painottomissa verkoissa. Painottomia verkkoja kuvaavat testikuvat ovat labyrintteja. Metodi hakee lyhimmän polun eri algoritmeilla kuvan vasemmasta yläkulmasta kuvan oikeaan alakulmaan. Metodi mittaa suoritukseen kulunutta aikaa.

#Saavutetut tila- ja aikavaativuudet

