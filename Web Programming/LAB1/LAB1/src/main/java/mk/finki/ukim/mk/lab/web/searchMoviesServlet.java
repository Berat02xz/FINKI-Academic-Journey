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

@WebServlet(name = "SearchMovies", urlPatterns = {"/searchMovies"})
public class searchMoviesServlet extends HttpServlet {

    private final MovieService movieService;
    private final SpringTemplateEngine templateEngine;

    public searchMoviesServlet(MovieService movieService, SpringTemplateEngine templateEngine) {
        this.movieService = movieService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);

        String searchText = req.getParameter("searchText"); //od html forma ke zeme searchText
        int minRating = Integer.parseInt(req.getParameter("minRating")); //od html forma e zeme minRating

        List<Movie> movies = movieService.searchMovies(searchText,minRating); //pravi lista od filmovi ednakvo na search i rating

        context.setVariable("movies", movies); //mu kazuva na thymeleaf vo index.html deka ${movies} e movies.

        this.templateEngine.process("index.html", context, resp.getWriter()); //prakja na thymeleaf so redirekcija na index (istoto mesto)
    }
}
