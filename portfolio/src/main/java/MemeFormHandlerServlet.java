// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/** Servlet that allows users submit memes. */
@WebServlet("/upload-meme")
@MultipartConfig
public class MemeFormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
          
    // gets the meme.
    Part meme = request.getPart("image");
    //TODO: use UUID.
    String fileName = System.currentTimeMillis() + meme.getSubmittedFileName();
    InputStream fileInputStream = meme.getInputStream();

    PrintWriter out = response.getWriter();
    if(fileInputStream.available() > 0) {
      //uploads the file to cloud storage
      String uploadedFileUrl = uploadToCloudStorage(fileName, fileInputStream);
    
      //shows the uploaded meme.
      out.println("<p>Your meme was uploaded!!!</p>");
      out.println("<img src=\"" + uploadedFileUrl + "\" />");

    } else {
      //the user tried to submit a empty form.
      out.println("<p>You need to submit a file.</p>");
    }
  }

  /** Uploads a file to Cloud Storage and returns the uploaded file's URL. */
  private static String uploadToCloudStorage(String fileName, InputStream fileInputStream) {
    String projectId = "fmendoza-sps-spring21";
    String bucketName = "fmendoza-sps-spring21.appspot.com";

    //magic, I need to read the documentation
    Storage storage =
      StorageOptions.newBuilder()
        .setProjectId(projectId)
        .build()
        .getService();
    BlobId blobId = BlobId.of(bucketName, fileName);
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

    // uploads the file to cloud storage
    Blob blob = storage.create(blobInfo, fileInputStream);

    //returns file URL.
    return blob.getMediaLink();
  }
}
