package com.cdev.kmmsharedui.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cdev.kmmsharedui.theme.black500
import com.cdev.kmmsharedui.theme.dodgerBlue
import com.cdev.kmmsharedui.theme.seashellPeachOrange
import com.cdev.kmmsharedui.model.UserDomainModel

@Composable
fun HomeScreenUI(
    state: HomeScreenState,
    onUserClick: (UserDomainModel) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val listState = rememberLazyListState()

            when (state) {
                is HomeScreenState.Error -> {
                    Text(
                        text = "Error" + state.errorMessage,
                        style = MaterialTheme.typography.h3
                    )
                }

                HomeScreenState.Idle -> {}

                HomeScreenState.Loading -> {
                    Text(
                        text = "Loading",
                        style = MaterialTheme.typography.h3
                    )
                }

                is HomeScreenState.Success -> {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        state = listState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        users(state.users, onItemClick = onUserClick)
                    }
                }
            }
        }
    }
}


private fun LazyListScope.users(
    vouchers: List<UserDomainModel>,
    onItemClick: (UserDomainModel) -> Unit
) {
    items(vouchers) { voucher ->
        UserCard(userDomainModel = voucher, onTap = { onItemClick(voucher) })
    }
}

@Composable
private fun UserCard(
    userDomainModel: UserDomainModel,
    onTap: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .height(80.dp)
            .clickable { onTap.invoke() },
        elevation = 1.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box {
                Text(
                    text = "Test",
                    fontSize = 48.sp,
                    fontWeight = Bold,
                    color = seashellPeachOrange,
                    modifier = Modifier
                        .rotate(-15f)
                        .padding(vertical = 24.dp, horizontal = 16.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.Start)
                        .padding(vertical = 13.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    UserInformation(userDomainModel)
                }
            }
        }
    }
}

@Composable
private fun UserInformation(user: UserDomainModel) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(fraction = 0.75f),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = user.name,
            fontSize = 14.sp,
            lineHeight = 22.sp,
            style = MaterialTheme.typography.h2,
            fontWeight = W500,
            color = black500,
            maxLines = 2,
        )
        Text(
            text = user.id,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = dodgerBlue,
        )
    }
}