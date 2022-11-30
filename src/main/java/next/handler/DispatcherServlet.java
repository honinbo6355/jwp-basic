package next.handler;

import next.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    public static final String PREFIX_REDIRECT = "redirect:/";

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Controller controller = RequestMapping.getController(req.getRequestURI());
            String path = controller.execute(req, resp);

            if (path == null) {
                resp.sendRedirect("/");
            }

            if (path.startsWith(PREFIX_REDIRECT)) {
                resp.sendRedirect(path.substring(PREFIX_REDIRECT.length()-1));
            } else {
                RequestDispatcher rd = req.getRequestDispatcher(path);
                rd.forward(req, resp);
            }
        } catch (Exception e) {
            logger.error("{}", e);
        }
    }
}
