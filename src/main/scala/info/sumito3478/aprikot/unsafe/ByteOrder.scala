/* Copyright (C) 2013 sumito3478 <sumito3478@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package info.sumito3478.aprikot.unsafe

sealed trait ByteOrder

case object LittleEndian extends ByteOrder

case object BigEndian extends ByteOrder

object ByteOrder {
  val isLE: Boolean = {
    (0xcafebabe >>> 16) == 0xcafe
  }
  
  val isBE: Boolean = {
    ! isLE
  }
  
  val native: ByteOrder = {
    if(isLE) {
      LittleEndian
    } else {
      BigEndian
    }
  }
}