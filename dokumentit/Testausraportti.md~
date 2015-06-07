#Testausraportti

Kannattaako painottomissa verkoissa käyttää painollisissa verkoissa toimivia algoritmeja, kuten Dijkstra tai A* -algortimeja? Painottomassa verkossa tavallinen leveyshaku löytää lyhimmät polut jokaiseen solmuun ajassa O(|V| + |E|). Binaarikeolla Dijkstra ja A* toimivat ajassa O((|V| + |E|)log |V|). On odotettavaa, että leveyshaku toimii nopeammin. Miten Dijkstra ja A* suoriutuisivat Fibonacci-kekoa käyttäen?

Bellman-Ford on tarkoitettu verkoille, joissa on negatiivisia kaaripainoja, onko se käyttökelvoton painottomassa verkossa?

Algoritmeihin on mahdollista tehdä erilaisia optimointeja. Nämä optimoinnit usein parantavat algoritmin suorituskykyä vakiokertoimella, mutta aikavaativuusluokka pysyy samana. Bidirectional - algoritmit aloittavat alkusolmusta ja loppusolmusta ja yhdistävät polut, kun ne kohtaavat. Teoriassa kuluvan ajan pitäisi puolittua. Useista polunhakemisalgoritmeista voi tehdä Bidirectional -tyyppisen.
Vertaamme Bidirectional-leveyshakua tavalliseen leveyshakuun.

Testaamme algoritmien toimintaa painottomissa verkoissa erikokoisilla labyrinteilla. Labyrintissa valkoisiin ruutuihin voi kulkea ja mustiin ei ollenkaan. Aloitamme 100x100 kokoisella labyrintilla. Suurin labyrintti on 4000x4000 pikseliä.

Käytämme testaamisessa Suorituskykytesti-luokan testaaLabyrintti-metodia. Metodi hakee annetun kuvatiedoston ja muodostaa siitä verkon jokaiselle algoritmille. Metodissa haetaan polku vasemmasta yläkulmasta vasempaan alakulmaan. Jokainen algoritmi lopettaa löydettyään polun. Metodi mittaa polunhakemiseen kuluvan ajan ja tulostaa sen. Tämän jälkeen algoritmin löytämä ratkaisu ja solmut kirjoitetaan uuteen kuvatiedostoon, joka sijoitetaan samaan hakemistoon, missä testilabyrintti sijaitsee. Testit ovat toistettavissa milloin vain. Täytyy vain kutsua metodia testikuvan nimellä.

Testit suoritetaan Intel® Core™ i5-3470 CPU @ 3.20GHz × 4 -suorittimella. Käytössä on 7.7GB muistia.



 


