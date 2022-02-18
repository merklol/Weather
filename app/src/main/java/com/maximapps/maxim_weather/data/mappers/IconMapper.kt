package com.maximapps.maxim_weather.data.mappers

import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.core.Mapper
import com.maximapps.maxim_weather.data.network.IconTypes
import javax.inject.Inject

class IconMapper @Inject constructor() : Mapper<String, Int> {
    override fun map(input: String) = when (input) {
        IconTypes.Day.ClearSky, IconTypes.Night.ClearSky -> R.drawable.few_clouds
        IconTypes.Day.FewClouds, IconTypes.Night.FewClouds -> R.drawable.few_clouds
        IconTypes.Day.ScatteredClouds, IconTypes.Night.ScatteredClouds -> R.drawable.few_clouds
        IconTypes.Day.BrokenClouds, IconTypes.Night.BrokenClouds -> R.drawable.few_clouds
        IconTypes.Day.ShowerRain, IconTypes.Night.ShowerRain -> R.drawable.rain
        IconTypes.Day.Rain, IconTypes.Night.Rain -> R.drawable.rain
        IconTypes.Day.Thunderstorm, IconTypes.Night.Thunderstorm -> R.drawable.thunder
        IconTypes.Day.Snow, IconTypes.Night.Snow -> R.drawable.few_clouds
        IconTypes.Day.Mist, IconTypes.Night.Mist -> R.drawable.few_clouds
        else -> R.drawable.few_clouds
    }
}
