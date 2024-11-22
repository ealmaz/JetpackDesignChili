package kg.devcats.compose.jetpack_chili.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

interface ChiliColorScheme {

    //Typography
    val primaryText: Color
    val secondaryText: Color
    val valueText: Color
    val markedText: Color
    val linkText: Color
    val errorText: Color

    //Icons
    val primaryIcon: Color

    // ScreenBackground
    val screenBackground: Color
    val surfaceBackground: Color
    val screenSecondary: Color

    //Toolbar
    val toolbarBackground: Color

    // Divider
    val dividerColor: Color

    // Chevron
    val chevronColor: Color

    // CardView attributes
    val cardViewBackground: Color

    // CellView attributes
    val cellViewBackground: Color

    //ShadowContainer
    val shadowContainerContent: Color

    //Checkbox
    val checkBoxCheckedBackground: Color
    val checkBoxUncheckedBorder: Color
    val checkBoxCheckedCheckmark: Color
    val checkBoxDisabled: Color


    //Switch
    val switchCheckedThumb: Color
    val switchCheckedTrack: Color
    val switchUncheckedThumb: Color
    val switchUncheckedTrack: Color
    val switchDisabledCheckedThumb: Color
    val switchDisabledCheckedTrack: Color
    val switchDisabledUncheckedThumb: Color
    val switchDisabledUncheckedTrack: Color

    //Loader
    val loader: Color

    //InputFiled
    val inputFieldBackground: Color
    val inputFieldErrorBackground: Color
    val inputFieldCursorColor: Color


    //Button primary
    val buttonPrimaryContainer: Color
    val buttonPrimaryDisabledContainer: Color
    val buttonPrimaryText: Color

    //Button Component
    val buttonComponentContainer: Color
    val buttonComponentText: Color
    val buttonComponentDisabledText: Color

    //Secondary button
    val buttonSecondaryContainer: Color
    val buttonSecondaryText: Color
    val buttonSecondaryDisabledText: Color

    //Additional button
    val buttonAdditionalContainer: Color
    val buttonAdditionalDisabledContainer: Color
    val buttonAdditionalText: Color
    val buttonAdditionalDisabledText: Color

    //Animated Gradient
    val animatedGradient1: Color
    val animatedGradient2: Color
    val animatedGradient3: Color
    val animatedGradient4: Color

    //BottomSheet
    val bottomSheetBackground: Color

    //QuickAction button
    val quickActionIconBackgroundDisabledColor: Color
    val quickActionIconBackgroundClickedColor: Color
    val quickActionIconBackgroundDefaultColor: Color
    val quickActionIconDisabledColor: Color
    val quickActionIconClickedColor: Color
    val quickActionIconDefaultColor: Color
    val quickActionButtonDisabledTextColor: Color

    //AlertBlockCardView
    val alertNeutralBg: Color
    val alertNeutralContent: Color
    val alertNeutralText: Color
    val alertWarningBg: Color
    val alertWarningContent: Color
    val alertWarningText: Color
    val alertErrorBg: Color
    val alertErrorContent: Color
    val alertErrorText: Color
}

val LocalChiliColorScheme = compositionLocalOf<ChiliColorScheme> { ChiliLightColorScheme() }
val LocalChiliDarkThemeMode = compositionLocalOf<Boolean> { false }

data class ChiliLightColorScheme(
    override val primaryText: Color = black_1,
    override val secondaryText: Color = black_4,
    override val valueText: Color = gray_1,
    override val markedText: Color = black_1,
    override val linkText: Color = magenta_1,
    override val errorText: Color = red_1,

    override val primaryIcon: Color = black_1,

    override val screenBackground: Color = gray_4,
    override val surfaceBackground: Color = white_1,
    override val screenSecondary: Color = gray_9,
    override val toolbarBackground: Color = white_1,
    override val dividerColor: Color = gray_6,
    override val chevronColor: Color = black_7,

    override val cardViewBackground: Color = white_1,
    override val cellViewBackground: Color = white_1,

    override val shadowContainerContent: Color = white_1,

    override val checkBoxCheckedBackground: Color = magenta_1,
    override val checkBoxUncheckedBorder: Color = black_5,
    override val checkBoxCheckedCheckmark: Color = white_1,
    override val checkBoxDisabled: Color = gray_2,

    override val switchCheckedThumb: Color = white_1,
    override val switchCheckedTrack: Color = magenta_1,
    override val switchUncheckedThumb: Color = gray_1,
    override val switchUncheckedTrack: Color = gray_3,
    override val switchDisabledCheckedThumb: Color = white_1,
    override val switchDisabledCheckedTrack: Color = magenta_3,
    override val switchDisabledUncheckedThumb: Color = white_1,
    override val switchDisabledUncheckedTrack: Color = gray_2,

    override val loader: Color = magenta_1,

    override val inputFieldBackground: Color = gray_5,
    override val inputFieldErrorBackground: Color = red_3,
    override val inputFieldCursorColor: Color = magenta_1,

    override val buttonPrimaryContainer: Color = green_1,
    override val buttonPrimaryDisabledContainer: Color = green_3,
    override val buttonPrimaryText: Color = white_1,

    override val buttonComponentContainer: Color = Color.Transparent,
    override val buttonComponentText: Color = blue_1,
    override val buttonComponentDisabledText: Color = blue_1_alpha_50,

    override val buttonSecondaryContainer: Color = Color.Transparent,
    override val buttonSecondaryText: Color = blue_1,
    override val buttonSecondaryDisabledText: Color = blue_1_alpha_50,

    override val buttonAdditionalContainer: Color = gray_5,
    override val buttonAdditionalDisabledContainer: Color = gray_5,
    override val buttonAdditionalText: Color = black_1,
    override val buttonAdditionalDisabledText: Color = gray_1_alpha_50,

    override val animatedGradient1: Color = magenta_1,
    override val animatedGradient2: Color = magenta_3,
    override val animatedGradient3: Color = magenta_5,
    override val animatedGradient4: Color = white_1,

    override val bottomSheetBackground: Color = white_1,

    override val quickActionIconBackgroundDisabledColor: Color = gray_5,
    override val quickActionIconBackgroundClickedColor: Color = gray_9,
    override val quickActionIconBackgroundDefaultColor: Color = gray_9,
    override val quickActionIconDisabledColor: Color = gray_2,
    override val quickActionIconClickedColor: Color = magenta_1,
    override val quickActionIconDefaultColor: Color = black_1,
    override val quickActionButtonDisabledTextColor: Color = gray_2,

    override val alertNeutralBg: Color = blue_6,
    override val alertNeutralContent: Color = blue_1,
    override val alertNeutralText: Color = blue_9,
    override val alertWarningBg: Color = orange_6,
    override val alertWarningContent: Color = orange_1,
    override val alertWarningText: Color = brown_1,
    override val alertErrorBg: Color = red_5,
    override val alertErrorContent: Color = red_1,
    override val alertErrorText: Color = magenta_6,
) : ChiliColorScheme

data class ChiliDarkColorScheme(
    override val primaryText: Color = white_1,
    override val secondaryText: Color = gray_3,
    override val valueText: Color = gray_1,
    override val markedText: Color = white_1,
    override val linkText: Color = magenta_1,
    override val errorText: Color = red_1,

    override val primaryIcon: Color = white_1,

    override val screenBackground: Color = black_2,
    override val surfaceBackground: Color = black_1,
    override val screenSecondary: Color = black_1,
    override val toolbarBackground: Color = black_3,
    override val dividerColor: Color = black_4,
    override val chevronColor: Color = gray_4,

    override val cardViewBackground: Color = black_3,
    override val cellViewBackground: Color = black_3,

    override val shadowContainerContent: Color = black_3,

    override val checkBoxCheckedBackground: Color = magenta_1,
    override val checkBoxUncheckedBorder: Color = gray_3,
    override val checkBoxCheckedCheckmark: Color = black_1,
    override val checkBoxDisabled: Color = gray_1_alpha_50,

    override val switchCheckedThumb: Color = white_1,
    override val switchCheckedTrack: Color = magenta_1,
    override val switchUncheckedThumb: Color = gray_3,
    override val switchUncheckedTrack: Color = black_5,
    override val switchDisabledCheckedThumb: Color = gray_3,
    override val switchDisabledCheckedTrack: Color = magenta_3,
    override val switchDisabledUncheckedThumb: Color = gray_3,
    override val switchDisabledUncheckedTrack: Color = gray_1,

    override val loader: Color = magenta_1,

    override val inputFieldBackground: Color = black_3,
    override val inputFieldErrorBackground: Color = red_2,
    override val inputFieldCursorColor: Color = magenta_1,

    override val buttonPrimaryContainer: Color = green_1,
    override val buttonPrimaryDisabledContainer: Color = green_2,
    override val buttonPrimaryText: Color = white_1,

    override val buttonComponentContainer: Color = Color.Transparent,
    override val buttonComponentText: Color = blue_1,
    override val buttonComponentDisabledText: Color = blue_1_alpha_50,

    override val buttonSecondaryContainer: Color = Color.Transparent,
    override val buttonSecondaryText: Color = blue_1,
    override val buttonSecondaryDisabledText: Color = blue_1_alpha_50,

    override val buttonAdditionalContainer: Color = black_5,
    override val buttonAdditionalDisabledContainer: Color = black_5,
    override val buttonAdditionalText: Color = white_1,
    override val buttonAdditionalDisabledText: Color = gray_1_alpha_50,

    override val animatedGradient1: Color = white_1,
    override val animatedGradient2: Color = gray_2,
    override val animatedGradient3: Color = gray_1,
    override val animatedGradient4: Color = black_3,

    override val bottomSheetBackground: Color = black_2,

    override val quickActionIconBackgroundDisabledColor: Color = black_4,
    override val quickActionIconBackgroundClickedColor: Color = black_4,
    override val quickActionIconBackgroundDefaultColor: Color = black_4,
    override val quickActionIconDisabledColor: Color = black_6,
    override val quickActionIconClickedColor: Color = gray_1,
    override val quickActionIconDefaultColor: Color = white_1,
    override val quickActionButtonDisabledTextColor: Color = gray_1,

    override val alertNeutralBg: Color = blue_7,
    override val alertNeutralContent: Color = blue_8,
    override val alertNeutralText: Color = white_1,
    override val alertWarningBg: Color = orange_7,
    override val alertWarningContent: Color = orange_5,
    override val alertWarningText: Color = white_1,
    override val alertErrorBg: Color = red_6,
    override val alertErrorContent: Color = red_7,
    override val alertErrorText: Color = white_1,
): ChiliColorScheme

