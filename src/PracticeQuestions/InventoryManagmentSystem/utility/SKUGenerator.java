package PracticeQuestions.InventoryManagmentSystem.utility;

//not a good design practice as it is violeation open cloase princinple, and depenedncy inversion .
//its not even thread-safe as it has static memebers
//better to substitute this with an sku interface.
//this is impossible to unit test currenlty.
public class SKUGenerator {

    private static int clothesCounter = 1;
    private static int electronicsCounter = 1;
    private static int groceryCounter = 1;

    public static String generateClothesSKU() {
        return "CL-" + clothesCounter++;
    }

    public static String generateElectronicsSKU() {
        return "EL-" + electronicsCounter++;
    }

    public static String generateGrocerySKU() {
        return "GR-" + groceryCounter++;
    }

}