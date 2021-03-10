import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;

/* Servlet that returns JSON  with messages retrieved from datastore. */
@WebServlet("/get-messages")
public class GetMessagesServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    //queries the messages form datastore
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    Query<Entity> query =
    Query.newEntityQueryBuilder()
      .setKind("random-message")
      .setOrderBy(OrderBy.desc("time"))
      .build();
    QueryResults<Entity> results = datastore.run(query);
    
    //creates a list to store Message objects
    ArrayList<Message> messages = new ArrayList<Message>();
    while (results.hasNext()) {
      Entity entity = results.next();

      //creates Message object and inserts it into the list
      long id = entity.getKey().getId();
      String name = entity.getString("name");
      String message = entity.getString("message");
      long timestamp = entity.getLong("time");
      messages.add(new Message(id, name, message, timestamp));
    }

    //converts to JSON
    Gson gson = new Gson();
    String json = gson.toJson(messages);

    //returns JSON
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }
}