import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static ArrayList<Client> persons = new ArrayList<>();
    private static ArrayList<Cloth> womanClothes = new ArrayList<>();
    private static ArrayList<Cloth> manClothes = new ArrayList<>();
    private static ArrayList<Cloth> kidClothes = new ArrayList<>();
    private static Client currentUser;

    public static void main(String[] args) {
        config();

        do {
            welcome(); // panel logowania/rejestracji
            if (currentUser == null) continue; // bardzo ważne!!! (inaczej niezalogowany wejdzie do sklepu)
            panel();
        } while (true);
    }

    public static void panel(){

        int option = 0;

        do {
            Scanner scanner = new Scanner(System.in);

            if (!currentUser.admin) {

                System.out.println("1 - realise order\n2 - show offer and add to basket\n3 - remove from basket\n" + "4 - show basket\n5 - exit");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        if (currentUser.basket.productsList.isEmpty()) {
                            System.out.println("Your basket is empty\n");
                        } else {
                            currentUser.placeOrder(new Order(currentUser.basket, OrderStatus.inRealization)); // realizacja zamówienia klienta

                            for (int i = 0; i < persons.size(); i++) {
                                if (persons.get(i).getEmail().equals(currentUser.getEmail()))
                                    persons.set(i, currentUser);
                            }
                            System.out.println(currentUser.orders.get(currentUser.orders.size() - 1));
                            System.out.println("Your order is in realization, we will send the details to your e-mail");
                            currentUser.basket.productsList.clear();
                        }
                        break;
                    case 2:
                        shop();
                        break;
                    case 3:
                        int clothToDelete;
                        if (currentUser.basket.productsList.isEmpty()) {
                            System.out.println("Your basket is empty\n");
                        } else {
                            System.out.println("Your basket: " + currentUser.basket + "\nWhat product you want to remove?");
                            clothToDelete = scanner.nextInt() - 1;
                            currentUser.basket.delete(currentUser.basket.productsList.remove(clothToDelete));
                        }
                        break;
                    case 4:
                        if (currentUser.basket.productsList.isEmpty()) {
                            System.out.println("Your basket is empty\n");
                        } else {
                            System.out.println("Your basket: " + currentUser.basket + "\n");
                        }
                        break;
                    case 5:
                        System.out.println("See you later!\n");
                        break;
                }
            }
            else {
                System.out.println("1 - orders\n2 - edit offer" + "\n5 - exit");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        for (int i = 0; i < persons.size(); i++) {
                            if (persons.get(i).orders.size() > 0) {
                                System.out.print((i + 1) + ". ");
                                persons.get(i).orders();
                            }
                        }

                        System.out.println("Choose user, order id");
                        int userId = scanner.nextInt();
                        int orderId = scanner.nextInt();

                        // tutaj walidacja
                        Order order = persons.get(userId).orders.get(orderId);
                        System.out.println("Choose status: ");


                        for (OrderStatus status : OrderStatus.values()) {
                            System.out.println(status);
                        }

                        // sprawdz czy działa
                        // dodać możliwość zmiany statusu zamówienia
                        break;
                    case 2:
                        shop();
                        // dodać możliwość edytowania ubrań
                        break;
                    case 3:
                        // dodac cos zeby bylo 5 case bo while jest do 5 XD
                        break;
                    case 4:
                        // dodac cos zeby bylo 5 case bo while jest do 5 XD
                        break;
                    case 5:
                        System.out.println("See you later!\n");
                        break;
                }
            }

        } while (option != 5);
    }

    public static void config() {
        persons.add(new Client("Jadzia","Styrta","jadzia@client.pl","123",false));
        persons.add(new Client("Jadzia","Styrta","jadzia@admin.pl","123",true));

        womanClothes.add(new Cloth("dresses","skirt",1,12,ClothColor.red,ClothFabric.cotton,ClothSize.S));
        womanClothes.add(new Cloth("dresses","dress",2,10,ClothColor.black,ClothFabric.viscose,ClothSize.M));
        womanClothes.add(new Cloth("dresses","summer",3,8,ClothColor.blue,ClothFabric.spandex,ClothSize.XL));
        womanClothes.add(new Cloth("tops","t-shirt",4,14,ClothColor.green,ClothFabric.silk,ClothSize.XS));
        womanClothes.add(new Cloth("tops","jumper",5,20,ClothColor.orange,ClothFabric.wool,ClothSize.XXL));
        womanClothes.add(new Cloth("tops","sweatshirt",6,10,ClothColor.pink,ClothFabric.ecoLeather,ClothSize.XXS));
        womanClothes.add(new Cloth("outerwear","coat",7,12,ClothColor.purple,ClothFabric.leather,ClothSize.L));
        womanClothes.add(new Cloth("outerwear","jacket",8,12,ClothColor.white,ClothFabric.cashmere,ClothSize.M));
        womanClothes.add(new Cloth("outerwear","bomber",9,12,ClothColor.brown,ClothFabric.viscose,ClothSize.S));
        womanClothes.add(new Cloth("trousers","jeans",10,12,ClothColor.purple,ClothFabric.leather,ClothSize.L));
        womanClothes.add(new Cloth("trousers","short",11,12,ClothColor.white,ClothFabric.cashmere,ClothSize.M));
        womanClothes.add(new Cloth("trousers","sweatpants",12,12,ClothColor.brown,ClothFabric.viscose,ClothSize.S));
        womanClothes.add(new Cloth("underwear","socks",13,12,ClothColor.black,ClothFabric.cotton,ClothSize.XL));
        womanClothes.add(new Cloth("underwear","panties",14,12,ClothColor.red,ClothFabric.cashmere,ClothSize.M));
        womanClothes.add(new Cloth("underwear","bra",15,12,ClothColor.blue,ClothFabric.polyester,ClothSize.XXL));

        manClothes.add(new Cloth("shirts","polo",16,12,ClothColor.green,ClothFabric.cotton,ClothSize.S));
        manClothes.add(new Cloth("shirts","flannel",17,12,ClothColor.blue,ClothFabric.cotton,ClothSize.XS));
        manClothes.add(new Cloth("shirts","elegant",18,12,ClothColor.white,ClothFabric.cotton,ClothSize.L));
        manClothes.add(new Cloth("tops","t-shirt", 19,12,ClothColor.blue, ClothFabric.cotton,ClothSize.M));
        manClothes.add(new Cloth("tops","jumper",20,12,ClothColor.brown,ClothFabric.wool,ClothSize.L));
        manClothes.add(new Cloth("tops","sweatshirt",21,12,ClothColor.green,ClothFabric.ecoLeather,ClothSize.M));
        manClothes.add(new Cloth("outerwear","coat",22,12,ClothColor.brown,ClothFabric.leather,ClothSize.XL));
        manClothes.add(new Cloth("outerwear","jacket",23,12,ClothColor.black,ClothFabric.cashmere,ClothSize.L));
        manClothes.add(new Cloth("outerwear","bomber",24,12,ClothColor.blue,ClothFabric.viscose,ClothSize.M));
        manClothes.add(new Cloth("trousers","jeans",25,12,ClothColor.blue,ClothFabric.leather,ClothSize.S));
        manClothes.add(new Cloth("trousers","short",26,12,ClothColor.purple,ClothFabric.cashmere,ClothSize.M));
        manClothes.add(new Cloth("trousers","sweatpants",27,12,ClothColor.yellow,ClothFabric.viscose,ClothSize.S));
        manClothes.add(new Cloth("underwear","socks",28,12,ClothColor.red,ClothFabric.cotton,ClothSize.M));
        manClothes.add(new Cloth("underwear","panties",29,12,ClothColor.white,ClothFabric.cotton,ClothSize.S));
        manClothes.add(new Cloth("underwear","boxers",30,12,ClothColor.purple,ClothFabric.cotton,ClothSize.M));

        kidClothes.add(new Cloth("cartoons","paw patrol",31,12,ClothColor.green,ClothFabric.cotton,ClothSize.XS));
        kidClothes.add(new Cloth("cartoons","frozen",32,12,ClothColor.purple,ClothFabric.cotton,ClothSize.XS));
        kidClothes.add(new Cloth("cartoons","peppa pig",33,12,ClothColor.pink,ClothFabric.cotton,ClothSize.S));
        kidClothes.add(new Cloth("tops","t-shirt",34,12,ClothColor.yellow,ClothFabric.cotton,ClothSize.S));
        kidClothes.add(new Cloth("tops","jumper",35,12,ClothColor.blue,ClothFabric.wool,ClothSize.M));
        kidClothes.add(new Cloth("tops","sweatshirt",36,12,ClothColor.orange,ClothFabric.cashmere,ClothSize.S));
        kidClothes.add(new Cloth("outerwear","coat",37,12,ClothColor.black,ClothFabric.leather,ClothSize.S));
        kidClothes.add(new Cloth("outerwear","jacket",38,12,ClothColor.purple,ClothFabric.cashmere,ClothSize.XS));
        kidClothes.add(new Cloth("outerwear","bomber",39,12,ClothColor.yellow,ClothFabric.viscose,ClothSize.S));
        kidClothes.add(new Cloth("trousers","jeans",40,12,ClothColor.black,ClothFabric.leather,ClothSize.XXS));
        kidClothes.add(new Cloth("trousers","short",41,12,ClothColor.blue,ClothFabric.cashmere,ClothSize.M));
        kidClothes.add(new Cloth("trousers","sweatpants",42,12,ClothColor.orange,ClothFabric.viscose,ClothSize.S));
        kidClothes.add(new Cloth("underwear","socks",43,12,ClothColor.yellow,ClothFabric.cotton,ClothSize.XXS));
        kidClothes.add(new Cloth("underwear","panties",44,12,ClothColor.white,ClothFabric.cotton,ClothSize.S));
        kidClothes.add(new Cloth("underwear","bodysuits",45,12,ClothColor.green,ClothFabric.cotton,ClothSize.XXS));

    }

    public static void welcome(){
        Scanner scanner = new Scanner(System.in);
        String login, password, name, surname;
        int option;

        do{
            System.out.println("Welcome in our clothes shop!\n1-Login \n2-Register");

            option = scanner.nextInt();

            switch(option){
                case 1:
                    System.out.print("Email: ");
                    login = scanner.next();
                    System.out.print("Password: ");
                    password = scanner.next();

                    currentUser = login(login,password);
                    break;
                case 2:
                    System.out.print("Email: ");
                    login = scanner.next();
                    System.out.print("Create Password(min. 4 chars): ");
                    password = scanner.next();
                    System.out.print("Your name: ");
                    name = scanner.next();
                    System.out.print("Your surname: ");
                    surname = scanner.next();

                    register(login, password, name, surname);
                    break;
                default:
                    System.out.println("Bad number, try again!\n");
                    break;
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
    public static Client login(String login, String password){

        /*
        użytkownicy: jadzia, kaśka
        1 przejście pętli - person = jadzia
        2 przejście pętli - person = kaśka
        */
        for (Client person : persons) { // persons - cała grupa, my chcemy 1 osobę dlatego pętla sprawdzająca każdego po kolei
            if (person.getEmail().equals(login) && person.getPassword().equals(password)){
                System.out.println("\nWelcome\n");

                return person;
            }
            else if(person.getEmail().equals(login) && !person.getPassword().equals(password)){
                System.out.println("Incorrect password\n");
                return null;
            }
        }

        System.out.println("\nUnknown user\n");
        return null;
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

        Pattern emailPattern = Pattern.compile("@.");
        Matcher emailMatcher = emailPattern.matcher(login);
        Pattern namePattern = Pattern.compile("\\d+"); // chyba działa
        Matcher nameMatcher = namePattern.matcher(name);
        Matcher surnameMatcher = namePattern.matcher(surname);

        if(emailMatcher.find() && password.length() > 3 && !nameMatcher.find() && !surnameMatcher.find()){ // Jeżeli wszystkie dane są dobrze wpisane tworzymy nowego użytkownika i dodajemy do persons (persons.add)
            Client person = new Client(name, surname, login, password, false);
            persons.add(person);
            currentUser = person;
            System.out.println("\nYou have successfully created an account! \nWelcome in our clothes shop!\n");
        }
        else{
            System.out.println("\nWrong email or to short password or name/surname have numbers\n"); // oddzielic kazdy blad osobno!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            return;
        }

    }

    private static void shop(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Who are you looking for clothes for?\n| Woman | Man | Kid |");
        String gender = scanner.next().toLowerCase();

        if (gender.equals("woman")){
            System.out.println("|  DRESSES   |      TOPS     |  OUTERWEAR  |   TROUSERS    |  UNDERWEAR  |\n" +
                               "|  1.Skirt   |  4.T-shirt    |    7.Coat   |   10.Jeans    |   13.Socks  |\n" +
                               "|  2.Dress   |   5.Jumper    |   8.Jacket  |   11.Short    |  14.Panties |\n" +
                               "|  3.Summer  | 6.Sweatshirt  |   9.Bomber  | 12.Sweatpants |    15.Bra   |\n");
        }
        else if(gender.equals("man")){
            System.out.println("|   SHIRTS   |    TOPS      |  OUTERWEAR  |    TROUSERS   |  UNDERWEAR  |\n" +
                               "|  1.Polo    |  4.T-shirt   |    7.Coat   |   10.Jeans    |   13.Socks  |\n" +
                               "|  2.Flannel |   5.Jumper   |   8.Jacket  |   11.Shorts   |  14.Panties |\n" +
                               "|  3.Elegant | 6.Sweatshirt |   9.Bomber  | 12.Sweatpants |  15.Boxers  |\n");
        }
        else if(gender.equals("kid")) {
            System.out.println("|   CARTOONS   |     TOPS      |  OUTERWEAR  |   TROUSERS    |   UNDERWEAR  |\n" +
                               "| 1.Paw Patrol |   4.T-shirt   |    7.Coat   |   10.Jeans    |   13.Socks   |\n" +
                               "|   2.Frozen   |    5.Jumper   |   8.Jacket  |   11.Shorts   |  14.Panties  |\n" +
                               "|  3.Peppa Pig |  6.Sweatshirt |   9.Bomber  | 12.Sweatpants |  15.Bodysuit |\n");
        } else {
            System.out.println("Bad choice, choose 1 from above options\n");
            return;
        }

        selectCloth(gender);
        System.out.println(currentUser.basket); // pokazuje koszyk po dodaniu do koszyka
    };

    private static void selectCloth(String gender) {
        /* wybieranie ubrania po id*/
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which one you want: ");
        int clothId = scanner.nextInt() - 1; // -1 bo lista zaczyna się od 0 a wyświetlone produkty od 1

        //switch po płci, potem sprawdzamy czy dany produkt istnieje (np. nr 16 nie istenieje!)
        switch(gender) {
            case "woman":
                if(clothId >= 0 && clothId < womanClothes.size()) {
                    System.out.println(womanClothes.get(clothId) + "\nDo you want to add this product to your basket?\n1 - yes\n2 - no");
                    int option = scanner.nextInt();
                    if (option == 1) {
                        currentUser.basket.add(womanClothes.get(clothId));
                    }
                }
                break;
            case "man":
                if(clothId >= 0 && clothId < manClothes.size()) {
                    System.out.println(manClothes.get(clothId) + "\nDo you want to add this product to your basket?\n1 - yes\n2 - no");
                    int option = scanner.nextInt();
                    if (option == 1) {
                        currentUser.basket.add(manClothes.get(clothId));
                    }
                }
                break;
            case "kid":
                if(clothId >= 0 && clothId < kidClothes.size()){
                    System.out.println(kidClothes.get(clothId) + "\nDo you want to add this product to your basket?\n1 - yes\n2 - no");
                    int option = scanner.nextInt();
                    if (option == 1) {
                        currentUser.basket.add(kidClothes.get(clothId));
                }
            }
                break;
            default:
                System.out.println("only 2 genders exists + kids as animals");
        }
    }
}