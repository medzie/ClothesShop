import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        //TalkingToClient.Welcome();
        //TalkingToClient.GenderSelection();

        Client client = new Client("Jadzia","Jagoda","jadzia@wp.pl","jadzia123",1,false); // tworzymy klienta
        Basket basket = new Basket(); // tworze koszyk

        ArrayList<Cloth> clothes = new ArrayList<>(Arrays.asList( // lista wszystkich produktów
                new Cloth("dresses","Skirt",1,ClothColor.black,ClothFabric.cotton,ClothSize.M)//,
                //new Cloth()
        ));

        basket.add(clothes.get(0)); // dodanie do koszyka
        basket.add(clothes.get(0));
        client.placeOrder(new Order(basket,OrderStatus.nonPayed)); // realizacja zamówienia klienta

        client.orders();
    }
}