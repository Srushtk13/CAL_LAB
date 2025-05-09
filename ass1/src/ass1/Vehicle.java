package ass1;

import java.sql.*;
import java.util.Scanner;

public class Vehicle {
    private static final String URL = "jdbc:mysql://localhost:3306/vehicel";
    private static final String USER = "root";  // Change if needed
    private static final String PASSWORD = "$ru$hti13";  // Change if needed

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n1. Add Service  2. View Services  3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  

                switch (choice) {
                    case 1:
                        addService(scanner);
                        break;
                    case 2:
                        viewServices();
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addService(Scanner scanner) {
        System.out.print("Enter Owner Name: ");
        String owner = scanner.nextLine();
        System.out.print("Enter Vehicle Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Service Type: ");
        String type = scanner.nextLine();
        System.out.print("Enter Service Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        String query = "INSERT INTO service (owner, model, `type`, `date`) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, owner);
            stmt.setString(2, model);
            stmt.setString(3, type);
            stmt.setString(4, date);
            stmt.executeUpdate();
            System.out.println("Service added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewServices() {
        String query = "SELECT * FROM service";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\nService Records:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Owner: " + rs.getString("owner") +
                        ", Model: " + rs.getString("model") + ", Type: " + rs.getString("type") +
                        ", Date: " + rs.getDate("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
