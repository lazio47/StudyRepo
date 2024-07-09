
function buildList(games = "19") {
    let table2 = document.getElementById('data2')
    let div2 = document.getElementById('div2')
    table2.innerHTML = ""

    let url = `http://192.168.160.58/Olympics/api/Games/SearchByName?q=${games}`

    fetch(url)
        .then((response) => response.json())
        .then(function (data) {
            console.log('Data22:', data)

            let counter = 0
            for (const modal of data) {
                let id, name, country, year
                id = modal.Id
                name = modal.Name
                year = modal.Year
                country = modal.CountryName

                console.log(modal)
                console.log("url: ", url)
                if (counter == 1) {
                    break
                }
                table2.innerHTML += `<tr>
            <td class="align-middle" >${id}</td>
            <td class="align-middle" >${name}</td>
            <td class="align-middle" >${year}</td>
            <td class="align-middle" >${country}</td>
            <td class="text-end">
                <a class="btn btn-default btn-light btn-xs" href="./gameDetails.html?id=${id}">
                    <i class="fa fa-eye" title="Selecione para ver detalhes"></i>
                </a>
                <button class="btn btn-default btn-xs">
                    <i class="fa fa-heart-o" title="Selecione para adicionar aos favoritos"></i>
                </button>
            </td>
        </tr>`

                /*Statistics*/

                let url2 = `http://192.168.160.58/Olympics/api/Statistics/Medals_Country?id=${id}`
                fetch(url2)
                    .then((response) => response.json())
                    .then(function (data) {
                        console.log('Data222:', data)

                        let counter = 0
                        for (const modal of data) {
                            let countryid, countryname, countryflag, medals
                            countryid = modal.CountryId
                            countryname = modal.CountryName
                            countryflag = modal.CountryFlag
                            medals = modal.Medals

                            function drawChart() {

                                // Create the data table.
                                var data = new google.visualization.DataTable();
                                data.addColumn('string', 'Topping');
                                data.addColumn('number', 'Slices');
                                data.addRows([
                                  ['Gold', medals[0].Counter],
                                  ['Silver', medals[1].Counter],
                                  ['Bonze', medals[2].Counter],
                                ]);
                        
                                // Set chart options
                                var options = {'title':'Stats',
                                               'width':200,
                                               'height':150};
                        
                                // Instantiate and draw our chart, passing in some options.
                                var chart = new google.visualization.PieChart(document.getElementById(`div_${id}`));
                                chart.draw(data, options);
                              }
                                // Load the Visualization API and the corechart package.
                                google.charts.load('current', {'packages':['corechart']});
                        
                                // Set a callback to run when the Google Visualization API is loaded.
                                google.charts.setOnLoadCallback(drawChart);
                     
                            div2.innerHTML += `
                                <div class="card d-inline-block col-md-4">
                                    <img class="img-thumbnail" src=${countryflag} style="max-width:50%;"><br /><i class="fa fa-link"></i><br />
                                </div>
                                    
                            </div>
                            <div class="jumbotron d-inline-block col-md-7">
                                <h5 class="display-7">
                                    
                                        <div class="row border-bottom  my-1">
                                            <div class="">Country Id: ${countryid}</div>
                                        </div>
                                    
                                        <div class="row border-bottom   my-1">
                                            <div class="">Country Name: ${countryname}</div>
                                        </div>

                                        <div class="row border-bottom   my-1">
                                            <div class="">Gold: ${medals[0].Counter}, Silver: ${medals[1].Counter}, Bronze: ${medals[2].Counter}</div>
                                        </div>
                                        <div class="card d-inline-block col-md-4" id="div_${id}">
                                       
                                        </div>
                                        
                                        
                                        
                                </h5>
                                
                            </div>`
                            counter++

                        }

                    })


                counter++
            }

            /* data.forEach(element => {
                 console.log(element)
 
 
             });*/




        })
}

buildList()

console.log("imprimindo valor...")
let input_box = document.querySelector("#games")

input_box.addEventListener("keyup", get_value);

function get_value() {
    console.log("dentro da função")
    document.getElementById('data2').innerHTML = ""
    let value_ = document.querySelector("#games").value
    console.log("valor: ", value_)
    buildList(value_)
}

