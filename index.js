// {
//     "id": 1,
//     "title": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
//     "price": 109.95,
//     "description": "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
//     "category": "men's clothing",
//     "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
//     "rating": {
//         "rate": 3.9,
//         "count": 120
//     }
// }
fetch('https://fakestoreapi.com/products')
    .then(function (res) { return res.json(); })
    .then(function (products) {
    //Prepare table html:
    var tableHtml = '<thead><tr><th>ID</th><th>TITLE</th><th>DESCRIPTION</th><th>PRICE</th></tr></thead><tbody>';
    //Loop through all products to generate table rows od the table
    products.forEach(function (p) {
        tableHtml += "<tr><td>".concat(p.id, "</td><td>").concat(p.title, "</td><td>").concat(p.description, "</td><td>").concat(p.price, "</td></tr>"); //con el alt y la tecla de cerrar llave se hace la comilla invertida
    });
    //close table body
    tableHtml += '</tbody>';
    //grab table element to set its inner html
    document.querySelector('#tableElement').innerHTML = tableHtml;
    // hide spinner
    var spinnerElement = document.querySelector('#spinnerContainer'); //el signo de exclamacion sirve para indicarle a typescript que el los busque si o si al elemento pq ya sabemos que lo va a encontrar
    spinnerElement.style.display = 'none';
});
