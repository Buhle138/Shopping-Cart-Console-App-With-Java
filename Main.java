import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static Store store = new Store();
    static Cart cart = new Cart();
    public static void main(String[] args) {

        

        try {
            loadItems("products.txt");
            manageItems();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
    public static void loadItems(String fileName) throws FileNotFoundException {

        FileInputStream fis = new FileInputStream(fileName);

        Scanner scan = new Scanner(fis);

        for(int i = 0; scan.hasNextLine(); i++){
            String line = scan.nextLine();
            String[] items =  line.split(";"); //This means where there is a semi colon split the thing
            for (int j = 0; j < items.length; j++) {
                String[] fields = items[j].split("=");
                store.setItem(i, j, new Item(fields[0], Double.parseDouble(fields[1]))); /*Note fields[0] represents the name. fields[1] represents the price. */
            }
        }
        scan.close();
    }

    public static void manageItems(){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("\n\t******************************JAVA GROCERS******************************\n");
            System.out.println(store);
            System.out.println("options: \n\ta) Add to cart\n\tb) Remove from cart \n\tc) Checkout");
            String response = scan.nextLine();

            switch(response){
                case "a": System.out.print("\nChoose an aisle number between: 1-7:  ");
                int row = scan.hasNextInt() ? scan.nextInt() - 1 : 404; /*Since when java counts from zero it will be whatever the user enters minus one.  */
                System.out.print("Enter the number between 1 - 3: ");
                int column = scan.hasNextInt() ? scan.nextInt() - 1 : 404;
                scan.nextLine();

                if(row == 404 || column == 404){
                    continue;
                }else if (row < 0 || row > 6 || column < 0 || column > 2){ /*Making sure that the app does not crash when the user enters something out of the bounds of the array. */
                    continue; 
                }

                /*Below if you enter the item with the same row (index) and column (index) as the one that's aready inside. The Item item = store.getItem(row, column) will return false.*/

                Item item = store.getItem(row, column);
                if (!(cart.add(item))){
                    System.out.println(item.getName() + " is already in your shopping cart.");
                }else{
                System.out.println(item.getName() + " was added to your shopping cart. ");
                }
                break;


                case "b": 
                if(cart.isEmpty()){
                    continue;
                }
                System.out.print("Enter the item you would like to remove:   "); 
                String name = scan.nextLine();
                cart.remove(name);
                break;
                case "c":
                if(cart.isEmpty()){
                    continue;
                } 
                System.out.println(cart.checkout());
                scan.close();
                return;
                default: continue; /*In the case a user enters something wrong. that is not a, b or c. */
            }
            System.out.println("\n\nSHOPPING CART\n\n " + cart);
            System.out.print("Enter anything to continue: ");
            scan.nextLine();

        }
    }
}
