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

/** Servlet that returns json with a list of music album recomendations. **/
@WebServlet("/album-recommendation")
public class AlbumRecommendationServlet extends HttpServlet {

  //stores the list of albums that will be returned
  private ArrayList<String> albumList = new ArrayList<String>();

  public AlbumRecommendationServlet() {
    albumList.add("(1994) Definitely Maybe, by Oasis");
    albumList.add("(2019) South of Reality, by The Claypool Lennon Delirium");
    albumList.add("(2015) Currents, by Tame Impala");
    albumList.add("(1971) Master of Reality, by Black Sabbath");
    albumList.add("(1967) Axis: Bold as Love, by The Jimi Hendrix Experience");
    albumList.add("(1971) Meddle, by Pink Floyd");
    albumList.add("(1995) (What's the Story) Morning Glory?, by Oasis");
    albumList.add("(1969) Led Zeppelin I, by Led Zeppelin");
    albumList.add("(2006) Erotic Cakes, by Guthrie Govan");
    albumList.add("(2010) Brothers, by The Black Keys");
    albumList.add("(1972) Thick as a Brick, by Jethro Tull");
    albumList.add("(2007) Favourite Worst Nightmare, by Arctic Monkeys");
    albumList.add("(2013) AM, by Arctic Monkeys");
    albumList.add("(2006) Whatever People Say I Am, That's What I'm Not, by Arctic Monkeys");
    albumList.add("(1970) Paranoid, by Black Sabbath");
    albumList.add("(2008) Reptilectric, by Zoé");
    albumList.add("(2006) Memo Rex Commander y el Corazón Atómico de la Vía Láctea, by Zoé");
    albumList.add("(1976) Rising, by Rainbow");
    albumList.add("(1998) The Masterplan, by Oasis");
    albumList.add("(2013) Random Access Memories, by Daft Punk");
    albumList.add("(2000) Standing on the Shoulders of Gigants, by Oasis");
    albumList.add("(1983) Kill 'em All, by Metallica");
    albumList.add("(1984) Ride the Lightning, by Metallica");
    albumList.add("(2001) Lateralus, by Tool");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //converts albumList to json
    Gson gson = new Gson();
    String json = gson.toJson(albumList);

    //returns json
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }
}