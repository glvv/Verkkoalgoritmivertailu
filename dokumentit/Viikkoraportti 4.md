#Viikkoraportti 4

Halusin toteuttaa lisää verkkoalgoritmeja. Painotetussa verkossa toimivia hakualgoritmeja on sen verran vähän, että päätin toteuttaa kaksi painottomassa verkossa toimivaa hakualgoritmia. Valitsin BFS ja Bidirectional -algoritmit.

On syytä kokeilla, miten painotetuille verkoille sopivat algoritmit toimivat painottomassa verkossa. Käsitän, että Dijkstra toimii aivan samoin kuin tavallinen leveyshaku painottomassa verkossa.

Toteuttamastani Verkko-luokasta saa painottomia verkkoja kuvaavan pienillä muutoksilla. Seiniin johtavia kaaria ei pidä luoda, jotta ongelma säilyy mielekkäänä. 

Toteutin ensimmäisenä tavallisen leveyshaun. Testasin sen toimintaa ja se toimii odotetusti. Tämän jälkeen aloin toteuttamaan Bidirectional-algoritmia. Birectional algoritmini suorittaa kaksi BFS hakua. Toinen BFS-haku alkaa aloitussolmusta ja toinen loppusolmusta. Birectional-algoritmin pitäisi olla kaksi kertaa tehokkaampi aikavaativuudeltaan kuin tavallinen BFS. Bidirectional-algoritmia toteuttaessani en seurannut tarkkaa pseudokoodia vaan toteutin algoritmin sen kuvauksen perusteella. Kun algoritmin kaksi hakualuetta kohtaavat, täytyy lopusta alkava polku kääntää ja polut yhdistää.

Bidirectional algoritmissa täytyy pitää kirjaa toisen polun läpikäydyistä solmuista. En halua toteuttaa hajautustaulua, sillä en halua toteuttaa uudelleenhajautusta. Koska jokaisella solmulla on x- ja y-koordinaatti, voidaan käyttää pelkkää kaksiulotteista boolean-taulukkoa. Hajautustaululle ei edelläänkään ole tarvetta.

Aluksi minulla oli hankaluuksia lopusta alkavan polun kääntämisessä. Etaisyytta ei päivitetty loppusolmuun asti. Olin laittanut viitteet vahingossa väärinpäin.

Aloitin suorituskykyvertailun. Tein ensiksi 300px*300px labyrintin. Labyrintti on painoton verkko, jossa seiniin ei mene kaaria. Mietin, mikä on syötteen koko? Solmujen määrä on 300 * 300. Mikä on kaarten määrä? Pitää ilmeisesti toteuttaa metodi kaarten laskemiseksi. Koska syötteen koko ilmaistaan kahdella parametrilla, miten esitän suorituskyvyn ja syötteen koon koordinaatistossa?

Dijkstra ja A* suoriutuivat ongelmitta. Bellman-Ford-algoritmilla kesti yli kolme minuuttia mikä on ehkä odotettavaa. Ehkä Bellman-Ford ei ole kiinnostava algoritmi, kun se putoaa pelistä jo näin pienissä ongelmissa. BFS ja Bidirectional -algoritmit hajosivat muistin loppumiseen. Ilmeisesti toteuttamani jono kasvattaa itseään tarpeettomasti. Muutin algoritmeja alustamaan jonot siten, että jonoja ei tarvitse kasvattaa. Tämän jälkeen algoritmit toimivat odotetusti. Nopein algoritmi oli leveyssuuntainen läpikäynti. Dijkstran algoritmi oli hitain.

Toteuttamani jono ei toimi. Koska Jono alkaa täyttämään taulukkoa uudelleen alusta asti, kun taulukossa on alussa tilaa, ei kasvattaminen auta tilanpuutteeseen. Muutin jonon toiminnallisuutta, nyt jono ei enää täytä alkioita uudestaan alusta. Kun jonoa kasvatetaan, alun ja lopun välissä olevat solmut sijoitetaan uuden jonon alkuun.

Aina kun suorituskykytestit ajaa uudestaan, saa erilaisia tuloksia. Bidirectional on usein nopein ja Dijkstra hitain. Ero on kuitenkin vain millisekuntien luokkaa. Kokeilin uudestaan Bellman-Fordin algoritmilla ja aikaa kului noin kaksi minuuttia.

Mietin pitäisikö esimerkiksi Dijkstran algoritmia muokata siten, että se lopettaa kun se on saavuttanut maalisolmun. Jos teen visualisointeja algoritmien läpikäymistä solmuista, onko tylsää kun Dijkstran algoritmi käy aina kaikki solmut läpi? Haluan vertailla läpikäytyjen solmujen määrää, joten muokkasin Dijkstran algoritmin lopettamaan, kun se on löytänyt ratkaisun kohdesolmuun.

Kirjoitin metodit läpikäytyjen solmujen ja ratkaisujen piirtämiseksi. Kuvista näkyy, että Bidirectional käy läpi vähiten solmuja. Laskin myös läpikäytyjen solmujen määrän.

Tein painottomien verkkojen testaamista varten labyrintteja. Suurin labyrintti on 4000x4000 pikseliä. Jos Bellman-Fordia ei käytä, niin kaikkien labyrinttien ratkomiseen kaikilla algoritmeilla kuluu aikaa noin 6 minuuttia. Jos haluan ottaa suorituksista keskiarvon, kuinka monta kertaa suoritan testit? Jo kaksitoista testikertaa kestää tunnin. Bellman-Ford on niin hidas, että sen suorituksista ei mitenkään voi ottaa keskiarvoa.

Algoritmien ratkaisut vievät noin 400mb tilaa, ehkä niitä ei ole kaikkia mielekästä generoida.
