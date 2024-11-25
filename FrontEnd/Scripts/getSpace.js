window.addEventListener("load",searchSpace())

const lista = document.getElementById("list_spaces")

const search = document.getElementById("searchSpace")
const lupa = document.getElementById("lupa")
// search.addEventListener("change",searchSpace(search.value))

async function searchSpace(query){
    
    const httpMethod = {
        method: 'GET',
        headers: {
                'Authorization' : sessionStorage.getItem("userToken")
            }
        };
        var spaces = await getSpace(httpMethod,query);
        console.log(spaces);
        
        spaces.forEach(element => {
            
            const a = document.createElement("a")
            a.href = "question.html"
            a.setAttribute("class", "list-group-item")

            const li = document.createElement("li")

            const div = document.createElement("div")
            div.setAttribute("class", "box-text")

            const id = document.createElement("p")
            id.textContent = `ID: ${element.spaceId}`;

            const barra = document.createElement("p")
            barra.textContent = "|";

            const nameSpace = document.createElement("p")
            nameSpace.textContent = element.title;

            div.appendChild(id)
            div.appendChild(barra)
            div.appendChild(nameSpace)

            li.appendChild(div)

            a.appendChild(li)

            lista.appendChild(a)
            
        });
        
    }

async function getSpace(httpMethod,query){
    
    if (query === undefined) {
        const response = await fetch('http://localhost:8080/spaces', httpMethod)
        const info =  await response.json()
        return info
    }

    const response = await fetch('http://localhost:8080/spaces?query'+query, httpMethod)
    const info =  await response.json()
    return info
}