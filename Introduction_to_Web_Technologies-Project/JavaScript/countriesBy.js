
function buildList(country="a") {
    let table2 = document.getElementById('data2')
    table2.innerHTML = ""

    let url = `http://192.168.160.58/Olympics/api/Countries/SearchByName?q=${country}`

    fetch(url)
        .then((response) => response.json())
        .then(function (data) {
            console.log('Data22:', data)

        let counter = 0
           for (const modal of data) {
            let id, name, IOC
            id = modal.Id
            name = modal.Name
            IOC = modal.IOC

            console.log(modal)
            console.log("url: ", url)
            if (counter == 10) {
                break
            }
            table2.innerHTML += `<tr>
            <td class="align-middle" >${id}</td>
            <td class="align-middle" >${name}</td>
            <td class="align-middle" >${IOC}</td>
            <td class="text-end">
                <a class="btn btn-default btn-light btn-xs" href="./countriesDetails.html?id=${id}">
                    <i class="fa fa-eye" title="Selecione para ver detalhes"></i>
                </a>
                <button class="btn btn-default btn-xs">
                    <i class="fa fa-heart-o" title="Selecione para adicionar aos favoritos"></i>
                </button>
            </td>
        </tr>`
            counter++
           }
           /* data.forEach(element => {
                console.log(element)


            });*/



          
        })
}

buildList()

console.log("imprimindo valor...")
let input_box = document.querySelector("#country")

input_box.addEventListener("keyup", get_value);

function get_value() {
    console.log("dentro da função")
    document.getElementById('data2').innerHTML = ""
    let value_ = document.querySelector("#country").value
    console.log("valor: ", value_)
    buildList(value_)
}



