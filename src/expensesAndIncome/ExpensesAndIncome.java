package expensesAndIncome;

import java.time.LocalDate;

public class ExpensesAndIncome{

    private final LocalDate _date;
    private double _expensesOrIncome;

    public ExpensesAndIncome(LocalDate date, Double expensesOrIncome) {
        _date = date;
        _expensesOrIncome = expensesOrIncome;
    }

    public LocalDate date(){
        return _date;
    }

    public double getExpensesOrIncome(){
        return _expensesOrIncome;
    }

    public void setExpensesOrIncome(double expensesOrIncome){
        if(expensesOrIncome > 0)
            _expensesOrIncome += expensesOrIncome;
    }

    @Override
    public String toString(){
        return _date + ":" + _expensesOrIncome;
    }
}
