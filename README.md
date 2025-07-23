# 📌 SmartTasker — Java Console Assistant

**SmartTasker** is a Java console application that combines task management, financial accounting and reminders. The project is divided into three modules with isolated logic and data storage in `.txt` files.

---

## 🚀 Features

### ✅ Tasks module (`Tasks`)
- Creating and deleting tasks
- Displaying active tasks
- Storage file: `task.txt`

### 💰 Income and expenses (`expensesAndIncome`)
- Financial accounting (income/expenses)
- Files: `income.txt`, `expenses.txt`

### ⏰ Reminders (`reminder`)
- Adding reminders (console format)
- Extensible architecture

---

## 🧱 Project architecture
```
src/
├── Tasks/ # Task logic
├── expensesAndIncome/ # Budget accounting
├── reminder/ # Reminders
├── system/ # Storage files
└── Main.java # Entry point
```

Each module includes:
- `UI` — user interaction
- `Service` — business logic
- `Repository` — working with files
- `Manager` — component coordination

---

## ⚙️ Technologies

- Java 17+
- Collections (`ArrayList`, `HashMap`)
- Working with files (`BufferedReader`, `BufferedWriter`)
- Exceptions and data validation

---

## ▶️ How to run

```bash
javac Main.java
java Main
```
Make sure that JDK 17 or higher is installed.
the system folder may not exist, as well as storage files, everything will be created automatically
