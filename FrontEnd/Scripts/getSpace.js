window.addEventListener("load",searchSpace())

const lista = document.getElementById("list_spaces")

const search = document.getElementById("searchSpace")
const lupa = document.getElementById("lupa")
const links = document.querySelectorAll("a.list-group-item")


    

// search.addEventListener("change",searchSpace(search.value))

async function searchSpace(query){

    const httpMethod = {
        method: 'GET',
        headers: {
                'Authorization' : sessionStorage.getItem("userToken")
            }
        };
        var spaces = await getSpace(httpMethod,query);
        
        spaces.forEach(element => {
            
            const a = document.createElement("a")
            a.href = "question.html"
            a.setAttribute("class", "list-group-item")

            const li = document.createElement("li")

            const div = document.createElement("div")
            div.setAttribute("class", "box-text")

            const id = document.createElement("p")
            id.setAttribute("class", "id")
            id.textContent = `${element.spaceId}`;

            const barra = document.createElement("p")
            barra.textContent = "|";

            const nameSpace = document.createElement("p")
            nameSpace.textContent = element.title;

            div.appendChild(id)
            div.appendChild(barra)
            div.appendChild(nameSpace)

            li.appendChild(div)
            
            a.appendChild(li)
            a.addEventListener("click",(event) => {
                event.preventDefault()
                sessionStorage.setItem("spaceId",a.querySelector("li .box-text .id").textContent)
                console.log(a.querySelector("li .box-text .id").textContent);
                window.location.replace("http://127.0.0.1:5500/question.html");
            })
            
            lista.appendChild(a)
            
        });
        
    }

async function getSpace(httpMethod, query){
    
    if (query === undefined) {
        const response = await fetch('http://localhost:8080/spaces', httpMethod)
        const info =  await response.json()
        return info
    }

    const response = await fetch('http://localhost:8080/spaces?query'+query, httpMethod)
    const info =  await response.json()
    return info
}