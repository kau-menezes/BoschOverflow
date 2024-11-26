var token = sessionStorage.getItem("userToken")
var addBtn = document.getElementById("btnAdd")
var deleteBtn = document.getElementById("btnDelete")



addBtn.addEventListener("click", function() {

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
                'Content-Type': 'application/json',
                'Authorization': token,
            },
            method: "POST",
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
    .then(res => res.json())
    .then(res => console.log(res))
})

