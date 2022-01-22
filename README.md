# ISA-projekat
Studentski projekat za predmet ISA (2021/2022).

Student 1: RA116/2018 Nikola Kajtes <br/>
Student 2: RA115/2018 Marko Nikolić <br/>
Student 3: RA113/2018 Andrija Bošnjaković <br/>

Aplikacija je rađena u Java Springboot-u (InteliJ okruženje) i Angular-u (Visual Studio Code okruženje). <br/>
Verzija jave je 1.8, a korišćena baza je PostgreSQL. Potrebno je podesiti username na "postgres", a password na "password" u SUBP-u. Napraviti novu bazu pod nazivom "isa". <br/>
Pozicionirajte se u direktorijum resources i otvorite file "application.properties". Proverite da li na liniji 4 (spring.jpa.hibernate.ddl-auto) piše "create-drop". Ukoliko ne piše, Vi ga napišite i onda možete da pokrenete aplikaciju.

Da biste pokrenuli backend aplikaciju u InteliJ-u, potrebno je da importujete direktorijum "app" u okruženje kao već postojeći Maven projekat. Zatim, da se pozicionirate mišem skroz desno gore i da kliknete na padajući meni pod nazivom "Maven". Iz novog menija se izabere opcija "Execute maven goal" i onda u otvoreno text polje ukucate "mvn spring-boot:run" i kad kliknete na tu naredbu pokrenuli ste aplikaciju. Potrebno je sačekati par sekundi dok se ne vidi u konzoli da pise "Started AppApplication in 10.453 seconds". Ako piše da se pokrenula za neki određen broj sekundi znači da je sve u redu. Potom, terminirajte aplikaciju i u onom istom fajlu gde ste pisali "create-drop", sada napišite "update" i otkomentarišite liniju 16 ("spring.datasource.schema=classpath:/data-postgres.sql"). Potrebno je ponovo pokrenuti program na isti način i onda je aplikacija spremna za korišćenje.

Skripta za popunjavanje baze se nalazi u istom direktorijumu kao i "application.properties", pod nazivom "data-postgres.sql". U tom fajlu možete videti sa kojim podacima će Vam se popuniti baza.

Što se tiče Angular aplikacije, potrebno je otvoriti VS Code i zatim otvoriti folder front/FishingBookingApp. U terminalu uneti prvo komandu "npm install", a zatim "npm start" i sačekati dok ne bude pisalo "√ Compiled successfully". Kad se to desi, uđite u Vaš browser i ukucajte http://localhost:4200/ i otvorili ste aplikaciju, te je možete koristiti.
