
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import com.google.gson.Gson;

/** Returns json with url's to the stored memes. **/
@WebServlet("/list-memes")
public class ListMemesServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // List all of the uploaded files.
    String projectId = "fmendoza-sps-spring21";
    String bucketName = "fmendoza-sps-spring21.appspot.com";
    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
    Bucket bucket = storage.get(bucketName);
    Page<Blob> blobs = bucket.list();

    //creates a list of url's    
    ArrayList<String> urls = new ArrayList<String>();
    for (Blob blob : blobs.iterateAll()) {
      urls.add(blob.getMediaLink());
    }
    
    //converts to json
    Gson gson = new Gson();
    String json = gson.toJson(urls);

    //returns json
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }
}
