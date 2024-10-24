package gg.jte.generated.ondemand;
import tr.com.huseyinaydin.entity.Expense;
import java.util.List;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,13,13,16,101,103,103,103,103,103,103,103,103,103,107,107,107,107,107,107,107,107,107,112,112,112,112,112,112,112,112,112,117,117,117,117,117,117,117,117,117,122,122,122,122,122,122,122,122,122,125,125,125,128,133,157,157,159,159,159,160,160,160,161,161,161,162,162,162,163,163,163,165,165,165,165,168,168,168,168,173,173,190,195,195,195,3,4,4,4,4};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Expense expense, List<Expense> expenses) {
		jteOutput.writeContent("\n<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>BudgetBuddy - Expense Tracker</title>\n    <link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">\n    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\"> ");
		jteOutput.writeContent("\n    <style>\n        body {\n            background-color: #f0f4f8; ");
		jteOutput.writeContent("\n            padding: 20px;\n        }\n        h1 {\n            color: #333;\n            text-align: center;\n            margin-bottom: 20px;\n            font-family: 'Arial Black', sans-serif;\n            font-size: 2rem;\n        }\n        .container {\n            max-width: 900px;\n            margin: 0 auto;\n            background-color: #ffffff;\n            padding: 30px;\n            border-radius: 15px;\n            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);\n        }\n        .form-group label {\n            font-weight: bold;\n        }\n        .btn-custom {\n            background-color: #28a745;\n            color: white;\n            transition: background-color 0.3s ease;\n        }\n        .btn-custom:hover {\n            background-color: #218838;\n        }\n        .table-container {\n            background-color: #ffffff;\n            padding: 20px;\n            border-radius: 10px;\n            margin-top: 20px;\n            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.15);\n        }\n        th {\n            background-color: #007bff;\n            color: white;\n            text-align: center;\n        }\n        td {\n            text-align: center;\n            vertical-align: middle;\n        }\n        .actions a {\n            margin: 0 5px;\n            color: #fff;\n        }\n        .actions .edit-icon {\n            color: #ffc107;\n        }\n        .actions .delete-icon {\n            color: #dc3545;\n        }\n        .modal-content {\n            background-color: #f7f9fc;\n        }\n        footer {\n            text-align: center;\n            margin-top: 30px;\n            color: #666;\n        }\n        .view-expenses-header {\n            cursor: pointer;\n            color: #007bff;\n            font-size: 1.5rem;\n            text-align: center;\n            margin: 20px 0;\n            font-weight: bold;\n            transition: color 0.3s;\n        }\n        .view-expenses-header:hover {\n            color: #0056b3;\n            text-decoration: underline;\n        }\n        .eye-icon {\n            margin-right: 10px;\n        }\n    </style>\n</head>\n<body>\n<div class=\"container\">\n    <h1>Harcama Dostu Uygulama</h1>\n\n    ");
		jteOutput.writeContent("\n    <form action=\"/save\" method=\"post\" class=\"bg-light p-4 border rounded shadow-sm\">\n        <input type=\"hidden\" name=\"id\"");
		var __jte_html_attribute_0 = expense.id;
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">\n\n        <div class=\"form-group\">\n            <label for=\"description\">Açıklama</label>\n            <input type=\"text\" class=\"form-control\" id=\"description\" name=\"description\"");
		var __jte_html_attribute_1 = expense.description;
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_1);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" required>\n        </div>\n\n        <div class=\"form-group\">\n            <label for=\"amount\">Miktarı</label>\n            <input type=\"number\" class=\"form-control\" id=\"amount\" name=\"amount\" step=\"0.01\"");
		var __jte_html_attribute_2 = expense.amount;
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_2);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" required>\n        </div>\n\n        <div class=\"form-group\">\n            <label for=\"date\">Tarihi</label>\n            <input type=\"date\" class=\"form-control\" id=\"date\" name=\"date\"");
		var __jte_html_attribute_3 = expense.date;
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_3)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_3);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" required>\n        </div>\n\n        <div class=\"form-group\">\n            <label for=\"category\">Kategorisi</label>\n            <input type=\"text\" class=\"form-control\" id=\"category\" name=\"category\"");
		var __jte_html_attribute_4 = expense.category;
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_4)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_4);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" required>\n        </div>\n\n        <button type=\"submit\" class=\"btn btn-custom btn-block\">");
		jteOutput.setContext("button", null);
		jteOutput.writeUserContent(expense.id == null ? "Harcamayı Kaydet" : "Harcamayı Güncelle");
		jteOutput.writeContent("</button>\n    </form>\n\n    ");
		jteOutput.writeContent("\n    <h2 class=\"view-expenses-header\" data-toggle=\"modal\" data-target=\"#expenseModal\">\n        <i class=\"fas fa-eye eye-icon\"></i> Harcamalarını Gör\n    </h2>\n\n    ");
		jteOutput.writeContent("\n    <div class=\"modal fade\" id=\"expenseModal\" tabindex=\"-1\" aria-labelledby=\"expenseModalLabel\" aria-hidden=\"true\">\n        <div class=\"modal-dialog modal-lg\">\n            <div class=\"modal-content\">\n                <div class=\"modal-header\">\n                    <h5 class=\"modal-title\" id=\"expenseModalLabel\">Harcamaların</h5>\n                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n                        <span aria-hidden=\"true\">&times;</span>\n                    </button>\n                </div>\n                <div class=\"modal-body\">\n                    <div class=\"table-container\">\n                        <table class=\"table table-striped table-hover\">\n                            <thead>\n                            <tr>\n                                <th>Vergi No</th>\n                                <th>Açıklama</th>\n                                <th>Miktar</th>\n                                <th>Tarih</th>\n                                <th>Kategori</th>\n                                <th>İşlemler</th>\n                            </tr>\n                            </thead>\n                            <tbody>\n                            ");
		for (Expense e : expenses) {
			jteOutput.writeContent("\n                                <tr>\n                                    <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(e.id);
			jteOutput.writeContent("</td>\n                                    <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(e.description);
			jteOutput.writeContent("</td>\n                                    <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(e.amount);
			jteOutput.writeContent("</td>\n                                    <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(e.date);
			jteOutput.writeContent("</td>\n                                    <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(e.category);
			jteOutput.writeContent("</td>\n                                    <td class=\"actions\">\n                                        <a class=\"edit-icon\" href=\"/edit/");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(e.id);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\" title=\"Edit\">\n                                            <i class=\"fas fa-edit\"></i>\n                                        </a>\n                                        <a class=\"delete-icon\" href=\"/delete/");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(e.id);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\" onclick=\"return confirm('Silmek istediğinize emin misiniz?')\" title=\"Delete\">\n                                            <i class=\"fas fa-trash-alt\"></i>\n                                        </a>\n                                    </td>\n                                </tr>\n                            ");
		}
		jteOutput.writeContent("\n                            </tbody>\n                        </table>\n                    </div>\n                </div>\n                <div class=\"modal-footer\">\n                    <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Kapat</button>\n                </div>\n            </div>\n        </div>\n    </div>\n\n    <footer>\n        <p>&copy; 2024 Bütçe Dostu. Tüm hakları saklıdır, keyfi hizmete mahsustur kardeşim. Hadi güle güle.</p>\n    </footer>\n</div>\n\n");
		jteOutput.writeContent("\n<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\"></script>\n<script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js\"></script>\n<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Expense expense = (Expense)params.get("expense");
		List<Expense> expenses = (List<Expense>)params.get("expenses");
		render(jteOutput, jteHtmlInterceptor, expense, expenses);
	}
}
