window.addEventListener("load",searchQuestions(sessionStorage.getItem("spaceId")));


var deleteForm = document.getElementById("deleteQuestion")
var token = sessionStorage.getItem("userToken")
var addBtn = document.getElementById("btnAdd")

addBtn.addEventListener("click", function() {

    console.log('fala fi')

    var userId = sessionStorage.getItem("userId")
    data = {
        "questionTitle": document.getElementById("newQuestionTitle").value,
        "questionText": document.getElementById('newQuestionText').value,
        "spaceId": sessionStorage.getItem("spaceId"),
        "userId": userId
    }

    const response = await fetch("http://localhost:8080/question", 
        {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token,
                'Access-Control-Allow-Origin' : "http://127.0.0.1:5500",
            },
            body: JSON.stringify(data)
    })
    // .then(res => res.json())
    .then(res => console.log(res))
})

deleteBtn.addEventListener("click", function() {

    var questionId = document.getElementById("questionId").value

    fetch("http://localhost:8080/question/" + questionId, 
        {
            headers: {
                'Authorization': token
            },
            method: "DELETE"
        }
    )
    .then(res => res.text())
    .then(res => alert(res))
    
    document.getElementById('closeDeleteModal').click()
})



async function searchQuestions(spaceId){

    console.log("oi")

    const httpMethod = {
        method: 'GET',
        headers: {
                'Authorization' : sessionStorage.getItem("userToken")
            }
        };
        var response = await fetch("http://localhost:8080/question/" + spaceId, httpMethod );
        var questions = await response.json()
        console.log(questions)

        questions.forEach(question => {
        console.log(question);
        
            const a = document.createElement("a")

            const li = document.createElement("li")
            li.setAttribute("class", "list-group-item d-flex justify-content-between align-items-start")


            const div = document.createElement("div")
            div.setAttribute("class", "ms-2 me-auto")

            const questionTitle = document.createElement("div")
            questionTitle.setAttribute("class", "fw-bold")
            questionTitle.textContent = `${question.questionTitle}`;

            const questionText = document.createElement("p")
            questionText.textContent = `${question.questionText}`;


            const id = document.createElement("span")
            id.setAttribute("class", "badge rounded-pill")
            id.textContent = `${question.questionId}`;

            div.appendChild(questionText)
            div.appendChild(questionTitle)
            li.appendChild(div)
            li.appendChild(id)

            
            a.appendChild(li)
            a.addEventListener("click",(event) => {
                event.preventDefault()
                sessionStorage.setItem("questionId",question.questionId)
                window.location.replace("http://127.0.0.1:5500/answer.html");
            })
            
            lista.appendChild(a)
            
        });
        
    }