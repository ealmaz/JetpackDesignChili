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
    val disabledText: Color
    val successText: Color
    val warningTextColor: Color

    //Icons
    val primaryIcon: Color

    // ScreenBackground
    val screenBackground: Color
    val surfaceBackground: Color
    val screenSecondary: Color

    // ContentBackground
    val contentPrimary: Color
    val contentSecondary: Color

    val accentPrimaryColor: Color

    //Toolbar
    val toolbarBackground: Color

    // Divider
    val dividerColor: Color
    val borderColor: Color

    // Chevron
    val chevronColor: Color

    // CardView attributes
    val cardViewBackground: Color

    // CellView attributes
    val cellViewBackground: Color

    //ShadowContainer
    val shadowContainerContent: Color

    //DatePickerControl
    val datePickerControl: Color

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

    //OldSwitch
    val materialSwitchCheckedThumb: Color
    val materialSwitchCheckedTrack: Color
    val materialSwitchUncheckedThumb: Color
    val materialSwitchUncheckedTrack: Color
    val materialSwitchDisabledCheckedThumb: Color
    val materialSwitchDisabledCheckedTrack: Color
    val materialSwitchDisabledUncheckedThumb: Color
    val materialSwitchDisabledUncheckedTrack: Color

    //Loader
    val loader: Color
    val loaderAccentColor: Color

    //InputFiled
    val inputFieldPrimaryBg : Color
    val inputFieldSecondaryBg : Color
    val inputFieldBackground: Color
    val inputFieldErrorBackground: Color
    val inputFieldCursorColor: Color


    //Button primary
    val buttonPrimaryContainer: Color
    val buttonPrimaryDisabledContainer: Color
    val buttonPrimaryText: Color
    val buttonPrimaryRipple: Color

    //Button Component
    val buttonComponentContainer: Color
    val buttonComponentText: Color
    val buttonComponentDisabledText: Color

    //Secondary button
    val buttonSecondaryContainer: Color
    val buttonSecondaryText: Color
    val buttonSecondaryDisabledText: Color
    val buttonSecondaryRipple: Color

    //Figma secondary button
    val buttonSecondaryBackground: Color

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

    val animatedCardNoteBg: Color

    //BottomSheet
    val bottomSheetBackground: Color
    val bottomSheetTopIconColor: Color

    //Snackbar
    val snackbarBackground: Color
    val snackbarIndeterminateColor: Color
    val snackbarWarningBackgound: Color
    val snackbarActionText: Color
    val snackbarText: Color

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
    val alertNeutralBorder: Color
    val alertNeutralContent: Color
    val alertNeutralText: Color

    val alertWarningBg: Color
    val alertWarningBorder: Color
    val alertWarningContent: Color
    val alertWarningText: Color

    val alertErrorBg: Color
    val alertErrorBorder: Color
    val alertErrorContent: Color
    val alertErrorText: Color

    //LockBackgroundColor
    val lockNonSelectedBg: Color
    val lockSelectedBg: Color
    val lockErrorBg: Color
    val lockSuccessBg: Color
    val lockBorderColor: Color

    val alertSuccessBg: Color
    val alertSuccessBorder: Color
    val alertSuccessContent: Color
    val alertSuccessText: Color

    val keyColor: Color
    val keyboardBackgroundColor: Color

//    AccountCard
    val toggleIconColor: Color

    //PinKeyboard
    val pinDigitClickedBackground: Color

    //PdfBackgroundColor
    val pdfBackgroundColor: Color
    val accountChevronColor: Color

    //customDialogMaterial2BackgroundColor
    val customDialogBackgroundColor: Color

    //Slider
    val sliderInactiveTrackColor: Color

    //ChiliChipsGroup
    val textChipUnselectedBackgroundColor: Color

    //Status
    val statusSuccessBg: Color

    val statusWarningBg: Color

    val statusErrorBg: Color

    //ChiliTabs
    val tabsContainerBg: Color
    val tabsSelectedTab: Color
    val tabsUnselectedTab: Color
}

val LocalChiliColorScheme = compositionLocalOf<ChiliColorScheme> { ChiliLightColorScheme() }
val LocalChiliDarkThemeMode = compositionLocalOf<Boolean> { false }

data class ChiliLightColorScheme(
    override val primaryText: Color = black_1,
    override val secondaryText: Color = black_7,
    override val valueText: Color = gray_1,
    override val markedText: Color = black_1,
    override val linkText: Color = magenta_1,
    override val errorText: Color = red_1,
    override val disabledText: Color = gray_2,
    override val successText: Color = green_8,
    override val warningTextColor: Color = orange_1,

    override val primaryIcon: Color = black_1,

    override val screenBackground: Color = gray_4,
    override val surfaceBackground: Color = white_1,
    override val screenSecondary: Color = gray_9,

    override val contentPrimary: Color = white_1,
    override val contentSecondary: Color = gray_5,

    override val toolbarBackground: Color = white_1,
    override val dividerColor: Color = gray_6,
    override val borderColor: Color = gray_3,
    override val chevronColor: Color = black_7,

    override val cardViewBackground: Color = white_1,
    override val cellViewBackground: Color = white_1,

    override val shadowContainerContent: Color = white_1,

    override val checkBoxCheckedBackground: Color = magenta_1,
    override val checkBoxUncheckedBorder: Color = gray_2,
    override val checkBoxCheckedCheckmark: Color = white_1,
    override val checkBoxDisabled: Color = gray_2.copy(alpha = 0.5f),

    override val switchCheckedThumb: Color = white_1,
    override val switchCheckedTrack: Color = green_8,
    override val switchUncheckedThumb: Color = white_1,
    override val switchUncheckedTrack: Color = gray_2,
    override val switchDisabledCheckedThumb: Color = white_1,
    override val switchDisabledCheckedTrack: Color = green_13,
    override val switchDisabledUncheckedThumb: Color = white_1,
    override val switchDisabledUncheckedTrack: Color = gray_3,

    override val materialSwitchCheckedThumb: Color = magenta_1,
    override val materialSwitchCheckedTrack: Color = magenta_1_alpha_40,
    override val materialSwitchUncheckedThumb: Color = white_1,
    override val materialSwitchUncheckedTrack: Color = gray_2,
    override val materialSwitchDisabledCheckedThumb: Color = white_1,
    override val materialSwitchDisabledCheckedTrack: Color = magenta_3,
    override val materialSwitchDisabledUncheckedThumb: Color = white_1,
    override val materialSwitchDisabledUncheckedTrack: Color = gray_2,

    override val loader: Color = magenta_1,
    override val loaderAccentColor: Color = folly_2,

    override val inputFieldPrimaryBg: Color = gray_11,
    override val inputFieldSecondaryBg: Color = white_1,
    override val inputFieldBackground: Color = gray_5,
    override val inputFieldErrorBackground: Color = red_3,
    override val inputFieldCursorColor: Color = magenta_1,

    override val buttonPrimaryContainer: Color = green_8,
    override val buttonPrimaryDisabledContainer: Color = green_13,
    override val buttonPrimaryText: Color = white_1,
    override val buttonPrimaryRipple: Color = green_14,

    override val buttonComponentContainer: Color = Color.Transparent,
    override val buttonComponentText: Color = blue_1,
    override val buttonComponentDisabledText: Color = blue_1_alpha_50,

    override val buttonSecondaryContainer: Color = Color.Transparent,
    override val buttonSecondaryText: Color = blue_1,
    override val buttonSecondaryDisabledText: Color = blue_1_alpha_50,
    override val buttonSecondaryRipple: Color = blue_12,

    override val buttonSecondaryBackground: Color = gray_3,

    override val buttonAdditionalContainer: Color = gray_3,
    override val buttonAdditionalDisabledContainer: Color = gray_3,
    override val buttonAdditionalText: Color = black_1,
    override val buttonAdditionalDisabledText: Color = gray_1_alpha_50,

    override val animatedGradient1: Color = magenta_1,
    override val animatedGradient2: Color = magenta_3,
    override val animatedGradient3: Color = magenta_5,
    override val animatedGradient4: Color = white_1,

    override val animatedCardNoteBg: Color = gray_5,

    override val bottomSheetBackground: Color = white_1,
    override val bottomSheetTopIconColor: Color = gray_1,

    override val snackbarBackground: Color = black_5,
    override val snackbarIndeterminateColor: Color = white_1,
    override val snackbarActionText: Color = blue_14,
    override val snackbarWarningBackgound: Color = orange_10,
    override val snackbarText: Color = white_1,

    override val quickActionIconBackgroundDisabledColor: Color = gray_5,
    override val quickActionIconBackgroundClickedColor: Color = gray_9,
    override val quickActionIconBackgroundDefaultColor: Color = gray_9,
    override val quickActionIconDisabledColor: Color = gray_2,
    override val quickActionIconClickedColor: Color = magenta_1,
    override val quickActionIconDefaultColor: Color = black_1,
    override val quickActionButtonDisabledTextColor: Color = gray_2,

    override val datePickerControl: Color = black_5,

    override val alertNeutralBg: Color = blue_6,
    override val alertNeutralBorder: Color = blue_10,
    override val alertNeutralContent: Color = blue_1,
    override val alertNeutralText: Color = blue_9,
    override val alertWarningBg: Color = orange_6,
    override val alertWarningBorder: Color = white_2,
    override val alertWarningContent: Color = orange_1,
    override val alertWarningText: Color = brown_1,
    override val alertErrorBg: Color = red_5,
    override val alertErrorBorder: Color = purple_3,
    override val alertErrorContent: Color = red_1,
    override val alertErrorText: Color = magenta_6,
    override val alertSuccessBg: Color = green_6,
    override val alertSuccessBorder: Color = green_10,
    override val alertSuccessContent: Color = green_8,
    override val alertSuccessText: Color = black_7,

    override val lockNonSelectedBg: Color = white_1,
    override val lockSelectedBg: Color = black_1,
    override val lockErrorBg: Color = red_1,
    override val lockSuccessBg: Color = green_1,
    override val lockBorderColor: Color = black_4,

    override val keyColor: Color = black_2,
    override val keyboardBackgroundColor: Color = gray_4,

    override val pdfBackgroundColor: Color = gray_4,

    override val pinDigitClickedBackground: Color = black_1,

    override val toggleIconColor: Color = black_5,
    override val accentPrimaryColor: Color = folly_1,
    override val accountChevronColor: Color = black_3,

    override val customDialogBackgroundColor: Color = white_1,

    override val sliderInactiveTrackColor: Color = gray_3,

    override val textChipUnselectedBackgroundColor: Color = gray_5,

    override val statusSuccessBg: Color = green_11,

    override val statusWarningBg: Color = orange_8,

    override val statusErrorBg: Color = red_3,

    override val tabsContainerBg: Color = gray_6,
    override val tabsSelectedTab: Color = white_1,
    override val tabsUnselectedTab: Color = gray_6
) : ChiliColorScheme

data class ChiliDarkColorScheme(
    override val primaryText: Color = white_1,
    override val secondaryText: Color = gray_10,
    override val valueText: Color = gray_1,
    override val markedText: Color = white_1,
    override val linkText: Color = magenta_1,
    override val errorText: Color = red_1,
    override val disabledText: Color = black_6,
    override val successText: Color = green_8,
    override val warningTextColor: Color = orange_4,


    override val primaryIcon: Color = white_1,

    override val screenBackground: Color = black_2,
    override val surfaceBackground: Color = black_1,
    override val screenSecondary: Color = black_1,

    override val contentPrimary: Color = black_3,
    override val contentSecondary: Color = black_4,

    override val toolbarBackground: Color = black_3,
    override val dividerColor: Color = black_4,
    override val borderColor: Color = black_5,
    override val chevronColor: Color = gray_4,

    override val cardViewBackground: Color = black_3,
    override val cellViewBackground: Color = black_3,

    override val shadowContainerContent: Color = black_3,

    override val checkBoxCheckedBackground: Color = magenta_1,
    override val checkBoxUncheckedBorder: Color = black_5,
    override val checkBoxCheckedCheckmark: Color = black_1,
    override val checkBoxDisabled: Color = black_5.copy(alpha = 0.5f),

    override val switchCheckedThumb: Color = white_1,
    override val switchCheckedTrack: Color = green_8,
    override val switchUncheckedThumb: Color = white_1,
    override val switchUncheckedTrack: Color = gray_1,
    override val switchDisabledCheckedThumb: Color = white_1,
    override val switchDisabledCheckedTrack: Color = green_2,
    override val switchDisabledUncheckedThumb: Color = white_1,
    override val switchDisabledUncheckedTrack: Color = black_7,

    override val materialSwitchCheckedThumb: Color = magenta_1,
    override val materialSwitchCheckedTrack: Color = magenta_1_alpha_40,
    override val materialSwitchUncheckedThumb: Color = white_1,
    override val materialSwitchUncheckedTrack: Color = gray_2,
    override val materialSwitchDisabledCheckedThumb: Color = gray_3,
    override val materialSwitchDisabledCheckedTrack: Color = magenta_3,
    override val materialSwitchDisabledUncheckedThumb: Color = gray_3,
    override val materialSwitchDisabledUncheckedTrack: Color = gray_1,

    override val loader: Color = magenta_1,
    override val loaderAccentColor: Color = white_1,

    override val inputFieldPrimaryBg: Color = gray_7,
    override val inputFieldSecondaryBg: Color = gray_7,
    override val inputFieldBackground: Color = black_3,
    override val inputFieldErrorBackground: Color = red_2,
    override val inputFieldCursorColor: Color = magenta_1,

    override val buttonPrimaryContainer: Color = green_8,
    override val buttonPrimaryDisabledContainer: Color = green_2,
    override val buttonPrimaryText: Color = white_1,
    override val buttonPrimaryRipple: Color = green_14,

    override val buttonComponentContainer: Color = Color.Transparent,
    override val buttonComponentText: Color = blue_1,
    override val buttonComponentDisabledText: Color = blue_1_alpha_50,

    override val buttonSecondaryContainer: Color = Color.Transparent,
    override val buttonSecondaryText: Color = blue_1,
    override val buttonSecondaryDisabledText: Color = blue_1_alpha_50,
    override val buttonSecondaryRipple: Color = blue_1_alpha_50,

    override val buttonSecondaryBackground: Color = black_7,

    override val buttonAdditionalContainer: Color = black_7,
    override val buttonAdditionalDisabledContainer: Color = black_7,
    override val buttonAdditionalText: Color = white_1,
    override val buttonAdditionalDisabledText: Color = gray_1_alpha_50,

    override val animatedGradient1: Color = white_1,
    override val animatedGradient2: Color = gray_2,
    override val animatedGradient3: Color = gray_1,
    override val animatedGradient4: Color = black_3,

    override val animatedCardNoteBg: Color = black_2,

    override val bottomSheetBackground: Color = black_3,
    override val bottomSheetTopIconColor: Color = gray_1,

    override val snackbarBackground: Color = black_5,
    override val snackbarIndeterminateColor: Color = white_1,
    override val snackbarActionText: Color = blue_14,
    override val snackbarWarningBackgound: Color = orange_11,
    override val snackbarText: Color = white_1,

    override val quickActionIconBackgroundDisabledColor: Color = black_4,
    override val quickActionIconBackgroundClickedColor: Color = black_4,
    override val quickActionIconBackgroundDefaultColor: Color = black_4,
    override val quickActionIconDisabledColor: Color = black_6,
    override val quickActionIconClickedColor: Color = gray_1,
    override val quickActionIconDefaultColor: Color = white_1,
    override val quickActionButtonDisabledTextColor: Color = gray_1,

    override val datePickerControl: Color = black_5,

    override val alertNeutralBg: Color = blue_7,
    override val alertNeutralBorder: Color = blue_11,
    override val alertNeutralContent: Color = blue_8,
    override val alertNeutralText: Color = white_1,
    override val alertWarningBg: Color = orange_7,
    override val alertWarningBorder: Color = folly_5,
    override val alertWarningContent: Color = orange_5,
    override val alertWarningText: Color = white_1,
    override val alertErrorBg: Color = red_6,
    override val alertErrorBorder: Color = red_6,
    override val alertErrorContent: Color = red_7,
    override val alertErrorText: Color = white_1,

    override val lockNonSelectedBg: Color = black_1,
    override val lockSelectedBg: Color = white_1,
    override val lockErrorBg: Color = red_1,
    override val lockSuccessBg: Color = green_1,
    override val lockBorderColor: Color = gray_3,

    override val alertSuccessBg: Color = green_7,
    override val alertSuccessBorder: Color = green_7,
    override val alertSuccessContent: Color = green_9,
    override val alertSuccessText: Color = white_1,

    override val keyColor: Color = gray_3,
    override val keyboardBackgroundColor: Color = black_3,

    override val pdfBackgroundColor: Color = black_2,

    override val pinDigitClickedBackground: Color = white_1,

    override val toggleIconColor: Color = gray_3,
    override val accentPrimaryColor: Color = red_8,
    override val accountChevronColor: Color = gray_4,

    override val customDialogBackgroundColor: Color = black_3,

    override val sliderInactiveTrackColor: Color = black_5,

    override val textChipUnselectedBackgroundColor: Color = black_4,

    override val statusSuccessBg: Color = green_12,

    override val statusWarningBg: Color = orange_9,

    override val statusErrorBg: Color = red_2,

    override val tabsContainerBg: Color = black_3,
    override val tabsSelectedTab: Color = black_4,
    override val tabsUnselectedTab: Color = black_3
): ChiliColorScheme