import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get values entered in the form.
    String randomMessage = request.getParameter("random-message");
    String name = request.getParameter("name");

    // Logs the sumbitted form.
    System.out.println(name + " submitted: " + randomMessage);

    response.sendRedirect("/#home");
  }
}