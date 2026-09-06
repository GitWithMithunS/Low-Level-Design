package PracticeQuestions.InventoryManagmentSystem.utility;

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
        return "GR-" + electronicsCounter++;
    }


}