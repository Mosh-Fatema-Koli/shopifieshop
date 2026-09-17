package com.example.studentcrud.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopifieshop.ui.AppNav.ProductBottomBar
import androidx.navigation.NavController

@Composable
fun FavouriteScreen(navController: NavController) {

    val favouriteProducts = listOf(
        ProductUi(
            1,
            "Wireless Headphones",
            49.99,
            4.5
        ),
        ProductUi(
            2,
            "Smart Watch",
            79.99,
            4.3
        ),
        ProductUi(
            3,
            "Premium Perfume",
            29.99,
            4.2
        ),
        ProductUi(
            4,
            "Running Shoes",
            59.99,
            4.7
        )
    )

    Scaffold(
        bottomBar = { ProductBottomBar(navController)}
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Text(
                    text = "My Favorites",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Your saved products",
                    color = Color.Gray
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )
            }

            item {

                Column(
                    verticalArrangement =
                        Arrangement.spacedBy(14.dp)
                ) {

                    favouriteProducts
                        .chunked(2)
                        .forEach { rowProducts ->

                            Row(
                                modifier =
                                    Modifier.fillMaxWidth(),
                                horizontalArrangement =
                                    Arrangement.spacedBy(14.dp)
                            ) {

                                rowProducts.forEach { product ->

                                    FavouriteCard(
                                        product = product,
                                        modifier =
                                            Modifier.weight(1f)
                                    )
                                }

                                if (rowProducts.size == 1) {
                                    Spacer(
                                        modifier =
                                            Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                }
            }
        }
    }
}
@Composable
fun FavouriteCard(
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
                    modifier = Modifier.size(50.dp),
                    tint = Color.Gray
                )

                IconButton(
                    onClick = {},
                    modifier = Modifier.align(
                        Alignment.TopEnd
                    )
                ) {

                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Remove favorite"
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = product.name,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )

            Text(
                text = "⭐ ${product.rating}",
                fontSize = 13.sp,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment =
                    Alignment.CenterVertically
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
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Add to cart"
                    )
                }
            }
        }
    }
}