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
            welcome();
            if (currentUser == null) continue;
            panel();
        } while (true);
    }

    /**
     * panel wyboru
     * @author Magdalena Woźniak
     */
    public static void panel(){

        int option = 0;

        do {
            Scanner scanner = new Scanner(System.in);

            if (!currentUser.admin) {

                System.out.println("1 - realise order\n2 - show offer and add to basket\n3 - remove from basket\n" + "4 - show basket\n5 - exit");
                try{
                    option = scanner.nextInt();
                }
                catch (Exception wordNotNumber) {
                    System.out.println("enter a Number please\n");
                }

                switch (option) {
                    case 1:
                        if (currentUser.basket.productsList.isEmpty()) {
                            System.out.println("Your basket is empty\n");
                        } else {
                            currentUser.placeOrder(new Order(currentUser.basket, OrderStatus.inRealization));

                            for (int i = 0; i < persons.size(); i++) {
                                if (persons.get(i).getEmail().equals(currentUser.getEmail()))
                                    persons.set(i, currentUser); // przypisanie zamowienia do tego logowania chyba działa
                            }
                            System.out.println(currentUser.orders.get(currentUser.orders.size() - 1));
                            System.out.println("Your order is in realization, we will send the details to your e-mail");
                            // w tej linijce usuwaliśmy rzeczy z koszyka, ale wtedy nie działało
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

                            try {
                                clothToDelete = scanner.nextInt() - 1;
                                currentUser.basket.delete(currentUser.basket.productsList.remove(clothToDelete));
                            }
                            catch (Exception wordNotNumber) {
                                System.out.println("enter a Number please\n");
                            }
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
                System.out.println("1 - orders\n2 - edit offer" + "\n3 - sexy naked woman" + "\n4 - size calculator" + "\n5 - exit");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        for (int i = 0; i < persons.size(); i++) {
                            System.out.print((i + 1) + ". ");
                            System.out.println(persons.get(i).getEmail());

                            if (persons.get(i).orders.size() > 0) {
                                persons.get(i).orders();
                            } else {
                                System.out.println("no orders");
                            }
                        }

                        System.out.println("Choose user, order id");
                        int userId = scanner.nextInt() - 1;
                        int orderId = scanner.nextInt() - 1;

                        // walidacja
                        if (userId < persons.size()) {
                            if (orderId < persons.get(userId).orders.size()) {
                                Order order = persons.get(userId).orders.get(orderId);
                                System.out.println("Choose status: ");

                                for (int i = 0; i < OrderStatus.values().length; i++) {
                                    System.out.println((i + 1) + ". " + OrderStatus.values()[i]);
                                }

                                int selected = scanner.nextInt() - 1;

                                if (OrderStatus.values().length > selected) {
                                    order.setStatus(OrderStatus.values()[selected]);
                                    System.out.println("status: " + order.getStatus());
                                } else {
                                    System.out.println("unknown status");
                                }
                            } else {
                                System.out.println("Unknown order");
                            }
                        } else {
                            System.out.println("Unknown user");
                        }
                        break;
                    case 2:
                        System.out.println("Choose gender: (woman, man, kid)");
                        String gender = scanner.next().toLowerCase();
                        int clothId, price;

                        if (gender.equals("woman")) {
                            for (int i = 0; i < womanClothes.size(); i++) {
                                System.out.println((i + 1) + ". " + womanClothes.get(i));
                            }

                            clothId = scanner.nextInt() - 1;

                            if (womanClothes.size() > clothId) {
                                System.out.println("Enter new prize: ");
                                price = scanner.nextInt();
                            }
                            else return;

                            if (price > 0)
                                womanClothes.get(clothId).setPrice(price);

                            System.out.println("New item data: " + womanClothes.get(clothId));
                        }
                        else if (gender.equals("man")) {
                            for (int i = 0; i < manClothes.size(); i++) {
                                System.out.println((i + 1) + ". " + manClothes.get(i));
                            }

                            clothId = scanner.nextInt() - 1;

                            if (manClothes.size() > clothId) {
                                System.out.println("Enter new prize: ");
                                price = scanner.nextInt();
                            }
                            else return;

                            if (price > 0)
                                manClothes.get(clothId).setPrice(price);

                            System.out.println("New item data: " + manClothes.get(clothId));
                        }
                        else if (gender.equals("kid")) {
                            for (int i = 0; i < kidClothes.size(); i++) {
                                System.out.println((i + 1) + ". " + kidClothes.get(i));
                            }

                            clothId = scanner.nextInt() - 1;

                            if (kidClothes.size() > clothId) {
                                System.out.println("Enter new prize: ");
                                price = scanner.nextInt();
                            }
                            else return;

                            if (price > 0)
                                kidClothes.get(clothId).setPrice(price);

                            System.out.println("New item data: " + kidClothes.get(clothId));
                        } else {
                            System.out.println("This gender doesn't exists");
                            return;
                        }
                        break;
                    case 3:
                        System.out.println("                _____    ____\n" +
                                "             .#########.#######..\n" +
                                "          .#######################.\n" +
                                "        .############################.\n" +
                                "       .################################.\n" +
                                "      .#########,##,#####################.\n" +
                                "     .#########-#,'########## ############.\n" +
                                "    .######'#-##' # ##'### #. `####:#######.\n" +
                                "    #####:'# #'###,##' # # +#. `###:':######\n" +
                                "   .####,'###,##  ###  # # #`#. -####`######.\n" +
                                "  .####+.' ,'#  ##' #   # # #`#`.`#####::####\n" +
                                "  ####'    #  '##'  #   #_'# #.## `#######;##\n" +
                                "  #:##'      '       #   # ``..__# `#######:#\n" +
                                "  #:##'  .#######s.   #.  .s######.\\`#####:##\n" +
                                "  #:##   .\"______\"\"'    '\"\"_____\"\". `.#####:#\n" +
                                " .#:##   ><'(##)'> )    ( <'(##)`><   `####;#\n" +
                                " ##:##  , , -==-' '.    .` `-==- . \\  ######'\n" +
                                " #|-'| / /      ,  :    : ,       \\ ` :####:'\n" +
                                " :#  |: '  '   /  .     .  .  `    `  |`####\n" +
                                " #|  :|   /   '  '       `  \\   . ,   :  #:# \n" +
                                " #L. | | ,  /   `.-._ _.-.'   .  \\ \\  : ) J##\n" +
                                "###\\ `  /  '                   \\  : : |  /##\n" +
                                " ## #|.:: '       _    _        ` | | |'####\n" +
                                " #####|\\  |  (.-'.__`-'__.`-.)   :| ' ######\n" +
                                " ######\\:      `-...___..-' '     :: /######\n" +
                                " #######\\``.                   ,'|  /#######\n" +
                                ".# ######\\  \\       ___       / /' /#######\n" +
                                "# #'#####|\\  \\    -     -    /  ,'|### #. #.\n" +
                                "`#  #####| '-.`             ' ,-'  |### #  #\n" +
                                "    #' `#|    '._         ,.-'     #`#`#.\n" +
                                "         |       .'------' :       |#   #\n" +
                                "         |       :         :       |\n" +
                                "         |       :         :       |\n" +
                                "                 :         :            \n" +
                                "            You're really stupid.          ");
                        break;
                    case 4:
                        try {
                            System.out.println("your weight in kg: ");
                            int weight = scanner.nextInt();

                            if(weight <= 50 && weight >= 40){
                                System.out.println("your size is XS\n");
                            }
                            else if(weight <= 55 && weight > 50){
                                System.out.println("your size is S\n");
                            }
                            else if(weight <= 60 && weight > 55){
                                System.out.println("your size is M\n");
                            }
                            else if(weight <= 65 && weight > 60){
                                System.out.println("your size is L\n");
                            }
                            else if(weight <= 80 && weight > 65){
                                System.out.println("your size is XL\n");
                            }
                            else if(weight <= 100 && weight > 80){
                                System.out.println("your size is XXL\n");
                            }
                            else{
                                System.out.println("how are you still alive?\n");
                            }

                        }  catch (Exception wordNotNumber) {
                            System.out.println("enter a Number please");
                        }
                        break;
                    case 5:
                        System.out.println("See you later!\n");
                        break;
                }
            }

        } while (option != 5);
    }

    /**
     * konfiguracja ubrań oraz testowych użytkowników
     * @author Magdalena Woźniak & Yelizaveta Samartsava
     */
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

    /**
     * panel logowania i rejestracji
     * @author Magdalena Woźniak & Yelizaveta Samartsava
     */
    public static void welcome(){
        Scanner scanner = new Scanner(System.in);
        String login, password, name, surname;
        int option;

            try{
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
            }
            catch (Exception wordNotNumber) {
                System.out.println("enter a Number please");
            }

    };

    /**
     * logowanie do konta użytkownika
     * @param login    - email użytkownika
     * @param password - hasło konta użytkownika
     * @author Magdalena Woźniak & Yelizaveta Samartsava
     * @return dane użytkownika o ile taki istnieje w przeciwnym wypadku null
     */
    public static Client login(String login, String password){

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
     * @param login    - email użytkownika
     * @param password - hasło konta użytkownika
     * @param name - imię użytkownika
     * @param surname - nazwisko użytkownika
     * @author Magdalena Woźniak & Yelizaveta Samartsava
     * @return błąd z informacją o istniejącym użytkowniku w przeciwnym wypadku walidacja danych, jeśli poprawna utworzenie konta, jeśli nie błąd
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
        Pattern namePattern = Pattern.compile("\\d+");
        Matcher nameMatcher = namePattern.matcher(name);
        Matcher surnameMatcher = namePattern.matcher(surname);

        if(emailMatcher.find() && password.length() > 3 && !nameMatcher.find() && !surnameMatcher.find()){
            Client person = new Client(name, surname, login, password, false);
            persons.add(person);
            currentUser = person;
            System.out.println("\nYou have successfully created an account! \nWelcome in our clothes shop!\n");
        }
        else{
            System.out.println("\nWrong email or to short password or name/surname have numbers\n");
            return;
        }

    }

    /**
     * wybór ubrań dla płci i przegląd pojedynczych sztuk
     * @author Magdalena Woźniak & Yelizaveta Samartsava
     * @return przypisanie płci do numeru ubrania, dodanie i wyświetlenie produktu w koszyku, w przeciwnym razie błąd o nieistniejącym produkcie
     */
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
        System.out.println(currentUser.basket);
    }

    /**
     * wybór ubrań dla płci po id i przegląd pojedynczych sztuk
     * @author Magdalena Woźniak & Yelizaveta Samartsava
     * @param gender - płeć dla numerów ubrań
     */
    private static void selectCloth(String gender) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Which one you want: ");

        try {
            int clothId = scanner.nextInt() - 1;

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
        catch (Exception wordNotNumber) {
            System.out.println("enter a Number please\n");
        }

    }
}