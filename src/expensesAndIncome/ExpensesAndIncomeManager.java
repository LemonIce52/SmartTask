package expensesAndIncome;

import java.util.Scanner;

public class ExpensesAndIncomeManager {

    public void startUI(Scanner scanner){
        FileExpensesAndIncomeRepository repo = new FileExpensesAndIncomeRepository();
        ExpensesAndIncomeService service = new ExpensesAndIncomeService(repo);
        ExpensesAndIncomeUI ui = new ExpensesAndIncomeUI(service);

        ui.startExpensesAndIncome(scanner);
    }

}
