public class Order{

    Basket basket;
    OrderStatus status;

    public Order(Basket basket, OrderStatus status) {
        this.basket = basket;
        this.status = status;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return basket + ", \nstatus: " + status;
    }
}