package expensesAndIncome;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileExpensesAndIncomeRepository implements ExpensesAndIncomeRepository {

    private final String _filePathExpenses = "system/expenses.txt";
    private final String _filePathIncome = "system/income.txt";
    private final Logger _logger = Logger.getLogger(FileExpensesAndIncomeRepository.class.getName());

    @Override
    public ArrayList<ExpensesAndIncome> readExpenses() {
        return readFile(_filePathExpenses);
    }

    @Override
    public ArrayList<ExpensesAndIncome> readIncome() {
        return readFile(_filePathIncome);
    }

    @Override
    public boolean savedExpenses(ArrayList<ExpensesAndIncome> expensesAndIncomeList) {
        return savedFile(expensesAndIncomeList, _filePathExpenses);
    }

    @Override
    public boolean savedIncome(ArrayList<ExpensesAndIncome> expensesAndIncomeList) {
        return savedFile(expensesAndIncomeList, _filePathIncome);
    }

    @Override
    public boolean appendExpenses(double expenses, LocalDate date) {
        return appendFile(expenses, date, _filePathExpenses);
    }

    @Override
    public boolean appendIncome(double income, LocalDate date) {
        return appendFile(income, date, _filePathIncome);
    }

    private ArrayList<ExpensesAndIncome> readFile(String filePath) {

        ArrayList<ExpensesAndIncome> expensesAndIncomeArrayList = new ArrayList<>();

        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitLine = line.split(":", 2);

                if (splitLine.length < 2) continue;

                try {
                    LocalDate date = LocalDate.parse(splitLine[0]);
                    double expenses = Double.parseDouble(splitLine[1]);

                    expensesAndIncomeArrayList.add(new ExpensesAndIncome(date, expenses));
                } catch (DateTimeParseException e) {
                    _logger.log(Level.SEVERE, "Error parse date", e);
                    continue;
                } catch (NumberFormatException e) {
                    _logger.log(Level.SEVERE, "Error parse expenses(double)", e);
                    continue;
                }
            }

        } catch (FileNotFoundException e) {
            _logger.log(Level.SEVERE, "File not found", e);
        } catch (IOException e) {
            _logger.log(Level.SEVERE, "Unknown error reading file", e);
        }

        return expensesAndIncomeArrayList;
    }

    private boolean appendFile(double expensesOrIncome, LocalDate date, String filePath) {

        try (FileWriter file = new FileWriter(filePath, true)) {

            ArrayList<ExpensesAndIncome> expensesAndIncomeArrayList = readFile(filePath);
            for (ExpensesAndIncome expensesAndIncome : expensesAndIncomeArrayList) {
                if (expensesAndIncome.date().equals(date)) {
                    expensesAndIncome.setExpensesOrIncome(expensesOrIncome);
                }
            }

            if (expensesAndIncomeArrayList.isEmpty()) {
                file.write(date + ":" + expensesOrIncome);
                return true;
            } else
                return savedFile(expensesAndIncomeArrayList, filePath);

        } catch (FileNotFoundException e) {
            _logger.log(Level.SEVERE, "File not found", e);
        } catch (IOException e) {
            _logger.log(Level.SEVERE, "Unknown error writing file", e);
        }

        return false;
    }

    private boolean savedFile(ArrayList<ExpensesAndIncome> expensesAndIncomeArrayList, String filePath) {
        try (FileWriter file = new FileWriter(filePath)) {
            for (ExpensesAndIncome expensesAndIncome : expensesAndIncomeArrayList) {
                file.write(expensesAndIncome.toString() + "\n");
            }

            return true;
        } catch (FileNotFoundException e) {
            _logger.log(Level.SEVERE, "File not found", e);
        } catch (IOException e) {
            _logger.log(Level.SEVERE, "Unknown error saved file", e);
        }

        return false;
    }
}
