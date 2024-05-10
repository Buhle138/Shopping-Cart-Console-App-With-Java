public class Item{
    private String name;
    private double price;

    public Item(String name, double price){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if(price < 0){
            throw new IllegalArgumentException("the price cannot be less than zero");
        }
        this.name = name;
        this.price = price;
    }
    public Item(Item source){
        this.name = source.name;
        this.price = source.price;
    }


    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        if (price < 0){
            throw new IllegalArgumentException("Price cannot be less than zero. ");
        }
        this.price = price;
    }
    public void setName(String name){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("name cannot be null or blank");
        }
        this.name = name;
    }
    public String toString(){
        return name + ": $" + this.price + "   ";
    }

    //Making sure we don't enter the same object twice on the main class using the equals method for that. 
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if (!(obj instanceof Item)){
            return false;
        }

        Item item = (Item)obj; //making obj an instance of the class Item.
        return this.name.equals(item.name) && this.price == item.price;

    }


}