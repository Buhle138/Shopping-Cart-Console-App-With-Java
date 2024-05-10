import java.text.DecimalFormat;
import java.util.ArrayList;
public class Cart {

    ArrayList<Item> items;

    public Cart(){ //cart receives not parameters because in the beginning the cart will be empty
        this.items = new ArrayList<Item>();
    }

    /*Getting the copy of an object inside the item array list. That is why it's return type is the item class. */
    public Item getItem(int index){
        return new Item(this.items.get(index));
    }

    public void setItem(int index, Item item){ 
        this.items.set(index, new Item(item));
    }
    //Making sure that we don't enter the same object twice on the car
    public boolean add(Item item){
        if(this.items.contains(item)){
            return false;
        }
        this.items.add(new Item(item));
        return true;
    }
    public void clear(){
        this.items.clear();
    }
    public void remove(String name){
        if(this.items.isEmpty()){
            throw new IllegalStateException("Invalid State");
        }
        this.items.removeIf((item) -> item.getName().equals(name));
    }

    // public void remove(String name){
    //     if(items.isEmpty()){
    //         throw new IllegalStateException("Cannot remove items from an empty cart!");
    //     }
    //     for (int i = 0; i < this.items.size(); i++) {
    //         if(this.items.get(i).getName().equals(name)){
    //             this.items.remove(i);
    //         }
    //     }  
    // }

    public double getSubtotal(){
        return this.items.stream()
            .mapToDouble((item) -> item.getPrice())
            .sum();
    }

    public double getTax(double subtotal){
        DecimalFormat formatter = new DecimalFormat("#.##");
        return Double.parseDouble(formatter.format(subtotal * 0.13));
    }

    public double getTotal(double subtotal, double tax){
        return subtotal + tax;
    }


    public boolean contains(Item item) {
        return this.items.contains(item);
    }

    public String checkout(){

        if(this.items.isEmpty()){
            throw new IllegalStateException("Invalid State");
        }

              return   "\tRECEIPT\n\n" +
        "\tSubtotal: $" + getSubtotal() + "\n" +
        "\tTax: $" + getTax(getSubtotal()) + "\n" +
        "\tTotal: $" + getTotal(getSubtotal(), getTax(getSubtotal())) + "\n";

    }

    // public void remove(String name){
    //     for (int i = 0; i <  this.items.size(); i++) {
    //         if (this.items.get(i).getName().equals(name)){
    //             this.items.remove(i);
    //         }
    //     }
    // }


    // public String checkout(){
    //     if(items.isEmpty()){
    //         throw new IllegalStateException("Cannot checkout an empty cart");
    //     }
    //     double[] measures = new double[3];
    //     for (int i = 0; i < measures.length; i++) {
    //         measures[0] += this.items.get(i).getPrice(); //calculating the subtotoal.
    //     }
    //     measures[1] = measures[0] * 0.13; //Tax
    //     measures[2] = measures[0] + measures[1]; //Total

    //     //calculating total text
    //     return   "\tRECEIPT\n\n" +
    //     "\tSubtotal: $" + measures[0] + "\n" +
    //     "\tTax: $" + measures[1] + "\n" +
    //     "\tTotal: $" + measures[2] + "\n";
    // }

    // public void add(Item item){
    //     this.items.add(new Item(item));
    // }

    public String toString(){
        String temp = "";
       for (int i = 0; i < this.items.size(); i++) {
        temp += this.items.get(i).toString();
       
    }
    
       return temp;
    }

    public boolean isEmpty(){
        return this.items.isEmpty();
    }

   
    
}
