import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
    // Get values entered in the form.
    String randomMessage = Jsoup.clean(request.getParameter("random-message"), Whitelist.none());
    String name = Jsoup.clean(request.getParameter("name"), Whitelist.none());

    //saving the current time
    long timestamp = System.currentTimeMillis();

    //Stores the message in datastore
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("random-message");
    FullEntity taskEntity =
      Entity.newBuilder(keyFactory.newKey())
        .set("name", name)
        .set("message", randomMessage)
        .set("time", timestamp)
        .build();
    datastore.put(taskEntity);

    // Logs the sumbitted form.
    System.out.println("["+ timestamp +"] "+ name + " submitted: " + randomMessage);

    //redirects
    response.sendRedirect("/#home");
  }
}