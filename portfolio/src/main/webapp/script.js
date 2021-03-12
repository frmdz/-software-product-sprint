// Copyright 2020 Google LLC
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

async function getAlbumRecommendation() {
  //Does a request to get some json.
  const serverResponse = await fetch("/album-recommendation");
  const responseObject = await serverResponse.json();

  //chooses a random album from resonseObject
  const randomNumber = Math.floor(Math.random() * responseObject.length);
  const randomAlbum = responseObject[randomNumber];

  //writes the chosen album into the page.
  const container = document.getElementById("recommendation-container");
  container.innerText = randomAlbum;
}

async function getMessages() {
  //Does a request to get the messages from datastore.
  const serverResponse = await fetch("/get-messages");
  const responseObject = await serverResponse.json();

  //transforms the objects into HTML formated strings
  const formattedMessages = await responseObject.map(
    elem => `${elem.name.bold()}: "${elem.message}"`
  );

  //creates a HTML <ul> and inserts elements to it
  const ul = document.createElement("ul");
  formattedMessages.forEach(msg => {
      const li = document.createElement("li");
      li.innerHTML = msg;
      ul.appendChild(li);
  });

  //inserts HTML <ul> into messages-container
  const container = document.getElementById("messages-container");
  container.appendChild(ul);
}

async function getMemes() {
  //Does a request to get the memes from cloud store
  const serverResponse = await fetch("/list-memes");
  const responseObject = await serverResponse.json();
  
  const memeUrls = responseObject.map(
      url => `<img src="${url}" max>`
  );

  const container = document.getElementById("meme-container");
  memeUrls.forEach(img => {
    const div = document.createElement("div");
    div.innerHTML = img;
    container.appendChild(div);
  });
}