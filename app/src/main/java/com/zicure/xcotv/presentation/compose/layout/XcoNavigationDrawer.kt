package com.zicure.xcotv.presentation.compose.layout

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.IntSize
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.ModalNavigationDrawer
import androidx.tv.material3.NavigationDrawer
import androidx.tv.material3.NavigationDrawerScope
import androidx.tv.material3.rememberDrawerState

@ExperimentalTvMaterial3Api
@Composable
fun XcoNavigationDrawer(
    drawerContent: @Composable NavigationDrawerScope.(DrawerValue) -> Unit,
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    content: @Composable () -> Unit
) {
    Row(modifier = modifier) {
        DrawerSheet(
            drawerState = drawerState,
            content = drawerContent
        )
        content()
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun DrawerSheet(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = remember { DrawerState() },
    sizeAnimationFinishedListener: ((initialValue: IntSize, targetValue: IntSize) -> Unit)? = null,
    content: @Composable NavigationDrawerScope.(DrawerValue) -> Unit
) {
    // indicates that the drawer has been set to its initial state and has grabbed focus if
    // necessary. Controls whether focus is used to decide the state of the drawer going forward.
    var initializationComplete: Boolean by remember { mutableStateOf(false) }
    var focusState by remember { mutableStateOf<FocusState?>(null) }
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(key1 = drawerState.currentValue) {
        initializationComplete = true
    }

    val internalModifier =
        Modifier
            .focusRequester(focusRequester)
            .animateContentSize(finishedListener = sizeAnimationFinishedListener)
            .fillMaxHeight()
            // adding passed-in modifier here to ensure animateContentSize is called before other
            // size based modifiers.
            .then(modifier)
            .onFocusChanged {
                focusState = it

                if (initializationComplete) {
                    drawerState.setValue(if (it.hasFocus) DrawerValue.Open else DrawerValue.Closed)
                }
            }
            .focusGroup()

    Box(modifier = internalModifier) {
        NavigationDrawerScopeImpl(drawerState.currentValue == DrawerValue.Open).apply {
            content(drawerState.currentValue)
        }
    }
}

@ExperimentalTvMaterial3Api // TODO (b/263353219): Remove this before launching beta
interface NavigationDrawerScope {
    /**
     * Whether any item within the [NavigationDrawer] or [ModalNavigationDrawer] is focused
     */
    @get:Suppress("GetterSetterNames")
    val hasFocus: Boolean
}

@OptIn(ExperimentalTvMaterial3Api::class)
internal class NavigationDrawerScopeImpl(
    override val hasFocus: Boolean
) : NavigationDrawerScope
