import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoanServlet")
public class LoanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get form data
        double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
        double annualInterestRate = Double.parseDouble(request.getParameter("interestRate"));
        int numberOfYears = Integer.parseInt(request.getParameter("numberOfYears"));

        // Create Loan object and calculate payments
        Loan loan = new Loan(annualInterestRate, numberOfYears, loanAmount);
        double monthlyPayment = loan.getMonthlyPayment();
        double totalPayment = loan.getTotalPayment();

        // Set response content type
        response.setContentType("text/html");

        // Display the result
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Loan Payment Result</title></head><body>");
        out.println("<h2>Loan Payment Results</h2>");
        out.println("<p>Loan Amount: $" + loanAmount + "</p>");
        out.println("<p>Annual Interest Rate: " + annualInterestRate + "%</p>");
        out.println("<p>Number of Years: " + numberOfYears + "</p>");
        out.println("<p>Monthly Payment: $" + String.format("%.2f", monthlyPayment) + "</p>");
        out.println("<p>Total Payment: $" + String.format("%.2f", totalPayment) + "</p>");
        out.println("</body></html>");
        out.close();
    }
}