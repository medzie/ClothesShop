import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    private static HashSet<Person> persons = new HashSet<>();
    private static ArrayList<Cloth> womanClothes = new ArrayList<>(); //pododawać ubrania (womanClothes.add(new Cloth(dane ubrania)))
    private static ArrayList<Cloth> manClothes = new ArrayList<>();   //pododawać ubrania
    private static ArrayList<Cloth> kidClothes = new ArrayList<>();   //pododawać ubrania
    private static Client currentUser;

    public static void main(String[] args) {
        config();
        do {
            welcome();                         // panel logowania/rejestracji
            if (currentUser == null)
                continue;                      // powtórzenie pętli

            do {
                /* 2 pętla ponieważ po zalogowaniu do momentu złożenia zamówienia / opuszczenia cały czas przeglądamy sklep
                * będziemy potrzebować wybór czy przeglądamy dalej czy wychodzimy, jak wyjdziemy to opuszczamy pętlę */
                shop(); // wyświetlenie sklepu
            } while (true); // nieskończona pętla

        } while (true);     // nieskończona pętla
        /*
        Client client = new Client("Jadzia","Jagoda","jadzia@wp.pl","jadzia123",1,false); // tworzymy klienta
        Basket basket = new Basket(); // tworze koszyk
        basket.add(clothes.get(0)); // dodanie do koszyka
        basket.add(clothes.get(0));
        client.placeOrder(new Order(basket,OrderStatus.nonPayed)); // realizacja zamówienia klienta
        client.orders();*/
    }

    public static void config() {
        persons.add(
                new Client("Jadzia","Jagoda","jadzia@wp.pl","jadzia123",false)
        );

        womanClothes.add(new Cloth("dresses","skirt",1,ClothColor.red,ClothFabric.cotton,ClothSize.S));
        womanClothes.add(new Cloth("dresses","dress",2,ClothColor.black,ClothFabric.viscose,ClothSize.M));
        womanClothes.add(new Cloth("dresses","summer",3,ClothColor.blue,ClothFabric.spandex,ClothSize.XL));
        womanClothes.add(new Cloth("tops","t-shirt",4,ClothColor.green,ClothFabric.silk,ClothSize.XS));
        womanClothes.add(new Cloth("tops","jumper",5,ClothColor.orange,ClothFabric.wool,ClothSize.XXL));
        womanClothes.add(new Cloth("tops","sweatshirt",6,ClothColor.pink,ClothFabric.ecoLeather,ClothSize.XXS));
        womanClothes.add(new Cloth("outerwear","coat",7,ClothColor.purple,ClothFabric.leather,ClothSize.L));
        womanClothes.add(new Cloth("outerwear","jacket",8,ClothColor.white,ClothFabric.cashmere,ClothSize.M));
        womanClothes.add(new Cloth("outerwear","bomber",9,ClothColor.brown,ClothFabric.viscose,ClothSize.S));
        womanClothes.add(new Cloth("trousers","jeans",10,ClothColor.purple,ClothFabric.leather,ClothSize.L));
        womanClothes.add(new Cloth("trousers","short",11,ClothColor.white,ClothFabric.cashmere,ClothSize.M));
        womanClothes.add(new Cloth("trousers","sweatpants",12,ClothColor.brown,ClothFabric.viscose,ClothSize.S));
        womanClothes.add(new Cloth("underwear","socks",13,ClothColor.black,ClothFabric.cotton,ClothSize.XL));
        womanClothes.add(new Cloth("underwear","panties",14,ClothColor.red,ClothFabric.cashmere,ClothSize.M));
        womanClothes.add(new Cloth("underwear","bra",15,ClothColor.blue,ClothFabric.polyester,ClothSize.XXL));

        manClothes.add(new Cloth("shirts","polo",16,ClothColor.green,ClothFabric.cotton,ClothSize.S));
        manClothes.add(new Cloth("shirts","flannel",17,ClothColor.blue,ClothFabric.cotton,ClothSize.XS));
        manClothes.add(new Cloth("shirts","elegant",18,ClothColor.white,ClothFabric.cotton,ClothSize.L));

    }

    public static void welcome(){
        System.out.println("Welcome in our clothes shop!\n1-Login \n2-Register\n");
        Scanner scanner = new Scanner(System.in);

        int option;
        String login, password, name, surname;

        do{
            option = scanner.nextInt();

            switch(option){
                case 1:
                    System.out.print("Email: ");
                    login = scanner.next();
                    System.out.print("Password: ");
                    password = scanner.next();

                    login(login,password);
                    break;
                case 2:
                    System.out.print("Email: ");
                    login = scanner.next();
                    System.out.print("Create Password: ");
                    password = scanner.next();
                    System.out.print("Your name: ");
                    name = scanner.next();
                    System.out.print("Your surname: ");
                    surname = scanner.next();

                    register(login, password, name, surname);
                    break;
                default:
                    System.out.println("Bad number, try again!");
            }

         } while(option != 1 && option != 2);

    };

    /**
     * logowanie do konta użytkownika
     *
     * @param login    - email użytkownika
     * @param password - hasło konta użytkownika
     * @author Magdalena Woźniak & Yelizaveta Samartseva
     */
    public static void login(String login, String password){

        /*
        użytkownicy:
        jadzia, kaśka
        1 przejście pętli - person = jadzia
        2 przejście pętli - person = kaśka
        */

        for (Person person : persons) { // persons - cała grupa, my chcemy 1 osobę dlatego pętla sprawdzająca każdego po kolei
            if (person.getEmail().equals(login) && person.getPassword().equals(password)){
                System.out.println("Welcome");
                currentUser = (Client) person;
            }
            else if(!person.getEmail().equals(login)) {
                System.out.println("Unknown user\n");
                currentUser = null;
            }
            else if(person.getEmail().equals(login) && !person.getPassword().equals(password)){
                System.out.println("Incorrect password\n");
                currentUser = null;
            }
        }
    }

    /**
     * rejestracja użytkownika
     *
     * @param login    - email użytkownika
     * @param password - hasło konta użytkownika
     * @author Magdalena Woźniak & Yelizaveta Samartseva
     */
    private static void register(String login, String password, String name, String surname){

        for (Person person : persons) {
            if (person.getEmail().equals(login)) {
                System.out.println("Email already used\n\n");
                return;
            }
        }

        /* WALIDACJA DANYCH NAME SURNAME (czy nie są liczbami, haslo czy nie za krotkie */

        Person person = new Person(name, surname, login, password, false);

        persons.add(person);
        currentUser = (Client) person;
        /*
            1 - Wprowadzanie danych (na początku sprawdzamy login czy już taki istnieje w bazie, jeżeli tak wyświetlamy błąd)
            2 - Jeżeli wszystkie dane są dobrze wpisane tworzymy nowego użytkownika i dodajemy do persons (persons.add)
        */
    }

    private static void shop(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ChooseYourFighterin\nWho are you looking for clothes for?\n" + "| 1.Woman | 2.Man | 3.Kid |");
        String gender = scanner.next().toLowerCase();

        if (gender.equals("woman")){
            System.out.println("|  DRESSES   |      TOPS     |  OUTERWEAR  |    TROUSERS   |  UNDERWEAR  |\n" +
                               "|  1.Skirts  |  4.T-shirts   |   7.Coats   |   10.Jeans    |   13.Socks  |\n" +
                               "|  2.Dresses |   5.Jumpers   |  8.Jackets  |   11.Shorts   |  14.Panties |\n" +
                               "|  3.Summer  | 6.Sweatshirts |  9.Bombers  | 12.Sweatpants |   15.Bras   |\n");
        }
        else if(gender.equals("man")){
            System.out.println("|   SHIRTS   |      TOPS     |  OUTERWEAR  |   TROUSERS    |  UNDERWEAR  |\n" +
                               "|  1.Polo    |  4.T-shirts   |   7.Coats   |   10.Jeans    |   13.Socks  |\n" +
                               "|  2.Flannel |   5.Jumpers   |  8.Jackets  |   11.Shorts   |  14.Panties |\n" +
                               "|  3.Elegant | 6.Sweatshirts |  9.Bombers  | 12.Sweatpants |  15.Boxers  |\n");
        }
        else if(gender.equals("kid")) {
            System.out.println("|   CARTOONS   |     TOPS      |  OUTERWEAR  |   TROUSERS    |   UNDERWEAR  |\n" +
                               "| 1.Paw Patrol |  4.T-shirts   |   7.Coats   |   10.Jeans    |   13.Socks   |\n" +
                               "|   2.Frozen   |   5.Jumpers   |  8.Jackets  |   11.Shorts   |  14.Panties  |\n" +
                               "|  3.Peppa Pig | 6.Sweatshirts |  9.Bombers  | 12.Sweatpants | 15.Bodysuits |\n");
        } else {
            return;
        }

        selectCloth(gender);
        System.out.println(currentUser.getBasket());
    };

    private static void selectCloth(String gender) {
        /* wybieranie ubrania po id*/
        Scanner scanner = new Scanner(System.in);
        int clothId = scanner.nextInt() - 1; // -1 bo lista zaczyna się od 0 a wyświetlone produkty od 1

        //switch po płci, potem sprawdzamy czy dany produkt istnieje (np. nr 16 nie istenieje!)
        switch(gender) {
            case "woman":
                if(clothId >= 0 && clothId < womanClothes.size())
                    currentUser.getBasket().add(womanClothes.get(clothId));
                break;
            case "man":
                if(clothId >= 0 && clothId < manClothes.size())
                    currentUser.getBasket().add(manClothes.get(clothId));
                break;
            case "kid":
                if(clothId >= 0 && clothId < kidClothes.size())
                    currentUser.getBasket().add(kidClothes.get(clothId));
                break;
            default:
                System.out.println("only 2 genders exists + kids as a");
        }
    }
}