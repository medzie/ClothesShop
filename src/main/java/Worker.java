public class Worker extends Person{

    // zmiana ceny, artykułów, nazw, ilości
    //szyfrowanie haseł

    String position, payment;


    public Worker(String name, String surname, String email, String password, int id, boolean admin) {
        super(name, surname, email, password, id, admin);
        this.payment = payment;
        this.position = position;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

}