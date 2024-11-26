const answer = document.getElementById("answerInput")
const button = document.getElementById("answerCreate") 
const questionId = sessionStorage.getItem("questionId")
const token = sessionStorage.getItem("userToken")
const userId = sessionStorage.getItem("userId")


button.addEventListener("click", () => createAnswer(token))

async function createAnswer(token){
        alert(answer.value);
        
        data = {
            "text": answer.value,
            "questionId": questionId
 
        }
    
        const response = await fetch("http://localhost:8080/answer", 
            {
                method: "POST",
                headers: {
                    'Authorization': token,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
        })
        
        const info = await response.text()
        location.reload("http://127.0.0.1:5500/answer.html");
        console.log(info);
        
    
    

}