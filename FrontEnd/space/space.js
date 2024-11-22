document.addEventListener('DOMContentLoaded', () => {
    const formSpace = document.getElementById('formSpace');

    //add um evento submit ao formulario
    formSpace.addEventListener('submit', async(e) => {
        e.preventDefault();

        //coletar os valores dos campos
        const spaceValue = document.getElementById('newSpace').value

       

        //FETCH API -ENVIAR OS DADOS VIS REQUEST(POST) PARA O SERVIDOR

        //Armazenando o token com localstorage
        // localStorage.setItem('nomedotoken', token);

        // //Obtendo token com localstorage
        // var token = localStorage.getItem("nomedotoken");

        // //Armazenando o token com sessionStorage
        // sessionStorage.setItem('chave', 'valor');

        //Obtendo token com sessionStorage

        var data = sessionStorage.getItem('token');
        var edv = sessionStorage.getItem("userEDV");

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

            body: JSON.stringify(formData), //converte o objeto em string json
        })
         
    })
})


