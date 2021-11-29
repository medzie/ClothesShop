import java.util.Scanner;

public interface TalkingToClient {

    static void Welcome(){
        System.out.println("Welcome in our clothes shop!\n" );
/*                "Who are you looking for clothes for?\n" +
                "| Woman | Man | Kid |");*/
        System.out.println("1-Login \n2-Register \n");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch(option){
            case 1:
                System.out.println("Login:");
                System.out.println("Password:");
                
        }

    };

    static void GenderSelection(){
        Scanner scanner = new Scanner(System.in);
        String gender = scanner.nextLine();

        if(gender.toLowerCase().equals("woman")){
              System.out.println("|  DRESSES  |     TOPS    | OUTERWEAR |  TROUSERS  | UNDERWEAR |\n" +
                                 "|  Skirts   |  T-shirts   |   Coats   |   Jeans    |   Socks   |\n" +
                                 "|  Dresses  |   Jumpers   |  Jackets  |   Shorts   |  Panties  |\n" +
                                 "|  Summer   | Sweatshirts |  Bombers  | Sweatpants |   Bras    |\n");
        }
        else if(gender.toLowerCase().equals("man")){
             System.out.println("|  SHIRTS  |     TOPS    | OUTERWEAR |  TROUSERS  | UNDERWEAR |\n" +
                                "|   Polo   |  T-shirts   |   Coats   |   Jeans    |   Socks   |\n" +
                                "|  Flannel |   Jumpers   |  Jackets  |   Shorts   |  Panties  |\n" +
                                "|  Elegant | Sweatshirts |  Bombers  | Sweatpants |           |\n");
        }
        else if(gender.toLowerCase().equals("kid")) {
             System.out.println("|  CARTOONS  |     TOPS    | OUTERWEAR |  TROUSERS  | UNDERWEAR |\n" +
                                "| Paw Patrol |  T-shirts   |   Coats   |   Jeans    |   Socks   |\n" +
                                "|   Frozen   |   Jumpers   |  Jackets  |   Shorts   |  Panties  |\n" +
                                "|  Peppa Pig | Sweatshirts |  Bombers  | Sweatpants | Bodysuits |\n");
        }
    };
}
