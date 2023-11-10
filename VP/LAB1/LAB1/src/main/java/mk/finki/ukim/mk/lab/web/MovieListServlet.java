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
    private final SpringTemplateEngine templateEngine;  //za thymeleaf
    private final MovieService movieService;

    public MovieListServlet(SpringTemplateEngine templateEngine, MovieService movieService) {
        this.templateEngine = templateEngine;
        this.movieService = movieService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Movie> movies = movieService.listAll();  //inicijaliziranje na movies promenliva so site filmovi vnatre

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange); //web context

        context.setVariable("movies", movies); //mu kazuva na thymeleaf vo index.html deka $movies e movies.

        this.templateEngine.process("index.html", context, resp.getWriter()); //prakja na thymeleaf
    }

}
