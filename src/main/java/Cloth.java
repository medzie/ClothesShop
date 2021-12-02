public class Cloth {

    private String category, name;
    private int clothId,price;
    private ClothColor color;
    private ClothFabric fabric;
    private ClothSize size;

    public Cloth(String category, String name, int clothId, int price, ClothColor color, ClothFabric fabric, ClothSize size) {
        this.category = category;
        this.name = name;
        this.clothId = clothId;
        this.color = color;
        this.fabric = fabric;
        this.size = size;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClothId() {
        return clothId;
    }

    public void setClothId(int clothId) {
        this.clothId = clothId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "category - " + category +
                ", name - " + name + ", color - " + color +
                ", fabric - " + fabric + ", size - " + size + ", price - " + price + "$";
    }
}