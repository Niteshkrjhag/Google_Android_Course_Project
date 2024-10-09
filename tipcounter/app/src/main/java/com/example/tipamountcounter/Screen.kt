package com.example.tipamountcounter

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.* // Importing necessary layout components
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.* // Importing Material 3 components
import androidx.compose.runtime.* // Importing Compose runtime components
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.annotations.VisibleForTesting
import java.text.NumberFormat
import kotlin.math.ceil

@OptIn(ExperimentalMaterial3Api::class) // Opt-in for experimental Material3 API usage
@Preview(showBackground = true, showSystemUi = true) // Preview configuration for the UI
@Composable
fun Screen_Tip(modifier: Modifier = Modifier) {
    // State variables to hold user input
    var billAmount by remember {
        mutableStateOf("") // Store the bill amount input as a String
    }
    var tipPercentage by remember {
        mutableStateOf("") // Store the tip percentage input as a String
    }
    var roundTipState by remember {
        mutableStateOf(false) // Store the state of the round tip switch
    }

    // Convert the string inputs to Double for calculation
    val bill = billAmount.toDoubleOrNull() ?: 0.0 // If conversion fails, default to 0.0
    val tipP = tipPercentage.toDoubleOrNull() ?: 0.0 // If conversion fails, default to 0.0

    // Calculate the tip based on user input
    val tip = calculatetip(bill, tipP, roundTipState)

    // Column layout for the main UI
    Column(
        modifier = Modifier
            .statusBarsPadding() // Adjust padding for status bars
            .fillMaxSize() // Fill the available space
            .padding(horizontal = 40.dp) // Horizontal padding
            .verticalScroll(rememberScrollState()) // Make the column scrollable
            .safeDrawingPadding(), // Safe area padding
        horizontalAlignment = Alignment.CenterHorizontally, // Center children horizontally
        verticalArrangement = Arrangement.Center // Center children vertically
    ) {
        // Header Text for the screen
        Text(
            text = stringResource(id = R.string.calculate_tip),
            modifier = Modifier.align(Alignment.Start) // Align text to the start
        )
        Spacer(modifier = Modifier.height(10.dp)) // Spacer for vertical spacing

        // EditTextBox for bill amount
        EditTextBox(
            label = R.string.bill_amount,
            value = billAmount,
            onValued = { billAmount = it }, // Update billAmount on value change
            modifier = Modifier.align(Alignment.Start), // Align to the start
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, // Numeric keyboard
                imeAction = ImeAction.Next // Next action key
            ),
            leadingIcon = R.drawable.money // Leading icon for the TextField
        )
        Spacer(modifier = Modifier.height(10.dp)) // Spacer for vertical spacing

        // EditTextBox for tip percentage
        EditTextBox(
            label = R.string.Tip_Percentage,
            value = tipPercentage,
            onValued = { tipPercentage = it }, // Update tipPercentage on value change
            modifier = Modifier.align(Alignment.Start), // Align to the start
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, // Numeric keyboard
                imeAction = ImeAction.Done // Done action key
            ),
            leadingIcon = R.drawable.percent // Leading icon for the TextField
        )
        Spacer(modifier = Modifier.height(20.dp)) // Spacer for vertical spacing

        // RoundTip component for rounding switch
        RoundTip(
            roundState = roundTipState, // Pass current round state
            onCheck = { roundTipState = it } // Update roundTipState on check change
        )
        Spacer(modifier = Modifier.height(50.dp)) // Spacer for vertical spacing

        // Display the calculated tip amount
        Text(
            text = stringResource(id = R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall // Apply Material theme typography style
        )
    }
}

// Composable for a TextField with a label and leading icon
@Composable
fun EditTextBox(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    value: String,
    onValued: (String) -> Unit, // Callback to update value
    keyboardOptions: KeyboardOptions,
    leadingIcon: Int // Resource ID for the leading icon
) {
    TextField(
        value = value,
        singleLine = true, // Single line input
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), contentDescription = null) }, // Display leading icon
        modifier = modifier.fillMaxWidth(), // Fill the available width
        onValueChange = onValued, // Callback on value change
        label = { Text(stringResource(id = label)) }, // Label for the TextField
        keyboardOptions = keyboardOptions // Keyboard options for the TextField
    )
}

// Function to calculate the tip amount based on inputs
@VisibleForTesting
internal fun calculatetip(bill: Double, tipP: Double = 15.0, roundState: Boolean): String {
    var tip = (bill * tipP / 100) // Calculate tip based on bill and percentage
    if (roundState) {
        tip = ceil(tip) // Round up the tip if roundState is true
    }
    // Format the tip amount as currency and return
    return NumberFormat.getCurrencyInstance().format(tip)
}

// Composable for the round tip switch

@Composable
fun RoundTip(
    roundState: Boolean,
    onCheck: (Boolean) -> Unit, // Callback to update round state
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(), // Fill the available width
        verticalAlignment = Alignment.CenterVertically // Center items vertically in the row
    ) {
        // Text label for the switch
        Text(
            text = stringResource(id = R.string.round_up_tip)
        )
        // Switch for toggling round state
        Switch(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End), // Align switch to the end
            checked = roundState, // Current state of the switch
            onCheckedChange = onCheck // Callback on checked state change
        )
    }
}
