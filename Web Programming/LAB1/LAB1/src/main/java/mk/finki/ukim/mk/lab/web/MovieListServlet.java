package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/index", ""})
public class MovieListServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final MovieService movieService;

    public MovieListServlet(SpringTemplateEngine templateEngine, MovieService movieService) {
        this.templateEngine = templateEngine;
        this.movieService = movieService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Movie> movies = movieService.listAll();

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        context.setVariable("movies", movies);

        this.templateEngine.process("index.html", context, resp.getWriter());
    }

}
