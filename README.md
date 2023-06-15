#Dokumentacja techniczna
Aplikacja jest serwerem REST API, który umożliwia uzyskiwanie informacji o repozytoriach na podstawie właściciela i nazwy repozytorium. Aplikacja jest napisana w języku Java przy użyciu frameworka Spring Boot.

###Uruchamianie aplikacji
Aplikację można uruchomić za pomocą narzędzia Maven. Upewnij się, że masz zainstalowanego Mavena na swoim systemie. Następnie wykonaj poniższe kroki:

Sklonuj repozytorium aplikacji na swój lokalny komputer.
* Przejdź do katalogu głównego aplikacji.
* Otwórz terminal lub wiersz polecenia w tym katalogu.
* Wykonaj polecenie mvn spring-boot:run.
* Aplikacja zostanie skompilowana i uruchomiona na lokalnym serwerze.
* Po uruchomieniu aplikacji będzie dostępna pod adresem: http://localhost:8080.

##Endpoints

###Pobieranie danych repozytorium
* Endpoint: **GET /repositories/{owner}/{repositoryName}**
* Opis: Endpoint umożliwia pobranie danych repozytorium na podstawie właściciela (owner) i nazwy repozytorium (repositoryName).
* Parametry ścieżki:
  * {owner} - nazwa właściciela repozytorium
  * {repositoryName} - nazwa repozytorium
  * Odpowiedź:
    * **Kod 200 OK** w przypadku powodzenia, zwracając dane repozytorium w formacie JSON.
    * **Kod 400 Bad Request** w przypadku niepoprawnych parametrów.
##Swagger
Aplikacja wykorzystuje Swagger do generowania dokumentacji API. Po uruchomieniu aplikacji, możesz uzyskać dostęp do interaktywnej dokumentacji, korzystając z następującego adresu: http://localhost:8080/swagger-ui.html. Przez Swagger UI będziesz mógł przejrzeć dostępne endpointy, ich opisy, parametry i odpowiadaną strukturę danych. Domyślnie dostęp jest chroniony przez Spring Securyty, a login i hasło znajdują się w pliku application.properties.  

##Wymagania
Do uruchomienia aplikacji wymagane są:
* Java 17 lub nowsza wersja
* Maven 3.x

##Konfiguracja
W przypadku potrzeby dostosowania konfiguracji aplikacji, można edytować plik **application.properties** znajdujący się w folderze src/main/resources. W tym pliku można ustawić parametry takie jak port, baza danych itp.

## Informacje dodatkowe
Dodatkowe informacje dotyczące implementacji aplikacji i wykorzystanych technologii można znaleźć w kodzie źródłowym aplikacji oraz w komentarzach w poszczególnych klasach.