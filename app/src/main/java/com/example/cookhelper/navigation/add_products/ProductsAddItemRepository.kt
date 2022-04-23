package com.example.cookhelper.navigation.add_products

import com.example.cookhelper.entities.AddProductsItem

class ProductsAddItemRepository {
    val products = mutableListOf<AddProductsItem>(
        AddProductsItem(
            1,
            "Peach",
            "Fruit ",
            "https://media.istockphoto.com/photos/peach-with-slice-and-leaf-isolated-on-white-picture-id828941520?k=6&m=828941520&s=612x612&w=0&h=rEsfdS7JodNKapZI5Hq6EFIfZtBt-atGwo9GzB11rgc="
        ),
        AddProductsItem(
            2,
            "Carrot",
            "Vegetable ",
            "https://befreshcorp.net/wp-content/uploads/2017/06/product-packshot-Carrot-558x600.jpg"
        ),
        AddProductsItem(
            3,
            "Apple",
            "Fruit ",
            "https://www.organichaive.com.ng/wp-content/uploads/2017/01/apple_red-350x350.jpg"
        ),
        AddProductsItem(
            4,
            "Chicken",
            "Meat ",
            "http://efresh.com/sites/default/files/chickenfrozen_1.jpg"
        ),
        AddProductsItem(
            5,
            "Beef",
            "Meat ",
            "https://blueenergytrade.com/wp-content/uploads/2019/02/beef_3.jpg"
        ),
        AddProductsItem(
            6,
            "Milk",
            "Animal milk",
            "https://media.gettyimages.com/photos/glass-of-milk-picture-id594838587?s=2048x2048"
        )

    )

}