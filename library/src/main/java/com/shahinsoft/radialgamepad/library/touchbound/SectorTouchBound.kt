/*
 * Created by Filippo Scognamiglio.
 * Copyright (c) 2020. This file is part of RadialGamePad.
 *
 * RadialGamePad is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RadialGamePad is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with RadialGamePad.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.shahinsoft.radialgamepad.library.touchbound

import com.shahinsoft.radialgamepad.library.math.Sector
import com.shahinsoft.radialgamepad.library.utils.Constants
import com.shahinsoft.radialgamepad.library.math.MathUtils

class SectorTouchBound(private val sector: Sector) : TouchBound {

    override fun contains(x: Float, y: Float): Boolean {
        val currentAngle = MathUtils.angle(sector.center.x, x, sector.center.y, y) % (Constants.PI2)
        val currentDistance = MathUtils.distance(x, sector.center.x, y, sector.center.y)

        return currentDistance > sector.minRadius &&
            currentDistance < sector.maxRadius &&
            MathUtils.isAngleInRange(currentAngle, sector.minAngle, sector.maxAngle)
    }
}