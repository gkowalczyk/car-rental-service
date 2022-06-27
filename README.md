#                              >>>>>>>>>>>> Car Rental Service <<<<<<<<<<<<


## 1.Cel aplikacji

Cel stworzenia aplikacji to podsumowanie zgromadzonej wiedzy na temat REST API z wykorzystaniem SPRING WEB

## 2.Wykorzystane technologie:

 **Technologie Frontend:** (in progress):
- VADIN
[https://github.com/gkowalczyk/carRentalFront]

**Technologie Backend:**
Restowe API napisane w Springu z wykorzystaniem następujących technologii:
- Spring Boot 
- Hibernate
- REST API
- JDBC
- JUnit5
- Mockito
- Spring Security
- Lombok

## 3.Opis funkcjonalności:

 ### 3.1 Uruchomienie (np IntelliJ IDEA) 

 W celu uruchomienia programu należy wykonać następujące kroki: 

- Sklonuj repozytorium za pomocą komendy : 
`git clone https://github.com/gkowalczyk/car-rental-service.git`
- Utwórz bazę danych na sewerze MySQL
- Dodaj odpowiednie zależności w sekcji *dependencies* pliku konfiguracyjnego .gradle w celu konfiguracji bazy danych MySQL
- Dodaj odpowiednie zależności do pliku application.properties - konfiguracja bazy danych SPRING

### 3.2 Endpointy: 
- Sprawdzenie listy dostępnych samochodów 
- Wyszukiwarka dostępnych samochodów w zakresie czasu z uwzględnieniem klasy samochodu
- Dodawanie samochodu do bazy danych
- Aktuaalizacja parametrów samochodu dla danego nr ID
- Usunięcie samochodu z bazy danych o danym ID
![alt text for image](https://github.com/gkowalczyk/car-rental-service/blob/main/src/main/resources/car-controller.bmp)



