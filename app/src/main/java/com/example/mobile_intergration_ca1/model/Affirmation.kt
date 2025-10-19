import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @StringRes val titleResourceId: Int,
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val continentResourceId: Int
)