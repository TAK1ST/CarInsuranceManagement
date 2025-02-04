package PresentationLayer.UI;

import static PresentationLayer.UI.CRUD.addInsurance;
import static PresentationLayer.UI.CRUD.addNewCar;
import static PresentationLayer.UI.CRUD.confirmExit;
import static PresentationLayer.UI.CRUD.deleteCar;
import static PresentationLayer.UI.CRUD.findCar;
import static PresentationLayer.UI.CRUD.listInsurances;
import static PresentationLayer.UI.CRUD.reportUninsuredCars;
import static PresentationLayer.UI.CRUD.saveAllData;
import static PresentationLayer.UI.CRUD.updateCar;

public class Menu {

    public static void displayMenu() {
        System.out.println("\nCar Insurance Management System");
        System.out.println("1. Add new car");
        System.out.println("2. Find a car by license plate");
        System.out.println("3. Update car information");
        System.out.println("4. Delete car information");
        System.out.println("5. Add an insurance statement");
        System.out.println("6. List of insurance statements");
        System.out.println("7. Report uninsured cars");
        System.out.println("8. Save data");
        System.out.println("9. Quit");
        System.out.print("Enter your choice: ");
    }

    public static boolean handleChoice(int choice) throws Exception {
        switch (choice) {
            case 1:
                addNewCar();
                break;
            case 2:
                findCar();
                break;
            case 3:
                updateCar();
                break;
            case 4:
                deleteCar();
                break;
            case 5:
                addInsurance();
                break;
            case 6:
                listInsurances();
                break;
            case 7:
                reportUninsuredCars();
                break;
            case 8:
                saveAllData();
                break;
            case 9:
                return confirmExit();
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }
}
