var addForm = document.getElementById("createQuestion")
var deleteForm = document.getElementById("deleteQuestion")
var token = sessionStorage.getItem("token")

addForm.addEventListener("submit", function() {

    var userId = sessionStorage.getItem("userId")

    data = {
        "questionTitle": addForm.elements['newQuestionTitle'],
        "questionText": addForm.elements['newQuestionText'],
        "spaceId": 4,
        "userId": userId
    }

    fetch("http://localhost:8080/question", 
        {
            headers: {
                'Authorization': token
            },
            method: "POST",
            body: JSON.stringify(data)
    })
})

deleteForm.addEventListener("submit", function() {

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