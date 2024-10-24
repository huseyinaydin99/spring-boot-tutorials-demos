package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.entity.Expense;
import tr.com.huseyinaydin.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Controller
@RequestMapping("/")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Ana Sayfa - GET
    @GetMapping
    public String index(Model model) {
        model.addAttribute("expenses", expenseService.getAllExpenses());
        model.addAttribute("expense", new Expense());
        return "index";
    }

    // Kaydet - güncelle - POST
    @PostMapping("/save")
    public String saveExpense(@ModelAttribute Expense expense) {
        if (expense.getId() != null) {
            // var olan harcamanın güncellenmesi
            Expense existingExpense = expenseService.getExpenseById(expense.getId());
            if (existingExpense != null) {
                existingExpense.setDescription(expense.getDescription());
                existingExpense.setAmount(expense.getAmount());
                existingExpense.setDate(expense.getDate());
                existingExpense.setCategory(expense.getCategory());
                expenseService.saveExpense(existingExpense);
            }
        } else {
            // yeni bir harcamanın kaydı
            expenseService.saveExpense(expense);
        }
        return "redirect:/";
    }

    // Var olan harcamanın güncellemek üzere form üzerinde gösterilmesi - GET
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Expense expense = expenseService.getExpenseById(id);
        if (expense != null) {
            model.addAttribute("expense", expense);  // model'e var olan harcamanın eklenmesi
        } else {
            model.addAttribute("expense", new Expense());  // harcama yoksa boş harcama nesnesi
        }
        model.addAttribute("expenses", expenseService.getAllExpenses());  // tüm harcamalar
        return "index";  // güncelleme formunu taşıyan index sayfası
    }

    // Harcama silme - GET
    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/";
    }
}