var SignUp = document.getElementById("SignUpButton")



SignUp.addEventListener("click", async function () {
    var username = document.getElementById("usernameUser").value;
    var edv = document.getElementById("edvUser").value;
    var email = document.getElementById("emailUser").value;
    var password = document.getElementById("passwordUser").value;
    

    const userData = {
        "username": username,
        "email": email,
        "password": password,
        "EDV": edv,
    }

    const httpMethod = {
        method: 'POST',
        headers: {
        'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData),
        };
        
        var data = await createUser(httpMethod)
        alert(data.msg)
    })

async function createUser(httpMethod){
    const response = await fetch('http://localhost:8080/user', httpMethod)
     if (response.status === 400) {
        return { "msg" : "Algum dos campos está vazio!"}
     }

     if (response.status === 422  ) {
        return  { "msg" : "Usuário já cadastrado!"}
     }
    
    const info =  await response.json()
    return info
}