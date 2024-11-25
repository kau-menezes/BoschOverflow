const input = document.getElementById("deleteInput")
const button = document.getElementById("delete")
const token = sessionStorage.getItem("userToken")

const httpMethod = {
    method: 'DELETE',
    headers: {
        'Authorization' : token
    }
};
console.log(httpMethod);

button.addEventListener("click", () => deleteSpace(httpMethod))

async function deleteSpace(httpMethod){
    const response = await fetch('http://localhost:8080/spaces/' + Number(input.value), httpMethod)
    const info =  await response.text()
    alert(info)
    window.location.reload()
    return info
}