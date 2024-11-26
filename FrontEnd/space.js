document.addEventListener('DOMContentLoaded', () => {
    const formSpace = document.getElementById('formSpace');
    const formPermission = document.getElementById('formPermission');
    
    //add um evento submit ao formulario
    formSpace.addEventListener('submit', async(e) => {
        e.preventDefault();

        const spaceValue = document.getElementById('newSpace').value
        //FETCH API -ENVIAR OS DADOS VIS REQUEST(POST) PARA O SERVIDOR
        
        var data = sessionStorage.getItem('userToken');
        var edv = sessionStorage.getItem("userEDV");
        
        console.log(data)
        console.log(edv)
        console.log(spaceValue)

        if(spaceValue ==  ""){
            window.alert("O campo de nome não pode ser vazio")
            return;
        }

        const formData = {
            title: spaceValue,
            EDV: edv
        }

        const response = await fetch('http://localhost:8080/spaces',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization':  data
            },

            body: JSON.stringify(formData) //converte o objeto em string json
        })

        if( spaceValue != ""){  
            alert("Space criado com sucesso!")
        }


        location.reload("http://127.0.0.1:5500/home.html");
    })


        //add um evento submit ao formulario
    formPermission.addEventListener('submit', async(e) => {
        e.preventDefault();

        const emailValue = document.getElementById('email').value
        const spaceValue = document.getElementById('spaceID').value
        const permissonValue = document.getElementById('permissionName').value

        //FETCH API -ENVIAR OS DADOS VIS REQUEST(POST) PARA O SERVIDOR


        if(emailValue ==  "" || spaceValue == "" || permissonValue ==  ""){
            window.alert("O campo de nome não pode ser vazio")
            return;
        }

        const formData = {
            email: emailValue,
            spaceID: spaceValue,
            newPermission: permissonValue
        }

        const response = await fetch('http://localhost:8080/permission',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization':  data,
                'Origin' : "http://127.0.0.1:5500"
            },

            body: JSON.stringify(formData) //converte o objeto em string json
        })

        if( spaceValue != ""){  
            alert("Permissao criado com sucesso!")
        }


        location.reload("http://127.0.0.1:5500/FrontEnd/space/home.html");
    })
})


