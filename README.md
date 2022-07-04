#                              Car Rental Service 


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

![alt text for image](https://github.com/gkowalczyk/car-rental-service/blob/main/src/main/resources/car-controller.bmp)
- Sprawdzenie listy dostępnych samochodów 
- Wyszukiwarka dostępnych samochodów w zakresie czasu z uwzględnieniem klasy samochodu
- Dodawanie samochodu do bazy danych
- Aktuaalizacja parametrów samochodu dla danego nr ID
- Usunięcie samochodu z bazy danych o danym ID

![alt text for image](https://github.com/gkowalczyk/car-rental-service/blob/main/src/main/resources/equipment-controller.png)
- Sprawdzenie listy dostępnych akcesoriów do samochodu
- Usunięcie dodatku wg nr ID
- Aktualizacja dodatku od danym ID
- Dodanie dodatku do bazy danych

![alt text for image](https://github.com/gkowalczyk/car-rental-service/blob/main/src/main/resources/weather-controller.png)
- Sprawdzenie pogody w danym mieście z zewnętrznego API 
- Zwrócenie pogody we wszystkich stacjach z zewnętrznego API

![alt text for image](https://github.com/gkowalczyk/car-rental-service/blob/main/src/main/resources/fuel-station-controller.png)
- Wyszukanie stacji w danym mieście z zewnętrznego API
- Wyszukanie wszystkich stacji z  zewnętrznego API

![alt text for image](https://github.com/gkowalczyk/car-rental-service/blob/main/src/main/resources/rent-controller.png)
- Wyszukanie wszystkich aktualnych wypożyczeń
- Wyszukanie wypożyczenia o danym ID
- Utworzenie nowej karty wypożyczenia dla danego uzytkownika
- Anulowanie wypożyczenia od danym ID
- Aktualizacja wypożyczenia od danym ID
- Wyliczenie całkowitego kosztu wypożyczenia dla danego ID samochodu w zależności od rodzaju wyposażenia 

![alt text for image](https://github.com/gkowalczyk/car-rental-service/blob/main/src/main/resources/trip-controller.png)
- Wyszukanie najlepszego miejsca na wyjazd o sprzyjających warunkach pogodowych
- Wyliczenie ilości potrzebnego paliwa na wyjazd (w trakcie)

![alt text for image](https://github.com/gkowalczyk/car-rental-service/blob/main/src/main/resources/user-controller.png)
- Utworzenie nowego uzytkownika
- Zablokowanie użytkownika
- Logowanie uzytkownika i wygenerowanie App-Token, kkóry powinien być podawany  w nagłówkach (headers)
- Usunięcie uzytkownika o danym ID
- Zwrócenie wszystkich dostępnych użytkowników







