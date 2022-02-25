package com.maximapps.maxim_weather.mainScreen.data.mappers

import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.common.mappers.Mapper
import com.maximapps.maxim_weather.mainScreen.data.network.IconTypes
import javax.inject.Inject

/**
 * Map [IconTypes] to mipmap resources.
 */
class IconMapper @Inject constructor() : Mapper<String, Int> {
    override fun map(input: String) = when (input) {
        IconTypes.Day.ClearSky, IconTypes.Night.ClearSky -> R.mipmap.few_clouds
        IconTypes.Day.FewClouds, IconTypes.Night.FewClouds -> R.mipmap.few_clouds
        IconTypes.Day.ScatteredClouds, IconTypes.Night.ScatteredClouds -> R.mipmap.few_clouds
        IconTypes.Day.BrokenClouds, IconTypes.Night.BrokenClouds -> R.mipmap.few_clouds
        IconTypes.Day.ShowerRain, IconTypes.Night.ShowerRain -> R.mipmap.rain
        IconTypes.Day.Rain, IconTypes.Night.Rain -> R.mipmap.rain
        IconTypes.Day.Thunderstorm, IconTypes.Night.Thunderstorm -> R.mipmap.thunder
        IconTypes.Day.Snow, IconTypes.Night.Snow -> R.mipmap.few_clouds
        IconTypes.Day.Mist, IconTypes.Night.Mist -> R.mipmap.few_clouds
        else -> R.mipmap.few_clouds
    }
}
