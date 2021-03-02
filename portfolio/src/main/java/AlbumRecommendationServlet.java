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

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

/** Servlet that returns HTML recommends a music album, for now is just a test". **/
@WebServlet("/album-recommendation")
public class AlbumRecommendationServlet extends HttpServlet {

  //stores a list of albums that will be returned
  private ArrayList<String> albumList = new ArrayList<String>();

  public AlbumRecommendationServlet() {
    albumList.add("(1994) Definitely Maybe, by Oasis");
    albumList.add("(2019) South of Reality, by The Claypool Lennon Delirium");
    albumList.add("(2015) Currents, by Tame Impala");
    albumList.add("(1971) Master of Reality, by Black Sabbath");
    albumList.add("(1967) Axis: Bold as Love, by The Jimi Hendrix Experience");
    albumList.add("(1971) Meddle, by Pink Floyd");
    albumList.add("(1995) (What's the Story) Morning Glory?, by Oasis");
    albumList.add("(1961) Led Zeppelin I, by Led Zeppelin");
    albumList.add("(2006) Erotic Cakes, by Guthrie Govan");
    albumList.add("(2010) Brothers, by The Black Keys");
    albumList.add("(1972) Thick as a Brick, by Jethro Tull");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //converts albumList to json
    Gson gson = new Gson();
    String json = gson.toJson(albumList);

    //returns the json
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }
}