var form = document.getElementById("createQuestion")

form.addEventListener("submit", function() {

    var token = sessionStorage.getItem("token")
    var userId = sessionStorage.getItem("userId")

    data = {
        "questionTitle": form.elements['newQuestionTitle'],
        "questionText": form.elements['newQuestionText'],
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