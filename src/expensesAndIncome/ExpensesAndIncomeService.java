package expensesAndIncome;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ExpensesAndIncomeService {

    private final FileExpensesAndIncomeRepository _fileExpensesAndIncomeRepository;

    public ExpensesAndIncomeService(FileExpensesAndIncomeRepository fIleExpensesAndIncomeRepository) {
        _fileExpensesAndIncomeRepository = fIleExpensesAndIncomeRepository;
    }

    public HashMap<LocalDate, Double> totalExpenses() {
        ArrayList<ExpensesAndIncome> expensesList = _fileExpensesAndIncomeRepository.readExpenses();
        return total(expensesList);
    }

    public HashMap<LocalDate, Double> totalIncome() {
        ArrayList<ExpensesAndIncome> incomeList = _fileExpensesAndIncomeRepository.readIncome();
        return total(incomeList);
    }

    public boolean appendExpenses(double expenses) {
        return _fileExpensesAndIncomeRepository.appendExpenses(expenses, LocalDate.now());
    }

    public boolean appendIncome(double income) {
        return _fileExpensesAndIncomeRepository.appendIncome(income, LocalDate.now());
    }

    public boolean deleteExpenses(LocalDate date) {
        ArrayList<ExpensesAndIncome> expensesList = _fileExpensesAndIncomeRepository.readExpenses();
        ArrayList<ExpensesAndIncome> newExpensesList = deleteExpensesOrIncome(date, expensesList);
        boolean isNotDate = !expensesList.equals(newExpensesList);

        return isNotDate && _fileExpensesAndIncomeRepository.savedExpenses(newExpensesList);
    }

    public boolean deleteIncome(LocalDate date) {
        ArrayList<ExpensesAndIncome> incomeList = _fileExpensesAndIncomeRepository.readIncome();
        ArrayList<ExpensesAndIncome> newIncomeList = deleteExpensesOrIncome(date, incomeList);
        boolean isNotDate = !incomeList.equals(newIncomeList);

        return isNotDate && _fileExpensesAndIncomeRepository.savedIncome(newIncomeList);
    }

    private ArrayList<ExpensesAndIncome> deleteExpensesOrIncome(LocalDate date,
                                                                ArrayList<ExpensesAndIncome> expensesOrIncomeArrayList) {
        ArrayList<ExpensesAndIncome> newExpensesList = new ArrayList<>();
        for (ExpensesAndIncome expenses : expensesOrIncomeArrayList) {
            if (!expenses.date().equals(date)) {
                newExpensesList.add(expenses);
            }
        }

        return newExpensesList;
    }

    private HashMap<LocalDate, Double> total(ArrayList<ExpensesAndIncome> expensesOrIncomeArrayList) {
        HashMap<LocalDate, Double> totalExpensesOrIncome = new HashMap<>();
        for (ExpensesAndIncome expenses : expensesOrIncomeArrayList) {
            if (totalExpensesOrIncome.containsKey(expenses.date())) {
                double total = totalExpensesOrIncome.get(expenses.date());
                total += expenses.getExpensesOrIncome();
                totalExpensesOrIncome.put(expenses.date(), total);
            } else {
                totalExpensesOrIncome.put(expenses.date(), expenses.getExpensesOrIncome());
            }
        }

        return totalExpensesOrIncome;
    }

}
