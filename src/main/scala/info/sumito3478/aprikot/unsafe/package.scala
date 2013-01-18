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

package info.sumito3478.aprikot

import scala.concurrent.util.Unsafe.{ instance => _unsafe }

import java.lang.{ Integer => JInt }
import java.lang.{ Long => JLong }
import java.lang.{ Short => JShort }

/**
 * An implementation of [[Pointer]] underlined with [[com.sun.jna.Pointer]].
 */
package object unsafe {
  private[this] val le = (0xcafebabe >>> 16) == 0xcafe

  private[this] val be = !le

  implicit class ShortW(val underlined: Short) extends AnyVal {
    def bswap: Short = {
      JShort.reverseBytes(underlined)
    }

    def toLE: Short = {
      if (be) {
        bswap
      } else {
        underlined
      }
    }

    def toBE: Short = {
      if (le) {
        bswap
      } else {
        underlined
      }
    }

    def fromLE: Short = {
      if (be) {
        bswap
      } else {
        underlined
      }
    }

    def fromBE: Short = {
      if (le) {
        bswap
      } else {
        underlined
      }
    }
  }

  implicit class IntW(val underlined: Int) extends AnyVal {
    def bswap: Int = {
      JInt.reverseBytes(underlined)
    }

    def toLE: Int = {
      if (be) {
        bswap
      } else {
        underlined
      }
    }

    def toBE: Int = {
      if (le) {
        bswap
      } else {
        underlined
      }
    }

    def fromLE: Int = {
      if (be) {
        bswap
      } else {
        underlined
      }
    }

    def fromBE: Int = {
      if (le) {
        bswap
      } else {
        underlined
      }
    }
  }

  implicit class LongW(val underlined: Long) extends AnyVal {
    def bswap: Long = {
      JLong.reverseBytes(underlined)
    }

    def toLE: Long = {
      if (be) {
        bswap
      } else {
        underlined
      }
    }

    def toBE: Long = {
      if (le) {
        bswap
      } else {
        underlined
      }
    }

    def fromLE: Long = {
      if (be) {
        bswap
      } else {
        underlined
      }
    }

    def fromBE: Long = {
      if (le) {
        bswap
      } else {
        underlined
      }
    }
  }

  implicit class ArrayOfByteW(val underlined: Array[Byte]) extends AnyVal {
    def memcpy(dest: Pointer, start: Int, len: Int): Unit = {
      var i = start
      val end = start + len
      var cp = dest.p
      while (i < end) {
        _unsafe.putByte(cp, underlined(i))
        i += 1
        cp += 1
      }
    }

    def memcpy(dest: Pointer, start: Int): Unit = {
      memcpy(dest, start, underlined.length - start)
    }

    def memcpy(dest: Pointer): Unit = {
      memcpy(dest, 0, underlined.length)
    }
  }

  implicit class ArrayOfShortW(val underlined: Array[Short]) extends AnyVal {
    def memcpy(dest: Pointer, start: Int, len: Int): Unit = {
      var i = start
      val end = start + len
      var cp = dest.p
      while (i < end) {
        _unsafe.putShort(cp, underlined(i))
        i += 1
        cp += 2
      }
    }

    def memcpy(dest: Pointer, start: Int): Unit = {
      memcpy(dest, start, underlined.length - start)
    }

    def memcpy(dest: Pointer): Unit = {
      memcpy(dest, 0, underlined.length)
    }
  }

  implicit class ArrayOfIntW(val underlined: Array[Int]) extends AnyVal {
    def memcpy(dest: Pointer, start: Int, len: Int): Unit = {
      var i = start
      val end = start + len
      var cp = dest.p
      while (i < end) {
        _unsafe.putInt(cp, underlined(i))
        i += 1
        cp += 4
      }
    }

    def memcpy(dest: Pointer, start: Int): Unit = {
      memcpy(dest, start, underlined.length - start)
    }

    def memcpy(dest: Pointer): Unit = {
      memcpy(dest, 0, underlined.length)
    }
  }

  implicit class ArrayOfLongW(val underlined: Array[Long]) extends AnyVal {
    def memcpy(dest: Pointer, start: Int, len: Int): Unit = {
      var i = start
      val end = start + len
      var cp = dest.p
      while (i < end) {
        _unsafe.putLong(cp, underlined(i))
        i += 1
        cp += 8
      }
    }

    def memcpy(dest: Pointer, start: Int): Unit = {
      memcpy(dest, start, underlined.length - start)
    }

    def memcpy(dest: Pointer): Unit = {
      memcpy(dest, 0, underlined.length)
    }
  }
}