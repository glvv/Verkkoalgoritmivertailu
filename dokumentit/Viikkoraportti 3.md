#Viikkoraportti 3

Aloitin ensimmäiseksi toteuttamaan binäärikekoa. Binäärikeko on toteutukseltaan yksinkertainen, mutta ongelmia aiheutti decrease-key -operaation toteuttaminen. Tietorakenteet ja algoritmit -kurssin pseudokoodin perusteella toteutetussa binäärikeossa decrease-key on O(n) aikainen operaatio eikä aikavaativuus ole vaadittu O(log n). Lineaarinen decrease-key tekee keosta hyödyttömän Dijkstran algoritmissa. Yhtä hyvin voitaisiin solmut pitää ArrayList-oliossa ja hakea lineaarisessa ajassa aina pienin. Tällöin Dijkstran algoritmin aikavaativuus kärsii, aikavaativuudeksi tulee 
O((|E|+|V|)(|V|)).

Decrease-key -operaation lineaarinen aikavaativuus seuraa siitä, että sen parametrina vaaditaan solmun indeksi. Solmuista ei kuitenkaan saa selville niiden indeksiä keossa, se täytyy hakea lineaarisesti. Solmujen indeksi keossa täytyy ilmeisesti tallettaa taulukkoon, jolloin decrease-key -operaation vaatima indeksi saadaan selville vakioajassa. Keon tilavaativuus muuttuu vakiokertoimella, mutta O-luokitus pysyy samana.

Seurasin Tietorakenteet ja algoritmit -kurssin materiaalia ja toteutin keot erityisillä kekosolmuilla. Keossa ei siis pidetä varsinaisia verkon solmuja. Verkon solmuilla on viite niitä vastaavaan kekosolmuun ja toisinpäin. Indeksit keossa on talletettu kekosolmuihin. Nyt hankaluuksia aiheuttaa kekosolmujen indeksien pitäminen ajan tasalla. Keko-operaatiot täytyy toteuttaa siten, että ne muokkaavat myös kekosolmujen indeksejä. Seuraamani pseudokoodi oli maksimikeon pseudokoodia, joten kaikki vertailut tuli kääntää toisinpäin.

Toteuttamani binaarikeko ei toiminut heti. Aloin toteuttamaan binaarikeolle testejä, jotta saisin selville kaikki korjaamista vaativat kohdat. Insert-operaatiossa indeksit eivät päivittyneet oikein. Vika oli testisolmujen arvoissa, ei binäärikeossa. Binaarikeko käsitteli aluksi KekoSolmu-olioita. Toteutettuani keon huomasin, että tämä ei ole järkevää. Refaktoroin koodiani siten, että kaikki metodien parametrit ovat Solmu-olioita. KekoSolmu-olioita käsitellään ainoastaan keon sisällä. Esimerkiksi decrease-key operaatiolle annetaan nyt parametrina ainoastaan solmu.

Halusin Lista-luokasta Iterable rajapinnan toteuttavan, jolloin sen voisi käydä läpi for each -lauseella. Tein Lista-luokasta iterable-rajapinnan toteuttavan. Nyt algoritmeja pystyi refaktoroimaan ja null -tarkistuksen poistamaan. En ole toteuttanut luokasta kasvavaa, sillä sille ei ole ollut tarvetta.

Seuraavaksi suunnitelmissa on toteuttaa fibonacci-keko, sillä sen vakioaikaisten operaatioiden vertaaminen binaarikeon logaritmisiin olisi kiinnostavaa. Fibonacci-keolla on kuitenkin pahamaineisen suuret kertoimet. Kuinka suuri aineiston pitää olla, ennen kuin Fibonacci-keosta on hyötyä? Pitääkö aineistojen olla niin suuria, että Dijkstra ja A* -algoritmit vievät lähtökohtaisesti liikaa aikaa?

Toteutin jonon, sillä suunnitelmissa oli toteuttaa joitain leveyshakuun perustuvia algoritmeja. Toteutus on taulukkopohjainen, eikä se kasva dynaamisesti.


