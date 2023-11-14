package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "OrderConfirmationServlet", urlPatterns = {"/orderConfirmation"})
public class TicketOrderServlet extends HttpServlet {

    private final SpringTemplateEngine templateEngine;  //za thymeleaf

    public TicketOrderServlet(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String selectedMovie = request.getParameter("selectedMovie");
        int numTickets = Integer.parseInt(request.getParameter("numTickets"));

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request, response);
        WebContext context = new WebContext(webExchange); //web context

        // proccess order save in database or repo if needed

        context.setVariable("selectedMovie", selectedMovie);
        context.setVariable("numTickets", numTickets);

        this.templateEngine.process("orderConfirmation.html", context, response.getWriter());

    }
}