window.addEventListener("load", searchAnswers(sessionStorage.getItem("questionId")))


const answer = document.getElementById("answerInput");
const button = document.getElementById("answerCreate");
const questionId = sessionStorage.getItem("questionId");
const token = sessionStorage.getItem("userToken");
const userId = sessionStorage.getItem("userId");

const questionTitle = document.getElementById("questionTitle");
const questionText = document.getElementById("questionText");

button.addEventListener("click", () => createAnswer(token));

async function createAnswer(token) {
  alert(answer.value);

  data = {
    text: answer.value,
    questionId: questionId,
  };

  const response = await fetch("http://localhost:8080/answer", {
    method: "POST",
    headers: {
      Authorization: token,
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  const info = await response.text();
  location.reload("http://127.0.0.1:5500/answer.html");
  console.log(info);
}
  async function searchAnswers(questionId) {
    console.log("oi");

    const httpMethod = {
      method: "GET",
      headers: {
        Authorization: sessionStorage.getItem("userToken"),
      },
    };
    var response = await fetch(
      "http://localhost:8080/question/byId/" + questionId,
      httpMethod
    );
    var info = await response.json();
    console.log(info);

    questionTitle.textContent = `${info.questionTitle}`;
    questionText.textContent = `${info.questionText}`;
    info.answers.forEach((answer) => {
      console.log(answer);

      const li = document.createElement("li");
      li.setAttribute(
        "class",
        "list-group-item d-flex justify-content-between align-items-start"
      );

      const div = document.createElement("div");
      div.setAttribute("class", "ms-2 me-auto");

      const questionTitle = document.createElement("div");
      questionTitle.setAttribute("class", "fw-bold");
      questionTitle.textContent = `${answer.userName}`;

      const questionText = document.createElement("p");
      questionText.textContent = `${answer.text}`;

      const lista = document.getElementById("list_answer");

      div.appendChild(questionTitle);
      div.appendChild(questionText);
      li.appendChild(div);

      lista.appendChild(li);
    });
  }

