var loginButton = document.getElementById("loginButtonUser")

loginButton.addEventListener("click", async function () {
    var edv = document.getElementById("edv").value;
    var password = document.getElementById("userPassword").value;
    console.log(edv);
    console.log(password);
    

    const userData = {
        "email": "",
        "edv": edv,
        "password": password
    }

    const httpMethod = {
        method: 'POST',
        headers: {
        'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData),
    };
        
    var data = await Login(httpMethod)
    console.log(data.user);
    alert(data.msg)

    if (data.msg === "Usuário logado com sucesso!") {
        window.location.replace("http://127.0.0.1:5500/FrontEnd/space/home.html");
        sessionStorage.setItem("userId", data.user.Id)
        sessionStorage.setItem("userEDV", data.user.edv)
        sessionStorage.setItem("userToken", data.user.token)
        console.log(data.user);   
    }
})
    



async function Login(httpMethod){
    const response = await fetch('http://localhost:8080/auth', httpMethod)
     if (response.status === 404) {
        return { "msg" : "Usuario nao encontrado", "token" : ""}
     }

     if (response.status === 401 ) {
        return  { "msg" : "Senha Incorreta", "token" : ""}
     }
    
    const info =  await response.json()
    return info
}