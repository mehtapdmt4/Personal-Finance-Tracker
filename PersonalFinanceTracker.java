import java.util.Scanner;

public class PersonalFinanceTracker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Default values
        String userName = "User1";
        String period = "one month";
        double balance = 0;
        String startDate = "";
        String endDate = "";
        
        int choice = 0;
        
        System.out.println("Welcome to Personal Finance Tracker!");

        while (choice != 5) {
            System.out.println("\n-----------------------------------------");
            System.out.println("Current User: " + userName + " | Balance: " + balance + " TL");
            System.out.println("-----------------------------------------");
            System.out.println("1 - Change Username");
            System.out.println("2 - Set Financial Tracking Period");
            System.out.println("3 - Add Income/Expense Transaction");
            System.out.println("4 - View Financial Summary");
            System.out.println("5 - Exit");
            
            System.out.print("Choose an option [1-5]: ");
            
            // Basic input validation to prevent crashes
            if(input.hasNextInt()){
                choice = input.nextInt();
                input.nextLine(); // Consume newline
            } else {
                input.nextLine(); // Clear invalid input
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }
            
            System.out.println();
            
            switch(choice){
                case 1:
                    System.out.print("Enter the new user name: ");
                    String newName = input.nextLine();
                    if(!newName.isEmpty()){
                        userName = newName;
                        System.out.println("User name successfully changed to: " + userName);
                    } else {
                        System.out.println("Name cannot be empty.");
                    }
                    break;
                
                case 2:
                    System.out.println("Select the tracking period:");
                    System.out.println("W - One Week");
                    System.out.println("M - One Month");
                    System.out.println("S - One Season");
                    System.out.print("Your choice: ");
                    String periodChoice = input.nextLine().toLowerCase();
                    
                    switch (periodChoice) {
                        case "w": period = "one week"; break;
                        case "m": period = "one month"; break;
                        case "s": period = "one season"; break;
                        default: System.out.println("Invalid choice. Tracking period remains as: " + period);
                    }
                    
                    if (!periodChoice.equals("w") && !periodChoice.equals("m") && !periodChoice.equals("s")) {
                         break;
                    }

                    System.out.print("Enter the start date (dd.mm.yyyy): ");
                    startDate = input.nextLine();
                
                    System.out.print("Enter the end date (dd.mm.yyyy): ");
                    endDate = input.nextLine();
                
                    System.out.println("Tracking period set from " + startDate + " to " + endDate);
                    break;
                    
                case 3:                    
                    System.out.print("Is this an Income (I) or Expense (E)? ");
                    String transactionType = input.nextLine().toUpperCase();
                    
                    double amount = 0;

                    if(transactionType.equals("I")){
                        System.out.print("Enter income category (salary, investment): ");
                        String category = input.nextLine().toLowerCase();
                        
                        switch (category){
                            case "salary":
                                if (startDate.length() >= 5) {
                                    String month = startDate.substring(3, 5);
                                    // First 6 months vs Last 6 months salary logic
                                    if (month.equals("01") || month.equals("02") || month.equals("03") ||
                                        month.equals("04") || month.equals("05") || month.equals("06")) {
                                        amount = 75000;
                                    } else {
                                        amount = 77500;
                                    }
                                    balance += amount;
                                    System.out.println("Salary income of " + amount + " TL added.");
                                } else {
                                    System.out.println("Start date is missing or invalid. Please set the period (Option 2) first.");
                                }
                                break;
                                
                            case "investment":
                                // Random investment return between 100 and 8000
                                amount = (int)(Math.random() * (8000 - 100 + 1)) + 100; 
                                balance += amount;
                                System.out.println("Investment income of " + amount + " TL added.");
                                break;  
                                
                            default:
                                System.out.println("Invalid income category.");
                        }
                    } else if (transactionType.equals("E")) {
                        System.out.print("Enter expense category (rent, groceries, electricity, entertainment, gas, transport): ");
                        String category = input.nextLine().toLowerCase();
                        
                        switch (category){
                            case "rent":
                                if (period.equals("one week")) 
                                    System.out.println("Tracking period is one week. Rent is not applied.");
                                else if (period.equals("one season")) {
                                    amount = 40000 * 3;
                                    balance -= amount;
                                    System.out.println("Seasonal rent of " + amount + " TL deducted.");
                                } else {
                                    amount = 40000;
                                    balance -= amount;
                                    System.out.println("Monthly rent of 40000 TL deducted.");
                                }
                                break;
                                
                            case "groceries":
                                if (period.equals("one week")) 
                                    amount = 10000 / 4;
                                else if (period.equals("one season"))
                                    amount = 10000 * 3;
                                else
                                    amount = 10000;
                                
                                balance -= amount;
                                System.out.println("Groceries expense of " + amount + " TL deducted.");
                                break;
                                
                            case "transport":
                                System.out.print("Enter transport expense amount: ");
                                if(input.hasNextDouble()) {
                                    amount = input.nextDouble();
                                    input.nextLine(); 
                                    balance -= amount;
                                    System.out.println("Transport expense of " + amount + " TL deducted.");
                                } else {
                                    input.nextLine();
                                    System.out.println("Invalid amount entered.");
                                }
                                break;
                                
                            case "electricity":
                                amount = 1000;
                                balance -= amount;
                                System.out.println("Electricity bill of 1000 TL deducted.");
                                break;    
 
                            case "entertainment":
                                System.out.print("Enter entertainment expense amount: ");
                                if(input.hasNextDouble()) {
                                    amount = input.nextDouble();
                                    input.nextLine(); 
                                    if (amount <= 9000) {
                                        balance -= amount;
                                        System.out.println("Entertainment expense of " + amount + " TL deducted.");
                                    } else {
                                        System.out.println("Expense exceeds monthly entertainment limit of 9000 TL. Transaction rejected.");
                                    }
                                } else {
                                     input.nextLine();
                                     System.out.println("Invalid amount.");
                                }
                                break;
                                
                            case "gas":
                                if(startDate.length() >= 5) {
                                    String monthGas = startDate.substring(3, 5);
                                    // Summer months have lower gas bills
                                    if (monthGas.equals("06") || monthGas.equals("07") || monthGas.equals("08")) {
                                        amount = 1000;
                                    } else {
                                        amount = 3000;
                                    }
                                    balance -= amount;
                                    System.out.println("Natural gas expense of " + amount + " TL deducted.");
                                } else {
                                    System.out.println("Start date missing. Please set period first.");
                                }
                                break;

                            default:
                                System.out.println("Invalid expense category.");
                        }
                    } else {
                        System.out.println("Invalid transaction type. Please enter 'I' for Income or 'E' for Expense.");
                    }
                    break;
                    
                case 4:
                    System.out.println("\n--- Financial Summary ---");
                    System.out.println("User Name: " + userName);
                    System.out.println("Tracking Period: " + period);
                    if(!startDate.isEmpty()) System.out.println("Dates: " + startDate + " - " + endDate);
                    System.out.println("Current Balance: " + balance + " TL");
                    
                    if(balance < 0) System.out.println("⚠️ Warning: You are in debt!");
                    else System.out.println("✅ Status: Healthy budget.");
                    break;

                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select between 1 and 5.");
            }
        }
        input.close();
    }
}
