package com.example.shopifieshop.ui.cart

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
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

data class CartItemUi(
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int
)

@Composable
fun CartScreen(navController: NavController) {

    val cartItems = listOf(
        CartItemUi(
            1,
            "Wireless Headphones",
            49.99,
            1
        ),
        CartItemUi(
            2,
            "Smart Watch",
            79.99,
            2
        ),
        CartItemUi(
            3,
            "Premium Perfume",
            29.99,
            1
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
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            item {
                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Text(
                    text = "My Cart",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "${cartItems.size} items",
                    color = Color.Gray
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )
            }

            items(cartItems) { item ->

                CartItemCard(item)
            }

            item {

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                OrderSummary()

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp)
                ) {

                    Text(
                        text = "Checkout",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }
        }
    }
}

@Composable
fun CartItemCard(
    item: CartItemUi
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
                    .background(
                        Color(0xFFF2F2F2)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.ShoppingBag,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = Color.Gray
                )
            }

            Spacer(
                modifier = Modifier.size(12.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = item.name,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = "$${item.price}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = {},
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            Icons.Default.Remove,
                            contentDescription = "Decrease"
                        )
                    }

                    Text(
                        text = item.quantity.toString(),
                        modifier = Modifier.padding(
                            horizontal = 8.dp
                        ),
                        fontWeight = FontWeight.Bold
                    )

                    IconButton(
                        onClick = {},
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Increase"
                        )
                    }
                }
            }

            IconButton(
                onClick = {}
            ) {

                Icon(
                    imageVector = Icons.Default.DeleteOutline,
                    contentDescription = "Delete"
                )
            }
        }
    }
}
@Composable
fun OrderSummary() {

    Column {

        Text(
            text = "Order Summary",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        SummaryRow(
            title = "Subtotal",
            value = "$209.97"
        )

        SummaryRow(
            title = "Delivery",
            value = "$10.00"
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        SummaryRow(
            title = "Total",
            value = "$219.97",
            isTotal = true
        )
    }
}
@Composable
fun SummaryRow(
    title: String,
    value: String,
    isTotal: Boolean = false
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = title,
            fontWeight = if (isTotal)
                FontWeight.Bold
            else
                FontWeight.Normal
        )

        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = if (isTotal) 18.sp else 14.sp
        )
    }
}