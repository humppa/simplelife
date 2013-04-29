
Käyttöohje
==========

Käynnistys
----------

Ohjelma on paketoitu tiedostoon SimpleLife.jar, joka löytyy tämän
projektin juurihakemistosta. Ohjelma käynnistyy, kun sen suorittaa
Java-virtuaalikoneella. Joissain järjestelmissä se saattaa onnistua
näin:
```
$ java -jar SimpleLife.jar
```

Käyttöliittymäelementit
-----------------------

![Käyttöliittymä](life.png?raw=true)

 + **Load**-painikkeella ladataan aiemmin tallennettu pelitilanne
   tiedostosta.

 + **Save**-painikkeella tallennetaan sen hetkinen pelitilanne
   tiedostoon.

 + **Delay**-syötekenttä määrittää viipeen jatkuvan simulaation
   päivitysten välissä. Yksikkö on sekunnin sadasosa.

 + **Step**-painike vie simulaatiota yhden aika-askeleen eteenpäin.

 + **Start**-painike aloittaa jatkuvan simulaation. Simulaatio
   pysäytetään samasta painikkeesta.

 + Pelialueella voi **hiiren klikkauksella** herättää henkiin kuolleen
   solun tai tapaa hengissä olevan solun.

 + **Hiiren rullan** avulla pelialuetta voi lähentää tai loitontaa.
