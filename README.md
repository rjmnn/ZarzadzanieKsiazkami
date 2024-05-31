# ZarzadzanieKsiazkami\

Spis Treści
1. Opis Projektu
2. Funkcjonalności
3. Instrukcja Obsługi


Opis Projektu:
Projekt "Zarządzanie książkami" to aplikacja umożliwiająca zarządzanie kolekcją książek. Użytkownik może przeszukiwać dostępne książki według gatunku, dodawać nowe pozycje do bazy danych oraz usuwać istniejące wpisy.


Funkcjonalności:
- Wyszukiwanie książek według gatunku
- Dodawanie nowych książek do bazy danych
- Usuwanie istniejących książek z bazy danych


Instrukcja Obsługi:

Instalacja MySQL:

1. Upewnij się, że masz zainstalowany MySQL. Możesz go pobrać i zainstalować z oficjalnej strony MySQL https://dev.mysql.com/downloads/mysql/.
2. Zaloguj się do MySQL.
3. Utwórz bazę danych mydatabase za pomocą poniższej komendy:

CREATE DATABASE mydatabase;

4. W bazie mydatabase utwórz tabelę books i dodaj przykładowe dane - przykłądowy skrypt do utworzenia tabeli znajduje się w repozytorium w folderze resources.

Kod: 

5. Przejdź do strony repozytorium Zarzadzanieksiazkami na GitHubie. Kliknij przycisk Code i skopiuj URL repozytorium (HTTPS). Następnie sklonuj repozytorium na komputerze.

IDE:

6. Upewnij się że masz zainstalowane zintegrowane środowisko programistyczne (IDE). 

Projekt "Zarządzanie książkami" powstał w Eclipse, więc poniższe instrukcje dotyczą Eclipse. 

7. Pobierz i zainstaluj Eclipse IDE for Java Developers https://www.eclipse.org/downloads/
8. Uruchom Eclipse. 
9. Przejdź do zakładki File > Import. Wybierz General > Projects from Folder or Archive i kliknij Next. 
10. W Import source wybierz folder, w którym jest sklonowane repozytorium. Naciśnij Finish.
11. Projekt powinien pojawić się w Workspace po lewej stronie ekranu.  

Uruchomienie Aplikacji:

12. Upewnij się, że masz zainstalowany JDK (Java Development Kit). Jeśli nie, możesz go pobrać ze strony Oracle https://www.oracle.com/java/technologies/downloads/?er=221886
13. Aplikacja korzysta z biblioteki JDBC do komunikacji z bazą danych MySQL. Upewnij się, że masz zainstalowany odpowiedni sterownik MySQL. Sterownik możesz pobrać z https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.31/.
14. Aby dodać sterownik do projektu w Eclipse kliknij prawym przyciskiem np. na folder src. Wybierz Properties > Libraries. Kliknij na Modulepath, następnie wybierz Add External JARs. Wybierz sterownik, naciśnij Open. Następnie naciśnik Apply and Close. Sterownik pojawi się w Referenced Libraries.
15. Kliknij prawym przyciskiem myszy na główną klasę projektu BookApp.java i wybierz Run As > Java Application.

