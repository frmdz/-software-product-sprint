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

  //stores a list of spotify IDs
  private ArrayList<String> albumList = new ArrayList<String>();

  public AlbumRecommendationServlet() {
    albumList.add("1VW1MFNstaJuygaoTPkdCk");
    albumList.add("50xG9YujTzMGaLHfJTskBy");
    albumList.add("6F2iD5c7Aca3E0wmmPhgax");
    albumList.add("468ZwCchVtzEbt9BHmXopb");
    albumList.add("3KiA7e7NDRMKecw7YGVkZK");
    albumList.add("5l5m1hnH4punS1GQXgEi3T");
    albumList.add("3kBG6q0aXKxzn01wKTwZr5");
    albumList.add("132qAo1cDiEJdA3fv4xyNK");
    albumList.add("79dL7FLiJFOO0EoehUHQBv");
    albumList.add("1J8QW9qsMLx3staWaHpQmU");
    albumList.add("1XkGORuUX2QGOEIL4EbJKm");
    albumList.add("78bpIziExqiI9qztvNFlQu");
    albumList.add("4m2880jivSbbyEGAKfITCa");
    albumList.add("48D1hRORqJq52qsnUYZX56");
    albumList.add("2e5TG0TyL4Q1HvW4w2O8FP");
    albumList.add("2ym2jcqckXqWeTDoxz3Kst");
    albumList.add("52AeC4gwbxDfFlLHgK1ByD");
    albumList.add("6uuQKwM3fRETiscHqlnxuo");
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