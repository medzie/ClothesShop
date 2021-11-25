public class Cloth {

    String category, name;
    int clothId;
    ClothColor color;
    ClothFabric fabric;
    ClothSize size;

    public Cloth(String category, String name, int clothId, ClothColor color, ClothFabric fabric, ClothSize size) {
        this.category = category;
        this.name = name;
        this.clothId = clothId;
        this.color = color;
        this.fabric = fabric;
        this.size = size;
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

    @Override
    public String toString() {
        return "\nCloth describe: " + " category - " + category +
                ", name - " + name + ", color - " + color +
                ", fabric - " + fabric + ", size - " + size;
    }
}