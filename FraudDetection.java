import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FraudDetection {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\dell\\Desktop\\transactions.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;

            while ((line = br.readLine()) != null) {
                if (row == 0) {
                    row++;
                    continue;
                }

                String[] data = line.split(",");
                String transactionID = data[0];
                String supplier = data[1];
                int amount = Integer.parseInt(data[2]);
                String location = data[3];
                String time = data[4];
                String product = data[5];
                int deliveryDays = Integer.parseInt(data[6]);

                boolean isFraud = amount > 20000;

                if (location.equalsIgnoreCase("unknown")) isFraud = true;
                String[] timeParts = time.split(":");
                int hour = Integer.parseInt(timeParts[0]);
                if (hour >= 0 && hour < 4) isFraud = true;
                if (deliveryDays > 10) isFraud = true;
                if (supplier.toLowerCase().contains("fake")) isFraud = true;

                if (isFraud) {
                    System.out.println("⚠️ Fraud Detected in Transaction ID: " + transactionID);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

