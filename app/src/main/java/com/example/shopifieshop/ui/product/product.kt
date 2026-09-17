package com.example.studentcrud.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ProductUi(
    val id: Int,
    val name: String,
    val price: Double,
    val rating: Double
)

@Composable
fun ProductScreen() {

    val products = remember {
        listOf(
            ProductUi(1, "Wireless Headphones", 49.99, 4.5),
            ProductUi(2, "Smart Watch", 79.99, 4.3),
            ProductUi(3, "Premium Perfume", 29.99, 4.2),
            ProductUi(4, "Running Shoes", 59.99, 4.7),
            ProductUi(5, "Leather Bag", 45.99, 4.4),
            ProductUi(6, "Sunglasses", 24.99, 4.1)
        )
    }

    Scaffold(
        bottomBar = {
            ProductBottomBar()
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 20.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                ProductHeader()
            }

            item {
                SearchBar()
            }

            item {
                CategorySection()
            }

            item {
                ProductTitle()
            }

            item {
                ProductGrid(products)
            }
        }
    }
}

@Composable
fun ProductHeader() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {

            Text(
                text = "Discover",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "Find your favorite products",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        IconButton(
            onClick = {}
        ) {

            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Cart",
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
fun SearchBar() {

    OutlinedTextField(
        value = "",
        onValueChange = {},

        modifier = Modifier.fillMaxWidth(),

        placeholder = {
            Text("Search products...")
        },

        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },

        singleLine = true,

        shape = RoundedCornerShape(14.dp)
    )
}

@Composable
fun CategorySection() {

    Column {

        Text(
            text = "Categories",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(
                listOf(
                    "All",
                    "Beauty",
                    "Electronics",
                    "Fashion",
                    "Sports"
                )
            ) { category ->

                CategoryItem(
                    category = category
                )
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: String
) {

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {

        Text(
            text = category,
            modifier = Modifier.padding(
                horizontal = 18.dp,
                vertical = 10.dp
            ),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ProductTitle() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Popular Products",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "See all",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ProductGrid(
    products: List<ProductUi>
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        products.chunked(2).forEach { rowProducts ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                rowProducts.forEach { product ->

                    ProductCard(
                        product = product,
                        modifier = Modifier.weight(1f)
                    )
                }

                if (rowProducts.size == 1) {
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun ProductCard(
    product: ProductUi,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(10.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp)
                    .clip(
                        RoundedCornerShape(14.dp)
                    )
                    .background(
                        Color(0xFFF2F2F2)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null,
                    modifier = Modifier.size(55.dp),
                    tint = Color.Gray
                )

                IconButton(
                    onClick = {},
                    modifier = Modifier.align(
                        Alignment.TopEnd
                    )
                ) {

                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite"
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = product.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "⭐ ${product.rating}",
                fontSize = 13.sp,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(7.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "$${product.price}",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                IconButton(
                    onClick = {}
                ) {

                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add to cart"
                    )
                }
            }
        }
    }
}

@Composable
fun ProductBottomBar() {

    NavigationBar {

        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Home"
                )
            },
            label = {
                Text("Home")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = "Cart"
                )
            },
            label = {
                Text("Cart")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    Icons.Default.FavoriteBorder,
                    contentDescription = "Favorites"
                )
            },
            label = {
                Text("Favorites")
            }
        )
    }
}