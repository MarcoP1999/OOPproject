
## Progetto Spring Boot 

Il progetto proposto consente di ricercare file in base al nome, alla data di modifica o al tipo di file (pnj, jpg, jpeg, tiff) ricevendo statistiche riguardo la dimensione. 

Nel nostro caso il Client è Postman che consente, grazie a chiamate di tipo GET , di inserire parametri di ricerca ed ottenere gli elementi desiderati sotto forma di JSON.

Una volta avviato il programma, il servizio sarà disponibile all'indirizzo http://localhost:8080/

I dati in ingresso sono stati prelevati grazie alle API di dropbox 
 - #file-search
 - #files-get_metadata

La prima restituisce un oggetto di tipo JSONObject contenente un JSONArray relativo a più elementi, mentre la seconda restituisce un  JSONObject relativo ad un unico elemento.
Perciò è stato necessario creare un file (https://github.com/MarcoP1999/OOPproject/blob/master/JSONPath.txt)  contenente i path relativi a diversi elementi salvati nell'account Dropbox utilizzato e scritti sotto forma di JSONArray. 
È stato poi generato un ciclo per scorrere il JSONArray e sfruttare i path contenuti per iterare la chiamata a get_metadata la quale restituisce il JSONObject relativo ad ogni elemento del JSONArray; ogni elemento restituito è poi stato inserito all'interno di un JSONArray che sarà successivamente utilizzato per l'elaborazione.
Proprio per questo motivo sono state create due classi distinte per l'elaborazione dei dati, una che presenta i metodi per lavorare con un JSONObject, l'altra con metodi relativi ad un JSONArray.


Per quanto riguarda la ricerca di elementi si possono quindi distinguere ricerche su oggetti restituiti da "search" e ricerche su oggetti restituiti da "get_metadata". 
Perciò la tipologia di JSONObject è diversa in base a quale dato si analizza 
 

**get_metadata:**

 1. ***(GET) search_tipo_dimMeta?corpo="formato"&corpo="operatore"&corpo="dimensione"***
Restituisce il JSON contenente tutti gli elementi che rispettano il formato e la dimensione (passati come parametri). Riporta inoltre dimensione media (geometrica e aritmetica), minima e massima dei file cercati.

 2. ***(GET) search_tipo_dim_altezzalarghezzaMeta?corpo="formato"&corpo="operatore"&corpo="altezza"&corpo="larghezza"&corpo="operatore"&corpo="dimensione"***
Restituisce il JSON contenente tutti gli elementi che rispettano formato, altezza, larghezza e dimensione (passati come parametri).  Riporta inoltre dimensione media (geometrica e aritmetica), minima e massima.



 3. ***(GET) search_dataMeta?corpo="data"&corpo="data"***
Restituisce il JSON contenente tutti gli elementi modificati dal Client in una data compresa tra le due passate come parametri.
 
 5. ***(GET) search_nomeMeta?body="nomefile"***
Restituisce il JSON contenente tutti gli elementi che hanno nome (o parte iniziale di esso) uguale a quello passato come parametro.



**search**

 

 1. ***(GET) search_tipo_dim?corpo="formato"&corpo"operatore"=&corpo="dimensione"***
Restituisce il JSON contenente tutti gli elementi che rispettano il formato e la dimensione (passati come parametri). Riporta inoltre dimensione media (geometrica e aritmetica), minima e massima
dei file cercati.

 3. ***(GET) search_data?corpo="data"&corpo="data"***
Restituisce il JSON contenente tutti gli elementi modificati dal Client in una data compresa tra le due passate come parametri.

 3. ***(GET) search_nome?body="nomefile"***
Restituisce il JSON contenente tutti gli elementi che hanno iniziale del nome (o nome completo) uguale a quello passato come parametro.


Abbiamo deciso di non utilizzare un Model in cui immagazzinare gli elementi più rilevanti, ma di andare a lavorare direttamente con il JSON ricevuto dopo il parsing. 
L'abbiamo fatto per una successiva verifica: dopo aver elaborato i dati, essi sono stati copiati in un file per una seconda rielaborazione, così da verificare l'effettiva possibilità di reinterpretare i dati restituiti dal nostro web service. 

    

> ## **Diagramma Casi D'uso**

![enter image description here](https://github.com/MarcoP1999/OOPproject/blob/master/src/UML/NewModel%20Use%20Case%20Diagram1.jpg)



> ## **Diagramma Classi**

 - **univpm.progetto.controller**
![enter image description here](https://github.com/MarcoP1999/OOPproject/blob/master/src/UML/Controller.png)
 - **univpm.progetto.JSON**
 ![enter image description here](https://github.com/MarcoP1999/OOPproject/blob/master/src/UML/JSON.png)
- **univpm.progetto.FiltrieStatistiche**
- ![enter image description here](https://github.com/MarcoP1999/OOPproject/blob/master/src/UML/Filtri_Statistiche.png)
- 
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTExNjkzNDI3MTgsMTUwMTk2NDg3LDE4Nj
AyMjMxNDMsLTQ0ODYyNjk4NCw2MDIxNTM2NDAsMTMzNDA0MDIz
OCwxMDM5NDAxNzA1LC01Nzk1Njk4MjEsMTU0NzUzOTY5OCwtMT
c0NTYzNzc2NiwyNTk1NDE4MTYsOTAzNTg2MjA1LDE0NTEzMzY0
OTUsMTQ1MTMzNjQ5NSwtMTYxMjQxMzUwNV19
-->