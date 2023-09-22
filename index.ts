type Rating = {
    rate: number;
    count: number;
};

type Product = {
    id: number;
    title: string;
    price: number;
    description: string;
    category: string;
    image: string;
    rating: Rating;
};

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
    .then (res => res.json())
    .then ((products: Product[]) => {
        //Prepare table html:
        let tableHtml: string = '<thead><tr><th>ID</th><th>TITLE</th><th>DESCRIPTION</th><th>PRICE</th></tr></thead><tbody>';
        //Loop through all products to generate table rows od the table
        products.forEach((p: Product) =>{
            tableHtml += `<tr><td>${p.id}</td><td>${p.title}</td><td>${p.description}</td><td>${p.price}</td></tr>`; //con el alt y la tecla de cerrar llave se hace la comilla invertida
        });
        //close table body
        tableHtml += '</tbody>';
        //grab table element to set its inner html
        document.querySelector('#tableElement')!.innerHTML = tableHtml;
        // hide spinner
        const spinnerElement: HTMLElement = document.querySelector('#spinnerContainer')!; //el signo de exclamacion sirve para indicarle a typescript que el los busque si o si al elemento pq ya sabemos que lo va a encontrar
        spinnerElement!.style.display = 'none';

    }); 