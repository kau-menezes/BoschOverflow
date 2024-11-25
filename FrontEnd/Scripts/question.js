var deleteForm = document.getElementById("deleteQuestion")
var token = sessionStorage.getItem("userToken")
var addBtn = document.getElementById("btnAdd")

addBtn.addEventListener("click", function() {

    console.log('fala fi')

    var userId = parseInt(sessionStorage.getItem("userId"))

    data = {
        "questionTitle": document.getElementById("newQuestionTitle").value,
        "questionText": document.getElementById('newQuestionText').value,
        "spaceId": 7,
        "userId": userId
    }

    fetch("http://localhost:8080/question", 
        {
            headers: {
                'Authorization': token,
                'Access-Control-Allow-Origin' : "http://127.0.0.1:5500",
            },
            method: "POST",
            body: JSON.stringify(data)
    })
    // .then(res => res.json())
    .then(res => console.log(res))
})

deleteForm.addEventListener("submit", function() {

    console.log('fala fi')

    var questionId = deleteForm.elements['questionId'].value

    fetch("http://localhost:8080/question/" + questionId, 
        {
            headers: {
                'Authorization': token
            },
            method: "DELETE"
        }
    )
    .then(res => res.json())
    .then(res => console.log(res))
})