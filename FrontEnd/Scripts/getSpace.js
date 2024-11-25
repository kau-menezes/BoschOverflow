window.addEventListener("load",searchSpace())



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
        
        spaces.forEach(element => {
            const div = document.createElement("div")
            div.className("")
            
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