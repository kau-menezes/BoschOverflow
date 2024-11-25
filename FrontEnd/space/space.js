document.addEventListener('DOMContentLoaded', () => {
    const formSpace = document.getElementById('formSpace');

    //add um evento submit ao formulario
    formSpace.addEventListener('submit', async(e) => {
        e.preventDefault();

        const spaceValue = document.getElementById('newSpace').value

    
        //FETCH API -ENVIAR OS DADOS VIS REQUEST(POST) PARA O SERVIDOR
        
        var data = sessionStorage.getItem('token');
        var edv = sessionStorage.getItem("userEDV");
        
        console.log(data)
        console.log(edv)
        console.log(spaceValue)

        const formData = {
            title: spaceValue,
            EDV: edv
        }

        const response = await fetch('http://localhost:8080/spaces',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization:  data,
            },

            body: JSON.stringify(formData) //converte o objeto em string json
        })

        window.location.reload("http://127.0.0.1:5500/FrontEnd/space/home.html");
    })
})

// const createBtn = document.getElementById("btnSpace")
// console.log(createBtn);


// createBtn.addEventListener("click", createSpace())


// async function createSpace() {
//     var spaceValue = document.getElementById("newSpace").value;
//     console.log(spaceValue);

//     var edv = sessionStorage.getItem("userEDV");

//     const formData = {
//         "title": spaceValue,
//         "EDV": edv
//     }
        
//     var data = sessionStorage.getItem('token');
    
//     const response = await fetch('http://localhost:8080/spaces',{
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json',
//             Authorization:  data,
//         },

//         body: JSON.stringify(formData) //converte o objeto em string json
//     })

//     window.location.reload("http://127.0.0.1:5500/FrontEnd/space/home.html");

// }


