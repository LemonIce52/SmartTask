package expensesAndIncome;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ExpensesAndIncomeRepository {

    ArrayList<ExpensesAndIncome> readExpenses();
    ArrayList<ExpensesAndIncome> readIncome();
    boolean savedExpenses(ArrayList<ExpensesAndIncome> listExpensesAndIncome);
    boolean savedIncome(ArrayList<ExpensesAndIncome> listExpensesAndIncome);
    boolean appendExpenses(double expensesAndIncome, LocalDate date);
    boolean appendIncome(double expensesAndIncome, LocalDate date);

}
